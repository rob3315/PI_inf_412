import java.util.Comparator;

public class edgeComparator implements Comparator<Integer>{
	Graph g;

	public edgeComparator(Graph g) {
		super();
		this.g = g;
	}
	@Override
	public int compare(Integer o1, Integer o2) {
		// TODO Auto-generated method stub
		return g.edges.get(o1).size() > g.edges.get(o2).size() ? -1 : 1;
	}

}
