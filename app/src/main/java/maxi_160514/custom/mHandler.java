package maxi_160514.custom;

import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

/*
 * 作用：自定义handler
 * 作者：马西
 * 时间：2014.7.9*/
public class mHandler extends Handler {
	TextView textView=null;

	public mHandler(TextView textView) {
		this.textView=textView;
	}
	
	public void handleMessage(Message msg) {

		switch (msg.arg1) {

		case 100:
			textView.setText("本地蓝牙设备已经打开");
			
			break;
		case 101:
			textView.setText("已经获得匹配设备");
			
			break;
		case 102:
			textView.setText("成功连接远程设备");
			
			break;
		
		default:
			break;
		}

	}

}
