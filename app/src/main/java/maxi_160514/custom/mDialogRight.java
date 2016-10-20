package maxi_160514.custom;

import java.util.logging.Handler;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;

import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

/*
 * 作用：自定义左侧对话框
 * 作者：马西
 * 时间：2014.7.9*/
public class mDialogRight extends Dialog {
	Context context;
	LinearLayout open = null;
	LinearLayout ser = null;
	LinearLayout link = null;
	Handler handler = null;

	public mDialogRight(Context context) {
		super(context);
		this.context = context;
	}

	public mDialogRight(Context context, int theme, Handler handler) {
		super(context, theme);
		this.context = context;
		this.handler = handler;
	}

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
//		this.setContentView(R.layout.m_dialog_right);

		Window dialogWindow = this.getWindow();

		WindowManager.LayoutParams lp = dialogWindow.getAttributes();

		dialogWindow.setGravity(Gravity.RIGHT | Gravity.TOP);
		lp.y = 300; // 新位置Y坐标
		lp.alpha = 0.8f;

	

		
	}

}
