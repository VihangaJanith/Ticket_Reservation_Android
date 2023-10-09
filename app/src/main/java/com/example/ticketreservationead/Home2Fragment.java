package com.example.ticketreservationead;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.ticketreservationead.HomeFragment;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.ticketreservationead.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;




/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Home2Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */


public class Home2Fragment extends Fragment {

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://10.0.2.2:5068/api/";



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Home2Fragment() {
        // Required empty public constructor

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Home2Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Home2Fragment newInstance(String param1, String param2) {
        Home2Fragment fragment = new Home2Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home2, container, false);

        // Find the views and set their click listeners
        view.findViewById(R.id.login2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleLoginDialog();
            }
        });

        view.findViewById(R.id.signup2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleSignupDialog();
            }
        });

        return view;
    }


    private void handleLoginDialog() {
        View view = getLayoutInflater().inflate(R.layout.login_dialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(view).show();


        Button loginBtn = view.findViewById(R.id.login);
        EditText emailEdit = view.findViewById(R.id.emailEdit);
        EditText passwordEdit = view.findViewById(R.id.passwordEdit);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("login button clicked");
                HashMap<String, String> map = new HashMap<>();
                map.put("email", emailEdit.getText().toString());
                map.put("password", passwordEdit.getText().toString());

                Call<LoginResult> call = retrofitInterface.executeLogin(map);
                call.enqueue(new Callback<LoginResult>() {
                    @Override
                    public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                        if (response.code() == 200){

                            LoginResult result = response.body();

                            AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
                            builder1.setTitle(result.getName());
                            builder1.show();

                            AlertDialog.Builder builder2 = new AlertDialog.Builder(getContext());
                            builder2.setTitle(result.getEmail());
                            builder2.show();

                            //add email to SharedPreferences

                            SharedPreferences.Editor editor = requireContext().getSharedPreferences("myPrefs", Context.MODE_PRIVATE).edit();
                            editor.putString("email", result.getEmail());
                            editor.apply();


                            //navigate to MainActivity
//                            Intent intent = new Intent(getContext(), MainActivity.class);
//                            startActivity(intent);

//                            navigate to TrainMenuFragment
//                            Fragment fragment = new TrainMenu();
//                            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
//                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                            fragmentTransaction.replace(R.id.train_menus, fragment);
//                            fragmentTransaction.addToBackStack(null);
//                            fragmentTransaction.commit();







                        } else{
                            Toast.makeText(getContext(),
                                    "Wrong Credentials", Toast.LENGTH_LONG).show();
                            SharedPreferences sharedPreferences = requireContext().getSharedPreferences("myPrefs", Context.MODE_PRIVATE);

                            // Retrieve the email value from shared preferences
                            String email = sharedPreferences.getString("email", "default_value_if_not_found");
                            System.out.println("email: " + email);


                        }

                    }

                    @Override
                    public void onFailure(Call<LoginResult> call, Throwable t) {
                        Toast.makeText(getContext(), t.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }


    private void handleSignupDialog() {

        View view = getLayoutInflater().inflate(R.layout.signup_dialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(view).show();

        Button signupBtn = view.findViewById(R.id.signup);
        EditText nameEdit = view.findViewById(R.id.nameEdit);
        EditText emailEdit = view.findViewById(R.id.emailEdit);
        EditText passwordEdit = view.findViewById(R.id.passwordEdit);

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, String> map = new HashMap<>();

                map.put("username", nameEdit.getText().toString());
                map.put("email", emailEdit.getText().toString());
                map.put("password", passwordEdit.getText().toString());

                Call<Void> call = retrofitInterface.executeSignup(map);

                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.code() == 200){
                            Toast.makeText(getContext(),
                                    "Signed up successfully", Toast.LENGTH_LONG).show();
                        } else if (response.code() == 400){
                            Toast.makeText(getContext(),
                                    "Already registered", Toast.LENGTH_LONG).show();
                        }

                    }
                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(getContext(), t.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });

            }
        });



    }




}