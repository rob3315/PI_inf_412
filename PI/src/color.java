import java.io.File;
import java.io.FileWriter;
import java.util.LinkedList;

public class color {

	public static void main(String[] args) {
		String graphPath="datasetA.txt";
		String colorPath="datasetA_color.txt";
		LinkedList<Graph> l = ReadFile2.datasetReader(graphPath);
		File fichier =new File(colorPath);
		try {
            // creation of the file
            fichier.createNewFile();
            FileWriter writer = new FileWriter(fichier);
            try {
            	for (Graph g:l){
            		writer.write(print_tab(g, naive_greedy_algorithm(g)));
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
			if (g.edges[i]!=null){
				//if i has neighbors
				for (int v : g.edges[i]){
					if (color[v]!=-1){
						unavailable_color.add(color[v]);
					}
				}
			}
			unavailable_color.sort(null);
			int c=0;
			if (unavailable_color!=null){
				while (unavailable_color.isEmpty()==false && c==unavailable_color.pop()){
					c++;// we are looking for the first color we could use
				}
			}
			color[i]=c;
		}
		return color;
	}

}
