import java.util.*;

// Node structure for Binary Search Tree
class Node {
    int data;
    Node left, right;

    Node(int val) {
        data = val;
        left = right = null;
    }
}

// BST class
class BST {
    Node root;

    BST() {
        root = null;
    }

    // Insert function
    Node insert(Node node, int data) {
        if (node == null) {
            return new Node(data);
        }
        if (data < node.data) {
            node.left = insert(node.left, data);
        } else {
            node.right = insert(node.right, data);
        }
        return node;
    }

    // In-order Display function
    void inOrder(Node node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.print(node.data + " ");
        inOrder(node.right);
    }

    // Search function
    boolean search(Node node, int key) {
        if (node == null) {
            return false;
        }
        if (node.data == key) {
            return true;
        }
        if (key < node.data) {
            return search(node.left, key);
        } else {
            return search(node.right, key);
        }
    }

    // Find minimum value node
    Node findMin(Node node) {
        while (node != null && node.left != null) {
            node = node.left;
        }
        return node;
    }

    // Delete function
    Node deleteNode(Node node, int key) {
        if (node == null) return node;

        if (key < node.data) {
            node.left = deleteNode(node.left, key);
        } else if (key > node.data) {
            node.right = deleteNode(node.right, key);
        } else {
            if (node.left == null) {
                Node temp = node.right;
                node = null;
                return temp;
            } else if (node.right == null) {
                Node temp = node.left;
                node = null;
                return temp;
            }

            Node temp = findMin(node.right);
            node.data = temp.data;
            node.right = deleteNode(node.right, temp.data);
        }
        return node;
    }

    // BFS Level-order print
    void bfs(Node node) {
        if (node == null) return;
        Queue<Node> q = new LinkedList<>();
        q.add(node);

        while (!q.isEmpty()) {
            Node current = q.poll();
            System.out.print(current.data + " ");

            if (current.left != null) {
                q.add(current.left);
            }
            if (current.right != null) {
                q.add(current.right);
            }
        }
    }
}

public class pr1b
 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BST bst = new BST();
        int choice, value;

        do {
            System.out.println("\nBinary Search Tree Operations Menu:");
            System.out.println("1. Insert a Node");
            System.out.println("2. In-Order Display");
            System.out.println("3. BFS (Level-order) Display");
            System.out.println("4. Search for a Node");
            System.out.println("5. Delete a Node");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter value to insert: ");
                    value = scanner.nextInt();
                    bst.root = bst.insert(bst.root, value);
                    break;

                case 2:
                    System.out.print("In-Order Display: ");
                    bst.inOrder(bst.root);
                    System.out.println();
                    break;

                case 3:
                    System.out.print("BFS (Level-order) Display: ");
                    bst.bfs(bst.root);
                    System.out.println();
                    break;

                case 4:
                    System.out.print("Enter value to search: ");
                    value = scanner.nextInt();
                    System.out.println(bst.search(bst.root, value) ? "Found" : "Not Found");
                    break;

                case 5:
                    System.out.print("Enter value to delete: ");
                    value = scanner.nextInt();
                    bst.root = bst.deleteNode(bst.root, value);
                    System.out.print("In-Order Display after Deletion: ");
                    bst.inOrder(bst.root);
                    System.out.println();
                    break;

                case 6:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 6);

        scanner.close();
    }
}
