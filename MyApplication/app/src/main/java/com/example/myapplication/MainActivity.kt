package com.example.myapplication

import android.content.ContentValues
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnRegistrar.setOnClickListener {
            val admin = SQLiteHelper(this,"administracion", null, 1)
            val bd = admin.writableDatabase
            val registro = ContentValues()
            registro.put("cve", edtClave.getText().toString())
            registro.put("nombre", edtNombre.getText().toString())
            registro.put("edad", edtEdad.getText().toString())
            bd.insert("Usuarios", null, registro)
            bd.close()
            edtClave.setText("")
            edtNombre.setText("")
            edtEdad.setText("")
            Toast.makeText(this, "Se cargaron los datos del usuario", Toast.LENGTH_SHORT).show()
        }

        btnConCod.setOnClickListener {
            val admin = SQLiteHelper(this, "administracion", null, 1)
            val bd = admin.writableDatabase
            val fila = bd.rawQuery("select nombre,edad from usuarios where cve=${edtClave.text.toString()}", null)
            if (fila.moveToFirst()) {
                edtNombre.setText(fila.getString(0))
                edtEdad.setText(fila.getString(1))
            } else
                Toast.makeText(this, "No existe un usuario con dicho código",  Toast.LENGTH_SHORT).show()
            bd.close()
        }
    }
}
