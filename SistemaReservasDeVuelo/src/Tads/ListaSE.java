package Tads;

/* Clase ListaSE: Implementacion del TDA Lista usando
 * nodos simplemente enlazados, con apuntador al primer nodo 
 */
public class ListaSE<T extends Comparable<T>> implements ILista<T> {

    protected NodoSE<T> cabeza;
    protected int longitud;

    public ListaSE() {
        cabeza = null;
        longitud = 0;
    }

    @Override
    public void Adicionar(T x) {
        NodoSE<T> nodo_nuevo = new NodoSE<T>(x);
        if (cabeza == null) {
            cabeza = nodo_nuevo;
        } else {
            NodoSE<T> actual = cabeza;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nodo_nuevo);
        }
        longitud++;

    }

    @Override
    public void Insertar(T x, int pos) {
        if (pos < 0) {
            return; // no hace nada
        }
        if (pos == 0) {
            cabeza = new NodoSE<>(x, cabeza);
        } else {
            NodoSE<T> actual = cabeza;
            for (int i = 0; i < pos - 1 && actual != null; i++) {
                actual = actual.getSiguiente();
            }
            if (actual != null) {
                NodoSE<T> nuevo = new NodoSE<>(x, actual.getSiguiente());
                actual.setSiguiente(nuevo);
            }
        }
        longitud++;
    }

    @Override
    public T Obtener(int pos) {
        if (pos < 0 || pos >= longitud || Vacia()) {
            return null; // en lugar de lanzar ListaVaciaException o PosFueraDeRangoException
        }
        NodoSE<T> actual = cabeza;
        for (int i = 0; i < pos; i++) {
            actual = actual.getSiguiente();
        }
        return actual.getDato();
    }

    @Override
    public void Eliminar(int pos) {
        if (pos < 0 || pos >= longitud || Vacia()) {
            return; // no hace nada
        }
        if (pos == 0) {
            cabeza = cabeza.getSiguiente();
        } else {
            NodoSE<T> actual = cabeza;
            for (int i = 0; i < pos - 1; i++) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(actual.getSiguiente().getSiguiente());
        }
        longitud--;
    }

    @Override
    public int Longitud() {
        return longitud;
    }

    @Override
    public boolean Vacia() {
        return (longitud == 0);

    }

    @Override
    public void adicionarOrdenado(T elem) {
        NodoSE<T> nuevoNodo = new NodoSE<T>(elem, null);

        if (cabeza == null || cabeza.getDato().compareTo(elem) > 0) {
            nuevoNodo.setSiguiente(cabeza);
            cabeza = nuevoNodo;
        } else {
            NodoSE<T> nodoActual = cabeza;
            while (nodoActual.getSiguiente() != null && nodoActual.getSiguiente().getDato().compareTo(elem) < 0) {

                nodoActual = nodoActual.getSiguiente();
            }
            nuevoNodo.setSiguiente(nodoActual.getSiguiente());
            nodoActual.setSiguiente(nuevoNodo);
        }

        longitud++;
    }

    @Override
    public boolean existeElemento(T elem) {
        if (Vacia()) {
            return false;
        }
        NodoSE<T> aux = cabeza;
        while (aux != null) {
            if (aux.getDato().equals(elem)) {
                return true;
            }
            aux = aux.getSiguiente();
        }
        return false;
    }

}
