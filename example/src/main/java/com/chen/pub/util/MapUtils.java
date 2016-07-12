package com.chen.pub.util;

import java.util.Map;

public class MapUtils {

	public static String getString(Map<String, Object> map, String string) {
		if (map.get(string) != null) {
			map.get(string).toString();
		}

		return null;
	}

	public static long getLongValue(Map<String, Object> map, String string) {
		if (map.get(string) != null) {
			return Long.parseLong(map.get(string).toString());
		}
		return 0;
	}
}
