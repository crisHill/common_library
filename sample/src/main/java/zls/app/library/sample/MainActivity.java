package zls.app.library.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.LinkedList;
import java.util.List;

import zls.app.library.sample.navbar.NavBarDemo;

public class MainActivity extends Activity{

    Class[] clz = {NavBarDemo.class};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(MainActivity.this, clz[position]);
                MainActivity.this.startActivity(i);
            }
        });
        List<String> arrayList = new LinkedList<>();
        for (int i = 0, n = clz.length; i < n; i++) {
            arrayList.add(clz[i].getSimpleName());
        }
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList));
    }

}
