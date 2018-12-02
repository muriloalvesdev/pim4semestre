package pim.alves.murilo.projetointegradomultidisciplinar.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import pim.alves.murilo.projetointegradomultidisciplinar.R;
import pim.alves.murilo.projetointegradomultidisciplinar.controller.ContatoController;
import pim.alves.murilo.projetointegradomultidisciplinar.model.Contato;

public class MainActivityListarContatos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_listar_contatos);
        atualizarListaContatos();
    }

    public void chamarActivityMain(View view){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public void atualizarListaContatos(){

        LinearLayout linearLayoutRecords = (LinearLayout) findViewById(R.id.objetosContatos);
        linearLayoutRecords.removeAllViews();

        List<Contato> contatos = new ContatoController(this).listContatos();

        if(contatos.size() > 0){
            for(Contato obj: contatos){
                int id = obj.getId();
                String nome = obj.getNome();
                String email = obj.getEmail();

                String textViewContatos = nome + " - " + email;

                TextView textViewContatoItem = new TextView(this);
                textViewContatoItem.setPadding(0, 10, 0, 10);
                textViewContatoItem.setText(textViewContatos.toString());
                textViewContatoItem.setTag(Integer.toString(id));

                linearLayoutRecords.addView(textViewContatoItem);
                textViewContatoItem.setOnLongClickListener(new RetrieveOnLongClickListenerContatos());
            }
        }
    }
}
