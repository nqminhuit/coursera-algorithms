import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class BruteCollinearPointsTest {

    @Test
    public void minh() {
        // given:
        int n = 5;
        Point[] points = new Point[n];
        BruteCollinearPoints bcp = new BruteCollinearPoints(points);

        // when:
        bcp.segments();
    }

}
