marveluniverseBFS
=================
This is a tool that can find the shortest path between any two nodes in a graph. It is customized to find the shortest lexicographic path between any two nodes (marvel characters) in a graph. It utilizes the BFS algorithm. It can be easily adapted to generate a graph that is based on different nodes and edges.

Instructions:
1. To run this program, run the main.class file using java.
2. It will shortly calculate the most central character and prompt you if you want to find his/her degree of centrality. To do so requires slighlty more time, as execution time increases exponentially with the size of the graph. If you choose to find the degree of centrality, the BFS algorithm will run through ~6400 times.
3. To find the shortest path between any two characters, you will be prompted for the name of the characters. They must be entered EXACTLY as they appear in the labeled_edges.tsv file, except without quotations.
