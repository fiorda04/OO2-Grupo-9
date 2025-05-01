package test;

import negocio.RespuestaABM;

import java.time.LocalDate;

import datos.Respuesta;

public class TestTraerRespuestas {
    public static void main(String[] args) {
        RespuestaABM respuestaABM = new RespuestaABM();
        try {
        	
        	
        	
            // traer respuesta por ID
            Respuesta respuestaPorId = respuestaABM.traer(1L);
            if (respuestaPorId != null) {
                System.out.printf("Respuesta encontrada por ID 1: %s\n", respuestaPorId);
            } else {
                System.out.println("No se encontro respuesta con ID 1.");
            }

            // traer respuesta por una ID que no existe
            Respuesta respuestaPorIdNoExiste = respuestaABM.traer(9999L);
            if (respuestaPorIdNoExiste != null) {
                System.out.printf("Respuesta encontrado por ID 9999 %s\n", respuestaPorIdNoExiste);
            } else {
                System.out.println("No se encontro respuesta con ID 9999 (esperado).");
            }

            // traer respuestas por Usuario
            System.out.println("\n--- Prueba de traer respuestas por numero de Usuario ---");
            long idUsuarioABuscar = 2L;
            java.util.List<Respuesta> respuestasPorUsuario = null;
            try {
            	respuestasPorUsuario = respuestaABM.traerPorUsuario(idUsuarioABuscar);
                if (respuestasPorUsuario != null && !respuestasPorUsuario.isEmpty()) {
                    System.out.printf("Listado de respuestas con Usuario ID %d:\n", idUsuarioABuscar);
                    for (Respuesta respuesta : respuestasPorUsuario) {
                        System.out.println(respuesta);
                    }
                } else {
                    System.out.printf("No se encontraron respuestas con Usuario ID %d en la base de datos.\n", idUsuarioABuscar);
                }
            } catch (Exception e) {
                System.err.println("Error al traer respuestas por usuario: " + e.getMessage());
            }
            
            
            
         // traer respuestas por Fecha de Creacion
            System.out.println("\n--- Prueba de traer respuestas por Fecha de Creacion ---");
            LocalDate fechaDeCreacion = LocalDate.of(2020, 6, 20);
            java.util.List<Respuesta> respuestasPorFechaDeCreacion = null;
            try {
            	respuestasPorFechaDeCreacion = respuestaABM.traerRespuestasPorFechaCreacion(fechaDeCreacion);
                if (respuestasPorFechaDeCreacion != null && !respuestasPorFechaDeCreacion.isEmpty()) {
                    System.out.printf("Listado de respuestas por fecha de creacion: %s:\n", fechaDeCreacion);
                    for (Respuesta respuesta : respuestasPorFechaDeCreacion) {
                        System.out.println(respuesta);
                    }
                } else {
                    System.out.printf("No se encontraron respuestas con fecha de creacion: %s en la base de datos.\n", fechaDeCreacion);
                }
            } catch (Exception e) {
                System.err.println("Error al traer respuestas por fecha de creacion: " + e.getMessage());
            }
            
            
            
         // traer respuestas por Fecha de Creacion y Autor
            System.out.println("\n--- Prueba de traer respuestas por Fecha de Creacion y Autor ---");
            LocalDate fechaCreacion = LocalDate.of(2020, 6, 20);
            long idAutor = 2L;
            java.util.List<Respuesta> respuestasPorFechaDeCreacionYAutor = null;
            try {
            	respuestasPorFechaDeCreacionYAutor = respuestaABM.traerRespuestasPorFechaCreacionYAutor(fechaCreacion, idAutor);
                if (respuestasPorFechaDeCreacionYAutor != null && !respuestasPorFechaDeCreacionYAutor.isEmpty()) {
                    System.out.printf("Listado de respuestas por fecha de creacion: %s, y autor: %d\n", fechaDeCreacion, idAutor);
                    for (Respuesta respuesta : respuestasPorFechaDeCreacionYAutor) {
                        System.out.println(respuesta);
                    }
                } else {
                    System.out.printf("No se encontraron respuestas con fecha de creacion: %s, y autor: %d\n", fechaDeCreacion, idAutor);
                }
            } catch (Exception e) {
                System.err.println("Error al traer respuestas por fecha de creacion y autor: " + e.getMessage());
            }
            
            
         // traer respuestas entre Fechas
            System.out.println("\n--- Prueba de traer respuestas entre Fechas ---");
            LocalDate fechaDesde = LocalDate.of(2020, 6, 20);
            LocalDate fechaHasta = LocalDate.of(2020, 6, 21);
            java.util.List<Respuesta> respuestasEntreFechas = null;
            try {
            	respuestasEntreFechas = respuestaABM.traerRespuestasEntreFechas(fechaDesde, fechaHasta);
                if (respuestasEntreFechas != null && !respuestasEntreFechas.isEmpty()) {
                    System.out.printf("Listado de respuestas entre fechas desde: %s, y: %s\n", fechaDesde, fechaHasta);
                    for (Respuesta respuesta : respuestasEntreFechas) {
                        System.out.println(respuesta);
                    }
                } else {
                    System.out.printf("No se encontraron respuestas entre fechas desde: %s, y: %s\n", fechaDesde, fechaHasta);
                }
            } catch (Exception e) {
                System.err.println("Error al traer respuestas entre fechas: " + e.getMessage());
            }
            
            
        	// traer todas las respuestas
        	System.out.println("\n Traer todas las respuesta");
            java.util.List<Respuesta> todasLasRespuestas = respuestaABM.traer();
            if (todasLasRespuestas != null && !todasLasRespuestas.isEmpty()) {
                System.out.println("Listado de todos los respuesta:");
                for (Respuesta respuesta : todasLasRespuestas) {
                    System.out.println(respuesta);
                }
            } else {
                System.out.println("No se encontraron respuestas en la base de datos.");
            }
        	
        } catch (Exception e) {
            e.printStackTrace();
        }

        
        
    }
}

