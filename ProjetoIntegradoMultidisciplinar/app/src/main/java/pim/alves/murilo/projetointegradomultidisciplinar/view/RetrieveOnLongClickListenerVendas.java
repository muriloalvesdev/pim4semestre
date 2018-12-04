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
    String valor;

    @Override
    public boolean onLongClick(View v) {
        contexto = v.getContext();
        id = v.getTag().toString();

        final CharSequence[] itens = {"Deletar"};
        new AlertDialog.Builder(contexto).setTitle("Deletar venda").setItems(itens, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if(item == 0){
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
}
