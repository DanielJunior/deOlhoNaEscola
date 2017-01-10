package br.com.danieljunior.deolhonaescola.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by danieljunior on 06/01/17.
 */

public class School implements Serializable, Parcelable {
    int id;
    String name;
    String municipio;
    String diretoriaRegional;
    double despesaMerenda;
    double despesaManuntencao;
    double receitaMerenda;
    double receitaManuntencao;
    double saldoMerenda;
    double saldoManuntencao;
    double latitude;
    double longitude;

    public School() {
    }

    public School(int id, String name, String municipio, String diretoriaRegional, double despesaMerenda, double despesaManuntencao, double receitaMerenda, double receitaManuntencao, double saldoMerenda, double saldoManuntencao, double latitude, double longitude) {
        this.id = id;
        this.name = name;
        this.municipio = municipio;
        this.diretoriaRegional = diretoriaRegional;
        this.despesaMerenda = despesaMerenda;
        this.despesaManuntencao = despesaManuntencao;
        this.receitaMerenda = receitaMerenda;
        this.receitaManuntencao = receitaManuntencao;
        this.saldoMerenda = saldoMerenda;
        this.saldoManuntencao = saldoManuntencao;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    protected School(Parcel in) {
        id = in.readInt();
        name = in.readString();
        municipio = in.readString();
        diretoriaRegional = in.readString();
        despesaMerenda = in.readDouble();
        despesaManuntencao = in.readDouble();
        receitaMerenda = in.readDouble();
        receitaManuntencao = in.readDouble();
        saldoMerenda = in.readDouble();
        saldoManuntencao = in.readDouble();
        latitude = in.readDouble();
        longitude = in.readDouble();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(municipio);
        dest.writeString(diretoriaRegional);
        dest.writeDouble(despesaMerenda);
        dest.writeDouble(despesaManuntencao);
        dest.writeDouble(receitaMerenda);
        dest.writeDouble(receitaManuntencao);
        dest.writeDouble(saldoMerenda);
        dest.writeDouble(saldoManuntencao);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<School> CREATOR = new Creator<School>() {
        @Override
        public School createFromParcel(Parcel in) {
            return new School(in);
        }

        @Override
        public School[] newArray(int size) {
            return new School[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getDiretoriaRegional() {
        return diretoriaRegional;
    }

    public void setDiretoriaRegional(String diretoriaRegional) {
        this.diretoriaRegional = diretoriaRegional;
    }

    public double getDespesaMerenda() {
        return despesaMerenda;
    }

    public void setDespesaMerenda(double despesaMerenda) {
        this.despesaMerenda = despesaMerenda;
    }

    public double getDespesaManuntencao() {
        return despesaManuntencao;
    }

    public void setDespesaManuntencao(double despesaManuntencao) {
        this.despesaManuntencao = despesaManuntencao;
    }

    public double getReceitaMerenda() {
        return receitaMerenda;
    }

    public void setReceitaMerenda(double receitaMerenda) {
        this.receitaMerenda = receitaMerenda;
    }

    public double getReceitaManuntencao() {
        return receitaManuntencao;
    }

    public void setReceitaManuntencao(double receitaManuntencao) {
        this.receitaManuntencao = receitaManuntencao;
    }

    public double getSaldoMerenda() {
        return saldoMerenda;
    }

    public void setSaldoMerenda(double saldoMerenda) {
        this.saldoMerenda = saldoMerenda;
    }

    public double getSaldoManuntencao() {
        return saldoManuntencao;
    }

    public void setSaldoManuntencao(double saldoManuntencao) {
        this.saldoManuntencao = saldoManuntencao;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
