package ch.philopateer.mibody.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.PagerSnapHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import ch.philopateer.mibody.R;
import ch.philopateer.mibody.app.AppConfig;
import ch.philopateer.mibody.app.AppController;
import ch.philopateer.mibody.object.ExerciseItem;
import ch.philopateer.mibody.helper.SQLiteHandler;
import ch.philopateer.mibody.helper.ViewPagerAdapter;
import me.relex.circleindicator.CircleIndicator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mamdouhelnakeeb on 12/2/16.
 */

public class ExercisesFragment extends Fragment {

    private static final String TAG = ExercisesFragment.class.getSimpleName();
    private ProgressDialog pDialog;
    SQLiteHandler sqLiteHandler;

    ArrayList<ExerciseItem> exerciseItemArrayList;
    String exercisesCategory = "";

    ViewPager exerciseViewPager;
    CircleIndicator circleIndicator;
    PagerSnapHelper pagerSnapHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.exercises_fragment, container, false);


        circleIndicator = (CircleIndicator) view.findViewById(R.id.page_indicator);

        // Progress dialog
        pDialog = new ProgressDialog(getActivity());
        pDialog.setCancelable(false);

        sqLiteHandler = new SQLiteHandler(getActivity());

        try {
            sqLiteHandler.open();

        } catch (Exception e) {
            Log.i("SQLexception", "can't open SQL");
        }

        exerciseViewPager = (ViewPager) view.findViewById(R.id.exercisesViewpager);

        exerciseItemArrayList = new ArrayList<ExerciseItem>();

        loadExercises();

        return view;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());

        ArrayList<ExerciseItem> tempExerciseItems = new ArrayList<ExerciseItem>();

        for (int i = 0; i<= exerciseItemArrayList.size(); i++){
            if((i % 9 == 0 || i == exerciseItemArrayList.size()) && i != 0){
                Exercises exercises = new Exercises();
                exercises.setArrayList(tempExerciseItems, viewPager.getHeight());
                adapter.addFragment(exercises, "ExPage" + String.valueOf(i));
                tempExerciseItems = new ArrayList<ExerciseItem>();
                Log.d("ExPage Added", String.valueOf(i));
            }
            if(i != exerciseItemArrayList.size()) {
                tempExerciseItems.add(new ExerciseItem(exerciseItemArrayList.get(i).getId(), exerciseItemArrayList.get(i).getName(), exerciseItemArrayList.get(i).getIcon(),
                        exerciseItemArrayList.get(i).getImage(), exerciseItemArrayList.get(i).getGIF(), exerciseItemArrayList.get(i).getDescription(), exerciseItemArrayList.get(i).getCategory()));
                Log.d("Ex Added", String.valueOf(i));
            }
        }

        viewPager.setAdapter(adapter);

        circleIndicator.setViewPager(viewPager);

    }

    private void loadExercises(){
/*
        for (int i=0; i<AppConfig.exercises_names.length; i++){
            exerciseItemArrayList.add(i, new ExerciseItem(String.valueOf(i),
                    AppConfig.exercises_names[i],
                    AppConfig.exercises_names[i] + ".png",
                    AppConfig.exercises_names[i]+ ".png",
                    "",
                    AppConfig.exercises_discreptions[i], ""));
        }
        setupViewPager(exerciseViewPager);
*/

        // Tag used to cancel the request
        String tag_string_req = "req_register";

        pDialog.setMessage("Loading Exercises ...");
        showDialog();


        StringRequest strReq = new StringRequest(Request.Method.GET, AppConfig.URL_SERVER + "exercises2.php",
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "MiBody Workouts Response: " + response);
                        hideDialog();

                        exerciseItemArrayList = new ArrayList<ExerciseItem>();

                        try {
                            // Extract JSON array from the response
                            JSONArray resultsArr = new JSONArray(response);

                            System.out.println(resultsArr.length());
                            // If no of array elements is not zero
                            if(resultsArr.length() != 0){

                                Log.d("resultsArray", resultsArr.toString());
                                // Loop through each array element, get JSON object
                                for (int i = 0; i < resultsArr.length(); i++) {
                                    // Get JSON object
                                    JSONObject obj = (JSONObject) resultsArr.get(i);

                                    // DB QueryValues Object to insert into Movies ArrayList
                                    String id = obj.get("id").toString();
                                    String ExerciseName = obj.get("name").toString();
                                    String ExerciseIcon = obj.get("icon").toString();
                                    String ExerciseImage = obj.get("image").toString();
                                    String ExerciseGIF = obj.get("GIF").toString();
                                    String ExerciseDescription = obj.get("description").toString();
                                    String ExerciseCategory = obj.get("category").toString();

                                    ExerciseItem exerciseItem = new ExerciseItem(id , ExerciseName, ExerciseIcon, ExerciseImage, ExerciseGIF, ExerciseDescription, ExerciseCategory);

                                    exerciseItemArrayList.add(exerciseItem);
                                    sqLiteHandler.addExercise(exerciseItem);

                                }
                                setupViewPager(exerciseViewPager);
                            }
                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: " + error.getMessage());

                exerciseItemArrayList = new ArrayList<ExerciseItem>();
                exerciseItemArrayList = sqLiteHandler.getExercises(null);

                if (exerciseItemArrayList.size() == 0) {
                    for (int i=0; i<AppConfig.exercises_names.length; i++){
                        exerciseItemArrayList.add(i, new ExerciseItem(String.valueOf(i),
                                AppConfig.exercises_names[i],
                                AppConfig.exercises_names[i] + ".png",
                                AppConfig.exercises_names[i]+ ".png",
                                "",
                                AppConfig.exercises_discreptions[i], ""));
                    }
                }
                setupViewPager(exerciseViewPager);

                //Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting params to register url
                Map<String, String> params = new HashMap<String, String>();
                if (!exercisesCategory.isEmpty()) {
                    params.put("category", exercisesCategory);
                }
                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);

    }


    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
/*
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("exArrayList", exerciseItemArrayList);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            exerciseItemArrayList = savedInstanceState.getParcelableArrayList("exArrayList");
            setupViewPager(exerciseViewPager);
        }
    }
*/
    @Override
    public void onResume() {
        super.onResume();
        loadExercises();
        Log.d("resuemd", "yep");
    }
}
