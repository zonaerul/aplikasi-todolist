package id.application.todolist.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import id.application.todolist.R;
import id.application.todolist.adapter.adapterdone;
import id.application.todolist.adapter.adaptertodolist;
import id.application.todolist.data.AlertCustom;
import id.application.todolist.data.Data;

public class FragmentTask extends Fragment {
    private ArrayList<Data> array;
    private ArrayList<Data> arraydone;
    private adapterdone adpter_done;
    private adaptertodolist adapter;
    private FloatingActionButton floatingaction_btn;
    private AlertCustom alert;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.task_fragment, container, false);
        array = new ArrayList<>();
        arraydone = new ArrayList<>();
        floatingaction_btn = view.findViewById(R.id.floatingaction_btn);
        alert = new AlertCustom(getContext());

        floatingaction_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alert.setiIcon(R.drawable.baseline_check_circle_outline_24);
                alert.formInput(new AlertCustom.CallBackForm() {
                    @Override
                    public void onSave() {
                        alert.success();
                    }

                    @Override
                    public void onCancel() {

                    }
                });
            }
        });
        adpter_done = new adapterdone(getContext(), arraydone, adapter);
        adapter = new adaptertodolist(getContext(), array, adpter_done);

        // Set up the adapters
        setupTodolist(view);
        setupDoneList(view);

        return view;
    }

    private void setupTodolist(View view) {
        ImageView imageView = view.findViewById(R.id.image_unknown);

        // Sample data for the to-do list
        array.add(new Data("1", "Rapat 1", R.drawable.baseline_outlined_flag_24, "26/10/2024"));
        array.add(new Data("2", "Rapat 2", R.drawable.baseline_outlined_flag_24, "28/10/2024"));
        array.add(new Data("3", "Rapat 3", R.drawable.baseline_outlined_flag_24, "28/10/2024"));
        array.add(new Data("4", "Rapat 4", R.drawable.baseline_outlined_flag_24, "28/10/2024"));
        array.add(new Data("5", "Rapat 5", R.drawable.baseline_outlined_flag_24, "28/10/2024"));

        // Initialize adapter for to-do list
        RecyclerView recyclerView = view.findViewById(R.id.recycler_todolist);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void setupDoneList(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_complite);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adpter_done);
    }
}
