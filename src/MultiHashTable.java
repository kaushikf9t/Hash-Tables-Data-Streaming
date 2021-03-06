/**
 * Maintains an array with FlowIDs in place
 * Implementations of hash functions for a given K
 */
public class MultiHashTable {

    private int[] hashFunctionsArray;

    private int[] hashTableEntries;

    private int numHashFunctions;

    private int numTableEntries;

    private int numFlows;

    public int[] getHashTableEntries() {
        return hashTableEntries;
    }

    public MultiHashTable(int numFlows, int numTableEntries, int numHashFunctions) {
        this.numFlows = numFlows;
        this.numTableEntries = numTableEntries;
        this.numHashFunctions = numHashFunctions;
        hashFunctionsArray = HashTablesUtil.initHashFunctionsArray(numHashFunctions);
        this.hashTableEntries = new int[numTableEntries];

    }

    public void insertFlow(int flowID) {
        int arrayIndex = getArrayIndex(flowID);
        if(arrayIndex >= 0)
            hashTableEntries[arrayIndex] = flowID;
    }

    private int getArrayIndex(int flowID) {
        for(int k = 0; k < numHashFunctions; k++) {
            int arrayIndex = HashTablesUtil.getHashCode(flowID^hashFunctionsArray[k], numTableEntries);

            if (hashTableEntries[arrayIndex] == 0) {
                return arrayIndex;
            }
        }
        return -1;
    }

}