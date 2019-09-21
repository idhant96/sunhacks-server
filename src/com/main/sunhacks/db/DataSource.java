package com.main.sunhacks.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONObject;

import com.main.sunhacks.pojo.Bus;

public class DataSource {

	public static JSONObject getBusDetailsAsJSON() throws Exception {
		String query = SqlQueries.SELECT_BUSES;
		Connection con = DataSourceConnector.getConnection();
		Statement stm = con.createStatement();
		ResultSet rs = null;
		JSONObject busesJSONObj = new JSONObject();
		JSONArray busesArr = new JSONArray();
		try {
			rs = stm.executeQuery(query);
			while (rs.next()) {
				String name = rs.getString("name");
				Long id = rs.getLong("id");
				String fromDestination = rs.getString("from_destination");
				String toDestination = rs.getString("to_destination");
				int capacity = rs.getInt("capacity");
				Bus bus = new Bus();
				bus.setId(id);
				bus.setName(name);
				bus.setFromDestination(fromDestination);
				bus.setToDestination(toDestination);
				bus.setCapacity(capacity);
				busesArr.put(bus.getAsJSONObject());
			}
		} finally {
			if (rs != null) {
				rs.close();
			}
		}
		busesJSONObj.put("buses", busesArr);
		return busesJSONObj;
	}

	public static int getBusCapacity(Long busID) throws Exception {
		String query = SqlQueries.FETCH_BUS_CAPACITY;
		query = String.format(query, busID);
		Connection con = DataSourceConnector.getConnection();
		Statement stm = con.createStatement();
		ResultSet rs = null;
		int capacity = 50;
		try {
			rs = stm.executeQuery(query);
			while (rs.next()) {
				rs.getInt("capacity");
			}
		} finally {
			if(rs != null) {
				rs.close();
			}
		}
		return capacity;
	}
	
	public static Bus getBusDetails(Long busID) throws Exception {
		String query = SqlQueries.SELECT_BUSES;
		Connection con = DataSourceConnector.getConnection();
		Statement stm = con.createStatement();
		ResultSet rs = stm.executeQuery(query);
		while(rs.next()) {
			String name = rs.getString("name");
			Long id = rs.getLong("id");
			String fromDestination = rs.getString("from_destination");
			String toDestination = rs.getString("toDestination");
			int capacity = rs.getInt("capacity");
			Bus bus = new Bus();
			bus.setId(id);
			bus.setName(name);
			bus.setFromDestination(fromDestination);
			bus.setToDestination(toDestination);
			bus.setCapacity(capacity);
			return bus;
		}
		rs.close();
		return null;
	}
}
