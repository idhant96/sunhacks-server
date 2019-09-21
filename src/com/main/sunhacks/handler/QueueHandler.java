package com.main.sunhacks.handler;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.main.sunhacks.db.DataSource;

public class QueueHandler {

	private static Map<Long, Map<String, Integer>> busQueue = new HashMap<>();

	public static JSONObject fetchBuses() throws Exception {
		return DataSource.getBusDetailsAsJSON();
	}

	public static JSONObject addUserToQueue(String beaconUID, long busID) {
		JSONObject responseJSONObj = new JSONObject();
		int lastQueueNo = 0;
		String userUID = "";
		String status = "failed";
		try {
			boolean uidVerified = UIDHandler.verifyBeaconUID(beaconUID);
			if (!uidVerified) {
				throw new Exception("UID Not verified");
			}
			userUID = UIDGenerator.generateUID();
			Map<String, Integer> userToQueueNo = busQueue.get(busID);
			if (userToQueueNo == null) {
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
		} catch (Exception e) {
			e.printStackTrace();
			lastQueueNo = -1;
		}
		responseJSONObj.put("queue_no", lastQueueNo);
		responseJSONObj.put("uid", userUID);
		responseJSONObj.put("status", status);
		return responseJSONObj;
	}

	public static JSONObject getBusUsersToQueueNo(Long busID) {
		JSONObject userToQueueNoJSONObj = new JSONObject();
		Map<String, Integer> userToQueueNo = busQueue.get(busID);
		for(String userUID : userToQueueNo.keySet()) {
			userToQueueNoJSONObj.put(userUID, userToQueueNo.get(userUID));
		}
		return userToQueueNoJSONObj;
	}
	
	public static void userBoarded(Long busID, String userUID) {
		Map<String, Integer> userToQueueNo = busQueue.get(busID);
		userToQueueNo.remove(userUID);
	}
}
