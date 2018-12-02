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

public class CreateVendaOnClickListener implements View.OnClickListener{
    @Override
    public void onClick(View v) {
        final Context context = v.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final View formElementsView = inflater.inflate(R.layout.venda_form, null, false);
        final EditText editTextNomeProduto = (EditText) formElementsView.findViewById(R.id.editTextNomeProduto);
        final EditText editTextValorProduto = (EditText) formElementsView.findViewById(R.id.editTextValorProduto);

        new AlertDialog.Builder(context)
                .setView(formElementsView)
                .setTitle("Efetuar venda")
                .setPositiveButton("Incluir",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                String nomeProduto = editTextNomeProduto.getText().toString();
                                String valorProduto = editTextValorProduto.getText().toString();

                                if(nomeProduto.equals("") || valorProduto.equals("")){
                                    Toast.makeText(context, "Você não preencheu todos os campos, a venda não foi efetuada.", Toast.LENGTH_SHORT).show();
                                }else {
                                    Venda venda = new Venda();
                                    venda.setNomeProduto(nomeProduto);
                                    venda.setValorVenda(valorProduto);

                                    boolean criadoComSucesso = new VendaController(context).createVenda(venda);

                                    if (criadoComSucesso) {
                                        Toast.makeText(context, "Venda efetuada com sucesso.", Toast.LENGTH_SHORT).show();
                                        ((MainActivity) context).totalVenda();
                                    } else {
                                        Toast.makeText(context, "Não foi possível efetuar a venda", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                dialog.cancel();
                            }
                        }).show();

    }
}
