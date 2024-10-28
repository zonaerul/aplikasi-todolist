package id.application.todolist.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.text.TextUtils;
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

public class adaptertodolist extends RecyclerView.Adapter<adaptertodolist.ViewHolder> {
    private final Context context;
    private final ArrayList<Data> arrayList;
    private final adapterdone adapterDone;

    public adaptertodolist(Context context, ArrayList<Data> arrayList, adapterdone adapterDone) {
        this.context = context;
        this.arrayList = arrayList;
        this.adapterDone = adapterDone;
    }

    public void addItem(Data data) {
        arrayList.add(data);
        notifyItemInserted(arrayList.size() - 1);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.todolist, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        Data data = arrayList.get(position);

        if (!TextUtils.isEmpty(data.getTitle())) {
            holder.name.setText(data.getTitle());
            holder.date.setText(data.getDate());
            holder.flag.setImageResource(R.drawable.baseline_outlined_flag_24);
        } else {
            holder.flag.setVisibility(View.VISIBLE);
        }

        // Clear previous listener to avoid issues
        holder.checkbox.setOnCheckedChangeListener(null);
        holder.checkbox.setChecked(false); // Ensure checkbox is reset

        // Set checkbox listener
        holder.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    // Update UI for completed task
                    holder.ll.setBackgroundColor(context.getColor(R.color.grey));
                    holder.name.setPaintFlags(holder.name.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    holder.date.setPaintFlags(holder.date.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

                    // Remove item from current list and add to done list
                    Data removedData = arrayList.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, arrayList.size());

                    adapterDone.addItem(removedData); // Add item to done adapter and update done adapter
                } else {
                    // Reset UI if unchecked
                    holder.ll.setBackgroundColor(context.getColor(R.color.white));
                    holder.name.setPaintFlags(holder.name.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
                    holder.date.setPaintFlags(holder.date.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
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
