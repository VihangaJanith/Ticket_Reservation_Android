package com.example.ticketreservationead;

import static androidx.core.content.ContentProviderCompat.requireContext;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button; // Import Button
import android.util.Log; // Import Log

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TrainAdaptor extends RecyclerView.Adapter<TrainAdaptor.TrainViewHolder> {

    List<Train> trainList;
    Context context;

    public TrainAdaptor(List<Train> trains, Context context) {
        this.trainList = trains;
        this.context = context;
    }

    @NonNull
    @Override
    public TrainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.train_item, parent, false);
        return new TrainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrainViewHolder holder, int position) {
        Train train = trainList.get(position);
        holder.trainName.setText(train.getTrainName());
        holder.trainType.setText(train.getTrainType());
        holder.trainTo.setText(train.getTrainTo());

        //log trainId
//        Log.d("trainId", train.getTrainId());


        // Set an OnClickListener for the button in each item


        //pass trainId to onclick listener


        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //log trainId
                Log.d("trainId", train.getTrainId());

                // Log your message when the button is clicked
                Log.d("Button Clicked", "Button in item " + position + " clicked.");


                //create a dialog box to get the number of tickets

                View dialogView = LayoutInflater.from(context).inflate(R.layout.train_reservation_dialog, null);

                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(context);
                builder.setView(dialogView).show();


//                AlertDialog.Builder builder = new AlertDialog.Builder(context); // Use context here
//                builder.setView(dialogView).show();




                Button bookButton = dialogView.findViewById(R.id.ticketreserve);
                EditText noOfTickets = dialogView.findViewById(R.id.no_ticketss);

                EditText bookdate = dialogView.findViewById(R.id.bookingDate);
                EditText from = dialogView.findViewById(R.id.ticketfrom);
                EditText to = dialogView.findViewById(R.id.ticketto);


                bookButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        SharedPreferences sharedPreferences = context.getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
                        String CusId = sharedPreferences.getString("userid", "");


                        //log trainId
                        Log.d("trainId", train.getTrainId());

                        // Log your message when the button is clicked
                        Log.d("Button Clicked", "Button in item " + position + " clicked.");

                        HashMap<String, String> map = new HashMap<>();

                        map.put("trainId", train.getTrainId());
                        map.put("noOfTickets", noOfTickets.getText().toString());
                        map.put("bookdate", bookdate.getText().toString());
                        map.put("from", from.getText().toString());
                        map.put("to", to.getText().toString());
                        map.put("cusId", CusId.toString());
                        map.put("total", "1000");
                        map.put("cusName", "EAD");
                        map.put("trainName", train.getTrainName());
                        map.put("traintime", "10:00");




//RetrofitInterface retrofitInterface = RetrofitClient.getRetrofit().create(RetrofitInterface.class);


                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl("http://10.0.2.2:5068/api/")
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

                        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);


                        Log.d("map", map.toString());
                        Call<Void> call = retrofitInterface.executeReservation(map);

//                        Call<Void> call = retrofitInterface.executeReservation(map);

                        call.enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                Log.d("Response Code", String.valueOf(response.code()));
                                if (response.code() == 201) {
                                    AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                                    builder1.setTitle("Reservation Successful");
                                    builder1.setMessage("Your reservation was successful");
                                    builder1.show();
                                } else if (response.code() == 400) {
                                    AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                                    builder1.setTitle("Reservation Failed");
                                    builder1.setMessage("Your reservation was not successful");
                                    builder1.show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                                Log.d("err Response Code", String.valueOf(t.getMessage()));
                                // Handle the case when the network request fails
                                // You can display an error message or take appropriate action here
                            }
                        });






                    }
                });









                //create a reservation object

                //pass the reservation object to the next activity

                //start the next activity










            }
        });
    }

    @Override
    public int getItemCount() {
        return trainList.size();
    }

    public class TrainViewHolder extends RecyclerView.ViewHolder {
        TextView trainName, trainType, trainTo;
        Button button; // Add Button

        public TrainViewHolder(@NonNull View itemView) {
            super(itemView);

            trainName = itemView.findViewById(R.id.trainname);
            trainType = itemView.findViewById(R.id.traintype);
            trainTo = itemView.findViewById(R.id.trainto);
            button = itemView.findViewById(R.id.booktrain); // Initialize the button
        }
    }
}
