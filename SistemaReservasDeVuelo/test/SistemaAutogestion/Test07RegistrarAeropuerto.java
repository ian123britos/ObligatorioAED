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
public class Test07RegistrarAeropuerto {
    
    private ISistema sistema;

    @Before
    public void setUp() {
        sistema = new ImplementacionSistema();
        sistema.inicializarSistema();
    }

    @Test
    public void testRegistrarAeropuerto_OK() {
        Retorno r = sistema.registrarAeropuerto("MVD", "Carrasco");
        assertEquals(Retorno.Resultado.OK, r.resultado);
    }

    @Test
    public void testRegistrarAeropuerto_OK_Varios() {
        sistema.registrarAeropuerto("MVD", "Carrasco");
        Retorno r = sistema.registrarAeropuerto("EZE", "Ezeiza");
        assertEquals(Retorno.Resultado.OK, r.resultado);
    }

    @Test
    public void testRegistrarAeropuerto_Error1_CodigoNull() {
        Retorno r = sistema.registrarAeropuerto(null, "Carrasco");
        assertEquals(Retorno.Resultado.ERROR_1, r.resultado);
    }

    @Test
    public void testRegistrarAeropuerto_Error1_NombreNull() {
        Retorno r = sistema.registrarAeropuerto("MVD", null);
        assertEquals(Retorno.Resultado.ERROR_1, r.resultado);
    }

    @Test
    public void testRegistrarAeropuerto_Error1_CodigoVacio() {
        Retorno r = sistema.registrarAeropuerto("", "Carrasco");
        assertEquals(Retorno.Resultado.ERROR_1, r.resultado);
    }

    @Test
    public void testRegistrarAeropuerto_Error1_NombreVacio() {
        Retorno r = sistema.registrarAeropuerto("MVD", "");
        assertEquals(Retorno.Resultado.ERROR_1, r.resultado);
    }

    @Test
    public void testRegistrarAeropuerto_Error2_CodigoDuplicado() {
        sistema.registrarAeropuerto("MVD", "Carrasco");
        Retorno r = sistema.registrarAeropuerto("MVD", "Otro Nombre");
        // OJO: tu compañero retorna ERROR_1 en vez de ERROR_2, este test va a fallar
        // hasta que lo corrija en la implementación
        assertEquals(Retorno.Resultado.ERROR_2, r.resultado);
    }

    @Test
    public void testRegistrarAeropuerto_Error2_CodigoDuplicadoCaseInsensitive() {
        sistema.registrarAeropuerto("MVD", "Carrasco");
        Retorno r = sistema.registrarAeropuerto("mvd", "Otro");
        assertEquals(Retorno.Resultado.ERROR_2, r.resultado);
    }
}
