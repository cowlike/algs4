public class PercolationStats {
	/*
	 * perform T independent computational experiments on an N-by-N grid
	 */
	public PercolationStats(final int N, final int T){
		if (N <= 0 || T <= 0) {
			throw new IllegalArgumentException("N and T must be greater than 0");
		}
	}

	/*
	 * sample mean of percolation threshold
	 */
	public double mean() {
		return 0;
	}

	/*
	 * sample standard deviation of percolation threshold
	 */
	public double stddev() {
		return 0;
	}

	/*
	 * returns lower bound of the 95% confidence interval
	 */
	public double confidenceLo() {
		return 0;
	}

	/*
	 * returns upper bound of the 95% confidence interval
	 */
	public double confidenceHi() {
		return 0;
	}

	public static void main(String[] args) {
		// run tests
	}
}