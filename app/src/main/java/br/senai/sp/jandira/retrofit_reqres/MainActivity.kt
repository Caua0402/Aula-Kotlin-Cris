package br.senai.sp.jandira.retrofit_reqres

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import com.google.gson.JsonObject
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        apiService = RetrofitHelper.getInstance().create(ApiService::class.java)

        // AÇÃO DO BoTÃO GET:
        findViewById<Button>(R.id.btnGET).setOnClickListener {
            getUserByID()
        }

        // AÇÃO DO BOTÃO POST:
        findViewById<Button>(R.id.btnPOST).setOnClickListener {
            createUser()
        }

        // AÇÃO DO BOTÃO PUT:
        findViewById<Button>(R.id.btnPUT).setOnClickListener {
            updateUser()
        }

        // AÇÃO DO BOTÃO DELTE:
        findViewById<Button>(R.id.btnDELETE).setOnClickListener {
            deleteUser()
        }

    }

    //Recupera dados de usuários
    private fun getUserByID() {

        lifecycleScope.launch {
            //CHAMADA PARA O END POINT
            val result = apiService.getUserByID("7")

            if (result.isSuccessful) {
                Log.e("GETTING-DATA", "${result.body()?.data}")
            } else {
                Log.e("GETTING-DATA", "${result.message()}")
            }
        }
    }

    //Insere dados de usuários
    private fun createUser() {

        lifecycleScope.launch {
            val body = JsonObject().apply {
                addProperty("name", "Cauã Felipe")
                addProperty("job", "Desenvolvedor Front-End")
            }

            val result = apiService.createUser(body)

            if (result.isSuccessful) {
                Log.e("CREATE-DATA", "${result.body()}")
            } else {
                Log.e("CREATE-DATA", "${result.message()}")
            }
        }
    }

    //Insere dados de usuários
    private fun updateUser() {
        lifecycleScope.launch {

            val body = JsonObject().apply {
                addProperty("name", "Cauã Felipe")
                addProperty("job", "Desenvolvedor Front-End")
            }

            val result = apiService.updateUser("2", body)


            if (result.isSuccessful) {
                Log.e("UPDATE-DATA", "${result.body()}")
            } else {
                Log.e("UPDATE-DATA", "${result.message()}")
            }
        }
    }

    //Deleta dados de usuários
    private fun deleteUser() {
        lifecycleScope.launch {

            val result = apiService.deleteUser("2")


            if (result.isSuccessful) {
                Log.e("DELETE-DATA", "${result.body()}")
            } else {
                Log.e("DELETE-DATA", "${result.message()}")
            }
        }
    }


}