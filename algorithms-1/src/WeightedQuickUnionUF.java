public class WeightedQuickUnionUF extends UF {

	public WeightedQuickUnionUF(int N) {
		super(N);
	}

	private int root(int i) {
		while (i != id[i])
			i = id[i];
		return i;
	}

	@Override
	public boolean connected(int p, int q) {
		return root(p) == root(q);
	}

	@Override
	public void union(int p, int q) {
		StdOut.printf("connecting p:%d and q:%d\n", p, q);
		
		int i = root(p);
		int j = root(q);
		if (sz[i] < sz[j]) {
			id[i] = j;
			sz[j] += sz[i];
		} else {
			id[j] = i;
			sz[i] += sz[j];
		}
	}
}
