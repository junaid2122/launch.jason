import java.util.Scanner;

public class QuickSortTopStudents {

    // Function to partition the array
    private static int partition(int[] credits, String[] names, int low, int high) {
        int pivot = credits[high]; // Choose the last element as the pivot
        int i = low - 1; // Index of the smaller element

        for (int j = low; j < high; j++) {
            if (credits[j] > pivot) { // Sorting in descending order
                i++;
                // Swap credits
                int temp = credits[i];
                credits[i] = credits[j];
                credits[j] = temp;

                // Swap names to maintain association
                String tempName = names[i];
                names[i] = names[j];
                names[j] = tempName;
            }
        }

        // Swap pivot into the correct position
        int temp = credits[i + 1];
        credits[i + 1] = credits[high];
        credits[high] = temp;

        // Swap corresponding names
        String tempName = names[i + 1];
        names[i + 1] = names[high];
        names[high] = tempName;

        return i + 1;
    }

    // Function to perform Quick Sort
    private static void quickSort(int[] credits, String[] names, int low, int high) {
        if (low < high) {
            // Partition index
            int pi = partition(credits, names, low, high);

            // Recursively sort elements before and after partition
            quickSort(credits, names, low, pi - 1);
            quickSort(credits, names, pi + 1, high);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input the number of students
        System.out.print("Enter the number of students: ");
        int n = scanner.nextInt();
        scanner.nextLine(); // Consume the newline

        // Input the names and credits of students
        String[] names = new String[n];
        int[] credits = new int[n];

        System.out.println("Enter the names and credits obtained by each student:");
        for (int i = 0; i < n; i++) {
            System.out.print("Student " + (i + 1) + " Name: ");
            names[i] = scanner.nextLine();
            System.out.print("Credits: ");
            credits[i] = scanner.nextInt();
            scanner.nextLine(); // Consume the newline
        }

        // Sort the students by credits in descending order using Quick Sort
        quickSort(credits, names, 0, n - 1);

        // Display the names of the top 3 students
        System.out.println("\nTop 3 Students:");
        for (int i = 0; i < Math.min(3, n); i++) {
            System.out.println((i + 1) + ". " + names[i] + " with " + credits[i] + " credits.");
        }

        scanner.close();
    }
}
