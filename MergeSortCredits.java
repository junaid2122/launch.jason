import java.util.Scanner;

public class MergeSortCredits {

    // Function to merge two halves of the array
    private static void merge(int[] credits, int left, int mid, int right) {
        int n1 = mid - left + 1; // Size of left half
        int n2 = right - mid;   // Size of right half

        // Temporary arrays to hold left and right halves
        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        // Copy data to temporary arrays
        for (int i = 0; i < n1; i++) {
            leftArray[i] = credits[left + i];
        }
        for (int i = 0; i < n2; i++) {
            rightArray[i] = credits[mid + 1 + i];
        }

        // Merge the temporary arrays back into credits[]
        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                credits[k] = leftArray[i];
                i++;
            } else {
                credits[k] = rightArray[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements of leftArray[]
        while (i < n1) {
            credits[k] = leftArray[i];
            i++;
            k++;
        }

        // Copy remaining elements of rightArray[]
        while (j < n2) {
            credits[k] = rightArray[j];
            j++;
            k++;
        }
    }

    // Function to sort the array using Merge Sort
    private static void mergeSort(int[] credits, int left, int right) {
        if (left < right) {
            // Find the middle point
            int mid = left + (right - left) / 2;

            // Recursively sort first and second halves
            mergeSort(credits, left, mid);
            mergeSort(credits, mid + 1, right);

            // Merge the sorted halves
            merge(credits, left, mid, right);
        }
    }

    // Main function to test the program
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input the number of students
        System.out.print("Enter the number of students: ");
        int n = scanner.nextInt();

        // Input the credits obtained by students
        int[] credits = new int[n];
        System.out.println("Enter the credits obtained by each student:");
        for (int i = 0; i < n; i++) {
            credits[i] = scanner.nextInt();
        }

        // Sort the credits using Merge Sort
        mergeSort(credits, 0, n - 1);

        // Print the sorted credits
        System.out.println("Sorted credits:");
        for (int credit : credits) {
            System.out.print(credit + " ");
        }
        scanner.close();
    }
}
