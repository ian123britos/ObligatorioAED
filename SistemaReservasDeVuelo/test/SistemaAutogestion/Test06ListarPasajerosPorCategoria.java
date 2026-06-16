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
public class Test06ListarPasajerosPorCategoria {
    
    private ISistema sistema;

    @Before
    public void setUp() {
        sistema = new ImplementacionSistema();
        sistema.inicializarSistema();
    }

    @Test
    public void testListarPorCategoria_ListaVacia() {
        Retorno r = sistema.listarPasajerosPorCategoría(Categoria.Frecuente);
        assertEquals(Retorno.Resultado.OK, r.resultado);
        assertEquals("", r.valorString);
    }

    @Test
    public void testListarPorCategoria_SinPasajerosDeEsaCategoria() {
        sistema.registrarPasajero("1.345.345-4", "Ana", 30, Categoria.Platino);
        Retorno r = sistema.listarPasajerosPorCategoría(Categoria.Frecuente);
        assertEquals(Retorno.Resultado.OK, r.resultado);
        assertEquals("", r.valorString);
    }

    @Test
    public void testListarPorCategoria_SoloMuestraLaSolicitada() {
        sistema.registrarPasajero("1.345.345-4", "Alberto", 62, Categoria.Frecuente);
        sistema.registrarPasajero("2.000.000-1", "Carlos", 40, Categoria.Platino);
        sistema.registrarPasajero("4.985.345-4", "Ana", 25, Categoria.Frecuente);

        Retorno r = sistema.listarPasajerosPorCategoría(Categoria.Frecuente);
        assertEquals(Retorno.Resultado.OK, r.resultado);
        assertTrue(r.valorString.contains("Alberto"));
        assertTrue(r.valorString.contains("Ana"));
        assertFalse(r.valorString.contains("Carlos"));
    }

    @Test
    public void testListarPorCategoria_OrdenAscendente() {
        sistema.registrarPasajero("4.985.345-4", "Ana", 25, Categoria.Frecuente);
        sistema.registrarPasajero("1.345.345-4", "Alberto", 62, Categoria.Frecuente);

        Retorno r = sistema.listarPasajerosPorCategoría(Categoria.Frecuente);
        assertTrue(r.valorString.startsWith("1.345.345-4;Alberto"));
        assertTrue(r.valorString.endsWith("4.985.345-4;Ana;25;Frecuente"));
    }

    @Test
    public void testListarPorCategoria_UnSoloPasajero() {
        sistema.registrarPasajero("1.345.345-4", "Alberto", 62, Categoria.Frecuente);
        Retorno r = sistema.listarPasajerosPorCategoría(Categoria.Frecuente);
        assertEquals("1.345.345-4;Alberto;62;Frecuente", r.valorString);
        assertFalse(r.valorString.contains("|"));
    }
}
