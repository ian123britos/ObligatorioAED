package SistemaAutogestion;

import Dominio.Categoria;
import Dominio.Aeropuerto;
import Dominio.Pasajero;
import Dominio.Reserva;
import Dominio.Vuelo;
import Dominio.EstadoVuelo;

import Tads.ILista;
import Tads.ListaSE;

public class ImplementacionSistema implements ISistema {

    private ILista<Pasajero> pasajeros;

    private ILista<Aeropuerto> aeropuertos;

    private ILista<Vuelo> vuelos;

    private ILista<Reserva> reservas;

    @Override
    public Retorno inicializarSistema() {

        pasajeros = new ListaSE<>();
        aeropuertos = new ListaSE<>();
        vuelos = new ListaSE<>();
        reservas = new ListaSE<>();

        return new Retorno(Retorno.Resultado.OK);
    }

    @Override
    public Retorno registrarPasajero(String cedula, String nombre, int edad, Categoria categoria) {

        if (cedula == null || nombre == null || cedula.trim().isEmpty() || nombre.trim().isEmpty()) {

            return new Retorno(Retorno.Resultado.ERROR_1);

        }

        if (!cedula.matches("^\\d{1,2}\\.\\d{3}\\.\\d{3}-\\d$")) {
            return new Retorno(Retorno.Resultado.ERROR_2);
        }

        if (edad < 0) {
            return new Retorno(Retorno.Resultado.ERROR_3);
        }

        //Si ya existe un pasajero registrado con esa cédula.
        for (int i = 0; i < pasajeros.Longitud(); i++) {
            Pasajero pasajeroExiste = pasajeros.Obtener(i);
            if (pasajeroExiste.getCedula().equalsIgnoreCase(cedula)) {
                return new Retorno(Retorno.Resultado.ERROR_4);
            }
        }

        //si pasa todos los errores entonces creamos el nuevo pasajero con esos datos
        Pasajero pasajero = new Pasajero(nombre, cedula, edad, categoria);

        //agregamos al nuevo pasajero recien creado a la lista de pasajeros
        //Usamos el adicionarOrdenado ya que este ordena automaticamente por el CompareTo de pasajero, osea cada pasajero que se regista
        // queda ordenado por la cedula
        pasajeros.adicionarOrdenado(pasajero);
        return new Retorno(Retorno.Resultado.OK);

    }

    @Override
    public Retorno buscarPasajero(String cedula) {
        if (!cedula.matches("^\\d{1,2}\\.\\d{3}\\.\\d{3}-\\d$")) {
            return new Retorno(Retorno.Resultado.ERROR_1);
        }

        for (int i = 0; i < pasajeros.Longitud(); i++) {
            //verificamos en la lista de pasajeros si hay un pasajero con la misma cedula que la dada por parametro
            Pasajero pasajero = pasajeros.Obtener(i);
            if (pasajero.getCedula().equals(cedula)) {
                //si hay un pasajero con esa cedula en la lista se devuelve en valorString con el formato dado.
                Retorno retorno = new Retorno(Retorno.Resultado.OK);

                retorno.valorString = pasajero.getCedula() + ";"
                        + pasajero.getNombre() + ";"
                        + pasajero.getEdad() + ";"
                        + pasajero.getCategoria();

                return retorno;
            }

        }

        //Si no existe un pasajero registrado con esa cédula.
        return new Retorno(Retorno.Resultado.ERROR_2);
    }

    @Override
    public Retorno listarPasajerosAscendente() {
        Retorno retorno = new Retorno(Retorno.Resultado.OK);

        String resultado = "";

        for (int i = 0; i < pasajeros.Longitud(); i++) {

            Pasajero pasajero = pasajeros.Obtener(i);

            resultado += pasajero.getCedula() + ";"
                    + pasajero.getNombre() + ";"
                    + pasajero.getEdad() + ";"
                    + pasajero.getCategoria();

            if (i < pasajeros.Longitud() - 1) {
                resultado += "|";
            }
        }

        retorno.valorString = resultado;

        return retorno;
    }

    @Override
    public Retorno listarPasajerosDescendente() {
        Retorno retorno = new Retorno(Retorno.Resultado.OK);
        String resultado = "";
        
        // No se si seria solo pasajeros.Longitud() o con el -1 como está
        for (int i = pasajeros.Longitud() - 1; i >= 0; i--) {
            Pasajero pasajero = pasajeros.Obtener(i);

            resultado += pasajero.getCedula() + ";"
                    + pasajero.getNombre() + ";"
                    + pasajero.getEdad() + ";"
                    + pasajero.getCategoria();

            if (i > 0) {
                resultado += "|";
            }
        }

        retorno.valorString = resultado;
        return retorno;
    }
    
