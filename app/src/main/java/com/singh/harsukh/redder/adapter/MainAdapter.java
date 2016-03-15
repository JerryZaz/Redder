package com.singh.harsukh.redder.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.singh.harsukh.redder.MainActivity;
import com.singh.harsukh.redder.R;
import com.singh.harsukh.redder.model.Reddit.RedditLink;
import com.singh.harsukh.redder.utils.Utilities;

import java.util.Date;
import java.util.List;

/**
 * Created by nano1 on 3/5/2016.
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private List<RedditLink> mLinks;
    private Context context;
    private ClickListener clickListener;

    public MainAdapter(Context context, List<RedditLink> mLinks) {
        this.mLinks = mLinks;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_single_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        String mUserName = trimUsername(mLinks.get(position).getAuthor());
        holder.mTextViewTitle.setText(mLinks.get(position).getTitle());
        holder.mTextViewUserName.setText(mUserName);
        holder.mTextViewScore.setText(String.valueOf(mLinks.get(position).getScore()));
        holder.mTextViewNumComments.setText(String.valueOf(mLinks.get(position).getNum_comments()));
        Date date = mLinks.get(position).getCreated_utc().toDate();
        holder.mTextViewTime.setText(Utilities.getDiff(date));

        if (mLinks.get(position).getPreview() != null) {

            final String url = mLinks.get(position).getPreview().getImages().get(0).getSource().getUrl();
            Uri uri = Uri.parse(url);
            holder.mImageViewItem.setImageURI(uri);

            holder.mImageViewItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity)context).customTab(url, context);
                }
            });
        }
        holder.itemView.setTag(mLinks);
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public int getItemCount() {
        return mLinks != null ? mLinks.size() : 0;
    }

    public String trimUsername(String name) {

        if (name.length() > 10) {
            name = name.substring(0, 7) + "...";
        }
        return name;
    }

    public void swapList(List<RedditLink> redditLinks) {
        if (mLinks != null) {
            mLinks.clear();
            mLinks.addAll(redditLinks);
        } else {
            mLinks = redditLinks;
        }
        notifyDataSetChanged();
    }

    public interface ClickListener {
        void itemClicked(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mTextViewTitle;
        TextView mTextViewUserName;
        TextView mTextViewScore;
        TextView mTextViewNumComments;
        TextView mTextViewTime;
        SimpleDraweeView mImageViewItem;
        ImageView mImageViewVoteUp;
        ImageView mImageViewVoteDown;
        ImageView mImageViewSave;

        public ViewHolder(final View itemView) {
            super(itemView);
            mTextViewTitle = (TextView) itemView.findViewById(R.id.text_title);
            mTextViewUserName = (TextView) itemView.findViewById(R.id.text_username);
            mTextViewScore = (TextView) itemView.findViewById(R.id.text_score);
            mTextViewNumComments = (TextView) itemView.findViewById(R.id.text_comments);
            mTextViewTime = (TextView) itemView.findViewById(R.id.text_time);
            mImageViewItem = (SimpleDraweeView) itemView.findViewById(R.id.image_item);
            mImageViewVoteUp = (ImageView) itemView.findViewById(R.id.image_vote_up);
            mImageViewVoteDown = (ImageView) itemView.findViewById(R.id.image_vote_down);
            mImageViewSave = (ImageView) itemView.findViewById(R.id.image_save);

            mImageViewVoteUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Login to Vote", Toast.LENGTH_SHORT).show();
                }
            });
            mImageViewVoteDown.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Login to Vote", Toast.LENGTH_SHORT).show();
                }
            });
            mImageViewSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Login to Save", Toast.LENGTH_SHORT).show();
                }
            });

            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            if (clickListener != null) {
                clickListener.itemClicked(v, getPosition());
            }
        }
    }
}
