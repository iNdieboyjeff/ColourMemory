package uk.me.jeffsutton.colourmemory;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import uk.me.jeffsutton.colourmemory.adapter.HighScoreAdapter;
import uk.me.jeffsutton.colourmemory.model.HighScoreTable;

public class HighScoresActivity extends AppCompatActivity {

    ListView mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);

        mList = (ListView) findViewById(R.id.listView);

        HighScoreTable table = HighScoreTable.loadHighScores(this);

        mList.setAdapter(new HighScoreAdapter(this, table.list));
    }

}
