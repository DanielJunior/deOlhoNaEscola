package br.com.danieljunior.deolhonaescola.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.danieljunior.deolhonaescola.MainActivity;
import br.com.danieljunior.deolhonaescola.MapsActivity;
import br.com.danieljunior.deolhonaescola.R;
import br.com.danieljunior.deolhonaescola.TextSearchActivity;
import br.com.danieljunior.deolhonaescola.asynctasks.LoadSchoolTask;
import br.com.danieljunior.deolhonaescola.fragments.dialogs.NoConectivityDialog;
import br.com.danieljunior.deolhonaescola.interfaces.LoadCallback;
import br.com.danieljunior.deolhonaescola.models.School;

/**
 * Created by danieljunior on 03/01/17.
 */

public class GridViewAdapter extends BaseAdapter implements LoadCallback {

    String[] result;
    Context context;
    int[] imageId;
    private static LayoutInflater inflater = null;
    private static final int TEXT_SEARCH = 0;
    private static final int MAP_SEARCH = 1;
    private static final int STATISTICS = 2;
    ArrayList<School> listSchool;

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

    @Override
    public void setSchoolList(ArrayList<School> listSchool) {
        this.listSchool = listSchool;
    }

    @Override
    public void postResult() {
        if (listSchool.size() > 0) {
            Intent intent = new Intent(context, MapsActivity.class);
            intent.putExtra("list", listSchool);
            context.startActivity(intent);
        } else {
            NoConectivityDialog noConectivityDialog = new NoConectivityDialog();
            Activity activity = (Activity) context;
            noConectivityDialog.show(activity.getFragmentManager(), "NoConectivity");
        }
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
        final LoadCallback loadCallback = this;
        rowView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                switch (position) {
                    case TEXT_SEARCH:
                        Intent intent = new Intent(context, TextSearchActivity.class);
                        context.startActivity(intent);
                        break;
                    case MAP_SEARCH:
                        LoadSchoolTask loadSchoolTask = new LoadSchoolTask(context, loadCallback, LoadSchoolTask.ALL);
                        loadSchoolTask.execute("");
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
