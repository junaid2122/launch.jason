import java.util.Scanner;
import java.util.Stack;

class TreeNode { // Renamed from Node to TreeNode to avoid conflict
    String data;
    TreeNode left, right;

    TreeNode(String datavalue) {
        data = datavalue;
        left = right = null;
    }
}

class ExpressionTree {
    int precedence(char c) {
        if (c == '+' || c == '-') return 1;
        if (c == '*' || c == '/') return 2;
        if (c == '^') return 3;
        return -1;
    }

    String infixToPostfix(String infix) {
        Stack<Character> st = new Stack<>();
        StringBuilder postfix = new StringBuilder();
        int n = infix.length();

        for (int i = 0; i < n; i++) {
            char c = infix.charAt(i);

            if (Character.isLetterOrDigit(c)) {
                postfix.append(c);
                while (i + 1 < n && Character.isLetterOrDigit(infix.charAt(i + 1))) {
                    postfix.append(infix.charAt(++i));
                }
                postfix.append(' ');
            } else if (c == '(') {
                st.push(c);
            } else if (c == ')') {
                while (!st.isEmpty() && st.peek() != '(') {
                    postfix.append(st.pop()).append(' ');
                }
                if (st.isEmpty()) {
                    throw new RuntimeException("Mismatched parentheses");
                }
                st.pop();
            } else if (c == '-') {
                if (i == 0 || (!Character.isLetterOrDigit(infix.charAt(i - 1)) && infix.charAt(i - 1) != ')')) {
                    postfix.append(c);
                    while (i + 1 < n && Character.isLetterOrDigit(infix.charAt(i + 1))) {
                        postfix.append(infix.charAt(++i));
                    }
                    postfix.append(' ');
                } else {
                    while (!st.isEmpty() && precedence(st.peek()) >= precedence(c) && st.peek() != '(') {
                        postfix.append(st.pop()).append(' ');
                    }
                    st.push(c);
                }
            } else {
                while (!st.isEmpty() && precedence(st.peek()) >= precedence(c) && st.peek() != '(') {
                    postfix.append(st.pop()).append(' ');
                }
                st.push(c);
            }
        }

        while (!st.isEmpty()) {
            if (st.peek() == '(') {
                throw new RuntimeException("Mismatched parentheses");
            }
            postfix.append(st.pop()).append(' ');
        }

        return postfix.toString();
    }

    TreeNode constructTree(String postfix) {
        Stack<TreeNode> st = new Stack<>();
        String[] tokens = postfix.split("\\s+");

        for (String token : tokens) {
            if (token.length() == 1 && !Character.isLetterOrDigit(token.charAt(0))) {
                if (st.size() < 2) {
                    throw new RuntimeException("Invalid postfix expression");
                }
                TreeNode t = new TreeNode(token);
                t.right = st.pop();
                t.left = st.pop();
                st.push(t);
            } else {
                st.push(new TreeNode(token));
            }
        }

        if (st.size() != 1) {
            throw new RuntimeException("Invalid postfix expression");
        }

        return st.pop();
    }

    void nonRecursiveInorder(TreeNode root) {
        Stack<TreeNode> s = new Stack<>();
        TreeNode curr = root;

        while (curr != null || !s.isEmpty()) {
            while (curr != null) {
                s.push(curr);
                curr = curr.left;
            }
            curr = s.pop();
            System.out.print(curr.data + " ");
            curr = curr.right;
        }
    }

   
    void nonRecursivePostorder(TreeNode root) {
        if (root == null) return;

        Stack<TreeNode> s1 = new Stack<>();
        Stack<TreeNode> s2 = new Stack<>();
        s1.push(root);

        while (!s1.isEmpty()) {
            TreeNode node = s1.pop();
            s2.push(node);

            if (node.left != null) s1.push(node.left);
            if (node.right != null) s1.push(node.right);
        }

        while (!s2.isEmpty()) {
            System.out.print(s2.pop().data + " ");
        }
    }
}

public class pr2a {
    public static void main(String[] args) {
        ExpressionTree et = new ExpressionTree();
        TreeNode root = null;
        Scanner scanner = new Scanner(System.in);
        String infix, postfix;
        int choice;

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Convert Infix to Postfix and Construct Expression Tree");
            System.out.println("2. Non-recursive Inorder Traversal");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter infix expression: ");
                    infix = scanner.nextLine();
                    try {
                        postfix = et.infixToPostfix(infix);
                        System.out.println("Postfix expression: " + postfix);
                        root = et.constructTree(postfix);
                        System.out.println("Expression tree constructed.");
                    } catch (RuntimeException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                case 2 -> {
                    if (root == null) {
                        System.out.println("Tree not constructed. Please construct the tree first.");
                    } else {
                        System.out.print("Non-recursive Inorder traversal: ");
                        et.nonRecursiveInorder(root);
                        System.out.println();
                    }
                }
             
                case 3
                 -> {
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
