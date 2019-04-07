package arkham.knight.practica6.Servicios;

import arkham.knight.practica6.Modelos.Mensaje;

import com.google.gson.Gson;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.javatuples.Pair;

import java.util.HashMap;
import java.util.Map;

@WebSocket
public class ServicioWebSocket {
    public static Map<String, Pair<Session, String>> usuariosConectados = new HashMap<>();

    /**
     * Una vez conectado el cliente se activa este metodo.
     *
     * @param usuario
     */
    @OnWebSocketConnect
    public void conectando(Session usuario) {
        System.out.println("Conectando usuario: " + usuario.getLocalAddress().getAddress().toString());
    }

    /**
     * Una vez cerrado la conexión, es ejecutado el metodo anotado
     *
     * @param usuario
     * @param statusCode
     * @param reason
     */
    @OnWebSocketClose
    public void cerrandoConexion(Session usuario, int statusCode, String reason) {
        System.out.println("Desconectando el usuario: " + usuario.getLocalAddress().getAddress().toString());
    }

    /**
     * Una vez recibido un mensaje es llamado el metodo anotado.
     *
     * @param usuario
     * @param message
     */
    @OnWebSocketMessage
    public void recibiendoMensaje(Session usuario, String message) {
        System.out.println("Recibiendo del cliente: " + usuario.getLocalAddress().getAddress().toString() + " - Mensaje: " + message);

        Gson gson = new Gson();
        Mensaje mc = gson.fromJson(message, Mensaje.class);

        if (mc.isInicio()) {
            usuariosConectados.put(mc.getDesde(), new Pair<>(usuario, mc.getHasta()));
        }

        Session session = usuariosConectados.get(mc.getHasta()).getValue0();

        try {
            if (session != null) {
                session.getRemote().sendString(gson.toJson(mc));
            } else {
                mc = new Mensaje("servidor", "", "Fin de la transmisión...", false, true);
                usuario.getRemote().sendString(gson.toJson(mc));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
