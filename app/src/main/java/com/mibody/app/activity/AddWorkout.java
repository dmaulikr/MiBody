package com.mibody.app.activity;

import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.RelativeLayout;

import com.mibody.app.R;
import com.mibody.app.app.ExerciseItem;
import com.mibody.app.app.ExercisesGroup;
import com.mibody.app.app.WorkoutExItem;
import com.mibody.app.helper.AddWorkoutAdapter;
import com.mibody.app.helper.OnStartDragListener;
import com.mibody.app.helper.RecyclerViewAdapter;
import com.mibody.app.helper.SimpleItemTouchHelperCallback;
import com.mibody.app.helper.WorkoutExItemAdapter;
import com.mibody.app.helper.WorkoutExsAdapter;

import android.app.Activity;
import android.content.ClipData;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import android.content.SharedPreferences.Editor;


/**
 * Created by NakeebMac on 10/4/16.
 */

public class AddWorkout extends AppCompatActivity {

    EditText WorkoutName;
    Button AddExercise;
    Button RemoveExercise;
    Button SaveWorkout;
    GridView ExercisesGrid;
    RecyclerView ExercisesSetGrid;
    RecyclerView ExercisesRV;
    WorkoutExItemAdapter WAdapter;
    String exercises_names[] = { "Exercise A", "Exercise B", "Exercise C", "Exercise D", "Exercise E",
            "Exercise F", "Exercise G", "Exercise H", "Exercise I", "Exercise J", "Exercise K" };


    int Images[] = { R.drawable.ex1, R.drawable.ex2,
            R.drawable.ex3, R.drawable.ex4, R.drawable.ex5,
            R.drawable.ex6, R.drawable.ex7, R.drawable.ex8,
            R.drawable.ex9, R.drawable.ex10, R.drawable.ex11 };

    int workoutExNo = 1;
     ArrayList<WorkoutExItem> workoutExItemArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_workout);

        WorkoutName = (EditText) findViewById(R.id.workout_name);
        AddExercise = (Button) findViewById(R.id.add_exercise);
        RemoveExercise = (Button) findViewById(R.id.remove_exercise);
        SaveWorkout = (Button) findViewById(R.id.save_btn);
      //  ExercisesGrid = (GridView) findViewById(R.id.exercises_grid);
      //  ExercisesRV = (RecyclerView) findViewById(R.id.exercises_grid);
      //  ExercisesSetGrid = (GridView) findViewById(R.id.exercises_set_grid);

/*
        SharedPreferences sharedPreferences = getSharedPreferences("Workout", MODE_PRIVATE);
        final Editor editor = sharedPreferences.edit();
        editor.putString("WorkoutExNo", String.valueOf(workoutExNo));
        editor.apply();
        */

 //       initViews();
        initWorkoutViews();


/*
        ExercisesRV.setLayoutManager(new GridLayoutManager(this, 2, GridLayoutManager.HORIZONTAL, false));
        ExercisesSetGrid.setAdapter(new WorkoutExItemAdapter(this));
        ExercisesRV.setAdapter(new WorkoutExsAdapter(this, exercises_names));
        ExercisesRV.setAdapter(new WorkoutExsAdapter(this, exercises_names));
*/
        SaveWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
/*
    private void initViews2(){
        ExercisesRV = (RecyclerView) findViewById(R.id.exercises_grid);
        ExercisesRV.setHasFixedSize(true);
        ExercisesRV.setLayoutManager(new LinearLayoutManager(AddWorkout.this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<ExerciseItem> exerciseItemArrayList = new ArrayList<>();
        for (int i = 0; i < exercises_names.length; i++) {
            exerciseItemArrayList.add(new ExerciseItem(Images[i],exercises_names[i]));
        }

        ExercisesSetGrid = (RecyclerView) findViewById(R.id.exercises_set_grid);
        ExercisesSetGrid.setHasFixedSize(true);
        ExercisesSetGrid.setLayoutManager(new LinearLayoutManager(AddWorkout.this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<WorkoutExItem> workoutExItemArrayList = new ArrayList<>();
        workoutExItemArrayList.add(new WorkoutExItem());

        AddWorkoutAdapter adapter = new AddWorkoutAdapter(this, exerciseItemArrayList, workoutExItemArrayList, this);
        ExercisesRV.setAdapter(adapter);// set adapter on recyclerview
        ExercisesSetGrid.setAdapter(adapter);// set adapter on recyclerview
        adapter.notifyDataSetChanged();// Notify the adapter

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(ExercisesRV);

    }
    */

    private void initViews(){
        ExercisesRV = (RecyclerView) findViewById(R.id.exercises_grid);
        ExercisesRV.setHasFixedSize(true);
        ExercisesRV.setLayoutManager(new LinearLayoutManager(AddWorkout.this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<ExerciseItem> arrayList = new ArrayList<>();
        for (int i = 0; i < exercises_names.length; i++) {
            arrayList.add(new ExerciseItem(Images[i],exercises_names[i]));
        }
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, arrayList);
        ExercisesRV.setAdapter(adapter);// set adapter on recyclerview
        adapter.notifyDataSetChanged();// Notify the adapter

    }

    private void initWorkoutViews(){
        ExercisesSetGrid = (RecyclerView) findViewById(R.id.exercises_set_grid);
        ExercisesSetGrid.setHasFixedSize(true);
        ExercisesSetGrid.setLayoutManager(new LinearLayoutManager(AddWorkout.this, LinearLayoutManager.HORIZONTAL, false));

        workoutExItemArrayList = new ArrayList<>();
        workoutExItemArrayList.add(new WorkoutExItem());

        WAdapter = new WorkoutExItemAdapter(this, workoutExItemArrayList);
        ExercisesSetGrid.setAdapter(WAdapter);// set adapter on recyclerview
        WAdapter.notifyDataSetChanged();// Notify the adapter

        AddExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workoutExItemArrayList.add(new WorkoutExItem());
                WAdapter.notifyItemInserted(workoutExItemArrayList.size() - 1);
            }
        });

    }


    private final class MyTouchListener implements OnTouchListener {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                ClipData data = ClipData.newPlainText("", "");
                DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(
                        view);
                view.startDrag(data, shadowBuilder, view, 0);
                view.setVisibility(View.INVISIBLE);
                return true;
            } else {
                return false;
            }
        }
    }

    class MyDragListener implements OnDragListener {
        Drawable enterShape = getResources().getDrawable(
                R.drawable.gym1);
        Drawable normalShape = getResources().getDrawable(R.drawable.gym1);

        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    // do nothing
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    v.setBackgroundDrawable(enterShape);
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    v.setBackgroundDrawable(normalShape);
                    break;
                case DragEvent.ACTION_DROP:
                    // Dropped, reassign View to ViewGroup
                    View view = (View) event.getLocalState();
                    ViewGroup owner = (ViewGroup) view.getParent();
                    owner.removeView(view);
                    LinearLayout container = (LinearLayout) v;
                    container.addView(view);
                    view.setVisibility(View.VISIBLE);
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    v.setBackgroundDrawable(normalShape);
                default:
                    break;
            }
            return true;
        }
    }
}
