package com.example.varosok;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class InsertActivity extends AppCompatActivity {

    String adat = "";

    private EditText editTextOrszag, editTextVarosNev, editTextLakossag;
    private Button buttonVissza, buttonFelvetel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        init();

        buttonVissza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InsertActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        buttonFelvetel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String orszag = editTextOrszag.getText().toString().trim();
                String varos = editTextVarosNev.getText().toString().trim();
                String lakossag = editTextLakossag.getText().toString().trim();
                String json = "{ \"varos\" : \""+varos+"\", \"orszag\" : \""+orszag+
                        "\", \"lakossag\" : \""+lakossag+"\" }";

                PerformNetworkRequest request = new PerformNetworkRequest("POST", json);
                request.execute();
            }
        });
    }

    private void init(){
        editTextOrszag=findViewById(R.id.editTextOrszag);
        editTextVarosNev=findViewById(R.id.editTextVarosNev);
        editTextLakossag=findViewById(R.id.editTextLakossag);
        buttonVissza=findViewById(R.id.buttonVissza);
        buttonFelvetel=findViewById(R.id.buttonFelvetel);
    }




    private class PerformNetworkRequest extends AsyncTask<Void, Void, String> {
        String method;
        String content;

        public PerformNetworkRequest(String method) {
            this.method = method;
            this.content = "";
        }

        public PerformNetworkRequest(String method, String content) {
            this.method = method;
            this.content = content;
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                if (method.equals("GET")){
                    adat = Request.getData();
                } else {
                    adat = Request.postData(content);
                }
            } catch (IOException e) {
                adat = e.getMessage();
            }

            return adat;
        }
    }
}
