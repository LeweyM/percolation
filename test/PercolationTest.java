import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PercolationTest {


    @Test
    void shouldThrowErrorIfNLessThanOne() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Percolation(0));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Percolation(-1));
    }
}