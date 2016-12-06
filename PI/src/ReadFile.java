import java.io.*;
import java.util.*;

public class ReadFile {
	public static void main(String[] args) {
		String graphs = "";
		String fichier = "GraphTest.txt";

		// lecture du fichier texte
		try {
			InputStream ips = new FileInputStream(fichier);
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String ligne;
			// int i=-1;
			while ((ligne = br.readLine()) != null) {
				// System.out.println(ligne);
				// if (ligne.charAt(0)=='G'){
				// graphs.length++;
				// i++;
				// graphs[i]=ligne;
				// }

				graphs += ligne + ":";
			}
			br.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		System.out.println(graphs);
		String[] G = graphs.split(":");

		int n = G.length;
		String graphID = "";
		String colored = "";
		String nbvertices = "";
		HashMap<Integer, Integer> vertices = new HashMap<Integer, Integer>();
		// String adjacency="";
		for (int i = 0; i < n; i++) {
			if (G[i].length() > 0) {
				if (G[i].charAt(0) == 'G')
					graphID += G[i];
				// else{
				if (G[i].charAt(0) == 'K')
					colored += G[i];
				// if (G[i].charAt(0)==)
				// else
				// adjacency+=G[i]+":";
			}
		}
		// }

		String[] Colored = colored.split("K=");
		String[] ID = graphID.split("Graph ");
		// for (int i =0; i<ID.length; i++)
		// System.out.println(ID[i]);
		int nbGraph = ID.length;

	}

}
