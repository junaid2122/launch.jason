#include <iostream> 
#include <queue>
using namespace std;

// Node structure for Binary Search Tree
struct Node {
    int data;
    Node* left;
    Node* right;
    
    Node(int val) {
        data = val;
        left = right = NULL;
    }
};

// BST class
class BST {
public:
    Node* root;
    
    BST() {
        root = NULL;
    }
    
    // Insert function
    Node* insert(Node* node, int data) {
        if (node == NULL) {
            return new Node(data);
        }
        if (data < node->data) {
            node->left = insert(node->left, data);
        } else {
            node->right = insert(node->right, data);
        }
        return node;
    }
    
    // In-order Display function
    void inOrder(Node* node) {
        if (node == NULL) {
            return;
        }
        inOrder(node->left);
        cout << node->data << " ";
        inOrder(node->right);
    }
    
    // Search function
    bool search(Node* node, int key) {
        if (node == NULL) {
            return false;
        }
        if (node->data == key) {
            return true;
        }
        if (key < node->data) {
            return search(node->left, key);
        } else {
            return search(node->right, key);
        }
    }
    
    // Find minimum value node
    Node* findMin(Node* node) {
        while (node && node->left != NULL) {
            node = node->left;
        }
        return node;
    }
    
    // Delete function
    Node* deleteNode(Node* node, int key) {
        if (node == NULL) return node;
        
        if (key < node->data) {
            node->left = deleteNode(node->left, key);
        } else if (key > node->data) {
            node->right = deleteNode(node->right, key);
        } else {
            if (node->left == NULL) {
                Node* temp = node->right;
                delete node;
                return temp;
            } else if (node->right == NULL) {
                Node* temp = node->left;
                delete node;
                return temp;
            }
            
            Node* temp = findMin(node->right);
            node->data = temp->data;
            node->right = deleteNode(node->right, temp->data);
        }
        return node;
    }
    
    // BFS Level-order print
    void bfs(Node* node) {
        if (node == NULL) return;
        queue<Node*> q;
        q.push(node);
        
        while (!q.empty()) {
            Node* current = q.front();
            cout << current->data << " ";
            q.pop();
            
            if (current->left != NULL) {
                q.push(current->left);
            }
            if (current->right != NULL) {
                q.push(current->right);
            }
        }
    }
};

int main() {
    BST bst;
    int choice, value;

    do {
        cout << "\nBinary Search Tree Operations Menu:\n";
        cout << "1. Insert a Node\n";
        cout << "2. In-Order Display\n";
        cout << "3. BFS (Level-order) Display\n";
        cout << "4. Search for a Node\n";
        cout << "5. Delete a Node\n";
        cout << "6. Exit\n";
        cout << "Enter your choice: ";
        cin >> choice;

        switch (choice) {
            case 1:
                cout << "Enter value to insert: ";
                cin >> value;
                bst.root = bst.insert(bst.root, value);
                break;
                
            case 2:
                cout << "In-Order Display: ";
                bst.inOrder(bst.root);
                cout << endl;
                break;
                
            case 3:
                cout << "BFS (Level-order) Display: ";
                bst.bfs(bst.root);
                cout << endl;
                break;
                
            case 4:
                cout << "Enter value to search: ";
                cin >> value;
                cout << (bst.search(bst.root, value) ? "Found" : "Not Found") << endl;
                break;
                
            case 5:
                cout << "Enter value to delete: ";
                cin >> value;
                bst.root = bst.deleteNode(bst.root, value);
                cout << "In-Order Display after Deletion: ";
                bst.inOrder(bst.root);
                cout << endl;
                break;
                
            case 6:
                cout << "Exiting...\n";
                break;
                
            default:
                cout << "Invalid choice. Please try again.\n";
        }
    } while (choice != 6);

    return 0;
}

