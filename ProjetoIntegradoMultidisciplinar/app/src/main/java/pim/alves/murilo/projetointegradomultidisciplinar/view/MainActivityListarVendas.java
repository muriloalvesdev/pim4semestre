package pim.alves.murilo.projetointegradomultidisciplinar.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import pim.alves.murilo.projetointegradomultidisciplinar.R;
import pim.alves.murilo.projetointegradomultidisciplinar.controller.VendaController;
import pim.alves.murilo.projetointegradomultidisciplinar.model.Venda;

public class MainActivityListarVendas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_listar_vendas);
        atualizarListaVendas();
    }

    public void chamarActivityMain(View view){
        Context context = view.getContext();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public void atualizarListaVendas(){

        LinearLayout linearLayoutRecords = (LinearLayout) findViewById(R.id.objetosVendas);
        linearLayoutRecords.removeAllViews();
        List<Venda> vendas = new VendaController(this).listVendas();

        if(vendas.size() > 0){
            for(Venda obj: vendas){
                int id = obj.getId();
                String nome = obj.getNomeProduto();
                String quantidade = obj.getQuantidade();
                String textViewVenda = nome + " - " + quantidade;

                TextView textViewVendasItem = new TextView(this);
                textViewVendasItem.setPadding(0, 10, 0, 10);
                textViewVendasItem.setText(textViewVenda.toString());
                textViewVendasItem.setTag(Integer.toString(id));

                linearLayoutRecords.addView(textViewVendasItem);
                textViewVendasItem.setOnLongClickListener(new RetrieveOnLongClickListenerVendas());
            }
        }
    }
}
