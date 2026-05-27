package Dominio;

public class Pasajero implements Comparable<Pasajero> {

    private String nombre;
    private String cedula;
    private int edad;
    private Categoria categoria;

    public Pasajero(String nombre, String cedula, int edad, Categoria categoria) throws Exception {

        this.nombre = nombre;
        this.cedula = cedula;
        this.edad = edad;
        this.categoria = categoria;

        validar();
    }

    public void validar() throws Exception {

        validarNombre();
        validarCedula();
        validarEdad();

    }

    // VALIDACIONES

    private void validarNombre() throws Exception {

        if (nombre == null || nombre.isEmpty()) {

            throw new Exception("ERROR_1");
        }
    }

    private void validarCedula() throws Exception {

        String regex = "\\d\\.\\d{3}\\.\\d{3}-\\d|\\d{3}\\.\\d{3}-\\d";

        if (cedula == null || !cedula.matches(regex)) {

            throw new Exception("ERROR_2");
        }
    }

    private void validarEdad() throws Exception {

        if (edad < 0) {

            throw new Exception("ERROR_3");
        }
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
    // cambiamos el compareto sobrescribiendolo para que verifique por cedula
    @Override
    public int compareTo(Pasajero p) {

        return this.cedula.compareTo(p.cedula);
    }

}