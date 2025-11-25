import fs from 'fs'
import path from 'path'
import { User, Activity, Certificate, Notification, Postulante } from './types'

const DATA_DIR = path.join(process.cwd(), 'data')

// Ensure data directory exists
if (!fs.existsSync(DATA_DIR)) {
  fs.mkdirSync(DATA_DIR, { recursive: true })
}

// File paths
const USERS_FILE = path.join(DATA_DIR, 'users.json')
const ACTIVITIES_FILE = path.join(DATA_DIR, 'activities.json')
const CERTIFICATES_FILE = path.join(DATA_DIR, 'certificates.json')
const NOTIFICATIONS_FILE = path.join(DATA_DIR, 'notifications.json')
const POSTULANTES_FILE = path.join(DATA_DIR, 'postulantes.json')

// Helper functions to read/write JSON files
function readJsonFile<T>(filePath: string): T[] {
  try {
    if (!fs.existsSync(filePath)) {
      return []
    }
    const data = fs.readFileSync(filePath, 'utf-8')
    return JSON.parse(data)
  } catch (error) {
    console.error(`Error reading ${filePath}:`, error)
    return []
  }
}

function writeJsonFile<T>(filePath: string, data: T[]): void {
  try {
    fs.writeFileSync(filePath, JSON.stringify(data, null, 2))
  } catch (error) {
    console.error(`Error writing ${filePath}:`, error)
  }
}

// Database class
class Database {
  // Users
  getUsers(): User[] {
    return readJsonFile<User>(USERS_FILE)
  }

  saveUsers(users: User[]): void {
    writeJsonFile(USERS_FILE, users)
  }

  // Activities
  getActivities(): Activity[] {
    return readJsonFile<Activity>(ACTIVITIES_FILE)
  }

  saveActivities(activities: Activity[]): void {
    writeJsonFile(ACTIVITIES_FILE, activities)
  }

  // Certificates
  getCertificates(): Certificate[] {
    return readJsonFile<Certificate>(CERTIFICATES_FILE)
  }

  saveCertificates(certificates: Certificate[]): void {
    writeJsonFile(CERTIFICATES_FILE, certificates)
  }

  // Notifications
  getNotifications(): Notification[] {
    return readJsonFile<Notification>(NOTIFICATIONS_FILE)
  }

  saveNotifications(notifications: Notification[]): void {
    writeJsonFile(NOTIFICATIONS_FILE, notifications)
  }

  // Postulantes
  getPostulantes(): Postulante[] {
    return readJsonFile<Postulante>(POSTULANTES_FILE)
  }

  savePostulantes(postulantes: Postulante[]): void {
    writeJsonFile(POSTULANTES_FILE, postulantes)
  }

