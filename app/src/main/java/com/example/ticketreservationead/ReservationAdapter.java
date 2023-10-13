package com.example.ticketreservationead;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.ReservationViewHolder> {

    List<Reservation> reservationList;
    Context context;

    public ReservationAdapter(List<Reservation> reservations, Context context) {
        this.reservationList = reservations;
        this.context = context;
    }

    @NonNull
    @Override
    public ReservationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.reservation_item, parent, false);
        return new ReservationViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ReservationViewHolder holder, int position) {

        Reservation reservation = reservationList.get(position);
        holder.trainName.setText(reservation.getTrainName());
        holder.bookdate.setText(reservation.getBookdate());
        holder.from.setText(reservation.getFrom());
        holder.to.setText(reservation.getToR());
        holder.total.setText(reservation.getTotal());
        holder.noOfTickets.setText(reservation.getNoOfTickets());
        holder.cusName.setText(reservation.getCusName());







    }

    @Override
    public int getItemCount() {
        return reservationList.size();
    }




    public class ReservationViewHolder extends RecyclerView.ViewHolder {
        TextView trainName, trainTo,bookdate,from,to,total,noOfTickets,cusName;



        public ReservationViewHolder(@NonNull View itemView) {
            super(itemView);

            trainName = itemView.findViewById(R.id.Train_Name);
            bookdate = itemView.findViewById(R.id.book_date);
            from = itemView.findViewById(R.id.from_r);
            to = itemView.findViewById(R.id.to_r);
            total = itemView.findViewById(R.id.total_price);
            noOfTickets = itemView.findViewById(R.id.no_tickets);
            cusName = itemView.findViewById(R.id.cus_name);






        }
    }
}
