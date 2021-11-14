package mx.tecnm.tepic.ladm_u1_practica02_archivos_memoria_interna_externa

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBase(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
    ) : SQLiteOpenHelper(context,name,factory,version) {

    override fun onCreate(p: SQLiteDatabase) {
        p.execSQL("CREATE TABLE NOTE( IDNOTE INTEGER PRIMARY KEY AUTOINCREMENT, TITULO VARCHAR(200), CONTENIDO VARCHAR(500), HORAFECHA DATETIME)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }
}