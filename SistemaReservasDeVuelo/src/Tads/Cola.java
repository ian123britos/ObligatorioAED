package Tads;

public class Cola<T> {

    private NodoSE<T> frente;
    private NodoSE<T> fin;

    private int cantidad;

    public Cola() {

        frente = null;
        fin = null;

        cantidad = 0;
    }

    // Agrega un elemento al final de la cola
    public void encolar(T dato) {

        NodoSE<T> nuevo = new NodoSE<>(dato);

        // Si la cola está vacía
        if (esVacia()) {

            frente = nuevo;
            fin = nuevo;

        } else {

            fin.setSiguiente(nuevo);
            fin = nuevo;
        }

        cantidad++;
    }

    // Elimina y retorna el primer elemento
    public T desencolar() {

        if (esVacia()) {
            return null;
        }

        T dato = frente.getDato();

        frente = frente.getSiguiente();

        // Si la cola quedó vacía
        if (frente == null) {
            fin = null;
        }

        cantidad--;

        return dato;
    }

    // Retorna el frente sin eliminarlo
    public T obtenerFrente() {

        if (esVacia()) {
            return null;
        }

        return frente.getDato();
    }

    public boolean esVacia() {
        return frente == null;
    }

    public int cantidad() {
        return cantidad;
    }
}