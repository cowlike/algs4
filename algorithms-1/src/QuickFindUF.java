public class QuickFindUF extends UF {
	
	public QuickFindUF(int N) {
		super(N);
	}

	/* (non-Javadoc)
	 * @see UF1#connected(int, int)
	 */
	@Override
	public boolean connected(int p, int q) {
		return id[p] == id[q];
	}

	/* (non-Javadoc)
	 * @see UF1#union(int, int)
	 */
	@Override
	public void union(int p, int q) {
		int pid = id[p];
		int qid = id[q];
		for (int i = 0; i < id.length; i++)
			if (id[i] == pid)
				id[i] = qid;
	}
}