package com.singh.harsukh.redder.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.singh.harsukh.redder.LinkActivity;
import com.singh.harsukh.redder.R;
import com.singh.harsukh.redder.model.Reddit.RedditLink;
import com.squareup.picasso.Picasso;

import org.joda.time.DateTime;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by nano1 on 3/5/2016.
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private List<RedditLink> mLinks;
    private Context context;
    private ClickListener clickListener;

    public MainAdapter(Context context, List<RedditLink> links) {
        this.mLinks = links;
        this.context = context;
    }

    public static String getDiff(Date date) {
        Date date2 = new Date();
        long diff = getDateDiff(date, date2, TimeUnit.HOURS);
        if (diff > 24) {
            diff = getDateDiff(date, date2, TimeUnit.DAYS);
            return String.valueOf(diff) + "d";
        } else if (diff < 1) {
            diff = getDateDiff(date, date2, TimeUnit.MINUTES);
            return String.valueOf(diff) + "m";
        } else {
            return String.valueOf(diff) + "h";
        }
    }

    public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_single_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final RedditLink link = mLinks.get(position);
        String mUserName = trimUsername(link.getAuthor());

        holder.mTextViewTitle.setText(link.getTitle());
        holder.mTextViewUserName.setText(mUserName);
        holder.mTextViewScore.setText(String.valueOf(link.getScore()));
        holder.mTextViewNumComments.setText(String.valueOf(link.getNum_comments()));
        DateTime dataTime = link.getCreated_utc();
        Date date = dataTime.toDate();
        holder.mTextViewTime.setText(getDiff(date));

        Log.v("image", link.getUrl());
        if (!TextUtils.isEmpty(link.getThumbnail())) {
            Picasso.with(context).setIndicatorsEnabled(true);
            Picasso.with(context).setLoggingEnabled(true);

            Picasso.with(context)
                    .load(link.getUrl())
                    .resize(holder.mImageViewItem.getMeasuredWidth(), 500)
                    .centerCrop()
                    .into(holder.mImageViewItem);
            holder.mImageViewItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Open Image", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, LinkActivity.class);
                   intent.putExtra("image", link.getUrl());
                    context.startActivity(intent);
                }
            });
        }
        holder.itemView.setTag(link);
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
