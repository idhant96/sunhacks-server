package com.main.sunhacks.action;

import java.io.UnsupportedEncodingException;

import com.main.sunhacks.handler.QueueHandler;
import com.main.sunhacks.util.Util;
import com.opensymphony.xwork2.Action;

public class QueueAction {
	
	private String beaconUID;
	private Long busID;
	private String buses;
	private String response;
	private String userUID;

	
	public String fetchBuses() {
		try {
			buses = Util.encodeData(QueueHandler.fetchBuses().toString());
		} catch(Exception e) {
			buses = "Standard";
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	public String addUserToQueue() {
		try {
			response = Util.encodeData(QueueHandler.addUserToQueue(beaconUID, busID).toString());
		} catch (UnsupportedEncodingException e) {
			response = "Failed";
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}

	public String fetchUserQueue() {
		try {
			response = Util.encodeData(QueueHandler.getBusUsersToQueueNo(busID).toString());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			response = "Failed to bus information";
		}
		return Action.SUCCESS;
	}
	
	public String requeue() {
		try {
			QueueHandler.requeue(busID);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}

	public String userBoarded() {
		QueueHandler.userBoarded(busID, userUID);
		return Action.SUCCESS;
	}

	public String getBuses() {
		return buses;
	}
	public void setBuses(String buses) {
		this.buses = buses;
	}

	public Long getBusID() {
		return busID;
	}

	public void setBusID(Long busID) {
		this.busID = busID;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getBeaconUID() {
		return beaconUID;
	}

	public void setBeaconUID(String beaconUID) {
		this.beaconUID = beaconUID;
	}

	public String getUserUID() {
		return userUID;
	}

	public void setUserUID(String userUID) {
		this.userUID = userUID;
	}

}
