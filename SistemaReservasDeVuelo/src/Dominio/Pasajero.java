/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dominio;

/**
 *
 * @author hlbc7
 */
public class Pasajero {
       public String Nombre;
       public String Cedula;
       public int Edad;
       public Categoria categoria;

    public Pasajero(String nombre, String cedula,int edad,Categoria categoria) {
        this.Nombre = nombre;
        this.Cedula = cedula;
        this.Edad =edad;
        this.categoria = categoria;
        Validar();
    }
    
    public void Validar()
    {
        //ValidarNombre();
        //ValidarCedula();
        //ValidarEdad();
    }

    @Override
    public boolean equals(Object obj) {
        //pregunto si el objeto ingresado no es un Pasajero, si no lo es return --> false
        //sino hace la comparacion de las cedulas
       if (!(obj instanceof Pasajero)) return false;
       Pasajero p = (Pasajero) obj;
        return p.Cedula.equals(this.Cedula);
// comparo los dos objetos si son iguales         
    }
}
