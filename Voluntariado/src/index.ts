#!/usr/bin/env node

import inquirer from 'inquirer';
import bcrypt from 'bcryptjs';
import jwt from 'jsonwebtoken';
import { db } from './lib/database';
import { User } from './lib/types';

const JWT_SECRET = process.env.JWT_SECRET || 'your-secret-key';

let currentUser: User | null = null;

async function mainMenu() {
  console.log('\n=== Sistema de Gestión de Voluntariado ===\n');

  if (!currentUser) {
    const { action } = await inquirer.prompt([
      {
        type: 'list',
        name: 'action',
        message: 'Selecciona una opción:',
        choices: [
          { name: 'Iniciar sesión', value: 'login' },
          { name: 'Registrarse', value: 'register' },
          { name: 'Salir', value: 'exit' }
        ]
      }
    ]);

    switch (action) {
      case 'login':
        await login();
        break;
      case 'register':
        await register();
        break;
      case 'exit':
        console.log('¡Hasta luego!');
        process.exit(0);
    }
  } else {
    await userMenu();
  }

  await mainMenu();
}

async function login() {
  const answers = await inquirer.prompt([
    { type: 'input', name: 'email', message: 'Email:' },
    { type: 'input', name: 'password', message: 'Contraseña:' }
  ]);

  const { email, password } = answers;

  const users: User[] = db.getUsers();
  const user = users.find((u: User) => u.email.trim().toLowerCase() === email.trim().toLowerCase());

  if (!user) {
    console.log('Usuario no encontrado.');
    return;
  }

  const isValidPassword = await bcrypt.compare(password, user.contraseña);
  if (!isValidPassword) {
    console.log('Contraseña incorrecta.');
    return;
  }

  currentUser = user;
  console.log(`¡Bienvenido, ${user.nombre}!`);
}

async function register() {
  const answers = await inquirer.prompt([
    { type: 'input', name: 'email', message: 'Email:' },
    { type: 'input', name: 'password', message: 'Contraseña:' },
    { type: 'input', name: 'nombre', message: 'Nombre completo:' },
    { type: 'input', name: 'ciudad', message: 'Ciudad:' },
    { type: 'list', name: 'rol', message: 'Rol:', choices: ['voluntario', 'coordinador', 'administrador'], default: 'voluntario' }
  ]);

  const { email, password, nombre, ciudad, rol } = answers;

  const users: User[] = db.getUsers();
  const trimmedEmail = email.trim().toLowerCase();
  if (users.find((u: User) => u.email.trim().toLowerCase() === trimmedEmail)) {
    console.log('El email ya está registrado.');
    return;
  }

  const hashedPassword = await bcrypt.hash(password, 10);
  const user: User = {
    id: Math.max(...users.map((u: User) => u.id), 0) + 1,
    nombre,
    email: trimmedEmail,
    contraseña: hashedPassword,
    rol,
    ciudad,
    intereses: [],
    fechaRegistro: new Date().toISOString(),
    estado: 'activo',
    actividadesRealizadas: 0,
    horasAcumuladas: 0,
  };
  users.push(user);
  db.saveUsers(users);

  console.log('Usuario registrado exitosamente!');
  currentUser = user;
}

async function userMenu() {
  if (!currentUser) return;

  const baseChoices = [
    { name: 'Ver actividades disponibles', value: 'activities' },
    { name: 'Ver mi perfil', value: 'profile' },
    { name: 'Ver mis postulaciones', value: 'applications' },
    { name: 'Ver mis certificados', value: 'certificates' },
    { name: 'Cerrar sesión', value: 'logout' }
  ];

  if (currentUser.rol === 'coordinador' || currentUser.rol === 'administrador') {
    baseChoices.splice(4, 0, { name: 'Gestionar actividades', value: 'manage_activities' });
  }

  if (currentUser.rol === 'administrador') {
    baseChoices.splice(5, 0, { name: 'Gestionar usuarios', value: 'manage_users' });
  }

  const { action } = await inquirer.prompt([
    {
      type: 'list',
      name: 'action',
      message: `Menú principal - ${currentUser.nombre} (${currentUser.rol}):`,
      choices: baseChoices
    }
  ]);

  switch (action) {
    case 'activities':
      await listActivities();
      break;
    case 'profile':
      await viewProfile();
      break;
    case 'applications':
      await viewApplications();
      break;
    case 'certificates':
      await viewCertificates();
      break;
    case 'manage_activities':
      await manageActivities();
      break;
    case 'manage_users':
      await manageUsers();
      break;
    case 'logout':
      currentUser = null;
      console.log('Sesión cerrada.');
      return;
  }
}

