import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.junit.Test;

public class RandomizedQueueTest {

    @Test
    public void enqueueString() {
        // given:
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>(1);
        String item = "itemX";

        // when:
        randomizedQueue.enqueue(item);

        // then:
        assertEquals(1, randomizedQueue.size());
        assertEquals("itemX", randomizedQueue.iterator().next());
    }

    @Test
    public void enqueueToExistingQueue() {
        // given:
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<>(1);
        assertEquals(0, randomizedQueue.size());

        randomizedQueue.enqueue(1234);
        assertEquals(1, randomizedQueue.size());

        Iterator<Integer> iterator = randomizedQueue.iterator();
        assertEquals(1234, iterator.next().intValue());

        // when:
        randomizedQueue.enqueue(5678);

        // then:
        assertEquals(2, randomizedQueue.size());

        iterator = randomizedQueue.iterator();
        List<Integer> ints = new ArrayList<>();
        ints.add(iterator.next());
        ints.add(iterator.next());
        assertTrue(ints.contains(1234) && ints.contains(5678));
    }

    @Test
    public void ensuredUniformlyRandomOrderIterator() {
        // when:
        for (int i = 0; i < 100; ++i) {
            enqueueToExistingQueue();
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void enqueueWithItemNull() {
        // given:
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>(0);

        // when:
        randomizedQueue.enqueue(null);
    }

    @Test
    public void dequeue() {
        // given:
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>(1);
        randomizedQueue.enqueue("item 1");
        randomizedQueue.enqueue("item 2");
        randomizedQueue.enqueue("item 3");
        randomizedQueue.enqueue("item 4");

        // when:
        randomizedQueue.dequeue();

        // then:
        assertEquals(3, randomizedQueue.size());
    }

    @Test
    public void enqueueShouldIncreaseArraySize() {
        // given:
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<>(1);

        // when:
        for (int i = 0; i < 100; ++i) {
            randomizedQueue.enqueue(i);
        }
    }

    @Test
    public void dequeueShouldDecreaseArraySize() {
        // given:
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<>(1);
        for (int i = 0; i < 100; ++i) {
            randomizedQueue.enqueue(i);
        }

        // when:
        for (int i = 0; i < 100; ++i) {
            randomizedQueue.dequeue();
        }
    }

    @Test
    public void sample() {
        // given:
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<>(1);
        for (int i = 0; i < 10; ++i) {
            randomizedQueue.enqueue(i);
        }

        // when:
        randomizedQueue.sample();

    }

}
