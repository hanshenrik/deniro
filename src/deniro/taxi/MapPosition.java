package deniro.taxi;

public class MapPosition {

	public String alias_ID;
	public String position;
	
	public MapPosition(String alias, String position){
		this.alias_ID = alias;
		this.position = position;
	}
	
	public String getAlias_ID() {
		return alias_ID;
	}
	public void setAlias_ID(String alias_ID) {
		this.alias_ID = alias_ID;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	
	
}
