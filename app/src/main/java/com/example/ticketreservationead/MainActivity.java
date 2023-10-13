package com.example.ticketreservationead;

import com.example.ticketreservationead.HomeFragment;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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


public class MainActivity extends AppCompatActivity {


    ActivityMainBinding binding;

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://10.0.2.2:5068/api/";

    private HomeFragment homeFragment;
    private DashboardFragment dashboardFragment;
    private NotificationsFragment notificationsFragment;

    private Fragment activeFragment;

    private BottomNavigationView bottomNavigationView;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);





        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


//        //go to main activity2
//        Intent intent = new Intent(this, MainActivity2.class);
//        startActivity(intent);
//
//        if(savedInstanceState == null) {
//
//
//            replaceFragment(new Home2Fragment());
//        }else {
            replaceFragment(new TrainMenu());



//        }



        binding.bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
//            switch (item.getItemId()) {
//                case R.id.Home:
//                    replaceFragment(new HomeFragment());
//                    break;
//                case R.id.Settings:
//                    replaceFragment(new SettingsFragment());
//                    break;
//                case R.id.Profile:
//                    replaceFragment(new ProfileFragment());
//                    break;
//            }
            if (item.getItemId() == R.id.Home) {
                replaceFragment(new TrainMenu());
                return true;
            } else if (item.getItemId() == R.id.Settings) {
                replaceFragment(new SettingsFragment());
                return true;
            } else if (item.getItemId() == R.id.Profile) {
                replaceFragment(new ProfileFragment());
                return true;
            }







            return false;
        });

//        setContentView(R.layout.activity_main);

//
//        homeFragment = new HomeFragment();
//        dashboardFragment = new DashboardFragment();
//        notificationsFragment = new NotificationsFragment();
//
//        activeFragment = homeFragment;
//        getSupportFragmentManager().beginTransaction()
//                .add(R.id.fragment_container, activeFragment)
//                .commit();

        // Initialize BottomNavigationView
//        bottomNavigationView = findViewById(R.id.bottom_navigation);
//        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
//            int itemId = item.getItemId();
//            if (itemId == R.id.menu_home) {
//                switchFragment(homeFragment);
//                System.out.println("Switching fragment home");
//                return true;
//            } else if (itemId == R.id.menu_dashboard) {
//                switchFragment(dashboardFragment);
//                System.out.println("Switching fragment dash");
//                return true;
//            } else if (itemId == R.id.menu_notifications) {
//                switchFragment(notificationsFragment);
//                System.out.println("Switching fragment noti");
//                return true;
//            }
//            return false;
//        });



        // Helper method to switch fragments

//retro start
//        retrofit = new Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//
//
//        retrofitInterface = retrofit.create(RetrofitInterface.class);



//        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                handleLoginDialog();
//            }
//        });
//
//        findViewById(R.id.signup).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                handleSignupDialog();
//            }
//        });
    }
    //retroend

//    private void switchFragment(Fragment fragment) {
//        if (fragment != activeFragment) {
//            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//            transaction.hide(activeFragment);
//            if (!fragment.isAdded()) {
//                transaction.add(R.id.fragment_container, fragment);
//            } else {
//                transaction.show(fragment);
//            }
//            transaction.commit();
//            activeFragment = fragment;
//        }
//    }

//    private void handleLoginDialog() {
//        View view = getLayoutInflater().inflate(R.layout.login_dialog, null);
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setView(view).show();
//
//        Button loginBtn = view.findViewById(R.id.login);
//        EditText emailEdit = view.findViewById(R.id.emailEdit);
//        EditText passwordEdit = view.findViewById(R.id.passwordEdit);
//
//        loginBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                HashMap<String, String> map = new HashMap<>();
//                map.put("email", emailEdit.getText().toString());
//                map.put("password", passwordEdit.getText().toString());
//
//                Call<LoginResult> call = retrofitInterface.executeLogin(map);
//                call.enqueue(new Callback<LoginResult>() {
//                    @Override
//                    public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
//                        if (response.code() == 200){
//
//                            LoginResult result = response.body();
//
//                            AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
//                            builder1.setTitle(result.getName());
//
//                            builder1.show();
//
//                              } else if (response.code() == 400){
//                            Toast.makeText(MainActivity.this,
//                                    "Wrong Credentials", Toast.LENGTH_LONG).show();
//                        }
//
//                    }
//
//                    @Override
//                    public void onFailure(Call<LoginResult> call, Throwable t) {
//                        Toast.makeText(MainActivity.this, t.getMessage(),
//                                Toast.LENGTH_LONG).show();
//                    }
//                });
//            }
//        });
//    }


    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();

        if (fragment instanceof Home2Fragment) {
            // Hide the BottomNavigationView
            binding.bottomNavigationView.setVisibility(View.GONE);
        } else {
            // Show the BottomNavigationView for other fragments
            binding.bottomNavigationView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dot_menu, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Log.i("MainActivity", "Settings");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



//    private void handleSignupDialog() {
//
//        View view = getLayoutInflater().inflate(R.layout.signup_dialog, null);
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setView(view).show();
//
//        Button signupBtn = view.findViewById(R.id.signup);
//        EditText nameEdit = view.findViewById(R.id.nameEdit);
//        EditText emailEdit = view.findViewById(R.id.emailEdit);
//        EditText passwordEdit = view.findViewById(R.id.passwordEdit);
//
//        signupBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                HashMap<String, String> map = new HashMap<>();
//
//                map.put("username", nameEdit.getText().toString());
//                map.put("email", emailEdit.getText().toString());
//                map.put("password", passwordEdit.getText().toString());
//
//                Call<Void> call = retrofitInterface.executeSignup(map);
//
//                call.enqueue(new Callback<Void>() {
//                    @Override
//                    public void onResponse(Call<Void> call, Response<Void> response) {
//                        if (response.code() == 200){
//                            Toast.makeText(MainActivity.this,
//                                    "Signed up successfully", Toast.LENGTH_LONG).show();
//                    } else if (response.code() == 400){
//                            Toast.makeText(MainActivity.this,
//                                    "Already registered", Toast.LENGTH_LONG).show();
//                        }
//
//                    }
//                        @Override
//                    public void onFailure(Call<Void> call, Throwable t) {
//                        Toast.makeText(MainActivity.this, t.getMessage(),
//                                Toast.LENGTH_LONG).show();
//                    }
//                });
//
//            }
//        });
//
//
//
//    }
}