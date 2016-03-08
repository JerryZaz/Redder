package com.singh.harsukh.redder.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.singh.harsukh.redder.R;
import com.singh.harsukh.redder.SpaceItemDecoration;
import com.singh.harsukh.redder.adapter.SectionAdapter;
import com.singh.harsukh.redder.utils.Utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SectionsFragment extends android.app.Fragment {

    public RecyclerView recyclerView;
    public Set<String> section;
    private OnFragmentInteractionListener mListener;
    private SectionAdapter sectionAdapter;

    public SectionsFragment() {
        // Required empty public constructor
    }

    public static SectionsFragment newInstance() {
        SectionsFragment fragment = new SectionsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_sections, container, false);

        section = new HashSet<>();
        getSections();
        sectionAdapter = new SectionAdapter(getActivity(), new ArrayList<>(section));
        recyclerView = (RecyclerView) v.findViewById(R.id.section_RecyclerView);
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.spacing);
        recyclerView.addItemDecoration(new SpaceItemDecoration(spacingInPixels));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(sectionAdapter);
        return v;
    }

    public void getSections(){
        SharedPreferences preferences = getActivity().getSharedPreferences(Utilities.PERSISTENT, Context.MODE_PRIVATE);
        section = preferences.getStringSet(Utilities.SECTIONS, new HashSet<>(Arrays.asList(Utilities.SECTIONS_DEFAULT)));
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
