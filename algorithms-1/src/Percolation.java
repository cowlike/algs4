public class Percolation {
    private enum State {
        CLOSED, OPEN
    };

    private WeightedQuickUnionUF uf;
    private State[] state;
    private final int size;
    private final int szGrid;

    /*
     * create N-by-N grid, with all sites blocked
     */
    public Percolation(final int N) {
        if (N < 2) {
            throw new IllegalArgumentException("grid must be at least 2 x 2");
        }
        size = N;
        szGrid = size * size;

        uf = new WeightedQuickUnionUF(szGrid + 2);
        state = new State[szGrid + 2];

        for (int i = 0; i < szGrid; i++) {
            state[i] = State.CLOSED;
        }

        /*
         * state[szGrid] is the virtual top point state[szGrid + 1] is the
         * virtual bottom point
         */
        state[szGrid] = State.OPEN;
        state[szGrid + 1] = State.OPEN;
    }

    private int index(int j, int i) {
        if (i < 1 || i > size || j < 1 || j > size) {
            throw new IndexOutOfBoundsException(
                    "arguments are not within the grid range");
        }
        return (i - 1) + ((j - 1) * size);
    }

    /*
     * open site (row i, column j) if it is not already
     */
    public void open(int j, int i) {
        final int index = index(i, j);
        state[index] = State.OPEN;
        int n;

        if (j == 1) {
            // first connect to top virtual node
            uf.union(index, szGrid);
            n = index(i, j + 1);
            if (isOpen(n)) {
                uf.union(index, n);
            }
        } else if (j == size) {
            // first connect to bottom virtual node
            uf.union(index, szGrid + 1);
            n = index(i, j - 1);
            if (isOpen(n)) {
                uf.union(index, n);
            }
        } else {
            // j in the middle of the grid
            n = index(i, j + 1);
            if (isOpen(n)) {
                uf.union(index, n);
            }
            n = index(i, j - 1);
            if (isOpen(n)) {
                uf.union(index, n);
            }
        }

        if (i < size) {
            n = index(i + 1, j);
            if (isOpen(n)) {
                uf.union(index, n);
            }
        }
        if (i > 1) {
            n = index(i - 1, j);
            if (isOpen(n)) {
                uf.union(index, n);
            }
        }
    }

    /*
     * is site (row i, column j) open?
     */
    public boolean isOpen(int j, int i) {
        return isOpen(index(i, j));
    }

    private boolean isOpen(int index) {
        return state[index] == State.OPEN;
    }

    /*
     * is site (row i, column j) full?
     */
    public boolean isFull(int j, int i) {
        final int index = index(i, j);
        return uf.connected(index, szGrid);
    }

    /*
     * does the system percolate?
     */
    public boolean percolates() {
        return uf.connected(szGrid, szGrid + 1);
    }
}