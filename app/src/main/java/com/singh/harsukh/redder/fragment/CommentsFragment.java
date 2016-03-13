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

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class CommentsFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private RedditLink mParam2;

    private OnListFragmentInteractionListener mListener;
    private List<RedditObject> mRedditObjects;
    private List<RedditComment> mComments;
    private List<RedditResponse<RedditListing>> mRedditList;
    private CommentAdapter mCommentAdapter;

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

        mCommentAdapter = new CommentAdapter(mComments,mListener);
        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
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


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
                        mComments.add(comment);
                    }
                }
                mCommentAdapter.swapList(mComments);
            }

            @Override
            public void onFailure(Throwable t) {

            }

        });
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(RedditComment item);
    }
}