  // Initialize with sample data if empty
  initializeSampleData(): void {
    const users = this.getUsers()
    if (users.length === 0) {
      const sampleUsers: User[] = [
        {
          id: 1,
          nombre: "Juan Pérez",
          email: "juan@email.com",
          contraseña: "$2b$10$AyEDqdg5tozOzy/td6aPKO5c1xxHD.bYp9l4sE9FYHFeAlHUJReha",
          rol: "voluntario",
          ciudad: "Ciudad de México",
          intereses: ["Educación", "Medio Ambiente"],
          fechaRegistro: "2023-06-15T00:00:00.000Z",
          estado: "activo",
          actividadesRealizadas: 12,
          horasAcumuladas: 48,
        },
        {
          id: 2,
          nombre: "María García",
          email: "maria@email.com",
          contraseña: "$2b$10$AyEDqdg5tozOzy/td6aPKO5c1xxHD.bYp9l4sE9FYHFeAlHUJReha",
          rol: "coordinador",
          ciudad: "Bogotá",
          fechaRegistro: "2023-04-20T00:00:00.000Z",
          estado: "activo",
          actividadesRealizadas: 25,
          horasAcumuladas: 0,
        },
        {
          id: 3,
          nombre: "Carlos López",
          email: "carlos@email.com",
          contraseña: "$2b$10$AyEDqdg5tozOzy/td6aPKO5c1xxHD.bYp9l4sE9FYHFeAlHUJReha",
          rol: "administrador",
          ciudad: "Lima",
          fechaRegistro: "2023-01-10T00:00:00.000Z",
          estado: "activo",
          actividadesRealizadas: 0,
          horasAcumuladas: 0,
        },
      ]
      this.saveUsers(sampleUsers)
    }

    const activities = this.getActivities()
    if (activities.length === 0 || activities.length <= 2) {
      const sampleActivities: Activity[] = [
        {
          id: 1,
          titulo: "Limpieza de Playas en Cartagena",
          descripcion: "Únete a nosotros para limpiar las playas de Cartagena y proteger la vida marina. Proporcionaremos todos los materiales necesarios.",
          fecha: "2025-03-15",
          ubicacion: "Cartagena",
          cupos: 30,
          cuposDisponibles: 15,
          tipo: "Presencial",
          categoria: "Medio Ambiente",
          duracion: "4 horas",
          estado: "Convocatoria",
          coordinadorId: 2,
          postulantes: [],
          participantes: [],
          fechaCreacion: "2025-01-01T00:00:00.000Z",
        },
        {
          id: 2,
          titulo: "Taller de Lectura para Niños en Bogotá",
          descripcion: "Ayuda a fomentar el amor por la lectura en niños de 6 a 10 años en bibliotecas de Bogotá.",
          fecha: "2025-04-20",
          ubicacion: "Bogotá",
          cupos: 20,
          cuposDisponibles: 8,
          tipo: "Presencial",
          categoria: "Educación",
          duracion: "3 horas",
          estado: "Convocatoria",
          coordinadorId: 2,
          postulantes: [],
          participantes: [],
          fechaCreacion: "2025-01-02T00:00:00.000Z",
        },
        {
          id: 3,
          titulo: "Reforestación en Medellín",
          descripcion: "Participa en la plantación de árboles en parques de Medellín para mejorar la calidad del aire.",
          fecha: "2025-05-10",
          ubicacion: "Medellín",
          cupos: 25,
          cuposDisponibles: 10,
          tipo: "Presencial",
          categoria: "Medio Ambiente",
          duracion: "5 horas",
          estado: "Convocatoria",
          coordinadorId: 2,
          postulantes: [],
          participantes: [],
          fechaCreacion: "2025-01-03T00:00:00.000Z",
        },
        {
          id: 4,
          titulo: "Apoyo Escolar Virtual",
          descripcion: "Ayuda a estudiantes de secundaria con sus tareas escolares a través de sesiones virtuales.",
          fecha: "2025-06-05",
          ubicacion: "Virtual",
          cupos: 15,
          cuposDisponibles: 12,
          tipo: "Virtual",
          categoria: "Educación",
          duracion: "2 horas",
          estado: "Convocatoria",
          coordinadorId: 2,
          postulantes: [],
          participantes: [],
          fechaCreacion: "2025-01-04T00:00:00.000Z",
        },
        {
          id: 5,
          titulo: "Recolección de Alimentos en Cali",
          descripcion: "Únete a nuestra campaña de recolección de alimentos no perecederos para familias en Cali.",
          fecha: "2025-07-12",
          ubicacion: "Cali",
          cupos: 40,
          cuposDisponibles: 25,
          tipo: "Presencial",
          categoria: "Ayuda Social",
          duracion: "3 horas",
          estado: "Convocatoria",
          coordinadorId: 2,
          postulantes: [],
          participantes: [],
          fechaCreacion: "2025-01-05T00:00:00.000Z",
        },
        {
          id: 6,
          titulo: "Taller de Primeros Auxilios en Barranquilla",
          descripcion: "Aprende y enseña técnicas básicas de primeros auxilios en comunidades de Barranquilla.",
          fecha: "2025-08-18",
          ubicacion: "Barranquilla",
          cupos: 20,
          cuposDisponibles: 18,
          tipo: "Presencial",
          categoria: "Salud",
          duracion: "6 horas",
          estado: "Convocatoria",
          coordinadorId: 2,
          postulantes: [],
          participantes: [],
          fechaCreacion: "2025-01-06T00:00:00.000Z",
        },
        {
          id: 7,
          titulo: "Cuidado de Animales en Pereira",
          descripcion: "Ayuda en refugios de animales de Pereira con alimentación y limpieza.",
          fecha: "2025-09-22",
          ubicacion: "Pereira",
          cupos: 15,
          cuposDisponibles: 10,
          tipo: "Presencial",
          categoria: "Animales",
          duracion: "4 horas",
          estado: "Convocatoria",
          coordinadorId: 2,
          postulantes: [],
          participantes: [],
          fechaCreacion: "2025-01-07T00:00:00.000Z",
        },
        {
          id: 8,
          titulo: "Taller de Programación para Jóvenes",
          descripcion: "Enseña programación básica a jóvenes estudiantes en Bucaramanga.",
          fecha: "2025-10-15",
          ubicacion: "Bucaramanga",
          cupos: 12,
          cuposDisponibles: 8,
          tipo: "Presencial",
          categoria: "Tecnología",
          duracion: "3 horas",
          estado: "Convocatoria",
          coordinadorId: 2,
          postulantes: [],
          participantes: [],
          fechaCreacion: "2025-01-08T00:00:00.000Z",
        },
        {
          id: 9,
          titulo: "Limpieza de Ríos en Bogotá",
          descripcion: "Participa en la limpieza de ríos y quebradas en la capital para preservar el medio ambiente.",
          fecha: "2026-01-20",
          ubicacion: "Bogotá",
          cupos: 35,
          cuposDisponibles: 20,
          tipo: "Presencial",
          categoria: "Medio Ambiente",
          duracion: "5 horas",
          estado: "Convocatoria",
          coordinadorId: 2,
          postulantes: [],
          participantes: [],
          fechaCreacion: "2025-01-09T00:00:00.000Z",
        },
        {
          id: 10,
          titulo: "Mentoría Tecnológica Virtual",
          descripcion: "Ofrece mentoría a jóvenes estudiantes interesados en carreras tecnológicas.",
          fecha: "2026-02-14",
          ubicacion: "Virtual",
          cupos: 10,
          cuposDisponibles: 6,
          tipo: "Virtual",
          categoria: "Tecnología",
          duracion: "1.5 horas",
          estado: "Convocatoria",
          coordinadorId: 2,
          postulantes: [],
          participantes: [],
          fechaCreacion: "2025-01-10T00:00:00.000Z",
        },
        {
          id: 11,
          titulo: "Campaña de Vacunación en Medellín",
          descripcion: "Ayuda en campañas de vacunación y educación sanitaria en barrios de Medellín.",
          fecha: "2026-03-08",
          ubicacion: "Medellín",
          cupos: 25,
          cuposDisponibles: 15,
          tipo: "Presencial",
          categoria: "Salud",
          duracion: "4 horas",
          estado: "Convocatoria",
          coordinadorId: 2,
          postulantes: [],
          participantes: [],
          fechaCreacion: "2025-01-11T00:00:00.000Z",
        },
        {
          id: 12,
          titulo: "Adopción de Mascotas en Cali",
          descripcion: "Organiza eventos de adopción y educa sobre responsabilidad con mascotas en Cali.",
          fecha: "2026-04-12",
          ubicacion: "Cali",
          cupos: 18,
          cuposDisponibles: 12,
          tipo: "Presencial",
          categoria: "Animales",
          duracion: "3 horas",
          estado: "Convocatoria",
          coordinadorId: 2,
          postulantes: [],
          participantes: [],
          fechaCreacion: "2025-01-12T00:00:00.000Z",
        },
        {
          id: 13,
          titulo: "Educación Ambiental en Cartagena",
          descripcion: "Realiza talleres de educación ambiental para niños en escuelas de Cartagena.",
          fecha: "2026-05-25",
          ubicacion: "Cartagena",
          cupos: 22,
          cuposDisponibles: 18,
          tipo: "Presencial",
          categoria: "Educación",
          duracion: "2.5 horas",
          estado: "Convocatoria",
          coordinadorId: 2,
          postulantes: [],
          participantes: [],
          fechaCreacion: "2025-01-13T00:00:00.000Z",
        },
        {
          id: 14,
          titulo: "Ayuda a Ancianos en Barranquilla",
          descripcion: "Visita y brinda compañía a adultos mayores en residencias de Barranquilla.",
          fecha: "2026-06-10",
          ubicacion: "Barranquilla",
          cupos: 20,
          cuposDisponibles: 14,
          tipo: "Presencial",
          categoria: "Ayuda Social",
          duracion: "3 horas",
          estado: "Convocatoria",
          coordinadorId: 2,
          postulantes: [],
          participantes: [],
          fechaCreacion: "2025-01-14T00:00:00.000Z",
        },
        {
          id: 15,
          titulo: "Desarrollo de Apps Móviles",
          descripcion: "Participa en proyectos de desarrollo de aplicaciones móviles para ONGs.",
          fecha: "2026-07-05",
          ubicacion: "Virtual",
          cupos: 8,
          cuposDisponibles: 5,
          tipo: "Virtual",
          categoria: "Tecnología",
          duracion: "4 horas",
          estado: "Convocatoria",
          coordinadorId: 2,
          postulantes: [],
          participantes: [],
          fechaCreacion: "2025-01-15T00:00:00.000Z",
        },
      ]
      this.saveActivities(sampleActivities)
    }

    const certificates = this.getCertificates()
    if (certificates.length === 0) {
      const sampleCertificates: Certificate[] = [
        {
          id: 1,
          usuarioId: 1,
          actividadId: 1,
          titulo: "Limpieza de Playas en Cartagena",
          horas: 4,
          fechaEmision: "2025-03-20",
          estado: "Disponible",
        },
        {
          id: 2,
          usuarioId: 1,
          actividadId: 2,
          titulo: "Taller de Lectura para Niños en Bogotá",
          horas: 3,
          fechaEmision: "2025-04-25",
          estado: "Disponible",
        },
        {
          id: 3,
          usuarioId: 2,
          actividadId: 3,
          titulo: "Reforestación en Medellín",
          horas: 5,
          fechaEmision: "2025-05-15",
          estado: "Disponible",
        },
      ]
      this.saveCertificates(sampleCertificates)
    }
  }
}

export const db = new Database()

// Initialize sample data
db.initializeSampleData()