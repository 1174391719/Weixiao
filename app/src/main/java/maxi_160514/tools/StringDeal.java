package maxi_160514.tools;

/*
 * 功能：处理字符串
 * 作者：马西
 * 时间：2014.9.26
 */

public class StringDeal {
	public String timestampToDate(String str) {// 提取timestamp中的日期
		String temp = str.substring(5, 10);
		return temp;
	}

	public String timestampToTime(String str) {// *******************
												// 提取timestamp中的时间
		String temp = str.substring(11, 16);
		return temp;
	}

	public int stringToInt(String str) {// ******************************字符型转整形
		int temp = 0;// 整形缓存
		int length = str.length();// 字符串长度
		int timesTemp = 1;// 扩大倍数缓存

		for (int i = 0; i < length; i++) {

			temp = temp + charToInt(str.substring(length - i - 1, length - i))
					* timesTemp;
			timesTemp = timesTemp * 10;
		}
		return temp;
	}
	public int hexStringToTenString(String str) {

		int temp = 0;

		int length = str.length();
		
		for (int i = length - 1; i >= 0; i--) {
			System.out.println("-------charToInt(str.substring(i, i + 1))----"
					+ charToInt(str.substring(i, i + 1)));
			temp = temp + charToInt(str.substring(i, i + 1))
					* power(16, length - i - 1);

		}

		return temp;

	}

	public int power(int x, int y) {
		int temp = 1;
		if (y == 0) {
			return 1;
		}
		for (int i = 0; i < y; i++) {
			temp = temp * x;
		}
		return temp;

	}


	public int charToInt(String str) {// ******************************字符型转整形
		if (str.equals("0")) {
			return 0;
		}
		if (str.equals("1")) {
			return 1;
		}
		if (str.equals("2")) {
			return 2;
		}
		if (str.equals("3")) {
			return 3;
		}
		if (str.equals("4")) {
			return 4;
		}
		if (str.equals("5")) {
			return 5;
		}
		if (str.equals("6")) {
			return 6;
		}
		if (str.equals("7")) {
			return 7;
		}
		if (str.equals("8")) {
			return 8;
		}
		if (str.equals("9")) {
			return 9;
		}
		if (str.equals("A")) {
			return 10;
		}
		if (str.equals("B")) {
			return 11;
		}
		if (str.equals("C")) {
			return 12;
		}
		if (str.equals("D")) {
			return 13;
		}
		if (str.equals("E")) {
			return 14;
		}
		if (str.equals("F")) {
			return 15;
		}
		return 0;

	}
}
