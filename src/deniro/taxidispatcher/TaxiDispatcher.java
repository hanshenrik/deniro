package deniro.taxidispatcher;

import java.util.LinkedList;

import com.bitreactive.library.android.maps.model.Position;

import deniro.user.Order;
import no.ntnu.item.arctis.runtime.Block;


public class TaxiDispatcher extends Block {

	public java.util.LinkedList<TaxiQueueItem> registeredTaxis;
	public java.util.LinkedList<TaxiQueueItem> availableTaxis;
	public java.util.LinkedList<Order> pendingOrders;
	public java.lang.String publishTopic;
	public deniro.user.Order tempOrder;

	public TaxiDispatcher() {
		this.registeredTaxis = new LinkedList<TaxiQueueItem>();
		this.availableTaxis = new LinkedList<TaxiQueueItem>();
		this.pendingOrders = new LinkedList<Order>();
	}
	
	public LinkedList<TaxiQueueItem> getAvailableTaxis() {
		return availableTaxis;
	}
	
	public TaxiQueueItem createTaxiQueueItem(String taxiInfo) {
		String[] info = taxiInfo.split(";");
		Position pos = new Position(Double.valueOf(info[1]), Double.valueOf(info[2]));
		return new TaxiQueueItem(info[0], pos);
	}
	
	public void taxiOnDuty(TaxiQueueItem tqi) {
		System.out.println("TaxiDispatcher: "+tqi+" now on duty");
		registeredTaxis.add(tqi);
	}

	public void taxiOffDuty(TaxiQueueItem tqi) {
		for (TaxiQueueItem t : availableTaxis) {
			if (tqi.getTaxiID().equals(t.getTaxiID())) {
				availableTaxis.remove(t);
				registeredTaxis.remove(t);
			}
		}
		
		System.out.println("TaxiDispatcher: "+tqi+" now off duty (and unavailable)");
	}
	
	public Order taxiAvailable(TaxiQueueItem tqi) {
		System.out.println("TaxiDispatcher: "+tqi+" now available");
		availableTaxis.add(tqi);
		
		if (!pendingOrders.isEmpty()) {
			Order order = pendingOrders.pop();
			order.setPlaceInQueue(0);
			return order;
		}
		return null;
	}
	
	public String taxiUnavailable(Order order) {
		System.out.println("TaxiDispatcher: "+order.getTaxiID()+" now unavailable");
		
		for (TaxiQueueItem t : availableTaxis) {
			if (order.getTaxiID().equals(t.getTaxiID())) {
				availableTaxis.remove(t);
			}
		}
		
		if (order.getUserID() == null) {
			System.out.println("TaxiDispatcher: noReject");
			return "noReject"; // no need to do anything more; is not handled as case in diagram
		}
		
		if (!availableTaxis.isEmpty()) {
			return "assignTaxi";
		} else {
			order.setMessage("No taxis available at the moment, see queue number...");
			
			for (Order o : pendingOrders) {
				if (o.getUserID().equals(order.getUserID())) {
					order.setPlaceInQueue(pendingOrders.indexOf(o));
				}
			}
			
			this.tempOrder = order; 
			System.out.println("TaxiDispatcher: queueing "+order.getOrderInfo());
			return "queue";
		}
	}
	
	public Order taxiConfirmed(Order order) {
		System.out.println("TaxiDispatcher: taxi "+order.getTaxiID()+" confirming "+order.getOrderInfo());
		
		for (Order o : pendingOrders) {
			if (o.getUserID().equals(order.getUserID())) {
				pendingOrders.remove(o);
			}
		}
		
		order.setMessage("A taxi is on it's way!");
		
		return order;
	}
	
	public String requestHandler(Order order) {
		System.out.println("TaxiDispatcher: creating "+order.getOrderInfo());
		
		for (Order o : pendingOrders) {
			if (o.getUserID().equals(order.getUserID())) {
				pendingOrders.remove(o);
			}
		}
		
		pendingOrders.add(order);
		order.setPlaceInQueue(pendingOrders.indexOf(order));
		
		if (!availableTaxis.isEmpty()) {
			System.out.println("TaxiDispatcher: taxis are available!");
			return "assignTaxi";
		} else {
			order.setMessage("No taxis available at the moment, see queue number...");
			return "queue";
		}
	}
	
	public int numberOfAvailableTaxis() {
		System.out.println("TaxiDispatcher: number of avail taxis: "+availableTaxis.size());
		return availableTaxis.size();
	}
	
	public Order assignTaxi(Order order) {
		order.setTaxiID(availableTaxis.pop().getTaxiID());
		order.setMessage("Hang on a minute, contacting a taxi now...");
		System.out.println("TaxiDispatcher: assigned "+order.getTaxiID()+" to order "+order.getUserID());
		return order;
	}
	
	public Order assignSpecificTaxi(Order order, TaxiQueueItem tqi) {
		order.setTaxiID(tqi.getTaxiID());
		order.setMessage("Hang on a minute, contacting a taxi now...");
		
		System.out.println("order: "+order );
		System.out.println("tqi: "+tqi);
		System.out.println("availableTaxis: "+availableTaxis);
		for (TaxiQueueItem t : availableTaxis) {
			if (tqi.getTaxiID().equals(t.getTaxiID())) {
				availableTaxis.remove(t);
			}
		}
		
		System.out.println("TaxiDispatcher: assigned specific "+order.getTaxiID()+" to order "+order.getUserID());
		return order;
	}
	
	public Order requestCancelled(Order order) {
		System.out.println("TaxiDispatcher: cancelling "+order.getOrderInfo());
		// not possible after taxi has confirmed!
		
		for (TaxiQueueItem tqi : registeredTaxis) {
			if (tqi.getTaxiID().equals(order.getTaxiID())) {
				availableTaxis.add(tqi);
			}
		}
		
		for (Order o : pendingOrders) {
			if (o.getUserID().equals(order.getUserID())) {
				pendingOrders.remove(o);
			}
		}
		
		order.setMessage("Order cancelled!");
		
		return order;
	}

	public String getUserID(Order order) {
		return order.getUserID();
	}
	
	public String getTaxiID(Order order) {
		return order.getTaxiID();
	}

}
