package br.com.danieljunior.deolhonaescola.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import br.com.danieljunior.deolhonaescola.MainActivity;
import br.com.danieljunior.deolhonaescola.R;

/**
 * Created by danieljunior on 03/01/17.
 */

public class GridViewAdapter extends BaseAdapter {

    String[] result;
    Context context;
    int[] imageId;
    private static LayoutInflater inflater = null;
    private static final int TEXT_SEARCH = 0;
    private static final int MAP_SEARCH = 1;
    private static final int STATISTICS = 2;

    public GridViewAdapter(MainActivity mainActivity, String[] actionNamesList, int[] actionsImages) {
        // TODO Auto-generated constructor stub
        result = actionNamesList;
        context = mainActivity;
        imageId = actionsImages;
        inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return result.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder {
        TextView tv;
        ImageView img;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder = new Holder();
        View rowView;

        rowView = inflater.inflate(R.layout.home_items, null);
        holder.tv = (TextView) rowView.findViewById(R.id.textView1);
        holder.img = (ImageView) rowView.findViewById(R.id.imageView1);

        holder.tv.setText(result[position]);
        holder.img.setImageResource(imageId[position]);

        rowView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                switch (position) {
                    case TEXT_SEARCH:
                        break;
                    case MAP_SEARCH:
                        break;
                    case STATISTICS:
                        break;
                    default:
                        Toast.makeText(context, "Essa não é uma opção válida.", Toast.LENGTH_LONG).show();
                }
            }
        });

        return rowView;
    }
}
