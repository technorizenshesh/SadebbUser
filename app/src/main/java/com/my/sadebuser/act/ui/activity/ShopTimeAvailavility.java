package com.my.sadebuser.act.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.CompositeDateValidator;
import com.google.android.material.datepicker.DateValidatorPointBackward;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.my.sadebuser.R;
import com.my.sadebuser.act.model.ResponseAuthError;
import com.my.sadebuser.act.model.ResponseAuthentication;
import com.my.sadebuser.act.model.bookingdetail.BookingResponse;
import com.my.sadebuser.act.model.servicedetail.ServiceDetailResponse;
import com.my.sadebuser.act.model.servicedetail.ServiceUserItem;
import com.my.sadebuser.act.model.servicelist.ResultItem;
import com.my.sadebuser.act.model.timeavailibity.TimeAvailibityResponse;
import com.my.sadebuser.act.model.timeavailibity.TimeItem;
import com.my.sadebuser.act.network.NetworkConstraint;
import com.my.sadebuser.act.network.RetrofitClient;
import com.my.sadebuser.act.network.request.bookingdetail.BookingRequest;
import com.my.sadebuser.act.network.time.TimeAvailibity;
import com.my.sadebuser.adapter.ProviderAllUserAdapter;
import com.my.sadebuser.adapter.TimeSlotAdapter;
import com.my.sadebuser.databinding.ActivityShopTimeAvailavilityBinding;
import com.my.sadebuser.utils.SharePrefrenceConstraint;
import com.my.sadebuser.utils.SharedPrefsManager;
import com.my.sadebuser.utils.Utils;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopTimeAvailavility extends AppCompatActivity {

    private List<TimeItem> timeItems = new ArrayList<>();
    private final Calendar selectedDateTime = Calendar.getInstance();
    private final int ALL_API_COUNT = 1;
    private ActivityShopTimeAvailavilityBinding binding;
    private String start_time, end_time, date, availibitiydate;
    private TimeSlotAdapter timeSlotAdapter;
    private ResponseAuthentication model;
    private String service_id;
    private ResultItem item;
    private int API_COUNT;
    private String provider_Name, provider_img;
//  private String providerUserId;


    private ProviderAllUserAdapter adapter;
    private List<ServiceUserItem> list = new ArrayList<>();
    private com.my.sadebuser.act.model.servicelist.ResultItem itemProvider;
    private String sProvider = "";
    private String providerUserIdProvider;

    int number=6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_shop_time_availavility);

//        providerUserId = getIntent().getStringExtra("providerUserId");

        init();
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        binding.rvTimeslot.setLayoutManager(new GridLayoutManager(ShopTimeAvailavility.this, 3, RecyclerView.HORIZONTAL, false));
        timeSlotAdapter = new TimeSlotAdapter(/*timeItems,*/ new TimeSlotAdapter.ClickCallback() {
            @Override
            public void click(int positoin) {
                Log.i("TAGczczv", "click: " + timeItems.get(positoin).getStatus());
                start_time = timeItems.get(positoin).getStart();
                end_time = timeItems.get(positoin).getEnd();
                if (timeItems.get(positoin).getStatus().equals("false")) {
                    date = new SimpleDateFormat("dd-MM-yyyy").format(selectedDateTime.getTimeInMillis());
                    binding.appointmentDT.setText(date + " " + start_time);
                }
            }
        });
        timeSlotAdapter.setTimeItems(timeItems);
        binding.rvTimeslot.setAdapter(timeSlotAdapter);

