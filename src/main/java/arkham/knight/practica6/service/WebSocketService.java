package arkham.knight.practica6.service;

import arkham.knight.practica6.encapsulacion.Chat;
import com.google.gson.Gson;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.javatuples.Pair;

import java.util.HashMap;
import java.util.Map;

public class WebSocketService {
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
        Chat mc = gson.fromJson(message, Chat.class);

        if (mc.isInicio()) {
            usuariosConectados.put(mc.getOrigen(), new Pair<>(usuario, mc.getDestino()));
        }

        Session session = usuariosConectados.get(mc.getDestino()).getValue0();

        try {
            if (session != null) {
                session.getRemote().sendString(gson.toJson(mc));
            } else {
                mc = new Chat("servidor", "", "Fin de la transmisión...", false, true);
                usuario.getRemote().sendString(gson.toJson(mc));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

