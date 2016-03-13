package com.singh.harsukh.redder.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.singh.harsukh.redder.R;
import com.singh.harsukh.redder.fragment.CommentsFragment;
import com.singh.harsukh.redder.model.Reddit.RedditComment;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {

    private List<RedditComment> mValues;
    private CommentsFragment.OnListFragmentInteractionListener mListener;

    public CommentAdapter(List<RedditComment> items, CommentsFragment.OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
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
        holder.mAuthor.setText(mValues.get(position).getAuthor());
        holder.mScore.setText(String.valueOf(mValues.get(position).getScore()));
        //holder.mCreated.setText(String.valueOf(mValues.get(position).getCreated_utc()));

        holder.mComment.setText(mValues.get(position).getBody());


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
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
