package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private String ip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //R.id.mainText
    }

    public void buttonClicked(View v) throws IOException {
        GetRequest request = new GetRequest();
        Button button = (Button) v;
        int id = button.getId();
        EditText input = (EditText) findViewById(R.id.inputIp);
        ip = input.getText().toString();
        System.out.println(ip);
        String out;

        String testSuffix = ".html";

        Firestore db = new Firestore();

        db.addIp(ip);
        db.getIp("Test");

        switch (id){
            case R.id.btnMode:
                out = request.sendGET(ip, "mode" + testSuffix);
                break;
            case R.id.btnHigher:
                out = request.sendGET(ip, "higher" + testSuffix);
                break;
            case R.id.btnLower:
                out = request.sendGET(ip, "lower" + testSuffix);
                break;
            case R.id.btnRainbow:
                out = request.sendGET(ip, "rainbow" + testSuffix);
                break;
            default:
                out = "Unknown button";
                break;
        }
        System.out.println(out);
        TextView txt = (TextView) findViewById(R.id.status);
        txt.setText(out);

    }
}