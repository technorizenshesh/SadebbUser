package com.my.sadebuser.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.my.sadebuser.R;
import com.my.sadebuser.act.model.search.ResultItem;

import java.util.List;


public class SuggestionAdapter extends RecyclerView.Adapter<SuggestionAdapter.SuggestionAdapter_View> {

    private final List<ResultItem> list;
    private ClickcallBack clickcallBack;

    public SuggestionAdapter(List<ResultItem> list,ClickcallBack clickcallBack) {
        this.list=list;
        this.clickcallBack=clickcallBack;
    }

    @Override
    public SuggestionAdapter_View onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.suggestion, parent, false);
        return new SuggestionAdapter_View(view);
    }

    @Override
    public void onBindViewHolder(SuggestionAdapter_View holder, int position) {
        holder.tv_Name.setText(list.get(position).getUserName());
        holder.tv_Name.setOnClickListener(v -> {
            clickcallBack.curruntposition(position);
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class SuggestionAdapter_View extends RecyclerView.ViewHolder {
        private TextView tv_Name;
        public SuggestionAdapter_View(View itemView) {
            super(itemView);
            tv_Name=itemView.findViewById(R.id.tv_Name);
        }
    }

    public interface ClickcallBack{
        void curruntposition(int pos);
    }
}
