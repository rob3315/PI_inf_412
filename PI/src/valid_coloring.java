import java.util.Hashtable;
import java.util.LinkedList;

public class valid_coloring {
	public static void main(String[] args) {
		String datasetPath="datasetA.txt";
		String colorPath="datasetA_color.txt";
		LinkedList<Graph> l = ReadFile2.datasetReader(datasetPath);
		Hashtable<Integer,int[]> colorHash=ReadFile2.colorReader(colorPath);
		int valid=0;
		int not_valid=0;
		int missing=0;
		for (Graph g :l){
			if (colorHash.containsKey(g.number)){
				if (g.isvalid_coloring(colorHash.get(g.number))){
					System.out.println(String.format("Graph %d: valid", g.number));
					valid+=1;
				}
				else{
					System.out.println(String.format("Graph %d: not valid", g.number));
					not_valid+=1;
				}
			}
			else{
				System.out.println(String.format("Graph %d: missing", g.number));
				missing+=1;
			}
		}
		System.out.println("TOTAL:");
		System.out.println(String.format("	NO_VALID = %d",valid));
		System.out.println(String.format("	NO_NOT_VALID = %d",not_valid));
		System.out.println(String.format("	NO_MISSING = %d",missing));
		return;
	}
}
