package com.main.sunhacks.handler;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.json.JSONObject;

import com.main.sunhacks.db.DataSource;

public class QueueHandler {
	private static Map<Long, Map<String, Integer>> busQueue = new HashMap<>();

	public static JSONObject fetchBuses() throws Exception {
		return DataSource.getBusDetailsAsJSON();
	}

	public static JSONObject addUserToQueue(String beaconUID, long busID) {
		System.out.println("2. BUSES QUEUE " + busQueue);
		JSONObject responseJSONObj = new JSONObject();
		int lastQueueNo = 0;
		String userUID = "";
		String status = "failed";
		boolean entry = true;
		try {
			boolean uidVerified = UIDHandler.verifyBeaconUID(beaconUID);
			if (!uidVerified) {
				throw new Exception("UID Not verified");
			}
			System.out.println("Beacon UID " + beaconUID + uidVerified );
			userUID = UIDGenerator.generateUID();
			Map<String, Integer> userToQueueNo = busQueue.get(busID);
			if (userToQueueNo == null) {
				System.out.println("New user for bus");
				userToQueueNo = new HashMap<String, Integer>();
				busQueue.put(busID, userToQueueNo);
			}

			for (String queueUserUID : userToQueueNo.keySet()) {
				int queueNo = userToQueueNo.get(queueUserUID);
				if (queueNo > lastQueueNo) {
					lastQueueNo = queueNo;
				}
			}
			userToQueueNo.put(userUID, ++lastQueueNo);
			status = "success";
			if(status.equals("success")) {
				responseJSONObj.put("queue_no", lastQueueNo);
				responseJSONObj.put("uid", userUID);
				int capacity = DataSource.getBusCapacity(busID);
				responseJSONObj.put("capacity", capacity);
				if(lastQueueNo > capacity) {
					entry = false;
				}
				
			}
			//busQueue.put(busID, userToQueueNo);
		} catch (Exception e) {
			e.printStackTrace();
			lastQueueNo = -1;
			entry = false;
		}
		
		responseJSONObj.put("status", status);
		responseJSONObj.put("entry", entry);
		return responseJSONObj;
	}

	public static void resetBus(Long busID) {
		System.out.println("Beofore reset for bus " + busID + " : " + busQueue.get(busID));
		Map<String, Integer> uq = new HashMap<>();
		busQueue.put(busID, uq);
		System.out.println("After reset for bus " + busID + " : " + busQueue.get(busID));
	}

	public static int getBusAvailability(Long busID) throws Exception {
		System.out.println("Checking availability for busid " + busID + " " + busQueue);
		int capacity = DataSource.getBusCapacity(busID);
		Map<String, Integer> userToQueueNo = busQueue.get(busID);
		if (userToQueueNo == null) {
			System.out.println("New user for bus");
			userToQueueNo = new HashMap<String, Integer>();
			busQueue.put(busID, userToQueueNo);
		}
		int lastQueueNo = 0;
		for (String queueUserUID : userToQueueNo.keySet()) {
			int queueNo = userToQueueNo.get(queueUserUID);
			if (queueNo > lastQueueNo) {
				lastQueueNo = queueNo;
			}
		}
		System.out.println("Capacity is " + capacity + " lastQueueNo " + lastQueueNo );
		if(lastQueueNo >= capacity) {
			return 0;
		} else {
			return capacity - lastQueueNo;
		}
	}

	public static JSONObject getBusUsersToQueueNo(Long busID) {
		System.out.println("1. BUSES data " + busQueue);
		System.out.println("GET ALL USER TO QUEUE NO FOR " + busID);
		JSONObject userToQueueNoJSONObj = new JSONObject();
		Map<String, Integer> userToQueueNo = busQueue.get(busID);
		System.out.println(userToQueueNo);
		if(userToQueueNo == null) {
			userToQueueNo = new HashMap<>();
		}
		for(String userUID : userToQueueNo.keySet()) {
			userToQueueNoJSONObj.put(userUID, userToQueueNo.get(userUID));
		}
		return userToQueueNoJSONObj;
	}
	
	public static void userBoarded(Long busID, String userUID) {
		Map<String, Integer> userToQueueNo = busQueue.get(busID);
		userToQueueNo.remove(userUID);
	}
	
	public static void requeue(Long busID) {
		Map<String, Integer> userToQueueNo = busQueue.get(busID);
		TreeMap<Integer, String> sortingQueue = new TreeMap<>();
		for(String userUID : userToQueueNo.keySet()) {
			int queueNo = userToQueueNo.get(userUID);
			sortingQueue.put(queueNo, userUID);
		}
		int i = 1;
		userToQueueNo = new HashMap<>();
		for(Integer queueNo : sortingQueue.keySet()) {
			String userUID = sortingQueue.get(queueNo);
			userToQueueNo.put(userUID, i++);
		}
		busQueue.put(busID, userToQueueNo);
	}
}
