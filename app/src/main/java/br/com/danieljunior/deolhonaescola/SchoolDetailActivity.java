package br.com.danieljunior.deolhonaescola;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import br.com.danieljunior.deolhonaescola.fragments.dialogs.SchoolDetailDialog;
import br.com.danieljunior.deolhonaescola.fragments.dialogs.SearchResultDialog;
import br.com.danieljunior.deolhonaescola.models.School;

public class SchoolDetailActivity extends AppCompatActivity {
    TableLayout tl;
    School school;
    int id = 100;
    Button mapButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String[] details = new String[9];
        super.onCreate(savedInstanceState);
        school = (School) getIntent().getBundleExtra("school").get("school");
        setContentView(R.layout.activity_school_detail);
        tl = (TableLayout) findViewById(R.id.maintable);
        fillTableData(school);
        mapButton = (Button)findViewById(R.id.map_show);
        if(school.getLatitude() == 0.0 && school.getLongitude() == 0.0){
            mapButton.setEnabled(false);
            Toast.makeText(this,"Localização indisponível para esta escola.", Toast.LENGTH_LONG);
        }
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
                SchoolDetailDialog aboutDialog = new SchoolDetailDialog();
                aboutDialog.show(getFragmentManager(), "SchoolDetail");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void fillTableData(School school) {
        addRowToTable("Nome: ", school.getName());
        addRowToTable("Município: ", school.getMunicipio());
        addRowToTable("Diretoria Regional: ", school.getDiretoriaRegional());
        addRowToTable("Despesas Merenda: ", "R$ " + school.getDespesaMerenda());
        addRowToTable("Despesas Manuntenção: ", "R$ " + school.getDespesaManuntencao());
        addRowToTable("Receitas Merenda: ", "R$ " + school.getReceitaMerenda());
        addRowToTable("Receitas Manuntenção: ", "R$ " + school.getReceitaManuntencao());
        addRowToTable("Saldo Merenda: ", "R$ " + school.getSaldoMerenda());
        addRowToTable("Saldo Manuntenção: ", "R$ " + school.getSaldoManuntencao());
    }

    private void addRowToTable(String description, String name) {
        // Create a TableRow and give it an ID
        TableRow tr = new TableRow(this);
        tr.setId(id++);
        tr.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));

        // Create a TextView to house the name of the province
        TextView label = new TextView(this);
        label.setId(id++);
        label.setText(description);
        label.setTextColor(Color.BLACK);
        label.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        label.setTypeface(null, Typeface.BOLD);
        tr.addView(label);

        // Create a TextView to house the value of the after-tax income
        TextView value = new TextView(this);
        value.setId(id++);
        value.setText(name);
        value.setTextColor(Color.BLACK);
        value.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        tr.addView(value);

        // Add the TableRow to the TableLayout
        tl.addView(tr, new TableLayout.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));
    }

    public void mapButtonClick(View v) {
        Intent intent = new Intent(this, MapsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("school", school);
        intent.putExtra("school", bundle);
        startActivity(intent);
    }

    public void reclaimButtonClick(View v){
        Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("plain/text");
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"cerel.ascom@educacao.rj.gov.br"});
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "RECLAMAÇÃO SEEDUC");
        startActivity(Intent.createChooser(emailIntent, "Enviar email..."));
    }

}
