/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SistemaAutogestion;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import Dominio.Categoria;
/**
 *
 * @author hlbc7
 */
public class Test05ListarPasajerosDescendentes {
    
     private ISistema sistema;

    @Before
    public void setUp() {
        sistema = new ImplementacionSistema();
        sistema.inicializarSistema();
    }

    @Test
    public void testListarDescendente_ListaVacia() {
        Retorno r = sistema.listarPasajerosDescendente();
        assertEquals(Retorno.Resultado.OK, r.resultado);
        assertEquals("", r.valorString);
    }

    @Test
    public void testListarDescendente_UnPasajero() {
        sistema.registrarPasajero("1.345.345-4", "Ana", 30, Categoria.Platino);
        Retorno r = sistema.listarPasajerosDescendente();
        assertEquals(Retorno.Resultado.OK, r.resultado);
        assertEquals("1.345.345-4;Ana;30;Platino", r.valorString);
    }

    @Test
    public void testListarDescendente_OrdenCorrecto() { 
        sistema.registrarPasajero("845.345-4", "Juan", 25, Categoria.Esporádico);
        sistema.registrarPasajero("4.985.345-4", "Alberto", 22, Categoria.Frecuente);

        Retorno r = sistema.listarPasajerosDescendente();
        assertEquals(Retorno.Resultado.OK, r.resultado);
        assertTrue(r.valorString.startsWith("4.985.345-4;Alberto"));
        assertTrue(r.valorString.endsWith("845.345-4;Juan;25;Esporádico"));
    }

    @Test
    public void testListarDescendente_SeparadorCorrecto() {
        sistema.registrarPasajero("1.345.345-4", "Ana", 30, Categoria.Platino);
        sistema.registrarPasajero("2.000.000-1", "Carlos", 40, Categoria.Frecuente);

        Retorno r = sistema.listarPasajerosDescendente();
        assertTrue(r.valorString.contains("|"));
        assertFalse(r.valorString.endsWith("|"));
    }
}
