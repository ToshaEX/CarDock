package com.example.cardock;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Locale;

public class CarRegister extends AppCompatActivity {
    //create object of database ref class to access firebase's realtime database
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://cardock-6a917-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_register);

        final EditText vRegNum=findViewById(R.id.editText_vReg);
        final EditText vBrand=findViewById(R.id.editText_vbrand);
        final EditText vModel=findViewById(R.id.editText_vModel);
        final EditText vYear=findViewById(R.id.editText_vYear);
        final EditText vSeat=findViewById(R.id.editText_vSeat);
        final EditText vPrice=findViewById(R.id.editText_vPrice);

        final Button registerVehicleBtn=findViewById(R.id.button_rVehicle);

        registerVehicleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String regNum = vRegNum.getText().toString();
                final String brand = vBrand.getText().toString();
                final String model = vModel.getText().toString();
                final String year = vYear.getText().toString();
                final String seat = vSeat.getText().toString();
                final String price = vPrice.getText().toString();

                //check if user fields are empty. before sending data
                if (regNum.isEmpty() || brand.isEmpty() || model.isEmpty() || year.isEmpty() || seat.isEmpty() || price.isEmpty()) {
                    Toast.makeText(CarRegister.this, "Please fill the all fields", Toast.LENGTH_SHORT).show();
                } else {

                    databaseReference.child("cars").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            //check the registration number are available
                            if (snapshot.hasChild(regNum)) {
                                Toast.makeText(CarRegister.this, "This registraion number is used", Toast.LENGTH_SHORT).show();
                            } else {
                                //sending data to firebase
                                //we are using reg number as unique identifier
                                databaseReference.child("cars").child(regNum).child("brand").setValue(brand);
                                databaseReference.child("cars").child(regNum).child("model").setValue(model);
                                databaseReference.child("cars").child(regNum).child("year").setValue(year);
                                databaseReference.child("cars").child(regNum).child("price").setValue(price);
                                databaseReference.child("cars").child(regNum).child("seat").setValue(seat);
                                //show success message on complete the activity
                                Toast.makeText(CarRegister.this, "Car registered successfully...", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(CarRegister.this,TableView.class));
                                finish();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });


   }

}