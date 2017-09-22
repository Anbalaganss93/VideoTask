package com.testsample;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.testsample.dto.DatabaseModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements DBHelper.clearRegister {
    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;
    DBHelper mDatabase;
    private Button button1, button2;
    private EditText mEmailAddress, mPassword;
    private TextView mToolbarTitle;
    private ArrayList<DatabaseModel> array_list = new ArrayList<>();
    private Typeface regular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        init();
        setfontTypeface();

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validation()) {
                    int currentapiVersion = android.os.Build.VERSION.SDK_INT;
                    if (currentapiVersion >= 23) {
                        // Do something for lollipop and above versions
                        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                                    Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_CONTACTS);
                        } else {
                            registerNewUser();
                        }
                    } else {
                        registerNewUser();
                    }
                }
                }
            });

        button2.setOnClickListener(new View.OnClickListener()

            {
                @Override
                public void onClick (View view){
                if (validation()) {
                    array_list = mDatabase.getAllCotacts();
                    if (array_list.size() != 0) {
                        for (int i = 0; i < array_list.size(); i++) {
                            if (array_list.get(i).getEmail().contains(mEmailAddress.getText().toString().trim()) && array_list.get(i).getPassword().contains(mPassword.getText().toString().trim())) {
                                Intent videointent = new Intent(MainActivity.this, MyPlayerActivity.class);
                                startActivity(videointent);
                                finish();
                                return;
                            } else {
                                Toast.makeText(MainActivity.this, "No user found. Please register", Toast.LENGTH_SHORT).show();
                            }
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "No user in database. Please register", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            });
        }

    public void registerNewUser() {
        array_list = mDatabase.getAllCotacts();
        if (array_list.size() != 0) {
            for (int i = 0; i < array_list.size(); i++) {
                if (array_list.get(i).getEmail().contains(mEmailAddress.getText().toString().trim()) && array_list.get(i).getPassword().contains(mPassword.getText().toString().trim())) {
                    Toast.makeText(MainActivity.this, "User already registered", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    mDatabase.insertContact(mEmailAddress.getText().toString().trim(), mPassword.getText().toString().trim());
                }
            }
        }else {
            mDatabase.insertContact(mEmailAddress.getText().toString().trim(), mPassword.getText().toString().trim());
        }
    }

    private void init() {
        regular = Typeface.createFromAsset(getAssets(), "fonts/proximanova-reg.ttf");
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        mToolbarTitle = (TextView) findViewById(R.id.activity_main_toolbar_name);
        mEmailAddress = (EditText) findViewById(R.id.editText1);
        mPassword = (EditText) findViewById(R.id.editText2);
        mDatabase = new DBHelper(this, this);
    }

    private void setfontTypeface() {
        mEmailAddress.setTypeface(regular);
        mPassword.setTypeface(regular);
        button1.setTypeface(regular);
        button2.setTypeface(regular);
        mToolbarTitle.setTypeface(regular);
    }

    private boolean validation() {
        if (mEmailAddress.getText().toString().trim().length() == 0) {
            Toast.makeText(MainActivity.this, R.string.email, Toast.LENGTH_SHORT).show();
            return false;
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(mEmailAddress.getText().toString().trim()).matches()) {
            Toast.makeText(MainActivity.this, R.string.valid_email, Toast.LENGTH_SHORT).show();
            return false;
        } else if (mPassword.getText().toString().trim().length() == 0) {
            Toast.makeText(MainActivity.this, R.string.password_error, Toast.LENGTH_SHORT).show();
            return false;
        } else if (mPassword.getText().toString().trim().length() != 0 && mPassword.getText().toString().trim().length() < 8) {
            Toast.makeText(MainActivity.this, "Password minimum character is 8", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    registerNewUser();
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
        }
    }


    @Override
    public void cleardata() {
        mEmailAddress.setText("");
        mPassword.setText("");
        Toast.makeText(MainActivity.this, "Data stored successfully", Toast.LENGTH_SHORT).show();
    }
}
