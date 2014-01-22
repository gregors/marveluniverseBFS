import java.util.HashMap;
import java.util.*;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.lang.StringBuilder;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/*Graph class that has the ability to creates nodes and connect them via Edges.*/
public class Graph
	{
	
	private HashMap<String, Node> nodeList;
	private HashMap<String, Edge> edgeList;
	
	/*Graph Constructor*/
	public Graph(){
		this.nodeList = new HashMap<String, Node>();
		this.edgeList = new HashMap<String, Edge>();
		}

	/*@Params, takes two nodes, passes them into BFS algorithm, then inserts edges between.
	@modifies finalPath, path
	@returns finalPath*/
	public ArrayList<String> bfs(String rootNode, String endNode){
			ArrayList<String> noPath = new ArrayList<String>();
			noPath.add("");
			if (!validInput(rootNode, endNode)){
				System.out.println("Invalid Inputs, nodes don't exist.");
				return noPath;
			}
			ArrayList<String> path = bfsNode(rootNode, endNode);
			ArrayList<String> finalPath= new ArrayList<String>();
			for(int i=0;i<path.size()-1;i++){
				finalPath.add(path.get(i));
				String temp = stringCon(path.get(i),path.get(i+1));
				finalPath.add((edgeList.get(temp)).edgeName());
			}
			finalPath.add(path.get(path.size()-1));
			return finalPath;
	}

	/*@paras takes two node names as inputs
	@returns used to prevent invalid inputs*/
	public boolean validInput(String nodeOne, String nodeTwo){
		if ((exposeNode(nodeOne) != null) && (exposeNode(nodeTwo)!=null))
			return true;
		else
			return false;
	}

	/*@params concs two strings in special format, 
	GreaterString_SmallerString ex. in 'asdf', 'jkl;' will create 'jkl;_asdf'
	@returns concat str*/
	public String stringCon(String firstNode, String secondNode){
		String temp = firstNode + "_" + secondNode;
		if (secondNode.compareTo(firstNode) > 0)
			temp = secondNode + "_" + firstNode;
		return temp;
	}

	/*@params takes two nodes 
	@returns shortest path between them, implemented using breadthFirstSearch*/
	public ArrayList<String> bfsNode(String rootNode, String endNode){
		// BFS uses Queue data structure
		String start= rootNode;
		String dest = endNode;
		ArrayList<String> empty = new ArrayList<String>();
		empty.add("No possible path");
		ArrayList<String> pathList = new ArrayList<String>();
		HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
		Queue queue = new LinkedList();
		queue.add(exposeNode(start));
		pathList.add(start);
		map.put(start,pathList);
		while(!queue.isEmpty()) {
			Node node = (Node)queue.remove();
			if ((node.getName()).equals(dest)){
				clearNodes();
				return map.get(node.getName());
			}
			pathList = map.get(node.getName());
			for( String child : (node.getReferences()) ) {
				if (exposeNode(child).visited)
					continue;
				exposeNode(child).visited=true;
				ArrayList<String> updatedPath = (ArrayList<String>)pathList.clone();
				updatedPath.add(child);
				map.put(child,updatedPath);
				queue.add(exposeNode(child));
			}
		}
		// Clear visited property of nodes
		clearNodes();
		return empty;
		}



	public void clearNodes(){
		for (String entry : nodeList.keySet()) {
			exposeNode(entry).visited=false;
		}
	}

	/*@returns node with most references to other nodes*/
    public ArrayList mostReferences(){
    	int temp=0;
    	int currentMax=0;
    	ArrayList<String> maxRefs = new ArrayList<String>();
    	for (String entry : nodeList.keySet()) {
    		Node tempNode = nodeList.get(entry);
    		temp = tempNode.numRefs();
    		if (temp > currentMax){
    			currentMax = temp;
    			maxRefs.clear();
    			maxRefs.add((nodeList.get(entry)).getName());
    		}
    		else if (temp == currentMax){
    			maxRefs.add((nodeList.get(entry)).getName());
    		}
    	}
    	return maxRefs;
    } 

    /*@param nodeName, returns node object associated with string*/
    public Node exposeNode(String nodeName){
    	return nodeList.get(nodeName);
    }

     /*@param edgeName, returns edge object associated with string*/
    public Edge exposeEdge(String edgeName){
    	return edgeList.get(edgeName);
    }

    //Method used for test, prints all nodes.
	public void allNodes(){
		for (String entry : nodeList.keySet()) {
			System.out.println(entry);
		}
	}

	//Method used to return all nodes as an ArrayList of Strings
	public ArrayList<String> listOfNodes(){
		ArrayList<String> allNodes = new ArrayList<String>();
		for (String entry : nodeList.keySet()) {
			allNodes.add(entry);
		}
		return allNodes;
	}

	public int degreeCentrality(String nodeName){
		int degree=0;
		int x = 0;
		for (String s : listOfNodes()){
			int temp=(bfs(nodeName, s)).size();
			if (x%100==0)
				System.out.println(x + " nodes have been travelled to");
			x++;
			if (temp > degree){
				degree = temp;
			}
		}
		return degree;
	}

	//Method used for test, prints all edges.
	public void allEdges(){
		for (String entry : edgeList.keySet()) {
			System.out.println(entry);
		}
	}


	/*Creates nodes
	@params takes nodeName and creates node and stores in HashMap
	@modifies hashMap*/
	public void createNode(String nodeName){
		if (this.nodeExists(nodeName)==false){
			nodeList.put(nodeName, new Node(nodeName));
			}
		return;
	}

	/*Joins two nodes using edge specified in parameter
	@params node1, node2, edge
	@modifies Graph object*/
	public void nodeJoin(String firstNode, String secondNode, String connection){
		String temp = firstNode + "_" + secondNode;
		createNode(firstNode);
		createNode(secondNode);
		(nodeList.get(firstNode)).addRef(nodeList.get(secondNode));
		(nodeList.get(secondNode)).addRef(nodeList.get(firstNode));
		if (secondNode.compareTo(firstNode) > 0)
			temp = secondNode + "_" + firstNode;
		if (edgeList.get(temp) == null)
			edgeList.put(temp, new Edge(temp, connection));
		else
			(edgeList.get(temp)).addTitle(connection);
	}
	
/*	@param Takes two nodes, if they are neighbours returns True
	@returns true or false depending on adjacency*/	
	public boolean isAdjacent(String firstNode, String secondNode) throws NullPointerException{
		if (nodeList.get(secondNode) == null || nodeList.get(firstNode) == null)
		{
			System.out.println("Invalid Nodes");
			return false;
		}
		if ((nodeList.get(firstNode)).refExists((nodeList.get(secondNode)).getName())){
			return true;
		}
		else
			return false;
	}	

	/*Checks for hashMap for existence of value associated with nodename Key
	@returns True if exists, false else*/
	public boolean nodeExists(String nodeName){
		if(nodeList.get(nodeName)!=null){
		 	return true;
		}
		return false;
	}

	/*@param takes file, parses, tokenizes and converts to Graph
	@modifies Graph object
	@throws IOException*/
	public void stringToGraph() throws IOException{
		try {
			File file = new File("labeled_edges.tsv");
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				stringBuffer.append(line);
			}
			fileReader.close();
			String temporary = (stringBuffer.toString());

			String[] splitString;
			int x = 0;
			StringTokenizer st = new StringTokenizer(temporary, "\t\"");
			splitString = new String[st.countTokens()];
			while (st.hasMoreTokens()==true){
				splitString[x]=st.nextToken();
				x++;
			}

			//Converts array of Strings into Graph
			String currentBook = "";
			ArrayList<String> currentBookCharacters = new ArrayList<String>();

			for(int i=1;    i<splitString.length;    i += 2){
				if( !splitString[i].equals(currentBook)){
                    String[] charsInBook = new String[currentBookCharacters.size()];
					charsInBook = currentBookCharacters.toArray(charsInBook);
					for(int j=0;j<charsInBook.length; j++){
						for(int k=j+1;  k<charsInBook.length;k++){ 
								nodeJoin(charsInBook[j],charsInBook[k],currentBook);       
						}
					}
					currentBookCharacters.clear();
                    currentBookCharacters.add(splitString[i-1]);
					currentBook = splitString[i];
				}else{
					currentBookCharacters.add(splitString[i-1]);
				} 

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
}
