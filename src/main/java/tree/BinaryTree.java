package tree;

public class BinaryTree {
    Node root;

    public BinaryTree(){
        root = null;
    }

    public BinaryTree(int value) {
        root = new Node(value);
    }

    public void insert(int value) {
        insert(root, value);
    }

    private void insert(Node current, int value) {
        Node newNode = new Node(value);
        if (current.value > value) {
            if (current.left == null) {
                current.left = newNode;
            } else {
                insert(current.left, value);
            }
        } else {
            if (current.right == null) {
                current.right = newNode;
            } else {
                insert(current.right, value);
            }
        }
    }

    public Node getRoot() {
        return root;
    }

    public void traverseInOrder(Node node){
        if(node!=null){
            traverseInOrder(node.left);
            System.out.println(node.value + " ");
            traverseInOrder(node.right);
        }
    }

    public void insertNoRecursion(int value) {
        Node newNode = new Node(value);
        if (root == null) {
            root = newNode;
            return;
        }

        // tim ra dau la parent de chuan bi insert node
        Node current = root;
        Node parent = null;
        while (current != null) {
            parent = current;
            if (value < current.value) {
                current = current.left;
            } else if (value > current.value) {
                current = current.right;
            }else{
                return;
            }
        }

        // kiem tra xem no o phia nao cua parent

        if (value < parent.value) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
    }



}
