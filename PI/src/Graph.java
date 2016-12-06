import java.util.LinkedList;
public class Graph {
	int number; // reference of the graph
	int n; // number of nodes - K
	int K;
	LinkedList<Integer>[] edges;
	int[] hint;
	public void Graph(int number, int n, int K, LinkedList<Integer>[] edges, int[] hint){
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
		for (int i=0; i< n -this.K;i++){
			int c=colors[i+K];
			for (int j : edges[i]){
				if (colors[j]==c) return false;
			}
			
		}
	return true;
	}
}
