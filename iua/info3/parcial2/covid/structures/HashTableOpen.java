package iua.info3.parcial2.covid.structures;

public class HashTableOpen<TypeKey extends Comparable, TypeValue> {

    AvlTree<HashEntry<TypeKey, TypeValue>>[] table;

    private int size = 0;

    public HashTableOpen(int size) {
        this.size = size;
        this.table = new AvlTree[size];
        for (int i = 0; i < table.length; i++) {
            table[i] = new AvlTree<>();
        }
    }

    /*
     * private int hashFunc(TypeKey key) { return (int) key % table.length; }
     */

    private int hashFunc(TypeKey key) {

        return ((int) key % size);

    }

    public void insert(TypeKey clave, TypeValue value) throws Exception {
        int pos = hashFunc(clave);
        int i = 0;

        table[pos].insert(new HashEntry<>(clave, value));
    }

    public TypeValue get(TypeKey clave) throws Exception {
        int pos = hashFunc(clave);
        return table[pos].get(new HashEntry<>(clave, null)).value;
    }

    public void remove(TypeKey clave) throws Exception {
        int pos = hashFunc(clave);
        table[pos].remove(new HashEntry<>(clave, null));
    }

    private static class HashEntry<TypeKey extends Comparable, TypeValue> implements Comparable<HashEntry> {
        TypeKey key;
        TypeValue value;

        public HashEntry(TypeKey key, TypeValue value) {
            this.key = key;
            this.value = value;
        }

        public TypeKey getKey() {
            return key;
        }

        public void setKey(TypeKey key) {
            this.key = key;
        }

        public TypeValue getValue() {
            return value;
        }

        public void setValue(TypeValue value) {
            this.value = value;
        }

        @Override
        public int compareTo(HashEntry hashEntry) {
            return key.compareTo(hashEntry.getKey());
        }
    }
}
