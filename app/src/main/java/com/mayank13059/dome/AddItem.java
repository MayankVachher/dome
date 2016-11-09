package com.mayank13059.dome;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileOutputStream;

public class AddItem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button trigger = (Button) findViewById(R.id.trigger);
        trigger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText title, details;
                title = (EditText) findViewById(R.id.value_task_title);
                details = (EditText) findViewById(R.id.value_task_details);
                makeTask(title.getText().toString(), details.getText().toString());
                Intent intent = new Intent();
                setResult(1001, intent);
                finish();
            }
        });
    }

    private void makeTask(String title, String deets) {

        SQLDB dbEntry = new SQLDB(getApplicationContext());
        dbEntry.open();
        dbEntry.createEntry(title, deets, 0);
//        Log.d("TASK ADD", dbEntry.readAll().get(0).TITLE.toString());
        dbEntry.close();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
