import static org.junit.Assert.assertEquals;
import java.util.Iterator;
import org.junit.Test;

public class DequeTest {

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
}
