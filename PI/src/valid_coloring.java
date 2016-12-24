import java.util.Collection;
import java.util.Map;

public class valid_coloring {
	public static void main(String[] args) {
		String datasetPath="datasetB.txt";
		String colorPath="datasetB_color.txt";
		Collection<Graph> l = ReadFile2.datasetReader(datasetPath);
		System.out.println("dataset read");
		Map<Integer,int[]> colorHash=ReadFile2.colorReader(colorPath);
		System.out.println("color read");
		int valid=0;
		int not_valid=0;
		int missing=0;
		int hint=0;
		int color=0;
		for (Graph g :l){
			if (colorHash.containsKey(g.number)){
				if (g.isvalid_coloring(colorHash.get(g.number))){
					int h= g.number_hint(colorHash.get(g.number));
					if (h>1){
						System.out.println(String.format("Graph %d: valid (%d hints satisfied)", g.number,h));
					}
					else{
						System.out.println(String.format("Graph %d: valid (%d hint satisfied)", g.number,h));
					}
					hint+=h;
					valid+=1;
					color+=valid_coloring.get_max(colorHash.get(g.number));
				}
				else{
					System.out.println(String.format("Graph %d: not valid", g.number));
					not_valid+=1;
					return;
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
		System.out.println(String.format("	NO_HINTS_SATISFIED = %d",hint));
		System.out.println(String.format("	NO_COLORS_USED = %d",color));
		return;
	}
	private static int get_max(int[] tab){
		int max=0;
		for (int i=0; i<tab.length;i++){
			if (tab[i]>max){
				max=tab[i];
			}
		}
		return max;
	}
}
