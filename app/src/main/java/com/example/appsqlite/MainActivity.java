package com.example.appsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Instanciar la clase clsDb
    clsDb ohDb = new clsDb(this, "dblibrary", null, 1);

    EditText fullname, email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Instanciar y referenciar los IDs del archivo xml (activity.main.xml)
        fullname = (EditText)findViewById(R.id.etfullname);
        email = (EditText)findViewById(R.id.etemail);
        password = (EditText)findViewById(R.id.etpassword);
        ImageButton btnsave = findViewById(R.id.btnsave);
        ImageButton btnsearch = findViewById(R.id.btnsearch);
        ImageButton btnupdate = findViewById(R.id.btnupdate);
        ImageButton btndelete = findViewById(R.id.btndelete);

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUser(fullname.getText().toString(), email.getText().toString(), password.getText().toString());
            }
        });

    }

    private void saveUser(String sfullname, String semail, String spassword) {
        // Instanciar la clase clsDb
        //clsDb ohDb = new clsDb(this, "dblibrary", null, 1);

        // Instanciar la base de datos en modo de escritura (INSERT, UPDATE, DELETE)
        SQLiteDatabase db = ohDb.getWritableDatabase();

        // Try sirve para el manejo de excepciones
        try {
            // Crear un objeto de ContentValues para que contenga los mismos campos de la tabla user
            ContentValues cvUser = new ContentValues();
            cvUser.put("fullname", sfullname);
            cvUser.put("email", semail);
            cvUser.put("password", spassword);

            // Guardar en la tabla user lo que tiene cvUser
            db.insert("user", null, cvUser);
            db.close();
            Toast.makeText(getApplicationContext(), "Usuario agregado con éxito...", Toast.LENGTH_SHORT).show();

            // Limpiar las cajas de texto
            fullname.setText("");
            email.setText("");
            password.setText("");
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}