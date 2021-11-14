package mx.tecnm.tepic.ladm_u1_practica02_archivos_memoria_interna_externa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.activity_main3.*

class MainActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        mostrarNotasSQlite()

        AbrirRegresar.setOnClickListener {
            finish()
        }
    }

    private fun mostrarNotasSQlite() {
        val arregloNote = Notas(this).consultarNote()
        AbrirLista.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arregloNote)
    }
}