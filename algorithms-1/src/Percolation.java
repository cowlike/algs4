public class Percolation {
	private WeightedQuickUnionUF uf;
	
	/*
	 * create N-by-N grid, with all sites blocked
	 */
	public Percolation(final int N) { 
		uf = new WeightedQuickUnionUF((N * N) + 2);
	}

	/*
	 * open site (row i, column j) if it is not already
	 */
	public void open(int i, int j) {

	}

	/*
	 * is site (row i, column j) open?
	 */
	public boolean isOpen(int i, int j) { 
		return true;
	}

	/*
	 * is site (row i, column j) full?
	 */
	public boolean isFull(int i, int j) { 
		return true;
	}

	/*
	 * does the system percolate?
	 */
	public boolean percolates() { 
		return true;
	}
}