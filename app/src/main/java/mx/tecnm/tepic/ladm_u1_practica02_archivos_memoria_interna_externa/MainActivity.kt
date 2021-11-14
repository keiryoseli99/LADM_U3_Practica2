package mx.tecnm.tepic.ladm_u1_practica02_archivos_memoria_interna_externa

import android.content.DialogInterface
import android.content.Intent
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.LayoutInflaterCompat
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main3.*
import mx.tecnm.tepic.ladm_u1_practica02_archivos_memoria_interna_externa.databinding.ActivityMainBinding
import java.io.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ultimaNota()

        crear.setOnClickListener {
            val intentoAgregar = Intent(this, MainActivity3::class.java)
            startActivity(intentoAgregar)
        }
        abrir.setOnClickListener {
            val intentoAbrir = Intent(this, MainActivity2::class.java)
            startActivity(intentoAbrir)
        }
        borrar.setOnClickListener {
            val intentoBorrar = Intent(this, MainActivity4::class.java)
            startActivity(intentoBorrar)
        }
    }

    private fun ultimaNota() {
        val arregloNote = Notas(this).consultarNoteLast()
        Lista3.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arregloNote)
    }

}