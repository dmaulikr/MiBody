package com.mibody.app.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.innodroid.expandablerecycler.ExpandableRecyclerAdapter;
import com.mibody.app.R;
import com.mibody.app.app.ExerciseItem;
import com.mibody.app.app.ExercisesGroup;
import com.mibody.app.app.WorkoutItem;
import com.mibody.app.helper.ExercisesItemAdapter;
import com.mibody.app.helper.ItemClickListener;
import com.mibody.app.helper.RecyclerViewAdapter;
import com.mibody.app.helper.WorkoutExItemAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NakeebMac on 10/1/16.
 */

public class Exercises extends Fragment {

    ExercisesItemAdapter ExpAdapter;
    ArrayList<ExercisesGroup> ExpListItems;
    ExpandableListView ExpandList;
    RecyclerView exercisesRV;

    ArrayList<ExerciseItem> arrayList;

    String exercises_names[] = { "Pushup", "Pullup", "Abs", "Climbers",
            "Squats", "Pike", "Leg Raises", "Lunges", "Plank", "Exercise J", "Exercise K" };

    String country_names[] = { "Description A", "Description B", "Description C", "Description D",
            "Description E", "Description F", "Description G", "Description H", "Description I",
            "Description J", "Description K", "Japan", "Costa Rica", "Uruguay",
            "Italy", "England", "France", "Switzerland", "Ecuador",
            "Honduras", "Agrentina", "Nigeria", "Bosnia and Herzegovina",
            "Iran", "Germany", "United States", "Portugal", "Ghana",
            "Belgium", "Algeria", "Russia", "Korea Republic" };

    int Images[] = { R.drawable.ex1, R.drawable.ex2,
            R.drawable.ex3, R.drawable.ex4, R.drawable.ex5,
            R.drawable.ex6, R.drawable.ex7, R.drawable.ex8,
            R.drawable.ex9, R.drawable.ex10, R.drawable.ex11 };

    public Exercises() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setArrayList(ArrayList<ExerciseItem> exerciseItemArrayList){
        arrayList = exerciseItemArrayList;
        Log.d("arraySent", String.valueOf(arrayList.size()));
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        exercisesRV = (RecyclerView) getView().findViewById(R.id.expandableListView);
        exercisesRV.setHasFixedSize(true);
        exercisesRV.setLayoutManager(new GridLayoutManager(this.getActivity(), 3, LinearLayoutManager.VERTICAL, false));

        exercisesRV.setItemAnimator(new DefaultItemAnimator());


        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this.getActivity(), arrayList);
        exercisesRV.setAdapter(adapter);

        adapter.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                LayoutInflater inflater = getActivity().getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.exercise_details, null);
                builder.setView(dialogView);

                TextView exerciseName;
                TextView exerciseDescription;
                ImageView exerciseImg;



                exerciseName = (TextView) dialogView.findViewById(R.id.exName);
                exerciseImg = (ImageView) dialogView.findViewById(R.id.exImg);
                exerciseDescription = (TextView) dialogView.findViewById(R.id.exDescription);

                exerciseName.setText(arrayList.get(position).getName());
                exerciseImg.setImageResource(arrayList.get(position).getImage());
                exerciseDescription.setText(arrayList.get(position).getDescription());


                AlertDialog alertDialog =  builder.create();
                alertDialog.show();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.exercises_fragment, container, false);

    }

}