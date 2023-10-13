package com.example.ticketreservationead;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UserDataAdapter extends RecyclerView.Adapter<UserDataAdapter.UserDataViewHolder> {

    private UserData userData;

    public UserDataAdapter(UserData userData) {
        this.userData = userData;
    }

    @NonNull
    @Override
    public UserDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View userView = inflater.inflate(R.layout.user_data, parent, false);

        return new UserDataViewHolder(userView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserDataViewHolder holder, int position) {
        holder.usernameTextView.setText(userData.getUsername());
        holder.emailTextView.setText(userData.getEmail());
        holder.nicTextView.setText(userData.getNic());
    }

    @Override
    public int getItemCount() {
        return 1; // Return 1 since there's only one user data object.
    }

    public static class UserDataViewHolder extends RecyclerView.ViewHolder {
        public TextView usernameTextView;
        public TextView emailTextView;
        public TextView nicTextView;

        public UserDataViewHolder(View itemView) {
            super(itemView);
            usernameTextView = itemView.findViewById(R.id.username);
            emailTextView = itemView.findViewById(R.id.email);
            nicTextView = itemView.findViewById(R.id.nic);
        }
    }
}
