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
public class Test02RegistrarPasajero {
    
    private ISistema sistema;

    @Before
    public void setUp() {
        sistema = new ImplementacionSistema();
        sistema.inicializarSistema();
    }

    @Test
    public void testRegistrarPasajero_OK_FormatoLargo() {
        Retorno r = sistema.registrarPasajero("1.345.345-4", "Ana", 52, Categoria.Platino);
        assertEquals(Retorno.Resultado.OK, r.resultado);
    }

    @Test
    public void testRegistrarPasajero_OK_FormatoCorto() { //NO PASO
        Retorno r = sistema.registrarPasajero("845.345-4", "Juan", 25, Categoria.Esporádico);
        assertEquals(Retorno.Resultado.OK, r.resultado);
    }

    @Test
    public void testRegistrarPasajero_OK_VariosRegistros() {
        sistema.registrarPasajero("1.345.345-4", "Ana", 52, Categoria.Platino);
        Retorno r = sistema.registrarPasajero("2.000.000-1", "Carlos", 30, Categoria.Frecuente);
        assertEquals(Retorno.Resultado.OK, r.resultado);
    }

    @Test
    public void testRegistrarPasajero_Error1_NombreNull() {
        Retorno r = sistema.registrarPasajero("1.345.345-4", null, 52, Categoria.Platino);
        assertEquals(Retorno.Resultado.ERROR_1, r.resultado);
    }

    @Test
    public void testRegistrarPasajero_Error1_CedulaNull() {
        Retorno r = sistema.registrarPasajero(null, "Ana", 52, Categoria.Platino);
        assertEquals(Retorno.Resultado.ERROR_1, r.resultado);
    }

    @Test
    public void testRegistrarPasajero_Error1_NombreVacio() {
        Retorno r = sistema.registrarPasajero("1.345.345-4", "   ", 52, Categoria.Platino);
        assertEquals(Retorno.Resultado.ERROR_1, r.resultado);
    }

    @Test
    public void testRegistrarPasajero_Error1_CedulaVacia() {
        Retorno r = sistema.registrarPasajero("   ", "Ana", 52, Categoria.Platino);
        assertEquals(Retorno.Resultado.ERROR_1, r.resultado);
    }

    @Test
    public void testRegistrarPasajero_Error2_FormatoInvalido_PuntosExtra() {
        Retorno r = sistema.registrarPasajero("1.3.45.345-4", "Guille", 53, Categoria.Platino);
        assertEquals(Retorno.Resultado.ERROR_2, r.resultado);
    }

    @Test
    public void testRegistrarPasajero_Error2_SinGuion() {
        Retorno r = sistema.registrarPasajero("1.345.3454", "Ana", 30, Categoria.Frecuente);
        assertEquals(Retorno.Resultado.ERROR_2, r.resultado);
    }

    @Test
    public void testRegistrarPasajero_Error2_SinPuntos() {
        Retorno r = sistema.registrarPasajero("13453454", "Ana", 30, Categoria.Frecuente);
        assertEquals(Retorno.Resultado.ERROR_2, r.resultado);
    }

    @Test
    public void testRegistrarPasajero_Error3_EdadNegativa() {
        Retorno r = sistema.registrarPasajero("1.345.345-4", "Ana", -1, Categoria.Platino);
        assertEquals(Retorno.Resultado.ERROR_3, r.resultado);
    }

    @Test
    public void testRegistrarPasajero_Error4_CedulaDuplicada() {
        sistema.registrarPasajero("1.345.345-4", "Ana", 52, Categoria.Platino);
        Retorno r = sistema.registrarPasajero("1.345.345-4", "Pedro", 30, Categoria.Frecuente);
        assertEquals(Retorno.Resultado.ERROR_4, r.resultado);
    }

}
