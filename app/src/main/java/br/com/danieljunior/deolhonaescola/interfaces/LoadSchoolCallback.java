package br.com.danieljunior.deolhonaescola.interfaces;

import java.util.ArrayList;

import br.com.danieljunior.deolhonaescola.models.School;

/**
 * Created by danieljunior on 10/01/17.
 */

public interface LoadSchoolCallback {
    public void setSchoolList(ArrayList<School> listSchool);
    public void postResult();
}
