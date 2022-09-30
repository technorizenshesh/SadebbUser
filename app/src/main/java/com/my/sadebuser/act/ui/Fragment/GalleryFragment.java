package com.my.sadebuser.act.ui.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.my.sadebuser.R;
import com.my.sadebuser.act.model.servicelist.AllServiceResponse;
import com.my.sadebuser.act.model.servicelist.ResultItem;
import com.my.sadebuser.act.model.timeavailibity.TimeAvailibityResponse;
import com.my.sadebuser.act.network.NetworkConstraint;
import com.my.sadebuser.act.network.RetrofitClient;
import com.my.sadebuser.act.network.time.TimeAvailibity;
import com.my.sadebuser.adapter.GalleryAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GalleryFragment extends Fragment {

    private View view;
    private RecyclerView rv_gallery;
    private GalleryAdapter  galleryAdapter;
    private AllServiceResponse response;
    private List<String> list=new ArrayList<>();
    ProgressBar progress;
    String Provider_id;
    public static GalleryFragment newInstance(AllServiceResponse response ,String Provider_id) {
        GalleryFragment f = new GalleryFragment();
        Bundle args = new Bundle();
        args.putString("response", new Gson().toJson(response));
        args.putString("Provider_id", Provider_id);
        f.setArguments(args);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_gallery, container, false);
        rv_gallery=view.findViewById(R.id.rv_gallery);
        progress=view.findViewById(R.id.progress);
        Bundle args = getArguments();
        if(args!=null){
            Log.i("Xsvd", "onCreateView: +"+2);
            Provider_id=args.getString("Provider_id");
            response = new Gson().fromJson(args.getString("response"),AllServiceResponse.class);

//            for (ResultItem resultItem : response.getResult()) {
//                list.add(resultItem.getImage1());
//                list.add(resultItem.getImage2());
//                list.add(resultItem.getImage3());
//                list.add(resultItem.getImage4());
//                list.add(resultItem.getImage5());
//                list.add(resultItem.getImage6());
//                list.add(resultItem.getImage7());
//            }

            RetrofitClient.getClient(NetworkConstraint.BASE_URL)
                    .create(TimeAvailibity.class)
                    .get_profile(Provider_id)
                    .enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            Log.i("cxvxvxv", "onResponse: " + response.body());
                            Log.i("cxvxvxv", "onResponse: " + response.toString());

                            progress.setVisibility(View.GONE);
//                        ++API_COUNT;
//                        if (API_COUNT == ALL_API_COUNT) {
//                            binding.llMain.setVisibility(View.VISIBLE);
//                            binding.loaderLayout.loader.setVisibility(View.GONE);

//                        }
                            if (response.body()!= null) {
//                                timeItems = new ArrayList<>();
//                                timeItems.addAll(response.body().getResult().getTime());
//                                timeSlotAdapter.setTimeItems(timeItems);
//                                timeSlotAdapter.notifyDataSetChanged();

                                try {
                                    JSONObject jsonObject=new JSONObject(response.body().string());
                                    if(jsonObject.optString("status").equals("1")) {
                                        JSONObject result=jsonObject.optJSONObject("result");
                                        list.add(result.optString("image1"));
                                        list.add(result.optString("image2"));
                                        list.add(result.optString("image3"));
                                        list.add(result.optString("image4"));
                                        list.add(result.optString("image5"));
                                        list.add(result.optString("image6"));
                                        list.add(result.optString("image7"));
                                        list.add(result.optString("image8"));
                                        list.add(result.optString("image9"));
                                    }

                                    rv_gallery.setLayoutManager(new GridLayoutManager(getContext(),3));
                                    galleryAdapter=new GalleryAdapter(list,getActivity());
                                    rv_gallery.setAdapter(galleryAdapter);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }


//                                Log.i("sfdddds", "onResponse: " + timeItems.size());
//                                if (timeItems.size() <= 0) {
//                                    binding.Nofound.setVisibility(View.VISIBLE);
//                                    binding.RRBook.setEnabled(false);
//                                    binding.RRBook.setAlpha(0.7f);
////                                binding.rvTimeslot.setVisibility(View.GONE);
//                                    binding.nextimage.setVisibility(View.GONE);
//                                } else {
//                                    binding.Nofound.setVisibility(View.GONE);
//                                    binding.RRBook.setEnabled(true);
//                                    binding.RRBook.setAlpha(1f);
////                                binding.rvTimeslot.setVisibility(View.VISIBLE);
//                                    binding.nextimage.setVisibility(View.VISIBLE);
//
//                                }
                            }

//                        if (timeItems.size()>0) {
//                            date = new SimpleDateFormat("dd-MMM-yyyy").format(selectedDateTime.getTimeInMillis());
//                             binding.appointmentDT.setText(date + " " + timeItems.get(0).getStart());
//
//                        }


                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            progress.setVisibility(View.GONE);

//                        ++API_COUNT;
//                        if (API_COUNT == ALL_API_COUNT) {
//                            binding.loaderLayout.loader.setVisibility(View.GONE);
//                        }
                            Log.i("xvcbcvxvdv", "onFailure: " + t.getMessage());
                        }
                    });



         }

        Log.i("sfdzffs", "onCreateView: "+response.getResult().toString());


        return view;
    }
}