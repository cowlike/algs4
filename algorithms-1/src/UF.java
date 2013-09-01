public abstract class UF {
	protected int[] id;
	protected int[] sz;

	public UF(int N) {
		id = new int[N];
		sz = new int[N];
		for (int i = 0; i < N; i++) {
			id[i] = i;
			sz[i] = 1;
		}
	}
	
	public String toString() {
		String result = "";
		for (int i = 0; i < id.length; i++) {
			result += id[i];
			result += " ";
		}
		result += "[";
		for (int i = 0; i < sz.length; i++) {
			result += sz[i];
			result += " ";
		}
		
		return result += "]";		
	}
	
	public abstract boolean connected(int p, int q);

	public abstract void union(int p, int q);

}