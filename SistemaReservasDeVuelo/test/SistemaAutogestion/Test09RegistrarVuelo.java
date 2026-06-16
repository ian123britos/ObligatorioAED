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
public class Test09RegistrarVuelo {
    
     private ISistema sistema;

    @Before
    public void setUp() {
        sistema = new ImplementacionSistema();
        sistema.inicializarSistema();
        sistema.registrarAeropuerto("MVD", "Carrasco");
        sistema.registrarAeropuerto("EZE", "Ezeiza");
    }

    @Test
    public void testRegistrarVuelo_OK() {
        Retorno r = sistema.registrarVuelo("MVD", "EZE", "VU001", 100, 500);
        assertEquals(Retorno.Resultado.OK, r.resultado);
    }

    @Test
    public void testRegistrarVuelo_OK_Varios() {
        sistema.registrarVuelo("MVD", "EZE", "VU001", 100, 500);
        Retorno r = sistema.registrarVuelo("EZE", "MVD", "VU002", 200, 300);
        assertEquals(Retorno.Resultado.OK, r.resultado);
    }

    @Test
    public void testRegistrarVuelo_Error1_CapacidadCero() {
        Retorno r = sistema.registrarVuelo("MVD", "EZE", "VU001", 0, 500);
        assertEquals(Retorno.Resultado.ERROR_1, r.resultado);
    }

    @Test
    public void testRegistrarVuelo_Error1_CapacidadNegativa() {
        Retorno r = sistema.registrarVuelo("MVD", "EZE", "VU001", -10, 500);
        assertEquals(Retorno.Resultado.ERROR_1, r.resultado);
    }

    @Test
    public void testRegistrarVuelo_Error1_CostoCero() {
        Retorno r = sistema.registrarVuelo("MVD", "EZE", "VU001", 100, 0);
        assertEquals(Retorno.Resultado.ERROR_1, r.resultado);
    }

    @Test
    public void testRegistrarVuelo_Error1_CostoNegativo() {
        Retorno r = sistema.registrarVuelo("MVD", "EZE", "VU001", 100, -50);
        assertEquals(Retorno.Resultado.ERROR_1, r.resultado);
    }

    @Test
    public void testRegistrarVuelo_Error2_OrigenNull() {
        Retorno r = sistema.registrarVuelo(null, "EZE", "VU001", 100, 500);
        assertEquals(Retorno.Resultado.ERROR_2, r.resultado);
    }

    @Test
    public void testRegistrarVuelo_Error2_DestinoNull() {
        Retorno r = sistema.registrarVuelo("MVD", null, "VU001", 100, 500);
        assertEquals(Retorno.Resultado.ERROR_2, r.resultado);
    }

    @Test
    public void testRegistrarVuelo_Error2_CodigoVueloNull() {
        Retorno r = sistema.registrarVuelo("MVD", "EZE", null, 100, 500);
        assertEquals(Retorno.Resultado.ERROR_2, r.resultado);
    }

    @Test
    public void testRegistrarVuelo_Error2_OrigenVacio() {
        Retorno r = sistema.registrarVuelo("  ", "EZE", "VU001", 100, 500);
        assertEquals(Retorno.Resultado.ERROR_2, r.resultado);
    }

    @Test
    public void testRegistrarVuelo_Error2_CodigoVueloVacio() {
        Retorno r = sistema.registrarVuelo("MVD", "EZE", "  ", 100, 500);
        assertEquals(Retorno.Resultado.ERROR_2, r.resultado);
    }

    @Test
    public void testRegistrarVuelo_Error3_OrigenNoExiste() {
        Retorno r = sistema.registrarVuelo("XXX", "EZE", "VU001", 100, 500);
        assertEquals(Retorno.Resultado.ERROR_3, r.resultado);
    }

    @Test
    public void testRegistrarVuelo_Error4_DestinoNoExiste() {
        Retorno r = sistema.registrarVuelo("MVD", "XXX", "VU001", 100, 500);
        assertEquals(Retorno.Resultado.ERROR_4, r.resultado);
    }

    @Test
    public void testRegistrarVuelo_Error5_CodigoDuplicado() {
        sistema.registrarVuelo("MVD", "EZE", "VU001", 100, 500);
        Retorno r = sistema.registrarVuelo("MVD", "EZE", "VU001", 200, 300);
        assertEquals(Retorno.Resultado.ERROR_5, r.resultado);
    }

    @Test
    public void testRegistrarVuelo_Error5_CodigoDuplicadoCaseInsensitive() {
        sistema.registrarVuelo("MVD", "EZE", "VU001", 100, 500);
        Retorno r = sistema.registrarVuelo("MVD", "EZE", "vu001", 200, 300);
        assertEquals(Retorno.Resultado.ERROR_5, r.resultado);
    }
}
