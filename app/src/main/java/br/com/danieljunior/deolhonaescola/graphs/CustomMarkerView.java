package br.com.danieljunior.deolhonaescola.graphs;

import android.content.Context;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;

import br.com.danieljunior.deolhonaescola.R;

/**
 * Created by danieljunior on 11/01/17.
 */

public class CustomMarkerView extends MarkerView {
    /**
     * Constructor. Sets up the MarkerView with a custom layout resource.
     *
     * @param context
     */

    private TextView tvContent;
    private int type;
    public static final int EXPENSE = 0;
    public static final int INCOME = 1;
    public CustomMarkerView(Context context, int type) {
        super(context, R.layout.marker_view_layout);
        tvContent = (TextView) findViewById(R.id.marker_content);
        this.type = type;
    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        if(type == EXPENSE){
            tvContent.setText((int)e.getX()+" escolas com até \nR$" + e.getY()+" de despesas.");
        }else{
            tvContent.setText((int)e.getX()+" escolas com até R$" + e.getY()+" de receitas.");
        }
        // this will perform necessary layouting
        super.refreshContent(e, highlight);
    }

    private MPPointF mOffset;

    @Override
    public MPPointF getOffset() {

        if(mOffset == null) {
            // center the marker horizontally and vertically
            mOffset = new MPPointF(-(getWidth() / 2), -getHeight());
        }

        return mOffset;
    }
}
