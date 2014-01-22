import java.util.ArrayList;
import java.util.Scanner;
public class mp5{
	
	public static void main(String [] args){

		Graph marvelUniverse = new Graph();
		try{
			marvelUniverse.stringToGraph();
		}catch(Exception e){
			e.printStackTrace();
		}

		ArrayList<String> centralCandidates = marvelUniverse.mostReferences();
		for (String s : centralCandidates){
			System.out.println("Name of the most central character is: "+ s + "(assumption that character with most neighbours would be most central.)");
			Scanner scan = new Scanner (System.in);
			System.out.println("CAUTION: WILL TAKE LONG TIME, time should be roughly proportional to number of nodes, if implemented wrongly will be much faster. Proper implementation requires BFS to clear visited flags on all visited nodes after EVERY iteration."+"\n");
			System.out.println("Do you want to find out his degree of centrality? Type \"YES\" or \"NO\"");
			String yesOrNo = scan.nextLine();
			if (yesOrNo.equals("YES")){
				System.out.println("Assumption that question is shortest path to node that is furthest away, roughly 6400 nodes to traverse");
				System.out.println(marvelUniverse.degreeCentrality(s));
			}

		}
		Scanner scanner = new Scanner (System.in);
		while (true){
			System.out.println("BFS search of the shortest path between two characters");
			System.out.println("Name of first character (Enter it exactly)");
			String nameFirst = scanner.nextLine();
			System.out.println("Name of the second charcter (Enter it exactly)");
			String nameSecond = scanner.nextLine();
			System.out.println("");
			ArrayList<String> bfsOut = marvelUniverse.bfs(nameFirst,nameSecond);
			for (String s: bfsOut){
 				if (s==null){
 					System.out.println("No path exists");
 					break;
 				}
 				System.out.println(s);
 			}
 			System.out.println("");
		}			



	}
}