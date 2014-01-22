import java.util.ArrayList;

public class GraphTest{
	
	public static void main(String [] args)
		{
			Graph marvelUniverse = new Graph();
			try{
				marvelUniverse.stringToGraph();
			 }catch(Exception e){
			 	e.printStackTrace();
 			}
 			
 			//Most Central Character
 			ArrayList<String> centralCandidates = marvelUniverse.mostReferences();
 			for (String s : centralCandidates){
 				System.out.println(s);
 			}
 			//System.out.println(marvelUniverse.stringCon("asdf","fsda"));
 			//System.out.println(marvelUniverse.stringCon("fsda","asdf"));
 			//marvelUniverse.allEdges();
 			/*//Test of sorted Reference output
 			ArrayList<String> centralCandidates = marvelUniverse.mostReferences();
 			for (String s : centralCandidates){
 				for (String x : (marvelUniverse.exposeNode(s)).getReferences()){
 				System.out.println(x);
// 				System.out.println(s);
 				}
 			}*/

 			marvelUniverse.createNode("Tony Wong");//node creation works
 			marvelUniverse.createNode("Princess Leia");
 			marvelUniverse.nodeJoin("Tony Wong", "Princess Leia", "Lord of the Rings");
 			System.out.println((marvelUniverse.exposeEdge("Tony Wong_Princess Leia")).edgeName());
 			System.out.println((marvelUniverse.exposeEdge("VENOM/EDDIE BROCK_SPIDER-MAN/PETER PAR")).edgeName());
 			marvelUniverse.nodeJoin("Tony Wong", "Darth Vader", "book1");
 			marvelUniverse.nodeJoin("Johnny Depp", "Darth Vader", "book2");
 			marvelUniverse.nodeJoin("Tony Stark", "Darth Vader", "book3");
 			marvelUniverse.nodeJoin("Alex Lam", "Darth Vader", "book4");
 			marvelUniverse.nodeJoin("Darth Vader", "Emperor Palpatine", "book5");
 			marvelUniverse.nodeJoin("Emperor Palpatine", "FrederickTomy", "book6");//node creation works via nodeJoin
 			System.out.println(marvelUniverse.validInput("SHROUD/MAXIMILLIAN Q", "CAPTAIN AMERICA"));
 			ArrayList<String> testZero = (marvelUniverse.bfsNode("FrederickTomy", "Johnny Depp"));
 			for (String s : testZero){
 				System.out.println(s);
 			}
 			System.out.println(marvelUniverse.isAdjacent("KILLRAVEN/JONATHAN R","FROST, CARMILLA"));
 			ArrayList<String> test = (marvelUniverse.bfs("FROST, CARMILLA", "KILLRAVEN/JONATHAN R"));
 			for (String s: test){
 				System.out.println(s);
 			}
 			/*ArrayList<String> testOne = marvelUniverse.bfsNode("HUMAN ROBOT", "CAPTAIN AMERICA");
 			for (String s: testOne){
 				System.out.println(s);
 			}*/
 			ArrayList<String> testTwo = (marvelUniverse.bfsNode("SHROUD/MAXIMILLIAN Q", "CAPTAIN AMERICA"));
 			for (String s: testTwo){
 				System.out.println(s);
 			}
			System.out.println(marvelUniverse.isAdjacent("Tony Wong","Princess Leia"));
			System.out.println(marvelUniverse.isAdjacent("M'SHULLA","FROST, CARMILLA"));
 			System.out.println(marvelUniverse.isAdjacent("FROST, CARMILLA","24-HOUR MAN/EMMANUEL"));
 			System.out.println(marvelUniverse.isAdjacent("VAPOR","AJAX"));
 			System.out.println(marvelUniverse.isAdjacent("VAPOR","MASTER FOUR")); //should be false
 			System.out.println(marvelUniverse.isAdjacent("WARLORD","MASTER FOUR"));
 			System.out.println(marvelUniverse.isAdjacent("Princess Leia","Tony Wong"));
			System.out.println(marvelUniverse.isAdjacent("Princess Leia","John Snow")); //should print invalid
		}

}