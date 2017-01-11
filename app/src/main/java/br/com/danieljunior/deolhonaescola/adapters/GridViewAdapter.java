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
import java.util.List;

import br.com.danieljunior.deolhonaescola.MainActivity;
import br.com.danieljunior.deolhonaescola.MapsActivity;
import br.com.danieljunior.deolhonaescola.R;
import br.com.danieljunior.deolhonaescola.StatisticsActivity;
import br.com.danieljunior.deolhonaescola.TextSearchActivity;
import br.com.danieljunior.deolhonaescola.asynctasks.LoadSchoolTask;
import br.com.danieljunior.deolhonaescola.asynctasks.LoadStatisticsTask;
import br.com.danieljunior.deolhonaescola.fragments.dialogs.NoConectivityDialog;
import br.com.danieljunior.deolhonaescola.fragments.dialogs.NoSearchResultsDialog;
import br.com.danieljunior.deolhonaescola.interfaces.LoadSchoolCallback;
import br.com.danieljunior.deolhonaescola.interfaces.LoadStatisticCallback;
import br.com.danieljunior.deolhonaescola.models.CustomBarEntry;
import br.com.danieljunior.deolhonaescola.models.School;

/**
 * Created by danieljunior on 03/01/17.
 */

public class GridViewAdapter extends BaseAdapter implements LoadSchoolCallback, LoadStatisticCallback {

    String[] result;
    Context context;
    int[] imageId;
    private static LayoutInflater inflater = null;
    private static final int TEXT_SEARCH = 0;
    private static final int MAP_SEARCH = 1;
    private static final int STATISTICS = 2;
    ArrayList<School> listSchool;
    ArrayList<CustomBarEntry> barEntries;

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
        final LoadSchoolCallback loadSchoolCallback = this;
        final LoadStatisticCallback loadStatisticCallback = this;
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
                        LoadSchoolTask loadSchoolTask = new LoadSchoolTask(context, loadSchoolCallback, LoadSchoolTask.ALL);
                        loadSchoolTask.execute("");
                        break;
                    case STATISTICS:
                        LoadStatisticsTask loadStatisticsTask = new LoadStatisticsTask(context, loadStatisticCallback);
                        loadStatisticsTask.execute();
                        break;
                    default:
                        Toast.makeText(context, "Essa não é uma opção válida.", Toast.LENGTH_LONG).show();
                }
            }
        });

        return rowView;
    }


    @Override
    public void setSchoolList(ArrayList<School> listSchool) {
        this.listSchool = listSchool;
    }

    @Override
    public void postResult() {
        if (listSchool == null) {
            NoConectivityDialog noConectivityDialog = new NoConectivityDialog();
            Activity activity = (Activity) context;
            noConectivityDialog.show(activity.getFragmentManager(), "NoConectivity");
        } else if (listSchool.size() > 0) {
            Intent intent = new Intent(context, MapsActivity.class);
            intent.putExtra("list", listSchool);
            context.startActivity(intent);
        } else {
            NoSearchResultsDialog searchDialog = new NoSearchResultsDialog();
            Activity activity = (Activity) context;
            searchDialog.show(activity.getFragmentManager(), "SearchResult");
        }
    }

    @Override
    public void setBarEntries(ArrayList<CustomBarEntry> entries) {
        this.barEntries = entries;
    }

    @Override
    public void postStatisticResult() {
        if (barEntries == null) {
            NoConectivityDialog noConectivityDialog = new NoConectivityDialog();
            Activity activity = (Activity) context;
            noConectivityDialog.show(activity.getFragmentManager(), "NoConectivity");
        } else if (barEntries.size() > 0) {
            Intent intent = new Intent(context, StatisticsActivity.class);
            intent.putExtra("barEntries", barEntries);
            context.startActivity(intent);
        } else {
            NoSearchResultsDialog searchDialog = new NoSearchResultsDialog();
            Activity activity = (Activity) context;
            searchDialog.show(activity.getFragmentManager(), "SearchResult");
        }
    }
}
