import java.io.IOException;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class HashTableTest {
    public static void main(String[] args) throws IOException {
        //Multi-Hash table
        int numFlows = 1000;
        int numTableEntries = 1000;
        int numHashes = 3;

        //Cuckoo-Hash table
        int numCuckooSteps = 2;

        //D-Left Hash table
        int numSegments = 4;
        int segmentSize = 250;

        //Tester Code for Multi Hash table
        MultiHashTable multiHashTable = new MultiHashTable(numFlows, numTableEntries, numHashes);
        Set<Integer> integerSet = new TreeSet<>();
        Random rd = new Random();
        while(integerSet.size() < numFlows) {
            integerSet.add(rd.nextInt(Integer.MAX_VALUE));
        }

        for (Iterator<Integer> it = integerSet.iterator(); it.hasNext(); ) {
            int flowID = it.next();
            multiHashTable.insertFlow(flowID);

        }

        HashTablesUtil.writeTableEntriesToOutputFile("Multi",
                HashTablesUtil.getNumFlows(multiHashTable.getHashTableEntries()));
        //HashTablesUtil.printHashTable(multiHashTable.getHashTableEntries());

        //Tester Code for Cuckoo Hash Table
        CuckooHashTable cuckooHashTable = new CuckooHashTable(numTableEntries, numFlows, numHashes, numCuckooSteps);
        for(int i = 1; i <= numFlows; i++){
            cuckooHashTable.insertFlow(i);
        }
        HashTablesUtil.writeTableEntriesToOutputFile("Cuckoo",
                HashTablesUtil.getNumFlows(cuckooHashTable.getHashTableEntries()));
        //HashTablesUtil.printHashTable(cuckooHashTable.getHashTableEntries());

        //Tester Code for D-Left hash table
        DLeftHashTable dLeftHashTable = new DLeftHashTable(numTableEntries, numSegments, segmentSize);
        for (Iterator<Integer> it = integerSet.iterator(); it.hasNext(); ) {
            int flowID = it.next();
            dLeftHashTable.insertFlow(flowID);

        }
        HashTablesUtil.writeTableEntriesToOutputFile("DLeft",
                HashTablesUtil.getNumFlows(dLeftHashTable.getHashTableEntries()));
        //HashTablesUtil.printHashTable(dLeftHashTable.getHashTableEntries());

    }
}
