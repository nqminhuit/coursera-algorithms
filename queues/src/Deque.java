import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A double-ended queue or deque (pronounced “deck”) is a generalization of a stack and a queue that
 * supports adding and removing items from either the front or the back of the data structure.
 */
public class Deque<Item> implements Iterable<Item> {

    private Node firstOfDeque;

    private Node lastOfDeque;

    private int size;

    private final MyStack stack;

    private final MyQueue queue;

    // construct an empty deque
    public Deque() {
        firstOfDeque = null;
        lastOfDeque = null;
        stack = new MyStack();
        queue = new MyQueue();
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    // return the number of items on the deque
    public int size() {
        return this.size;
    }

    private void validateItem(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("item can not be null");
        }
    }

    // add the item to the front
    public void addFirst(Item item) {
        validateItem(item);
        stack.push(item);
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        validateItem(item);
        queue.enqueue(item);
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Cannot perform operation on an empty Queue!");
        }
        size--;
        return queue.dequeue();
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Cannot perform operation on an empty Queue!");
        }
        size--;
        return stack.pop();
    }

    private class Node {

        Item item;

        Node next;

        Node prev;

        public Node(Item item) {
            this.item = item;
        }
    }

    private class ListItorator implements Iterator<Item> {

        Node current = firstOfDeque;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (isEmpty()) {
                throw new NoSuchElementException("Cannot perform operation on an empty Queue!");
            }
            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("This method is not supported!");
        }
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ListItorator();
    }

    private class MyStack {

        public void push(Item item) {
            if (isEmpty()) {
                firstOfDeque = new Node(item);
                lastOfDeque = firstOfDeque;
            } else {
                Node newNode = new Node(item);
                newNode.next = firstOfDeque;
                firstOfDeque.prev = newNode;
                firstOfDeque = newNode;
            }
        }

        public Item pop() {
            Node oldLast = lastOfDeque;
            Item item = oldLast.item;
            lastOfDeque = lastOfDeque.prev;
            if (lastOfDeque != null) { // not the final Node
                lastOfDeque.next = null;
            }
            oldLast.prev = null;
            return item;
        }
    }

    private class MyQueue {

        public void enqueue(Item item) {
            if (isEmpty()) {
                lastOfDeque = new Node(item);
                firstOfDeque = lastOfDeque;
            } else {
                Node newNode = new Node(item);
                lastOfDeque.next = newNode;
                newNode.prev = lastOfDeque;
                lastOfDeque = newNode;
            }
        }

        public Item dequeue() {
            Item item = firstOfDeque.item;
            firstOfDeque = firstOfDeque.next;
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<String> deque = new Deque<>();
        for (int i = 0; i < 4; ++i) {
            deque.addFirst("item " + i);
            deque.addLast("item " + i);
        }

        deque.forEach(System.out::print);

        System.out.println("Remove first and last:");
        for (int i = 0; i < 3; ++i) {
            deque.removeFirst();
            deque.removeLast();
            for (String item : deque) {
                System.out.println(item + " ");
            }
            System.out.println();
        }
        System.out.print("Remaining: ");
        deque.forEach(System.out::println);
    }

}
