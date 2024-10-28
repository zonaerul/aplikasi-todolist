package id.application.todolist.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import id.application.todolist.R;
import id.application.todolist.data.Data;

public class adapterdone extends RecyclerView.Adapter<adapterdone.ViewHolder> {
    private final Context context;
    private final ArrayList<Data> arrayList; // List of done tasks
    private final adaptertodolist adapter; // Reference to todo adapter

    public adapterdone(Context context, ArrayList<Data> arrayList, adaptertodolist adapter) {
        this.context = context;
        this.arrayList = arrayList;
        this.adapter = adapter;
    }

    public void addItem(Data data) {
        arrayList.add(data); // Add item to done list
        notifyItemInserted(arrayList.size() - 1); // Refresh RecyclerView for added item
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.todolist, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Data data = arrayList.get(position);

        // Set item details
        if (!TextUtils.isEmpty(data.getTitle())) {
            holder.name.setText(data.getTitle());
            holder.date.setText(data.getDate());
            holder.flag.setImageResource(R.drawable.baseline_outlined_flag_24);
        }

        holder.checkbox.setOnCheckedChangeListener(null); // Clear previous listener
        holder.checkbox.setChecked(true); // Mark as done
        holder.ll.setBackgroundColor(context.getColor(R.color.grey));
        holder.name.setPaintFlags(holder.name.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        holder.date.setPaintFlags(holder.date.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        // Set checkbox listener to handle uncheck action
        holder.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (!isChecked) {
                    // Remove item from done list
                    Data removedData = arrayList.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, arrayList.size());

                    // Add item back to todo list and notify adapter
                    if (adapter != null) {
                        adapter.addItem(data);
                    } else {
                        Log.e("AdapterDone", "adapterTodolist is null");
                    }

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, date;
        CheckBox checkbox;
        ImageView flag;
        LinearLayout ll;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ll = itemView.findViewById(R.id.ll);
            name = itemView.findViewById(R.id.name_task);
            date = itemView.findViewById(R.id.date_task);
            checkbox = itemView.findViewById(R.id.checkbox);
            flag = itemView.findViewById(R.id.image_flag);
        }
    }
}
