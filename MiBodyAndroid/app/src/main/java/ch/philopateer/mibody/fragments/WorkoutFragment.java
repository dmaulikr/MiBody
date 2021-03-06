package ch.philopateer.mibody.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ch.philopateer.mibody.object.WorkoutExItem;
import ch.philopateer.mibody.object.WorkoutItem;
import ch.philopateer.mibody.helper.SQLiteHandler;
import ch.philopateer.mibody.helper.WorkoutSQLAdapter;
import ch.philopateer.mibody.helper.WorkoutsAdapter;

import java.util.ArrayList;

public class WorkoutFragment extends Fragment{

    RecyclerView workoutsRV;
    ArrayList<WorkoutItem> workoutItemArrayList;
    SQLiteHandler sqLiteHandler;

    public WorkoutFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sqLiteHandler = new SQLiteHandler(this.getActivity());
        try {
            sqLiteHandler.open();

        } catch (Exception e) {
            Log.i("hello", "hello");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view1 = inflater.inflate(ch.philopateer.mibody.R.layout.activity_workouts, container, false);

/*
        FloatingActionButton fab = (FloatingActionButton) view1.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                Intent i = new Intent(getActivity(), AddWorkout.class);
                startActivity(i);
            }
        });



            adapter.setClickListener(new ItemClickListener() {
                @Override
                public void onClick(View view, final int position) {

                    final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                    LayoutInflater inflater = getActivity().getLayoutInflater();
                    View dialogView = inflater.inflate(R.layout.custom_workout, null);
                    builder.setView(dialogView);

                    EditText WorkoutName;
                    TextView exercisesSetText;
                    EditText workoutRepeat;
                    int workoutReps = 0;
                    Button AddExercise;
                    ImageButton workoutRepeatBtn;
                    Button SaveWorkout;
                    GridView ExercisesGrid;
                    RecyclerView ExercisesSetGrid = (RecyclerView) dialogView.findViewById(R.id.exercises_set_grid);
                    RecyclerView ExercisesRV;
                    WorkoutExItemAdapter WAdapter;

                    exercisesSetText = (TextView) dialogView.findViewById(R.id.exercises_set_txt);
                    WorkoutName = (EditText) dialogView.findViewById(R.id.workout_name);
                    workoutRepeat = (EditText) dialogView.findViewById(R.id.workoutRepeat);
                    workoutRepeatBtn = (ImageButton) dialogView.findViewById(R.id.workoutRepeatBtn);
                    AddExercise = (Button) dialogView.findViewById(R.id.add_exercise);
                    SaveWorkout = (Button) dialogView.findViewById(R.id.save_btn);

//                    initWorkoutViews(ExercisesSetGrid, workoutItemArrayList.get(position).exercisesList);

                    view.setEnabled(false);
                    WorkoutName.setText(workoutItemArrayList.get(position).workoutName);


                    AddExercise.setVisibility(View.GONE);
                    exercisesSetText.setText("Exercises");

                    SaveWorkout.setVisibility(View.GONE);

                    builder.setNeutralButton("Play WorkoutFragment", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(getActivity(), WorkoutPlayActivity.class);
                            intent.putExtra("WorkoutItem", workoutItemArrayList.get(position));
                            startActivity(intent);
                        }
                    });

                    AlertDialog alertDialog =  builder.create();
                    alertDialog.show();

                }
            });
            */


        // Inflate the layout for this fragment
        return view1;
    }

    private void initWorkoutViews(RecyclerView ExercisesSetGrid, ArrayList<WorkoutExItem> workoutExItemArrayList){

        // ExercisesSetGrid.setHasFixedSize(true);
        ExercisesSetGrid.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
/*
        workoutItemArrayList = new ArrayList<>();
        workoutItemArrayList.add(new WorkoutItem());
*/

        workoutExItemArrayList.add(new WorkoutExItem());

        WorkoutSQLAdapter WAdapter = new WorkoutSQLAdapter(getActivity(), workoutExItemArrayList);
        ExercisesSetGrid.setAdapter(WAdapter);// set adapter on recyclerview
        WAdapter.notifyDataSetChanged();// Notify the adapter

    }

    @Override
    public void onResume() {
        super.onResume();

        workoutItemArrayList = sqLiteHandler.getWorkouts(null);


        if (workoutItemArrayList.size() != 0) {
            Log.d("sizen", String.valueOf(workoutItemArrayList.size()) + String.valueOf(workoutItemArrayList.get(0).exercisesList.size()));
            workoutsRV = (RecyclerView) getView().findViewById(ch.philopateer.mibody.R.id.expandableListView);
            workoutsRV.setHasFixedSize(true);
            workoutsRV.setLayoutManager(new GridLayoutManager(this.getActivity(), 3, LinearLayoutManager.VERTICAL, false));

            workoutsRV.setItemAnimator(new DefaultItemAnimator());

            WorkoutsAdapter adapter = new WorkoutsAdapter(this.getActivity(), workoutItemArrayList, new WorkoutsAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(WorkoutItem workoutItem) {

                }
            });
            workoutsRV.setAdapter(adapter);
        }
    }


    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workouts);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent i = new Intent(getApplicationContext(), AddWorkout.class);
                startActivity(i);
                finish();
            }

        });
    }
*/
}
