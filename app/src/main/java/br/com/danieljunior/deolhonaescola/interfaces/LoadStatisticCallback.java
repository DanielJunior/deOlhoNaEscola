package br.com.danieljunior.deolhonaescola.interfaces;

import java.util.ArrayList;

import br.com.danieljunior.deolhonaescola.models.CustomBarEntry;

/**
 * Created by danieljunior on 10/01/17.
 */

public interface LoadStatisticCallback {
    public void setBarEntries(ArrayList<CustomBarEntry> entries);
    public void postStatisticResult();
}
