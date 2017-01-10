package br.com.danieljunior.deolhonaescola;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import br.com.danieljunior.deolhonaescola.adapters.ListViewAdapter;
import br.com.danieljunior.deolhonaescola.asynctasks.LoadSchoolTask;
import br.com.danieljunior.deolhonaescola.fragments.dialogs.NoSearchResultsDialog;
import br.com.danieljunior.deolhonaescola.fragments.dialogs.SearchResultDialog;
import br.com.danieljunior.deolhonaescola.fragments.dialogs.TextSearchDialog;
import br.com.danieljunior.deolhonaescola.models.School;

public class SearchResultActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView listView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<School> listSchool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        listView = (RecyclerView) findViewById(R.id.results);
        listView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        listView.setLayoutManager(mLayoutManager);
        listSchool = getIntent().getParcelableArrayListExtra("list");
        mAdapter = new ListViewAdapter(listSchool, this);
        listView.setAdapter(mAdapter);
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
                SearchResultDialog aboutDialog = new SearchResultDialog();
                aboutDialog.show(getFragmentManager(), "SearchResult");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {
        TextView text = (TextView) v.findViewById(R.id.result_item);
        String name = text.getText().toString();
        School school = findByName(name);
        Intent intent = new Intent(this, SchoolDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("school", school);
        intent.putExtra("school", bundle);
        startActivity(intent);
    }

    public School findByName(String name) {
        for (School s : listSchool) {
            if (s.getName().equalsIgnoreCase(name)) {
                return s;
            }
        }
        return null;
    }
}
