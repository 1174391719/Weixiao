package maxi_160514.tools;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Tools {

	/*public String jsonArrayToObjString(JSONArray jsonArray, String key) {// ***********查找jsonArray的目标值
		String value = null;
		JSONObject json_data = null;

		for (int i = 0; i < jsonArray.length(); i++) {
			try {
				json_data = jsonArray.getJSONObject(i);
				value = json_data.getString(key);
			} catch (Exception e) {
			}
		}

		return value;

	}

	public String replaceString(int direct, String str) {// *********************************字符替换

		if (direct == 0) {

			while (true) {
				int flag = str.indexOf("-");
				if (flag == -1) {// 没有-了
					break;
				} else {// 有-
					if (str.substring(flag + 3, flag + 4).endsWith("-")) {// 是日期
						if (flag + 13 < str.length()) {
							if (str.substring(flag + 6, flag + 7).endsWith(" ")
									&& str.substring(flag + 9, flag + 10)
											.endsWith(":")
									&& str.substring(flag + 12, flag + 13)

									.endsWith(":")) {// System.out.println("-------进来了----:");

								String temp1 = str.substring(0, flag);// System.out.println("------temp1----:"+temp1);
								String temp2 = str
										.substring(flag + 1, flag + 3);// System.out.println("------temp2----:"+temp2);
								String temp3 = str
										.substring(flag + 4, flag + 6);// System.out.println("------temp3----:"+temp3);
								String temp4 = str
										.substring(flag + 7, flag + 9);// System.out.println("------temp4----:"+temp4);
								String temp5 = str.substring(flag + 10,
										flag + 12);// System.out.println("------temp5----:"+temp5);
								String temp6 = str.substring(flag + 13);// System.out.println("------temp6----:"+temp6);
								str = temp1 + temp2 + temp3 + temp4 + temp5
										+ temp6;
							}
						} else {
							break;
						}

					} else {// 不是日期

						str = str.substring(0, flag) + "henggang"
								+ str.substring(flag + 1);

					}

				}
			}
			str = str.replace("\"", "");
			str = str.replace("/", "slash");
			str = str.replace(" ", "space");
			str = str.replace("\n", "enter");
			str = str.replace("\r", "3333");
			str = str.replace("\r\n", "3333");
			str = str.replace("\t", "555");
			str = str.replace("\\s*", "522225");

			// while(true){

			// }

		}
		if (direct == 1) {
			str = str.replace("slash", "/");
			str = str.replace("space", " ");
			str = str.replace("enter", "\n");
			str = str.replace("henggang", "-");

		}

		return str;

	}

	public ArrayList<NameValuePair> mapToNameValuePairs(Map<String, String> map) {// ****************构造nameValuePairs对象
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		Set<String> k = map.keySet();
		for (String key : k) {
			nameValuePairs.add(new BasicNameValuePair(key, map.get(key)));
		}
		return nameValuePairs;
	}

	public Map<String, String> stringToName(String str, String mark) {// ****************字符串转换换位map对象
		Map<String, String> map = new HashMap<String, String>();
		String temp = null;

		int flag = -1;
		int num = 0;
		while (true) {
			flag = str.indexOf(mark);
			if (flag == -1) {// 没有数据了
				break;
			} else {

				temp = str.substring(0, flag);
				str = str.substring(flag + 1);
				map.put(num + "", temp);
				System.out.println("num---------------" + num);
				num++;

			}

		}

		return map;
	}

	public String stringToTime(String str) {

		String temp = null;
		// System.out.println("str---------------" + str);
		temp = str.substring(4, 6) + "-" + str.substring(6, 8) + " "
				+ str.substring(8, 10) + ":" + str.substring(10, 12);
		// System.out.println("temp---------------" + temp);

		return temp;
	}

	public void writeFileSdcardFile(String fileName, String write_str) {
		try {

			FileOutputStream fout = new FileOutputStream(fileName);

			byte[] bytes = write_str.getBytes();

			fout.write(bytes);

			fout.close();

		}

		catch (Exception e) {

			e.printStackTrace();

		}

	}

	// 读SD中的文件

	public String readFileSdcardFile(String fileName) {

		String res = "";

		try {

			FileInputStream fin = new FileInputStream(fileName);

			int length = fin.available();

			byte[] buffer = new byte[length];

			fin.read(buffer);

			res = EncodingUtils.getString(buffer, "UTF-8");

			fin.close();

		}

		catch (Exception e) {

			e.printStackTrace();

		}

		return res;

	}

	public boolean isJson(String str) {

		try {

			JSONArray jsonArray = new JSONArray("[{key:" + str + "}]");
			return true;

		} catch (Exception e) {
			return false;
		}

	}*/

	public static boolean isNetworkAvailable(Context context) {
		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity != null) {
			NetworkInfo info = connectivity.getActiveNetworkInfo();
			if (info != null && info.isConnected())
			{
				// 当前网络是连接的
				if (info.getState() == NetworkInfo.State.CONNECTED)
				{
					// 当前所连接的网络可用
					return true;
				}
			}
		}
		return false;
	}



}
