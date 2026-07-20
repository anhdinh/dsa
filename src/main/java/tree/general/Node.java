package tree.general;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Node {
    private int value;
    private Node right;
    private Node left;
    private int height;

    public Node(int value) {
        this.value = value;
        this.right = null;
        this.left = null;
        this.height = 1;
    }
}
