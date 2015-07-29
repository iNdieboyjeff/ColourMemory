package uk.me.jeffsutton.colourmemory;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import uk.me.jeffsutton.colourmemory.adapter.CardGridAdapter;
import uk.me.jeffsutton.colourmemory.model.Card;


public class ColourmemoryActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    GridView grid;
    TextView scoreView;

    CardGridAdapter adapter;

    int score = 0;
    int activeCardPosition = -1;
    int remainincCards = 16;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colourmemory);

        grid = (GridView) findViewById(R.id.gridView);
        scoreView = (TextView) findViewById(R.id.textView);

        adapter = new CardGridAdapter(this, Card.generateCards());
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        final Card card = (Card) adapter.getItem(position);

        if (activeCardPosition == -1) {
            if (!card.shown) {
                card.shown = true;
                activeCardPosition = position;
            }
        } else {
            if (!card.shown) {
                card.shown = true;
            }
        }

        adapter.notifyDataSetChanged();

        if (position != activeCardPosition && activeCardPosition != -1) {
            grid.setOnItemClickListener(null);
            grid.postDelayed(new Runnable() {
                @Override
                public void run() {
                    compareCards(card, (Card) adapter.getItem(activeCardPosition));
                }
            }, 1000);

        }
    }

    private void compareCards(final Card lhs, final Card rhs) {


        if (lhs.type == rhs.type) {
            lhs.matched = true;
            rhs.matched = true;
            score += 2;
            remainincCards -= 2;
        } else {
            lhs.matched = false;
            rhs.matched = false;
            lhs.shown = false;
            rhs.shown = false;
            score--;
        }

        scoreView.setText("Score\n" + score);
        activeCardPosition = -1;
        grid.setOnItemClickListener(ColourmemoryActivity.this);
        adapter.notifyDataSetChanged();


    }
}
