package com.singh.harsukh.redder.fragment;


import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.singh.harsukh.redder.R;
import com.singh.harsukh.redder.SpaceItemDecoration;
import com.singh.harsukh.redder.adapter.SectionAdapter;

import java.util.ArrayList;



public class SectionsFragment extends android.app.Fragment implements SectionAdapter.ClickListener {

    public RecyclerView recyclerView;
    public ArrayList<String> section;
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
        section = new ArrayList<>();
        section.add(getString(R.string.title_section1));
        section.add(getString(R.string.title_section2));
        section.add(getString(R.string.title_section3));
        section.add("etc");
        section.add("more");
        section.add("It Works");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_sections, container, false);

        sectionAdapter = new SectionAdapter(getActivity(),section);
        sectionAdapter.setClickListener(this);
        recyclerView = (RecyclerView) v.findViewById(R.id.section_RecyclerView);
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.spacing);
        recyclerView.addItemDecoration(new SpaceItemDecoration(spacingInPixels));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(sectionAdapter);
        return v;
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

    @Override
    public void itemClicked(View view, int position) {
        openFragment(section.get(position));
        Toast.makeText(getActivity(),section.get(position),Toast.LENGTH_SHORT).show();
    }

    public void openFragment(String title){
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        FragmentTransaction t = getFragmentManager()
                .beginTransaction();
        MainFragment mFrag = new MainFragment();
        mFrag.setArguments(bundle);
        t.replace(R.id.main_container, mFrag, "section")
                .commit();
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
