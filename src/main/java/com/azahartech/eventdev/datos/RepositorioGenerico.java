package com.azahartech.eventdev.datos;

import java.util.ArrayList;
import java.util.List;

/**
 * La clase RepositorioGenerico sirver para
 *
 * @author Junfeng Wangli
 * @version 1.0
 * @param <T>
 */
public class RepositorioGenerico <T>{
    private List<T> elementos = new ArrayList<>();

    /**
     * Guarda el objeto en el repositorio
     *
     * @param objeto es el objeto a guardar
     */
    public void guardar(T objeto) {
        this.elementos.add(objeto);
    }

    public void guardar(List<T> objetos) {
        this.elementos.addAll(objetos);
    }

    /**
     * Devuelve la lista completa de elementos almacenados.
     *
     * @return una lista con todos los elementos del repositorio
     */
    public List<T> listar() {
        return this.elementos;
    }

    /**
     * Elimina un objeto del repositorio
     *
     * @param objeto el objeto que se desea eleminar
     */
    public void eliminar(T objeto){
        this.elementos.remove(objeto);

    }

    /**
     * Obtiene un elemento del repositorio según su índice.
     *
     * @param indice la posición del elemento en la lista
     * @return el elemento almacenado en el índice indicado
     */
    public T obtener(int indice) {
        T resultado = null;
        if (indice > 0 && indice <= elementos.size()-1){
            resultado = this.elementos.get(indice);
        }
        return resultado;
    }

    public void cargarDatos(List<T> listaACargar) {
        this.elementos = listaACargar;
    }

}
