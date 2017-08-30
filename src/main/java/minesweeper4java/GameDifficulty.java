package minesweeper4java;

/**
 * Created by olivergerhardt on 30.08.17.
 */
public enum GameDifficulty {
    EASY(0.1),MEDIUM(0.2),HARD(0.3), PEACE(0), HELL(1);

    private double percentage;

    GameDifficulty(double percentage) {
        this.percentage = percentage;
    }

    public double getPercentage() {
        return this.percentage;
    }
}