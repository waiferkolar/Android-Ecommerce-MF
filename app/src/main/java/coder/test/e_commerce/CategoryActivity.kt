package coder.test.e_commerce

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import coder.test.e_commerce.adapters.CategoryAdapter
import coder.test.e_commerce.libby.H
import coder.test.e_commerce.models.Category
import coder.test.e_commerce.services.ServiceBuilder
import coder.test.e_commerce.services.WebService
import kotlinx.android.synthetic.main.activity_category.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        supportActionBar?.title = "Category"
        catRecycler.layoutManager = GridLayoutManager(this,2)
        loadAllCat()
    }

    private fun loadAllCat() {
        val service : WebService = ServiceBuilder.createService(WebService::class.java)
        val postRequest : Call<List<Category>> = service.getAllCat("Bearer ${H.token?.token}")

        postRequest.enqueue(object : Callback<List<Category>> {
            override fun onFailure(call: Call<List<Category>>, t: Throwable) {
                Log.d("msg",t.message!!)
            }

            override fun onResponse(
                call: Call<List<Category>>,
                response: Response<List<Category>>
            ) {
                val cats = response.body()
                val adapters = CategoryAdapter(this@CategoryActivity,cats!!)
                catRecycler.adapter = adapters
            }

        })
    }
}
