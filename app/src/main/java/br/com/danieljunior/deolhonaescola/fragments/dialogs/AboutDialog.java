package br.com.danieljunior.deolhonaescola.fragments.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import br.com.danieljunior.deolhonaescola.R;

/**
 * Created by danieljunior on 04/01/17.
 */

public class AboutDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Sobre este aplicativo");
        builder.setIcon(R.mipmap.ic_info);
        builder.setMessage("De olho na Escola \n\nEste é um aplicativo que tem como objetivo fornecer informações sobre " +
                "as receitas e despesas das escolas da rede estadual de ensino do Rio de Janeiro, além de " +
                "prover a funcionalidade de submeter uma denúncia para a ouvidoria do Estado.\n\n" +
                "Desenvolvido por Daniel Junior.");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
