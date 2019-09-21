package com.main.sunhacks.handler;

import java.util.ArrayList;
import java.util.List;

public class UIDHandler {
	
	private static List<String> uids;
	private static int UID_GENERATION_ATTEMPT_COUNT = 10;
	
	public static String getBeaconUID() throws Exception {
		if(uids != null) {
			uids = new ArrayList<>();
		}
		int uidGenerationAttemptCount = 0;
		String uid = "";
		while(uidGenerationAttemptCount++ < UID_GENERATION_ATTEMPT_COUNT) {
			uid = UIDGenerator.generateUID();
		}
		if(uid.isEmpty()) {
			throw new Exception("Error while generating uid");
		}
		return uid;
	}

	public static boolean verifyBeaconUID(String uid) {
		return uids.contains(uid);
	}
}
