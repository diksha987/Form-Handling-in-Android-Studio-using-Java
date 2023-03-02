package com.example.newsletter_registration_form;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.time.LocalDate;
import java.time.Period;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    int counter = 0;
    EditText edtxtName, edtxtEmail, edtxtMob, edtxtDOB;
    TextView txtAge, txtInterest;
    Spinner S1;
    Button b1, b2;
    RadioButton RB1, RB2, RB3;
    RadioGroup RG;
    boolean isAllFieldsChecked = false;
    boolean[] selectedInterest;
    CheckBox checkBox1;
    CheckBox checkBox2 , checkBox3;
    String[] freq = {"Daily", "Weekly", "Monthly", "Quarterly"};

    @SuppressLint("SetTextI18n")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Button
        b1 = findViewById(R.id.button1);
        b2 = findViewById(R.id.button6);

        //EditText
        edtxtName = findViewById(R.id.editTextTextPersonName2);
        edtxtEmail = findViewById(R.id.editTextTextEmailAddress);
        edtxtMob = findViewById(R.id.editTextPhone);
        edtxtDOB = findViewById(R.id.editTextDate);

        //checkbox
        checkBox1 = findViewById(R.id.checkBox);
        checkBox2 = findViewById(R.id.checkBox2);
        checkBox3 = findViewById(R.id.checkBox3);

        //Spinner
        S1 = findViewById(R.id.planets_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.freq, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        S1.setAdapter(adapter);


        //Textview
        txtAge = findViewById(R.id.editTextTextPersonName4);

        //RadioGroup
        RG = findViewById(R.id.radioGroup);

        //RadioButton
        RB1 = findViewById(R.id.radioButton);
        RB2 = findViewById(R.id.radioButton2);
        RB3 = findViewById(R.id.radioButton3);
        b1.setEnabled(false);

        //Multichoice List

        //DateOFBIRTH
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on below line we are getting
                // the instance of our calendar.
                final Calendar c = Calendar.getInstance();

                // on below line we are getting
                // our day, month and year.
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                // on below line we are creating a variable for date picker dialog.
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        // on below line we are passing context.
                        MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // on below line we are setting date to our text view.
                                edtxtDOB.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                                String sDate = edtxtDOB.getText().toString();
                                String[] date1 = sDate.split("-");
                                Calendar c = Calendar.getInstance();

                                if (Integer.parseInt((date1[2])) > c.get(Calendar.YEAR)) {
                                    edtxtDOB.setError("INVALID DOB");
                                } else {
                                    int age = c.get(Calendar.YEAR) - Integer.parseInt((date1[2]));
                                    String age1 = String.valueOf(age);
                                    // show the final output
                                    txtAge.setText(age1);
                                }
                            }
                        },
                        // on below line we are passing year,
                        // month and day for selected date in our date picker.
                        year, month, day);
                // at last we are calling show to
                // display our date picker dialog.
                datePickerDialog.show();
            }
        });

    }




    public void onCheck(View view)
    {
        if(checkBox1.isChecked())
        {
            b1.setEnabled(true);
        }
        else
        {
            b1.setEnabled(false);
        }
    }
    public void onClick1(View view)
    {
        isAllFieldsChecked = CheckAllFields();

        if (isAllFieldsChecked) {
            String name = edtxtName.getText().toString();
            Intent i = new Intent(getApplicationContext(), MainActivity2.class);
            i.putExtra("keyname",name);
            startActivity(i);
        }
    }



    private boolean CheckAllFields() {

        String Emailval = edtxtEmail.getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String Nameval = edtxtName.getText().toString();

        //NAME
        if (edtxtName.length() == 0)
        {
            edtxtName.setError("This field is required");
            return false;
        }
        if(!Nameval.matches("[a-zA-Z ]+"))
        {
            edtxtName.requestFocus();
            edtxtName.setError("ENTER ONLY ALPHABETICAL CHARACTER");
            return false;
        }
        //EMAIL
        if (Emailval.isEmpty())
        {
            edtxtEmail.setError("Field cannot be empty");
            return false;
        }
        if (!Emailval.matches(emailPattern))
        {
            edtxtEmail.setError("Invalid email address");
            return false;
        }
        //Mobile
        if (edtxtMob.length() == 0 )
        {
           edtxtMob.setError("This field is required");
           return false;
        }
        if (edtxtMob.length() >=11 || edtxtMob.length() < 10)
        {
            edtxtMob.setError("Invalid Mobile Number");
            return false;
        }

        //RadioButton
        int selectedId1 = RG.getCheckedRadioButtonId();
        RB1 = (RadioButton) findViewById(selectedId1);
        int selectedId2 = RG.getCheckedRadioButtonId();
        RB2 = (RadioButton) findViewById(selectedId2);
        int selectedId3 = RG.getCheckedRadioButtonId();
        RB3 = (RadioButton) findViewById(selectedId3);

        if(selectedId1==-1 && selectedId2 == -1 && selectedId3 ==-1){
            Toast.makeText(MainActivity.this,"Gender NO SELECTED", Toast.LENGTH_SHORT).show();
            return false;
        }

        //spinner
        if(S1.getSelectedItem() ==null)
        {
            Toast.makeText(MainActivity.this,"FREQUENCY NO SELECTED", Toast.LENGTH_SHORT).show();
            return false;
        }

        //MultiChoice list
        if((!checkBox2.isChecked()) && (!checkBox3.isChecked()))
        {
            Toast.makeText(MainActivity.this,"INTEREST NO SELECTED", Toast.LENGTH_SHORT).show();
            return false;
        }


        // after all validation return true.
        return true;
    }
}
