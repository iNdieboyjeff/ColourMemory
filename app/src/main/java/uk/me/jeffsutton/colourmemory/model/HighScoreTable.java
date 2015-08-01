package uk.me.jeffsutton.colourmemory.model;

import android.content.Context;

import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by jeff on 01/08/15.
 */
public class HighScoreTable {
    public ArrayList<HighScore> list = new ArrayList<>();

    public static HighScoreTable loadHighScores(Context context) {
        try {
            FileInputStream fis = context.openFileInput("high_scores.json");
            StringBuilder builder = new StringBuilder();
            int ch;
            while((ch = fis.read()) != -1){
                builder.append((char)ch);
            }
            Gson gson = new Gson();
            HighScoreTable table = gson.fromJson(builder.toString(), HighScoreTable.class);
            Collections.sort(table.list);
            return table;
        } catch (Exception err) {
            err.printStackTrace();
            return new HighScoreTable();
        }
    }

    public static void saveHighScores(Context context, HighScoreTable scores) {
        try {
            Gson gson = new Gson();
            String data = gson.toJson(scores, HighScoreTable.class);
            FileOutputStream fos = context.openFileOutput("high_scores.json", Context.MODE_PRIVATE);
            fos.write(data.getBytes());
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addScore(HighScore scores) {
        list.add(scores);
        Collections.sort(list);
         if (list.size() > 10) {
             list = (ArrayList<HighScore>) list.subList(0, 10);
         }
    }
}
