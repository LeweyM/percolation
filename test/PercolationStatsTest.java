import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.util.Arrays.asList;

class PercolationStatsTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void shouldThrowIfTrailsOrNIsZeroOrLower() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new PercolationStats(0, 5));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new PercolationStats(-1, 5));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new PercolationStats(1, 0));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new PercolationStats(1, -1));
    }

    @Test
    void shouldRunFromMain() {
        String[] args = {"2", "100000"};
        PercolationStats.main(args);
    }
}