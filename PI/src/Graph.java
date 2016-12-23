import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
public class Graph {
	int number; // reference of the graph
	int n; // number of nodes ie last number which appear in the graph description
	int K;
	List<Integer>[] edges;
	Collection<Integer[]> hint;//a hint is an array of 2 elements

	public Graph(){
		//used in Readfile
	}
	public Graph(int number){
		//used in Readfile2
		this.number=number;
		this.hint = new LinkedList<Integer[]>();
	}
	public Graph(int number, int n, int K, LinkedList<Integer>[] edges, LinkedList<Integer[]> hint){
		this.number=number;
		this.n=n;
		this.K=K;
		this.edges=edges;
		this.hint=hint;
	}
	public boolean isvalid_coloring(int[] colors){
		for (int i=0;i<this.K;i++){
			for (int j=i+1;j<this.K;j++){
				if (colors[i]==colors[j]){
					//System.out.println(i); //for debug purpose
					//System.out.println(j);
					return false;
				}
			}
		}
		for (int i=this.K; i< this.n;i++){
			int c=colors[i];
			if (this.edges[i]!=null){
				for (int j : this.edges[i]){
					if (colors[j]==c) {
						//System.out.println(i); //for debug purpose
						//System.out.println(j);
						return false;
					}
				}
			}
			
		}
	return true;
	}

	public int number_hint(int[] colors){
		int res=0;
		for (Integer[] h : this.hint){
			//System.out.println(h[0]); for debug purpose
			//System.out.println(h[1]);
			//System.out.println(colors.length);
			//System.out.println(this.number);
			if (colors[h[0]]==colors[h[1]]){
				res+=1;
			}
		}
		return res;
	}
	public String toString(){
		return "Graph "+this.number+", "+this.n+" nodes, "+this.K+" colored nodes, "+this.hint.size()/2+" hints";
	}
}