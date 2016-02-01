package roadgraph;

import geography.GeographicPoint;

import java.util.ArrayList;
import java.util.List;
public class MapNode {

	GeographicPoint location;
	List<MapEdge> MAPEDGES = new ArrayList<MapEdge>();
	
	public MapNode(GeographicPoint location)
	{
		this.location = location;
	}
	
	public void addEdge(MapEdge edge){
		MAPEDGES.add(edge);
	}
	public int getNumEdges(){
		
		return MAPEDGES.size();
		
	}
	public List<MapEdge> getEdges(){
		List<MapEdge> MAPEDGESCOPY = MAPEDGES;
		return MAPEDGESCOPY;
	}
}
