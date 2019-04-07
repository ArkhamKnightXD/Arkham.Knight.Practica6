package arkham.knight.practica6.Modelos;

public class Mensaje {
    private String desde;
    private String hasta;
    private String mensaje;
    private boolean inicio;
    private boolean fin;

    public Mensaje() {
    }

    public Mensaje(String desde, String hasta, String mensaje, boolean inicio, boolean fin) {
        this.desde = desde;
        this.hasta = hasta;
        this.mensaje = mensaje;
        this.inicio = inicio;
        this.fin = fin;
    }

    public String getDesde() {
        return desde;
    }

    public void setDesde(String desde) {
        this.desde = desde;
    }

    public String getHasta() {
        return hasta;
    }

    public void setHasta(String hasta) {
        this.hasta = hasta;
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
