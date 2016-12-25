import java.util.Comparator;

public class DsaturComparator implements Comparator<Integer> {
	Graph g;
	int[] nb_neighbors_colored;
	public DsaturComparator(Graph g,int[] nb_neighbors_colored) {
		super();
		this.g = g;
		this.nb_neighbors_colored=nb_neighbors_colored;
	}


	@Override
	public int compare(Integer o1, Integer o2) {
		if (nb_neighbors_colored[o1]>nb_neighbors_colored[o2]){
			return -1;
		}
		else if(nb_neighbors_colored[o1]>nb_neighbors_colored[o2]){
			return 1;
		}
		else{
			if (g.edges.get(o1).size() > g.edges.get(o2).size()){return -1;}
			else if (g.edges.get(o1).size() < g.edges.get(o2).size()){return 1;}
			else return 0;
		}
	}

}
