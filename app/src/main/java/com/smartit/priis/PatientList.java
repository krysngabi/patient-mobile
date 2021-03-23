package com.smartit.priis;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.smartit.priis.models.PatientsModel;
import com.smartit.priis.online.APICategorie;
import com.smartit.priis.online.ApiClient;
import com.weiwangcn.betterspinner.library.BetterSpinner;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PatientList extends AppCompatActivity {
    RelativeLayout rel1;
    BetterSpinner spinner;
    private List<PatientsModel> patientList;
    List<String> patients=new ArrayList<>();

    TextView text_field_title,footer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.patients);

        spinner = (BetterSpinner) findViewById(R.id.spinner);
        ArrayAdapter<String> departmentAdapter = new ArrayAdapter<>(PatientList.this, android.R.layout.simple_dropdown_item_1line, new ArrayList<String>());
        spinner.setAdapter(departmentAdapter);

        populatePatient();


    }

    private void populatePatient() {

        APICategorie service = ApiClient.getClient2().create(APICategorie.class);
        Call<List<PatientsModel>> call = service.getPatients();
        call.enqueue(new Callback<List<PatientsModel>>() {
            @Override
            public void onResponse(Call<List<PatientsModel>> call, final Response<List<PatientsModel>> response) {

                patientList = response.body();

                for(PatientsModel p:patientList){
                    patients.add(new StringBuilder().append(p.getFirstname()).append(" ").append(p.getLastname()).toString());
                }


                ArrayAdapter<String> adapter = new ArrayAdapter<String>(PatientList.this, android.R.layout.simple_dropdown_item_1line,patients);
                spinner.setAdapter(adapter);


            }

            @Override
            public void onFailure(Call<List<PatientsModel>> call, Throwable t) {
                Log.e("Error",t.getMessage());
                Toast.makeText(getApplicationContext(), "Unable to fetch json: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void initializeView() {
        /* Get Information text view and ImageView handle to be used further */

        Typeface face = Typeface.createFromAsset(getAssets(),
                "fonts/AvenirNextLTPro-MediumCn.otf");
        text_field_title.setTypeface(face);
        spinner.setTypeface(face);
        footer.setTypeface(face);


    }
    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(), Patient.class);
        startActivity(i);

        finish();
    }
}
