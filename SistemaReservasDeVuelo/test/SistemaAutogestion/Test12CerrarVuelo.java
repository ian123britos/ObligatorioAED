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
public class Test12CerrarVuelo {
    
     private ISistema sistema;

    @Before
    public void setUp() {
        sistema = new ImplementacionSistema();
        sistema.inicializarSistema();
        sistema.registrarAeropuerto("MVD", "Carrasco");
        sistema.registrarAeropuerto("EZE", "Ezeiza");
        sistema.registrarVuelo("MVD", "EZE", "VU001", 100, 500);
        sistema.abrirVuelo("VU001");
    }

    @Test
    public void testCerrarVuelo_OK() {
        Retorno r = sistema.cerrarVuelo("VU001");
        assertEquals(Retorno.Resultado.OK, r.resultado);
    }

    @Test
    public void testCerrarVuelo_OK_EstadoCambiaCerrado() {
        sistema.cerrarVuelo("VU001");
        Retorno r = sistema.obtenerInformacionDeVuelo("VU001");
        assertTrue(r.valorString.contains("CERRADO"));
    }

    @Test
    public void testCerrarVuelo_OK_SinReservas_StringVacio() {
        Retorno r = sistema.cerrarVuelo("VU001");
        assertEquals(Retorno.Resultado.OK, r.resultado);
        assertEquals("", r.valorString);
        assertEquals(0, r.valorEntero);
    }

    @Test
    public void testCerrarVuelo_OK_EncolaEnAeropuertoOrigen() {
        sistema.cerrarVuelo("VU001");
        // La cola del aeropuerto MVD debe tener 1 vuelo
        Retorno r = sistema.obtenerAeropuerto("MVD");
        assertEquals(1, r.valorEntero);
    }

    @Test
    public void testCerrarVuelo_Error1_CodigoNull() {
        Retorno r = sistema.cerrarVuelo(null);
        assertEquals(Retorno.Resultado.ERROR_1, r.resultado);
    }

    @Test
    public void testCerrarVuelo_Error1_CodigoVacio() {
        Retorno r = sistema.cerrarVuelo("  ");
        assertEquals(Retorno.Resultado.ERROR_1, r.resultado);
    }

    @Test
    public void testCerrarVuelo_Error2_VueloNoExiste() {
        Retorno r = sistema.cerrarVuelo("VU999");
        assertEquals(Retorno.Resultado.ERROR_2, r.resultado);
    }

    @Test
    public void testCerrarVuelo_Error3_VueloEstaProgramado() {
        // VU002 nunca fue abierto, está PROGRAMADO
        sistema.registrarVuelo("MVD", "EZE", "VU002", 50, 200);
        Retorno r = sistema.cerrarVuelo("VU002");
        assertEquals(Retorno.Resultado.ERROR_3, r.resultado);
    }

    @Test
    public void testCerrarVuelo_Error3_VueloYaCerrado() {
        sistema.cerrarVuelo("VU001");
        Retorno r = sistema.cerrarVuelo("VU001");
        assertEquals(Retorno.Resultado.ERROR_3, r.resultado);
    }
}
