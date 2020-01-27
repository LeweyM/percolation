import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

class PercolationTest {

    private Percolation percolation;

    @BeforeEach
    void setUp() {
        this.percolation = new Percolation(5);
    }

    @Test
    void shouldThrowErrorIfNLessThanOne() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Percolation(0));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Percolation(-1));
    }

    @Test
    void shouldOpenASquare() {
        assertFalse(percolation.isOpen(1,1));
        percolation.open(1,1);
        assertTrue(percolation.isOpen(1,1));
    }

    @Test
    void shouldOnlyBeFullIfOpen() {
        assertFalse(percolation.isFull(1,1));
        percolation.open(1,1);
        assertTrue(percolation.isFull(1,1));
    }

    @Test
    void topRowShouldBeFullWhenOpen() {
        percolation.open(1, 1);
        percolation.open(1, 2);
        percolation.open(1, 3);
        percolation.open(1, 4);
        assertTrue(percolation.isFull(1,1));
        assertTrue(percolation.isFull(1,2));
        assertTrue(percolation.isFull(1,3));
        assertTrue(percolation.isFull(1,4));

        assertFalse(percolation.isFull(1,5));
    }

    @Test
    void shouldBeFullIfConnectedToTopOfGrid() {
        percolation.open(1,1);
        percolation.open(3,1);
        assertFalse(percolation.isFull(3,1));

        percolation.open(2,1);
        assertTrue(percolation.isFull(3, 1));
    }

    @Test
    void shouldPercolateIfTopConnectedToBottom() {
        percolation.open(1,1);
        percolation.open(2,1);
        percolation.open(3,1);
        percolation.open(4,1);
        assertFalse(percolation.percolates());

        percolation.open(4,2);
        percolation.open(4,3);
        percolation.open(4,4);
        assertFalse(percolation.percolates());

        percolation.open(5,4);
        assertTrue(percolation.percolates());
    }

    @Test
    void shouldCountTheNumberOfOpenCells() {
        assertThat(percolation.numberOfOpenSites(), is(0));
        percolation.open(1,1);
        percolation.open(1,2);
        assertThat(percolation.numberOfOpenSites(), is(2));
        percolation.open(1,1);
        assertThat(percolation.numberOfOpenSites(), is(2));
    }

    @Test
    void methodsShouldThrowErrorIfOutsideRange() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> percolation.open(6, 1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> percolation.open(0, 1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> percolation.open(1, 0));
        Assertions.assertThrows(IllegalArgumentException.class, () -> percolation.open(1, 6));

        Assertions.assertThrows(IllegalArgumentException.class, () -> percolation.isFull(6, 1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> percolation.isFull(0, 1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> percolation.isFull(1, 0));
        Assertions.assertThrows(IllegalArgumentException.class, () -> percolation.isFull(1, 6));

        Assertions.assertThrows(IllegalArgumentException.class, () -> percolation.isOpen(6, 1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> percolation.isOpen(0, 1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> percolation.isOpen(1, 0));
        Assertions.assertThrows(IllegalArgumentException.class, () -> percolation.isOpen(1, 6));

        percolation.open(1, 5);
        percolation.open(5, 1);
        percolation.isOpen(1, 5);
        percolation.isOpen(5, 1);
        percolation.isFull(1, 5);
        percolation.isFull(5, 1);
    }
}