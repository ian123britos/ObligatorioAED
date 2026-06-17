/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SistemaAutogestion;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author hlbc7
 */
public class Test15embarqueYDespegueDeVuelo {
    
    private ISistema sistema;

    @Before
    public void setUp() {
        sistema = new ImplementacionSistema();
        sistema.inicializarSistema();
        sistema.registrarAeropuerto("MVD", "Carrasco");
        sistema.registrarAeropuerto("EZE", "Ezeiza");
        sistema.registrarVuelo("MVD", "EZE", "VU001", 100, 500);
        sistema.abrirVuelo("VU001");
        sistema.cerrarVuelo("VU001"); // queda encolado en MVD
    }

    @Test
    public void testEmbarque_OK() {
        Retorno r = sistema.embarqueYDespegueDeVuelo("MVD");
        assertEquals(Retorno.Resultado.OK, r.resultado);
    }

    @Test
    public void testEmbarque_OK_RetornaCodigoVuelo() {
        Retorno r = sistema.embarqueYDespegueDeVuelo("MVD");
        assertEquals("VU001", r.valorString);
    }

    @Test
    public void testEmbarque_OK_ValorEnteroEsCero_ColaQuedaVacia() {
        Retorno r = sistema.embarqueYDespegueDeVuelo("MVD");
        assertEquals(0, r.valorEntero);
    }

    @Test
    public void testEmbarque_OK_VueloQuedaFinalizado() {
        sistema.embarqueYDespegueDeVuelo("MVD");
        Retorno r = sistema.obtenerInformacionDeVuelo("VU001");
        assertTrue(r.valorString.contains("FINALIZADO"));
    }

    @Test
    public void testEmbarque_OK_OrdenLlegada_PrimeroEnEntrarPrimeroEnSalir() {
        // VU002 cierra después de VU001, debe embarcar segundo
        sistema.registrarVuelo("MVD", "EZE", "VU002", 50, 300);
        sistema.abrirVuelo("VU002");
        sistema.cerrarVuelo("VU002");

        Retorno r = sistema.embarqueYDespegueDeVuelo("MVD");
        assertEquals("VU001", r.valorString);
        assertEquals(1, r.valorEntero);
    }

    @Test
    public void testEmbarque_OK_SegundoVueloEmbarca() {
        sistema.registrarVuelo("MVD", "EZE", "VU002", 50, 300);
        sistema.abrirVuelo("VU002");
        sistema.cerrarVuelo("VU002");

        sistema.embarqueYDespegueDeVuelo("MVD"); // saca VU001
        Retorno r = sistema.embarqueYDespegueDeVuelo("MVD"); // saca VU002
        assertEquals("VU002", r.valorString);
        assertEquals(0, r.valorEntero);
    }

    @Test
    public void testEmbarque_Error1_CodigoNull() {
        Retorno r = sistema.embarqueYDespegueDeVuelo(null);
        assertEquals(Retorno.Resultado.ERROR_1, r.resultado);
    }

    @Test
    public void testEmbarque_Error1_CodigoVacio() {
        Retorno r = sistema.embarqueYDespegueDeVuelo("  ");
        assertEquals(Retorno.Resultado.ERROR_1, r.resultado);
    }

    @Test
    public void testEmbarque_Error2_AeropuertoNoExiste() {
        Retorno r = sistema.embarqueYDespegueDeVuelo("XXX");
        assertEquals(Retorno.Resultado.ERROR_2, r.resultado);
    }

    @Test
    public void testEmbarque_Error3_ColaVacia() {
        // EZE no tiene vuelos en cola
        Retorno r = sistema.embarqueYDespegueDeVuelo("EZE");
        assertEquals(Retorno.Resultado.ERROR_3, r.resultado);
    }

    @Test
    public void testEmbarque_Error3_DespuesDeVaciarCola() {
        sistema.embarqueYDespegueDeVuelo("MVD"); // saca el único vuelo
        Retorno r = sistema.embarqueYDespegueDeVuelo("MVD"); // cola vacía
        assertEquals(Retorno.Resultado.ERROR_3, r.resultado);
    }
}
