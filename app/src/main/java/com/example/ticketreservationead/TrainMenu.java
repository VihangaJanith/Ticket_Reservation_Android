package com.example.ticketreservationead;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TrainMenu#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TrainMenu extends Fragment {

    private RecyclerView recyclerView1;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TrainMenu() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TrainMenu.
     */
    // TODO: Rename and change types and number of parameters
    public static TrainMenu newInstance(String param1, String param2) {
        TrainMenu fragment = new TrainMenu();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.train_card_view, container, false);
        recyclerView1 = view.findViewById(R.id.trainrecyclerView);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(new LinearLayoutManager(requireContext()));

        fetchData();
        return view;
    }

    private void fetchData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:5068/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);
        Call<List<Train>> call = retrofitInterface.getTrains();

        call.enqueue(new Callback<List<Train>>() {
            @Override
            public void onResponse(Call<List<Train>> call, Response<List<Train>> response) {
                if (!response.isSuccessful()) {
                    // Handle the error here, e.g., show an error message to the user
                    return;
                }

                List<Train> trains = response.body();
                if (trains != null) {
                    Log.d("API Response", "Received " + trains.size() + " trains.");

                    // Create an adapter and set it to the RecyclerView
                    TrainAdaptor adapter = new TrainAdaptor(trains, requireContext());
                    recyclerView1.setAdapter(adapter);


                }
            }

            @Override
            public void onFailure(Call<List<Train>> call, Throwable t) {
                // Handle the failure here, e.g., show an error message to the user
            }
        });
    }
}