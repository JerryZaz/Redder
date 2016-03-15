package com.singh.harsukh.redder.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.singh.harsukh.redder.LinkActivity;
import com.singh.harsukh.redder.MainActivity;
import com.singh.harsukh.redder.R;
import com.singh.harsukh.redder.adapter.MainAdapter;
import com.singh.harsukh.redder.data.RedditService;
import com.singh.harsukh.redder.model.Reddit.RedditLink;
import com.singh.harsukh.redder.model.Reddit.RedditListing;
import com.singh.harsukh.redder.model.Reddit.RedditObject;
import com.singh.harsukh.redder.model.Reddit.RedditResponse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by nano1 on 3/5/2016.
 */
public class MainFragment extends Fragment implements MainAdapter.ClickListener {

    RedditResponse<RedditListing> mRedditListing;
    private List<RedditObject> mRedditObjects;
    private List<RedditLink> mRedditLinks;
    private MainAdapter mainAdapter;
    private RecyclerView recyclerView;
    private String mSection;

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_main, container, false);
        recyclerView = (RecyclerView) layout.findViewById(R.id.main_recycleView);
        Bundle bundle = getArguments();
        if (bundle.isEmpty()) {
            mSection = "all";
        }
        else{
            mSection = bundle.getString("title");
        }

        mainAdapter = new MainAdapter(getActivity(),mRedditLinks);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(mainAdapter);
        fetchData(mSection);
        mainAdapter.notifyDataSetChanged();
        recyclerView.refreshDrawableState();

        return layout;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mainAdapter.setClickListener(this);
        ((MainActivity) getActivity()).setActionBarTitle(mSection);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void fetchData(String subreddit) {
        mRedditLinks = new ArrayList<>();
        Call<RedditResponse<RedditListing>> call = RedditService.Implementation.get().getSubreddit(subreddit);
        call.enqueue(new Callback<RedditResponse<RedditListing>>() {
                         @Override
                         public void onResponse(Response<RedditResponse<RedditListing>> response) {

                             mRedditListing = response.body();
                             mRedditObjects = mRedditListing.getData().getChildren();
                             for (RedditObject child : mRedditObjects) {
                                 RedditLink link = (RedditLink) child;
                                 mRedditLinks.add(link);
                             }
                             mainAdapter.swapList(mRedditLinks);
                         }

                         @Override
                         public void onFailure(Throwable t) {
                             Toast.makeText(getActivity(),"Connection Failed",Toast.LENGTH_SHORT).show();
                         }
                     }
        );
    }

    @Override
    public void itemClicked(View view, int position) {

        Intent intent = new Intent(getActivity(), LinkActivity.class);
        intent.putExtra("subreddit", mRedditLinks.get(position));
        getActivity().startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_sort_title:
                Collections.sort(mRedditLinks, new Comparator<RedditLink>() {
                    @Override
                    public int compare(RedditLink lhs, RedditLink rhs) {
                        return lhs.getTitle().compareTo(rhs.getTitle());
                    }
                });
                break;
            case R.id.action_sort_score:
                Collections.sort(mRedditLinks, Collections.reverseOrder(new Comparator<RedditLink>() {
                    @Override
                    public int compare(RedditLink lhs, RedditLink rhs) {
                        return lhs.getScore() - rhs.getScore();
                    }
                }));
                break;
            case R.id.action_sort_created:
                Collections.sort(mRedditLinks, Collections.reverseOrder(new Comparator<RedditLink>() {
                    @Override
                    public int compare(RedditLink lhs, RedditLink rhs) {
                        return Long.valueOf(lhs.getCreated()).compareTo(rhs.getCreated());
                    }
                }));
                break;
        }

        mainAdapter.notifyDataSetChanged();
        recyclerView.refreshDrawableState();
        return false;
    }
}
