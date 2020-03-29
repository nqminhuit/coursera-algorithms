import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    // construct an empty deque
    public Deque() {
    }

    // is the deque empty?
    public boolean isEmpty() {
        return false;
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
    }

    // add the item to the back
    public void addLast(Item item) {
        validateItem(item);
    }

    private void preventOperationOnEmptyQueue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Cannot perform operation on an empty Queue!");
        }
    }

    // remove and return the item from the front
    public Item removeFirst() {
        preventOperationOnEmptyQueue();
        return null;
    }

    // remove and return the item from the back
    public Item removeLast() {
        preventOperationOnEmptyQueue();
        return null;
    }

    private class ListItorator<Item> implements Iterator<Item> {

        @Override
        public boolean hasNext() {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public Item next() {
            preventOperationOnEmptyQueue();
            return null;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("This method is not supported!");
        }

    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return null;
    }

    // unit testing (required)
    public static void main(String[] args) {
    }

}
