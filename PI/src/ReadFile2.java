import java.io.*;
import java.util.*;

public class ReadFile2 {
	public static LinkedList<Graph> datasetReader(String fichier) {
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
		LinkedList<Graph> graphes = new LinkedList<Graph>();
		Graph current = null;
		int nb_node=0;// number of node of current graph as far as I know
		LinkedList<LinkedList<Integer>> lst_edges=null; //list of the edge of the current graph, first element of each list is the node the next are linked to
		for (String l : lines){
			if (l.replaceAll("\\s","").length() > 0) {
				//if the line is not empty
				if (l.charAt(0) == 'G') {
					if (current!=null){
						// we finish the precedent graph
						current.n=nb_node+1;
						current.edges=new LinkedList[current.n];
						for (LinkedList<Integer> e:lst_edges){
							current.edges[e.pop()]=(LinkedList) e.clone() ;
						}
					}
					//we add a new graph and we initialize current
					String[] number = l.split(" ");
					current =new Graph(Integer.parseInt(number[1].replaceAll("\\W","")));
					graphes.add(current);
					lst_edges= new LinkedList<LinkedList<Integer>>();
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
						LinkedList<Integer> e= new LinkedList<Integer>();
						for (String k : nodes[1].split(" ")){
							if (k.equals("")==false){
								e.add(Integer.parseInt(k.replaceAll("\\s","")));
							}
						}
						e.push(nb_node);// we add the number of the node at the head of the list
						lst_edges.add(e);
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
		current.edges=new LinkedList[current.n];
		for (LinkedList<Integer> e:lst_edges){
			current.edges[e.pop()]=e;
		}
		return graphes;
		}

	public static Hashtable<Integer,int[]> colorReader(String fichier) {
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
		Hashtable<Integer,int[]> graph_coloring= new Hashtable<Integer,int[]>();//hashtable, the key is the number of the Graph and the content is the array of color
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