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
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
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
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<>();
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

    @Test(expected = IllegalArgumentException.class)
    public void enqueueWithItemNull() {
        // given:
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();

        // when:
        randomizedQueue.enqueue(null);
    }


}
