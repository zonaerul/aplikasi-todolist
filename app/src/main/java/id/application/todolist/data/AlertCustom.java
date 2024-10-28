package id.application.todolist.data;

import android.app.AlertDialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;

import id.application.todolist.R;

public class AlertCustom {
    Context context;
    AlertDialog.Builder alertDialog;
    String title, message = "";
    int icon;
    public AlertCustom(Context context){
        this.context = context;
        alertDialog = new AlertDialog.Builder(context);
    }

    public void setiIcon(int icon){
        this.icon = icon;
    }

    public void setTitle(String title){
        this.title = title;
    }
    public void setMessage(String message){
        this.message = message;
    }

    public void success(){
        View view = LayoutInflater.from(context).inflate(R.layout.alertcustom_1, null);
        ImageView image = view.findViewById(R.id.icon_alert);
        image.setImageResource(icon);
        TextView text = view.findViewById(R.id.text_alert);
        if(TextUtils.isEmpty(message)){
            message = "Berhasil";
        }
        text.setText(message);
        Button open = view.findViewById(R.id.open_btn);
        Button close = view.findViewById(R.id.close_btn);

        alertDialog.setView(view);
        AlertDialog alert = alertDialog.create();
        alert.show();
        close.setOnClickListener(v->{
            alert.dismiss();
        });
    }

    // Define interface for callback
    public interface CallBackForm {
        void onSave();
        void onCancel();
    }

    // Method to show form with callbacks for Save and Cancel
    public void formInput(CallBackForm callBackForm) {
        View view = LayoutInflater.from(context).inflate(R.layout.alertcustom_2, null);
        alertDialog.setView(view);
        AlertDialog alert = alertDialog.create();
        alert.show();

        AppCompatButton save = view.findViewById(R.id.save_alert);
        save.setOnClickListener(v -> {
            alert.dismiss();
            if (callBackForm != null) {
                callBackForm.onSave(); // Trigger onSave callback
            }
        });

        AppCompatButton cancel = view.findViewById(R.id.cancel_alert);
        cancel.setOnClickListener(v -> {
            alert.dismiss();
            if (callBackForm != null) {
                callBackForm.onCancel(); // Trigger onCancel callback
            }
        });
    }
}
