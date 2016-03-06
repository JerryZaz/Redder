package com.singh.harsukh.redder.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.singh.harsukh.redder.R;
import com.singh.harsukh.redder.model.Listing;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by nano1 on 3/5/2016.
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private List<Listing.DataEntity.ChildrenEntity> childrenEntities;
    private Context context;
    private LayoutInflater inflater;

    public MainAdapter(Context context, List<Listing.DataEntity.ChildrenEntity> childrenEntities) {
        this.childrenEntities = childrenEntities;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.main_single_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Listing.DataEntity.ChildrenEntity  childrenEntity = childrenEntities.get(position);
        String mUserName = trimUsername(childrenEntity.getData().getAuthor());

        holder.mTextViewTitle.setText(childrenEntity.getData().getTitle());
        holder.mTextViewUserName.setText(mUserName);

        //int
        holder.mTextViewScore.setText(String.valueOf(childrenEntity.getData().getScore()));
        holder.mTextViewNumComments.setText(String.valueOf(childrenEntity.getData().getNum_comments()));
        //holder.mTextViewTime.setText(String.valueOf(childrenEntity.getData().getCreated_utc()));

        if (!childrenEntity.getData().getThumbnail().equals(null)) {
            holder.mImageViewItem.setVisibility(View.VISIBLE);

            Picasso.with(context)
                    .load(childrenEntity.getData().getUrl())
                    .into(holder.mImageViewItem);
        }
    }

    @Override
    public int getItemCount() {
       return (null != childrenEntities ? childrenEntities.size() : 0);
    }

    public String trimUsername(String name){

        if (name.length() > 10){
            name = name.substring(0,7) + "...";
        }
        return name;
    }

    public void swapList(List<Listing.DataEntity.ChildrenEntity> childrenEntities){
        if (this.childrenEntities != null) {
            this.childrenEntities.clear();
            this.childrenEntities.addAll(childrenEntities);
        }else{
            this.childrenEntities = childrenEntities;
        }
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTextViewTitle;
        TextView mTextViewUserName;
        TextView mTextViewScore;
        TextView mTextViewNumComments;
        TextView mTextViewTime;
        ImageView mImageViewItem;
        ImageView mImageViewVoteUp;
        ImageView mImageViewVoteDown;
        ImageView mImageViewSave;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextViewTitle = (TextView) itemView.findViewById(R.id.text_title);
            mTextViewUserName = (TextView) itemView.findViewById(R.id.text_username);
            mTextViewScore = (TextView) itemView.findViewById(R.id.text_score);
            mTextViewNumComments = (TextView) itemView.findViewById(R.id.text_comments);
            mTextViewTime = (TextView) itemView.findViewById(R.id.text_time);
            mImageViewItem = (ImageView) itemView.findViewById(R.id.image_item);
            mImageViewVoteUp = (ImageView) itemView.findViewById(R.id.image_vote_up);
            mImageViewVoteDown = (ImageView) itemView.findViewById(R.id.image_vote_down);
            mImageViewSave = (ImageView) itemView.findViewById(R.id.image_save);
        }
    }
}
