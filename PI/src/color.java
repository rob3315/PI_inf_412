import java.io.File;
import java.io.FileWriter;
import java.util.Collection;
import java.util.LinkedList;
import java.util.TreeSet;

public class color {

	public static void main(String[] args) {
		String graphPath="datasetB.txt";
		String colorPath="datasetB_color.txt";
		Collection<Graph> l = ReadFile2.datasetReader(graphPath);
		File fichier =new File(colorPath);
		try {
            // creation of the file
            fichier.createNewFile();
            FileWriter writer = new FileWriter(fichier);
            try {
            	for (Graph g:l){
            		//writer.write(print_tab(g, naive_greedy_algorithm(g)));
            		writer.write(print_tab(g, naive_greedy_algorithm_with_max_hint(g)));
            		System.out.println(g.number);
            	}
            }
            finally {
                writer.close();
            }
        } catch (Exception e) {
            System.out.println("fail to create the file");
            System.out.println(e);
        }
		

	}
	private static String print_tab(Graph g, int[] color){
		String res=String.format("Graph %d:\n",g.number);
		for (int i=0;i<color.length;i++){
			res+=String.format("%d -c> %d\n",i,color[i]);
		}
		return res;
	}
	private static int[] naive_greedy_algorithm(Graph g){
		int[] color = new int[g.n];
		for (int i=0;i<g.n;i++){color[i]=-1;}//we initialize the array
		for (int i = 0; i<  g.K;i++){color[i]=i;}// we deal with the first K elements
		for (int i=g.K;i<g.n;i++){
			LinkedList<Integer> unavailable_color= new LinkedList<Integer>();
			if (g.edges.containsKey(i)){
				//if i has neighbors
				for (int v : g.edges.get(i)){
					if (color[v]!=-1){
						unavailable_color.add(color[v]);
					}
				}
			}
			unavailable_color.sort(null);
			int c=0;
			int last=-1;
			boolean flag=true;
			if (unavailable_color!=null){
				while (unavailable_color.isEmpty()==false && flag){
					int d=unavailable_color.pop();
					if (d==last+1){
						last+=1;
						c++;
					}
					else if (d!=last){
						flag=false;
					}
				}
			}
			color[i]=c;
		}
		return color;
	}
	private static int[] naive_greedy_algorithm_with_max_hint(Graph g){
		int[] color = new int[g.n];
		for (int i=0;i<g.n;i++){color[i]=-1;}//we initialize the array
		g.create_hint_map();
		for (int i = 0; i<  g.K;i++){color[i]=i;}// we deal with the first K elements
		for (Integer i : g.edges.keySet()){
			Collection<Integer> unavailable_color= new TreeSet<Integer>();
			if (g.edges.containsKey(i)){
				//if i has neighbors which should always be the case
				for (int v : g.edges.get(i)){
					if (color[v]!=-1){
						unavailable_color.add(color[v]);
					}
				}
			}
			int c=0;
			boolean flag=true;
			// We try the hint first
			if (g.hint_map.containsKey(i)){
				for (Integer h :g.hint_map.get(i)){
					if (color[h]!=-1 && unavailable_color.contains(color[h])==false){
						c=color[h];
						flag=false;
					}
				}
			}
			// else we find the first we can
			while (unavailable_color!=null  && flag){
				if (unavailable_color.contains(c)){
					c++;
				}
				else{
					flag=false;
				}
			}
			color[i]=c;
		}
		return color;
	}

}
