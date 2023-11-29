package com.example.trivitup;

import androidx.annotation.NonNull;

import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


public class LeaderboardAdapter extends RecyclerView.Adapter<LeaderboardAdapter.ViewHolder> {

    private List<LeaderboardEntry> leaderboardEntries;

    public LeaderboardAdapter(List<LeaderboardEntry> leaderboardEntries) {
        this.leaderboardEntries = leaderboardEntries;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView rankTextView;
        public TextView nameTextView;
        public TextView pointsTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            rankTextView = itemView.findViewById(R.id.rankTextView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            pointsTextView = itemView.findViewById(R.id.pointsTextView);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.leaderboard_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LeaderboardEntry entry = leaderboardEntries.get(position);
        holder.rankTextView.setText(String.valueOf(position + 1));
        holder.nameTextView.setText(entry.getDisplayName());
        holder.pointsTextView.setText(String.valueOf(entry.getPoints()));
    }

    @Override
    public int getItemCount() {
        return leaderboardEntries.size();
    }
}
