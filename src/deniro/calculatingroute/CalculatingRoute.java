package deniro.calculatingroute;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import deniro.taxidispatcher.TaxiQueueItem;
import deniro.user.Order;
import no.ntnu.item.arctis.runtime.Block;

public class CalculatingRoute extends Block {
	
	private static final String ENC = "UTF-8";
	public java.util.LinkedList<deniro.taxidispatcher.TaxiQueueItem> availableTaxis;

	public String createURI(LinkedList<TaxiQueueItem> taxiQueue,Order order) {
		System.out.println("CalculatingRoute...");
		String origins ="";
		String toAddress = order.getAddress();
		for(TaxiQueueItem tqi:taxiQueue){
			origins += ""+tqi.getPosition().getLatitude()/1000000+","+tqi.getPosition().getLongitude()/1000000+"%7C";
		}
		try {
			System.out.println("trying: "+origins);
			origins += URLEncoder.encode(origins, ENC);
			toAddress = URLEncoder.encode(toAddress, ENC);
		} catch (UnsupportedEncodingException e) {
			// Deprecated code
			origins = URLEncoder.encode(origins);
			toAddress = URLEncoder.encode(toAddress);
		}
		
		return "http://maps.googleapis.com/maps/api/distancematrix/json?origins="+origins+"&destinations="+toAddress+"&mode=driving&sensor=false";
	}

	public TaxiQueueItem getNearestTaxi(String result) {
		System.out.println("##############\n"+result);
		
		int closest = Integer.MAX_VALUE;
		int index = 0;
		
		Pattern pattern = Pattern.compile("duration.*\"value\" : (\\d*)");
		Matcher matcher = pattern.matcher(result);
		
		if (matcher.find()) {
			for (int i = 1; i < matcher.groupCount(); i++) {
				if (Integer.valueOf(matcher.group(i)) < closest) {
					index = i;
				}
			}
		}
		
		return availableTaxis.get(index);
	}

	public String printMe(String s) {
		System.out.println("CalculatingRoute: "+s);
		return s;
	}

}
