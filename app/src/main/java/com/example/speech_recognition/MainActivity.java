package com.example.speech_recognition;

import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtResult = findViewById(R.id.txtSpeechInput);
    }


//    get the voice inputs
    public void getSpeechInput(View view) {

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
//        ne hao ma ? :) كيفك
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.CHINA);

        if(intent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(intent, 10);
        }
        else{
            Toast.makeText(this, R.string.speech_not_supported,
                    Toast.LENGTH_LONG).show();
        }
    }


//    display the voice as text
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

//        switch (requestCode){
//            case 10:
                if(resultCode == RESULT_OK && data != null)
                {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    txtResult.setText(result.get(0));
                }
//                break;
//        }
    }
}