async function listActivities() {
  const activities = db.getActivities();
  console.log('\n=== Actividades Disponibles ===\n');

  activities.forEach((act, index) => {
    console.log(`${index + 1}. ${act.titulo}`);
    console.log(`   Descripción: ${act.descripcion}`);
    console.log(`   Fecha: ${act.fecha}`);
    console.log(`   Ubicación: ${act.ubicacion}`);
    console.log(`   Cupos disponibles: ${act.cuposDisponibles}/${act.cupos}`);
    console.log(`   Categoría: ${act.categoria}`);
    console.log(`   Duración: ${act.duracion}\n`);
  });

  if (activities.length > 0) {
    const { apply } = await inquirer.prompt([
      { type: 'confirm', name: 'apply', message: '¿Deseas postularte a una actividad?', default: false }
    ]);

    if (apply) {
      const { activityIndex } = await inquirer.prompt([
        { type: 'number', name: 'activityIndex', message: 'Ingresa el número de la actividad:' }
      ]);

      const activity = activities[activityIndex - 1];
      if (activity) {
        const applications = db.getPostulantes();
        applications.push({
          id: Math.max(...applications.map((a: any) => a.id), 0) + 1,
          usuarioId: currentUser!.id,
          actividadId: activity.id,
          fechaPostulacion: new Date().toISOString(),
          estado: 'Pendiente'
        });
        db.savePostulantes(applications);
        console.log('¡Postulación enviada exitosamente!');
      }
    }
  }
}

async function viewProfile() {
  console.log('\n=== Mi Perfil ===\n');
  console.log(`Nombre: ${currentUser!.nombre}`);
  console.log(`Email: ${currentUser!.email}`);
  console.log(`Rol: ${currentUser!.rol}`);
  console.log(`Ciudad: ${currentUser!.ciudad}`);
  console.log(`Actividades realizadas: ${currentUser!.actividadesRealizadas}`);
  console.log(`Horas acumuladas: ${currentUser!.horasAcumuladas}`);
  console.log(`Estado: ${currentUser!.estado}`);
  console.log(`Fecha de registro: ${new Date(currentUser!.fechaRegistro).toLocaleDateString()}`);
}

async function viewApplications() {
  const applications = db.getPostulantes().filter(app => app.usuarioId === currentUser!.id);
  const activities = db.getActivities();

  console.log('\n=== Mis Postulaciones ===\n');

  applications.forEach(app => {
    const activity = activities.find(act => act.id === app.actividadId);
    if (activity) {
      console.log(`Actividad: ${activity.titulo}`);
      console.log(`Estado: ${app.estado}`);
      console.log(`Fecha de postulación: ${new Date(app.fechaPostulacion).toLocaleDateString()}\n`);
    }
  });
}

async function viewCertificates() {
  const certificates = db.getCertificates().filter(cert => cert.usuarioId === currentUser!.id);
  const activities = db.getActivities();

  console.log('\n=== Mis Certificados ===\n');

  certificates.forEach(cert => {
    const activity = activities.find(act => act.id === cert.actividadId);
    if (activity) {
      console.log(`Actividad: ${activity.titulo}`);
      console.log(`Horas: ${cert.horas}`);
      console.log(`Fecha de emisión: ${cert.fechaEmision}`);
      console.log(`Estado: ${cert.estado}\n`);
    }
  });
}

async function manageActivities() {
  const { action } = await inquirer.prompt([
    {
      type: 'list',
      name: 'action',
      message: 'Gestión de actividades:',
      choices: [
        { name: 'Ver todas las actividades', value: 'list' },
        { name: 'Crear nueva actividad', value: 'create' },
        { name: 'Editar actividad', value: 'edit' },
        { name: 'Ver postulaciones', value: 'applications' },
        { name: 'Volver', value: 'back' }
      ]
    }
  ]);

  switch (action) {
    case 'list':
      const activities = db.getActivities();
      console.log('\n=== Todas las Actividades ===\n');
      activities.forEach(act => {
        console.log(`${act.id}. ${act.titulo} - Estado: ${act.estado}`);
      });
      break;
    case 'create':
      await createActivity();
      break;
    case 'edit':
      await editActivity();
      break;
    case 'applications':
      await manageApplications();
      break;
  }
}

