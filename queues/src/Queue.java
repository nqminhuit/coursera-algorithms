import java.util.Iterator;

public class Queue<Item> implements Iterable<Item> {

    private Node first;

    private Node last;

    public void enqueue(Item item) {
        if (isEmpty()) {
            last = new Node(item);
            first = last;
        } else {
            Node newLast = new Node(item);
            last.next = newLast;
            last = newLast;
        }
    }

    public Item dequeue() {
        Item item = first.item;
        first = first.next;
        return item;
    }

    public boolean isEmpty() {
        return last == null;
    }

    private class Node {

        private Item item;
        private Node next;

        public Node(Item item) {
            this.item = item;
        }
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {

        Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }

    }

    public static void main(String[] args) {
        Queue<String> queue = new Queue<>();
        for (int i = 0; i < 10; ++i) {
            queue.enqueue("item " + i);
        }

        System.out.println("After initialization:");
        for (String item : queue) {
            System.out.println(item);
            queue.dequeue();
        }

        System.out.println("After dequeuing:");
        for (String item : queue) {
            System.out.println(item);
        }

    }

}
