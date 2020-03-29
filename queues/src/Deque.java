import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node firstOfDeque;

    private Node lastOfDeque;

    private int size;

    private Stack stack;

    private Queue queue;

    // construct an empty deque
    public Deque() {
        firstOfDeque = new Node(null);
        lastOfDeque = firstOfDeque;
        firstOfDeque.next = lastOfDeque;
        stack = new Stack();
        queue = new Queue();
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

    private void preventOperationOnEmptyQueue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Cannot perform operation on an empty Queue!");
        }
    }

    // remove and return the item from the front
    public Item removeFirst() {
        preventOperationOnEmptyQueue();
        size--;
        return queue.dequeue();
    }

    // remove and return the item from the back
    public Item removeLast() {
        preventOperationOnEmptyQueue();
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
            preventOperationOnEmptyQueue();
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

    private class Stack {

        public void push(Item item) {
            if (firstOfDeque.item == null) {
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
            Item item = lastOfDeque.item;
            lastOfDeque.next = null;
            lastOfDeque = lastOfDeque.prev;
            return item;
        }
    }

    class Queue {

        public void enqueue(Item item) {
            if (lastOfDeque.item == null) {
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
