import java.util.Arrays;

class Job {
    String id; // Job ID (e.g., A, B, C)
    int deadline; // Deadline for the job
    int profit; // Profit associated with the job

    public Job(String id, int deadline, int profit) {
        this.id = id;
        this.deadline = deadline;
        this.profit = profit;
    }
}

public class JobScheduling {

    // Function to schedule jobs to maximize profit
    public static void scheduleJobs(Job[] jobs) {
        // Step 1: Sort the jobs by profit in descending order
        Arrays.sort(jobs, (a, b) -> b.profit - a.profit);

        int n = jobs.length;

        // Step 2: Create a result array to store the job sequence and an array to track used time slots
        String[] result = new String[n];
        boolean[] timeSlot = new boolean[n]; // Tracks if a time slot is occupied

        // Step 3: Schedule jobs
        int totalProfit = 0;

        for (Job job : jobs) {
            // Try to schedule the job in the latest possible time slot before its deadline
            for (int j = Math.min(n, job.deadline) - 1; j >= 0; j--) {
                if (!timeSlot[j]) { // If the time slot is free
                    result[j] = job.id; // Schedule the job
                    timeSlot[j] = true; // Mark the time slot as occupied
                    totalProfit += job.profit; // Add the job's profit
                    break;
                }
            }
        }

        // Step 4: Display the results
        System.out.println("Scheduled Jobs:");
        for (int i = 0; i < n; i++) {
            if (timeSlot[i]) {
                System.out.println("Time Slot " + (i + 1) + ": Job " + result[i]);
            }
        }
        System.out.println("Total Profit: " + totalProfit);
    }

    public static void main(String[] args) {
        // Input: List of jobs with deadlines and profits
        Job[] jobs = {
            new Job("A", 2, 100),
            new Job("B", 1, 19),
            new Job("C", 2, 27),
            new Job("D", 1, 25),
            new Job("E", 3, 15)
        };

        // Schedule the jobs
        scheduleJobs(jobs);
    }
}
