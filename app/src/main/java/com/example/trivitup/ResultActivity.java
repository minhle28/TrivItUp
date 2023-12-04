package com.example.trivitup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.trivitup.databinding.ActivityResultBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

public class ResultActivity extends AppCompatActivity {
    ActivityResultBinding binding;
    FirebaseFirestore database;
    long POINTS = 10;
    MediaPlayer player2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityResultBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int correctAnswers = getIntent().getIntExtra("correct",0);
        int totalQuestions = getIntent().getIntExtra("total",0);

        long points = correctAnswers * POINTS;
        binding.score.setText(String.format("%d/%d",correctAnswers,totalQuestions));
        binding.pointsEarned.setText(String.valueOf(points));

        if (player2 == null){
            player2 = MediaPlayer.create(this, R.raw.complete);
            player2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    stopPlayer();
                }
            });
        }
        player2.start();

        FirebaseFirestore database = FirebaseFirestore.getInstance();
        database.collection("users")
                .document(FirebaseAuth.getInstance().getUid())
                .update("points", FieldValue.increment(points));

    }

    public void stop(View v){
        stopPlayer();
    }
    public void stopPlayer(){
        if (player2 == null) {
            player2.release();
            player2 = null;
            Toast.makeText(this,"MediaPlayer released", Toast.LENGTH_SHORT);
        }
    }
    @Override
    protected void onStop(){
        super.onStop();
        stopPlayer();
    }

    public void onClick(View view){
        int viewId = view.getId();

        if (viewId == R.id.restartBtn) {
            Intent intent = new Intent(ResultActivity.this,MainActivity.class);
            startActivity(intent);}

        }

}