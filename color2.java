import java.io.File;
import java.io.FileWriter;
import java.util.LinkedList;

public class color2 {
	public static void main(String[] args) {
		String graphPath="datasetA.txt";
		String colorPath="datasetA_color.txt";
		LinkedList<Graph> l = ReadFile2.datasetReader(graphPath);
//		for (Graph g : l){ to check DegreeOf
//			System.out.println("Graph "+g.number);
//			DegreeOf(g);
//			System.out.println();
//		}
		File fichier =new File(colorPath);
		try {
            // creation of the file
            fichier.createNewFile();
            FileWriter writer = new FileWriter(fichier);
            try {
            	for (Graph g:l){
            		writer.write(printcoloring(g,welsh_powell_coloring(g,DegreeOf(g)))); 
            		//System.out.println(g.number);
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
	
	public static int[] DegreeOf(Graph g){
		int[] Degree=new int[g.n];
		for (int i=0; i<g.n; i++){
			if (g.edges[i]!=null)
				Degree[i]=g.edges[i].size();
			else
				Degree[i]=0;
		}
		//return Degree; value = degree
		
		LinkedList<Integer> DescDeg=new LinkedList<Integer>();
		for (int j=g.n; j>=0; j--){
			for(int i=0;i<g.n;i++){
				if (Degree[i]==j)
					DescDeg.add(i);
			}
		}
		int[] Decreasing=new int[g.n];
		for (int i = 0; i<g.n; i++){
			Decreasing[i]=DescDeg.pop();
		}
		//System.out.println(DescDeg.size()); size ok
		return Decreasing;// return the vertices in decreasing order of degree
	}
	
	public static int[] welsh_powell_coloring(Graph g,int[] Decreasing){
		int[] color2= new int[g.n];
		for (int i=0; i<g.n; i++)
			color2[i]=-1;
		int c=0;
		int vertex=Decreasing[0];
		//color2[vertex]=0;;
		int v=0;
		//Decreasing[v]=-1;
		int compt=0;// colored vertices
		while (compt<g.n){
			if (g.edges[vertex]==null){
				color2[vertex]=0;
				Decreasing[v]=-1;
				compt++;
			}
			else{
				color2[vertex]=c;
				Decreasing[v]=-1;
				compt++;
			}
			LinkedList<Integer> colored= new LinkedList<Integer>();
			colored.add(vertex);
			for (int j=0;j<g.n;j++){ //index j of vertex i dans le bon ordre
				if (Decreasing[j]!=-1){// pb si on a un vecteur i non connecté à vertex mais connecté a i+1
					for (int co : colored){
						if ( (g.edges[co]!=null) && (!g.edges[co].contains(Decreasing[j]))){
							color2[Decreasing[j]]=c;
							compt++;
							Decreasing[j]=-1; 
							colored.add(Decreasing[j]);
						}
					}
				}
				
			}
			c++;
			v++;
			while (Decreasing[v]==-1 && v<g.n){
				v++;
			}
			vertex=Decreasing[v];
		}
		return color2;
	}
	
	private static String printcoloring(Graph g, int[] color){
		String res=String.format("Graph %d:\n",g.number);
		for (int i=0;i<color.length;i++){
			res+=String.format("%d -c> %d\n",i,color[i]);
		}
		return res;
	}
	
}
