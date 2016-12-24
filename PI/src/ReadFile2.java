import java.io.*;
import java.util.*;

public class ReadFile2 {
	public static Collection<Graph> datasetReader(String fichier) {
		List<String> lines= new LinkedList<String>();//for the storage of all the line

		// lecture du fichier texte
		try {
			InputStream ips = new FileInputStream(fichier);
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String ligne;
			while ((ligne = br.readLine()) != null) {
				lines.add(ligne);
			}
			br.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		LinkedList<Graph> graphes = new LinkedList<Graph>();
		Graph current = null;
		int nb_node=0;// number of node of current graph as far as I know
		for (String l : lines){
			if (l.replaceAll("\\s","").length() > 0) {
				//if the line is not empty
				if (l.charAt(0) == 'G') {
					if (current!=null){
						// we finish the precedent graph
						current.n=nb_node+1;
					}
					//we add a new graph and we initialize current
					String[] number = l.split(" ");
					current =new Graph(Integer.parseInt(number[1].replaceAll("\\W","")));
					graphes.add(current);
					current.edges= new HashMap<Integer, Collection<Integer>>();
				}
				else if (l.charAt(0) == 'K') {
					//we set K value
					String[] K = l.split("=");
					nb_node=Integer.parseInt(K[1])-1;
					current.K=nb_node+1;
				}
				else {
					String[] nodes = l.split("-->");
					if (nodes.length == 2){
						//we are dealing with edges
						nb_node=Integer.parseInt(nodes[0].replaceAll("\\W",""));//we complete current
						current.edges.put(nb_node, new LinkedList<Integer>());
						for (String k : nodes[1].split(" ")){
							if (k.equals("")==false){
								current.edges.get(nb_node).add(Integer.parseInt(k.replaceAll("\\s","")));
							}
						}
					}
					else {
						//we are dealing with hints
						String[] hint = l.split(" ");
						for (String k : hint) {
							String[] h = k.split("<->");
							Integer[] ti={Integer.parseInt(h[0]),Integer.parseInt(h[1])};
							current.hint.add(ti);
						nb_node=Math.max(nb_node,Math.max(ti[1], ti[0]));//line add because of graph 25 of datasetB
						}
					}
				}
			}
		}
		// we finish the last graph
		current.n=nb_node+1;
		return graphes;
		}

	public static Map<Integer,int[]> colorReader(String fichier) {
		LinkedList<String> lines= new LinkedList<String>();//for the storage of all the line

		// lecture du fichier texte
		try {
			InputStream ips = new FileInputStream(fichier);
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String ligne;
			while ((ligne = br.readLine()) != null) {
				lines.add(ligne);
			}
			br.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		Map<Integer,int[]> graph_coloring= new Hashtable<Integer,int[]>();//hashtable, the key is the number of the Graph and the content is the array of color
		
		int n = -1;// the number of the graph
		int nb_vertice=0;// number of vertices of current graph as far as I know
		LinkedList<Integer> lst_edges=null; //list of the color of the vertices
		for (String l : lines){
			if (l.replaceAll("\\s","").length() > 0) {
				//if the line is not empty
				if (l.charAt(0) == 'G') {
					if (lst_edges!=null){
						// we finish the precedent graph
						int[] color=new int[nb_vertice];
						for ( int i=0;i<nb_vertice;i++){
							color[i]=lst_edges.pop();
						}
						graph_coloring.put(n, (int[]) color.clone());
					}
					//we initialise a new list for the color of the new graph
					String[] number = l.split(" ");
					n =Integer.parseInt(number[1].replaceAll("\\W",""));
					lst_edges= new LinkedList<Integer>();
					nb_vertice=0;
				}
				else {
				//we add a color
					String[] h = l.split("-c>");
					lst_edges.add(Integer.parseInt(h[1].replaceAll("\\s", "")));
					nb_vertice+=1;
				}
			}
		}
		// we finish the last graph
		int[] color=new int[nb_vertice];
		for ( int i=0;i<nb_vertice;i++){
			color[i]=lst_edges.pop();
		}
		graph_coloring.put(n, (int[]) color.clone());
		return graph_coloring;
		}
}