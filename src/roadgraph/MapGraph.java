/**
 * @author UCSD MOOC development team and YOU
 * 
 * A class which reprsents a graph of geographic locations
 * Nodes in the graph are intersections between 
 *
 */
package roadgraph;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Queue;
import java.util.Set;
import java.util.function.Consumer;

import geography.GeographicPoint;
import util.GraphLoader;

/**
 * @author UCSD MOOC development team and YOU
 * 
 * A class which represents a graph of geographic locations
 * Nodes in the graph are intersections between 
 *
 */
public class MapGraph {
	//TODO: Add your member variables here in WEEK 2
	HashMap<GeographicPoint,MapNode> Vertices;
	//List<MapEdge> MAPEDGES = new ArrayList<MapEdge>();
	
	/** 
	 * Create a new empty MapGraph 
	 */
	public MapGraph()
	{
		// TODO: Implement in this constructor in WEEK 2
		Vertices = new HashMap<GeographicPoint,MapNode>();
		
	}
	
	/**
	 * Get the number of vertices (road intersections) in the graph
	 * @return The number of vertices in the graph.
	 */
	public int getNumVertices()
	{
		int num;
		num = Vertices.size();
		return num;
		//TODO: Implement this method in WEEK 2
//		return 0;
	}
	
	/**
	 * Return the intersections, which are the vertices in this graph.
	 * @return The vertices in this graph as GeographicPoints
	 */
	public Set<GeographicPoint> getVertices()
	{
		//TODO: Implement this method in WEEK 2
				Set<GeographicPoint> onlyVertices = Vertices.keySet();
				Set<GeographicPoint> output = new HashSet<GeographicPoint>();
		        for (GeographicPoint v : onlyVertices)
		        {
		            output.add(v);
		        }
		return output;
//		return null;
	}
	
	/**
	 * Get the number of road segments in the graph
	 * @return The number of edges in the graph.
	 */
	public int getNumEdges()
	{
		//TODO: Implement this method in WEEK 2
		int num = 0;
		for(GeographicPoint i:Vertices.keySet()){  
			MapNode temp = (MapNode)Vertices.get(i);
			num = num + temp.getNumEdges();
		   }
		return num;
//		return 0;
	}

	
	
	/** Add a node corresponding to an intersection at a Geographic Point
	 * If the location is already in the graph or null, this method does 
	 * not change the graph.
	 * @param location  The location of the intersection
	 * @return true if a node was added, false if it was not (the node
	 * was already in the graph, or the parameter is null).
	 */
	public boolean addVertex(GeographicPoint location)
	{
		// TODO: Implement this method in WEEK 2
		if(location != null && !Vertices.containsKey(location)){		
			MapNode vertex = new MapNode(location);
			Vertices.put(location, vertex);
			return true;
		}		
		else
			return false;
//		return false;
	}
	
	/**
	 * Adds a directed edge to the graph from pt1 to pt2.  
	 * Precondition: Both GeographicPoints have already been added to the graph
	 * @param from The starting point of the edge
	 * @param to The ending point of the edge
	 * @param roadName The name of the road
	 * @param roadType The type of the road
	 * @param length The length of the road, in km
	 * @throws IllegalArgumentException If the points have not already been
	 *   added as nodes to the graph, if any of the arguments is null,
	 *   or if the length is less than 0.
	 */
	public void addEdge(GeographicPoint from, GeographicPoint to, String roadName,
			String roadType, double length) throws IllegalArgumentException {

		//TODO: Implement this method in WEEK 2
		if(Vertices.containsKey(from) && Vertices.containsKey(to) && length>=0){
			MapEdge edge = new MapEdge(from, to, roadName, roadType, length);
			MapNode temp = (MapNode)Vertices.get(from);
			temp.addEdge(edge);
			Vertices.put(from, temp);
		}
		else
			throw new IllegalArgumentException();
		
		
	}
	

	/** Find the path from start to goal using breadth first search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest (unweighted)
	 *   path from start to goal (including both start and goal).
	 */
	public List<GeographicPoint> bfs(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
        Consumer<GeographicPoint> temp = (x) -> {};
        return bfs(start, goal, temp);
	}
	
