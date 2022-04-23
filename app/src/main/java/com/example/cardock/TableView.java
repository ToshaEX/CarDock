package com.example.cardock;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.Query;


public class TableView extends AppCompatActivity {

    private RecyclerView recyclerView;
    CarAdapter adapter;
    DatabaseReference mbase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_view);

        mbase=FirebaseDatabase.getInstance().getReferenceFromUrl("https://cardock-6a917-default-rtdb.firebaseio.com/cars");
        recyclerView = findViewById(R.id.recycler1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Cars> options =new FirebaseRecyclerOptions.Builder<Cars>().setQuery(mbase,Cars.class).build();


        adapter =new CarAdapter(options);

        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}