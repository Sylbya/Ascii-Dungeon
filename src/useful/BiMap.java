package useful;


import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public class BiMap<K, V> extends HashMap<K, V> {
    private Map<V, K> inversedMap = new HashMap<>();

    public K getKey(V value) {
        return inversedMap.get(value);
    }

    @Override
    public V put(K key, V value) {
        inversedMap.put(value, key);
        return super.put(key, value);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        super.putAll(m);
        for (K key : m.keySet()) {
            inversedMap.put(super.get(key), key);
        }
    }

    @Override
    public V remove(Object key) {
        inversedMap.remove(super.get(key));
        return super.remove(key);
    }

    @Override
    public void clear() {
        super.clear();
        inversedMap.clear();
    }

    @Override
    public V putIfAbsent(K key, V value) {
        inversedMap.putIfAbsent(value, key);
        return super.putIfAbsent(key, value);
    }

    @Override
    public boolean remove(Object key, Object value) {
        inversedMap.remove(value, key);
        return super.remove(key, value);
    }

    @Override
    public boolean replace(K key, V oldValue, V newValue) {
        inversedMap.remove(oldValue);
        inversedMap.put(newValue, key);
        return super.replace(key, oldValue, newValue);
    }

    @Override
    public V replace(K key, V value) {
        if (super.containsKey(key)) {
            put(key, value);
        }
        return value;
    }

    @Override
    public V computeIfAbsent(K key,
                             Function<? super K, ? extends V> mappingFunction) {
        V value = super.computeIfAbsent(key, mappingFunction);
        inversedMap.put(value, key);
        return value;
    }

    @Override
    public V computeIfPresent(K key,
                              BiFunction<? super K,? super V,? extends V> remappingFunction) {
        V value = super.computeIfPresent(key, remappingFunction);
        inversedMap.put(value, key);
        return value;
    }

    @Override
    public V compute(K key,
                     BiFunction<? super K,? super V,? extends V> remappingFunction) {
        V value = super.compute(key, remappingFunction);
        inversedMap.put(value, key);
        return value;
    }

    @Override
    public V merge(K key,
                   V value,
                   BiFunction<? super V,? super V,? extends V> remappingFunction) {
        V replacedValue = super.merge(key, value, remappingFunction);
        inversedMap.remove(value);
        inversedMap.put(replacedValue, key);
        return replacedValue;
    }

    @Override
    public void replaceAll(BiFunction<? super K,? super V,? extends V> function) {
        inversedMap.clear();
        super.replaceAll(function);
        for (K key : super.keySet()) {
            inversedMap.put(super.get(key), key);
        }
    }
}