package com.singh.harsukh.redder.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.singh.harsukh.redder.R;
import com.singh.harsukh.redder.model.Reddit.RedditComment;
import com.singh.harsukh.redder.utils.Utilities;

import org.joda.time.DateTime;

import java.util.Date;
import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {

    private List<RedditComment> mValues;
    private Context mContext;

    public CommentAdapter(List<RedditComment> items, Context context) {
        mValues = items;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_comment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

        holder.mScore.setText(String.valueOf(mValues.get(position).getScore()));
        DateTime dateTime = mValues.get(position).getCreated_utc();
        Date date = dateTime.toDate();
        holder.mCreated.setText(Utilities.getDiff(date));

        StringBuilder builder, builder1;
                builder = new StringBuilder();
                builder1 = new StringBuilder();

        for(int i = 0; i < mValues.get(position).getDepth(); i++){
            builder.append("-");
            builder1.append("-");
        }
        builder1.append(mValues.get(position).getAuthor());
        builder.append(mValues.get(position).getBody());

        holder.mComment.setText(builder.toString());
        holder.mAuthor.setText(builder1.toString());
        //holder.mSubComment.setText(mValues.get(position).getReplies());

    }

    @Override
    public int getItemCount() {
        return mValues != null ? mValues.size() : 0;
    }

    public void swapList(List<RedditComment> redditLinks) {
        if (mValues != null) {
            mValues.clear();
            mValues.addAll(redditLinks);
        } else {
            mValues = redditLinks;
        }
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mAuthor;
        public final TextView mScore;
        public final TextView mCreated;
        public final TextView mComment;
        public RedditComment mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mAuthor = (TextView) view.findViewById(R.id.text_comm_author);
            mScore = (TextView) view.findViewById(R.id.text_comm_score);
            mCreated = (TextView) view.findViewById(R.id.text_comm_created);
            mComment = (TextView) view.findViewById(R.id.text_comment);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mScore.getText() + "'";
        }
    }
}
