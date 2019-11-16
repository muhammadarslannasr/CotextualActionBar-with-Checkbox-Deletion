package com.beautiful.soundss.contextualactionbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnLongClickListener {
    TextView counter_text;
    boolean is_in_action_mode = false;
    Toolbar toolbar;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    int[] img_id = {R.drawable.image1,R.drawable.image2,R.drawable.image3,R.drawable.image4,R.drawable.image5,
    R.drawable.image1,R.drawable.image2,R.drawable.image3,R.drawable.image4,R.drawable.image5};
    ArrayList<Contact> contactArrayList = new ArrayList<>();
    ArrayList<Contact> selection_list = new ArrayList<>();
    int counter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        counter_text = findViewById(R.id.counter_text);
        counter_text.setVisibility(View.GONE);
        String[] name,email;
        name = getResources().getStringArray(R.array.name);
        email = getResources().getStringArray(R.array.email);
        int i = 0;
        for (String NAME : name){

            Contact contact = new Contact(img_id[i],NAME,email[i]);
            contactArrayList.add(contact);
            i++;
        }
        adapter = new ContactAdapter(contactArrayList,MainActivity.this);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main,menu);
        return true;
    }

    @Override
    public boolean onLongClick(View view) {
        toolbar.getMenu().clear();
        toolbar.inflateMenu(R.menu.menu_action_mode);
        counter_text.setVisibility(View.VISIBLE);
        is_in_action_mode = true;
        adapter.notifyDataSetChanged();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        return true;
    }

    public void prepareSelection(View view,int position){

        if(((CheckBox) view).isChecked()){

            selection_list.add(contactArrayList.get(position));
            counter = counter + 1;
            updateCounter(counter);
        }else {

            selection_list.remove(contactArrayList.get(position));
            counter = counter - 1;
            updateCounter(counter);
        }
    }

    public void updateCounter(int counter){

        if(counter == 0){

            counter_text.setText("0 item selected");
        }else {

            counter_text.setText(counter+" item selected");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.item_delete){
            ContactAdapter contactAdapter = (ContactAdapter) adapter;
            contactAdapter.updateAdapter(selection_list);
            clearActionMode();
        }else if(item.getItemId() == android.R.id.home){
            clearActionMode();
            adapter.notifyDataSetChanged();
        }
        return true;
    }

    public void clearActionMode(){

        is_in_action_mode = false;
        toolbar.getMenu().clear();
        toolbar.inflateMenu(R.menu.menu_activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        counter_text.setVisibility(View.GONE);
        counter_text.setText("0 item selected");
        counter = 0;
        selection_list.clear();
    }

    @Override
    public void onBackPressed() {
        if(is_in_action_mode){
            clearActionMode();
            adapter.notifyDataSetChanged();
        }else {
            super.onBackPressed();
        }

    }
}
