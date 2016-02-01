package roadgraph;

import geography.GeographicPoint;
public class MapEdge {

	GeographicPoint from;
	GeographicPoint to;
	String roadName;
	String roadType;
	double length;
	
	public MapEdge(GeographicPoint from, GeographicPoint to, String roadName,
			String roadType, double length)
	{
		this.from = from;
		this.to = to;
		this.roadName = roadName;
		this.roadType = roadType;
		this.length = length;
	}
	
	public GeographicPoint getFrom(GeographicPoint to){
		
		if(to != from){
			return from;
		}
		return null;
	}
	
	public GeographicPoint getTo(GeographicPoint from){
			
			if(to != from){
				return to;
			}
			return null;
		}

	public String getRoadName() {
		return roadName;
	}

	public String getRoadType() {
		return roadType;
	}

	public double getLength() {
		return length;
	}
	 public String toString()
	    {
	    	return "RoadName: " + getRoadName() + ", RoadType: " +  getRoadType();
	    }
}
