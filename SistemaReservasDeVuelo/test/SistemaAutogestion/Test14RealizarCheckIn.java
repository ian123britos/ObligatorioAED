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
public class Test14RealizarCheckIn {
    private ISistema sistema;

    @Before
    public void setUp() {
        sistema = new ImplementacionSistema();
        sistema.inicializarSistema();
        sistema.registrarAeropuerto("MVD", "Carrasco");
        sistema.registrarAeropuerto("EZE", "Ezeiza");
        sistema.registrarVuelo("MVD", "EZE", "VU001", 100, 500);
        sistema.registrarPasajero("1.345.345-4", "Ana", 30, Categoria.Platino);
        sistema.registrarPasajero("2.000.000-1", "Carlos", 25, Categoria.Frecuente);
        // Abrir vuelo y reservar para tener escenario base listo
        sistema.abrirVuelo("VU001");
        sistema.realizarReserva("VU001", "1.345.345-4");
        sistema.realizarReserva("VU001", "2.000.000-1");
    }

    @Test
    public void testRealizarCheckIn_OK() {
        Retorno r = sistema.realizarCheckIn("VU001", "1.345.345-4");
        assertEquals(Retorno.Resultado.OK, r.resultado);
    }

    @Test
    public void testRealizarCheckIn_OK_VariosPassajeros() {
        sistema.realizarCheckIn("VU001", "1.345.345-4");
        Retorno r = sistema.realizarCheckIn("VU001", "2.000.000-1");
        assertEquals(Retorno.Resultado.OK, r.resultado);
    }

    @Test
    public void testRealizarCheckIn_Error1_VueloNull() {
        Retorno r = sistema.realizarCheckIn(null, "1.345.345-4");
        assertEquals(Retorno.Resultado.ERROR_1, r.resultado);
    }

    @Test
    public void testRealizarCheckIn_Error1_CedulaNull() {
        Retorno r = sistema.realizarCheckIn("VU001", null);
        assertEquals(Retorno.Resultado.ERROR_1, r.resultado);
    }

    @Test
    public void testRealizarCheckIn_Error1_VueloVacio() {
        Retorno r = sistema.realizarCheckIn("  ", "1.345.345-4");
        assertEquals(Retorno.Resultado.ERROR_1, r.resultado);
    }

    @Test
    public void testRealizarCheckIn_Error1_CedulaVacia() {
        Retorno r = sistema.realizarCheckIn("VU001", "  ");
        assertEquals(Retorno.Resultado.ERROR_1, r.resultado);
    }

    @Test
    public void testRealizarCheckIn_Error2_CedulaFormatoInvalido() {
        Retorno r = sistema.realizarCheckIn("VU001", "12345678");
        assertEquals(Retorno.Resultado.ERROR_2, r.resultado);
    }

    @Test
    public void testRealizarCheckIn_Error3_VueloNoExiste() {
        Retorno r = sistema.realizarCheckIn("VU999", "1.345.345-4");
        assertEquals(Retorno.Resultado.ERROR_3, r.resultado);
    }

    @Test
    public void testRealizarCheckIn_Error4_PasajeroNoExiste() {
        Retorno r = sistema.realizarCheckIn("VU001", "9.999.999-9");
        assertEquals(Retorno.Resultado.ERROR_4, r.resultado);
    }

    @Test
    public void testRealizarCheckIn_Error5_VueloProgramado() {
        sistema.registrarVuelo("MVD", "EZE", "VU002", 100, 300);
        sistema.registrarPasajero("3.000.000-1", "Pedro", 40, Categoria.Estandar);
        // VU002 está PROGRAMADO, no ABIERTO
        Retorno r = sistema.realizarCheckIn("VU002", "3.000.000-1");
        assertEquals(Retorno.Resultado.ERROR_5, r.resultado);
    }

    @Test
    public void testRealizarCheckIn_Error5_VueloCerrado() {
        sistema.cerrarVuelo("VU001");
        Retorno r = sistema.realizarCheckIn("VU001", "1.345.345-4");
        assertEquals(Retorno.Resultado.ERROR_5, r.resultado);
    }

    @Test
    public void testRealizarCheckIn_Error6_SinReserva() {
        sistema.registrarPasajero("3.000.000-1", "Pedro", 40, Categoria.Estandar);
        Retorno r = sistema.realizarCheckIn("VU001", "3.000.000-1");
        assertEquals(Retorno.Resultado.ERROR_6, r.resultado);
    }

    @Test
    public void testRealizarCheckIn_Error7_YaHizoCheckIn() {
        sistema.realizarCheckIn("VU001", "1.345.345-4");
        Retorno r = sistema.realizarCheckIn("VU001", "1.345.345-4");
        assertEquals(Retorno.Resultado.ERROR_7, r.resultado);
    }

    @Test
    public void testRealizarCheckIn_Error8_CapacidadMaxima() {
        // Vuelo con capacidad 1
        sistema.registrarVuelo("MVD", "EZE", "VU002", 1, 300);
        sistema.abrirVuelo("VU002");
        sistema.realizarReserva("VU002", "1.345.345-4");
        sistema.realizarReserva("VU002", "2.000.000-1");

        // Ana ocupa el único lugar
        sistema.realizarCheckIn("VU002", "1.345.345-4");

        // Carlos no puede hacer check-in, capacidad llena
        Retorno r = sistema.realizarCheckIn("VU002", "2.000.000-1");
        assertEquals(Retorno.Resultado.ERROR_8, r.resultado);
    }
}
