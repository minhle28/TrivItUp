package com.example.trivitup;

public class LeaderboardEntry {
    private String userId;
    private String displayName;
    private int points;

    // Required default constructor for Firebase
    public LeaderboardEntry() {
    }

    public LeaderboardEntry(String userId, String displayName, int points) {
        this.userId = userId;
        this.displayName = displayName;
        this.points = points;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}