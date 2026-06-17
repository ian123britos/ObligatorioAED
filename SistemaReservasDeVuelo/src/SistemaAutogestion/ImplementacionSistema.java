package SistemaAutogestion;

import Dominio.Categoria;
import Dominio.Aeropuerto;
import Dominio.Pasajero;
import Dominio.Reserva;
import Dominio.Vuelo;
import Dominio.EstadoVuelo;
import Dominio.Clase;


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

                retorno.valorString
                        = vuelo.getAeropuertoOrigen().getCodigo() + ":"
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
        // Armar el valorString con los confirmados (formato idem op4)
        String resultado = "";
        for (int i = 0; i < confirmados.Longitud(); i++) {
            Pasajero pasajero = confirmados.Obtener(i);
            resultado += pasajero.getCedula() + ";"
                    + pasajero.getNombre() + ";"
                    + pasajero.getEdad() + ";"
                    + pasajero.getCategoria();
            if (i < confirmados.Longitud() - 1) {
                resultado += "|";
            }
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

    //Ejercicio 15
    @Override
    public Retorno embarqueYDespegueDeVuelo(String codigoAeropuerto) {
        if (codigoAeropuerto == null || codigoAeropuerto.trim().isEmpty()) {
            return new Retorno(Retorno.Resultado.ERROR_1);
        }
        // Buscar el aeropuerto por código
        Aeropuerto aeropuerto = null;
        for (int i = 0; i < aeropuertos.Longitud(); i++) {
            Aeropuerto aerObtenido = aeropuertos.Obtener(i);
            if (aerObtenido.getCodigo().equalsIgnoreCase(codigoAeropuerto)) {
                aeropuerto = aerObtenido;
            }
        }
        // Si no existe el aeropuerto
        if (aeropuerto == null) {
            return new Retorno(Retorno.Resultado.ERROR_2);
        }
        // Si no hay vuelos esperando en la cola
        if (aeropuerto.getVuelosEnEspera().esVacia()) {
            return new Retorno(Retorno.Resultado.ERROR_3);
        }
        // Tomar el vuelo más antiguo y cambiarlo a finalizado
        Vuelo vuelo = aeropuerto.getVuelosEnEspera().desencolar();
        vuelo.setEstado(EstadoVuelo.FINALIZADO);
        Retorno retorno = new Retorno(Retorno.Resultado.OK);
        retorno.valorString = vuelo.getCodigoVuelo();
        retorno.valorEntero = aeropuerto.getVuelosEnEspera().cantidad();
        return retorno;
    }
    
    @Override
    public Retorno consultaDisponibilidad(int[][] matriz, int cantidad, Clase unaClase) {
        if (cantidad <= 0) {
            return new Retorno(Retorno.Resultado.ERROR_1);
        }

        int colInicio, colFin;
        switch (unaClase) {
            case PRIMERA:
                colInicio = 0; colFin = 2; break;
            case EJECUTIVA:
                colInicio = 3; colFin = 9; break;
            case ECONOMICA:
                colInicio = 10; colFin = 25; break;
            default:
                colInicio = 0; colFin = 0; break;
        }

        String resultado = buscarContiguosPorRango(matriz, colInicio, colFin, cantidad, 0, "");

        int opciones = 0;
        if (!resultado.isEmpty()) {
            opciones = resultado.split("\\|").length;
        }

        Retorno retorno = new Retorno(Retorno.Resultado.OK);
        retorno.valorEntero = opciones;
        retorno.valorString = resultado;
        return retorno;
    }

    // Recursivo: recorre columnas del rango
    private String buscarContiguosPorRango(int[][] matriz, int col, int colFin, int cantidad, int fila, String acumulado) {
        // Caso base: terminamos todas las columnas
        if (col > colFin) {
            return acumulado;
        }
        // Buscamos contiguos en esta columna desde fila 0
        String resultadoCol = buscarContiguos(matriz, col, cantidad, 0, acumulado);
        // Avanzamos a la siguiente columna
        return buscarContiguosPorRango(matriz, col + 1, colFin, cantidad, 0, resultadoCol);
    }

    // Recursivo: recorre filas de una columna
    private String buscarContiguos(int[][] matriz, int columna, int cantidad, int fila, String acumulado) {
        if (fila + cantidad > matriz.length) {
            return acumulado;
        }
        if (sonContiguosLibres(matriz, columna, fila, cantidad, 0)) {
            String opcion = armarOpcion(columna, fila, cantidad, 0, "");
            if (!acumulado.isEmpty()) {
                acumulado += "|";
            }
            acumulado += opcion;
        }
        return buscarContiguos(matriz, columna, cantidad, fila + 1, acumulado);
    }

    // Recursivo: verifica si hay 'cantidad' filas libres desde 'fila'
    private boolean sonContiguosLibres(int[][] matriz, int columna, int fila, int cantidad, int contador) {
        if (contador == cantidad) {
            return true;
        }
        if (matriz[fila + contador][columna] != 0) {
            return false;
        }
        return sonContiguosLibres(matriz, columna, fila, cantidad, contador + 1);
    }

    // Recursivo: arma "A4-B4-C4"
    private String armarOpcion(int columna, int fila, int cantidad, int contador, String resultado) {
        if (contador == cantidad) {
            return resultado;
        }
        char letra = (char) ('A' + fila + contador);
        String asiento = String.valueOf(letra) + (columna + 1);
        if (!resultado.isEmpty()) {
            resultado += "-";
        }
        resultado += asiento;
        return armarOpcion(columna, fila, cantidad, contador + 1, resultado);
    }
}
