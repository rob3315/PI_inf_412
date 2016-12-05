import java.util.Queue;

public class Graph {
	int number; // reference of the graph
	int n; // number of nodes
	int K;
	Queue<Queue<Integer>> edges;
	int[] hint;
	public void Graph(int number, int n, int K, Queue<Queue<Integer>> edges, int[] hint){
		this.number=number;
		this.n=n;
		this.K=K;
		this.edges=edges;
		this.hint=hint;
	}
	private boolean isvalid_coloring(int[] colors){
		for (int i=0;i<K;i++){
			for (int j=i+1;j<K;j++){
				if (colors[i]==colors[j]){
					return false;
				}
			}
		}
		//if we arrive this point, it means that the K first nodes are colored with differents colors
		while
	}
}
