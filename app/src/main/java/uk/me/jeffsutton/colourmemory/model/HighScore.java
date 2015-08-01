package uk.me.jeffsutton.colourmemory.model;

/**
 * Created by jeff on 01/08/15.
 */
public class HighScore implements Comparable<HighScore> {
    public int score;
    public String name;

    @Override
    public int compareTo(HighScore another) {
        return Integer.compare(score, another.score);
    }
}
