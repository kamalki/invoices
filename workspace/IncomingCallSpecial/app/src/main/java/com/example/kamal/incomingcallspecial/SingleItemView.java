package com.example.kamal.incomingcallspecial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SingleItemView extends AppCompatActivity {

    TextView phone11111,remark11111,create11111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_item_view);

        Intent intent = getIntent();
        String nameA = intent.getStringExtra("phone_number");
        String createA = intent.getStringExtra("call_date");
        String emailA = intent.getStringExtra("remark");
        phone11111 = (TextView) findViewById(R.id.phone11111);
        create11111 = (TextView) findViewById(R.id.create11111);
        remark11111 = (TextView) findViewById(R.id.remark11111);

        phone11111.setText(nameA);
        create11111.setText(createA);
        remark11111.setText(emailA);
    }
}
