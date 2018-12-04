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
import pim.alves.murilo.projetointegradomultidisciplinar.controller.VendaController;
import pim.alves.murilo.projetointegradomultidisciplinar.model.Estoque;
import pim.alves.murilo.projetointegradomultidisciplinar.model.Venda;

public class RetrieveOnLongClickListenerEstoque  implements View.OnLongClickListener {
    Context contexto;
    String id;

    @Override
    public boolean onLongClick(View v) {
        contexto = v.getContext();
        id = v.getTag().toString();

        final CharSequence[] itens = {"Vender", "Deletar"};
        new AlertDialog.Builder(contexto).setTitle("Detalhes do estoque").setItems(itens, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if(item == 0){
                    venderProduto(Integer.parseInt(id));
                }else if(item == 1){
                    boolean excluidoComSucesso = new EstoqueController(contexto).deleteEstoque(Integer.parseInt(id));
                    if(excluidoComSucesso){
                        Toast.makeText(contexto, "Excluído com sucesso", Toast.LENGTH_SHORT).show();
                        ((MainActivityListarEstoque) contexto).atualizarListaVendas();
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

    public void venderProduto(final int estoqueId){

        final EstoqueController controller = new EstoqueController(contexto);
        final Estoque estoque = controller.findById(estoqueId);
        final Estoque estoqueQuantidadeValor = controller.findByIdEstoqueParaVendas(estoqueId);

        String splitParticionado = estoqueQuantidadeValor.getQuantidadeProdutos();

        String split[] = splitParticionado.split(" ");

        Venda venda = new Venda();
        venda.setId(estoque.getId());
        venda.setNomeProduto(estoque.getNomeProduto());
        venda.setQuantidade(split[1]);
        venda.setValorVenda(split[4]);

        boolean criadoComSucesso = new VendaController(contexto).createVenda(venda);

        if(criadoComSucesso){
            Toast.makeText(contexto, "Venda efetuada com sucesso!", Toast.LENGTH_SHORT).show();
            boolean excluidoComSucesso = new EstoqueController(contexto).deleteEstoque(Integer.parseInt(id));
            if(excluidoComSucesso){
                ((MainActivityListarEstoque) contexto).atualizarListaVendas();
            }
        }else{
            Toast.makeText(contexto, "Erro ao efetuar a venda", Toast.LENGTH_SHORT).show();
        }


    }
}
