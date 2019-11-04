package coder.test.e_commerce

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import coder.test.e_commerce.libby.H
import coder.test.e_commerce.models.Token
import coder.test.e_commerce.services.ServiceBuilder
import coder.test.e_commerce.services.WebService
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btnLogin.setOnClickListener {
            val email = etEmail.text.toString()
            val pass = etPassword.text.toString()
            val apikey = etApikey.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty() && apikey.isNotEmpty()) {
                loginuser(email, pass, apikey)
            } else {
                toast("Please Fill all required fields!")
            }
        }
    }

    private fun loginuser(email: String, pass: String, apikey: String) {

        val service: WebService = ServiceBuilder.createService(WebService::class.java)
        val postRequest: Call<Token> = service.loginUser(email, pass, apikey)

        postRequest.enqueue(object : Callback<Token> {
            override fun onFailure(call: Call<Token>, t: Throwable) {
                Log.d("msg", t.message!!)
            }

            override fun onResponse(call: Call<Token>, response: Response<Token>) {
                val token = response.body();
                H.token = token
                val intent  = Intent(this@MainActivity,CategoryActivity::class.java)
                startActivity(intent)
            }

        })

    }
}
