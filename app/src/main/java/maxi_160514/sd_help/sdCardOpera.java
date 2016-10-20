package maxi_160514.sd_help;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import android.graphics.Bitmap;

public class sdCardOpera {
	public boolean fileIsExists(String url, String name) {// ************检查文件是否存在
		try {
			File f = new File(url + name);
			if (!f.exists()) {
				return false;
			}

		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public void saveBitmap(String url, Bitmap bitmap, String picName) {// ***************将bitmap保存到sd卡中
		File f = new File(url + picName);
		try {
			f.createNewFile();
		} catch (IOException e) {
		}
		FileOutputStream fOut = null;
		try {
			fOut = new FileOutputStream(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
		} catch (Exception e) {
		}

		try {
			fOut.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			fOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
