/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SistemaAutogestion;

import Dominio.Categoria;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author hlbc7
 */
public class Test04ListarPasajerosAscendente {
    private ISistema sistema;

    @Before
    public void setUp() {
        sistema = new ImplementacionSistema();
        sistema.inicializarSistema();
    }

    @Test
    public void testListarAscendente_ListaVacia() {
        Retorno r = sistema.listarPasajerosAscendente();
        assertEquals(Retorno.Resultado.OK, r.resultado);
        assertEquals("", r.valorString);
    }

    @Test
    public void testListarAscendente_UnPasajero() {
        sistema.registrarPasajero("1.345.345-4", "Ana", 30, Categoria.Platino);
        Retorno r = sistema.listarPasajerosAscendente();
        assertEquals(Retorno.Resultado.OK, r.resultado);
        assertEquals("1.345.345-4;Ana;30;Platino", r.valorString);
    }

    @Test
    public void testListarAscendente_OrdenCorrecto() { //NO PASO
        // Se insertan en desorden, deben salir ordenados
        sistema.registrarPasajero("4.985.345-4", "Camila", 30, Categoria.Frecuente);
        sistema.registrarPasajero("845.345-4", "Juan", 25, Categoria.Esporádico);

        Retorno r = sistema.listarPasajerosAscendente();
        assertEquals(Retorno.Resultado.OK, r.resultado);
        assertTrue(r.valorString.startsWith("845.345-4;Juan"));
        assertTrue(r.valorString.endsWith("4.985.345-4;Camila;30;Frecuente"));
    }

    @Test
    public void testListarAscendente_SeparadorCorrecto() {
        sistema.registrarPasajero("1.345.345-4", "Ana", 30, Categoria.Platino);
        sistema.registrarPasajero("2.000.000-1", "Carlos", 40, Categoria.Frecuente);

        Retorno r = sistema.listarPasajerosAscendente();
        assertTrue(r.valorString.contains("|"));
        // No debe terminar en |
        assertFalse(r.valorString.endsWith("|"));
    }
}
