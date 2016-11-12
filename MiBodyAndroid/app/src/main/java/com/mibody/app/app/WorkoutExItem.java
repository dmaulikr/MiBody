package com.mibody.app.app;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.mibody.app.helper.SQLiteHandler;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by NakeebMac on 10/5/16.
 */

public class WorkoutExItem implements Parcelable{

    private ImageView ExerciseImg;
    private Button RGB;
    private Button RestPlus;
    private Button RestMinus;
    private Button AddEx;
    private EditText RestTime;
    private EditText Reps;
    private Button setRepsBtn;
    public int RestT;
    public String name;
    public int RepsT;
    public String exercise;
    private String rgb;
    public int setReps;

    public WorkoutExItem(String name, int ResT, int RepsT, String rgb, int setReps) {
        this.name = name;
        this.RestT = ResT;
        this.RepsT = RepsT;
        this.rgb = rgb;
        this.setReps = setReps;
    }

    public WorkoutExItem(){

    }

    protected WorkoutExItem(Parcel in) {
        RestT = in.readInt();
        name = in.readString();
        RepsT = in.readInt();
        exercise = in.readString();
        rgb = in.readString();
        setReps = in.readInt();
    }

    public static final Creator<WorkoutExItem> CREATOR = new Creator<WorkoutExItem>() {
        @Override
        public WorkoutExItem createFromParcel(Parcel in) {
            return new WorkoutExItem(in);
        }

        @Override
        public WorkoutExItem[] newArray(int size) {
            return new WorkoutExItem[size];
        }
    };

    public JSONObject getJSONObject() {
        JSONObject obj = new JSONObject();
        try {
            obj.put("name", name);
            obj.put("RestT", RestT);
            obj.put("RepsT", RepsT);
            obj.put("exercise", exercise);
            obj.put("rgb", rgb);
            obj.put("setReps", setReps);
        } catch (JSONException e) {
            Log.d( "JSONObject", "DefaultListItem.toString JSONException: "+e.getMessage());
        }
        return obj;
    }


    public String getRgb (){
        return rgb;
    }

    public void setRgb (String rgb){
        this.rgb = rgb;
    }
    public int getRestT(){
        return RestT;
    }

    public void setRestT(int RestT){
        this.RestT = RestT;
    }

    public ImageView getExerciseImg(){
        return ExerciseImg;
    }

    public void setExerciseImg (ImageView ExerciseImg){
        this.ExerciseImg = ExerciseImg;
    }

    public Button getRGB(){
        return RGB;
    }

    public void setRGB (Button RGB){
        this.RGB = RGB;
    }

    public Button getRestPlus(){
        return RestPlus;
    }

    public void setRestPlus (Button RestPlus){
        this.RestPlus = RestPlus;
    }

    public Button getRestMinus(){
        return RestMinus;
    }

    public void setRestMinus (Button RestMinus){
        this.RestMinus = RestMinus;
    }

    public Button getAddEx(){
        return AddEx;
    }

    public void setAddEx (Button AddEx){
        this.AddEx = AddEx;
    }

    public EditText getRestTime(){
        return RestTime;
    }

    public void setRestTime (EditText RestTime){
        this.RestTime = RestTime;
    }

    public EditText getReps(){
        return Reps;
    }

    public void setReps (EditText Reps){
        this.Reps = Reps;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(RestT);
        dest.writeString(name);
        dest.writeInt(RepsT);
        dest.writeString(exercise);
        dest.writeString(rgb);
        dest.writeInt(setReps);
    }
}