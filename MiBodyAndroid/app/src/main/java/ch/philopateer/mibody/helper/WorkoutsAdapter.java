package ch.philopateer.mibody.helper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ch.philopateer.mibody.R;
import ch.philopateer.mibody.object.WorkoutItem;

import java.util.ArrayList;

/**
 * Created by NakeebMac on 10/6/16.
 */

public class WorkoutsAdapter extends RecyclerView.Adapter<WorkoutsAdapter.ViewHolder> {


    private static final int VIEW_TYPE_PADDING = 1;
    private static final int VIEW_TYPE_ITEM = 2;
    private ArrayList<WorkoutItem> arrayList;
    private Context context;


    public interface OnItemClickListener {
        void onItemClick(WorkoutItem workoutItem);
    }


    private final OnItemClickListener onItemClickListener;


    public WorkoutsAdapter(Context context, ArrayList<WorkoutItem> arrayList, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.arrayList = arrayList;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(context).inflate(R.layout.workout_rv_item, parent, false);
            view.setVisibility(View.VISIBLE);
            return new ViewHolder(view);
        }
        else {
            View view = LayoutInflater.from(context).inflate(R.layout.workout_rv_item, parent, false);
            view.setVisibility(View.INVISIBLE);
            return new ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {


        if (getItemViewType(position) == VIEW_TYPE_ITEM) {
            final WorkoutItem workoutItem = arrayList.get(position);
            holder.workoutName.setText(workoutItem.workoutName);
            String workoutExercises = "";
            for (int i = 0; i < workoutItem.exercisesList.size(); i++) {
                if (i == workoutItem.exercisesList.size() - 1) {
                    workoutExercises += workoutItem.exercisesList.get(i).name;
                } else {
                    workoutExercises += workoutItem.exercisesList.get(i).name + ", ";
                }
            }
            holder.workoutExercises.setText(workoutExercises);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(workoutItem);
                }
            });
        }
        else {
            holder.itemView.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position >= getItemCount()-3) {
            return VIEW_TYPE_PADDING;
        }
        else {
            return VIEW_TYPE_ITEM;
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size() + 3;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView workoutName;
        TextView workoutExercises;

        public ViewHolder(View view) {
            super(view);
            workoutName = (TextView) view.findViewById(R.id.workoutsItemName);
            workoutExercises = (TextView) view.findViewById(R.id.workoutsItemExercises);
        }
    }
}
