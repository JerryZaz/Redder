package com.singh.harsukh.redder.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.singh.harsukh.redder.R;

import java.util.ArrayList;


/**
 * Created by nano1 on 3/4/2016.
 */
public class SectionAdapter extends RecyclerView.Adapter<SectionAdapter.ViewHolder> {

    private ArrayList section;
    private LayoutInflater inflater;
    private Context context;
    private ClickListener clickListener;

    public SectionAdapter(Context context, ArrayList section){
        this.section = section;
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.single_section_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.sectionTextView.setText(section.get(position).toString());
    }

    public void setClickListener(ClickListener clickListener){
        this.clickListener = clickListener;
    }

    @Override
    public int getItemCount() {
        return (null != section ? section.size() : 0);
    }

    public interface ClickListener{
        void itemClicked(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView sectionTextView;

        public ViewHolder(final View itemView) {
            super(itemView);
            sectionTextView = (TextView) itemView.findViewById(R.id.section_textView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (clickListener!= null){
                clickListener.itemClicked(v, getPosition());
            }
        }
    }
}
