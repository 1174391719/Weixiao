package maxi_160514.picture;

import android.graphics.Bitmap;

import maxi_160514.picture.MemoryCache;

public class PicDeal {
	private Bitmap btCache = null;
	private MemoryCache cache = null;

	public PicDeal() {
		cache = new MemoryCache();
	}

	/*public Bitmap isPicSd(String url, String name) {// 检查图片是否存在
		Bitmap bt = null;
		if (constants.sdOpera.fileIsExists(url, name) == true) {// 本地存在图片
			bt = BitmapFactory.decodeFile(url + name, null);
		}
		return bt;
	}

	public void downPic(String url, String name, Handler han, Bitmap bt[],
			int id) {// 下载图片
		FtpServer fs = new FtpServer();// 下载头像
		fs.downPic(bt, han, url, name, id);
		System.out.println("-----------下载头像去了");

	}

	public void getBitmap(Handler han, handlerId hanId, String path) {// 获取位图
		btCache = cache.get(path);
		if (btCache != null) {
			Message message = new Message();
			message.arg1 = hanId.cor;
			message.obj = btCache;
			han.sendMessage(message);
		} else {
			getBitmapFromSD(han, hanId, path);
		}
	}

	// ************************************************************************************************************内部类
	public void getBitmapFromSD(final Handler han, final handlerId hanId,
			final String path) {
		new Thread() {
			public void run() {

				BitmapFactory.Options options = new BitmapFactory.Options();
				options.inSampleSize = 20;
				Message msg = new Message();
				Bitmap bt = BitmapFactory.decodeFile(path, options);
				if (bt != null) {
					cache.put(path, bt);// 添加到缓存中
					msg.arg1 = hanId.cor;
					msg.obj = bt;
				} else {
					msg.arg1 = hanId.err;
				}

				han.sendMessage(msg);
			}
		}.start();
	}*/

}
