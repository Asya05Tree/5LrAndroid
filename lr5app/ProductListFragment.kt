package com.example.lr5app

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ProductListFragment : Fragment() {
    private var listener: OnFragmentInteractionListener? = null

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(fragmentName: String)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_product_list, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewProducts)
        val btnOpenSecondActivity: Button = view.findViewById(R.id.btnOpenSecondActivity)

        val products = listOf(
            Product("Smartphone", R.drawable.ic_smartphone),
            Product("Laptop", R.drawable.ic_laptop),
            Product("Headphones", R.drawable.ic_headphones)
        )

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = ProductAdapter(products)

        btnOpenSecondActivity.setOnClickListener {
            val intent = Intent(activity, SecondActivity::class.java)
            startActivity(intent)
        }

        listener?.onFragmentInteraction("Product List")

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        }
    }

    data class Product(val name: String, val iconResId: Int)

    inner class ProductAdapter(private val products: List<Product>) :
        RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

        inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val nameTextView: TextView = itemView.findViewById(R.id.productName)
            val iconImageView: ImageView = itemView.findViewById(R.id.productIcon)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_product, parent, false)
            return ProductViewHolder(view)
        }

        override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
            val product = products[position]
            holder.nameTextView.text = product.name
            holder.iconImageView.setImageResource(product.iconResId)
        }

        override fun getItemCount() = products.size
    }
}