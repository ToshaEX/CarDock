package com.example.cardock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;


public class CarAdapter extends FirebaseRecyclerAdapter<
        Cars,CarAdapter.carsViewHolder> {

    public CarAdapter(
            @NonNull FirebaseRecyclerOptions<Cars> options){
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull carsViewHolder holder, int position, @NonNull Cars model) {
        holder.brand.setText(model.getBrand());
        holder.model.setText(model.getModel());
        holder.year.setText(model.getYear());
        holder.seat.setText(model.getSeat());
        holder.price.setText(model.getPrice());
    }

    @NonNull
    @Override
    public carsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.cars,parent,false);
        return new carsViewHolder(view);
    }

    class carsViewHolder extends RecyclerView.ViewHolder{
        TextView brand,model,year,seat,price;
        public carsViewHolder(@NonNull View itemView){
            super(itemView);

            brand=itemView.findViewById(R.id.space_brand);
            model=itemView.findViewById(R.id.space_model);
            year=itemView.findViewById(R.id.space_year);
            price=itemView.findViewById(R.id.space_price);
            seat =itemView.findViewById(R.id.space_seat);
            System.out.println(brand);
        }
    }
}
