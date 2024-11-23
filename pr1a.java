import java.util.Scanner;

// Node structure for Binary Tree
class Node {
    int data;
    Node left, right;
    
    Node(int val) {
        data = val;
        left = right = null;
    }
}

// Binary Tree class
class BinaryTree {
    Node root;
    
    BinaryTree() {
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
    
    // Find depth of the tree
    int findDepth(Node node) {
        if (node == null) {
            return 0;
        }
        int leftDepth = findDepth(node.left);
        int rightDepth = findDepth(node.right);
        return Math.max(leftDepth, rightDepth) + 1;
    }
    
    // Display leaf nodes
    void displayLeafNodes(Node node) {
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null) {
            System.out.print(node.data + " ");
        }
        displayLeafNodes(node.left);
        displayLeafNodes(node.right);
    }
    
    // Create a copy of the tree
    Node copyTree(Node node) {
        if (node == null) {
            return null;
        }
        Node newNode = new Node(node.data);
        newNode.left = copyTree(node.left);
        newNode.right = copyTree(node.right);
        return newNode;
    }
}

public class pr1a {
    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree();
        BinaryTree copyBt = new BinaryTree();
        Scanner scanner = new Scanner(System.in);
        int choice, value;
        
        do {
            // Display menu options
            System.out.println("\nBinary Tree Operations Menu:");
            System.out.println("1. Insert a Node");
            System.out.println("2. In-Order Display");
            System.out.println("3. Find Depth of the Tree");
            System.out.println("4. Display Leaf Nodes");
            System.out.println("5. Create and Display Copy of the Tree");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    System.out.print("Enter value to insert: ");
                    value = scanner.nextInt();
                    bt.root = bt.insert(bt.root, value);
                    break;
                
                case 2:
                    System.out.print("In-Order Display: ");
                    bt.inOrder(bt.root);
                    System.out.println();
                    break;
                    
                case 3:
                    System.out.println("Depth of Tree: " + bt.findDepth(bt.root));
                    break;
                    
                case 4:
                    System.out.print("Leaf Nodes: ");
                    bt.displayLeafNodes(bt.root);
                    System.out.println();
                    break;
                    
                case 5:
                    copyBt.root = bt.copyTree(bt.root);
                    System.out.print("In-Order Display of Copied Tree: ");
                    bt.inOrder(copyBt.root);
                    System.out.println();
                    break;
                    
                case 6:
                    System.out.println("Exiting...");
                    break;
                    
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 6);
        
        scanner.close();
    }
}