    @Override 
    public Retorno listarPasajerosPorCategoría(Categoria unaCategoria)
    {
        Retorno retorno = new Retorno(Retorno.Resultado.OK);
        
        String resultado = "";
        
        for(int  i = 0; i < pasajeros.Longitud();i++ )
        {
            Pasajero pasajeroObtenido = pasajeros.Obtener(i);
            
            if(pasajeroObtenido.getCategoria() == unaCategoria)
            {
                  if (!resultado.equals("")) {
                    resultado += "|";
                  }
                
                
                resultado += pasajeroObtenido.getCedula() + ";" +
                        pasajeroObtenido.getNombre() + ";" +
                        pasajeroObtenido.getEdad() + ";" +
                        pasajeroObtenido.getCategoria();
                        
                        
            }
        
        }
            retorno.valorString = resultado;            
            return retorno;
            
    }
    
    public Retorno registrarVuelo(String codigoAeropuertoOrigen, String
codigoAeropuertoDestino,String codigoDeVuelo, int capacidad, int
costoEnDolares)
    {
        
        if(capacidad <= 0 || costoEnDolares <= 0)
        {
            return new Retorno(Retorno.Resultado.ERROR_1);
            
            
        }
        
        if(codigoAeropuertoOrigen == null || codigoAeropuertoDestino == null || codigoDeVuelo == null
                || codigoAeropuertoOrigen.trim().isEmpty() || codigoAeropuertoDestino.trim().isEmpty() 
                || codigoDeVuelo.trim().isEmpty())
        {
            return new Retorno(Retorno.Resultado.ERROR_2);
        }
        
        Aeropuerto origen = null;
        Aeropuerto destino = null;
        for(int i = 0; i<aeropuertos.Longitud();i++)
        {
            Aeropuerto AerObtenido = aeropuertos.Obtener(i);
            
            if(AerObtenido.getCodigo().equalsIgnoreCase(codigoAeropuertoOrigen))
            {
                origen = AerObtenido;
            }
            if(AerObtenido.getCodigo().equalsIgnoreCase(codigoAeropuertoDestino))
            {
             destino = AerObtenido;   
                
            }

        }
        //entonces si origen o destino salen como null de ese for aplico los errores
        if(origen == null)
        {
            return new Retorno(Retorno.Resultado.ERROR_3);
        }
        if(destino== null)
        {
           return new Retorno(Retorno.Resultado.ERROR_4);

        }
        
        for(int i = 0; i <vuelos.Longitud(); i++)
        {
            Vuelo vueloObtenido =  vuelos.Obtener(i);
            
            if(vueloObtenido.getCodigoVuelo().equalsIgnoreCase(codigoDeVuelo))
            {
                return new Retorno(Retorno.Resultado.ERROR_5);
            }
            
        }
        
       Vuelo vuelo =  new Vuelo(codigoDeVuelo,origen,destino,capacidad,costoEnDolares);
       vuelos.Adicionar(vuelo);
       
       return new Retorno(Retorno.Resultado.OK);
         
    }
    
    
    @Override
public Retorno obtenerInformacionDeVuelo(String codigoDeVuelo) {

    if (codigoDeVuelo == null || codigoDeVuelo.trim().isEmpty()) {
        return new Retorno(Retorno.Resultado.ERROR_1);
    }

    for (int i = 0; i < vuelos.Longitud(); i++) {

        Vuelo vuelo = vuelos.Obtener(i);

        if (vuelo.getCodigoVuelo().equalsIgnoreCase(codigoDeVuelo)) {

            Retorno retorno = new Retorno(Retorno.Resultado.OK);

            retorno.valorString =
                    vuelo.getAeropuertoOrigen().getCodigo() + ":"
                    + vuelo.getAeropuertoDestino().getCodigo() + ";"
                    + vuelo.getCodigoVuelo() + ";"
                    + vuelo.getCapacidad() + ";"
                    + vuelo.getCostoEnDolares() + ";"
                    + vuelo.getEstado();

            return retorno;
        }
    }

    return new Retorno(Retorno.Resultado.ERROR_2);
}

@Override
public Retorno abrirVuelo(String codigoDeVuelo) {

    if (codigoDeVuelo == null || codigoDeVuelo.trim().isEmpty()) {
        return new Retorno(Retorno.Resultado.ERROR_1);
    }

    for (int i = 0; i < vuelos.Longitud(); i++) {

        Vuelo vuelo = vuelos.Obtener(i);

        if (vuelo.getCodigoVuelo().equalsIgnoreCase(codigoDeVuelo)) {

            if (vuelo.getEstado() != EstadoVuelo.PROGRAMADO) {
                return new Retorno(Retorno.Resultado.ERROR_3);
            }

            vuelo.setEstado(EstadoVuelo.ABIERTO);

            return new Retorno(Retorno.Resultado.OK);
        }
    }

    return new Retorno(Retorno.Resultado.ERROR_2);
}

}
