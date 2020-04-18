package com.example.testing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;




public class MainActivity extends AppCompatActivity  {

    ListView listView;
    TextView textView;
    String[] listItem;
    String[] subItem;
    EditText editText;


    //Kristine
    Button btnDelete, btnAdd;
    CheckBox checkbox;
    ArrayList<HashMap<String, String>> arrayList;
    ArrayList<String> listOfSelections;

    SimpleAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Kristine
        btnAdd = (Button) findViewById(R.id.button2);
        btnDelete = (Button) findViewById(R.id.button);
        checkbox = (CheckBox) findViewById(R.id.cBox);
        editText =(EditText) findViewById(R.id.editText);

        listView = (ListView) findViewById(R.id.simpleListView);
        listItem = getResources().getStringArray(R.array.array_of_Stuff);
        subItem = getResources().getStringArray(R.array.array_of_Stuff_second_coming);


        //To make the list with subitems I found that the easiest way was to use
        // hashmap to pair together the two arraylists in strings.xml that works together.
        // then add it to the arraylist with Add using a For Loop.

        arrayList = new ArrayList<>();
        for (int i = 0; i < listItem.length; i++) {

            //create a hash map to store the data in key value pair
            HashMap<String, String> hashMap = new HashMap<>();

            hashMap.put("name", listItem[i]);
            hashMap.put("Description", subItem[i]);

            //add the hash map into arrayList
            arrayList.add(hashMap);
        }

        //using simple Adapter with the arraylist to put everything into the ListView.

        final String[] from= {"name", "Description"};
        int [] to = new int[]{R.id.textView, R.id.textView2};
        adapter = new SimpleAdapter(this, arrayList, R.layout.activity_listview, from, to);
        listView.setAdapter(adapter);



        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
               public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // TODO Auto-generated method stub
                String value=adapter.getItem(position);
                Toast.makeText(getApplicationContext(),value,Toast.LENGTH_SHORT).show();
          }
         });
        */



            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    WindowManager.LayoutParams layout = getWindow().getAttributes();

                    if(layout.screenBrightness == 1F){
                        layout.screenBrightness = 0F;
                        getWindow().setAttributes(layout);}
                    else{
                        layout.screenBrightness = 1F;
                        getWindow().setAttributes(layout);
                    }
                    //In this Function we are waiting click. when the click happens
                    //it makes a window manager that puts info in layout.
                    //it checks wether the screen is on maxlight or not.
                    // if so it changes to max or minimum value.
                }

                });

            btnAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                    Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    vibe.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    //deprecated in API 26
                    vibe.vibrate(500);
                }

                }
            });

            }



    }
