package com.maxi.weixiao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class ActivityMyNovel extends Activity {
    private ListView lv=null;
    private TextView add=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_novel);

        lv=(ListView)findViewById(R.id.lv_mynovel);
        add=(TextView)findViewById(R.id.tv_mynovel_add);
        lv.setAdapter(new AdapterMynovel(this));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityMyNovel.this,ActivityAddNovel.class));
                
            }
        });
    }
}
