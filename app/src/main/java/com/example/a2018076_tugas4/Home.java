package com.example.a2018076_tugas4;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Home extends Fragment {

    RecyclerView recylerView;
    String s1[], s2[], s3[];
    int images[] =
            {R.drawable.kebab, R.drawable.spagettii, R.drawable.es_coklat};


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recylerView = getActivity().findViewById(R.id.recylerViewMenu);
        s1 = getResources().getStringArray(R.array.menus);
        s2 = getResources().getStringArray(R.array.harga);
        s3 = getResources().getStringArray(R.array.star);
        MenuAdapter appAdapter = new MenuAdapter(getActivity(), s1, s2, s3, images);
        recylerView.setAdapter(appAdapter);
        recylerView.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false));

    }
}