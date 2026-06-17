/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SistemaAutogestion;

import Dominio.Clase;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author hlbc7
 */
public class Test16consultaDisponibilidad {

    private ISistema sistema;

    // Matriz de 6 filas (A-F) y 26 columnas
    // 0 = libre, 1 = ocupado

    @Before
    public void setUp() {
        sistema = new ImplementacionSistema();
        sistema.inicializarSistema();
    }

    @Test
    public void testConsulta_Error1_CantidadCero() {
        int[][] matriz = new int[6][26];
        Retorno r = sistema.consultaDisponibilidad(matriz, 0, Clase.EJECUTIVA);
        assertEquals(Retorno.Resultado.ERROR_1, r.resultado);
    }

    @Test
    public void testConsulta_Error1_CantidadNegativa() {
        int[][] matriz = new int[6][26];
        Retorno r = sistema.consultaDisponibilidad(matriz, -1, Clase.EJECUTIVA);
        assertEquals(Retorno.Resultado.ERROR_1, r.resultado);
    }

    @Test
    public void testConsulta_OK_EjecutivaTodosLibres_Cantidad3() {
        // Toda la matriz libre
        int[][] matriz = new int[6][26]; // todos 0
        Retorno r = sistema.consultaDisponibilidad(matriz, 3, Clase.EJECUTIVA);
        assertEquals(Retorno.Resultado.OK, r.resultado);
        // Por cada columna ejecutiva (4-10) hay 4 opciones de 3 contiguos en 6 filas
        // 7 columnas x 4 opciones = 28
        assertEquals(28, r.valorEntero);
        assertTrue(r.valorString.contains("A4-B4-C4"));
        assertTrue(r.valorString.contains("B4-C4-D4"));
        assertTrue(r.valorString.contains("C4-D4-E4"));
        assertTrue(r.valorString.contains("D4-E4-F4"));
    }

    @Test
    public void testConsulta_OK_SinDisponibilidad() {
        // Toda la zona ejecutiva ocupada
        int[][] matriz = new int[6][26];
        for (int fila = 0; fila < 6; fila++) {
            for (int col = 3; col <= 9; col++) {
                matriz[fila][col] = 1;
            }
        }
        Retorno r = sistema.consultaDisponibilidad(matriz, 2, Clase.EJECUTIVA);
        assertEquals(Retorno.Resultado.OK, r.resultado);
        assertEquals(0, r.valorEntero);
        assertEquals("", r.valorString);
    }

    @Test
    public void testConsulta_OK_Cantidad1_UnaFilaLibre() {
        int[][] matriz = new int[6][26];
        // Ocupar toda la ejecutiva menos fila B columna 4 (índice fila 1, col 3)
        for (int fila = 0; fila < 6; fila++) {
            for (int col = 3; col <= 9; col++) {
                matriz[fila][col] = 1;
            }
        }
        matriz[1][3] = 0; // B4 libre
        Retorno r = sistema.consultaDisponibilidad(matriz, 1, Clase.EJECUTIVA);
        assertEquals(Retorno.Resultado.OK, r.resultado);
        assertEquals(1, r.valorEntero);
        assertEquals("B4", r.valorString);
    }

    @Test
    public void testConsulta_OK_PrimeraClase() {
        int[][] matriz = new int[6][26];
        // Solo dejar libre fila A y B en columna 1 (índice 0)
        for (int fila = 0; fila < 6; fila++) {
            for (int col = 0; col <= 2; col++) {
                matriz[fila][col] = 1;
            }
        }
        matriz[0][0] = 0; // A1 libre
        matriz[1][0] = 0; // B1 libre
        Retorno r = sistema.consultaDisponibilidad(matriz, 2, Clase.PRIMERA);
        assertEquals(Retorno.Resultado.OK, r.resultado);
        assertEquals(1, r.valorEntero);
        assertEquals("A1-B1", r.valorString);
    }

    @Test
    public void testConsulta_OK_CantidadMayorQueFilas() {
        int[][] matriz = new int[6][26];
        Retorno r = sistema.consultaDisponibilidad(matriz, 10, Clase.EJECUTIVA);
        assertEquals(Retorno.Resultado.OK, r.resultado);
        assertEquals(0, r.valorEntero);
        assertEquals("", r.valorString);
    }
}
