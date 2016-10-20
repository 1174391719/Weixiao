package maxi_160514.custom;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

/*
 * 作用：自定义左侧等待对话框
 * 作者：马西
 * 时间：2014.7.9*/
public class mDialogWait extends Dialog{
	Context context;
    public mDialogWait(Context context) {
        super(context);     
        this.context = context;
    }
    public mDialogWait(Context context, int theme){
        super(context, theme);
        this.context = context;
    }
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
 //       this.setContentView(R.layout.m_dialog_wait);
    }


}
