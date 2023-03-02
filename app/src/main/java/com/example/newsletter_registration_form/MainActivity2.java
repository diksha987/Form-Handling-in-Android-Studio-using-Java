package com.example.newsletter_registration_form;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    TextView e1 ;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        e1 = findViewById(R.id.textView10);
        String name = getIntent().getStringExtra("keyname");
        e1.setText(name);
    }
    public void onClick(View view)
    {
        Uri u = Uri.parse("tel:7888226967");
        Intent callIntent = new Intent(Intent.ACTION_DIAL,u);
        startActivity(callIntent);
    }
    public void onClick11(View view)
    {
        Uri uri = Uri.parse("smsto:7888226967");
        Intent it = new Intent(Intent.ACTION_SENDTO, uri);
        it.putExtra("sms_body", "**** FEEDBACK RELATED TO NEWSLETTER REGISTRATION****");
        startActivity(it);
    }
    public void onClick2(View view)
    {
        Uri u = Uri.parse("mailto:diksharg2002@gmail.com");
        Intent mailto = new Intent(Intent.ACTION_SENDTO,u);
        //mailto.putExtra(mailto.EXTRA_SUBJECT,"QUERY RELATED TO NEWSLETTER WORK");
        startActivity(mailto);
    }
    public void onClick3(View view)
    {
        String latitude = "21.088304477252954" , longitude ="79.09033722765435" ;
        String uri = "geo:" + latitude + "," +longitude + "?q=" + latitude + "," + longitude;
        startActivity(new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri)));
    }
    public void onClick4(View view)
    {
        Intent i = new Intent(android.content.Intent.ACTION_VIEW);
        i.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.whatsapp"));
        startActivity(i);
    }
}