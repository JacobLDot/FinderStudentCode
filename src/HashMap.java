public class HashMap {
    private static final int DEFAULT_TABLE_SIZE = 1627;
    private static final double LOAD_FACTOR = 0.7;
    private int tableSize;
    private int numRecords;
    private String[] keys;
    private String[] values;

    /**
     * Constructor:
     * Initializes variables
     */
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
        int RADIX = 31;
        long p = 54321102419L;
        for (int i = 0; i < key.length(); i++) {
            hash = (hash * RADIX + key.charAt(i)) % p;
        }
        return hash;
    }

    /**
     * Method:
     * Adds a key and value to an array
     */
    public void add(String key, String value) {

        // If the table needs to be resized
        if (numRecords * 1.0 / tableSize >= LOAD_FACTOR) {
            resize();
        }
        int index = (int)(hash(key) % tableSize);

        // Loop through the array and check for an open index; move 1 right if already filled
        while (keys[index] != null) {
            if (keys[index].equals(key)) {
                values[index] = value;
                return;
            }
            index = (index + 1) % tableSize;
        }
        keys[index] = key;
        values[index] = value;
        numRecords++;
    }

    /**
     * Method:
     * Gets the value associated with a key
     */
    public String get(String key) {
        int index = (int)(hash(key) % tableSize);

        // Loop through the array and check if the key matches the key at index; move 1 right if already filled
        while (keys[index] != null) {
            if (keys[index].equals(key)) {
                return values[index];
            }
            index = (index + 1) % tableSize;
        }
        return null;
    }

    /**
     * Method:
     * Resizes the arrays while keeping the same values
     */
    public void resize() {
        int newSize = tableSize * 2 - 1;

        // Save keys and values
        String[] oldKeys = keys;
        String[] oldValues = values;
        keys = new String[newSize];
        values = new String[newSize];
        tableSize = newSize;
        numRecords = 0;

        // Load saved keys and values into the resized table
        for (int i = 0; i < oldKeys.length; i++) {
            if (oldKeys[i] != null) {
                add(oldKeys[i], oldValues[i]);
            }
        }
    }
}

