// import java.util.Scanner;
// class node{
//     int data;
//     Node left,right;
//     node(int val){
//         data=val;
//         left=right=null;
//     }
// }
// class binaryTree{
//     Node root;
//     void BinaryTree(){
//         root=null;
        
//     }
//     Node insert (Node node,int data){
//         if(node==null)
//         {
//             return new Node(data);
//         }
//         if(data<node.data)
//         {
//             node.left=insert(node.left,data);
//         }
//         else{
//             node.right=insert(node.right,data);
//         }
//         return node;
//     }
//     void inOrder (Node node)
//     {
//         if(node==null)
//         {
//             return ;
//         }
//         inOrder(node.left);
//         System.out.print(node.data +" " );
//         inOrder(node.right);
//     }

//     int findDepth(Node node )
//     {
//         if(node==null)
//         {
//             return 0 ;
//         }
//         int leftDepth=findDepth(node.left);
//         int rightDepth=findDepth(node.right);
//         return Math.max(leftDepth,rightDepth);
//     }

//     void displayLeafNodes(Node node){
//         if(node==null)
//         {
//             return ;
//         }
//         if(node.left==null && node.right==null){
//             System.out.print(node.data + " ");
//         }
//         displayLeafNodes(node.left);
//         displayLeafNodes(node.right);
//     }
//     Node copyTree(Node node ){
//         if(node==null)
//         {
//             return null;
//         }
//         Node newNode=new Node(node.data);
//         newNode.left=copyTree(node.left);
//         newNode.right=copyTree(node.right);
//         return newNode;
//     }
// }
// public class pr1aa{
//     public static void main (String args[])
//     {
//         BinaryTree bt=new BinaryTree();
//         BinaryTree copyTree=new BinaryTree();
//         Scanner scanner=new Scanner(System.in);
//         int choice ,value;

//         do{
//             System.out.println("1.Insert element ");
//             System.out.println("2.inorder ");
//             System.out.println("3.findDepth ");
//             System.out.println("4.Display leaf nodes ");
//             System.out.println("5.CopyTree ");
//             System.out.println("6.Exit ");
//             System.out.println("Enter you value");
//             choice=scanner.nextInt();

//             switch(choice){
//                 case 1:
//                 System.out.println("Enter element value");
//                 value=scanner.nextInt();
//                 bt.root=bt.insert(bt.root,value);
//                 break;

                  
//                 case 2:
//                     System.out.print("In-Order Display: ");
//                     bt.inOrder(bt.root);
//                     System.out.println();
//                     break;
                    
//                 case 3:
//                     System.out.println("Depth of Tree: " + bt.findDepth(bt.root));
//                     break;
                    
//                 case 4:
//                     System.out.print("Leaf Nodes: ");
//                     bt.displayLeafNodes(bt.root);
//                     System.out.println();
//                     break;
                    
//                 case 5:
//                     copyTree.root = bt.copyTree(bt.root);
//                     System.out.print("In-Order Display of Copied Tree: ");
//                     bt.inOrder(copyTree.root);
//                     System.out.println();
//                     break;
                    
//                 case 6:
//                     System.out.println("Exiting...");
//                     break;
                    
//                 default:
//                     System.out.println("Invalid choice. Please try again.");
//                     break;
//             }
//         } while (choice != 6);
        
//         scanner.close();
//     }
// }

import java.util.Scanner;

// Node structure for the Binary Tree
class Node1 {
    int data;
    Node left, right;

    Node1(int val) {
        data = val;
        left = right = null;  
    }
}

// Binary Tree class
class BinaryTree1 {
    Node root;

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

    void inOrder(Node node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.print(node.data + " ");
        inOrder(node.right);
    }

    int findDepth(Node node) {
        if (node == null) {
            return 0;
        }
        int leftDepth = findDepth(node.left);
        int rightDepth = findDepth(node.right);
        return Math.max(leftDepth, rightDepth) + 1;
    }

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

public class pr1aa {
    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree();
        BinaryTree copyTree = new BinaryTree();
        Scanner scanner = new Scanner(System.in);
        int choice, value;

        do {
            System.out.println("1. Insert element");
            System.out.println("2. In-order");
            System.out.println("3. Find Depth");
            System.out.println("4. Display Leaf Nodes");
            System.out.println("5. Copy Tree");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter element value: ");
                    value = scanner.nextInt();
                    bt.root = bt.insert(bt.root, value);
                }
                case 2 -> {
                    System.out.print("In-Order Display: ");
                    bt.inOrder(bt.root);
                    System.out.println();
                }
                case 3 -> System.out.println("Depth of Tree: " + bt.findDepth(bt.root));
                case 4 -> {
                    System.out.print("Leaf Nodes: ");
                    bt.displayLeafNodes(bt.root);
                    System.out.println();
                }
                case 5 -> {
                    copyTree.root = bt.copyTree(bt.root);
                    System.out.print("In-Order Display of Copied Tree: ");
                    bt.inOrder(copyTree.root);
                    System.out.println();
                }
                case 6 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 6);

        scanner.close();
    }
}
