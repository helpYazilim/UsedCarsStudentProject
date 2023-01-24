package com.example.usedcarsstudentproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

public class AlertDialogClass {

    public void ilanlarimAlertDialog(Activity activity,String ilan_id){

        LayoutInflater ınflater = activity.getLayoutInflater();
        View view = ınflater.inflate(R.layout.alertlayout, null);

        Button button = (Button)view.findViewById(R.id.buton);
        Button buttonCik = (Button)view.findViewById(R.id.buton2);

        AlertDialog.Builder alert = new AlertDialog.Builder(activity);
        alert.setView(view);
        alert.setCancelable(false);
        final AlertDialog dialog = alert.create();

        buttonCik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        dialog.show();
    }

}
