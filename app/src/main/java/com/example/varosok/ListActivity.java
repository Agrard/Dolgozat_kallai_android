package com.example.varosok;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class ListActivity extends AppCompatActivity {

    String adat = "";

    private TextView textViewVarosok;
    private Button buttonVissza;
    //private LinearLayout LinearLayoutVarosokListForm;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        init();
        textViewVarosok.setMovementMethod(new ScrollingMovementMethod());
        kiir();

        buttonVissza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }


    private void kiir() {
        PerformNetworkRequest request = new PerformNetworkRequest("GET");
        request.execute();
    }

    private void frissitMuvelet() {
        this.runOnUiThread(frissites);
    }

    private Runnable frissites = new Runnable() {
        @Override
        public void run() {
            textViewVarosok.setText(adat);
        }
    };

    public void init() {
        textViewVarosok=findViewById(R.id.textViewVarosok);
        buttonVissza=findViewById(R.id.buttonVissza);
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

            frissitMuvelet();
            return adat;
        }
    }

}
