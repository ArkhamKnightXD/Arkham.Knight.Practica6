package arkham.knight.practica6.encapsulacion;

public class Chat {

    private String origen;
    private String destino;
    private String mensaje;
    private boolean inicio;
    private boolean fin;

    public Chat(String origen, String destino, String mensaje, boolean inicio, boolean fin) {
        this.origen = origen;
        this.destino = destino;
        this.mensaje = mensaje;
        this.inicio = inicio;
        this.fin = fin;
    }

    public Chat() {
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public boolean isInicio() {
        return inicio;
    }

    public void setInicio(boolean inicio) {
        this.inicio = inicio;
    }

    public boolean isFin() {
        return fin;
    }

    public void setFin(boolean fin) {
        this.fin = fin;
    }
}
