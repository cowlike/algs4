public class UnionClient {
	
	public static void main(String[] args) throws Exception {
		int N = StdIn.readInt();
		UF uf = new WeightedQuickUnionUF(N);
		while (!StdIn.isEmpty()) {
			int p = StdIn.readInt();
			int q = StdIn.readInt();
			if (!uf.connected(p, q)) {
				uf.union(p, q);				
			}
			StdOut.println(uf.toString());
		}
	}
}
