package com.my.sadebuser.act.ui.Fragment;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.my.sadebuser.R;
import com.my.sadebuser.act.model.ResponseAuthError;
import com.my.sadebuser.act.model.ResponseAuthentication;
import com.my.sadebuser.act.model.booking.BookingAppointmentResponse;
import com.my.sadebuser.act.model.booking.ResultItem;
import com.my.sadebuser.act.model.bookingdetail.BookingResponse;
import com.my.sadebuser.act.network.NetworkConstraint;
import com.my.sadebuser.act.network.RetrofitClient;
import com.my.sadebuser.act.network.request.booking.BookingRequest;
import com.my.sadebuser.act.ui.activity.ShopTimeAvailavility;
import com.my.sadebuser.adapter.BookingAdapter;
import com.my.sadebuser.databinding.FragmentMyBookingBinding;
import com.my.sadebuser.utils.SharePrefrenceConstraint;
import com.my.sadebuser.utils.SharedPrefsManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MyBookingFragment extends Fragment{ //implements OnMapReadyCallback {

    private GoogleMap mMap;
    private FragmentMyBookingBinding binding;

    private  List<ResultItem> list = new ArrayList<>();
    private BookingAppointmentResponse authentication;
    private ResponseAuthentication model;
//    private int API_COUNT,ALL_API_COUNT = 1;
    AlertDialog alertDialog;
    private BookingAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentMyBookingBinding.inflate(getLayoutInflater());

//        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
//                .findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);

        return binding.getRoot();
    }
//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        mMap = googleMap;
//        try {
//            // Customise the styling of the base map using a JSON object defined
//            // in a raw resource file.
//            boolean success = mMap.setMapStyle(
//                    MapStyleOptions.loadRawResourceStyle(
//                            getContext(), R.raw.style));
//
//            if (!success) {
//                Log.e("MapsActivityRaw", "Style parsing failed.");
//            }
//        } catch (Resources.NotFoundException e) {
//            Log.e("MapsActivityRaw", "Can't find style.", e);
//        }
//
//        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(22.7196, 75.8577);
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 10));
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
//    }

    private void getBookingRequest() {
        Log.i("cscc", "getBookingRequest: "+12);
        model = SharedPrefsManager.getInstance().getObject(SharePrefrenceConstraint.user, ResponseAuthentication.class);

        binding.llMain.setVisibility(View.GONE);
        binding.loaderLayout.loader.setVisibility(View.VISIBLE);

        if (model != null) {
            String User_ID = model.getResult().getId();
            RetrofitClient.getClient(NetworkConstraint.BASE_URL)
                    .create(BookingRequest.class)
                    .getBookingAppointment(User_ID)
                    .enqueue(new Callback<JsonElement>() {
                        @Override
                        public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
//                            ++API_COUNT;
//                            if(API_COUNT ==ALL_API_COUNT){
                                binding.llMain.setVisibility(View.VISIBLE);
                                binding.loaderLayout.loader.setVisibility(View.GONE);
//                            }
                            Log.i("scvdbx", "onResponse: " + response.toString());

                            if (response != null) {
                                if (response.isSuccessful()) {
                                    JsonObject object = response.body().getAsJsonObject();
                                    int status = object.get("status").getAsInt();
                                    Log.i("scvdbx", "sta: " + status);
                                    if (status == 1) {
                                        list = new ArrayList<>();
                                        authentication = new Gson().fromJson(object, BookingAppointmentResponse.class);
                                        list.addAll(authentication.getResult());
                                        mAdapter.setList(list);
                                        mAdapter.notifyDataSetChanged();
                                        Log.i("dvdvvxv", "onResponse: " + list.toString());
                                    } else {
                                        ResponseAuthError authentication = new Gson().fromJson(object, ResponseAuthError.class);
                                        Toast.makeText(getContext(), authentication.getResult(), Toast.LENGTH_SHORT).show();

                                        if (list.size()==0){
                                            binding.tvNoProductFound.setVisibility(View.VISIBLE);
                                        }
                                    }
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<JsonElement> call, Throwable t) {
//                            ++API_COUNT;
//                            if(API_COUNT ==ALL_API_COUNT){
                                Log.i("scvdbx", "sta: " + t.getMessage());

                                binding.loaderLayout.loader.setVisibility(View.GONE);
//                            }
                        }
                    });

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        setAdapter();
        getBookingRequest();
    }

    private void setAdapter() {
        mAdapter = new BookingAdapter(getContext()/*, list*/ /*,new BookingAdapter.Status() {
//            @Override
//            public void acceptcontrol(int position,DoneCallback callback) {
//                acceptResponse(position,callback);
//            }

//            @Override
//            public void declinetcontrol(int position,DoneCallback callback) {
//                declineResponce(position,callback);
//            }
        }*/);
        mAdapter.setList(list);
        mAdapter.setRemoveBooking(new BookingAdapter.RemoveBooking() {
            @Override
            public void removeClick(String id) {
                alertDialog = new AlertDialog.Builder(getActivity())

                        .setMessage(R.string.are_you_sure_you_wanted_to_delete)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {

                                booking(id);
                            }
                        })
                        .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                alertDialog.dismiss();
                            }
                        })

                        .show();
            }
        });
        binding.recyclerReequuest.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL,false));
        binding.recyclerReequuest.setAdapter(mAdapter);

    }

    private void booking(String id) {
        if (model != null) {
            binding.loaderLayout.loader.setVisibility(View.VISIBLE);
            RetrofitClient.getClient(NetworkConstraint.BASE_URL)
                    .create(BookingRequest.class)
                    .delete_booking_appointment(id)
                    .enqueue(new Callback<JsonElement>() {
                        @Override
                        public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                            binding.loaderLayout.loader.setVisibility(View.GONE);
                            Log.i("adsfscs", "onResponse: " + response.toString());
                            Log.i("adsfscs", "onResponse: " + response.body());
                            if (response != null) {
                                if (response.isSuccessful()) {
                                    JsonObject object = response.body().getAsJsonObject();
                                    int status = object.get("status").getAsInt();
                                    if (status == 1) {
                                        Toast.makeText(getActivity(), "Appointment removed successfully", Toast.LENGTH_SHORT).show();
//                                        BookingResponse authentication = new Gson().fromJson(object, BookingResponse.class);
                                        Log.i("adsfscs", "authen: " + authentication.getResult().toString());
                                        getBookingRequest();
//                                        startActivity(new Intent(BookingDetails.this, PaymentOption.class));
                                    } else {
                                        ResponseAuthError authentication = new Gson().fromJson(object, ResponseAuthError.class);
//                                        Snackbar.make(getContext().findViewById(android.R.id.content),
//                                                authentication.getResult(),
//                                                Snackbar.LENGTH_SHORT).show();
                                        Toast.makeText(getActivity(), authentication.getResult(), Toast.LENGTH_SHORT).show();

                                    }
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<JsonElement> call, Throwable t) {
                            binding.loaderLayout.loader.setVisibility(View.GONE);
                            Log.i("adsfscs", "failure: " + 1);
                            Log.i("adsfscs", "failure: " + t.getMessage());
                        }
                    });
        }
    }

}