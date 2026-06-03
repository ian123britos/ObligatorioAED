package SistemaAutogestion;

import Dominio.Categoria;

public interface ISistema {

    Retorno inicializarSistema();
    Retorno registrarPasajero(String cedula, String nombre, int edad,Categoria categoria);
    Retorno buscarPasajero(String cedula);
    Retorno listarPasajerosAscendente();
    Retorno listarPasajerosDescendente();

}