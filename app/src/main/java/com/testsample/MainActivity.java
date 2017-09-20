package com.testsample;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.testsample.dto.DatabaseModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements DBHelper.clearRegister {
    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;
    DBHelper mDatabase;
    private Button button1, button2;
    private EditText editText2, editText1;
    private ArrayList<DatabaseModel> array_list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        editText1 = (EditText) findViewById(R.id.editText1);
        editText2 = (EditText) findViewById(R.id.editText2);
        mDatabase = new DBHelper(this,this);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                    // Should we show an explanation?
                    if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                        // Show an explanation to the user *asynchronously* -- don't block
                        // this thread waiting for the user's response! After the user
                        // sees the explanation, try again to request the permission.
                        mDatabase.insertContact(editText1.getText().toString().trim(), editText2.getText().toString().trim());
                    } else {

                        // No explanation needed, we can request the permission.

                        ActivityCompat.requestPermissions(MainActivity.this,
                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                MY_PERMISSIONS_REQUEST_READ_CONTACTS);
                        mDatabase.insertContact(editText1.getText().toString().trim(), editText2.getText().toString().trim());
                        // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                        // app-defined int constant. The callback method gets the
                        // result of the request.
                    }
                }else{
                    Toast.makeText(MainActivity.this, "Not granded", Toast.LENGTH_SHORT).show();
                }

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String email=cursor.getString(cursor.getColumnIndex("password"));
//                String password=cursor.getString(cursor.getColumnIndex("email_address"));
               /* array_list= mDatabase.getAllCotacts();
                for (int i = 0; i < array_list.size(); i++) {
                    if (array_list.get(i).getEmail().contains("anbalaganss93")) {
                        Toast.makeText(MainActivity.this, "Found email within "+array_list.size(), Toast.LENGTH_SHORT).show();
                    }
                }*/
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    mDatabase.insertContact(editText1.getText().toString().trim(), editText2.getText().toString().trim());
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }


    @Override
    public void cleardata() {
        editText1.setText("");
        editText2.setText("");
        Toast.makeText(MainActivity.this, "Data stored successfully", Toast.LENGTH_SHORT).show();
    }
}
