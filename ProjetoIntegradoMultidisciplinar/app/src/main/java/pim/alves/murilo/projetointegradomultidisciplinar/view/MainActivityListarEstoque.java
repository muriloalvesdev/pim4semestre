package pim.alves.murilo.projetointegradomultidisciplinar.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import pim.alves.murilo.projetointegradomultidisciplinar.R;
import pim.alves.murilo.projetointegradomultidisciplinar.controller.EstoqueController;
import pim.alves.murilo.projetointegradomultidisciplinar.model.Estoque;

public class MainActivityListarEstoque extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_listar_estoque);
        atualizarListaEstoquue();
    }

    public void chamarActivityMain(View view){
        Context context = view.getContext();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public void atualizarListaEstoquue(){

        LinearLayout linearLayoutRecords = (LinearLayout) findViewById(R.id.objetosEstoque);
        linearLayoutRecords.removeAllViews();
        List<Estoque> estoque = new EstoqueController(this).listEstoque();

        if(estoque.size() > 0){
            for(Estoque obj: estoque){
                int id = obj.getId();
                String nome = obj.getNomeProduto();
                String quantidade = obj.getQuantidadeProdutos();
                String textViewEstoque = nome + " - " + quantidade;

                TextView textViewEstoqueItem = new TextView(this);
                textViewEstoqueItem.setPadding(0, 10, 0, 10);
                textViewEstoqueItem.setText(textViewEstoque.toString());
                textViewEstoqueItem.setTag(Integer.toString(id));

                linearLayoutRecords.addView(textViewEstoqueItem);
                textViewEstoqueItem.setOnLongClickListener(new RetrieveOnLongClickListenerEstoque());
            }
        }
    }
}
