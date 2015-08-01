package uk.me.jeffsutton.colourmemory;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import uk.me.jeffsutton.colourmemory.adapter.CardGridAdapter;
import uk.me.jeffsutton.colourmemory.model.Card;
import uk.me.jeffsutton.colourmemory.model.HighScore;
import uk.me.jeffsutton.colourmemory.model.HighScoreTable;


public class ColourMemoryActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, Animation.AnimationListener {

    private GridView grid;
    private TextView scoreView;

    private CardGridAdapter adapter;

    private int score = 0;
    private int activeCardPosition = -1;
    private int remainingCards = 16;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colourmemory);

        grid = (GridView) findViewById(R.id.gridView);
        scoreView = (TextView) findViewById(R.id.textView);
        Button highScoresButton = (Button) findViewById(R.id.button);

        highScoresButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHighScores();
            }
        });

        newGame();
    }

    private void showHighScores() {
        Intent intent = new Intent(this, HighScoresActivity.class);
        startActivity(intent);
    }

    private void newGame() {
        adapter = new CardGridAdapter(this, Card.generateCards());
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(this);
    }

    private void doFlipShow(final View view, final Card card, final int position) {
        Animation animation1 = AnimationUtils.loadAnimation(this, R.anim.to_middle);
        final Animation animation2 = AnimationUtils.loadAnimation(this, R.anim.from_middle);

        animation1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ImageView iv = (ImageView) view.findViewById(R.id.imageView);
                iv.setImageResource(card.type);
                view.setAnimation(animation2);
                view.startAnimation(animation2);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        animation2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
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

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        view.clearAnimation();
        view.setAnimation(animation1);
        view.startAnimation(animation1);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        final Card card = (Card) adapter.getItem(position);

        if (activeCardPosition == -1 && !card.shown) {
            card.shown = true;
            activeCardPosition = position;
            doFlipShow(view, card, position);
        } else if (!card.shown) {
            card.shown = true;
            doFlipShow(view, card, position);
        }

    }

    private void compareCards(final Card lhs, final Card rhs) {

        if (lhs.type == rhs.type) {
            lhs.matched = true;
            rhs.matched = true;
            score += 2;
            remainingCards -= 2;
        } else {
            lhs.matched = false;
            rhs.matched = false;
            lhs.shown = false;
            rhs.shown = false;
            score--;
        }

        scoreView.setText(getString(R.string.score_display, score));
        activeCardPosition = -1;
        grid.setOnItemClickListener(ColourMemoryActivity.this);
        adapter.notifyDataSetChanged();

        if (remainingCards == 0) {
            promptToSaveScore();
        }

    }

    private void promptToSaveScore() {
        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.save_score_prompt_dialog, null);
        final EditText nameInput = (EditText) promptsView.findViewById(R.id.editText);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(promptsView);
        builder.setCancelable(false);
        builder.setPositiveButton(R.string.ok, null);
        builder.setNegativeButton(R.string.cancel, null);

        final AlertDialog dialog = builder.create();
        dialog.show();

        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nameInput.getText().length() < 1) {
                    nameInput.setError(getString(R.string.forgot_name));
                } else {
                    saveScore(score, nameInput.getText().toString());
                    dialog.dismiss();
                    showHighScores();
                }
            }
        });
    }

    private void saveScore(int score, String name) {
        HighScore scores = new HighScore();
        scores.name = name;
        scores.score = score;

        HighScoreTable table = HighScoreTable.loadHighScores(this);
        table.addScore(scores);
        HighScoreTable.saveHighScores(this, table);
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
