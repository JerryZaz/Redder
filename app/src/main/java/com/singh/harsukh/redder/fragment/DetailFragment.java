package com.singh.harsukh.redder.fragment;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.singh.harsukh.redder.R;
import com.singh.harsukh.redder.model.Reddit.RedditLink;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DetailFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
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
    ImageView mImageView;
    ImageView mImageViewVoteUp;
    ImageView mImageViewVoteDown;
    ImageView mImageViewSave;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private RedditLink mParam2;
    private OnFragmentInteractionListener mListener;

    public DetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailFragment.
     */
    // TODO: Rename and change types and number of parameters
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
        mImageView = (ImageView) view.findViewById(R.id.image_detail_item);
        mImageViewVoteUp = (ImageView) view.findViewById(R.id.image_detail_vote_up);
        mImageViewVoteDown = (ImageView) view.findViewById(R.id.image_detail_vote_down);
        mImageViewSave = (ImageView) view.findViewById(R.id.image_detail_save);

        mTextViewTitle.setText(mParam2.getTitle());
        mTextViewUserName.setText(mParam2.getAuthor());
        mTextViewScore.setText(String.valueOf(mParam2.getScore()));
        mTextViewNumComments.setText(String.valueOf(mParam2.getNum_comments()));
        mTextViewTime.setText(mParam2.getCreated_utc());

        if (mParam2.getPreview() != null) {

            Picasso.with(getActivity())
                    .load(mParam2.getPreview().getImages().get(0).getSource().getUrl())
                    .into(mImageView);
        }

        // Inflate the layout for this fragment
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
