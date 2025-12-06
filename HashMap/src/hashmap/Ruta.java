package hashmap;

/**
 * Representa una ruta dentro de la tabla de ruteo.
 * Cada ruta define red destino, máscara, next hop, interfaz,
 * métrica y protocolo asociado.
 */
public class Ruta {

    private String redDestino;      // Ej: "192.168.1.0/24"
    private String mascaraSubred;   // Ej: "255.255.255.0"
    private String nextHop;         // Siguiente salto
    private String interfaz;        // Interfaz de salida
    private int metrica;            // Costo de la ruta
    private String protocolo;       // STATIC, OSPF, BGP, CONNECTED, etc.

    public Ruta(String redDestino, String mascaraSubred,
                String nextHop, String interfaz, int metrica, String protocolo) {

        this.redDestino = redDestino;
        this.mascaraSubred = mascaraSubred;
        this.nextHop = nextHop;
        this.interfaz = interfaz;
        this.metrica = metrica;
        this.protocolo = protocolo;
    }

    public String getRedDestino() {
        return redDestino;
    }

    public String getMascaraSubred() {
        return mascaraSubred;
    }

    public String getNextHop() {
        return nextHop;
    }

    public String getInterfaz() {
        return interfaz;
    }

    public int getMetrica() {
        return metrica;
    }

    public String getProtocolo() {
        return protocolo;
    }

    @Override
    public String toString() {
        return "[" + redDestino + " via " + nextHop + " (" + protocolo + ")]";
    }
}

