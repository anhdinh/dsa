package com.dsa.generic;

import java.util.Arrays;
import java.util.Collection;
import java.util.EmptyStackException;
import java.util.List;


public class PETest {

    public static void main(String[] args) {
        Stack<Number> stack =  new Stack<>();
        List<Integer> stackInt = List.of(1,2,3,4);
        stack.pushAll(stackInt);

        PETest.putFavorite(String.class,"andy");


    }

    public static <T> void putFavorite(Class<T> type, T instance) {

    }
    static class Stack<E> {
        private E[] elements;
        private int size = 0;
        private static final int DEFAULT_INITIAL_CAPACITY = 16;

        @SuppressWarnings("unchecked")
        public Stack() {
            elements = (E[]) new Object[DEFAULT_INITIAL_CAPACITY];
        }

        public void push(E e) {
            ensureCapacity();
            elements[size++] = e;
        }

        public E pop() {
            if (size == 0) throw new EmptyStackException();
            E result = elements[--size];
            elements[size] = null;   // xóa tham chiếu thừa, tránh rò rỉ bộ nhớ
            return result;
        }

        public boolean isEmpty() { return size == 0; }

        private void ensureCapacity() {
            if (elements.length == size)
                elements = Arrays.copyOf(elements, 2 * size + 1);
        }

        // Producer → extends
        public void pushAll(Collection<? extends E> src) {
            for (E e : src) push(e);
        }

        // Consumer → super
        public void popAll(Collection<? super E> dst) {
            while (!isEmpty()) dst.add(pop());
        }
    }
}
