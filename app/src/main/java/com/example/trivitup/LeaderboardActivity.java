package com.example.trivitup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.Comparator;


public class LeaderboardActivity extends AppCompatActivity {
    private DatabaseReference leaderboardRef;
    private List<LeaderboardEntry> leaderboardEntries;
    private RecyclerView recyclerView;
    private LeaderboardAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        leaderboardRef = FirebaseDatabase.getInstance().getReference().child("Leaderboard");
        leaderboardEntries = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new LeaderboardAdapter(leaderboardEntries);
        recyclerView.setAdapter(adapter);

        // Fetch leaderboard entries from Firebase
        leaderboardRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                leaderboardEntries.clear();

                for (DataSnapshot entrySnapshot : dataSnapshot.getChildren()) {
                    LeaderboardEntry entry = entrySnapshot.getValue(LeaderboardEntry.class);
                    leaderboardEntries.add(entry);
                }

                // Sort the entries based on points (highest first)
                Collections.sort(leaderboardEntries, new Comparator<LeaderboardEntry>() {
                    @Override
                    public int compare(LeaderboardEntry entry1, LeaderboardEntry entry2) {
                        return Integer.compare(entry2.getPoints(), entry1.getPoints());
                    }
                });

                // Update UI with leaderboardEntries list
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle potential errors
            }
        });
    }
}
