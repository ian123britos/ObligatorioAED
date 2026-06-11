package Tads;

import Tads.NodoDE;
import Tads.ListaVaciaException;
import Tads.PosFueraDeRangoException;
import Tads.ILista;

/* Clase ListaDE: Implementacion del TDA Lista usando
 * nodos doblemente enlazados, con apuntador al primer nodo 
 */
public class ListaDE<T extends Comparable<T>> implements ILista<T> {

    protected NodoDE<T> cabeza;
    protected int longitud;

    public ListaDE() {
        cabeza = null;
        longitud = 0;
    }

    @Override
    public void Adicionar(T x) {
        NodoDE<T> nodo = new NodoDE<T>(x);
        if (Vacia()) {
            cabeza = nodo;
        } else {
            NodoDE<T> cursor = cabeza;
            while (cursor.getSiguiente() != null) {
                cursor = cursor.getSiguiente();
            }
            nodo.setAnterior(cursor);
            cursor.setSiguiente(nodo);
        }
        longitud++; 
    }

    @Override
    public void Insertar(T x, int pos){
       if ((pos < 0) || (pos >= longitud)) {
            throw new PosFueraDeRangoException();
        }

        NodoDE<T> nodo = new NodoDE<T>(x);
        if (pos == 0) {
            nodo.setSiguiente(cabeza);
            if (cabeza != null) {
                cabeza.setAnterior(nodo);
            }
            cabeza = nodo;
        } else {
            NodoDE<T> cursor = cabeza;
            int i = 0;
            while (i < pos - 1) {
                i++;
                cursor = cursor.getSiguiente();
            }
            nodo.setSiguiente(cursor.getSiguiente());
            nodo.setAnterior(cursor);
            cursor.getSiguiente().setAnterior(nodo);
            cursor.setSiguiente(nodo);

        }
        longitud++;  
    }

    @Override
    public T Obtener(int pos) throws PosFueraDeRangoException {
       if ((pos < 0) || (pos >= longitud)) {
            throw new PosFueraDeRangoException();
        }

        NodoDE<T> cursor = cabeza;
        for (int i = 0; i < pos; i++) {
            cursor = cursor.getSiguiente();
        }
        return cursor.getDato();
    }

    @Override
    public void Eliminar(int pos) throws PosFueraDeRangoException, ListaVaciaException {
        if (Vacia()) {
            throw new ListaVaciaException();
        }
        if ((pos < 0) || (pos >= longitud)) {
            throw new PosFueraDeRangoException();
        }

        NodoDE<T> cursor = cabeza;
        if (pos == 0) {
            if (cabeza.getSiguiente() != null) {
                cabeza.getSiguiente().setAnterior(null);
            }
            cabeza = cursor.getSiguiente();
        } else {
            int i = 0;
            while (i < pos - 1) {
                i++;
                cursor = cursor.getSiguiente();
            }
            cursor.setSiguiente(cursor.getSiguiente().getSiguiente());
            if (cursor.getSiguiente() != null) {
                cursor.getSiguiente().setAnterior(cursor);
            }

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
    public boolean existeElemento(T elem){
        if (Vacia()) {
            throw new ListaVaciaException();
        } else {
           NodoDE<T> aux = cabeza;
           while (aux != null){
            if (aux.getDato().equals(elem)) return true;
            else 
            aux = aux.getSiguiente();
           }
        }
       
        return false;
    }
    
    @Override
    public void adicionarOrdenado(T elem) {
        NodoDE<T> nuevoNodo = new NodoDE<>(elem);
        if (Vacia()) {
            cabeza = nuevoNodo;
            longitud++;
            return;
        }

        NodoDE<T> actual = cabeza;
        NodoDE<T> anterior = null;

        while (actual != null && (actual.getDato().compareTo(nuevoNodo.getDato()) < 0)) {
            anterior = actual;
            actual = actual.getSiguiente();
        }

        if (anterior == null) {
            nuevoNodo.setSiguiente(cabeza);
            cabeza.setAnterior(nuevoNodo);
            cabeza = nuevoNodo;
        } else {
            nuevoNodo.setSiguiente(actual);
            nuevoNodo.setAnterior(anterior);
            anterior.setSiguiente(nuevoNodo);
            if (actual != null) {
                actual.setAnterior(nuevoNodo);
            }
        }

        longitud++;
    
    }

}
