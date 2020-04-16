package com.example.testing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
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

        arrayList = new ArrayList<>();
        for (int i = 0; i < listItem.length; i++) {

            //create a hashmap to store the data in key value pair
            HashMap<String, String> hashMap = new HashMap<>();

            hashMap.put("name", listItem[i]);
            hashMap.put("Description", subItem[i]);

            //add the hashmap into arrayList
            arrayList.add(hashMap);
        }

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


        //Kristine working on listeners
            btnDelete.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    SparseBooleanArray checkedItemPositions = listView.getCheckedItemPositions();
                    //listOfSelections = new ArrayList<>(Arrays.asList(listItem));
                    int itemCount = listView.getCount();

                    for(int i=itemCount-1; i >= 0; i--){
                        if(checkedItemPositions.get(i)){
                            //adapter.remove(arraylist.get(i));
                        }
                    }
                    adapter.notifyDataSetChanged();
                    checkedItemPositions.clear();
                }

                });
            
            btnAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                        String value = editText.getText().toString();
                        //adapter.add(value);
                        adapter.notifyDataSetChanged();
                    }
                });

            }



    }
