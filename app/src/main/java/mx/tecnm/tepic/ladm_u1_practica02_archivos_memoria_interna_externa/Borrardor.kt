/**
package mx.tecnm.tepic.ladm_u1_practica02_archivos_memoria_interna_externa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main3.*
import kotlinx.android.synthetic.main.activity_main4.*

class MainActivity4 : AppCompatActivity() {
    var dbRemota = FirebaseFirestore.getInstance()
    var datalista = ArrayList<String>()
    var listaID = ArrayList<String>()
    var noteHF = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        //DB Remota
        dbRemota.collection("notes")
            .addSnapshotListener { querySnapshot, error ->
                datalista.clear()
                listaID.clear()
                for (document in querySnapshot!!){
                    var cadena = "${document.getString("horafecha")}\n${document.getString("titulo")}\n${document.get(("contenido"))}"
                    datalista.add(cadena)
                    listaID.add(document.id.toString())
                    noteHF = "${document.getString("horafecha")}"
                }
                EditarLista.adapter = ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, datalista)
                EditarLista.setOnItemClickListener { adapterView, view, posicion, l ->
                    dialogoEliminarActualiza(posicion)
                }
            }

        EditarRegresar.setOnClickListener {
            finish()
        }

    }

    private fun dialogoEliminarActualiza(posicion: Int) {
        var idElegido = listaID.get(posicion)
        AlertDialog.Builder(this)
            .setTitle("ATENCION")
            .setMessage("¿QUE DESEA HACER CON ESTA NOTA")
            .setPositiveButton("ELIMINAR"){d,i -> eliminar(idElegido)}
            .setNeutralButton("ACTUALIZAR"){d,i->}
            .setNegativeButton("CANCELAR"){d,i-> d.cancel()}
            .show()
    }

    private fun eliminar(idElegido: String) {
        //SQLite
        Notas(this).eliminarNoteHF(noteHF)

        //Firestore
        dbRemota.collection("notes")
            .document(idElegido)
            .delete()
            .addOnSuccessListener { alerta("SE ELIMINO CON EXITO!!!") }
            .addOnFailureListener { mensaje("ERROR: ${it.message!!}") }
    }

    private fun alerta(s: String) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show()
    }

    private fun mensaje(s: String) {
        AlertDialog.Builder(this)
            .setTitle("ATENCION")
            .setMessage(s)
            .setPositiveButton("OK"){d,i->}
            .show()
    }
}**/