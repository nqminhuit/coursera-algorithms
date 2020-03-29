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
        return queue.dequeue();
    }

    // remove and return the item from the back
    public Item removeLast() {
        preventOperationOnEmptyQueue();
        return stack.pop();
    }

    private class Node {
        Item item;

        Node next;

        public Node(Item item) {
            this.item = item;
        }
    }

    private class ListItorator implements Iterator<Item> {

        Node current = lastOfDeque;

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
        Node first;

        public Stack() {
            first = firstOfDeque;
        }

        public void push(Item item) {
            if (first == null) {
                first.item = item;
                first.next = lastOfDeque;
            } else {
                Node newFirst = new Node(null);
                newFirst.item = item;
                newFirst.next = first;
                first = newFirst;
                lastOfDeque = first;
            }
        }

        public Item pop() {
            Item item = firstOfDeque.item;
            firstOfDeque = firstOfDeque.next;
            return item;
        }
    }

    class Queue {

        // private Node first;

        private Node last;

        public Queue() {
            // first = firstOfDeque;
            last = firstOfDeque;
        }

        public void enqueue(Item item) {
            if (last == null) {
                last.item = item;
                firstOfDeque.next = last;
            } else {
                Node newLast = new Node(item);
                last.next = newLast;
                last = newLast;
                firstOfDeque = last;
            }
        }

        public Item dequeue() {
            Item item = lastOfDeque.item;
            lastOfDeque = lastOfDeque.next;
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<String> deque = new Deque<>();
        for (int i = 0; i < 3; ++i) {
            deque.addFirst("item " + i);
            deque.addLast("item " + i);
        }

        deque.forEach(System.out::println);

        // System.out.println("Remove first and last:");
        // for (int i = 0; i < 3; ++i) {
        //     deque.removeFirst();
        //     deque.removeLast();
        //     deque.forEach(System.out::println);
        // }
    }

}
