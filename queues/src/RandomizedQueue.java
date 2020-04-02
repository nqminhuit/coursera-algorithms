import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

/**
 * A randomized queue is similar to a stack or queue, except that the item removed is chosen
 * uniformly at random among items in the data structure.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] items;

    private int size;

    // construct an empty randomized queue
    public RandomizedQueue() {
        this.size = 0;
        items = (Item[]) new Object[1];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return this.size;
    }

    private void validateItem(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
    }

    // add the item
    public void enqueue(Item item) {
        validateItem(item);
        int itemLength = items.length;
        if (size == itemLength) {
            resizeArray(itemLength * 2);
        }
        shuffleItems();
        items[size++] = item;
    }

    private void resizeArray(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];

        for (int i = 0; i < size; ++i) {
            copy[i] = items[i];
        }

        items = copy;
    }

    private void validateEmptyQueue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
    }

    private void shuffleItems() {
        Item[] shuffled = (Item[]) new Object[size];
        for (int i = 0; i < size; ++i) {
            shuffled[i] = items[i];
        }
        StdRandom.shuffle(shuffled);
        for (int i = 0; i < size; ++i) {
            items[i] = shuffled[i];
        }
    }

    // remove and return a random item
    public Item dequeue() {
        validateEmptyQueue();
        shuffleItems();
        int itemLength = items.length;
        if (size <= itemLength / 4) {
            resizeArray(itemLength / 2);
        }
        Item item = items[0];
        size--;
        items[0] = items[size];
        items[size] = null;
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        validateEmptyQueue();
        return items[StdRandom.uniform(size)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {

        int current = 0;

        @Override
        public boolean hasNext() {
            return current < size && items[current] != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            return items[current++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
        for (int i = 0; i < 10; ++i) {
            randomizedQueue.enqueue("" + i);
        }
        System.out.println(randomizedQueue.sample());
    }
}
