package com.singh.harsukh.redder.fragment;

import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.singh.harsukh.redder.R;
import com.singh.harsukh.redder.model.Reddit.RedditLink;

public class DetailFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    TextView mTextViewTitle;
    TextView mTextViewUserName;
    TextView mTextViewScore;
    TextView mTextViewNumComments;
    TextView mTextViewTime;
    SimpleDraweeView mImageView;
    ImageView mImageViewVoteUp;
    ImageView mImageViewVoteDown;
    ImageView mImageViewSave;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private RedditLink mParam2;

    public DetailFragment() {
        // Required empty public constructor
    }

    public static DetailFragment newInstance(String param1, RedditLink param2) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putParcelable(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getParcelable(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        mTextViewTitle = (TextView) view.findViewById(R.id.text_detail_title);
        mTextViewUserName = (TextView) view.findViewById(R.id.text_detail_username);
        mTextViewScore = (TextView) view.findViewById(R.id.text_detail_score);
        mTextViewNumComments = (TextView) view.findViewById(R.id.text_detail_comments);
        mTextViewTime = (TextView) view.findViewById(R.id.text_detail_time);
        mImageView = (SimpleDraweeView) view.findViewById(R.id.image_detail_item);
        mImageViewVoteUp = (ImageView) view.findViewById(R.id.image_detail_vote_up);
        mImageViewVoteDown = (ImageView) view.findViewById(R.id.image_detail_vote_down);
        mImageViewSave = (ImageView) view.findViewById(R.id.image_detail_save);
        mTextViewTitle.setText(mParam2.getTitle());
        mTextViewUserName.setText(mParam2.getAuthor());
        mTextViewScore.setText(String.valueOf(mParam2.getScore()));
        mTextViewNumComments.setText(String.valueOf(mParam2.getNum_comments()));
//        DateTime datetime = mParam2.getCreated_utc();
//        Date date = datetime.toDate();
//        mTextViewTime.setText(Utilities.getDiff(date));


        if (mParam2.getPreview() != null) {
            String url = mParam2.getPreview().getImages().get(0).getSource().getUrl();
            Uri uri = Uri.parse(url);
            mImageView.setImageURI(uri);
        }
        // Inflate the layout for this fragment
        return view;
    }
}
