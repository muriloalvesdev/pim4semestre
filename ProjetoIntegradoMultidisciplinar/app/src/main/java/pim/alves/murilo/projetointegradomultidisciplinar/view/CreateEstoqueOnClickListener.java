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

public class CreateEstoqueOnClickListener implements View.OnClickListener{

    @Override
    public void onClick(View v) {
        final Context context = v.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final View formElementsView = inflater.inflate(R.layout.estoque_form, null, false);
        final EditText editNomeProduto = (EditText) formElementsView.findViewById(R.id.editNomeProduto);
        final EditText editTextQuantidadeProduto = (EditText) formElementsView.findViewById(R.id.editTextQuantidadeProduto);

        new AlertDialog.Builder(context)
                .setView(formElementsView)
                .setTitle("Salvar Estoque")
                .setPositiveButton("Incluir",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                String nomeProduto = editNomeProduto.getText().toString();
                                String quantidade = editTextQuantidadeProduto.getText().toString();

                                if(nomeProduto.equals("") || quantidade.equals("")){
                                    Toast.makeText(context, "Você não preencheu todos os campos, o produto não foi armazenado.", Toast.LENGTH_SHORT).show();
                                }else {
                                    Estoque estoque = new Estoque();
                                    estoque.setNomeProduto(nomeProduto);
                                    estoque.setQuantidadeProdutos(quantidade);

                                    boolean criadoComSucesso = new EstoqueController(context).createEstoque(estoque);

                                    if (criadoComSucesso) {
                                        Toast.makeText(context, "Produto adicionado com sucesso.", Toast.LENGTH_SHORT).show();
                                        ((MainActivity) context).totalEstoque();
                                    } else {
                                        Toast.makeText(context, "Não foi possível adicionar o produto.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                dialog.cancel();
                            }
                        }).show();

    }
}