async function createActivity() {
  const answers = await inquirer.prompt([
    { type: 'input', name: 'titulo', message: 'Título de la actividad:' },
    { type: 'input', name: 'descripcion', message: 'Descripción:' },
    { type: 'input', name: 'fecha', message: 'Fecha (YYYY-MM-DD):' },
    { type: 'input', name: 'ubicacion', message: 'Ubicación:' },
    { type: 'number', name: 'cupos', message: 'Número de cupos:' },
    { type: 'list', name: 'tipo', message: 'Tipo:', choices: ['Virtual', 'Presencial'] },
    { type: 'input', name: 'categoria', message: 'Categoría:' },
    { type: 'input', name: 'duracion', message: 'Duración:' },
    { type: 'list', name: 'estado', message: 'Estado:', choices: ['Borrador', 'Convocatoria', 'En ejecución', 'Finalizada', 'Cancelada'], default: 'Borrador' }
  ]);

  const activities = db.getActivities();
  const newActivity = {
    id: Math.max(...activities.map(act => act.id), 0) + 1,
    titulo: answers.titulo,
    descripcion: answers.descripcion,
    fecha: answers.fecha,
    ubicacion: answers.ubicacion,
    cupos: answers.cupos,
    cuposDisponibles: answers.cupos,
    tipo: answers.tipo,
    categoria: answers.categoria,
    duracion: answers.duracion,
    estado: answers.estado,
    coordinadorId: currentUser!.id,
    postulantes: [],
    participantes: [],
    fechaCreacion: new Date().toISOString()
  };

  activities.push(newActivity);
  db.saveActivities(activities);
  console.log('Actividad creada exitosamente!');
}

async function editActivity() {
  const activities = db.getActivities();
  if (activities.length === 0) {
    console.log('No hay actividades para editar.');
    return;
  }

  const choices = activities.map(act => ({
    name: `${act.id}. ${act.titulo} - Estado: ${act.estado}`,
    value: act.id
  }));

  const { activityId } = await inquirer.prompt([
    {
      type: 'list',
      name: 'activityId',
      message: 'Selecciona la actividad a editar:',
      choices
    }
  ]);

  const activity = activities.find(act => act.id === activityId);
  if (!activity) return;

  const answers = await inquirer.prompt([
    { type: 'input', name: 'titulo', message: 'Título:', default: activity.titulo },
    { type: 'input', name: 'descripcion', message: 'Descripción:', default: activity.descripcion },
    { type: 'input', name: 'fecha', message: 'Fecha (YYYY-MM-DD):', default: activity.fecha },
    { type: 'input', name: 'ubicacion', message: 'Ubicación:', default: activity.ubicacion },
    { type: 'number', name: 'cupos', message: 'Número de cupos:', default: activity.cupos },
    { type: 'list', name: 'tipo', message: 'Tipo:', choices: ['Virtual', 'Presencial'], default: activity.tipo },
    { type: 'input', name: 'categoria', message: 'Categoría:', default: activity.categoria },
    { type: 'input', name: 'duracion', message: 'Duración:', default: activity.duracion },
    { type: 'list', name: 'estado', message: 'Estado:', choices: ['Borrador', 'Convocatoria', 'En ejecución', 'Finalizada', 'Cancelada'], default: activity.estado }
  ]);

  // Update activity
  activity.titulo = answers.titulo;
  activity.descripcion = answers.descripcion;
  activity.fecha = answers.fecha;
  activity.ubicacion = answers.ubicacion;
  activity.cupos = answers.cupos;
  activity.cuposDisponibles = answers.cupos; // Reset available spots
  activity.tipo = answers.tipo;
  activity.categoria = answers.categoria;
  activity.duracion = answers.duracion;
  activity.estado = answers.estado;

  db.saveActivities(activities);
  console.log('Actividad actualizada exitosamente!');
}

