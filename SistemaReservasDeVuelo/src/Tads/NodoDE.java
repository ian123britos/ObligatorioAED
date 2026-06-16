/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tads;

/**
 *
 * @author hlbc7
 */
public class NodoDE<T> {
 
       protected T dato;
    protected NodoDE<T> siguiente;
    protected NodoDE<T> anterior;

    public NodoDE(T dato) {
        this.dato = dato;
        this.siguiente = null;
        this.anterior = null;
    }

    public NodoDE(T dato, NodoDE<T> siguiente, NodoDE<T> anterior) {
        this.dato = dato;
        this.siguiente = siguiente;
        this.anterior = anterior;
    }

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public NodoDE<T> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoDE<T> siguiente) {
        this.siguiente = siguiente;
    }

    public NodoDE<T> getAnterior() {
        return anterior;
    }

    public void setAnterior(NodoDE<T> anterior) {
        this.anterior = anterior;
    }
}
