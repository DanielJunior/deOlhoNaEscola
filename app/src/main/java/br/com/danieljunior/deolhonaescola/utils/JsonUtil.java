package br.com.danieljunior.deolhonaescola.utils;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import br.com.danieljunior.deolhonaescola.models.School;

/**
 * Created by danieljunior on 09/01/17.
 */

public class JsonUtil {

    public static School mapperToSchool(JSONObject jsonObject) {
        School school = null;
        try {
            school = new School();
            school.setId(jsonObject.getInt("id"));
            school.setName(jsonObject.getString("unidade_escolar"));
            school.setMunicipio(jsonObject.getString("municipio"));
            school.setDiretoriaRegional(jsonObject.getString("diretoria_regional"));
            school.setDespesaManuntencao(jsonObject.getDouble("manuntencao_despesa"));
            school.setDespesaMerenda(jsonObject.getDouble("merenda_estadual_federal_despesa"));
            school.setLatitude(jsonObject.getDouble("latitude"));
            school.setLongitude(jsonObject.getDouble("longitude"));
            school.setReceitaManuntencao(jsonObject.getDouble("manuntencao_receita"));
            school.setReceitaMerenda(jsonObject.getDouble("merenda_estadual_federal_receita"));
            school.setSaldoManuntencao(jsonObject.getDouble("manuntencao_saldo_atual"));
            school.setSaldoMerenda(jsonObject.getDouble("merenda_estadual_federal_saldo_atual"));
        } catch (JSONException e) {
            Log.d(String.valueOf(Log.INFO), "School with falty data");
        } finally {
            return school;
        }
    }
}
