public class HashMap {
    private static final int DEFAULT_TABLE_SIZE = 1000000;
    private static final double LOAD_FACTOR = 0.5;
    private int tableSize;
    private int numRecords;
    private String[] keys;
    private String[] values;

    public HashMap() {
        this.tableSize = DEFAULT_TABLE_SIZE;
        this.numRecords = 0;
        this.keys = new String[tableSize];
        this.values = new String[tableSize];
    }

    /**
     * Method:
     * Computes a hash value from a string
     */
    public static long hash(String key) {
        long hash = 0;
        int RADIX = 256;
        long p = 54321102419L;
        for (int i = 0; i < key.length(); i++) {
            hash = (hash * RADIX + key.charAt(i)) % p;
        }
        return hash;
    }

    public void add(String key, String value) {
        if (numRecords * 1.0 / tableSize >= LOAD_FACTOR) {
            resize();
        }
        int index = (int)(hash(key) % tableSize);
        while (keys[index] != null) {
            if (keys[index].equals(key)) {
                values[index] = value;
                return;
            }
            index = (index + 1) % tableSize;
        }
        keys[index] = key;
        values[index] = key;
        numRecords++;
    }

    public String get(String key) {
        int index = (int)(hash(key) % tableSize);
        while (keys[index] != null) {
            if (keys[index].equals(key)) {
                return values[index];
            }
            index = (index + 1) % tableSize;
        }
        return null;
    }

    public void resize() {
        int newSize = tableSize * 2;
        String[] oldKeys = keys;
        String[] oldValues = values;
        keys = new String[newSize];
        values = new String[newSize];
        tableSize = newSize;
        numRecords = 0;
        for (int i = 0; i < oldKeys.length; i++) {
            if (oldKeys[i] != null) {
                add(oldKeys[i], oldValues[i]);
            }
        }
    }
}

