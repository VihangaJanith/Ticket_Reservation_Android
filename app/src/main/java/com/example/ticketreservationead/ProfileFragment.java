package com.example.ticketreservationead;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.ticketreservationead.databinding.ActivityMain2Binding;

public class ProfileFragment extends Fragment {

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://10.0.2.2:5068/api/";

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);

//        //get userid from shared preferences and log it
//        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("myKey", Context.MODE_PRIVATE);
//        String userId = sharedPreferences.getString("userid", "");
//        Log.d("userid", userId);
//
//        //get email from shared preferences and log it
//        String email = sharedPreferences.getString("email", "");
//        Log.d("email", email);

        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        String userId = sharedPreferences.getString("userid", "");
        String email = sharedPreferences.getString("email", "");
        Log.d("userid", userId);
        Log.d("email", email);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_data, container, false);
        final TextView usernameTextView = view.findViewById(R.id.username);
        final TextView emailTextView = view.findViewById(R.id.email);
        final TextView nicTextView = view.findViewById(R.id.nic);

        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        String id = sharedPreferences.getString("userid", "");

        String apiUrl = "http://10.0.2.2:5068/api/User/" + id;

        Log.d("apiUrl", apiUrl);

        Call<UserData> call = retrofitInterface.getProfileData(apiUrl);

        // Make an API request with the user ID as a query parameter
//        Call<UserData> call = retrofitInterface.getProfileData(userId);

        // Make an API request
//        Call<UserData> call = retrofitInterface.getProfileData();
        call.enqueue(new Callback<UserData>() {
            @Override
            public void onResponse(Call<UserData> call, Response<UserData> response) {
                if (response.isSuccessful() && response.body() != null) {

                    Log.d("API Response", "Received user data");
                    Log.d("API Response", response.body().toString());
                    UserData userData = response.body();
                    usernameTextView.setText(userData.getUsername());
                    emailTextView.setText(userData.getEmail());
                    nicTextView.setText(userData.getNic());


                    // Update other UI elements with user data if needed
                } else {
                    // Handle the error
                    usernameTextView.setText("Error fetching data");
                }
            }

            @Override
            public void onFailure(Call<UserData> call, Throwable t) {
                // Handle network or other errors
                usernameTextView.setText("Network error");
            }
        });

        return view;
    }
}
