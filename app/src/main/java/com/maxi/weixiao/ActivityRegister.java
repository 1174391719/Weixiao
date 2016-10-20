package com.maxi.weixiao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class ActivityRegister extends Activity implements NetInterface.register{
    private Button register=null;
    private EditText account=null;
    private  EditText passwd=null;
    private String ac=null;
    private String pa=null;
    private Handler handler=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        register=(Button)findViewById(R.id.bt_register);
        account=(EditText)findViewById(R.id.et_register_account);
        passwd=(EditText)findViewById(R.id.et_register_passwd);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ac=account.getText().toString();
                pa=passwd.getText().toString();
                if(!ac.equals("")){
                    if(!pa.equals("")){

                        Map map=new HashMap();
                        map.put("key","0");
                        map.put("account",ac);
                        map.put("passwd",pa);

                        HttpHelp hh=new HttpHelp();
                        hh.register(ActivityRegister.this,map);
                    }else {
                        Toast.makeText(ActivityRegister.this,"密码不能为空",Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(ActivityRegister.this,"帐号不能为空",Toast.LENGTH_SHORT).show();
                }

            }
        });
        handler=new Handler(){

            @Override
            public void handleMessage(Message msg){
                if((boolean)msg.obj){
                    ActivityRegister.this.finish();
                    ActivityLogin.instant.finish();
                }else {
                    Toast.makeText(ActivityRegister.this,"帐号已被注册或未知错误",Toast.LENGTH_SHORT).show();
                }

            }

        };
    }


    @Override
    public void registerCallBack(boolean result){

        Message ms=new Message();
        ms.obj=result;
        handler.sendMessage(ms);


    }
}
