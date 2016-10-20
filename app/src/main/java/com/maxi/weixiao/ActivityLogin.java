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
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import maxi_160514.tools.Tools;

public class ActivityLogin extends Activity implements NetInterface.Login {

    private Button login = null;
    private EditText account = null;
    private EditText passwd = null;
    private String ac = null;
    private String pa = null;
    private TextView register = null;
    public static ActivityLogin instant = null;
    private Handler handler = null;
    public static int kk=100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Log.d("kkActivityLogin", "onCreate---kk:" + kk);
        kk++;
        Log.d("kkActivityLogin","onCreate---kk:"+kk);


        instant = this;
        login = (Button) findViewById(R.id.bt_login);
        account = (EditText) findViewById(R.id.et_account);
        passwd = (EditText) findViewById(R.id.et_passwd);
        register = (TextView) findViewById(R.id.tv_login);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityLogin.this, ActivityRegister.class));
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Tools.isNetworkAvailable(ActivityLogin.this)) {
                    ac = account.getText().toString();
                    pa = passwd.getText().toString();
                    if (ac.equals("")) {
                        Toast.makeText(ActivityLogin.this, "帐号不能为空", Toast.LENGTH_SHORT).show();
                    } else if (pa.equals("")) {
                        Toast.makeText(ActivityLogin.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                    } else {
                        Map map = new HashMap();
                        map.put("key", "1");
                        map.put("account", ac);
                        map.put("passwd", pa);
                        HttpHelp hh = new HttpHelp();
                        hh.login(ActivityLogin.this, map);

                    }
                }else {
                    Toast.makeText(ActivityLogin.this,"亲，你木有网络呢",Toast.LENGTH_SHORT);
                }

            }
        });
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if ((boolean) msg.obj) {
                    ActivityLogin.this.finish();

                } else {
                    Toast.makeText(ActivityLogin.this, "帐号或密码错误", Toast.LENGTH_SHORT).show();
                }

            }

        };

    }
    @Override
    public void loginCallBack(boolean result) {

        Message msg = new Message();
        msg.obj = result;
        handler.sendMessage(msg);

    }
}
