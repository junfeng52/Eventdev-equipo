package com.azahartech.eventdev.servicio;

import com.azahartech.eventdev.datos.RepositorioGenerico;
import com.azahartech.eventdev.modelo.Usuario;
import com.azahartech.eventdev.util.GestorPersistencia;

import java.util.List;
import java.util.Map;

public class ServicioUsuario {
    private static final String FICHERO_DATOS = "datos/usuarios.dat";
    private RepositorioGenerico<Usuario> repositorio = new RepositorioGenerico<>();
    private Map<String, Usuario> mapa;
    private GestorPersistencia gestorPersistencia = new GestorPersistencia();

    public ServicioUsuario() {
        this.repositorio.cargarDatos(gestorPersistencia.cargarDatos(FICHERO_DATOS));
    }

    /**
     * Función para guardar el usuario dentro de el repositorio.
     * @param nuevoUsuario usuario nuevo para guardar en el RepositorioGenerico.
     */
    public void registrarUsuario(Usuario nuevoUsuario) {
        repositorio.guardar(nuevoUsuario);
    }

    /**
     * Funcion que devuelve el usuario que se desea buscar por el parametro nombre.
     * @param nombre el nombre del usuario a buscar.
     * @return El usuario que coincide con el nombre del usuario a buscar.
     */
    public Usuario buscarPorNombre (String nombre) {
        return repositorio.listar().stream()
                .filter(usuario -> usuario.getNombre().equals(nombre))
                .findFirst().orElse(null);
    }

    /**
     * Funcion que devuelve el usuario que se desea buscar por el parametro email.
     * @param email el email del usuario a buscar.
     * @return EL usuario que coincide con el email del usuario a buscar.
     */
    public Usuario buscarPorEmail(String email) {
        return  repositorio.listar().stream()
                .filter(usuario -> usuario.getEmail().equals(email))
                .findFirst().orElse(null);
    }

    /**
     * Función que devuelve la lista de usuarios guardados
     * @return lista de usuarios guardados.
     */
    public List<Usuario> listarTodosLosUsuario() {
        return repositorio.listar();
    }

    public void guardar() {
        gestorPersistencia.guardarDatos(repositorio.listar(), FICHERO_DATOS);
    }
}
