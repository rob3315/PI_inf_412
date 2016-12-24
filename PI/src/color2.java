import java.io.File;
import java.io.FileWriter;
import java.util.LinkedList;

public class color2 {
	public static void main(String[] args) {
		String graphPath="datasetB.txt";
		String colorPath="datasetB_color.txt";
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
		//System.out.println("Decreasing avant opération:");
		//for (int i : Decreasing)
		//	System.out.println(i);
		return Decreasing;
		// return the vertices in decreasing order of degree
		
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
		int compt=g.K+1;// colored vertices
		LinkedList<Integer> colored= new LinkedList<Integer>();
		if (g.K>0){
			for (int i=0; i<g.K; i++){
				color2[i]=c;
				c++;
				colored.add(i);
			}
		}
			
		while (compt<g.n){
			if (g.edges[vertex]==null && vertex>g.K){
				color2[vertex]=0;
				Decreasing[v]=-1;
				compt++;
			}
			else if (vertex>g.K){
				color2[vertex]=c;
				Decreasing[v]=-1;
				compt++;
			}
			else if (vertex<=g.K)
				Decreasing[v]=-1; // on est obligé de repasser dans les K premiers vertex car on ne connait pas leur classement dans Decreasing
//			System.out.println("vertex " + vertex);
//			System.out.println("nb vertices " + g.n);
		//	System.out.println("Decreasing :");
			//for (int i : Decreasing)
			//	System.out.println(i);
			//System.out.println("compt "+compt);
			colored.add(vertex);
			int k=color2[vertex];
			for (int j=0;j<g.n;j++){ //index j of vertex i dans le bon ordre
				if (Decreasing[j]!=-1 && Decreasing[j]>g.K){
					boolean flag=true;
					for (int co : colored){ //pb on stocke toutes les couleurs mais on affecte ensuite au vertex que la couleur c du dernier graph
						if ( g.edges[co]!=null){
							if (g.edges[co].contains(Decreasing[j]) && color2[co]==k)// pb qd vertex<K, ce n'est pas le c actuel qu'on affecte mais color2[vertex]
								flag=false;
						}
					}
					if (flag){
						color2[Decreasing[j]]=k;
						compt++;
						colored.add(Decreasing[j]);
						Decreasing[j]=-1; 
						
					}
				}
				
			}
			
			c++;
			//System.out.println(g.number);
			//System.out.println("vertex " + vertex);
			while (Decreasing[v]==-1 && v<g.n-1){
				v++;
			}
			vertex=Decreasing[v]; // vertex uncolored with highest degree 
			
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
