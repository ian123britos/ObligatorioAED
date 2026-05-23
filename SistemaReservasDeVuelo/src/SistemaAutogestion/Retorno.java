package SistemaAutogestion;

public class Retorno {

    public enum Resultado {

        OK,
        ERROR_1,
        ERROR_2,
        ERROR_3,
        ERROR_4,
        ERROR_5,
        ERROR_6,
        ERROR_7,
        ERROR_8,
        NO_IMPLEMENTADA
    }

    public int valorEntero;

    public String valorString;

    public Resultado resultado;

    // Constructor vacío
    public Retorno() {
    }

    // Constructor solo resultado
    public Retorno(Resultado resultado) {
        this.resultado = resultado;
    }

    // Constructor completo
    public Retorno(Resultado resultado, int valorEntero, String valorString) {

        this.resultado = resultado;
        this.valorEntero = valorEntero;
        this.valorString = valorString;
    }
}