package common;

import java.io.Serializable;

public class VectorTimestamp implements Serializable {

	private static final long serialVersionUID = 1L;

	private int[] ts;

	public VectorTimestamp(int dim) {
		ts = new int[dim];
	}

	public synchronized boolean isLessThan(VectorTimestamp t) {
		boolean strictlyless = false;
		for (int i = 0; i < this.ts.length; i++) {
			if (this.ts[i] > t.ts[i])
				return false;
			if (this.ts[i] < t.ts[i])
				strictlyless = true;
		}
		return strictlyless;
	}

	public synchronized void next(int id) {
		ts[id]++;
	}

	public synchronized void next(int id, VectorTimestamp t) {
		for (int i = 0; i < ts.length; i++) {
			ts[i] = Math.max(ts[i], t.ts[i]);
		}
		next(id);
	}

	public synchronized String toString() {
		StringBuilder sb = new StringBuilder(20);
		sb.append("[");
		for (int i = 0; i < ts.length; i++) {
			if (i != 0)
				sb.append(",");
			sb.append(ts[i]);
		}
		sb.append("]");
		return sb.toString();
	}

}
