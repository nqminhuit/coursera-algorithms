import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.Iterator;
import org.junit.Test;

public class DequeTest {

    private static final Double DELTA = 0.000000001D;

    @Test
    public void dequeOfString_StartWithAddFirst() {
        // given:
        Deque<String> deque = new Deque<>();
        // when:
        deque.addFirst("a");
        deque.addLast("c");
        deque.addFirst("b");
        deque.addFirst("d");
        deque.addLast("e");
        deque.addLast("g");
        deque.addFirst("f");

        // then:
        Iterator<String> iterator = deque.iterator();
        assertEquals("f", iterator.next());
        assertEquals("d", iterator.next());
        assertEquals("b", iterator.next());
        assertEquals("a", iterator.next());
        assertEquals("c", iterator.next());
        assertEquals("e", iterator.next());
        assertEquals("g", iterator.next());
    }

    @Test
    public void dequeOfString_StartWithAddLast() {
        // given:
        Deque<String> deque = new Deque<>();
        // when:
        deque.addLast("a");
        deque.addLast("c");
        deque.addFirst("b");
        deque.addFirst("d");
        deque.addLast("e");
        deque.addLast("g");
        deque.addFirst("f");

        // then:
        Iterator<String> iterator = deque.iterator();
        assertEquals("f", iterator.next());
        assertEquals("d", iterator.next());
        assertEquals("b", iterator.next());
        assertEquals("a", iterator.next());
        assertEquals("c", iterator.next());
        assertEquals("e", iterator.next());
        assertEquals("g", iterator.next());
    }

    @Test
    public void dequeOfInt_OnlyAddFirst() {
        // given:
        Deque<Integer> deque = new Deque<>();

        // when:
        deque.addFirst(4);
        deque.addFirst(3);
        deque.addFirst(2);
        deque.addFirst(1);
        deque.addFirst(0);

        // then:
        Iterator<Integer> iterator = deque.iterator();
        assertEquals(0, iterator.next().intValue());
        assertEquals(1, iterator.next().intValue());
        assertEquals(2, iterator.next().intValue());
        assertEquals(3, iterator.next().intValue());
        assertEquals(4, iterator.next().intValue());
    }

    @Test
    public void dequeOfDouble_OnlyAddLeft() {
        // given:
        Deque<Double> deque = new Deque<>();

        // when:
        deque.addLast(8D);
        deque.addLast(3D);
        deque.addLast(0D);
        deque.addLast(5D);

        // then:
        Iterator<Double> iterator = deque.iterator();
        assertEquals(8D, iterator.next().doubleValue(), DELTA);
        assertEquals(3D, iterator.next().doubleValue(), DELTA);
        assertEquals(0D, iterator.next().doubleValue(), DELTA);
        assertEquals(5D, iterator.next().doubleValue(), DELTA);
    }

    @Test
    public void checkForEmptyDeque_WhenInitialize() {
        // given:
        Deque<Integer> deque = new Deque<>();

        // when:
        boolean actual = deque.isEmpty();

        // then:
        assertTrue(actual);
    }

    @Test
    public void checkForNotEmptyDeque_WhenAddFirst() {
        // given:
        Deque<Integer> deque = new Deque<>();

        // when:
        deque.addFirst(1);

        // then:
        assertFalse(deque.isEmpty());
    }

    @Test
    public void checkForNotEmptyDeque_WhenAddLast() {
        // given:
        Deque<Integer> deque = new Deque<>();

        // when:
        deque.addLast(3);

        // then:
        assertFalse(deque.isEmpty());
    }

    @Test
    public void sizeOfDeque_WhenAddFirst() {
        // given:
        Deque<Integer> deque = new Deque<>();

        // when:
        deque.addFirst(3);
        deque.addFirst(33);
        deque.addFirst(333);

        // then:
        assertEquals(3, deque.size());
    }

    @Test
    public void sizeOfDeque_WhenAddLast() {
        // given:
        Deque<Integer> deque = new Deque<>();

        // when:
        deque.addLast(3);
        deque.addLast(1);

        // then:
        assertEquals(2, deque.size());
    }

    @Test
    public void removeFirstItem() {
        // given:
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(3);
        deque.addLast(33);
        deque.addFirst(333);
        assertEquals(3, deque.size());

        // when:
        int removed = deque.removeFirst();

        // then:
        assertEquals(333, removed);
        assertEquals(2, deque.size());
        Iterator<Integer> iterator = deque.iterator();
        assertEquals(3, iterator.next().intValue());
        assertEquals(33, iterator.next().intValue());
    }


    @Test
    public void removeLastItem() {
        // given:
        Deque<Integer> deque = new Deque<>();
        deque.addLast(33);
        deque.addFirst(3);
        deque.addLast(333);
        assertEquals(3, deque.size());

        // when:
        int removed = deque.removeLast();

        // then:
        assertEquals(333, removed);
        assertEquals(2, deque.size());
        Iterator<Integer> iterator = deque.iterator();
        assertEquals(3, iterator.next().intValue());
        assertEquals(33, iterator.next().intValue());
    }


    @Test
    public void removeAllFirstItem() {
        // given:
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(3);
        deque.addLast(33);
        deque.addFirst(333);
        int originalSize = 3;
        assertEquals(3, originalSize);

        // when:
        for (int i = 0; i < originalSize; ++i) {
            deque.removeFirst();
        }

        // then:
        assertTrue(deque.isEmpty());
    }

    @Test
    public void removeAllLastItem() {
        // given:
        Deque<Integer> deque = new Deque<>();
        deque.addLast(33);
        deque.addFirst(3);
        deque.addLast(333);
        int originalSize = 3;
        assertEquals(3, originalSize);

        // when:
        for (int i = 0; i < originalSize; ++i) {
            deque.removeLast();
        }

        // then:
        assertTrue(deque.isEmpty());
    }

    @Test
    public void removeFirstAndLastItem() {
        // given:
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(1);
        deque.addLast(35);
        deque.addFirst(3);
        deque.addLast(2);
        deque.addLast(7);
        deque.addFirst(6);
        assertEquals(6, deque.size());

        // when:
        deque.removeFirst();
        deque.removeLast();
        deque.removeFirst();
        deque.removeFirst();
        deque.removeLast();

        // then:
        assertEquals(1, deque.size());
        Iterator<Integer> iterator = deque.iterator();
        assertEquals(35, iterator.next().intValue());
    }

    @Test
    public void checkSize_AddFirstAfterRemoveFirst() {
        // given:
        Deque<Integer> deque = new Deque<>();
        assertTrue(deque.isEmpty());

        deque.addFirst(1);

        // when:
        deque.removeFirst();
        deque.isEmpty();
        deque.isEmpty();
        deque.addFirst(5);
    }

}
