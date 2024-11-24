import java.util.*;

class User {
    String name;
    String dob;  
    int comments;

    public User(String name, String dob, int comments) {
        this.name = name;
        this.dob = dob;
        this.comments = comments;
    }

    public String getMonthOfBirth() {
        return dob.substring(5, 7);
    }

    @Override
    public String toString() {
        return name;
    }
}

class FriendNetwork {
    private Map<User, List<User>> adjacencyList;

    public FriendNetwork() {
        adjacencyList = new HashMap<>();
    }

    public void addUser(User user) {
        adjacencyList.putIfAbsent(user, new ArrayList<>());
    }

    public void addFriendship(User user1, User user2) {
        adjacencyList.get(user1).add(user2);
        adjacencyList.get(user2).add(user1);  
    }

    public User findUserWithMaxFriends() {
        User maxUser = null;
        int maxFriends = 0;
        for (User user : adjacencyList.keySet()) {
            int friendsCount = adjacencyList.get(user).size();
            if (friendsCount > maxFriends) {
                maxFriends = friendsCount;
                maxUser = user;
            }
        }
        return maxUser;
    }

    public void findUsersWithMaxMinComments() {
        if (adjacencyList.isEmpty()) return;

        User maxCommentUser = null, minCommentUser = null;
        int maxComments = Integer.MIN_VALUE, minComments = Integer.MAX_VALUE;

        for (User user : adjacencyList.keySet()) {
            if (user.comments > maxComments) {
                maxComments = user.comments;
                maxCommentUser = user;
            }
            if (user.comments < minComments) {
                minComments = user.comments;
                minCommentUser = user;
            }
        }

        System.out.println("User with maximum comments: " + maxCommentUser.name + " (" + maxComments + " comments)");
        System.out.println("User with minimum comments: " + minCommentUser.name + " (" + minComments + " comments)");
    }

    public void findUsersWithBirthdayInMonth(String currentMonth) {
        System.out.println("Users with birthdays in month " + currentMonth + ":");
        for (User user : adjacencyList.keySet()) {
            if (user.getMonthOfBirth().equals(currentMonth)) {
                System.out.println(user.name);
            }
        }
    }

   

    public User getUserByName(String name) {
        for (User user : adjacencyList.keySet()) {
            if (user.name.equals(name)) {
                return user;
            }
        }
        return null;
    }
}

public class pr3a {
    public static void main(String[] args) {
        FriendNetwork network = new FriendNetwork();
        Scanner scanner = new Scanner(System.in);
        Map<String, User> users = new HashMap<>();

        int choice;
        do {
            System.out.println("\n--- Social Network Menu ---");
            System.out.println("1. Add User");
            System.out.println("2. Add Friendship");
            System.out.println("3. Display User with Maximum Friends");
            System.out.println("4. Display User with Maximum and Minimum Comments");
            System.out.println("5. Display Users with Birthday in Current Month");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1: // Add User
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter date of birth (YYYY-MM-DD): ");
                    String dob = scanner.nextLine();
                    System.out.print("Enter number of comments: ");
                    int comments = scanner.nextInt();
                    User newUser = new User(name, dob, comments);
                    users.put(name, newUser);
                    network.addUser(newUser);
                    System.out.println("User " + name + " added.");
                    break;

                case 2: // Add Friendship
                    System.out.print("Enter first user's name: ");
                    String user1Name = scanner.nextLine();
                    System.out.print("Enter second user's name: ");
                    String user2Name = scanner.nextLine();
                    User user1 = users.get(user1Name);
                    User user2 = users.get(user2Name);
                    if (user1 != null && user2 != null) {
                        network.addFriendship(user1, user2);
                        System.out.println("Friendship added between " + user1Name + " and " + user2Name);
                    } else {
                        System.out.println("One or both users not found.");
                    }
                    break;

                case 3: // Maximum Friends
                    User maxFriendUser = network.findUserWithMaxFriends();
                    if (maxFriendUser != null) {
                        System.out.println("User with maximum friends: " + maxFriendUser.name);
                    } else {
                        System.out.println("No users found.");
                    }
                    break;

                case 4: // Minimum Comments
                    network.findUsersWithMaxMinComments();
                    break;

                case 5: // Current Month
                    System.out.print("Enter the current month (MM): ");
                    String currentMonth = scanner.nextLine();
                    network.findUsersWithBirthdayInMonth(currentMonth);
                    break;

                case 6: // Exit
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }

        } while (choice != 8);

        scanner.close();
    }}