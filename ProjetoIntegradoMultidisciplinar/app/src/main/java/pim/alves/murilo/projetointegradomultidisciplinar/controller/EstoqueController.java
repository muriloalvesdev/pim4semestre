package pim.alves.murilo.projetointegradomultidisciplinar.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import pim.alves.murilo.projetointegradomultidisciplinar.adapter.DataBaseAdapterEstoque;
import pim.alves.murilo.projetointegradomultidisciplinar.model.Estoque;

public class EstoqueController extends DataBaseAdapterEstoque {
    public EstoqueController(Context context){
        super(context, null, null, 0);
    }

    public boolean createEstoque(Estoque estoque){
        ContentValues values = new ContentValues();
        values.put("produto", estoque.getNomeProduto());
        values.put("quantidadeProdutos", estoque.getQuantidadeProdutos());
        SQLiteDatabase db = this.getWritableDatabase();

        boolean isCreate = db.insert("estoque", null, values) > 0;
        db.close();

        return isCreate;
    }

    public int totalEstoque(){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM estoque";

        int contador = db.rawQuery(sql, null).getCount();

        return contador;
    }

    public List<Estoque> listEstoque(){
        List<Estoque>  estoques = new ArrayList<>();
        String sql = "SELECT * FROM estoque ORDER by produto";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if(cursor.moveToFirst()){
            do{
                int id = Integer.parseInt(cursor.getString
                        (cursor.getColumnIndex("id")));
                String nome = cursor.getString(cursor.getColumnIndex("produto"));
                String quantidade = cursor.getString(cursor.getColumnIndex("quantidadeProdutos"));

                Estoque estoque = new Estoque();
                estoque.setId(id);
                estoque.setNomeProduto(nome);
                estoque.setQuantidadeProdutos(quantidade);
                estoques.add(estoque);

            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return estoques;
    }

    public Estoque findById(int estoqueId){
        Estoque estoque = null;
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM estoque where id = " + estoqueId;
        Cursor cursor = db.rawQuery(sql, null);

        if(cursor.moveToFirst()){
            String nome = cursor.getString(cursor.getColumnIndex("produto"));
            String quantidade = cursor.getString(cursor.getColumnIndex("quantidadeProdutos"));

            estoque = new Estoque();
            estoque.setId(estoqueId);
            estoque.setNomeProduto(nome);
            estoque.setQuantidadeProdutos(quantidade);
        }
        return estoque;
    }

    public boolean updateEstoque(Estoque estoque){
        ContentValues values = new ContentValues();

        values.put("produto", estoque.getNomeProduto());
        values.put("quantidadeProdutos", estoque.getQuantidadeProdutos());

        String  where = "id = ?";
        String[] whereArgs = { Integer.toString(estoque.getId()) };

        SQLiteDatabase db = this.getWritableDatabase();
        boolean atualizar = db.update("estoque", values, where, whereArgs) > 0;
        db.close();

        return atualizar;
    }

    public boolean deleteEstoque (int estoqueId){

        boolean excluirEstoque = false;

        SQLiteDatabase db = this.getWritableDatabase();

        excluirEstoque = db.delete("estoque", "id = '" + estoqueId + "'", null) > 0;
        db.close();

        return excluirEstoque;

    }
}
