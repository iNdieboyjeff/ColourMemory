package uk.me.jeffsutton.colourmemory;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import uk.me.jeffsutton.colourmemory.adapter.HighScoreAdapter;
import uk.me.jeffsutton.colourmemory.model.HighScoreTable;

public class HighScoresActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.from_middle, R.anim.to_middle);
        setContentView(R.layout.activity_high_scores);

        ListView mList = (ListView) findViewById(R.id.listView);
        Button doneButton = (Button) findViewById(R.id.button2);

        HighScoreTable table = HighScoreTable.loadHighScores(this);

        mList.setAdapter(new HighScoreAdapter(this, table.list));

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.from_middle, R.anim.to_middle);

            }
        });
    }

}
