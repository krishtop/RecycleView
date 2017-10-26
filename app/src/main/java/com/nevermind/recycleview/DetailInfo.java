package com.nevermind.recycleview;

import com.nevermind.recycleview.MainActivity;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Dmitry on 24.10.2017.
 */

public class DetailInfo extends AppCompatActivity {
    TextView telephoneView;
    TextView addressView;
    TextView infoView;
    TextView distanceView;
    TextView rateView;

    public final static String THIEF = "com.nevermind.recycleview.THIEF";
    Place place;
    int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        place = (Place) getIntent().getSerializableExtra("data");
        position = getIntent().getIntExtra("position",-1);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        telephoneView = (TextView) findViewById(R.id.telephoneView);
        addressView = (TextView) findViewById(R.id.headAddress);
        infoView = (TextView) findViewById(R.id.headInfo);
        distanceView = (TextView) findViewById(R.id.headDistance);
        rateView = (TextView) findViewById(R.id.headRate);


        addressView.setText(place.getAddress());
        infoView.setText(place.getInfo());
        telephoneView.setText(place.getTelephone());
        distanceView.setText(Long.toString(place.getDistance())+" км");
        rateView.setText("Оценка отделения: "+Integer.toString(place.getRate()));
    }

public void showRateDialog(View v){
    showRating();
}

    public void showRating() {

        final AlertDialog.Builder ratingdialog = new AlertDialog.Builder(this);
        final Intent intent = new Intent();

        ratingdialog.setIcon(android.R.drawable.btn_star_big_on);
        ratingdialog.setTitle("Оцените отделение");

        View linearlayout = getLayoutInflater().inflate(R.layout.rate_dialog, null);
        ratingdialog.setView(linearlayout);

        final RatingBar rating = (RatingBar)linearlayout.findViewById(R.id.ratingbar);

        ratingdialog.setPositiveButton("Готово",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        rateView.setText("Оценка отделения: "+String.valueOf(rating.getRating()));
                        place.setRate((int) Math.round(rating.getRating()));
                        intent.putExtra(THIEF, place);
                        intent.putExtra("position", position);
                        setResult(RESULT_OK, intent);
                        dialog.dismiss();
                    }
                })

                .setNegativeButton("Отмена",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        ratingdialog.create();
        ratingdialog.show();
    }





}