async function manageApplications() {
  const allApplications = db.getPostulantes();
  console.log('\n=== Todas las Postulaciones ===\n');

  if (allApplications.length === 0) {
    console.log('No hay postulaciones.');
    return;
  }

  const choices = allApplications.map(app => {
    const activity = db.getActivities().find(act => act.id === app.actividadId);
    const user = db.getUsers().find(u => u.id === app.usuarioId);
    return {
      name: `${activity?.titulo || 'Actividad desconocida'} - ${user?.nombre || 'Usuario desconocido'} - Estado: ${app.estado}`,
      value: app.id.toString()
    };
  });

  choices.push({ name: 'Volver', value: 'back' });

  const { applicationId } = await inquirer.prompt([
    {
      type: 'list',
      name: 'applicationId',
      message: 'Selecciona una postulación:',
      choices
    }
  ]);

  if (applicationId === 'back') return;

  const application = allApplications.find(app => app.id === parseInt(applicationId));
  if (!application) return;

  const { action } = await inquirer.prompt([
    {
      type: 'list',
      name: 'action',
      message: 'Acción:',
      choices: [
        { name: 'Aprobar postulación', value: 'approve' },
        { name: 'Rechazar postulación', value: 'reject' },
        { name: 'Crear certificado (si actividad completada)', value: 'certificate' },
        { name: 'Volver', value: 'back' }
      ]
    }
  ]);

  switch (action) {
    case 'approve':
      application.estado = 'Aprobado';
      db.savePostulantes(allApplications);
      console.log('Postulación aprobada!');
      break;
    case 'reject':
      application.estado = 'Rechazado';
      db.savePostulantes(allApplications);
      console.log('Postulación rechazada!');
      break;
    case 'certificate':
      await createCertificate(application);
      break;
  }
}

async function createCertificate(application: any) {
  const activity = db.getActivities().find(act => act.id === application.actividadId);
  const user = db.getUsers().find(u => u.id === application.usuarioId);

  if (!activity || !user) {
    console.log('Actividad o usuario no encontrado.');
    return;
  }

  const certificates = db.getCertificates();
  const existingCert = certificates.find(cert => cert.usuarioId === user.id && cert.actividadId === activity.id);

  if (existingCert) {
    console.log('Ya existe un certificado para esta actividad.');
    return;
  }

  const { horas } = await inquirer.prompt([
    { type: 'number', name: 'horas', message: 'Horas de voluntariado:', default: 4 }
  ]);

  const newCert = {
    id: Math.max(...certificates.map(cert => cert.id), 0) + 1,
    usuarioId: user.id,
    actividadId: activity.id,
    titulo: activity.titulo,
    horas,
    fechaEmision: new Date().toISOString().split('T')[0],
    estado: 'Disponible' as const
  };

  certificates.push(newCert);
  db.saveCertificates(certificates);

  // Update user stats
  user.actividadesRealizadas += 1;
  user.horasAcumuladas += horas;
  db.saveUsers(db.getUsers());

  console.log(`Certificado creado para ${user.nombre} por la actividad "${activity.titulo}"!`);
}

async function manageUsers() {
  const { action } = await inquirer.prompt([
    {
      type: 'list',
      name: 'action',
      message: 'Gestión de usuarios:',
      choices: [
        { name: 'Ver todos los usuarios', value: 'list' },
        { name: 'Cambiar rol de usuario', value: 'change_role' },
        { name: 'Volver', value: 'back' }
      ]
    }
  ]);

  switch (action) {
    case 'list':
      const users = db.getUsers();
      console.log('\n=== Todos los Usuarios ===\n');
      users.forEach(user => {
        console.log(`${user.id}. ${user.nombre} (${user.email}) - Rol: ${user.rol} - Estado: ${user.estado}`);
      });
      break;
    case 'change_role':
      await changeUserRole();
      break;
  }
}

async function changeUserRole() {
  const users = db.getUsers().filter(u => u.id !== currentUser!.id); // No cambiar su propio rol
  if (users.length === 0) {
    console.log('No hay otros usuarios para modificar.');
    return;
  }

  const choices = users.map(user => ({
    name: `${user.id}. ${user.nombre} (${user.email}) - Rol actual: ${user.rol}`,
    value: user.id
  }));

  const { userId } = await inquirer.prompt([
    {
      type: 'list',
      name: 'userId',
      message: 'Selecciona el usuario:',
      choices
    }
  ]);

  const { newRole } = await inquirer.prompt([
    {
      type: 'list',
      name: 'newRole',
      message: 'Nuevo rol:',
      choices: ['voluntario', 'coordinador', 'administrador']
    }
  ]);

  const user = users.find(u => u.id === userId);
  if (user) {
    user.rol = newRole;
    db.saveUsers(db.getUsers());
    console.log(`Rol de ${user.nombre} cambiado a ${newRole} exitosamente!`);
  }
}

// Iniciar la aplicación
mainMenu().catch(console.error);