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
		state[szGrid] = state[szGrid + 1] = State.OPEN;
	}

	private int index(int i, int j) {
		if (i < 1 || i > size || j < 1 || j > size) {
			throw new IllegalArgumentException(
					"arguments are not within the grid range");
		}
		return (i - 1) + ((j - 1) * size);
	}

	/*
	 * open site (row i, column j) if it is not already
	 */
	public void open(int i, int j) {
		final int index = index(i, j);
		state[index] = State.OPEN;
		int n;

		if (j == 1) {
			// first connect to top virtual node
			uf.union(index, szGrid);
			if (isOpen(n = index(i, j + 1))) {
				uf.union(index, n);
			}
		} else if (j == size) {
			// first connect to bottom virtual node
			uf.union(index, szGrid + 1);
			if (isOpen(n = index(i, j - 1))) {
				uf.union(index, n);
			}
		} else {
			// j in the middle of the grid
			if (isOpen(n = index(i, j + 1))) {
				uf.union(index, n);
			}
			if (isOpen(n = index(i, j - 1))) {
				uf.union(index, n);
			}
		}

		if (i < size) {
			if (isOpen(n = index(i + 1, j))) {
				uf.union(index, n);
			}
		}
		if (i > 1) {
			if (isOpen(n = index(i - 1, j))) {
				uf.union(index, n);
			}
		}
	}

	/*
	 * is site (row i, column j) open?
	 */
	public boolean isOpen(int i, int j) {
		return isOpen(index(i, j));
	}

	private boolean isOpen(int index) {
		return state[index] == State.OPEN;
	}

	/*
	 * is site (row i, column j) full?
	 */
	public boolean isFull(int i, int j) {
		final int index = index(i, j);
		return uf.connected(index, szGrid);
	}

	/*
	 * does the system percolate?
	 */
	public boolean percolates() {
		return uf.connected(szGrid, szGrid + 1);
	}

	public String toString() {
		StringBuffer buf = new StringBuffer();

		for (int j = 1; j <= size; j++) {
			for (int i = 1; i <= size; i++) {
				String s = isFull(i, j) ? "f " : isOpen(i, j) ? "o " : "_ ";
				buf.append(s);
			}
			buf.append('\n');
		}
		return buf.toString();
	}
}