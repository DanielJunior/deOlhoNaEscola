package br.com.danieljunior.deolhonaescola.adapters;

import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.danieljunior.deolhonaescola.R;
import br.com.danieljunior.deolhonaescola.models.School;

/**
 * Created by danieljunior on 06/01/17.
 */
public class ListViewAdapter extends RecyclerView.Adapter {

    private ArrayList<School> results;
    private View.OnClickListener listener;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View mTextView;

        public ViewHolder(View v, View.OnClickListener listener) {
            super(v);
            mTextView = v;
            v.setOnClickListener(listener);
        }
    }

    public ListViewAdapter(ArrayList<School> results, View.OnClickListener listener) {
        this.results = results;
        this.listener = listener;
    }

    @Override
    public ListViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.result_text_view, parent, false);

        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v, listener);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        TextView t = (TextView) holder.itemView.findViewById(R.id.result_item);
        t.setText(results.get(position).getName());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return results.size();
    }

}
