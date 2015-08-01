package uk.me.jeffsutton.colourmemory.model;

import android.support.annotation.NonNull;

/**
 * Created by jeff on 01/08/15.
 */
public class HighScore implements Comparable<HighScore> {
    public int score;
    public String name;

    @Override
    public int compareTo(@NonNull HighScore another) {
        return (score > another.score ? -1 : (score==another.score ? 0 : 1));
    }
}
