public class PercolationStats {
    private final int gridSize;
    private final int nTests;
    private final double[] results;

    /*
     * perform T independent computational experiments on an N-by-N grid
     */
    public PercolationStats(final int N, final int T) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("N and T must be greater than 0");
        }

        gridSize = N;
        nTests = T;
        results = new double[nTests];
        for (int i = 0; i < nTests; i++) {
            results[i] = 0;
        }
    }

    /*
     * sample mean of percolation threshold
     */
    public double mean() {
        return StdStats.mean(results);
    }

    /*
     * sample standard deviation of percolation threshold
     */
    public double stddev() {
        return StdStats.stddev(results);
    }

    /*
     * returns lower bound of the 95% confidence interval
     */
    public double confidenceLo() {
        return mean() - (1.96 * stddev() / Math.sqrt(nTests));
    }

    /*
     * returns upper bound of the 95% confidence interval
     */
    public double confidenceHi() {
        return mean() + (1.96 * stddev() / Math.sqrt(nTests));
    }

    private void run() {
        // execute the tests
        for (int n = 0; n < nTests; n++) {
            int threshold = 0;
            Percolation p = new Percolation(gridSize);

            while (!p.percolates()) {
                int i = StdRandom.uniform(gridSize) + 1;
                int j = StdRandom.uniform(gridSize) + 1;

                if (!p.isOpen(i, j)) {
                    p.open(i, j);
                    threshold++;
                }
            }
            results[n] = ((double) threshold) / (gridSize * gridSize);
        }
    }

    public static void main(String[] args) {
        final int gridSize = StdIn.readInt();
        final int tests = StdIn.readInt();

        PercolationStats s = new PercolationStats(gridSize, tests);
        s.run();

        StdOut.printf("%-23s = %f\n", "mean", s.mean());
        StdOut.printf("%-23s = %f\n", "stddev", s.stddev());
        StdOut.printf("%-23s = %f, %f\n", "95% confidence interval",
                s.confidenceLo(), s.confidenceHi());
    }
}