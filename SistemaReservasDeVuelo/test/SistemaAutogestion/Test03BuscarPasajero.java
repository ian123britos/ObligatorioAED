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
public class Test03BuscarPasajero {    

    private ISistema sistema;

    @Before
    public void setUp() {
        sistema = new ImplementacionSistema();
        sistema.inicializarSistema();
    }

    @Test
    public void testBuscarPasajero_OK() {
        sistema.registrarPasajero("1.345.345-4", "Pedro", 25, Categoria.Esporádico);
        Retorno r = sistema.buscarPasajero("1.345.345-4");
        assertEquals(Retorno.Resultado.OK, r.resultado);
        assertEquals("1.345.345-4;Pedro;25;Esporádico", r.valorString);
    }

    @Test
    public void testBuscarPasajero_OK_FormatoCorto() { 
        sistema.registrarPasajero("845.345-4", "Juan", 25, Categoria.Esporádico);
        Retorno r = sistema.buscarPasajero("845.345-4");
        assertEquals(Retorno.Resultado.OK, r.resultado);
        assertEquals("845.345-4;Juan;25;Esporádico", r.valorString);
    }

    @Test
    public void testBuscarPasajero_Error1_FormatoInvalido() {
        Retorno r = sistema.buscarPasajero("123456");
        assertEquals(Retorno.Resultado.ERROR_1, r.resultado);
    }

    @Test
    public void testBuscarPasajero_Error1_CedulaNull() { //NO PASO
        Retorno r = sistema.buscarPasajero(null);
        assertEquals(Retorno.Resultado.ERROR_1, r.resultado);
    }

    @Test
    public void testBuscarPasajero_Error2_NoExiste() {
        Retorno r = sistema.buscarPasajero("1.345.345-4");
        assertEquals(Retorno.Resultado.ERROR_2, r.resultado);
    }

    @Test
    public void testBuscarPasajero_Error2_ListaConOtrosPasajeros() {
        sistema.registrarPasajero("2.000.000-1", "Carlos", 30, Categoria.Frecuente);
        Retorno r = sistema.buscarPasajero("1.345.345-4");
        assertEquals(Retorno.Resultado.ERROR_2, r.resultado);
    }
}
