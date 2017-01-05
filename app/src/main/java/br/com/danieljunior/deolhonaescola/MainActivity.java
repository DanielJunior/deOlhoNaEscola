package br.com.danieljunior.deolhonaescola;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import java.util.ArrayList;

import br.com.danieljunior.deolhonaescola.adapters.GridViewAdapter;
import br.com.danieljunior.deolhonaescola.fragments.dialogs.AboutDialog;


public class MainActivity extends AppCompatActivity {
    GridView gv;
    Context context;
    ArrayList actionsName;
    public static String [] actionsNameList={"Busca textual","Busca no Mapa", "Estatísticas Gerais"};
    public static int [] actionsImages ={R.drawable.text_search,R.drawable.map_search,R.drawable.chart,};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gv=(GridView) findViewById(R.id.gridView);
        gv.setAdapter(new GridViewAdapter(this, actionsNameList, actionsImages));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.info:
                AboutDialog aboutDialog = new AboutDialog();
                aboutDialog.show(getFragmentManager(), "About");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
