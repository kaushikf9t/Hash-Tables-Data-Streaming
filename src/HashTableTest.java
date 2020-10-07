import java.util.*;

public class HashTableTest {
    public static void main(String[] args) {
        int numTableEntries = 1000;
        int numFlows = 1000;
        int numHashes = 3;
        int numCuckooSteps = 2;
        int numSegments = 4;
        int segmentSize = 250;

        //Tester Code for Multi Hash table
        MultiHashTable multiHashTable = new MultiHashTable(numTableEntries, numHashes);
        Set<Integer> integerSet = new TreeSet<>();
        Random rd = new Random();
        while(integerSet.size() < numFlows) {
            integerSet.add(rd.nextInt(Integer.MAX_VALUE));
        }

//        for (Iterator<Integer> it = integerSet.iterator(); it.hasNext(); ) {
//            int flowID = it.next();
//            multiHashTable.insertFlow(flowID);
//
//        }
//        HashTablesUtil.printHashTable(multiHashTable.getHashTableEntries());
//
//        //Tester Code for Cuckoo Hash Table
//        CuckooHashTable cuckooHashTable = new CuckooHashTable(numTableEntries, numFlows, numHashes, numCuckooSteps);
//        for(int i = 1; i <= numFlows; i++){
//            cuckooHashTable.insertFlow(i);
//        }
//        HashTablesUtil.printHashTable(cuckooHashTable.getHashTableEntries());

        //Tester Code for D-Left hash table
        DLeftHashTable dLeftHashTable = new DLeftHashTable(numTableEntries, numSegments, segmentSize);
        for (Iterator<Integer> it = integerSet.iterator(); it.hasNext(); ) {
            int flowID = it.next();
            dLeftHashTable.insertFlow(flowID);

        }
        HashTablesUtil.printHashTable(dLeftHashTable.getHashTableEntries());

    }
}
