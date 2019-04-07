
package arkham.knight.practica6;

import arkham.knight.practica6.Modelos.Usuario;
import arkham.knight.practica6.Servicios.ServicioBootstrap;
import arkham.knight.practica6.Servicios.ServicioUsuario;

public class Main {
    public static void main(String[] args) {
        // Iniciando el servicio de Base de datos por medio de Hibernate y H2.
        try {
            ServicioBootstrap.iniciarBaseDatos();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insertando el usuario por defecto (administrador).
        if (ServicioUsuario.getInstancia().encontrar(new Long(1)) == null) {
            ServicioUsuario.getInstancia().crear(new Usuario(new Long(1), "Administrador", "admin", "1234", true, true, null));
        }

        // Creando las rutas.
        Enrutamiento.crearRutas();
    }
}
