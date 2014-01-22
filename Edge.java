public class Edge{
	
	private String lowestTitle, edgeName;

	/*Edge Constructor, links nodes*/
	public Edge(String concatNode, String connection){
		this.lowestTitle = connection;
		this.edgeName = concatNode;
	}

	
	/*@Param takes string title as input
	@modifies changes String title of node if lexicographically smaller*/
	public void addTitle(String title){
		if ((this.lowestTitle).compareTo(title) > 0){
			lowestTitle = title;
		}
		else
			return;
		}

	/*@returns title of edge.*/
	public String edgeName(){
		return lowestTitle;
	}
}