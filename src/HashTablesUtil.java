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
        double filledCount = 0.0;
        for (int hashTableEntry : hashTableEntries) {
            if(hashTableEntry > 0) {
                filledCount++;
                //System.out.println("Flow " + hashTableEntry);
            }
        }
        System.out.println("Filled Ratio " + filledCount/hashTableEntries.length);
    }

}
