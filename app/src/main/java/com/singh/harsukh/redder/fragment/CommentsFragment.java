package com.singh.harsukh.redder.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.singh.harsukh.redder.R;
import com.singh.harsukh.redder.adapter.CommentAdapter;
import com.singh.harsukh.redder.data.RedditService;
import com.singh.harsukh.redder.model.Reddit.RedditComment;
import com.singh.harsukh.redder.model.Reddit.RedditLink;
import com.singh.harsukh.redder.model.Reddit.RedditListing;
import com.singh.harsukh.redder.model.Reddit.RedditObject;
import com.singh.harsukh.redder.model.Reddit.RedditResponse;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentsFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Customize parameters
    protected int mColumnCount = 1;
    protected RedditLink mParam2;
    protected List<RedditObject> mRedditObjects;
    protected List<RedditComment> mComments;
    protected List<RedditResponse<RedditListing>> mRedditList;
    protected CommentAdapter mCommentAdapter;

    private Vector<RedditComment> vComments;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CommentsFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static CommentsFragment newInstance(int columnCount, RedditLink param2) {
        CommentsFragment fragment = new CommentsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        args.putParcelable(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
            mParam2 = getArguments().getParcelable(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comment_list, container, false);

        mCommentAdapter = new CommentAdapter(mComments, getActivity());
        vComments = new Vector<>();
        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(context)
                    .size(5)
                    .build());
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(mCommentAdapter);
        }
        fetchComments(mParam2.getSubreddit(), mParam2.getId());
        return view;
    }


    public void fetchComments(String subreddit, String link_id) {
        mComments = new ArrayList<>();
        Call<List<RedditResponse<RedditListing>>> call = RedditService.Implementation.get().getComments(subreddit, link_id);
        call.enqueue(new Callback<List<RedditResponse<RedditListing>>>() {
            @Override
            public void onResponse(Response<List<RedditResponse<RedditListing>>> response) {
                mRedditList = response.body();
                mRedditObjects = mRedditList.get(1).getData().getChildren();
                for (RedditObject child : mRedditObjects) {
                    if (child instanceof RedditComment) {
                        RedditComment comment = (RedditComment) child;
                        vComments.add(comment);
                        getChildren(comment);
                    }
                    mCommentAdapter.swapList(new ArrayList<>(vComments));
                }


            }

            @Override
            public void onFailure(Throwable t) {

            }

        });
    }

    private void getChildren(RedditComment comment) {
        if(comment.getReplies() != null){
            RedditListing repliesListing = (RedditListing) comment.getReplies();
            for(RedditObject object : repliesListing.getChildren()){
                if(object instanceof RedditComment){
                    RedditComment childComment = (RedditComment) object;
                    childComment.setDepth(comment.getDepth() + 1);
                    vComments.add(childComment);
                    getChildren(childComment);
                }
            }
        }
    }

}