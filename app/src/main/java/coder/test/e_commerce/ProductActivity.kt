package coder.test.e_commerce

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import coder.test.e_commerce.adapters.CategoryAdapter
import coder.test.e_commerce.adapters.ProductAdapter
import coder.test.e_commerce.libby.H
import coder.test.e_commerce.models.Category
import coder.test.e_commerce.models.Product
import coder.test.e_commerce.services.ServiceBuilder
import coder.test.e_commerce.services.WebService
import kotlinx.android.synthetic.main.activity_category.*
import kotlinx.android.synthetic.main.activity_product.*
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        productRecycler.layoutManager = GridLayoutManager(this,2)
        val cat_id = intent.extras!!["cat_id"] as Int
        val cid = cat_id * 50
        loadAllProduct(cid)
    }

    private fun loadAllProduct(id: Int){
        val service : WebService = ServiceBuilder.createService(WebService::class.java)
        val postRequest : Call<List<Product>> = service.getProductByCatId("Bearer ${H.token?.token}",id)

        postRequest.enqueue(object : Callback<List<Product>> {
            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                Log.d("msg",t.message!!)
            }

            override fun onResponse(
                call: Call<List<Product>>,
                response: Response<List<Product>>
            ) {
                val products = response.body()
                val adapters = ProductAdapter(this@ProductActivity,products!!)
                productRecycler.adapter = adapters
            }

        })
    }
}
