package tree.general;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AVLTreeTest {

    @Test
    public void testLLRotation() {
        AVLTree tree = new AVLTree();
        tree.insert(30);
        tree.insert(20);
        tree.insert(10);

        Node root = tree.getRoot();
        assertEquals(20, root.getValue());
        assertEquals(2, root.getHeight());

        Node left = root.getLeft();
        Node right = root.getRight();
        assertEquals(10, left.getValue());
        assertEquals(1, left.getHeight());
        assertEquals(30, right.getValue());
        assertEquals(1, right.getHeight());
    }

    @Test
    public void testRRRotation() {
        AVLTree tree = new AVLTree();
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);

        Node root = tree.getRoot();
        assertEquals(20, root.getValue());
        assertEquals(2, root.getHeight());

        Node left = root.getLeft();
        Node right = root.getRight();
        assertEquals(10, left.getValue());
        assertEquals(1, left.getHeight());
        assertEquals(30, right.getValue());
        assertEquals(1, right.getHeight());
    }

    @Test
    public void testLRRotation() {
        AVLTree tree = new AVLTree();
        tree.insert(30);
        tree.insert(10);
        tree.insert(20);

        Node root = tree.getRoot();
        assertEquals(20, root.getValue());
        assertEquals(2, root.getHeight());

        Node left = root.getLeft();
        Node right = root.getRight();
        assertEquals(10, left.getValue());
        assertEquals(1, left.getHeight());
        assertEquals(30, right.getValue());
        assertEquals(1, right.getHeight());
    }

    @Test
    public void testRLRotation() {
        AVLTree tree = new AVLTree();
        tree.insert(10);
        tree.insert(30);
        tree.insert(20);

        Node root = tree.getRoot();
        assertEquals(20, root.getValue());
        assertEquals(2, root.getHeight());

        Node left = root.getLeft();
        Node right = root.getRight();
        assertEquals(10, left.getValue());
        assertEquals(1, left.getHeight());
        assertEquals(30, right.getValue());
        assertEquals(1, right.getHeight());
    }

    @Test
    public void testInsertManyNodesStaysBalanced() {
        AVLTree tree = new AVLTree();
        tree.insert(50);
        tree.insert(30);
        tree.insert(70);
        tree.insert(20);
        tree.insert(40);
        tree.insert(60);
        tree.insert(80);
        tree.insert(10);
        tree.insert(25);
        tree.insert(35);
        tree.insert(45);
        tree.insert(55);
        tree.insert(65);
        tree.insert(75);
        tree.insert(90);
        tree.insert(5);
        tree.insert(15);
        tree.insert(85);
        tree.insert(95);

        Node root = tree.getRoot();
        int balance = Math.abs(root.getLeft().getHeight() - root.getRight().getHeight());
        assertTrue(balance <= 1, "Root balance factor must be within [-1, 1]");
        verifyBalance(tree.getRoot());
    }

    @Test
    public void testDuplicateThrowsException() {
        AVLTree tree = new AVLTree();
        tree.insert(10);
        tree.insert(20);
        assertThrows(IllegalArgumentException.class, () -> tree.insert(10));
    }

    @Test
    public void testRemoveLeaf() {
        AVLTree tree = new AVLTree();
        tree.insert(20);
        tree.insert(10);
        tree.insert(30);
        tree.remove(30);

        Node root = tree.getRoot();
        assertEquals(20, root.getValue());
        assertEquals(10, root.getLeft().getValue());
        assertNull(root.getRight());
        verifyBalance(root);
    }

    @Test
    public void testRemoveNodeWithOneChild() {
        AVLTree tree = new AVLTree();
        tree.insert(20);
        tree.insert(10);
        tree.insert(30);
        tree.insert(5);
        tree.remove(10);

        Node root = tree.getRoot();
        assertEquals(20, root.getValue());
        assertEquals(5, root.getLeft().getValue());
        assertEquals(30, root.getRight().getValue());
        verifyBalance(root);
    }

    @Test
    public void testRemoveNodeWithTwoChildren() {
        AVLTree tree = new AVLTree();
        tree.insert(20);
        tree.insert(10);
        tree.insert(30);
        tree.insert(25);
        tree.insert(35);
        tree.remove(30);

        Node root = tree.getRoot();
        assertEquals(20, root.getValue());
        assertEquals(10, root.getLeft().getValue());
        assertEquals(35, root.getRight().getValue());
        assertEquals(25, root.getRight().getLeft().getValue());
        verifyBalance(root);
    }

    @Test
    public void testRemoveCausesLLRotation() {
        AVLTree tree = new AVLTree();
        tree.insert(20);
        tree.insert(10);
        tree.insert(30);
        tree.insert(5);
        tree.remove(30);

        Node root = tree.getRoot();
        assertEquals(10, root.getValue());
        assertEquals(5, root.getLeft().getValue());
        assertEquals(20, root.getRight().getValue());
        verifyBalance(root);
    }

    @Test
    public void testRemoveCausesLRRotation() {
        AVLTree tree = new AVLTree();
        tree.insert(20);
        tree.insert(10);
        tree.insert(30);
        tree.insert(15);
        tree.remove(30);

        Node root = tree.getRoot();
        assertEquals(15, root.getValue());
        assertEquals(10, root.getLeft().getValue());
        assertEquals(20, root.getRight().getValue());
        verifyBalance(root);
    }

    @Test
    public void testRemoveCausesRRRotation() {
        AVLTree tree = new AVLTree();
        tree.insert(20);
        tree.insert(10);
        tree.insert(30);
        tree.insert(40);
        tree.remove(10);

        Node root = tree.getRoot();
        assertEquals(30, root.getValue());
        assertEquals(20, root.getLeft().getValue());
        assertEquals(40, root.getRight().getValue());
        verifyBalance(root);
    }

    @Test
    public void testRemoveCausesRLRotation() {
        AVLTree tree = new AVLTree();
        tree.insert(20);
        tree.insert(10);
        tree.insert(30);
        tree.insert(25);
        tree.remove(10);

        Node root = tree.getRoot();
        assertEquals(25, root.getValue());
        assertEquals(20, root.getLeft().getValue());
        assertEquals(30, root.getRight().getValue());
        verifyBalance(root);
    }

    @Test
    public void testRemoveNonExistent() {
        AVLTree tree = new AVLTree();
        tree.insert(10);
        tree.insert(20);
        tree.remove(99);

        Node root = tree.getRoot();
        assertEquals(10, root.getValue());
        assertEquals(20, root.getRight().getValue());
        verifyBalance(root);
    }

    @Test
    public void testRemoveAllNodes() {
        AVLTree tree = new AVLTree();
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        tree.remove(10);
        tree.remove(20);
        tree.remove(30);

        assertNull(tree.getRoot());
    }

    private void verifyBalance(Node node) {
        if (node == null) return;
        int leftHeight = node.getLeft() != null ? node.getLeft().getHeight() : 0;
        int rightHeight = node.getRight() != null ? node.getRight().getHeight() : 0;
        int balance = Math.abs(leftHeight - rightHeight);
        assertTrue(balance <= 1, "Node " + node.getValue() + " có balance = " + balance);
        verifyBalance(node.getLeft());
        verifyBalance(node.getRight());
    }
}
