Hash-Tables-Data-Streaming
Hash Tables are used to store packets from different flows and sources in Data Streaming to capture statistics like flow size and flow spread. Hash tables can be used to capture other information like number of distinct sources and destinations.

MultiHashTable
This uses k different hash functions to hash the FlowID to a unique position in the hash table, higher chances of avoiding collisions with a larger k. This maintains an array of hash functions internally to which each FlowID is hashed to, in order to find a unique position.

CuckooHashTable
This tries to improve upon Multi-Hash table by first trying to place an element in the k indexes returned by the hash functions, and if none of them returns an empty index, this algorithm tries to displace elements in the existing indexes and put the new element and does so recursively to optimize and fit in as many flows as possible. Fit in the maximum number of flows with a usage of ~98%

DLeftHashTable
This manages the hash table by creating d segments each of which has a unique hash function and fills in the table from left to right, ensuring more entries are filled in each segment in comparison with Multi-Hash table
