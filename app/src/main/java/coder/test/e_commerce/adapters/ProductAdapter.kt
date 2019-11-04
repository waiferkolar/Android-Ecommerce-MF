package coder.test.e_commerce.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import androidx.recyclerview.widget.RecyclerView
import coder.test.e_commerce.R
import coder.test.e_commerce.models.Product
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.product_row.view.*
import org.jetbrains.anko.toast

class ProductAdapter(val context: Context, val products: List<Product>) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.product_row, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val product = products[position]

        holder.itemView.tvName.text = product.name
        holder.itemView.tvPrice.text = product.price.toString()
        Picasso.get().load(product.image).into(holder.itemView.imgProduct)
        holder.itemView.btnCart.setOnClickListener {
            context.toast("Product id is ${product.id}")
        }

    }

    class ViewHolder(item: View) : RecyclerView.ViewHolder(item)
}