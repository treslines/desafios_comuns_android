package com.progdeelite.dca.content_provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.SharedPreferences
import android.database.Cursor
import android.database.MatrixCursor
import android.net.Uri
import android.provider.BaseColumns
import com.progdeelite.dca.BuildConfig

// IMAGINE QUE EXISTE UM APP INSTALADO QUE E PROVÊ INFOS ATRAVES DA API (CONTENT PROVIDER)
// QUE PERMITE VOCE FAZER REQUISIçÕES AS DADOS ATUAIS CADASTRADOS NELA.
// https://guides.codepath.com/android/creating-content-providers

// 1) CRIAR CONTENT PROVIDER
// 2) EXPECIFICAR PERMISSÕES E DEFINICÃO NO MANIFEST
// 3) EXPECIFICAR ACESSO NO MANIFEST DO APP QUE USA
// 4) ACESSAR O CONTEUDO PROVIDO (EXEMPLO DE CHAMADA)
class AppInfoProvider : ContentProvider() {

    private var settings: SharedPreferences? = null

    companion object {
        private const val PROVIDER_VERSION = 1.0
        private const val COLUMN_VERSION = "version"
        private const val COLUMN_ACCOUNT_STATUS = "account_status"
        private const val AUTHORITY = "${BuildConfig.APPLICATION_ID}.provider"
        private const val PROVIDER_TYPE = "vnd.android.cursor.item/vnd.$AUTHORITY/$COLUMN_ACCOUNT_STATUS"
        // APENAS PARA FINS DEMONSTRATIVOS
        private const val CONTENT_URI = "content://$AUTHORITY/$COLUMN_ACCOUNT_STATUS"
    }

    override fun onCreate(): Boolean = true

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor {
        val columns = arrayOf(BaseColumns._ID, COLUMN_VERSION, COLUMN_ACCOUNT_STATUS)
        val matrixCursor = MatrixCursor(columns)
        matrixCursor.addRow(arrayOf<Any>(1, PROVIDER_VERSION, "active")) // EX: AQUI VOCE PEGARIA ALGO DE SETTINGS
        return matrixCursor
    }

    override fun getType(uri: Uri): String = PROVIDER_TYPE

    override fun insert(uri: Uri, values: ContentValues?): Uri? = null

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int = -1

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int = -1
}

// +----------------------------------------------------------------------+
// | PERMISÕES NO MANIFEST DO APP QUE CHAMA/USA O PROVEDOR DE CONTEUDO:   |
// +----------------------------------------------------------------------+
// <uses-permission android:name="{package_id_app_que_fornece_provider}.permission.ACCOUNT_STATUS" />
//
//
// <queries>
//    <provider
//        android:authorities="{package_id_app_que_fornece_provider}.provider"
//        android:exported="false" />
// </queries>


// +---------------------------------------------------------------------+
// | COMO USAR O PROVIDER NO APP QUE CHAMA O PROVEDOR DE CONTEUDO:       |
// +---------------------------------------------------------------------+
//  val PROVIDER_CONTENT_URI = "content://{package_id_app_que_fornece_provider}.provider/account_status"
//  val PROVIDER_VERSION = "version"
//  val PROVIDER_COLUMN = "account_status"
//
//  val contentUri: Uri = Uri.parse(PROVIDER_CONTENT_URI)
//  val projection: Array<String> = arrayOf(BaseColumns._ID, PROVIDER_VERSION, PROVIDER_COLUMN)
//
//  val cursor = requireActivity().contentResolver.query(contentUri, projection, null, null, null)
//
//  # SE TIVER APENAS UMA ENTRADA USE ASSIM:
//  if (cursor != null) { //null check in case digipass can't be reached
//    cursor.moveToFirst()
//    val status = cursor.getString(cursor.getColumnIndexOrThrow(PROVIDER_COLUMN)
//    Toast.makeText(requireContext(), "Account status:  $status", Toast.LENGTH_LONG).show()
//
//      # SE TIVER UMA LISTA, OUTRA OPçÃO SERIA ASSIM:
//      val category: String
//      for (int i = 0; i < cursor.getCount(); i++){
//          category = cursor.getString(cursor.getColumnIndexOrThrow(PROVIDER_COLUMN))
//          status.add(category)
//          cursor.moveToNext()
//      }
//
//    cursor.close()
//  }