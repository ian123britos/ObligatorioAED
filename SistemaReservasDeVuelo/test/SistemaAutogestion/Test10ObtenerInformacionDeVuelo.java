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
public class Test10ObtenerInformacionDeVuelo {
    private ISistema sistema;

    @Before
    public void setUp() {
        sistema = new ImplementacionSistema();
        sistema.inicializarSistema();
        sistema.registrarAeropuerto("MVD", "Carrasco");
        sistema.registrarAeropuerto("EZE", "Ezeiza");
        sistema.registrarVuelo("MVD", "EZE", "VU001", 100, 500);
    }

    @Test
    public void testObtenerInformacion_OK() {
        Retorno r = sistema.obtenerInformacionDeVuelo("VU001");
        assertEquals(Retorno.Resultado.OK, r.resultado);
    }

    @Test
    public void testObtenerInformacion_OK_FormatoString() {
        Retorno r = sistema.obtenerInformacionDeVuelo("VU001");
        // formato: origen:destino;codigo;capacidad;costo;estado
        assertTrue(r.valorString.contains("MVD:EZE"));
        assertTrue(r.valorString.contains("VU001"));
        assertTrue(r.valorString.contains("100"));
        assertTrue(r.valorString.contains("500"));
        assertTrue(r.valorString.contains("PROGRAMADO"));
    }

    @Test
    public void testObtenerInformacion_OK_EstadoProgramadoInicial() {
        Retorno r = sistema.obtenerInformacionDeVuelo("VU001");
        assertTrue(r.valorString.contains("PROGRAMADO"));
    }

    @Test
    public void testObtenerInformacion_OK_DespuesDeAbrir() {
        sistema.abrirVuelo("VU001");
        Retorno r = sistema.obtenerInformacionDeVuelo("VU001");
        assertEquals(Retorno.Resultado.OK, r.resultado);
        assertTrue(r.valorString.contains("ABIERTO"));
    }

    @Test
    public void testObtenerInformacion_Error1_CodigoNull() {
        Retorno r = sistema.obtenerInformacionDeVuelo(null);
        assertEquals(Retorno.Resultado.ERROR_1, r.resultado);
    }

    @Test
    public void testObtenerInformacion_Error1_CodigoVacio() {
        Retorno r = sistema.obtenerInformacionDeVuelo("   ");
        assertEquals(Retorno.Resultado.ERROR_1, r.resultado);
    }

    @Test
    public void testObtenerInformacion_Error2_NoExiste() {
        Retorno r = sistema.obtenerInformacionDeVuelo("VU999");
        assertEquals(Retorno.Resultado.ERROR_2, r.resultado);
    }
}
