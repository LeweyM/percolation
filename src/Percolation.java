import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final int BOTTOM;
    private int TOP;
    private final int n;
    private boolean[][] openCells;
    private final WeightedQuickUnionUF uf;
    private int openCellCounter = 0;

    // creates n-by-n openCells, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException();

        this.n = n;
        openCells = new boolean[n][n];
        TOP = (n * n);
        BOTTOM = (n * n) + 1;
        uf = new WeightedQuickUnionUF((n * n) + 2);
        unionTopToTopRow();
        unionBottomToBottomRow();
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validateInput(row, col);
        setOpen(row, col);
        unionWithNeighbours(row, col);
    }

    // is the site (row, col) open?

    public boolean isOpen(int row, int col) {
        validateInput(row, col);
        return getOpen(row, col);
    }
    // is the site (row, col) full?

    public boolean isFull(int row, int col) {
        validateInput(row, col);
        return getOpen(row, col) && isConnectedToTopRow(row, col);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openCellCounter;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.find(TOP) == uf.find(BOTTOM);
    }

    private void unionWithNeighbours(int row, int col) {
        unionIfPossible(row, col, row + 1, col);
        unionIfPossible(row, col, row - 1, col);
        unionIfPossible(row, col, row, col + 1);
        unionIfPossible(row, col, row, col - 1);
    }

    private void unionIfPossible(int row1, int col1, int row2, int col2) {
        if (withinGrid(row2, col2) && isOpen(row2, col2)) {
            uf.union(rowColumnToIndex(row1, col1), rowColumnToIndex(row2, col2));
        }
    }

    private boolean withinGrid(int row, int col) {
        return row > 0 && row <= n && col > 0 && col <= n;
    }

    private boolean isConnectedToTopRow(int row, int col) {
        int rootIndex = uf.find(rowColumnToIndex(row, col));
        return rootIndex == TOP;
    }

    private void setOpen(int row, int col) {
        if (!openCells[row - 1][col - 1]) openCellCounter++;
        openCells[row - 1][col - 1] = true;
    }

    private boolean getOpen(int row, int col) {
        return openCells[row - 1][col - 1];
    }

    private void unionTopToTopRow() {
        for (int i = 1; i <= n; i++) {
            uf.union(TOP, rowColumnToIndex(1, i));
        }
    }

    private void unionBottomToBottomRow() {
        for (int i = 1; i <= n; i++) {
            uf.union(BOTTOM, rowColumnToIndex(n, i));
        }
    }

    private int rowColumnToIndex(int row, int col) {
        return (row - 1) * n + (col - 1);
    }

    private void validateInput(int row, int col) {
        if (row < 1) throw new IllegalArgumentException();
        if (col < 1) throw new IllegalArgumentException();
        if (row > n) throw new IllegalArgumentException();
        if (col > n) throw new IllegalArgumentException();
    }
}