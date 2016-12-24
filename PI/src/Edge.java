import java.util.Collection;

public class Edge implements Comparable<Edge>{
	Collection<Edge> neightbour;
	int number;
	int color;
	public int getLength(){
		return this.neightbour.size();
	}
	public Edge(Collection<Edge> neightbour, int number) {
		super();
		this.neightbour = neightbour;
		this.number = number;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + number;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Edge other = (Edge) obj;
		if (number != other.number)
			return false;
		return true;
	}
	@Override
	public int compareTo(Edge o) {
		// TODO Auto-generated method stub
		return (this.getLength() < o.getLength()) ? -1 : 1;
	}
	
	
}
