import java.io.File;
import java.io.FileWriter;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

public class color2 {
	public static void main(String[] args) {
		String graphPath="datasetB.txt";
		String colorPath="datasetB_color.txt";
		Collection<Graph> l = ReadFile2.datasetReader(graphPath);
//		for (Graph g : l){ to check DegreeOf
//			System.out.println("Graph "+g.number);
//			DegreeOf(g);
//			System.out.println();
//		}
		File fichier =new File(colorPath);
    	for (Graph g:l){
    		g.check_edges();
    		printcoloring(g,welsh_powell_coloring(g,DegreeOf(g))); 
    		System.out.println(g.number);
    	}
		try {
            // creation of the file
            fichier.createNewFile();
            FileWriter writer = new FileWriter(fichier);
            try {
            	for (Graph g:l){
            		writer.write(printcoloring(g,welsh_powell_coloring(g,DegreeOf(g)))); 
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
		System.out.println("end");
	}
	
	public static List<Integer> DegreeOf(Graph g){
		List<Integer> DescDeg=new LinkedList<Integer>();
		for(int i=g.K;i<g.n;i++){ DescDeg.add(i);}
		Comparator<Integer> comp=new EdgeComparator(g);
		Collections.sort(DescDeg,comp);
		return DescDeg;
		// return the vertices in decreasing order of degree
		
	}
	
	public static int[] welsh_powell_coloring(Graph g,List<Integer> Decreasing){
		int[] color= new int[g.n];
		for (int i=0; i<g.n; i++){color[i]=-1;}
		for (int i=0; i<g.K; i++){
			color[i]=i;
		}
		Iterator<Integer> iter;
		int c=0;
		while (Decreasing.isEmpty()==false){
			iter=Decreasing.iterator();
			Collection<Integer> same_co=new TreeSet<Integer>();//the vertices colored with c
			if (c<g.K){same_co.add(c);}
			while (iter.hasNext()){
				int current=iter.next();
				boolean coloriable=true;
				if (g.edges.containsKey(current)){
					for (int s : g.edges.get(current)){
						if (same_co.contains(s)){
							coloriable=false;
						}
					}
				}
				if (coloriable){
					color[current]=c;
					same_co.add(current);
					iter.remove();
				}
			}
			c++;
		}
		return color;
	}
	private static String printcoloring(Graph g, int[] color){
		String res=String.format("Graph %d:\n",g.number);
		for (int i=0;i<color.length;i++){
			res+=String.format("%d -c> %d\n",i,color[i]);
		}
		return res;
	}
	
}
