import java.util.*;

public class pr6 {

    // Function to find max and min using heaps
    public static void findMaxAndMinMarks(int[] marks) {
        // Min-Heap to find the minimum mark
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        
        // Max-Heap to find the maximum mark
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        
        // Insert all the marks into both heaps
        for (int mark : marks) {
            minHeap.offer(mark);  // Adding mark to Min-Heap
            maxHeap.offer(mark);  // Adding mark to Max-Heap
        }

        // The root of Min-Heap is the minimum mark
        int minMark = minHeap.peek();
        
        // The root of Max-Heap is the maximum mark
        int maxMark = maxHeap.peek();
        
        System.out.println("Minimum mark: " + minMark);
        System.out.println("Maximum mark: " + maxMark);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Number of students
        System.out.print("Enter the number of students: ");
        int n = scanner.nextInt();

        // Marks array
        int[] marks = new int[n];

        System.out.println("Enter the marks obtained by students:");
        for (int i = 0; i < n; i++) {
            marks[i] = scanner.nextInt();
        }

        // Find and display max and min marks using heaps
        findMaxAndMinMarks(marks);

        scanner.close();
    }
}
