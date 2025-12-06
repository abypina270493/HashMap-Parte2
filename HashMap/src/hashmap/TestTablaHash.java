package hashmap;

/**
 * Clase de prueba para la implementación de TablaHash.
 * 
 * Esta clase ejecuta diversas operaciones sobre un objeto de tipo TablaHash
 * para verificar su correcto funcionamiento. Se prueban inserciones, 
 * actualizaciones de valores, búsquedas, eliminaciones, el método containsKey
 * y la verificación del tamaño actual de la tabla.
 * 
 * No forma parte de la implementación de la estructura, sino que funciona
 * como un archivo auxiliar para demostrar el comportamiento del programa.
 * 
 * @author apina
 */
public class TestTablaHash {

    public static void main(String[] args) {

        TablaHash<String, Integer> mapa = new TablaHash<>();

        System.out.println("Insertando valores...");
        mapa.put("manzana", 5);
        mapa.put("pera", 3);
        mapa.put("uva", 10);

        System.out.println("manzana = " + mapa.get("manzana"));
        System.out.println("pera = " + mapa.get("pera"));

        System.out.println("\nActualizando 'manzana'...");
        mapa.put("manzana", 99);
        System.out.println("manzana = " + mapa.get("manzana"));

        System.out.println("\nRemoviendo 'pera'...");
        System.out.println("Valor eliminado: " + mapa.remove("pera"));

        System.out.println("\nSize actual: " + mapa.size());

        System.out.println("\nProbando containsKey...");
        System.out.println("Existe 'uva'? " + mapa.containsKey("uva"));
        System.out.println("Existe 'pera'? " + mapa.containsKey("pera"));
    }
}


