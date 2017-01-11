package br.com.danieljunior.deolhonaescola;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import br.com.danieljunior.deolhonaescola.fragments.dialogs.StatisticsDialog;
import br.com.danieljunior.deolhonaescola.fragments.dialogs.TextSearchDialog;
import br.com.danieljunior.deolhonaescola.graphs.CustomMarkerView;
import br.com.danieljunior.deolhonaescola.interfaces.LoadStatisticCallback;
import br.com.danieljunior.deolhonaescola.models.CustomBarEntry;

public class StatisticsActivity extends AppCompatActivity {

    BarChart chart;
    BarChart chart2;
    List<BarEntry> entries;
    ArrayList<CustomBarEntry> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        chart = (BarChart) findViewById(R.id.chart);
        chart2 = (BarChart) findViewById(R.id.chart2);
        data = getIntent().getParcelableArrayListExtra("barEntries");
        fillChart(chart);
        fillChart(chart2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_text_search, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.info:
                StatisticsDialog aboutDialog = new StatisticsDialog();
                aboutDialog.show(getFragmentManager(), "TextSearch");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void fillChart(BarChart chart) {
        entries = new ArrayList<BarEntry>();
        for (CustomBarEntry entry : data) {
            entries.add(new BarEntry(entry.getX(), entry.getY()));
        }
        BarDataSet dataset = new BarDataSet(entries, "Despesas até");
        dataset.setColors(ColorTemplate.COLORFUL_COLORS);
        BarData data = new BarData(dataset);
        data.setBarWidth(10);

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(0);
        IAxisValueFormatter custom = new MyAxisValueFormatter();

        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setValueFormatter(custom);

        YAxis rightAxis = chart.getAxis(YAxis.AxisDependency.RIGHT);
        rightAxis.setEnabled(false);

        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);

        chart.getDescription().setEnabled(false);
        chart.setData(data);
    }

    private class MyAxisValueFormatter implements IAxisValueFormatter {
        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return "R$ " + value;
        }
    }
}
