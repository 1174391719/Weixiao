package maxi_160514.picture;


public class PictureHelp{
	private MemoryCache cache = null;
	private PictureHelp me=this;

	public PictureHelp() {
		cache=new MemoryCache();
	}
/*
	public Bitmap isPicSd(String url, String name) {// 检查图片是否存在
		Bitmap bt = null;
		if (constants.sdOpera.fileIsExists(url, name) == true) {// 本地存在图片
			bt = BitmapFactory.decodeFile(url + name, null);
		}
		return bt;
	}

	public void downPic(final String url, final String name, final Handler han, final Bitmap bt[],///*****************下载图片
			final int id,final int location) {// 下载图片
	/*	FtpServer fs = new FtpServer();// 下载头像
		fs.downPic(bt, han, url, name, id);
		System.out.println("-----------下载头像去了");*/
		
	/*	new Thread(){
			public void run(){
				FtpServer fs = new FtpServer();// 下载头像
				Bitmap bp=fs.loadPic(bt, han, url, name, id,location);
		//		fs.loadPic(bt, han, url, name, id, me);			
			}
		}.start();

	}*/
	
	/*public void loadPic(final String url, final String name, final Handler han, final Bitmap bt[],///*****************下载图片
			final int id,final int location,final LoadPicCallBack dpc) {// 下载图片
	/*	FtpServer fs = new FtpServer();// 下载头像
		fs.downPic(bt, han, url, name, id);
		System.out.println("-----------下载头像去了");*/
		
	/*	new Thread(){
			public void run(){
				FtpServer fs = new FtpServer();// 下载头像
				Bitmap bp=fs.loadPic(bt, han, url, name, id,location);
		//		fs.loadPic(bt, han, url, name, id, me);\
				dpc.loadPicCallBack(bp,location);
					
			}
		}.start();

	}
	
	public interface LoadPicCallBack{
		public void loadPicCallBack(Bitmap bp, int location);
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
	
	public void getAllPicPath(final picListcallBack pcb) {// *************获取所有图片

		final String path = Environment.getExternalStorageDirectory().getPath();
		new Thread() {
			public void run() {
				getPicPath(pcb, path);
			}
		}.start();
	}


	// ************************************************************************************************内部类
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
	}
	private void getPicPath(picListcallBack pcb, String path) {

		File file = new File(path);
		if (file.isDirectory()) {
			File fs[] = file.listFiles();
			if (fs != null) {

				if (fs.length > 0) {
					Album na = new Album();
					boolean isPicExist = false;
					for (int i = 0; i < fs.length; i++) {

						if (fs[i].isFile()) {

							String pa = fs[i].getAbsoluteFile().toString();
							if (pa.endsWith(".jpg")) {// 一.jpg结尾的

								String str = pa.substring(pa.indexOf("."));// *不是隐藏文件
								if (str.indexOf("/") == -1) {
									isPicExist = true;
									if (na.getName() == null) {

										String st = path;
										int flag = st.indexOf("/");
										while (flag != -1) {
											st = st.substring(flag + 1);
											flag = st.indexOf("/");

										}
										na.setName(st);
									}
									
						
									MPicture mPicture=new MPicture();
									mPicture.setPath(	fs[i].getAbsolutePath().toString());
									na.getPicList().add(mPicture);

								}

							}

						} else if (fs[i].isDirectory()) {// 是文件夹

							String str = fs[i].getAbsolutePath().toString();// *不是隐藏文件夹
							if (str.indexOf(".") == -1) {
								getPicPath(pcb, fs[i].getAbsolutePath());

							}

						}

					}

					if (isPicExist == true)
						pcb.back(na);

				}
			}
		}
	}

	public interface picListcallBack {// 定义回调接口
		public void back(Album na);
	}



	*/

	

	
}
