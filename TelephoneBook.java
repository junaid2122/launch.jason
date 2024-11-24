import java.util.*;

public class TelephoneBook {

    // Hash Function
    private static int hashFunction(int key) {
        return key % 10; // Using a constant TABLE_SIZE of 10 for simplicity
    }

    // Separate Chaining: Hash Table using Linked Lists
    static class HashTableChaining {
        private List<List<Integer>> table;

        public HashTableChaining() {
            table = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                table.add(new LinkedList<>()); // Fixed size table
            }
        }

        // Insert key into hash table
        public void insertItem(int key) {
            int index = hashFunction(key);
            table.get(index).add(key);
        }

        // Function to search for a key and count comparisons
        public boolean searchItem(int key) {
            int index = hashFunction(key);
            int comparisons = 0;
            for (int item : table.get(index)) {
                comparisons++;
                if (item == key) {
                    System.out.println("Comparisons in Chaining: " + comparisons);
                    return true;
                }
            }
            return false;
        }
    }

    // Open Addressing: Linear Probing
    static class HashTableProbing {
        private int[] table;
        private boolean[] isOccupied;

        public HashTableProbing() {
            table = new int[10];  // Fixed size
            isOccupied = new boolean[10];
            Arrays.fill(table, -1); // -1 indicates an empty cell
        }

        // Insert key using linear probing
        public void insertItem(int key) {
            int index = hashFunction(key);
            int originalIndex = index;
            while (isOccupied[index]) {
                index = (index + 1) % 10; // Fixed size
                if (index == originalIndex) break; // Table is full
            }
            if (!isOccupied[index]) {
                table[index] = key;
                isOccupied[index] = true;
            }
        }

        // Function to search for a key and count comparisons
        public boolean searchItem(int key) {
            int index = hashFunction(key);
            int originalIndex = index;
            int comparisons = 0;
            while (isOccupied[index]) {
                comparisons++;
                if (table[index] == key) {
                    System.out.println("Comparisons in Linear Probing: " + comparisons);
                    return true;
                }
                index = (index + 1) % 10; // Fixed size
                if (index == originalIndex) break;  // Full cycle, not found
            }
            return false;
        }
    }

    // Function to generate random phone numbers based on user input
    public static void generateRandomPhoneNumbers(List<Integer> phoneNumbers, int n, int minRange, int maxRange) {
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            phoneNumbers.add(rand.nextInt((maxRange - minRange + 1)) + minRange); // Generate numbers within the range
        }
    }

    // Comparison test function
    public static void compareCollisionHandling(List<Integer> phoneNumbers) {
        HashTableChaining chainingTable = new HashTableChaining();
        HashTableProbing probingTable = new HashTableProbing();

        // Insert phone numbers into both tables
        for (int num : phoneNumbers) {
            chainingTable.insertItem(num);
            probingTable.insertItem(num);
        }

        // Search and compare
        int comparisonsChaining = 0, comparisonsProbing = 0;
        for (int num : phoneNumbers) {
            if (chainingTable.searchItem(num)) comparisonsChaining++;
            if (probingTable.searchItem(num)) comparisonsProbing++;
        }

        System.out.println("Total comparisons using Separate Chaining: " + comparisonsChaining);
        System.out.println("Total comparisons using Linear Probing: " + comparisonsProbing);
    }

    public static void main(String[] args) {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);

        // User input for number of phone numbers and range
        System.out.print("Enter the number of phone numbers to generate: ");
        int n = scanner.nextInt();

        System.out.print("Enter the minimum range for phone numbers: ");
        int minRange = scanner.nextInt();

        System.out.print("Enter the maximum range for phone numbers: ");
        int maxRange = scanner.nextInt();

        List<Integer> phoneNumbers = new ArrayList<>();
        generateRandomPhoneNumbers(phoneNumbers, n, minRange, maxRange);

        // Compare the two techniques
        compareCollisionHandling(phoneNumbers);
    }
}
