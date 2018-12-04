package pim.alves.murilo.projetointegradomultidisciplinar.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import pim.alves.murilo.projetointegradomultidisciplinar.R;
import pim.alves.murilo.projetointegradomultidisciplinar.controller.ContatoController;
import pim.alves.murilo.projetointegradomultidisciplinar.controller.EstoqueController;
import pim.alves.murilo.projetointegradomultidisciplinar.controller.VendaController;

public class MainActivity extends AppCompatActivity {

    Button btnCriarContato;
    Button btnCriarVenda;
    Button btnCriarEstoque;
    Button buttonListEstoque;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCriarContato = (Button) findViewById(R.id.btnCriarContato);
        btnCriarContato.setOnClickListener(new CreateContatoOnClickListener());

        btnCriarVenda = (Button) findViewById(R.id.btnCriarVenda);
        btnCriarVenda.setOnClickListener(new CreateVendaOnClickListener());

        btnCriarEstoque = (Button) findViewById(R.id.btnCriarEstoque);
        btnCriarEstoque.setOnClickListener(new CreateEstoqueOnClickListener());

        totalContatos();
        totalEstoque();
        totalVenda();
    }

    public void chamarActivityListaContatos(View view){
        Intent intent = new Intent(getApplicationContext(), MainActivityListarContatos.class);
        startActivity(intent);
    }

    public void chamarActivityListaEstoque(View view){
        Intent intent = new Intent(getApplicationContext(), MainActivityListarEstoque.class);
        startActivity(intent);
    }

    public void chamarActivityListaVendas(View view){
        Intent intent = new Intent(getApplicationContext(), MainActivityListarVendas.class);
        startActivity(intent);
    }

    public void totalContatos() {
        String message = "";

        int contador = new ContatoController(this).totalContatos();

        TextView textViewTotalContatos = (TextView)  findViewById(R.id.textViewTotalContatos);
        //message = contador + "";
        message = "";
        textViewTotalContatos.setText(message);
    }

    public void totalEstoque() {
        String message = "";

        int contador = new EstoqueController(this).totalEstoque();

        TextView textViewEstoque = (TextView)  findViewById(R.id.textViewEstoque);
        //message = contador + "";
        message = "";
        textViewEstoque.setText(message);

    }

    public void totalVenda() {
        String message = "";

        int contador = new VendaController(this).totalVendas();

        TextView textViewTotalVendas = (TextView)  findViewById(R.id.textViewTotalVendas);
        //message = contador + "";
        message = "";
        textViewTotalVendas.setText(message);
    }
}
