/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tads;

public interface ILista<T> {

    void Adicionar(T x);

    void Insertar(T x, int pos);   

    T Obtener(int pos);            

    void Eliminar(int pos);        

    int Longitud();

    boolean Vacia();
    
    boolean existeElemento(T x);
    
    void adicionarOrdenado(T x);
}

