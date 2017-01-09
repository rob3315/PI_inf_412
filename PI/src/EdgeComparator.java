import java.util.Comparator;

public class EdgeComparator implements Comparator<Integer>{
	Graph g;

	public EdgeComparator(Graph g) {
		super();
		this.g = g;
	}
	@Override
	public int compare(Integer o1, Integer o2) {
		// TODO Auto-generated method stub
		if (g.edges.containsKey(o1)){
			if (g.edges.containsKey(o2)){
				if (g.edges.get(o1).size() > g.edges.get(o2).size()){return -1;}
				else if (g.edges.get(o1).size() < g.edges.get(o2).size()){return 1;}
				else{return o1.compareTo(o2);}
			}
			else {return -1;}
		}
		else{
			if (g.edges.containsKey(o2)){
				return 1;
			}
			else{
				return o1.compareTo(o2);
			}
		}
		
	}

}
