export interface User {
  id: number
  nombre: string
  email: string
  contraseña: string
  rol: 'voluntario' | 'coordinador' | 'administrador'
  ciudad: string
  intereses?: string[]
  telefono?: string
  tipoDocumento?: string
  numeroDocumento?: string
  fechaRegistro: string
  estado: 'activo' | 'inactivo'
  actividadesRealizadas: number
  horasAcumuladas: number
}

export interface Activity {
  id: number
  titulo: string
  descripcion: string
  fecha: string
  ubicacion: string
  cupos: number
  cuposDisponibles: number
  tipo: 'Virtual' | 'Presencial'
  categoria: string
  duracion: string
  estado: 'Borrador' | 'Convocatoria' | 'En ejecución' | 'Finalizada' | 'Cancelada'
  coordinadorId: number
  postulantes: Postulante[]
  participantes: number[]
  fechaCreacion: string
}

export interface Postulante {
  id: number
  usuarioId: number
  actividadId: number
  estado: 'Pendiente' | 'Aprobado' | 'Rechazado'
  fechaPostulacion: string
}

export interface Certificate {
  id: number
  usuarioId: number
  actividadId: number
  titulo: string
  horas: number
  fechaEmision: string
  estado: 'Disponible' | 'Descargado'
}

export interface Notification {
  id: number
  usuarioId: number
  tipo: 'actividad_nueva' | 'postulacion_aprobada' | 'certificado_disponible' | 'recordatorio'
  titulo: string
  mensaje: string
  fecha: string
  leida: boolean
}

export interface AuthResponse {
  success: boolean
  user?: User
  token?: string
  message?: string
}

export interface ApiResponse<T = any> {
  success: boolean
  data?: T
  message?: string
  error?: string
}