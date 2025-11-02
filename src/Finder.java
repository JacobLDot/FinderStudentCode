import java.io.BufferedReader;
import java.io.IOException;
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
    private KeyValue[] table;

    /**
     * Constructor:
     * Initializes the table of KeyValues
     */
    public Finder() {
        table = new KeyValue[TABLE_SIZE];
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

            // Add the key and value to the table
            table[Integer.parseInt(value)] = new KeyValue(key, value);
        }

        // Close resource
        br.close();
    }

    public String query(String key){
        // TODO: Complete the query() function!
        return INVALID;
    }
}
