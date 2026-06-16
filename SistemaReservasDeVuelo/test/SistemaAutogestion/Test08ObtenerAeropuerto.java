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
public class Test08ObtenerAeropuerto {
    private ISistema sistema;

    @Before
    public void setUp() {
        sistema = new ImplementacionSistema();
        sistema.inicializarSistema();
    }

    @Test
    public void testObtenerAeropuerto_OK() {
        sistema.registrarAeropuerto("MVD", "Carrasco");
        Retorno r = sistema.obtenerAeropuerto("MVD");
        assertEquals(Retorno.Resultado.OK, r.resultado);
        assertEquals("MVD;Carrasco", r.valorString);
    }

    @Test
    public void testObtenerAeropuerto_OK_ValorEnteroEsCero_SinVuelosEnCola() {
        sistema.registrarAeropuerto("MVD", "Carrasco");
        Retorno r = sistema.obtenerAeropuerto("MVD");
        assertEquals(Retorno.Resultado.OK, r.resultado);
        assertEquals(0, r.valorEntero);
    }

    @Test
    public void testObtenerAeropuerto_OK_CaseInsensitive() {
        sistema.registrarAeropuerto("MVD", "Carrasco");
        Retorno r = sistema.obtenerAeropuerto("mvd");
        assertEquals(Retorno.Resultado.OK, r.resultado);
        assertEquals("MVD;Carrasco", r.valorString);
    }

    @Test
    public void testObtenerAeropuerto_Error1_CodigoNull() {
        Retorno r = sistema.obtenerAeropuerto(null);
        assertEquals(Retorno.Resultado.ERROR_1, r.resultado);
    }

    @Test
    public void testObtenerAeropuerto_Error1_CodigoVacio() {
        Retorno r = sistema.obtenerAeropuerto("   ");
        assertEquals(Retorno.Resultado.ERROR_1, r.resultado);
    }

    @Test
    public void testObtenerAeropuerto_Error2_NoExiste() {
        Retorno r = sistema.obtenerAeropuerto("XXX");
        assertEquals(Retorno.Resultado.ERROR_2, r.resultado);
    }

    @Test
    public void testObtenerAeropuerto_Error2_ListaConOtros() {
        sistema.registrarAeropuerto("EZE", "Ezeiza");
        Retorno r = sistema.obtenerAeropuerto("MVD");
        assertEquals(Retorno.Resultado.ERROR_2, r.resultado);
    }
}
