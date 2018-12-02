package pim.alves.murilo.projetointegradomultidisciplinar.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import pim.alves.murilo.projetointegradomultidisciplinar.adapter.DataBaseAdapterContato;
import pim.alves.murilo.projetointegradomultidisciplinar.model.Contato;

public class ContatoController extends DataBaseAdapterContato {

    public ContatoController(Context context){
        super(context, null, null, 0);
    }

    public boolean createContato(Contato contato){
        ContentValues values = new ContentValues();
        values.put("nome", contato.getNome());
        values.put("email", contato.getEmail());
        SQLiteDatabase db = this.getWritableDatabase();

        boolean isCreate = db.insert("contato", null, values) > 0;
        db.close();

        return isCreate;
    }

    public int totalContatos(){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM contato";

        int contador = db.rawQuery(sql, null).getCount();

        return contador;
    }

    public List<Contato> listContatos(){
        List<Contato>  contatos = new ArrayList<>();
        String sql = "SELECT * FROM contato ORDER by id DESC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if(cursor.moveToFirst()){
            do{
                int id = Integer.parseInt(cursor.getString
                        (cursor.getColumnIndex("id")));
                String nome = cursor.getString(cursor.getColumnIndex("nome"));
                String email = cursor.getString(cursor.getColumnIndex("email"));

                Contato contato = new Contato();
                contato.setId(id);
                contato.setNome(nome);
                contato.setEmail(email);

                contatos.add(contato);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return contatos;
    }

    public Contato findById(int contatoId){
        Contato contato = null;
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM contato where id = " + contatoId;
        Cursor cursor = db.rawQuery(sql, null);

        if(cursor.moveToFirst()){
            String nome = cursor.getString(cursor.getColumnIndex("nome"));
            String email = cursor.getString(cursor.getColumnIndex("email"));

            contato = new Contato();
            contato.setId(contatoId);
            contato.setNome(nome);
            contato.setEmail(email);
        }
        return contato;
    }

    public boolean updateContato(Contato contato){
        ContentValues values = new ContentValues();

        values.put("nome", contato.getNome());
        values.put("email", contato.getEmail());

        String  where = "id = ?";
        String[] whereArgs = { Integer.toString(contato.getId()) };

        SQLiteDatabase db = this.getWritableDatabase();
        boolean atualizar = db.update("contato", values, where, whereArgs) > 0;
        db.close();

        return atualizar;
    }

    public boolean deleteContato (int contatoId){

        boolean excluirContato = false;

        SQLiteDatabase db = this.getWritableDatabase();

        excluirContato = db.delete("contato", "id = '" + contatoId + "'", null) > 0;
        db.close();

        return excluirContato;

    }
}
