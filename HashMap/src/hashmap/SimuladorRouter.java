package hashmap;

/**
 * Simulador que demuestra el funcionamiento de la tabla de ruteo.
 */
public class SimuladorRouter {

    private TablaRuteo tablaRuteo;

    public SimuladorRouter() {
        this.tablaRuteo = new TablaRuteo();
        inicializarRutas();
    }

        /**
     * Inicializa la tabla de ruteo con rutas típicas encontradas
     * en un entorno empresarial.
     * <p>
     * Incluye rutas conectadas directamente, rutas estáticas,
     * rutas aprendidas mediante OSPF y la ruta por defecto.
     */
    private void inicializarRutas() {

        tablaRuteo.agregarRuta(new Ruta(
                "0.0.0.0/0", "0.0.0.0",
                "200.100.50.1", "eth0", 10, "STATIC"
        ));

        tablaRuteo.agregarRuta(new Ruta(
                "192.168.1.0/24", "255.255.255.0",
                "0.0.0.0", "eth1", 0, "CONNECTED"
        ));

        tablaRuteo.agregarRuta(new Ruta(
                "10.0.0.0/8", "255.0.0.0",
                "192.168.1.254", "eth1", 20, "OSPF"
        ));

        tablaRuteo.agregarRuta(new Ruta(
                "172.16.0.0/16", "255.255.0.0",
                "192.168.1.253", "eth2", 5, "STATIC"
        ));
    }

    public void procesarPaquetes() {
        String[] paquetes = {
                "192.168.1.50",
                "10.5.3.2",
                "172.16.10.5",
                "8.8.8.8",
                "192.168.2.1",
                "10.0.0.1"
        };

        System.out.println("========================================");
        System.out.println(" SIMULADOR DE ROUTER - FORWARDING");
        System.out.println("========================================");

        for (String destino : paquetes) {
            Ruta ruta = tablaRuteo.buscarRuta(destino);

            if (ruta != null) {
                System.out.println("\nPaquete -> " + destino);
                System.out.println(" Ruta encontrada: " + ruta.getRedDestino());
                System.out.println(" Next Hop: " + ruta.getNextHop());
                System.out.println(" Interfaz: " + ruta.getInterfaz());
                System.out.println(" Protocolo: " + ruta.getProtocolo()
                        + " (metrica: " + ruta.getMetrica() + ")");
                System.out.println(" ACCION: FORWARD");
            } else {
                System.out.println("\nPaquete -> " + destino);
                System.out.println(" ACCION: DROP (No route to host)");
            }
        }

        tablaRuteo.imprimirEstadisticas();
    }
}

