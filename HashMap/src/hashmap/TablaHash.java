
package hashmap;

/**
 *
 * @author apina
 */
import java.util.LinkedList;

/**
 * Implementación de una tabla hash utilizando encadenamiento separado.
 * Maneja colisiones por medio de listas enlazadas y realiza rehashing
 * automático cuando el factor de carga supera 0.75.
 *
 * @param <K> Tipo de las claves
 * @param <V> Tipo de los valores
 * @author Tu nombre
 * @version 1.0
 */
public class TablaHash<K, V> implements Diccionario<K, V> {

    /**
     * Clase interna que representa un nodo con clave y valor.
     */
    private class Nodo {
        K key;
        V value;

        public Nodo(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    // Atributos principales
    private LinkedList<Nodo>[] tabla;   // Arreglo de listas (buckets)
    private int size;                   // Cantidad de elementos
    private int capacidad;              // Tamaño del arreglo
    private static final double FACTOR_CARGA_MAX = 0.75;

    /**
     * Constructor por defecto.
     * Inicializa la tabla con capacidad 11.
     */
    @SuppressWarnings("unchecked")
    public TablaHash() {
        this.capacidad = 11;
        this.tabla = new LinkedList[capacidad];
        this.size = 0;

        for (int i = 0; i < capacidad; i++) {
            tabla[i] = new LinkedList<>();
        }
    }

    /**
     * Calcula un índice válido del arreglo usando hashCode().
     *
     * @param key clave a hashear
     * @return índice en el rango [0, capacidad-1]
     */
    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % capacidad;
    }

    /**
     * Inserta o actualiza un par clave-valor. Si la clave ya existe
     * actualiza su valor. Si no existe, lo agrega.
     * Realiza resize() si el factor de carga excede 0.75.
     *
     * @param key clave del elemento
     * @param value valor asociado
     */
    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new NullPointerException("La clave no puede ser null.");
        }

        int indice = hash(key);
        LinkedList<Nodo> lista = tabla[indice];

        // Buscar si la clave ya existe
        for (Nodo nodo : lista) {
            if (nodo.key.equals(key)) {
                nodo.value = value; // actualizar
                return;
            }
        }

        // Insertar nuevo nodo
        lista.add(new Nodo(key, value));
        size++;

        // Verificar factor de carga
        if ((double) size / capacidad >= FACTOR_CARGA_MAX) {
            resize();
        }
    }

    /**
     * Devuelve el valor asociado a una clave.
     *
     * @param key clave a buscar
     * @return valor encontrado o null si no existe
     */
    @Override
    public V get(K key) {
        int indice = hash(key);
        LinkedList<Nodo> lista = tabla[indice];

        for (Nodo nodo : lista) {
            if (nodo.key.equals(key)) {
                return nodo.value;
            }
        }

        return null;
    }

    /**
     * Elimina una clave de la tabla hash.
     *
     * @param key clave a eliminar
     * @return valor eliminado o null si no se encontró
     */
    @Override
    public V remove(K key) {
        int indice = hash(key);
        LinkedList<Nodo> lista = tabla[indice];

        for (int i = 0; i < lista.size(); i++) {
            Nodo nodo = lista.get(i);
            if (nodo.key.equals(key)) {
                lista.remove(i);
                size--;
                return nodo.value;
            }
        }

        return null;
    }

    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    @Override
    public int size() {
        return size;
    }

    /**
    * Devuelve una lista con todas las claves almacenadas en la tabla.
    * Permite iterar sobre todas las rutas, algo necesario para routing.
    *
    * @return LinkedList con las claves
    */
    public LinkedList<K> keys() {
        LinkedList<K> lista = new LinkedList<>();

        for (LinkedList<Nodo> bucket : tabla) {
            for (Nodo nodo : bucket) {
            lista.add(nodo.key);
            }
        }

        return lista;
    }

    
    /**
     * Duplica la capacidad de la tabla y reubica todos los elementos.
     */
    @SuppressWarnings("unchecked")
    private void resize() {
        LinkedList<Nodo>[] tablaVieja = tabla;

        capacidad *= 2;
        tabla = new LinkedList[capacidad];

        for (int i = 0; i < capacidad; i++) {
            tabla[i] = new LinkedList<>();
        }

        size = 0;

        // Reinsertar todo
        for (LinkedList<Nodo> bucket : tablaVieja) {
            for (Nodo nodo : bucket) {
                put(nodo.key, nodo.value);
            }
        }
    }
}


