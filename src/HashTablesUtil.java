import java.io.*;
import java.util.Random;

public class HashTablesUtil {
    public static int[] initHashFunctionsArray(int numHashFunctions) {
        int [] hashFunctionsArray = new int[numHashFunctions];
        Random rd = new Random();
        for (int i = 0; i < hashFunctionsArray.length; i++) {
            hashFunctionsArray[i] = rd.nextInt(Integer.MAX_VALUE);// storing random integers in an array
            //System.out.println(hashFunctionsArray[i]); // printing each array element
        }
        return hashFunctionsArray;
    }

    public static int getHashCode(int hashInput, int numTableEntries) {
        return hashInput%numTableEntries;
    }

    public static void printHashTable(int[] hashTableEntries) {
        for (int hashTableEntry : hashTableEntries) {
            System.out.println("Flow " + hashTableEntry);
        }
        System.out.println("Total number of flows in the table " + getNumFlows(hashTableEntries));
    }

    public static void writeTableEntriesToOutputFile(String tableType, int numFlows) throws IOException {
        String fileName = tableType + "output.txt";
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(Integer.toString(numFlows));
        writer.close();
    }

    public static int getNumFlows(int[] hashTableEntries) {
        int filledCount = 0;
        for (int hashTableEntry : hashTableEntries) {
            if(hashTableEntry > 0) {
                filledCount++;
            }
        }
        return filledCount;
    }

}
