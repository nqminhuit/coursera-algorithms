import java.util.Iterator;

public class Stack<Item> implements Iterable<Item> {

    Node first;

    public void push(Item item) {
        Node newFirst = new Node();
        newFirst.item = item;
        newFirst.next = first;
        first = newFirst;
    }

    public Item pop() {
        Item item = first.item;
        first = first.next;
        return item;
    }

    public boolean isEmpty() {
        return first == null;
    }

    private class Node {
        Item item;
        Node next;
    }

    private class ListItorator implements Iterator<Item> {

        private Node current = first;

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

    @Override
    public Iterator<Item> iterator() {
        return new ListItorator();
    }

    public static void main(String[] args) {
        Stack<String> stackOfString = new Stack<>();

        for (int i = 0; i < 10; ++i) {
            stackOfString.push("item " + i);
        }

        System.out.println("New Stack:");
        stackOfString.forEach(string -> System.out.println(string));

        System.out.println("All popped out:");
        for (int i = 0; i < 10; ++i) {
            stackOfString.pop();
        }
        stackOfString.forEach(string -> System.out.println(string));

    }

}
