package kr.or.bit.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class MyUtils {
	private static JsonParser jsonParser = new JsonParser();
	
	public static JsonObject parseStringToJson(String strJson) {
		return (JsonObject)jsonParser.parse(strJson);
	}
}
