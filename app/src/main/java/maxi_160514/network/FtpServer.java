package maxi_160514.network;

/*
 * 功能：提供ftp服务
 * 作者：马西
 * 时间：2014.09.22*/

public class FtpServer {

/*	private String serverUrl = constants.serverUrl;// 服务器地址
	private int port = constants.serverPort;// 服务器端口号
	private String userName = constants.serverUserName;// 用户名
	private String password = constants.serverPassword;// 密码
	private String serverPath = null;// 服务器端的存储路径

	// private String picPath = null;// 图片地址
	// private String newName = null;// 图片新名字

	public void uploadPic(String serverUri, final String picPath,
			final String newName) {// *******************************************上传图片
		this.serverPath = constants.serverBasicPath + serverUri;
		new Thread() {
			public void run() {
				picUpload(picPath, newName);
			}
		}.start();

	}

	public void uploadPics(String serverUri, final List<String> picPathList,
			final List<String> picNameList) {// *******************************批量上传图片
		this.serverPath = constants.serverBasicPath + serverUri;
		new Thread() {
			public void run() {
				for (int i = 0; i < picPathList.size(); i++) {
					picUpload(picPathList.get(i), picNameList.get(i));
				}
			}
		}.start();
	}

	public void downPic(Bitmap bt[], Handler han, String url, String name,
			int id) {// ********************************下载图片
		new ftpDownThread(bt, han, url, name, id).start();

	}
	

	public Bitmap loadPic(Bitmap bt[], Handler han, String url, String name,//****下载图片
			int id,int location) {
		Bitmap bp=null;
		try {
			URL url1 = new URL(url +name);
			HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
			conn.setDoInput(true);
			conn.connect();
			InputStream inputStream = conn.getInputStream();
			bp = BitmapFactory.decodeStream(inputStream);
			
			sdCardOpera sd = new sdCardOpera();// 保存图片
			sd.saveBitmap(constants.sdCardUrl, bp, name);

	//		Message msg = new Message();
	//		msg.arg1 = id;
			
	//		han.sendMessage(msg);
			
			System.out.println("ftpServer-----------下载图片成功");
			
		} catch (MalformedURLException e1) {
			System.out.println("下载图片出错-----------：" + e1);
		} catch (IOException e) {
			System.out.println("下载图片出错-----------：" + e);
		}
		return bp;

	}
	// /////////////////////////////////////////////////////////////////////////////////////////////////////////////内部方法

	private class ftpDownThread extends Thread {
		private Bitmap bt[] = null;
		private String url = null;
		private String name = null;
		private Handler han = null;
		private int id = 0;

		public ftpDownThread(Bitmap[] bt, Handler han, String url, String name,
				int id) {
			this.bt = bt;
			this.url = url;
			this.han = han;
			this.id = id;
			this.name = name;

		}

		public void run() {
			try {
				URL url = new URL(this.url + this.name);
				HttpURLConnection conn = (HttpURLConnection) url
						.openConnection();
				conn.setDoInput(true);
				conn.connect();
				InputStream inputStream = conn.getInputStream();
				this.bt[0] = BitmapFactory.decodeStream(inputStream);
				System.out.println("-----------图片成功");

				sdCardOpera sd = new sdCardOpera();// 保存图片
				sd.saveBitmap(constants.sdCardUrl, this.bt[0], this.name);

				Message msg = new Message();
				msg.arg1 = id;
				han.sendMessage(msg);
			} catch (MalformedURLException e1) {
				System.out.println("-----------下载图片---e1：" + e1);
			} catch (IOException e) {
				System.out.println("-----------下载图片---e：" + e);
			}

		}
	}

	private void picUpload(String picPath, String newName) {// ******************上传图片

		System.out.println("上传图片---picPath/newName------" + picPath + "/"
				+ newName);

		FTPClient ftpClient = new FTPClient();
		FileInputStream fis = null;
		try {
			ftpClient.connect(serverUrl, port);
			boolean loginResult = ftpClient.login(userName, password);
			int returnCode = ftpClient.getReplyCode();
			if (loginResult && FTPReply.isPositiveCompletion(returnCode)) {// *********如果登录成功

				ftpClient.makeDirectory(serverPath);// 设置上传目录
				ftpClient.changeWorkingDirectory(serverPath);
				ftpClient.setBufferSize(5000);
				ftpClient.setControlEncoding("UTF-8");
				ftpClient.enterLocalPassiveMode();
				fis = new FileInputStream(picPath);
				ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
				ftpClient.storeFile(newName, fis);
				System.out.println("----------上传成功");
			} else {// ********登录失败
				System.out.println("-----------上传图片时登陆失败");
			}

		} catch (IOException e) {
			System.out.println("IOException======" + e);
			e.printStackTrace();
			throw new RuntimeException("FTP客户端出错！", e);
		} finally {
			try {
				ftpClient.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException("关闭FTP连接发生异常！", e);
			}
		}
*
	}*/

}
