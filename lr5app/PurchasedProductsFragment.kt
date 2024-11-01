package com.example.lr5app

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PurchasedProductsFragment : Fragment() {
    private var listener: OnFragmentInteractionListener? = null

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(fragmentName: String)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_purchased_products, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewPurchasedProducts)

        val purchasedProducts = listOf(
            Product("Samsung TV", R.drawable.ic_tv),
            Product("Gaming Console", R.drawable.ic_console),
            Product("Smart Watch", R.drawable.ic_watch)
        )

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = PurchasedProductAdapter(purchasedProducts)

        listener?.onFragmentInteraction("Purchased Products")

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        }
    }

    data class Product(val name: String, val iconResId: Int)

    inner class PurchasedProductAdapter(private val products: List<Product>) :
        RecyclerView.Adapter<PurchasedProductAdapter.PurchasedProductViewHolder>() {

        inner class PurchasedProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val nameTextView: TextView = itemView.findViewById(R.id.purchasedProductName)
            val iconImageView: ImageView = itemView.findViewById(R.id.purchasedProductIcon)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PurchasedProductViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_purchased_product, parent, false)
            return PurchasedProductViewHolder(view)
        }

        override fun onBindViewHolder(holder: PurchasedProductViewHolder, position: Int) {
            val product = products[position]
            holder.nameTextView.text = product.name
            holder.iconImageView.setImageResource(product.iconResId)
        }

        override fun getItemCount() = products.size
    }
}