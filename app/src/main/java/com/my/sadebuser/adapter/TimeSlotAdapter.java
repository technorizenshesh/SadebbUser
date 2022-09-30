package com.my.sadebuser.adapter;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.my.sadebuser.R;
import com.my.sadebuser.act.model.timeavailibity.TimeItem;
import com.my.sadebuser.utils.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class TimeSlotAdapter extends RecyclerView.Adapter<TimeSlotAdapter.TimeSlotAdapter_View> {

    private List<TimeItem> timeItems;
    private final ClickCallback callback;
    private int row_index = -1;


    public TimeSlotAdapter(/*List<TimeItem> timeItems,*/ ClickCallback callback) {
//        this.timeItems = timeItems;
        this.callback = callback;
    }

    public void setTimeItems(List<TimeItem> timeItems) {
        this.timeItems = timeItems;
    }

    @Override
    public TimeSlotAdapter_View onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.time_slot, parent, false);
        view.getLayoutParams().width = (Utils.getScreenWidth(view.getContext()) - Utils.dpToPx(view.getContext(), 16)) / 2; /// THIS LINE WILL DIVIDE OUR VIEW INTO NUMBERS OF PARTS

        return new TimeSlotAdapter_View(view);
    }

    @Override
    public void onBindViewHolder(TimeSlotAdapter.TimeSlotAdapter_View holder, int position) {


        String start_time = timeItems.get(position).getStart();
        String end_time = timeItems.get(position).getEnd();
        SimpleDateFormat _24HourSDF = new SimpleDateFormat("HH:mm");
        SimpleDateFormat _12HourSDF = new SimpleDateFormat("hh:mm a");
        try {
            String a = _12HourSDF.format(_24HourSDF.parse(start_time));
            String b = _12HourSDF.format(_24HourSDF.parse(end_time));
            holder.tv_time.setText(a + " - " + b);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
        if (timeItems.get(position).getStatus().equals("false")) {
            holder.tv_time.setBackgroundResource(R.drawable.bg_stroke_blue);
            holder.tv_time.setTextColor(Color.parseColor("#0053B4"));
            if (row_index == position) {
                holder.tv_time.setBackgroundResource(R.drawable.bg_gray_new);
                holder.tv_time.setTextColor(Color.parseColor("#FFFFFF"));

            } else {
                holder.tv_time.setBackgroundResource(R.drawable.bg_stroke_blue);
                holder.tv_time.setTextColor(Color.parseColor("#0053B4"));

            }
        } else {
            Log.i("xzcv ", "onBindViewHolder: "+131);
            holder.tv_time.setBackgroundResource(R.drawable.backgroud_gray);
            holder.tv_time.setTextColor(Color.parseColor("#9F9FA6"));
        }
        } catch (Exception e) {
            e.printStackTrace();
        }


        holder.tv_time.setOnClickListener(v -> {
            callback.click(position);
            if (timeItems.get(position).getStatus().equals("false")) {
                row_index = position;
                notifyDataSetChanged();
                holder.tv_time.setBackgroundResource(R.drawable.bg_gray_new);
            }
        });





    }

    @Override
    public int getItemCount() {
        return timeItems.size();
    }


    private TimeItem getItem(int position) {
        return timeItems.get(position);
    }


    public interface ClickCallback {
        void click(int positoin);
    }

    public class TimeSlotAdapter_View extends RecyclerView.ViewHolder {

        private final TextView tv_time;

        public TimeSlotAdapter_View(View itemView) {
            super(itemView);
            tv_time = itemView.findViewById(R.id.tv_time);
        }
    }
}
