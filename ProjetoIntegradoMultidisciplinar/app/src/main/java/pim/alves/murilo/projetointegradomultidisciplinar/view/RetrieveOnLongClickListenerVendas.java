package pim.alves.murilo.projetointegradomultidisciplinar.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import pim.alves.murilo.projetointegradomultidisciplinar.R;
import pim.alves.murilo.projetointegradomultidisciplinar.controller.VendaController;
import pim.alves.murilo.projetointegradomultidisciplinar.model.Venda;

public class RetrieveOnLongClickListenerVendas implements View.OnLongClickListener{
    Context contexto;
    String id;

    @Override
    public boolean onLongClick(View v) {
        contexto = v.getContext();
        id = v.getTag().toString();

        final CharSequence[] itens = {"Editar", "Deletar"};
        new AlertDialog.Builder(contexto).setTitle("Detalhes da venda").setItems(itens, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if(item == 0){
                    editarVendaPeloId(Integer.parseInt(id));
                }else if(item == 1){
                    boolean excluidoComSucesso = new VendaController(contexto).deleteVenda(Integer.parseInt(id));
                    if(excluidoComSucesso){
                        Toast.makeText(contexto, "Excluído com sucesso", Toast.LENGTH_SHORT).show();
                        ((MainActivityListarVendas) contexto).atualizarListaVendas();
                    }else{
                        Toast.makeText(contexto, "Erro ao excuir", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }).show();
        Toast.makeText(v.getContext(), "Item da lista selecionado", Toast.LENGTH_SHORT).show();
        return false;
    }

    public void editarVendaPeloId(final int vendasId){
        Toast.makeText(contexto, "Editando" + vendasId, Toast.LENGTH_SHORT).show();

        final VendaController controller = new VendaController(contexto);
        final Venda venda = controller.findById(vendasId);

        LayoutInflater inflater = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formVendas = inflater.inflate(R.layout.venda_form, null, false);

        final EditText editTextNomeProduto = (EditText) formVendas.findViewById(R.id.editTextNomeProduto);
        final EditText editTextValor = (EditText) formVendas.findViewById(R.id.editTextValorProduto);

        editTextNomeProduto.setText(venda.getNomeProduto());
        editTextValor.setText(venda.getQuantidade());

        new AlertDialog.Builder(contexto)
                .setView(formVendas)
                .setTitle("Editar")
                .setPositiveButton("Atualizar dados",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                Venda atualizarVenda  = new Venda();

                                atualizarVenda.setId(vendasId);
                                atualizarVenda.setNomeProduto(editTextNomeProduto.getText().toString());
                                atualizarVenda.setQuantidade(editTextValor.getText().toString());

                                boolean atualizar = controller.updateVenda(atualizarVenda);
                                if(atualizar){
                                    Toast.makeText(contexto, "Dados atualizados com sucesso", Toast.LENGTH_LONG).show();
                                    ((MainActivityListarVendas) contexto).atualizarListaVendas();
                                }else{
                                    Toast.makeText(contexto, "Erro ao atualizar os dados", Toast.LENGTH_SHORT).show();
                                }
                                dialog.cancel();
                            }
                        }).show();
    }
}
