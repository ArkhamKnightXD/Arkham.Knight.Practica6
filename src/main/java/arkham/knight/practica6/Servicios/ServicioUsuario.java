
package arkham.knight.practica6.Servicios;

import arkham.knight.practica6.Modelos.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class ServicioUsuario extends ServicioBaseDatos<Usuario> {
    private static ServicioUsuario instancia;

    private ServicioUsuario() {
        super(Usuario.class);
    }

    public static ServicioUsuario getInstancia() {
        if (instancia == null) {
            instancia = new ServicioUsuario();
        }
        return instancia;
    }

    public Object encontrarUsuarioSesion(String sesion) {
        EntityManager em = getEntityManager();

        try {
            Query query = em.createQuery("from Usuario user where user.sesion = :user_sesion");
            query.setParameter("user_sesion", sesion);
            return query.getSingleResult();
        } catch (Exception ex) {
            return null;
        } finally {
            em.close();
        }
    }

    public Object encontrarUsuario(String username, String password) {
        EntityManager em = getEntityManager();

        try {
            Query query = em.createQuery("from Usuario user where user.username = :user_username and user.password = :user_password");
            query.setParameter("user_username", username);
            query.setParameter("user_password", password);
            return query.getSingleResult();
        } catch (Exception ex) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<Usuario> encontrarUsuariosPrivilegiados() {
        EntityManager em = getEntityManager();

        try {
            Query query = em.createQuery("from Usuario user where user.administrator = true or user.autor = true");
            return query.getResultList();
        } catch (Exception ex) {
            return null;
        } finally {
            em.close();
        }
    }
}