//        timeAvailibity();
        setData();
        setUpDatePicker();
        binding.imgBack.setOnClickListener(v -> {
            onBackPressed();
        });

        binding.RRBook.setOnClickListener(v -> {

//            Log.i("fsfsffsfs", "init: "+item.toString());
//            String provider_Name = getIntent().getStringExtra("provider_Name");
//            String provider_img = getIntent().getStringExtra("provider_img");
//            String service_id = getIntent().getStringExtra("service_id");

            if (!sProvider.equals("")) {
//                Intent intent = new Intent(ProviderAllUserActivity.this, ShopTimeAvailavility.class);
//                intent.putExtra("service_id", service_id);
//                intent.putExtra("provider_Name", provider_Name);
//                intent.putExtra("provider_img", provider_img);
//                intent.putExtra("providerUserId", providerUserId);
//                intent.putExtra("item", new Gson().toJson(item));
//                startActivity(intent);


                if (validate()) {
//                Intent intent = new Intent(ShopTimeAvailavility.this, BookingDetails.class);
//                intent.putExtra("service_id", service_id);
//                intent.putExtra("start_time", start_time);
//                intent.putExtra("end_time", end_time);
//                intent.putExtra("date", date);
//                intent.putExtra("providerUserId", providerUserIdProvider);//providerUserId);
//                intent.putExtra("email", binding.tvEmail.getText().toString());
//                intent.putExtra("no", binding.tvNo.getText().toString());
//                intent.putExtra("item", new Gson().toJson(item));
//                startActivity(intent);

                    booking(binding.tvEmail.getText().toString(), binding.tvNo.getText().toString());
                }


            } else {
                Snackbar.make(findViewById(android.R.id.content),
                        getString(R.string.please_select_a_service_user),
                        Snackbar.LENGTH_SHORT).show();
            }
        });

        initProviderAllUser();

        binding.nextimage.setOnClickListener(v -> {
            if(timeItems.size()>number) {
                number = number + 3;
                binding.rvTimeslot.smoothScrollToPosition(number);
            }
        });

    }

    private void init() {
        model = SharedPrefsManager.getInstance().getObject(SharePrefrenceConstraint.user, ResponseAuthentication.class);
        service_id = getIntent().getStringExtra("service_id");
//        Log.i("sfsf", "init: " + model.getResult());
        provider_Name = getIntent().getStringExtra("provider_Name");
        provider_img = getIntent().getStringExtra("provider_img");
        item = new Gson().fromJson(getIntent().getStringExtra("item"), ResultItem.class);
        binding.tvProviderName.setText(provider_Name);

        binding.tvServiceName.setText(item.getServiceName());
        binding.tvServicePrice.setText("$" + item.getServicePrice() + ".00");

    }

    private void setData() {
        Picasso.get().load(item.getImage1()).placeholder(R.drawable.user_placeholder).into(binding.ivImage);
        Picasso.get().load(provider_img).placeholder(R.drawable.user_placeholder).into(binding.ivPorviderImg);
        if (model != null) {
            binding.tvEmail.setText(model.getResult().getEmail());
            binding.tvNo.setText(model.getResult().getMobile());
        }

    }

    private void timeAvailibity() {

        String sdfsdfsDate=new SimpleDateFormat("dd-MM-yyyy").format(selectedDateTime.getTimeInMillis());
//        binding.tvDate.setText(sdfsdfsDate);

        binding.llMain.setVisibility(View.GONE);
        binding.loaderLayout.loader.setVisibility(View.VISIBLE);
        RetrofitClient.getClient(NetworkConstraint.BASE_URL)
                .create(TimeAvailibity.class)
                .getAllTimeSlot(service_id, sdfsdfsDate/*String.valueOf(selectedDateTime.getTimeInMillis())*/, providerUserIdProvider)//providerUserId)
                .enqueue(new Callback<TimeAvailibityResponse>() {
                    @Override
                    public void onResponse(Call<TimeAvailibityResponse> call, Response<TimeAvailibityResponse> response) {
                        Log.i("cxvxvxv", "onResponse: " + response.body());
                        Log.i("cxvxvxv", "onResponse: " + response.toString());

//                        ++API_COUNT;
//                        if (API_COUNT == ALL_API_COUNT) {
                            binding.llMain.setVisibility(View.VISIBLE);
                            binding.loaderLayout.loader.setVisibility(View.GONE);

//                        }
                        if (response.body().getResult().getTime() != null) {
                            timeItems = new ArrayList<>();
                            timeItems.addAll(response.body().getResult().getTime());
                            timeSlotAdapter.setTimeItems(timeItems);
                            timeSlotAdapter.notifyDataSetChanged();

                            Log.i("sfdddds", "onResponse: " + timeItems.size());
                            if (timeItems.size() <= 0) {
                                binding.Nofound.setVisibility(View.VISIBLE);
                                binding.RRBook.setEnabled(false);
                                binding.RRBook.setAlpha(0.7f);
//                                binding.rvTimeslot.setVisibility(View.GONE);
                                binding.nextimage.setVisibility(View.GONE);
                            } else {
                                binding.Nofound.setVisibility(View.GONE);
                                binding.RRBook.setEnabled(true);
                                binding.RRBook.setAlpha(1f);
//                                binding.rvTimeslot.setVisibility(View.VISIBLE);
                                binding.nextimage.setVisibility(View.VISIBLE);
                            }
                        }

//                        if (timeItems.size()>0) {
//                            date = new SimpleDateFormat("dd-MMM-yyyy").format(selectedDateTime.getTimeInMillis());
//                             binding.appointmentDT.setText(date + " " + timeItems.get(0).getStart());
//
//                        }

                    }

                    @Override
                    public void onFailure(Call<TimeAvailibityResponse> call, Throwable t) {
//                        ++API_COUNT;
//                        if (API_COUNT == ALL_API_COUNT) {
                            binding.loaderLayout.loader.setVisibility(View.GONE);
//                        }
                        Log.i("xvcbcvxvdv", "onFailure: " + t.getMessage());
                    }
                });
    }


    private void setUpDatePicker() {

        List<CalendarConstraints.DateValidator> validators = new ArrayList<>();
        validators.add(DateValidatorPointForward.from(selectedDateTime.getTimeInMillis()));

        MaterialDatePicker picker = MaterialDatePicker.Builder.datePicker()
                .setTitleText(R.string.select_date)
                .setTheme(R.style.ThemeMaterialCalendar)
                .setCalendarConstraints(
                        new CalendarConstraints.Builder().
                                setStart(MaterialDatePicker.todayInUtcMilliseconds()).
                                setValidator(CompositeDateValidator.allOf(validators)).
                                build()).
                        build();

        binding.llDate.setOnClickListener(v -> {
            picker.show(getSupportFragmentManager(), "tag");
        });

        picker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
            @Override
            public void onPositiveButtonClick(Long selection) {
                selectedDateTime.setTime(new Date(selection));
                binding.tvDate.setText(new SimpleDateFormat("dd-MM-yyyy").format(selectedDateTime.getTimeInMillis()));
                availibitiydate = new SimpleDateFormat("dd-MM-yyyy").format(selectedDateTime.getTimeInMillis());
                nextdateAvalibility();
                if (start_time != null)
                    binding.appointmentDT.setText(availibitiydate + " " + start_time);

            }
        });

        selectedDateTime.setTime(new Date());
        availibitiydate = new SimpleDateFormat("dd-MM-yyyy").format(selectedDateTime.getTimeInMillis());
        nextdateAvalibility();

    }

    private void nextdateAvalibility() {
        binding.loaderLayout.loader.setVisibility(View.VISIBLE);

        RetrofitClient.getClient(NetworkConstraint.BASE_URL)
                .create(TimeAvailibity.class)
                .getAllTimeSlot(service_id, availibitiydate, providerUserIdProvider)//providerUserId)
                .enqueue(new Callback<TimeAvailibityResponse>() {
                    @Override
                    public void onResponse(Call<TimeAvailibityResponse> call, Response<TimeAvailibityResponse> response) {
                        binding.loaderLayout.loader.setVisibility(View.GONE);
                        timeItems = new ArrayList<>();
                        timeItems.addAll(response.body().getResult().getTime());
                        timeSlotAdapter.setTimeItems(timeItems);
                        timeSlotAdapter.notifyDataSetChanged();
//                         date = new SimpleDateFormat("dd-MMM-yyyy").format(selectedDateTime.getTimeInMillis());
//                        binding.appointmentDT.setText(date + " " + timeItems.get(0).getStart());

                        Log.i("sfdddds", "onResponse: " + timeItems.size());
                        if (timeItems.size() == 0) {
                            binding.Nofound.setVisibility(View.VISIBLE);
                            binding.RRBook.setEnabled(false);
                            binding.RRBook.setAlpha(0.7f);
                            binding.rvTimeslot.setVisibility(View.GONE);
                            binding.nextimage.setVisibility(View.GONE);
                        } else {
                            binding.Nofound.setVisibility(View.GONE);
                            binding.RRBook.setEnabled(true);
                            binding.RRBook.setAlpha(1f);
                            binding.rvTimeslot.setVisibility(View.VISIBLE);
                            binding.nextimage.setVisibility(View.VISIBLE);

                        }
                    }

                    @Override
                    public void onFailure(Call<TimeAvailibityResponse> call, Throwable t) {
                        binding.loaderLayout.loader.setVisibility(View.GONE);

                        Log.i("sfdddds", "onResponse: " + timeItems.size());
                        if (timeItems.size() == 0) {
                            binding.Nofound.setVisibility(View.VISIBLE);
                            binding.RRBook.setEnabled(false);
                            binding.RRBook.setAlpha(0.7f);
                            binding.rvTimeslot.setVisibility(View.GONE);
                            binding.nextimage.setVisibility(View.GONE);
                        } else {
                            binding.Nofound.setVisibility(View.GONE);
                            binding.RRBook.setEnabled(true);
                            binding.RRBook.setAlpha(1f);
                            binding.rvTimeslot.setVisibility(View.VISIBLE);
                            binding.nextimage.setVisibility(View.VISIBLE);

                        }
                    }
                });

    }

    private boolean validate() {
//        if (TextUtils.isEmpty(binding.tvEmail.getText().toString().replace(" ", ""))) {
//            Snackbar.make(findViewById(android.R.id.content),
//                    R.string.enter_email,
//                    Snackbar.LENGTH_SHORT).show();
//            return false;
//        } else if (!Utils.EMAIL_ADDRESS_PATTERN.matcher(binding.tvEmail.getText().toString().replace(" ", "")).matches()
//        ) {
//            Snackbar.make(findViewById(android.R.id.content),
//                    R.string.text_register_correct_email,
//                    Snackbar.LENGTH_SHORT).show();
//            return false;
//        } else if (TextUtils.isEmpty(binding.tvNo.getText().toString().replace(" ", ""))) {
//            Snackbar.make(findViewById(android.R.id.content), R.string.text_register_no, Snackbar.LENGTH_SHORT).show();
//            return false;
//        }
//        else if (binding.tvNo.getText().toString().replace(" ", "").length() <7) {
//            Snackbar.make(findViewById(android.R.id.content),
//                    R.string.text_register_right_no,
//                    Snackbar.LENGTH_SHORT).show();
//            return false;
//        }
//        else
        if (binding.appointmentDT.getText().equals(getString(R.string.select_time_availability))) {

            Snackbar.make(findViewById(android.R.id.content),
                    getString(R.string.please_select_time_availability),
                    Snackbar.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }


    private void initProviderAllUser() {
        itemProvider = new Gson().fromJson(getIntent().getStringExtra("item"), com.my.sadebuser.act.model.servicelist.ResultItem.class);
        getAllProviderUser();


//        binding.ivBack.setOnClickListener(v -> {
//            finish();
//        });
        binding.rvAllUsers.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));

        adapter = new ProviderAllUserAdapter(list, new ProviderAllUserAdapter.SelectedItem() {
            @Override
            public void selectedItem(int position) {
                sProvider = "Aamir";
                providerUserIdProvider = list.get(position).getId();
                Log.i("dsfsfsfds", "selectedItem: " + providerUserIdProvider);
                nextdateAvalibility();
            }
        });
        binding.rvAllUsers.setAdapter(adapter);
//        binding.tvNext.setOnClickListener(v -> {
//            Log.i("fsfsffsfs", "init: "+item.toString());
//            String provider_Name = getIntent().getStringExtra("provider_Name");
//            String provider_img = getIntent().getStringExtra("provider_img");
//            String service_id = getIntent().getStringExtra("service_id");
//
//            if (!s.equals("")){
//                Intent intent = new Intent(ProviderAllUserActivity.this, ShopTimeAvailavility.class);
//                intent.putExtra("service_id", service_id);
//                intent.putExtra("provider_Name", provider_Name);
//                intent.putExtra("provider_img", provider_img);
//                intent.putExtra("providerUserId", providerUserId);
//                intent.putExtra("item", new Gson().toJson(item));
//                startActivity(intent);
//            }else {
//                Snackbar.make(findViewById(android.R.id.content),
//                        getString(R.string.please_select_a_provider),
//                        Snackbar.LENGTH_SHORT).show();
//            }
//        });
    }

    private void getAllProviderUser() {
        binding.loaderLayoutProvider.loader.setVisibility(View.VISIBLE);
        if (itemProvider != null) {
            RetrofitClient.getClient(NetworkConstraint.BASE_URL)
                    .create(BookingRequest.class)
                    .getmyServicesdetail(itemProvider.getId())
                    .enqueue(new Callback<JsonElement>() {
                        @Override
                        public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                            binding.loaderLayoutProvider.loader.setVisibility(View.GONE);
                            if (response != null) {
                                if (response.isSuccessful()) {
                                    JsonObject object = response.body().getAsJsonObject();
                                    int status = object.get("status").getAsInt();
                                    if (status == 1) {
                                        ServiceDetailResponse authentication = new Gson().fromJson(object, ServiceDetailResponse.class);
                                        list.clear();
                                        list.addAll(authentication.getResult().getServiceUser());
                                        if(list.size()>0){
                                            adapter.setSelectedPosition(0);
                                            providerUserIdProvider = list.get(0).getId();
                                        }
                                        adapter.notifyDataSetChanged();
                                        for (int i = 0; i < list.size(); i++) {
                                            if (!authentication.getResult().getServiceUser().get(i).getBookingStatus().equals("Free")) {
                                                list.remove(i);
                                                adapter.notifyDataSetChanged();
                                                break;
                                            }
                                        }
                                        if (list.size() > 0) {
                                            binding.tvNoUserFound.setVisibility(View.GONE);
                                        }
                                    } else {
                                        if (list.size() == 0) {
                                            binding.tvNoUserFound.setVisibility(View.VISIBLE);
//                                            binding.tvNext.setAlpha(0.5f);
//                                            binding.tvNext.setEnabled(false);
                                        }
                                        ResponseAuthError authentication = new Gson().fromJson(object, ResponseAuthError.class);
                                        Log.i("dscbhs", "onResponse: " + authentication);
                                        Snackbar.make(findViewById(android.R.id.content),
                                                authentication.getResult(),
                                                Snackbar.LENGTH_SHORT).show();
                                    }

                                    timeAvailibity();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<JsonElement> call, Throwable t) {
                            binding.loaderLayoutProvider.loader.setVisibility(View.GONE);
                            Log.i("sfssfs", "onResponse: " + t.getMessage());
                        }
                    });
        }
    }

    private void booking(String email, String no) {

        String language= SharedPrefsManager.getInstance().getString("language");

        if (model != null) {
            binding.loaderLayout.loader.setVisibility(View.VISIBLE);
            RetrofitClient.getClient(NetworkConstraint.BASE_URL)
                    .create(BookingRequest.class)
                    .booking(model.getResult().getId(),
                            service_id,
                            date,
                            start_time, end_time,
                            email,
                            no,
                            "22.7196°",
                            "75.8577°",
                            providerUserIdProvider,
                            language)
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
                                        Toast.makeText(ShopTimeAvailavility.this, getString(R.string.your_request_is_successfully_sent_to_admin), Toast.LENGTH_SHORT).show();
                                        BookingResponse authentication = new Gson().fromJson(object, BookingResponse.class);
                                        Log.i("adsfscs", "authen: " + authentication.getResult().toString());
                                        finish();
//                                        startActivity(new Intent(BookingDetails.this, PaymentOption.class));
                                    } else {
                                        ResponseAuthError authentication = new Gson().fromJson(object, ResponseAuthError.class);
                                        Snackbar.make(findViewById(android.R.id.content),
                                                authentication.getResult(),
                                                Snackbar.LENGTH_SHORT).show();
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