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
public class Test11AbrirVuelo {
    
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
    public void testAbrirVuelo_OK() {
        Retorno r = sistema.abrirVuelo("VU001");
        assertEquals(Retorno.Resultado.OK, r.resultado);
    }

    @Test
    public void testAbrirVuelo_OK_EstadoCambiaAbierto() {
        sistema.abrirVuelo("VU001");
        Retorno r = sistema.obtenerInformacionDeVuelo("VU001");
        assertTrue(r.valorString.contains("ABIERTO"));
    }

    @Test
    public void testAbrirVuelo_Error1_CodigoNull() {
        Retorno r = sistema.abrirVuelo(null);
        assertEquals(Retorno.Resultado.ERROR_1, r.resultado);
    }

    @Test
    public void testAbrirVuelo_Error1_CodigoVacio() {
        Retorno r = sistema.abrirVuelo("  ");
        assertEquals(Retorno.Resultado.ERROR_1, r.resultado);
    }

    @Test
    public void testAbrirVuelo_Error2_VueloNoExiste() {
        Retorno r = sistema.abrirVuelo("VU999");
        assertEquals(Retorno.Resultado.ERROR_2, r.resultado);
    }

    @Test
    public void testAbrirVuelo_Error3_YaEstaAbierto() {
        sistema.abrirVuelo("VU001");
        Retorno r = sistema.abrirVuelo("VU001");
        assertEquals(Retorno.Resultado.ERROR_3, r.resultado);
    }

    @Test
    public void testAbrirVuelo_Error3_NoEstaEnProgramado() {
        // Abrimos y luego volvemos a intentar abrir
        sistema.abrirVuelo("VU001");
        Retorno r = sistema.abrirVuelo("VU001");
        assertEquals(Retorno.Resultado.ERROR_3, r.resultado);
    }
}
