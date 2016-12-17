import java.io.*;
import java.util.*;

public class ReadFile {
	public static void main (String[] args){
		datasetReader("datasetA.txt");
	}
	public static LinkedList<Graph> datasetReader(String fichier) {
		String graphs = "";

		// lecture du fichier texte
		try {
			InputStream ips = new FileInputStream(fichier);
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String ligne;
			// int i=-1;
			while ((ligne = br.readLine()) != null) {
				graphs += ligne + ":";
			}

			br.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		String[] G = graphs.split(":");
		for(int i=0; i<G.length; i++)
		 System.out.println(G[i]);

		int n = G.length;
		LinkedList<Graph> graphes = new LinkedList<Graph>();
		String numbers = "";
		String Ks = "";
		String ns = "";
		String node = ""; // pour Graph2 : "2/0:1:2:3="
		String voisins = "";// pour Graph2 : "2/1 2:0 3:0 3:1 2=" = marque la fin
		// pour le graph
		String hints = "";// pour Graph2 "2/1:2:0:3="
		int i = 0;
		int marqueur=0;
		while (i < n) {
			// for (int i = 0; i < n; i++) {
			if (G[i].length() > 0) {
				//note pour Lorraine : il n'y avait pas de else donc si la condition ci-dessus n'etait pas verifie, on a une boucle infinie
				// conseil pour la prochaine fois : la boucle for!!!
				if (G[i].charAt(0) == 'G') {
					String[] number = G[i].split(" ");
					// System.out.println(number[1]);
					numbers += number[1] + ":";
					marqueur=Integer.parseInt(number[1]);
					i++;
				} else if (G[i].charAt(0) == 'K') {
					String[] K = G[i].split("=");
					// System.out.println(number[1]);
					Ks += K[1] + ":";
					i++;
				} else {
					String[] nodes = G[i].split("-->");
					// System.out.println(nodes[0]);
					if (nodes.length == 2) {
						if (G[i-1].charAt(0)=='G'){
							node+=marqueur+"/";
							voisins+=marqueur+"/";
						}
						node += nodes[0]+ ":";
						voisins += nodes[1] + ":";
						if (G[i + 1].charAt(0) == 'G'){// lorraine : tu ne peut pas faire ca, c'est dangereux car si tu le fait en derniere iteration tu sort du tableau
							voisins += "=";
							node+="=";
						}
						i++;
					} else {
						String[] hint = G[i].split(" "); // Graph2
															// ["1<-->2","0<-->3"]
						hints+=marqueur+"/";
						for (int j = 0; j < hint.length; j++) {
							String[] h = hint[j].split("<->");// lorraine : tu as mis "<-->" d'ou l'erreur
							hints += h[0] +":"+ h[1]+":";
						}
						hints += "=";
						i++;

					}
				}
			}
			else i++;
		}
		String[] number=numbers.split(":");
		String[] K=Ks.split(":");
		String[] Vertices=node.split("=");//"2/0:1:2:3","3/..
		String[] Adjacency=voisins.split("=");
		String[] hin=hints.split("=");
		String[] noeuds=new String[number.length+1]; // case num i pour les noeuds du graphe i
		String[] adjacents=new String[number.length+1];
		String[] HINT=new String[number.length+1];
		for (int l=0;l<Vertices.length; l++){
			String[] N=Vertices[l].split("/");
			noeuds[Integer.parseInt(N[0])]=N[1];	
		}
		for (int l=0;l<Adjacency.length; l++){
			String[] N=Adjacency[l].split("/");
			adjacents[Integer.parseInt(N[0])]=N[1];	
		}
		for (int l=0;l<hin.length; l++){
			String[] N=hin[l].split("/");
			HINT[Integer.parseInt(N[0])]=N[1];	
		}
		
		
		
		for (int k=0; k<number.length; k++){
			Graph g=new Graph();
			g.number=Integer.parseInt(number[k]);
			g.K=Integer.parseInt(K[k]);
			String[] nod=noeuds[k].split(":");
			g.n=Math.max(g.K,Integer.parseInt(nod[nod.length-1]));// valeur du dernier noeud = le plus grand
			LinkedList<Integer>[] edges = new LinkedList[g.n];
			String[] adj=adjacents[k].split(":");
			for(int l=0;l<nod.length; l++){
				edges[Integer.parseInt(nod[l])]=new LinkedList<Integer>();
				String[] finaladj=adj[l].split(" ");
				for ( String a : finaladj)
					edges[Integer.parseInt(nod[l])].add(Integer.parseInt(a));
			}
			g.edges=edges;
			String[] H=HINT[k].split(":");
			LinkedList<Integer> hint=new LinkedList<Integer>();
			for(int l=0;l<H.length; l++){
				hint.add(Integer.parseInt(H[l]));
			}
			g.hint=hint;
		}
		return graphes;
		}

}