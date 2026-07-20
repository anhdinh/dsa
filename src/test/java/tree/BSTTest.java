package tree;

import org.junit.jupiter.api.Test;
import tree.general.BinarySearchTree;

public class BSTTest {

    @Test
    public void testInsert(){
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(10);
        bst.insert(20);
        bst.insert(30);
        bst.insert(40);
        bst.insert(50);
        bst.insert(60);
        System.out.println("Done");
    }
}
