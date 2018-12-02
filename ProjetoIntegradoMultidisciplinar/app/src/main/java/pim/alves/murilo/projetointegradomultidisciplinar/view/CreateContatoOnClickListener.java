package pim.alves.murilo.projetointegradomultidisciplinar.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import pim.alves.murilo.projetointegradomultidisciplinar.R;
import pim.alves.murilo.projetointegradomultidisciplinar.controller.ContatoController;
import pim.alves.murilo.projetointegradomultidisciplinar.model.Contato;

public class CreateContatoOnClickListener implements View.OnClickListener {

    @Override
    public void onClick(final View v) {
        final Context context = v.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final View formElementsView = inflater.inflate(R.layout.contato_form, null, false);
        final EditText editTextContatoNome = (EditText) formElementsView.findViewById(R.id.editTextContatoNome);
        final EditText editTextContatoEmail = (EditText) formElementsView.findViewById(R.id.editTextContatoEmail);

        new AlertDialog.Builder(context)
                .setView(formElementsView)
                .setTitle("Criar contato")
                .setPositiveButton("Incluir",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                String contatoNome = editTextContatoNome.getText().toString();
                                String contatoEmail = editTextContatoEmail.getText().toString();

                                if(contatoNome.equals("") || contatoEmail.equals("")){
                                    Toast.makeText(context, "Você não preencheu todos os campos, o contato não foi salvo.", Toast.LENGTH_SHORT).show();
                                }else {
                                    Contato contato = new Contato();
                                    contato.setNome(contatoNome);
                                    contato.setEmail(contatoEmail);

                                    boolean criadoComSucesso = new ContatoController(context).createContato(contato);

                                    if (criadoComSucesso) {
                                        Toast.makeText(context, "Contato salvo com sucesso.", Toast.LENGTH_SHORT).show();
                                        ((MainActivity) context).totalContatos();

                                    } else {
                                        Toast.makeText(context, "Não foi possível salvar o contato", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                dialog.cancel();
                            }
                        }).show();

    }
}
