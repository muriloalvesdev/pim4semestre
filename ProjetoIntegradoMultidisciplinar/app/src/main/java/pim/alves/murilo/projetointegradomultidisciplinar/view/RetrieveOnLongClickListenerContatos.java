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

public class RetrieveOnLongClickListenerContatos implements View.OnLongClickListener{

    Context contexto;
    String id;

    @Override
    public boolean onLongClick(View v) {
        contexto = v.getContext();
        id = v.getTag().toString();

        final CharSequence[] itens = {"Editar", "Deletar"};
        new AlertDialog.Builder(contexto).setTitle("Detalhes do contato").setItems(itens, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if(item == 0){
                    editarContatoPeloId(Integer.parseInt(id));
                }else if(item == 1){
                    boolean excluidoComSucesso = new ContatoController(contexto).deleteContato(Integer.parseInt(id));
                    if(excluidoComSucesso){
                        Toast.makeText(contexto, "Excluído com sucesso", Toast.LENGTH_SHORT).show();
                        ((MainActivityListarContatos) contexto).atualizarListaContatos();
                    }else{
                        Toast.makeText(contexto, "Erro ao excuir", Toast.LENGTH_SHORT).show();
                    }
                }

                dialog.dismiss();
            }
        }).show();
        Toast.makeText(v.getContext(), "Item da lista selecionado", Toast.LENGTH_SHORT).show();
        return false;
    }

    public void editarContatoPeloId(final int contatoId){
        Toast.makeText(contexto, "Editando" + contatoId, Toast.LENGTH_SHORT).show();

        final ContatoController controller = new ContatoController(contexto);
        final Contato contato = controller.findById(contatoId);

        LayoutInflater inflater = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formContato = inflater.inflate(R.layout.contato_form, null, false);

        final EditText editTextNome = (EditText) formContato.findViewById(R.id.editTextContatoNome);
        final EditText editTextEmail = (EditText) formContato.findViewById(R.id.editTextContatoEmail);

        editTextNome.setText(contato.getNome());
        editTextEmail.setText(contato.getEmail());

        new AlertDialog.Builder(contexto)
                .setView(formContato)
                .setTitle("Editar")
                .setPositiveButton("Atualizar dados",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                Contato contato  = new Contato();

                                contato.setId(contatoId);
                                contato.setNome(editTextNome.getText().toString());
                                contato.setEmail(editTextEmail.getText().toString());

                                boolean atualizar = controller.updateContato(contato);
                                if(atualizar){
                                    Toast.makeText(contexto, "Dados atualizados com sucesso", Toast.LENGTH_LONG).show();
                                    ((MainActivityListarContatos) contexto).atualizarListaContatos();
                                }else{
                                    Toast.makeText(contexto, "Erro ao atualizar os dados", Toast.LENGTH_SHORT).show();
                                }
                                dialog.cancel();
                            }
                        }).show();
    }
}
