package SistemaAutogestion;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class Test01InicializarSistema {

    private ISistema sistema;

   @Before
public void setUp() {
    sistema = new ImplementacionSistema();
}
    @Test
    public void testInicializarSistema_OK() {
        Retorno r = sistema.inicializarSistema();
        assertEquals(Retorno.Resultado.OK, r.resultado);
    }

    @Test
    public void testInicializarSistema_DobleInicializacion_OK() {
        sistema.inicializarSistema();
        Retorno r = sistema.inicializarSistema();
        assertEquals(Retorno.Resultado.OK, r.resultado);
    }
}