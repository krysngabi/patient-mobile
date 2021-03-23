package com.smartit.priis;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.smartit.priis.online.APICategorie;
import com.smartit.priis.online.ApiClient;
import com.smartit.priis.online.MSG;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Patient extends AppCompatActivity implements View.OnClickListener {
    RelativeLayout rel1;

    EditText fname, lname, age;
    private ProgressDialog pDialog;
    TextView text_field_title, footer;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient);

        fname = (EditText) findViewById(R.id.fname);
        lname = (EditText) findViewById(R.id.lname);
        age = (EditText) findViewById(R.id.rAge);
        save = (Button) findViewById(R.id.btnSave);
        rel1 = (RelativeLayout) findViewById(R.id.rel1);
        save.setOnClickListener(this);
        text_field_title = (TextView) findViewById(R.id.text_field_title);
        footer = (TextView) findViewById(R.id.mfooter);

        initializeView();

    }

    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnSave:

                if (fname.getText().length() > 0 && lname.getText().length() > 0 && age.getText().length() > 0) {

                    register();
                } else {
                    new CustomToast().Show_Toast(Patient.this
                            , rel1,
                            "Empty Fields");
                }
                break;


        }
    }

    private void register() {
        pDialog = new ProgressDialog(Patient.this);
        pDialog.setIndeterminate(true);
        pDialog.setMessage("NEW PATIENT...");
        pDialog.setCancelable(false);

        showpDialog();
        APICategorie service = ApiClient.getClient2().create(APICategorie.class);
        Call<MSG> userCall = service.newPatient(" ", fname.getText().toString(), lname.getText().toString(), age.getText().toString());


        userCall.enqueue(new Callback<MSG>() {
            @Override
            public void onResponse(Call<MSG> call, Response<MSG> response) {
                hidepDialog();
                //onSignupSuccess();
                int rep = response.body().getSuccess();

                if (rep > 0) {
                    new CustomToast().Show_Toast1(Patient.this
                            , rel1,
                            "Patient created");
                    Intent i = new Intent(getApplicationContext(), PatientList.class);
                    startActivity(i);

                    finish();
                } else {
                    new CustomToast().Show_Toast(Patient.this
                            , rel1,
                            "Error while adding!");
                }
                Log.d("register", response.body().getSuccess() + "-->" + response.body().getMessage());

            }

            @Override
            public void onFailure(Call<MSG> call, Throwable t) {
                hidepDialog();
                Log.d("register", t.getMessage());
                new CustomToast().Show_Toast(Patient.this
                        , rel1,
                        t.getMessage());


            }
        });
    }

    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    private void initializeView() {
        /* Get Information text view and ImageView handle to be used further */

        Typeface face = Typeface.createFromAsset(getAssets(),
                "fonts/AvenirNextLTPro-MediumCn.otf");
        lname.setTypeface(face);
        fname.setTypeface(face);
        age.setTypeface(face);
        save.setTypeface(face);
        text_field_title.setTypeface(face);
        footer.setTypeface(face);


    }
}
