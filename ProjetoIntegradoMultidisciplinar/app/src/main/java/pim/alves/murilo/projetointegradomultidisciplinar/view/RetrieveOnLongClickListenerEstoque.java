package pim.alves.murilo.projetointegradomultidisciplinar.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import pim.alves.murilo.projetointegradomultidisciplinar.R;
import pim.alves.murilo.projetointegradomultidisciplinar.controller.EstoqueController;
import pim.alves.murilo.projetointegradomultidisciplinar.model.Estoque;

public class RetrieveOnLongClickListenerEstoque  implements View.OnLongClickListener {
    Context contexto;
    String id;

    @Override
    public boolean onLongClick(View v) {
        contexto = v.getContext();
        id = v.getTag().toString();

        final CharSequence[] itens = {"Editar", "Deletar"};
        new AlertDialog.Builder(contexto).setTitle("Detalhes do estoque").setItems(itens, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if(item == 0){
                    editarEstoquePeloId(Integer.parseInt(id));
                }else if(item == 1){
                    boolean excluidoComSucesso = new EstoqueController(contexto).deleteEstoque(Integer.parseInt(id));
                    if(excluidoComSucesso){
                        Toast.makeText(contexto, "Excluído com sucesso", Toast.LENGTH_SHORT).show();
                        ((MainActivityListarEstoque) contexto).atualizarListaEstoquue();
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

    public void editarEstoquePeloId(final int estoqueId){
        Toast.makeText(contexto, "Editando" + estoqueId, Toast.LENGTH_SHORT).show();

        final EstoqueController controller = new EstoqueController(contexto);
        final Estoque estoque = controller.findById(estoqueId);

        LayoutInflater inflater = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formEstoque = inflater.inflate(R.layout.estoque_form, null, false);

        final EditText editTextNomeProduto = (EditText) formEstoque.findViewById(R.id.editNomeProduto);
        final EditText editTextQuantidade = (EditText) formEstoque.findViewById(R.id.editTextQuantidadeProduto);

        editTextNomeProduto.setText(estoque.getNomeProduto());
        editTextQuantidade.setText(estoque.getQuantidadeProdutos());

        new AlertDialog.Builder(contexto)
                .setView(formEstoque)
                .setTitle("Editar")
                .setPositiveButton("Atualizar dados",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                Estoque atualizarEstoque  = new Estoque();

                                atualizarEstoque.setId(estoqueId);
                                atualizarEstoque.setNomeProduto(editTextNomeProduto.getText().toString());
                                atualizarEstoque.setQuantidadeProdutos(editTextQuantidade.getText().toString());

                                boolean atualizar = controller.updateEstoque(atualizarEstoque);
                                if(atualizar){
                                    Toast.makeText(contexto, "Dados atualizados com sucesso", Toast.LENGTH_LONG).show();
                                    ((MainActivityListarEstoque) contexto).atualizarListaEstoquue();
                                }else{
                                    Toast.makeText(contexto, "Erro ao atualizar os dados", Toast.LENGTH_SHORT).show();
                                }
                                dialog.cancel();
                            }
                        }).show();
    }
}
