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

    
    //Ejercicio 1:
    @Override
    public Retorno inicializarSistema() {

        pasajeros = new ListaSE<>();
        aeropuertos = new ListaSE<>();
        vuelos = new ListaSE<>();
        reservas = new ListaSE<>();

        return new Retorno(Retorno.Resultado.OK);
    }

    //Ejercicio 2:
    @Override
    public Retorno registrarPasajero(String cedula, String nombre, int edad, Categoria categoria) {

        if (cedula == null || nombre == null || cedula.trim().isEmpty() || nombre.trim().isEmpty()) {

            return new Retorno(Retorno.Resultado.ERROR_1);

        }

        if (!cedula.matches("^(\\d{1,2}\\.)?\\d{3}\\.\\d{3}-\\d$")) {
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
    if (cedula == null || !cedula.matches("^(\\d{1,2}\\.)?\\d{3}\\.\\d{3}-\\d$")) {
        return new Retorno(Retorno.Resultado.ERROR_1);
    }
    for (int i = 0; i < pasajeros.Longitud(); i++) {
        Pasajero pasajero = pasajeros.Obtener(i);
        if (pasajero.getCedula().equals(cedula)) {
            Retorno retorno = new Retorno(Retorno.Resultado.OK);
            retorno.valorString = pasajero.getCedula() + ";"
                    + pasajero.getNombre() + ";"
                    + pasajero.getEdad() + ";"
                    + pasajero.getCategoria();
            return retorno;
        }
    }
    return new Retorno(Retorno.Resultado.ERROR_2);
}

    //Ejercicio 4:
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

    //Ejercicio 5:
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

    //Ejercicio 6:
    @Override
    public Retorno listarPasajerosPorCategoría(Categoria unaCategoria) {
        Retorno retorno = new Retorno(Retorno.Resultado.OK);

        String resultado = "";

        for (int i = 0; i < pasajeros.Longitud(); i++) {
            Pasajero pasajeroObtenido = pasajeros.Obtener(i);

            if (pasajeroObtenido.getCategoria() == unaCategoria) {
                if (!resultado.equals("")) {
                    resultado += "|";
                }

                resultado += pasajeroObtenido.getCedula() + ";"
                        + pasajeroObtenido.getNombre() + ";"
                        + pasajeroObtenido.getEdad() + ";"
                        + pasajeroObtenido.getCategoria();

            }

        }
        retorno.valorString = resultado;
        return retorno;

    }
    
    //Ejercicio 7 Falta test:
        @Override
    public Retorno registrarAeropuerto(String codigo, String nombre) {

        if (codigo == null || codigo.isEmpty() || nombre == null || nombre.isEmpty()) {
            return new Retorno(Retorno.Resultado.ERROR_1);
        }

        for (int i = 0; i < aeropuertos.Longitud(); i++) {
            Aeropuerto aeropuertoExiste = aeropuertos.Obtener(i);

            if (aeropuertoExiste.getCodigo().equalsIgnoreCase(codigo)) {
                return new Retorno(Retorno.Resultado.ERROR_2);
            }
        }

        aeropuertos.Adicionar(new Aeropuerto(codigo, nombre));

        return new Retorno(Retorno.Resultado.OK);
    }

    //Ejercicio 8 falta test
    @Override
    public Retorno obtenerAeropuerto(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            return new Retorno(Retorno.Resultado.ERROR_1);
        }

        for (int i = 0; i < aeropuertos.Longitud(); i++) {
            Aeropuerto aeropuerto = aeropuertos.Obtener(i);
            if (aeropuerto.getCodigo().equalsIgnoreCase(codigo)) {
                Retorno retorno = new Retorno(Retorno.Resultado.OK);
                retorno.valorString = aeropuerto.getCodigo() + ";" + aeropuerto.getNombre();
                retorno.valorEntero = aeropuerto.getVuelosEnEspera().cantidad();
                return retorno;
            }
        }

        return new Retorno(Retorno.Resultado.ERROR_2);
    }

    @Override
    public Retorno registrarVuelo(String codigoAeropuertoOrigen, String codigoAeropuertoDestino, String codigoDeVuelo, int capacidad, int costoEnDolares) {

        if (capacidad <= 0 || costoEnDolares <= 0) {
            return new Retorno(Retorno.Resultado.ERROR_1);

        }

        if (codigoAeropuertoOrigen == null || codigoAeropuertoDestino == null || codigoDeVuelo == null
                || codigoAeropuertoOrigen.trim().isEmpty() || codigoAeropuertoDestino.trim().isEmpty()
                || codigoDeVuelo.trim().isEmpty()) {
            return new Retorno(Retorno.Resultado.ERROR_2);
        }

        Aeropuerto origen = null;
        Aeropuerto destino = null;
        for (int i = 0; i < aeropuertos.Longitud(); i++) {
            Aeropuerto AerObtenido = aeropuertos.Obtener(i);

            if (AerObtenido.getCodigo().equalsIgnoreCase(codigoAeropuertoOrigen)) {
                origen = AerObtenido;
            }
            if (AerObtenido.getCodigo().equalsIgnoreCase(codigoAeropuertoDestino)) {
                destino = AerObtenido;

            }

        }
        //entonces si origen o destino salen como null de ese for aplico los errores
        if (origen == null) {
            return new Retorno(Retorno.Resultado.ERROR_3);
        }
        if (destino == null) {
            return new Retorno(Retorno.Resultado.ERROR_4);

        }

        for (int i = 0; i < vuelos.Longitud(); i++) {
            Vuelo vueloObtenido = vuelos.Obtener(i);

            if (vueloObtenido.getCodigoVuelo().equalsIgnoreCase(codigoDeVuelo)) {
                return new Retorno(Retorno.Resultado.ERROR_5);
            }

        }

        Vuelo vuelo = new Vuelo(codigoDeVuelo, origen, destino, capacidad, costoEnDolares);
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

//Ejercicio 12
@Override
public Retorno cerrarVuelo(String codigoDeVuelo) {

    if (codigoDeVuelo == null || codigoDeVuelo.trim().isEmpty()) {
        return new Retorno(Retorno.Resultado.ERROR_1);
    }

    Vuelo vueloEncontrado = null;
    for (int i = 0; i < vuelos.Longitud(); i++) {
        Vuelo v = vuelos.Obtener(i);
        if (v.getCodigoVuelo().equalsIgnoreCase(codigoDeVuelo)) {
            vueloEncontrado = v;
            break;
        }
    }

    if (vueloEncontrado == null) {
        return new Retorno(Retorno.Resultado.ERROR_2);
    }

    if (vueloEncontrado.getEstado() != EstadoVuelo.ABIERTO) {
        return new Retorno(Retorno.Resultado.ERROR_3);
    }

    // Cambiar estado
    vueloEncontrado.setEstado(EstadoVuelo.CERRADO);

    // Encolar en el aeropuerto de origen
    vueloEncontrado.getAeropuertoOrigen().getVuelosEnEspera().encolar(vueloEncontrado);

    // Recorrer reservas del vuelo
    ILista<Pasajero> confirmados = new ListaSE<>();
    int sinCheckIn = 0;

    for (int i = 0; i < reservas.Longitud(); i++) {
        Reserva reserva = reservas.Obtener(i);
        if (reserva.getVuelo().equals(vueloEncontrado)) {
            if (reserva.isCheckIn()) {
                confirmados.adicionarOrdenado(reserva.getPasajero());
            } else {
                sinCheckIn++;
            }
        }
    }

    // Armar valorString con confirmados ordenados por cédula
    String resultado = "";
    for (int i = 0; i < confirmados.Longitud(); i++) {
        Pasajero p = confirmados.Obtener(i);
        if (i > 0) resultado += "|";
        resultado += p.getCedula() + ";"
                + p.getNombre() + ";"
                + p.getEdad() + ";"
                + p.getCategoria();
    }

    Retorno retorno = new Retorno(Retorno.Resultado.OK);
    retorno.valorString = resultado;
    retorno.valorEntero = sinCheckIn;
    return retorno;
}

//Ejercicio 13
@Override
public Retorno realizarReserva(String codigoDeVuelo, String cedula) {

    if (codigoDeVuelo == null || codigoDeVuelo.trim().isEmpty()
            || cedula == null || cedula.trim().isEmpty()) {
        return new Retorno(Retorno.Resultado.ERROR_1);
    }

    if (!cedula.matches("^(\\d{1,2}\\.)?\\d{3}\\.\\d{3}-\\d$")) {
    return new Retorno(Retorno.Resultado.ERROR_2);
}

    Vuelo vueloEncontrado = null;
    for (int i = 0; i < vuelos.Longitud(); i++) {
        Vuelo v = vuelos.Obtener(i);
        if (v.getCodigoVuelo().equalsIgnoreCase(codigoDeVuelo)) {
            vueloEncontrado = v;
            break;
        }
    }
    if (vueloEncontrado == null) {
        return new Retorno(Retorno.Resultado.ERROR_3);
    }

    Pasajero pasajeroEncontrado = null;
    for (int i = 0; i < pasajeros.Longitud(); i++) {
        Pasajero p = pasajeros.Obtener(i);
        if (p.getCedula().equals(cedula)) {
            pasajeroEncontrado = p;
            break;
        }
    }
    if (pasajeroEncontrado == null) {
        return new Retorno(Retorno.Resultado.ERROR_4);
    }

    if (vueloEncontrado.getEstado() != EstadoVuelo.PROGRAMADO
            && vueloEncontrado.getEstado() != EstadoVuelo.ABIERTO) {
        return new Retorno(Retorno.Resultado.ERROR_5);
    }

    // Contar reservas del vuelo y verificar si el pasajero ya tiene una
    int cantReservas = 0;
    for (int i = 0; i < reservas.Longitud(); i++) {
        Reserva res = reservas.Obtener(i);
        if (res.getVuelo().equals(vueloEncontrado)) {
            cantReservas++;
            if (res.getPasajero().equals(pasajeroEncontrado)) {
                return new Retorno(Retorno.Resultado.ERROR_6);
            }
        }
    }

    // Calcular límite con overbooking: ceil(capacidad * 1.10)
    int limiteOverbooking = (int) Math.ceil(vueloEncontrado.getCapacidad() * 1.10);
    if (cantReservas >= limiteOverbooking) {
        return new Retorno(Retorno.Resultado.ERROR_7);
    }

    reservas.Adicionar(new Reserva(pasajeroEncontrado, vueloEncontrado));
    return new Retorno(Retorno.Resultado.OK);
}

//Ejercicio 14
@Override
public Retorno realizarCheckIn(String codigoDeVuelo, String cedula) {

    if (codigoDeVuelo == null || codigoDeVuelo.trim().isEmpty()
            || cedula == null || cedula.trim().isEmpty()) {
        return new Retorno(Retorno.Resultado.ERROR_1);
    }
    if (!cedula.matches("^(\\d{1,2}\\.)?\\d{3}\\.\\d{3}-\\d$")) {
        return new Retorno(Retorno.Resultado.ERROR_2);
    }

    Vuelo vueloEncontrado = null;
    for (int i = 0; i < vuelos.Longitud(); i++) {
        Vuelo v = vuelos.Obtener(i);
        if (v.getCodigoVuelo().equalsIgnoreCase(codigoDeVuelo)) {
            vueloEncontrado = v;
            break;
        }
    }
    if (vueloEncontrado == null) {
        return new Retorno(Retorno.Resultado.ERROR_3);
    }

    Pasajero pasajeroEncontrado = null;
    for (int i = 0; i < pasajeros.Longitud(); i++) {
        Pasajero p = pasajeros.Obtener(i);
        if (p.getCedula().equals(cedula)) {
            pasajeroEncontrado = p;
            break;
        }
    }
    if (pasajeroEncontrado == null) {
        return new Retorno(Retorno.Resultado.ERROR_4);
    }

    if (vueloEncontrado.getEstado() != EstadoVuelo.ABIERTO) {
        return new Retorno(Retorno.Resultado.ERROR_5);
    }

    // Buscar reserva del pasajero y contar confirmados
    Reserva reservaEncontrada = null;
    int cantConfirmados = 0;
    for (int i = 0; i < reservas.Longitud(); i++) {
        Reserva res = reservas.Obtener(i);
        if (res.getVuelo().equals(vueloEncontrado)) {
            if (res.getPasajero().equals(pasajeroEncontrado)) {
                reservaEncontrada = res;
            }
            if (res.isCheckIn()) {
                cantConfirmados++;
            }
        }
    }

    if (reservaEncontrada == null) {
        return new Retorno(Retorno.Resultado.ERROR_6);
    }

    if (reservaEncontrada.isCheckIn()) {
        return new Retorno(Retorno.Resultado.ERROR_7);
    }

    if (cantConfirmados >= vueloEncontrado.getCapacidad()) {
        return new Retorno(Retorno.Resultado.ERROR_8);
    }

    reservaEncontrada.setCheckIn(true);
    return new Retorno(Retorno.Resultado.OK);
}
}
