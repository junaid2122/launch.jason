import java.util.Arrays;
import java.util.Scanner;

class Item {
    int weight;
    int value;
    double valuePerWeight;

    // Constructor
    public Item(int weight, int value) {
        this.weight = weight;
        this.value = value;
        this.valuePerWeight = (double) value / weight;
    }
}

public class FractionalKnapsack {
    // Function to calculate the maximum value
    public static double getMaxValue(Item[] items, int capacity) {
        // Sort items by value/weight ratio in descending order
        Arrays.sort(items, (a, b) -> Double.compare(b.valuePerWeight, a.valuePerWeight));

        double totalValue = 0.0;

        for (Item item : items) {
            if (capacity == 0) break; // Stop if the knapsack is full

            if (item.weight <= capacity) {
                // If the item can fit entirely, take it
                totalValue += item.value;
                capacity -= item.weight;
            } else {
                // If the item cannot fit entirely, take the fractional part
                totalValue += item.valuePerWeight * capacity;
                capacity = 0; // Knapsack is full
            }
        }

        return totalValue;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input number of items
        System.out.print("Enter the number of items: ");
        int n = scanner.nextInt();

        // Input weights and values of items
        Item[] items = new Item[n];
        System.out.println("Enter the weight and value of each item:");
        for (int i = 0; i < n; i++) {
            System.out.print("Item " + (i + 1) + " Weight: ");
            int weight = scanner.nextInt();
            System.out.print("Item " + (i + 1) + " Value: ");
            int value = scanner.nextInt();
            items[i] = new Item(weight, value);
        }

        // Input the capacity of the knapsack
        System.out.print("Enter the capacity of the knapsack: ");
        int capacity = scanner.nextInt();

        // Calculate and display the maximum value
        double maxValue = getMaxValue(items, capacity);
        System.out.println("Maximum total value in the knapsack: " + maxValue);

        scanner.close();
    }
}
