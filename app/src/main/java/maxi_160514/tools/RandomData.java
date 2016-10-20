package maxi_160514.tools;

import java.util.Random;
/*
 * 功能：处理字符串
 * 作者：马西
 * 时间：2014.9.26
 */

public class RandomData {

	public String getRandomString(int length) { // length表示生成字符串的长度
		String base = "0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}
}
