package tree;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

class BinaryTreeTest {

    @ParameterizedTest
    @MethodSource("provideInsertData")
    void testInsertNoRecursion(int[] inputValues) {
        BinaryTree tree = new BinaryTree();
        BinaryTree tree1 =  new BinaryTree(5);

        for (int val : inputValues) {
            assertDoesNotThrow(() -> tree.insertNoRecursion(val),
                    "Lỗi khi chèn giá trị: " + val);
            assertDoesNotThrow(() -> tree1.insert(val),
                    "Lỗi khi chèn giá trị: " + val);
        }
    }

    @Test
    void normalTest(){
        BinaryTree tree1 =  new BinaryTree(5);
        tree1.insert(3);
        tree1.insert(7);
        tree1.insert(2);
        tree1.insert(4);
        tree1.insert(6);
    }

    @Test
    void testTraverseInOrder(){
        BinaryTree tree1 =  new BinaryTree(5);
        tree1.insert(3);
        tree1.insert(7);
        tree1.insert(2);
        tree1.insert(4);
        tree1.insert(6);
        tree1.traverseInOrder(tree1.getRoot());
    }


    @Test
    void testTraversePreOrder(){
        BinaryTree tree1 =  new BinaryTree(5);
        tree1.insert(3);
        tree1.insert(7);
        tree1.insert(2);
        tree1.insert(4);
        tree1.insert(6);
        tree1.traversePreOrder(tree1.getRoot());
        System.out.println("clone tree and show it");

        BinaryTree cloneTree = tree1.cloneTree();
        cloneTree.traverseInOrder(cloneTree.getRoot());
        System.out.println("Copy Done");
    }



    static Stream<Arguments> provideInsertData() {
        return Stream.of(
                Arguments.of((Object) new int[]{5, 3, 7,8, 2, 4}),
                Arguments.of((Object) new int[]{2, 3,3,3,3,3}),
                Arguments.of((Object) new int[]{10, 20, 30}),
                Arguments.of((Object) new int[]{100})
        );
    }
}