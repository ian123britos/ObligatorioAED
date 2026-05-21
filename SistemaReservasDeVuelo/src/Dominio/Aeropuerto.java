/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dominio;

/**
 *
 * @author hlbc7
 */
public class Aeropuerto {
    
    //verificar si agregamos dos string para identificar el aeropuerto de origen y el aeropuerto de destino 
    //o hacer una clase para aeropuertoDestino y otra para aeropuertoOrigen
    public int Codigo;
    public String Nombre;
    
    public Aeropuerto(int codigo, String nombre)
    {
        this.Codigo = codigo;
        this.Nombre = nombre;
    }
}

