package hashmap;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Tabla de ruteo implementada utilizando la TablaHash creada en la Parte 1.
 * Simula el funcionamiento real de routers: almacenamiento, búsqueda y
 * mantenimiento de rutas.
 */
public class TablaRuteo {

    private TablaHash<String, Ruta> tabla;
    private int consultasExitosas;
    private int consultasFallidas;

    public TablaRuteo() {
        this.tabla = new TablaHash<>();
        this.consultasExitosas = 0;
        this.consultasFallidas = 0;
    }

    /**
     * Agrega o actualiza una ruta en la tabla de ruteo.
     * <p>
     * Si la red ya existe, únicamente se actualiza cuando la nueva ruta
     * posee una métrica menor, siguiendo la lógica del "mejor camino".
     * Si la red no existe, se inserta como una nueva entrada.
     *
     * @param ruta La ruta a agregar o evaluar para actualización.
     */
    public void agregarRuta(Ruta ruta) {
        String clave = ruta.getRedDestino();

        if (tabla.containsKey(clave)) {
            Ruta existente = tabla.get(clave);

            if (ruta.getMetrica() < existente.getMetrica()) {
                tabla.put(clave, ruta);
                System.out.println("[UPDATE] Ruta mejorada: " + clave);
            }
        } else {
            tabla.put(clave, ruta);
            System.out.println("[ADD] Nueva ruta: " + clave);
        }
    }

        /**
     * Busca la ruta más adecuada para una IP destino utilizando
     * un esquema simplificado de "Longest Prefix Match".
     * <p>
     * El método compara la IP destino contra cada red almacenada,
     * seleccionando aquella cuyo prefijo coincida más específicamente.
     * Si no se encuentra una coincidencia, se evalúa la ruta por defecto
     * (0.0.0.0/0). Si tampoco existe, la búsqueda falla.
     *
     * @param ipDestino Dirección IP destino en formato decimal con puntos.
     * @return La ruta correspondiente si existe, o {@code null} si no hay
     *         ninguna ruta aplicable.
     */
    public Ruta buscarRuta(String ipDestino) {

        for (String red : obtenerRedesOrdenadas()) {
            if (ipPerteneceARedSimplificado(ipDestino, red)) {
                consultasExitosas++;
                return tabla.get(red);
            }
        }

        // Ruta por defecto
        if (tabla.containsKey("0.0.0.0/0")) {
            consultasExitosas++;
            return tabla.get("0.0.0.0/0");
        }

        consultasFallidas++;
        return null;
    }

    /**
     * Elimina una ruta asociada a una red destino.
     * <p>
     * Simula la eliminación de una ruta cuando un enlace cae
     * o cuando un protocolo de enrutamiento retira la ruta.
     *
     * @param redDestino La red cuya ruta será eliminada.
     * @return {@code true} si la ruta existía y fue eliminada,
     *         {@code false} si no estaba registrada.
     */
    public boolean eliminarRuta(String redDestino) {
        if (tabla.containsKey(redDestino)) {
            tabla.remove(redDestino);
            System.out.println("[DELETE] Ruta eliminada: " + redDestino);
            return true;
        }
        return false;
    }

    /**
     * Ordena las redes por prefijo (longest-prefix match simplificado).
     */
    private ArrayList<String> obtenerRedesOrdenadas() {
        ArrayList<String> redes = new ArrayList<>(tabla.keys());

        // Ordenar por tamaño de prefijo (más específico primero)
        Collections.sort(redes, (a, b) -> {
            int prefA = Integer.parseInt(a.split("/")[1]);
            int prefB = Integer.parseInt(b.split("/")[1]);
            return Integer.compare(prefB, prefA); // Descendente
        });

        return redes;
    }

    /**
     * Determina si una dirección IP pertenece a una red dada en formato CIDR.
     * <p>
     * Este método implementa una versión simplificada del algoritmo
     * "Longest Prefix Match", comparando únicamente los octetos completos
     * definidos por el prefijo de la máscara (por ejemplo: /8, /16, /24).
     * <br><br>
     * Se realiza de la siguiente forma:
     * <ul>
     *   <li>Se separa la red y el prefijo (ejemplo: "192.168.1.0/24").</li>
     *   <li>Se calcula cuántos octetos deben coincidir: prefijo / 8.</li>
     *   <li>Se comparan los primeros N octetos de la IP destino contra los de la red.</li>
     * </ul>
     * Si todos los octetos requeridos coinciden, la IP pertenece a la red.
     *
     * <p><b>Ejemplo:</b><br>
     * IP destino: 192.168.1.50<br>
     * Red: 192.168.1.0/24 → prefijo = 24 → octetos a comparar = 3<br>
     * Coinciden: 192 == 192, 168 == 168, 1 == 1 → pertenece.
     *
     * @param ip        Dirección IP destino en formato decimal con puntos
     *                  (ejemplo: "192.168.1.50").
     * @param redCIDR   Red en notación CIDR (ejemplo: "192.168.1.0/24").
     * @return {@code true} si la IP pertenece a la red indicada,
     *         {@code false} en caso contrario.
     */
    public boolean ipPerteneceARedSimplificado(String ip, String redCIDR) {

        String[] partes = redCIDR.split("/");
        String red = partes[0];
        int prefijo = Integer.parseInt(partes[1]); // Ej: 24

        String[] ipPartes = ip.split("\\.");
        String[] redPartes = red.split("\\.");

        int octetos = prefijo / 8;  // Ej: 24/8 = 3 octetos a comparar

        for (int i = 0; i < octetos; i++) {
            if (!ipPartes[i].equals(redPartes[i])) {
                return false;
            }
        }

        return true;
    }


    public void imprimirEstadisticas() {
        System.out.println("========================================");
        System.out.println(" ESTADISTICAS DE LA TABLA DE RUTEO:");
        System.out.println(" - Consultas exitosas: " + consultasExitosas);
        System.out.println(" - Consultas fallidas: " + consultasFallidas);
        System.out.println("========================================");
    }

    // Método auxiliar temporal para acceder al tamaño interno
    private int tablaPrimitivaSize() {
        return tabla.size();
    }
}

