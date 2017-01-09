import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
public class Graph {
	int number; // reference of the graph
	int n; // number of nodes ie last number which appear in the graph description
	int K;
	Map<Integer, Collection<Integer>> edges;
	Collection<Integer[]> hint;//a hint is an array of 2 elements
	Map<Integer, Collection<Integer>> hint_map;
	public Graph(int number){
		//used in Readfile
		this.number=number;
		this.hint = new LinkedList<Integer[]>();
	}
	public Graph(int number, int n, int K, Map<Integer, Collection<Integer>> edges, Collection<Integer[]> hint){
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
			if (this.edges.containsKey(i)){
				for (int j : this.edges.get(i)){
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

	public int number_hint_satisfied(int[] colors){
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
	@Override
	public String toString(){
		return "Graph "+this.number+", "+this.n+" nodes, "+this.K+" colored nodes, "+this.hint.size()+" hints";
	}
	public void create_hint_map(){
		this.hint_map= new HashMap<Integer,Collection<Integer>>();
		for (Integer[] tab : this.hint){
			if (this.hint_map.containsKey(tab[0])==false){
				this.hint_map.put(tab[0], new LinkedList<Integer>());
			}
			if (this.hint_map.containsKey(tab[1])==false){
				this.hint_map.put(tab[1], new LinkedList<Integer>());
			}
			this.hint_map.get(tab[0]).add(tab[1]);
			this.hint_map.get(tab[1]).add(tab[0]);
		}
	}
	public void check_edges(){
		LinkedList<Integer> s= new LinkedList<Integer>();
		s.addAll(this.edges.keySet());
		for (Integer e :s){
			for (Integer i : this.edges.get(e)){
				if (i>=this.K){
					if (this.edges.containsKey(i)==false){
						this.edges.put(i, new LinkedList<Integer>());
					}
					if (this.edges.get(i).contains(e)==false){
						this.edges.get(i).add(e);
					}
				}
			}
		}
	}
}