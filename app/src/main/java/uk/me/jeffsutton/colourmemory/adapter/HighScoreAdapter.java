package uk.me.jeffsutton.colourmemory.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import uk.me.jeffsutton.colourmemory.R;
import uk.me.jeffsutton.colourmemory.model.HighScore;

/**
 * Created by jeff on 29/07/15.
 */
public class HighScoreAdapter extends BaseAdapter {

    private final ArrayList<HighScore> mItems;
    private final LayoutInflater mInflater;

    public HighScoreAdapter(Context context, ArrayList<HighScore> data) {
        this.mInflater = LayoutInflater.from(context);
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
            convertView = mInflater.inflate(R.layout.high_score_item, parent, false);
            holder = new ViewHolder();
            holder.rank = (TextView) convertView.findViewById(R.id.rankView);
            holder.name = (TextView) convertView.findViewById(R.id.nameView);
            holder.score = (TextView) convertView.findViewById(R.id.scoreView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        HighScore score = mItems.get(position);

        holder.rank.setText(String.valueOf(position + 1) + ". ");
        holder.name.setText(score.name);
        holder.score.setText(String.valueOf(score.score));
        return convertView;
    }

    public class ViewHolder {
        TextView rank;
        TextView name;
        TextView score;

    }
}
