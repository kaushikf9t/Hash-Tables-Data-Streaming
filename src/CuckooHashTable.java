public class CuckooHashTable {
    private int numTableEntries;

    private int numFlows;

    private int numHashFunctions;

    private int numSteps;

    private int[] hashFunctionsArray;

    private int[] hashTableEntries;

    public int[] getHashTableEntries() {
        return hashTableEntries;
    }

    public CuckooHashTable(int numTableEntries, int numFlows, int numHashFunctions, int numSteps) {
        this.numTableEntries = numTableEntries;
        this.numFlows = numFlows;
        this.numHashFunctions = numHashFunctions;
        this.numSteps = numSteps;
        hashFunctionsArray = HashTablesUtil.initHashFunctionsArray(numHashFunctions);
        this.hashTableEntries = new int[numTableEntries];

    }

    public void insertFlow(int flowID) {
        int arrayIndex = getArrayIndex(flowID);

        if(arrayIndex >= 0) {
            hashTableEntries[arrayIndex] = flowID;
        }

        //Displace the elements in ArrayIndex
        else {
            for(int k = 0; k < numHashFunctions; k++) {
                int hashOutput = HashTablesUtil.getHashCode(flowID^hashFunctionsArray[k], numTableEntries);
                if(displaceElementFromPosition(hashOutput, numSteps)) {
                    hashTableEntries[hashOutput] = flowID;
                }
            }
        }
    }

    private boolean displaceElementFromPosition(int position, int numSteps) {
        //If a negative input is provided or numSteps are exhausted
        if(numSteps <= 0) {
            return false;
        }

        int flowInPosition = hashTableEntries[position];

        //First level displacement
        for(int k = 0; k < numHashFunctions; k++) {
            int newArrayIndex = getArrayIndex(flowInPosition);
            if(newArrayIndex >= 0 && newArrayIndex != position) {
                hashTableEntries[newArrayIndex] = flowInPosition;
                hashTableEntries[position] = 0;
                return true;
            }
        }

        //Further levels of displacement
        for(int k = 0; k < numHashFunctions; k++) {
            int newArrayIndex = getArrayIndex(flowInPosition);
            if(newArrayIndex >= 0 && newArrayIndex != position) {
                if(displaceElementFromPosition(newArrayIndex, numSteps - 1)) {
                    hashTableEntries[newArrayIndex] = flowInPosition;
                    hashTableEntries[position] = 0;
                    return true;
                }
            }
        }

        return false;
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
