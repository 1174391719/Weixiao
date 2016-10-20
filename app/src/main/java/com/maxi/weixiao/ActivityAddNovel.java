package com.maxi.weixiao;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class ActivityAddNovel extends Activity {
    private Button submit=null;
    private EditText title=null;
    private  EditText brief=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_novel);
        submit=(Button)findViewById(R.id.bt_addnovel);
        title=(EditText)findViewById(R.id.et_addnovel_title);
        title=(EditText)findViewById(R.id.et_addnovel_brief);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }
}
