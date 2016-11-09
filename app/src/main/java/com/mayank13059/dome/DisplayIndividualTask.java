package com.mayank13059.dome;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class DisplayIndividualTask extends AppCompatActivity {

    TextView deets, title;
    CheckBox status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_individual_task);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        status = (CheckBox) findViewById(R.id.display_task_status);
        deets = (TextView) findViewById(R.id.display_task_deets);
        title = (TextView) findViewById(R.id.display_task_title);

        Intent prevIntent = getIntent();

        deets.setText(prevIntent.getStringExtra("TaskDeets"));
        title.setText(prevIntent.getStringExtra("TaskTitle"));

        if(prevIntent.getIntExtra("TaskStatus", 0) == 1) {
            status.setChecked(true);
        }

        status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLDB dbEntry = new SQLDB(getApplicationContext());
                dbEntry.open();
                if(status.isChecked()) {
                    dbEntry.changeStatus(title.getText().toString(), 1);
                }
                else {
                    dbEntry.changeStatus(title.getText().toString(), 0);
                }
                dbEntry.close();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_task:
                Intent intent = new Intent(this, AddItem.class);
                startActivity(intent);
                return true;

            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
