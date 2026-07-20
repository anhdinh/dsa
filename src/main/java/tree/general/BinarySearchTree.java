package tree.general;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BinarySearchTree {
    private Node root;

    public  BinarySearchTree() {
        root = null;
    }

    public BinarySearchTree(Node root) {
        this.root = root;
    }

    public void insert(int value) {
        Node newNode = new Node(value);
        if (root == null) {
            root = newNode;
            return;
        }
        var current = root;
        Node previous = null;
        while (current != null) {
            previous = current;
            if (current.getValue() > value) {
                current = current.getLeft();
            } else if (current.getValue() < value) {
                current = current.getRight();
            } else {
                throw new IllegalArgumentException("Giá trị " + value + " đã tồn tại trong cây");
            }
        }
        if (value < previous.getValue()) {
            previous.setLeft(newNode);
        } else {
            previous.setRight(newNode);
        }
    }
}