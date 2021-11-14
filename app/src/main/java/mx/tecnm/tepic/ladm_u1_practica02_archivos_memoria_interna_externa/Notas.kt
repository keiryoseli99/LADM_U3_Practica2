package mx.tecnm.tepic.ladm_u1_practica02_archivos_memoria_interna_externa

import android.content.ContentValues
import android.content.Context
import android.icu.text.SimpleDateFormat
import android.os.Build
import androidx.annotation.RequiresApi
import com.google.firestore.v1.Cursor
import java.util.*
import kotlin.collections.ArrayList

class Notas(p: Context) {
    var titulo = ""
    var contenido = ""
    var horafecha = ""
    var idnote = ""
    var pnt = p
    var ultimo = ""

    @RequiresApi(Build.VERSION_CODES.N)
    fun insertarNote() : Boolean {
        val tablaNotes = DataBase(pnt,"Notes",null,1).writableDatabase
        val datos = ContentValues()

        datos.put("titulo",titulo)
        datos.put("contenido",contenido)
        datos.put("horafecha",horafecha)

        val resultado = tablaNotes.insert("NOTE",null,datos)
        if (resultado == -1L){
            return false
        }
        return true
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun getDateTime(): String? {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val date = Date()
        return dateFormat.format(date)
    }

    fun consultarNote() : ArrayList<String> {
        val tablaNotes = DataBase(pnt,"Notes",null,1).writableDatabase
        val resultado = ArrayList<String>()
        val cursor = tablaNotes.query("NOTE", arrayOf("*"),null,null,null,null,null)
        if (cursor.moveToLast()) {
            do {
                val datos = cursor.getString(3)+"\n"+cursor.getString(1)+"\n"+cursor.getString(2)
                resultado.add(datos)
            } while (cursor.moveToPrevious())
        }
        return resultado
    }

    fun eliminarNote(idEliminar:Int) : Boolean {
        val tablaNotes = DataBase(pnt,"Notes",null,1).writableDatabase
        val resultado = tablaNotes.delete("NOTE", "IDNOTE=?", arrayOf(idEliminar.toString()))
        if (resultado == 0) return false
        return true
    }

    fun actualizarNote(idActualizar: String): Boolean {
        val tablaNotes = DataBase(pnt,"Notes",null,1).writableDatabase
        val datos = ContentValues()

        datos.put("titulo",titulo)
        datos.put("contenido",contenido)

        val resultado = tablaNotes.update("NOTE",datos,"IDNOTE=?", arrayOf(idActualizar))
        if (resultado==0) return false
        return true
    }

    fun obtenerIDsNota() : ArrayList<Int> {
        val tablaNotes = DataBase(pnt,"Notes",null,1).readableDatabase
        val resultado = ArrayList<Int>()
        val cursor = tablaNotes.query("NOTE", arrayOf("*"),null,null,null,null,null)
        if (cursor.moveToFirst()) {
            do {
                resultado.add(cursor.getInt(0))
            } while (cursor.moveToNext())
        }
        return resultado
    }

    fun eliminarNoteHF(hf: String) : Boolean {
        val tablaNotes = DataBase(pnt,"Notes",null,1).writableDatabase
        val resultado = tablaNotes.delete("NOTE", "HORAFECHA=?", arrayOf(hf))
        if (resultado == 0) return false
        return true
    }

    fun consultarNoteLast() : ArrayList<String> {
        val tablaNotes = DataBase(pnt,"Notes",null,1).writableDatabase
        val resultado = ArrayList<String>()
        //val cursor = tablaNotes.query("NOTE", arrayOf("*"),null,null,null,"3","IDNOTE DESC")
        val cursor = tablaNotes.rawQuery("SELECT * FROM (SELECT * FROM NOTE ORDER BY IDNOTE DESC LIMIT 3) ORDER BY IDNOTE ASC", null)
        if (cursor.moveToLast()) {
            do {
                val datos = cursor.getString(3)+"\n"+cursor.getString(1)+"\n"+cursor.getString(2)
                resultado.add(datos)
            } while (cursor.moveToPrevious())
        }
        return resultado
    }
}