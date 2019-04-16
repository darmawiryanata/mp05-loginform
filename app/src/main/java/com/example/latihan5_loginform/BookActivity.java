package com.example.latihan5_loginform;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class BookActivity extends AppCompatActivity {
    String[] daftar;
    //Array di android

    ListView ListView01;
    //List view, menampilkan data dalam bentuk daftar

    Menu menu;
    //Menu pojok kanan atas

    protected Cursor cursor;
    //Cursor = pointer = baris

    sql dbHelper;
    //Library database

    public static BookActivity ma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        ma = this;
        dbHelper = new sql(this);
        RefreshList();
    }

    private void RefreshList() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        //Mengambil database

        cursor = db.rawQuery("SELECT * FROM buku", null);
        //Menjalankan query select

        daftar = new String[cursor.getCount()];
        //Memasukkan jumlah baris menjadi ukuran array daftar

        cursor.moveToFirst();
        //Perintah cursor ke data paling pertama

        for (int cc=0; cc<cursor.getCount(); cc++)
        //Perulangan untuk memasukan setiap cursor ke dalam daftar array
        {
            cursor.moveToPosition(cc);
            daftar[cc] = cursor.getString(1).toString();
        }
        ListView01 = (ListView) findViewById(R.id.ListView01);
        ListView01.setAdapter(new ArrayAdapter<Object>(this, android.R.layout.simple_list_item_1, daftar));
        //Untuk memberikan list

        ListView01.setSelected(true);
        ListView01.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                final String selection = daftar[arg2];
                final CharSequence[] dialogitem = {"Edit", "Delete"};

                AlertDialog.Builder builder = new
                        AlertDialog.Builder(BookActivity.this);
                builder.setTitle("Pilih ?");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        switch(item){
                            case 0 :
                                Intent i = new Intent(getApplicationContext(), Edit.class);
                                i.putExtra("judul_buku", selection);
                                startActivity(i);
                                break;
                            case 1 :
                                SQLiteDatabase db = dbHelper.getWritableDatabase();
                                db.execSQL("DELETE FROM buku WHERE judul_buku = '"+selection+"'");
                                RefreshList();
                                break;
                        }
                    }
                });
                builder.create().show();
            }});

        ((ArrayAdapter)ListView01.getAdapter()).notifyDataSetInvalidated();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu
        this.menu = menu;
        menu.add(0,1,0,"Tambah").setIcon(android.R.drawable.btn_plus);
        menu.add(0,2,0,"Refresh").setIcon(android.R.drawable.ic_menu_rotate);
        menu.add(0,3,0,"Exit").setIcon(android.R.drawable.ic_menu_close_clear_cancel);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                Intent i = new Intent(BookActivity.this, Add.class);
                startActivity(i);
                return true;
            case 2:
                RefreshList();
                return true;
            case 3:
                finish();
                return true;
        }
        return false;
    }
}
