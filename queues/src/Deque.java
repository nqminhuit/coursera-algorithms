import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first;

    private Node last;

    // construct an empty deque
    public Deque() {
        first = new Node(null);
        last = first;
        first.next = last;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return first.item == null && last.item == null;
    }

    // return the number of items on the deque
    public int size() {
        return 0;
    }

    private void validateItem(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("item can not be null");
        }
    }

    // add the item to the front
    public void addFirst(Item item) {
        validateItem(item);
        push(item);
    }

    // add the item to the back
    public void addLast(Item item) {
        validateItem(item);
        enqueue(item);
    }

    private void preventOperationOnEmptyQueue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Cannot perform operation on an empty Queue!");
        }
    }

    // remove and return the item from the front
    public Item removeFirst() {
        preventOperationOnEmptyQueue();
        return dequeue();
    }

    // remove and return the item from the back
    public Item removeLast() {
        preventOperationOnEmptyQueue();
        return pop();
    }

    private class Node {

        Item item;

        Node next;

        public Node(Item item) {
            this.item = item;
        }
    }

    private void initializeFirstItem(Item item) {
        last.item = item;
        first.item = item;
    }

    private void enqueue(Item item) {
        if (isEmpty()) {
            initializeFirstItem(item);
        } else {
            Node newLast = new Node(item);
            last.next = newLast;
            last = newLast;
        }
    }

    private Item dequeue() {
        Item item = first.item;
        first = first.next;
        return item;
    }

    private void push(Item item) {
        if (isEmpty()) {
            initializeFirstItem(item);
        } else {
            Node newFirst = new Node(item);
            newFirst.item = item;
            newFirst.next = first;
            first = newFirst;
        }
    }

    private Item pop() {
        Item item = last.item;
        last = last.next;
        return item;
    }

    private class ListItorator implements Iterator<Item> {

        Node current = first;

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

    // unit testing (required)
    public static void main(String[] args) {
        Deque<String> deque = new Deque<>();
        for (int i = 0; i < 3; ++i) {
            deque.addFirst("item " + i);
            deque.addLast("item " + i);
        }
        for (String item : deque) {
            System.out.println(item);
        }
    }

}
