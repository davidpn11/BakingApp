package com.android.pena.david.bakingapp.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.pena.david.bakingapp.R;

/**
 * Created by david on 01/06/17.
 */

public class RecipeFragment extends Fragment {


    public RecipeFragment(){
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recipe_fragment,container,false);


        return view;
    }
}
