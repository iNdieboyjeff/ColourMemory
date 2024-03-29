package uk.me.jeffsutton.colourmemory.model;

import java.util.ArrayList;
import java.util.Collections;

import uk.me.jeffsutton.colourmemory.R;

/**
 * Created by jeff on 29/07/15.
 */
public class Card {

    private static final int CARD_TYPE_1 = R.drawable.colour1;
    private static final int CARD_TYPE_2 = R.drawable.colour2;
    private static final int CARD_TYPE_3 = R.drawable.colour3;
    private static final int CARD_TYPE_4 = R.drawable.colour4;
    private static final int CARD_TYPE_5 = R.drawable.colour5;
    private static final int CARD_TYPE_6 = R.drawable.colour6;
    private static final int CARD_TYPE_7 = R.drawable.colour7;
    private static final int CARD_TYPE_8 = R.drawable.colour8;

    public final int type;
    public boolean shown = false;
    public boolean matched = false;

    private Card(int type) {
        this.type = type;
    }

    public static ArrayList<Card> generateCards() {
        ArrayList<Card> data = new ArrayList<>(16);

        data.add(new Card(CARD_TYPE_1));
        data.add(new Card(CARD_TYPE_1));
        data.add(new Card(CARD_TYPE_2));
        data.add(new Card(CARD_TYPE_2));
        data.add(new Card(CARD_TYPE_3));
        data.add(new Card(CARD_TYPE_3));
        data.add(new Card(CARD_TYPE_4));
        data.add(new Card(CARD_TYPE_4));
        data.add(new Card(CARD_TYPE_5));
        data.add(new Card(CARD_TYPE_5));
        data.add(new Card(CARD_TYPE_6));
        data.add(new Card(CARD_TYPE_6));
        data.add(new Card(CARD_TYPE_7));
        data.add(new Card(CARD_TYPE_7));
        data.add(new Card(CARD_TYPE_8));
        data.add(new Card(CARD_TYPE_8));

        Collections.shuffle(data);

        return data;
    }

}
