/**
 * Maintains an array with Flow IDs in place
 * Table is divided into d-segments
 * Table gets filled from left to right
 */
public class DLeftHashTable {
    private int[] hashFunctionsArray;

    public int[] getHashTableEntries() {
        return hashTableEntries;
    }

    private int[] hashTableEntries;

    private int numTableEntries;

    private int numSegments;

    private int segmentSize;

    public DLeftHashTable(int numTableEntries, int numSegments, int segmentSize) {
        this.numSegments = numSegments;
        this.segmentSize = segmentSize;
        this.numTableEntries = numTableEntries;
        hashFunctionsArray = HashTablesUtil.initHashFunctionsArray(numSegments);
        this.hashTableEntries = new int[numTableEntries];
    }

    public void insertFlow(int flowID) {
        int leftMostPosition = getArrayIndex(flowID);
        if(leftMostPosition >= 0)
            hashTableEntries[leftMostPosition] = flowID;

    }

    private int getArrayIndex(int flowID) {
        int arrayIndex = -1;

        for(int k = 0; k < numSegments; k++) {
            arrayIndex = HashTablesUtil.getHashCode(flowID^hashFunctionsArray[k], segmentSize)+(k*segmentSize);
            if (hashTableEntries[arrayIndex] == 0) {
                return arrayIndex;
            }
        }
        return arrayIndex;
    }
}
