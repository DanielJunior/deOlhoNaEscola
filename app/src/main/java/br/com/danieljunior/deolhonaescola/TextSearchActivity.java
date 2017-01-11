package br.com.danieljunior.deolhonaescola;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import java.util.ArrayList;

import br.com.danieljunior.deolhonaescola.asynctasks.LoadSchoolTask;
import br.com.danieljunior.deolhonaescola.fragments.dialogs.NoConectivityDialog;
import br.com.danieljunior.deolhonaescola.fragments.dialogs.NoSearchResultsDialog;
import br.com.danieljunior.deolhonaescola.fragments.dialogs.TextSearchDialog;
import br.com.danieljunior.deolhonaescola.interfaces.LoadSchoolCallback;
import br.com.danieljunior.deolhonaescola.models.School;

public class TextSearchActivity extends AppCompatActivity implements LoadSchoolCallback {
    RadioButton rb1;
    RadioButton rb2;
    RadioButton rb3;
    EditText school;
    EditText exp1;
    EditText inc1;
    ArrayList<School> schoolList;
    int searchType;
    private ArrayList<School> listSchool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_search);
        rb1 = (RadioButton) findViewById(R.id.radio0);
        rb2 = (RadioButton) findViewById(R.id.radio1);
        rb3 = (RadioButton) findViewById(R.id.radio2);
        school = (EditText) findViewById(R.id.school_name);
        exp1 = (EditText) findViewById(R.id.expense);
        inc1 = (EditText) findViewById(R.id.income);
        searchType = LoadSchoolTask.BY_NAME;
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
                TextSearchDialog aboutDialog = new TextSearchDialog();
                aboutDialog.show(getFragmentManager(), "TextSearch");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void radioButton1Click(View v) {
        school.setEnabled(true);
        school.requestFocus();
        searchType = LoadSchoolTask.BY_NAME;
        exp1.setEnabled(false);
        inc1.setEnabled(false);
        rb1.toggle();
        rb2.setChecked(false);
        rb3.setChecked(false);
    }

    public void radioButton2Click(View v) {
        school.setEnabled(false);
        exp1.setEnabled(true);
        exp1.requestFocus();
        searchType = LoadSchoolTask.BY_EXPENSE;
        inc1.setEnabled(false);
        rb1.setChecked(false);
        rb2.toggle();
        rb3.setChecked(false);
    }

    public void radioButton3Click(View v) {
        school.setEnabled(false);
        exp1.setEnabled(false);
        inc1.setEnabled(true);
        searchType = LoadSchoolTask.BY_INCOME;
        inc1.requestFocus();
        rb1.setChecked(false);
        rb2.setChecked(false);
        rb3.toggle();
    }

    public void searchButtonClick(View v) {
        String param;
        if (school.isEnabled()) {
            param = school.getText().toString();
        } else if (exp1.isEnabled()) {
            param = exp1.getText().toString();
        } else {
            param = inc1.getText().toString();
        }

        loadSchools(searchType, param);
    }

    public void setSchoolList(ArrayList<School> listSchool) {
        this.listSchool = listSchool;
    }

    public void postResult() {
        if (listSchool == null) {
            NoConectivityDialog noConectivityDialog = new NoConectivityDialog();
            noConectivityDialog.show(getFragmentManager(), "NoConectivity");
        } else if (listSchool.size() > 0) {
            Intent intent = new Intent(getApplicationContext(), SearchResultActivity.class);
            intent.putExtra("list", listSchool);
            startActivity(intent);
        } else {
            NoSearchResultsDialog searchDialog = new NoSearchResultsDialog();
            searchDialog.show(getFragmentManager(), "SearchResult");
        }
    }

    public void loadSchools(int searchType, String param) {
        LoadSchoolTask load = new LoadSchoolTask(this, this, searchType);
        load.execute(param);
    }
}
