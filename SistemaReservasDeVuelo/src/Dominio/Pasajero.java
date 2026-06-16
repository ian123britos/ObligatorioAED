package Dominio;

public class Pasajero implements Comparable<Pasajero> {

    private String nombre;
    private String cedula;
    private int edad;
    private Categoria categoria;

    public Pasajero(String nombre, String cedula, int edad, Categoria categoria) {

        this.nombre = nombre;
        this.cedula = cedula;
        this.edad = edad;
        this.categoria = categoria;

        validar();
    }

    public void validar() {

        // ValidarNombre();
        // ValidarCedula();
        // ValidarEdad();

    }

    // GETTERS

    public String getNombre() {
        return nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public int getEdad() {
        return edad;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    // SETTERS

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public boolean equals(Object obj) {

        // si NO es pasajero
        if (!(obj instanceof Pasajero)) {
            return false;
        }

        Pasajero p = (Pasajero) obj;

        return this.cedula.equals(p.cedula);
    }
    
    
    
@Override
public int compareTo(Pasajero p) {
    String cedula1 = this.cedula.replace(".", "").replace("-", "");
    String cedula2 = p.cedula.replace(".", "").replace("-", "");
    long num1 = Long.parseLong(cedula1);
    long num2 = Long.parseLong(cedula2);
    return Long.compare(num1, num2);
}

}