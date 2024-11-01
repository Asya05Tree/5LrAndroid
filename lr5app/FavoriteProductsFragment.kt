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

class FavoriteProductsFragment : Fragment() {
    private var listener: OnFragmentInteractionListener? = null

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(fragmentName: String)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favorite_products, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewFavoriteProducts)

        val favoriteProducts = listOf(
            Product("MacBook Pro", R.drawable.ic_laptop),
            Product("AirPods Pro", R.drawable.ic_headphones),
            Product("iPhone 13", R.drawable.ic_smartphone)
        )

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = FavoriteProductAdapter(favoriteProducts)

        listener?.onFragmentInteraction("Favorite Products")

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        }
    }

    data class Product(val name: String, val iconResId: Int)

    inner class FavoriteProductAdapter(private val products: List<Product>) :
        RecyclerView.Adapter<FavoriteProductAdapter.FavoriteProductViewHolder>() {

        inner class FavoriteProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val nameTextView: TextView = itemView.findViewById(R.id.favoriteProductName)
            val iconImageView: ImageView = itemView.findViewById(R.id.favoriteProductIcon)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteProductViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_favorite_product, parent, false)
            return FavoriteProductViewHolder(view)
        }

        override fun onBindViewHolder(holder: FavoriteProductViewHolder, position: Int) {
            val product = products[position]
            holder.nameTextView.text = product.name
            holder.iconImageView.setImageResource(product.iconResId)
        }

        override fun getItemCount() = products.size
    }
}