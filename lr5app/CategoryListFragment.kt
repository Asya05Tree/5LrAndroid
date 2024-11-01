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

class CategoryListFragment : Fragment() {
    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(fragmentName: String)
    }

    private var listener: OnFragmentInteractionListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_category_list, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewCategories)

        val categories = listOf(
            Category("Electronics", R.drawable.ic_electronics),
            Category("Clothing", R.drawable.ic_clothing),
            Category("Books", R.drawable.ic_books)
        )

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = CategoryAdapter(categories)
        listener?.onFragmentInteraction("Category List")

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    data class Category(val name: String, val iconResId: Int)

    inner class CategoryAdapter(private val categories: List<Category>) :
        RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

        inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val nameTextView: TextView = itemView.findViewById(R.id.categoryName)
            val iconImageView: ImageView = itemView.findViewById(R.id.categoryIcon)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_category, parent, false)
            return CategoryViewHolder(view)
        }

        override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
            val category = categories[position]
            holder.nameTextView.text = category.name
            holder.iconImageView.setImageResource(category.iconResId)
        }

        override fun getItemCount() = categories.size
    }
}