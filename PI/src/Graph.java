import java.util.LinkedList;
public class Graph {
	int number; // reference of the graph
	int n; // number of nodes
	int K;
	LinkedList<Integer>[] edges;
	LinkedList<Integer> hint;
	public void Graph(int number, int n, int K, LinkedList<Integer>[] edges, LinkedList<Integer> hint){
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
		for (int i=this.K; i< n;i++){
			int c=colors[i];
			for (int j : edges[i]){
				if (colors[j]==c) return false;
			}
			
		}
	return true;
	}
	
	public String toString(){
		return "Graph "+this.number+", "+this.n+" nodes, "+this.K+" colored nodes, "+this.hint.size()/2+" hints";
	}
}