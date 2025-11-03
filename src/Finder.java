import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Finder
 * A puzzle written by Zach Blick
 * for Adventures in Algorithms
 * At Menlo School in Atherton, CA
 *
 * Completed by: [Jacob Lowe]
 **/

public class Finder {

    // Value if key is not found in the table
    private static final String INVALID = "INVALID KEY";

    // Number of KeyValues in the table
    private static final int TABLE_SIZE = 1000000;

    // Table of KeyValues
    private ArrayList<KeyValue>[] table;

    /**
     * Helper Class:
     * Store key-value pairs both as strings
     */
    private static class KeyValue {
        public String key;
        public String value;
        public KeyValue (String key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    /**
     * Constructor:
     * Initializes the table of KeyValues
     */
    public Finder() {
        table = new ArrayList[TABLE_SIZE];
        for (int i = 0; i < TABLE_SIZE; i++) {
            table[i] = new ArrayList<>();
        }
    }

    /**
     * Method:
     * Reads each line from the CSV and extracts the key and value from each column
     */
    public void buildTable(BufferedReader br, int keyCol, int valCol) throws IOException {
        String line;

        // Go through every line from the CSV and split each line by commas
        // to separate columns, then extract the key and value from the column
        while ((line = br.readLine()) != null) {
            String[] columns = line.split(",", -1);
            String key = columns[keyCol];
            String value = columns[valCol];

            long keyHash = hash(key);
            int index = (int)(keyHash % TABLE_SIZE);
            // Add the key and value to the table

        }

        // Close resource
        br.close();
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

    /**
     * Method:
     * Returns value associated with a key
     */
    public String query(String key){
        long keyHash = hash(key);
        int index = (int)(keyHash % TABLE_SIZE);

        // Search through the list for the exact key
        for (KeyValue keyValue : table[index]) {
            if (keyValue.key.equals(key)) {
                return keyValue.value;
            }
        }

        // If not found, return INVALID
        return INVALID;
    }
}
