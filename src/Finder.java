import java.io.BufferedReader;
import java.io.IOException;

/**
 * Finder
 * A puzzle written by Zach Blick
 * for Adventures in Algorithms
 * At Menlo School in Atherton, CA
 * Completed by: [Jacob Lowe]
 **/

public class Finder {
    private static final String INVALID = "INVALID KEY";
    private final HashMap map;

    /**
     * Constructor:
     * Initializes the table of KeyValues
     */
    public Finder() {
        map = new HashMap();
    }

    /**
     * Method:
     * Reads each line from the CSV and extracts the key and value from each column
     */
    public void buildTable(BufferedReader br, int keyCol, int valCol) throws IOException {
        String line;

        // Go through every line from the CSV and split each line by commas
        // to separate columns, then extract the key and value from the column
        // Finally, add the key and value to the table
        while ((line = br.readLine()) != null) {
            String[] columns = line.split(",");
            String key = columns[keyCol];
            String value = columns[valCol];
            map.add(key, value);
        }

        // Close resource
        br.close();
    }

    /**
     * Method:
     * Returns value associated with a key
     */
    public String query(String key){

        // Find key value, return it, otherwise if key is not found, return invalid
        String result = map.get(key);
        if (result == null) return INVALID;
        return result;
    }
}
