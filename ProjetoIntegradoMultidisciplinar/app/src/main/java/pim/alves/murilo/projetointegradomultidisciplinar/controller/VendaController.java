package pim.alves.murilo.projetointegradomultidisciplinar.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import pim.alves.murilo.projetointegradomultidisciplinar.adapter.DataBaseAdapterVenda;
import pim.alves.murilo.projetointegradomultidisciplinar.model.Venda;

public class VendaController extends DataBaseAdapterVenda {

    public VendaController(Context context){
        super(context, null, null, 0);
    }

    public boolean createVenda(Venda venda){
        ContentValues values = new ContentValues();
        values.put("produto", venda.getNomeProduto());
        values.put("valorVenda", venda.getValorVenda());
        SQLiteDatabase db = this.getWritableDatabase();

        boolean isCreate = db.insert("vendas", null, values) > 0;
        db.close();

        return isCreate;
    }

    public int totalVendas(){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM vendas";

        int contador = db.rawQuery(sql, null).getCount();

        return contador;
    }

    public List<Venda> listTotalVendas(){
        List<Venda>  vendas = new ArrayList<>();
        String sql = "SELECT valorVenda FROM vendas ORDER by id DESC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if(cursor.moveToFirst()){
            do{
                int id = Integer.parseInt(cursor.getString
                        (cursor.getColumnIndex("id")));
                String quantidade = cursor.getString(cursor.getColumnIndex("valorVenda"));

                Venda venda = new Venda();
                venda.setId(id);
                venda.setQuantidade(quantidade);
                vendas.add(venda);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return vendas;
    }

    public List<Venda> listVendas(){
        List<Venda>  vendas = new ArrayList<>();
        String sql = "SELECT * FROM vendas ORDER by id DESC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if(cursor.moveToFirst()){
            do{
                int id = Integer.parseInt(cursor.getString
                        (cursor.getColumnIndex("id")));
                String nome = cursor.getString(cursor.getColumnIndex("produto"));
                String quantidade = cursor.getString(cursor.getColumnIndex("valorVenda"));

                Venda venda = new Venda();
                venda.setId(id);
                venda.setNomeProduto(nome);
                venda.setQuantidade(quantidade);
                vendas.add(venda);

            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return vendas;
    }

    public Venda findById(int vendaId){
        Venda venda = null;
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM vendas where id = " + vendaId;
        Cursor cursor = db.rawQuery(sql, null);

        if(cursor.moveToFirst()){
            String nome = cursor.getString(cursor.getColumnIndex("produto"));
            String valor = cursor.getString(cursor.getColumnIndex("valorVenda"));

            venda = new Venda();
            venda.setId(vendaId);
            venda.setNomeProduto(nome);
            venda.setQuantidade(valor);
        }
        return venda;
    }

    public boolean updateVenda(Venda venda){
        ContentValues values = new ContentValues();

        values.put("produto", venda.getNomeProduto());
        values.put("valorVenda", venda.getQuantidade());

        String  where = "id = ?";
        String[] whereArgs = { Integer.toString(venda.getId()) };

        SQLiteDatabase db = this.getWritableDatabase();
        boolean atualizar = db.update("vendas", values, where, whereArgs) > 0;
        db.close();

        return atualizar;
    }

    public boolean deleteVenda (int vendaId){
        boolean excluirVenda = false;

        SQLiteDatabase db = this.getWritableDatabase();

        excluirVenda = db.delete("vendas", "id = '" + vendaId + "'", null) > 0;
        db.close();

        return excluirVenda;
    }
}
