package uk.me.jeffsutton.colourmemory.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

import uk.me.jeffsutton.colourmemory.R;
import uk.me.jeffsutton.colourmemory.model.Card;

/**
 * Created by jeff on 29/07/15.
 */
public class CardGridAdapter extends BaseAdapter {

    private ArrayList<Card> mItems;
    private Context mContext;
    private LayoutInflater mInflater;

    public CardGridAdapter(Context context, ArrayList<Card> data) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(this.mContext);
        this.mItems = data;
    }

    @Override
    public int getCount() {
        if (mItems == null)
            return 0;
        else
            return mItems.size();
    }

    @Override
    public Object getItem(int position) {
        if (mItems == null)
            return null;
        else
            return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.card_item, parent, false);
            holder = new ViewHolder();
            holder.cardFace = (ImageView) convertView.findViewById(R.id.imageView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Card card = mItems.get(position);

        if (card.matched || card.shown) {
            holder.cardFace.setImageResource(card.type);
        } else {
            holder.cardFace.setImageResource(R.drawable.card_bg);
        }

        return convertView;
    }

    public class ViewHolder {
        ImageView cardFace;
    }
}
