
package hashmap;

/**
 *
 * @author apina
 */

/**
 * Interfaz Diccionario para estructuras tipo mapa.
 *
 * @param <K> Tipo de las claves
 * @param <V> Tipo de los valores
 */
public interface Diccionario<K, V> {

    void put(K key, V value);

    V get(K key);

    V remove(K key);

    boolean containsKey(K key);

    int size();
}