	/** Find the path from start to goal using breadth first search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest (unweighted)
	 *   path from start to goal (including both start and goal).
	 */
	public List<GeographicPoint> bfs(GeographicPoint start, 
			 					     GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
	{
		// TODO: Implement this method in WEEK 2
		
		// Hook for visualization.  See writeup.
		//nodeSearched.accept(next.getLocation());
		if(start == null || goal == null){
			System.out.println("Start or goal node is null!  No path exists.");
			return new LinkedList<GeographicPoint>();
		}
		HashSet<GeographicPoint> visited = new HashSet<GeographicPoint>();
		Queue<GeographicPoint> toExplore = new LinkedList<GeographicPoint>();
		HashMap<GeographicPoint, GeographicPoint> parentMap = new HashMap<GeographicPoint, GeographicPoint>();
		toExplore.add(start);
		boolean found = false;
		while (!toExplore.isEmpty()) {
			System.out.println(toExplore.toString());
			System.out.println("visited"+visited.toString());
			System.out.println("parentmap"+parentMap.toString());


			GeographicPoint curr = toExplore.remove();
			if (curr.getX() == goal.getX() && curr.getY() == goal.getY()) {
				found = true;
				break;
			}
			List<MapEdge> neighboredges = Vertices.get(curr).getEdges();
//			for(MapEdge i : neighboredges)
//				System.out.println(i.toString());
//			List<GeographicPoint> neighbors = null;
//			for(MapEdge i : neighboredges){
//				neighbors.add(i.getTo(curr));
//			}
			
			ListIterator<MapEdge> it = neighboredges.listIterator(neighboredges.size());
			while (it.hasPrevious()) {
				GeographicPoint next = it.previous().getTo(curr);
				if (!visited.contains(next)) {
					visited.add(next);
					parentMap.put(next, curr);
					toExplore.add(next);
				}
			}
		}
		if (!found) {
			System.out.println("No path exists");
			return new ArrayList<GeographicPoint>();
		}
		
		// reconstruct the path
				LinkedList<GeographicPoint> path = new LinkedList<GeographicPoint>();
				GeographicPoint curr = goal;
				while (curr != start) {
					path.addFirst(curr);
					curr = parentMap.get(curr);
				}
				path.addFirst(start);
				return path;
	}


		

//		return null;
	
	

	/** Find the path from start to goal using Dijkstra's algorithm
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> dijkstra(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
		// You do not need to change this method.
        Consumer<GeographicPoint> temp = (x) -> {};
        return dijkstra(start, goal, temp);
	}
	
	/** Find the path from start to goal using Dijkstra's algorithm
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> dijkstra(GeographicPoint start, 
										  GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
	{
		// TODO: Implement this method in WEEK 3

		// Hook for visualization.  See writeup.
		//nodeSearched.accept(next.getLocation());
		
		return null;
	}

	/** Find the path from start to goal using A-Star search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> aStarSearch(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
        Consumer<GeographicPoint> temp = (x) -> {};
        return aStarSearch(start, goal, temp);
	}
	
	/** Find the path from start to goal using A-Star search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> aStarSearch(GeographicPoint start, 
											 GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
	{
		// TODO: Implement this method in WEEK 3
		
		// Hook for visualization.  See writeup.
		//nodeSearched.accept(next.getLocation());
		
		return null;
	}

	
	
	public static void main(String[] args)
	{
		System.out.print("Making a new map...");
		MapGraph theMap = new MapGraph();
		System.out.print("DONE. \nLoading the map...");
		GraphLoader.loadRoadMap("data/testdata/simpletest.map", theMap);
		System.out.println("DONE.");
		GeographicPoint start = new GeographicPoint(1,1);
		GeographicPoint goal = new GeographicPoint(8,-1);
		List<GeographicPoint> sol = theMap.bfs( start,  goal);
		for(GeographicPoint i : sol){
			System.out.println(i.toString());
		}
		// You can use this method for testing.  
		
		/* Use this code in Week 3 End of Week Quiz
//		MapGraph theMap = new MapGraph();
		System.out.print("DONE. \nLoading the map...");
		GraphLoader.loadRoadMap("data/maps/utc.map", theMap);
		System.out.println("DONE.");

		GeographicPoint start = new GeographicPoint(32.8648772, -117.2254046);
		GeographicPoint end = new GeographicPoint(32.8660691, -117.217393);
		
		
		List<GeographicPoint> route = theMap.dijkstra(start,end);
		List<GeographicPoint> route2 = theMap.aStarSearch(start,end);

		*/
		
	}
	
}
