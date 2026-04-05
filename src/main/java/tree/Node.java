package tree;

public class Node {
    int value;
    Node left;
    Node right;
    Node parent; // THÊM: Tham chiếu ngược lại nút cha để dễ dàng "xoay" cây
    boolean color;

    public Node(int value) {
        this.value = value;
        left = null;
        right = null;
    }
}