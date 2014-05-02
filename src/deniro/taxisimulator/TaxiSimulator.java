package deniro.taxisimulator;

import com.bitreactive.library.android.maps.model.MapUpdate;
import com.bitreactive.library.android.maps.model.Marker;
import com.bitreactive.library.android.maps.model.Polyline;
import com.bitreactive.library.android.maps.model.Position;

import deniro.taxi.MapPosition;
import no.ntnu.item.arctis.runtime.Block;
import no.ntnu.item.ttm4115.simulation.routeplanner.Journey;
import no.ntnu.item.ttm4115.simulation.routeplanner.Route;

public class TaxiSimulator extends Block {
	
	private String curPos = "Elgesetergate 1";
	public java.lang.String taxiAlias;
	public java.lang.String destination = "Sunnlandsvegen 64";
	
	public Journey makeJourney(){
		Journey j = new Journey(taxiAlias, curPos,destination);
		return j;
	}

	public Route printRoute(Route r) {
		for (int i = 0; i < r.legs.get(0).steps.size(); i++) {
			float lat = (float) (r.legs.get(0).steps.get(i).endLocation.latitude*1E6);
			float lng = (float) (r.legs.get(0).steps.get(i).endLocation.longitude*1E6);
			System.out.println("p.addPoint(new Position p"+i+1+"("+lat+","+lng+")");
		}
		return r;
	}

	public MapUpdate addPolyline(MapUpdate m){
		Polyline p1 = new Polyline(taxiAlias);
		p1.hue(Marker.HUE_VIOLET);
		p1.addPoint(new Position (6.3422984E7,1.0394329E7));
		p1.addPoint(new Position (6.3424928E7,1.0393792E7));
		p1.addPoint(new Position (6.3427508E7,1.0393112E7));
		p1.addPoint(new Position (6.3427696E7,1.0396566E7));
		p1.addPoint(new Position (6.3428928E7,1.0395916E7));
		p1.addPoint(new Position (6.3429064E7,1.0392618E7));
		p1.addPoint(new Position (6.3424928E7,1.0393792E7));
		p1.addPoint(new Position (6.3422984E7,1.0394329E7));
		p1.addPoint(new Position (6.3414984E7,1.039808E7));
		p1.addPoint(new Position (6.3408608E7,1.0398607E7));
		p1.addPoint(new Position (6.3408376E7,1.0401408E7));
		p1.addPoint(new Position (6.3408696E7,1.0403184E7));
		p1.addPoint(new Position (6.3407896E7,1.0403862E7));
		p1.addPoint(new Position (6.340548E7,1.0407723E7));
		p1.addPoint(new Position (6.3405152E7,1.0409483E7));
		p1.addPoint(new Position (6.3404756E7,1.0406923E7));
		p1.addPoint(new Position (6.3404808E7,1.0404997E7));
		m.addPolyline(p1);
		return m;
	}
}