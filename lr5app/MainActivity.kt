package com.example.lr5app

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity(),
    ProductListFragment.OnFragmentInteractionListener,
    CategoryListFragment.OnFragmentInteractionListener,
    FavoriteProductsFragment.OnFragmentInteractionListener,
    PurchasedProductsFragment.OnFragmentInteractionListener {

    private lateinit var tvFragmentName: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MainActivity", "onCreate() called")
        Toast.makeText(this, "onCreate() called", Toast.LENGTH_SHORT).show()

        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        tvFragmentName = findViewById(R.id.tvFragmentName)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnProductList: Button = findViewById(R.id.btnProductList)
        val btnCategoryList: Button = findViewById(R.id.btnCategoryList)
        val btnFavoriteProducts: Button = findViewById(R.id.btnFavoriteProducts)
        val btnPurchasedProducts: Button = findViewById(R.id.btnPurchasedProducts)

        btnProductList.setOnClickListener {
            loadFragment(ProductListFragment())
        }

        btnCategoryList.setOnClickListener {
            loadFragment(CategoryListFragment())
        }

        btnFavoriteProducts.setOnClickListener {
            loadFragment(FavoriteProductsFragment())
        }

        btnPurchasedProducts.setOnClickListener {
            loadFragment(PurchasedProductsFragment())
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }

    override fun onFragmentInteraction(fragmentName: String) {
        tvFragmentName.text = fragmentName
    }

    override fun onStart() {
        super.onStart()
        Log.d("MainActivity", "onStart() called")
        Toast.makeText(this, "onStart() called", Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        Log.d("MainActivity", "onResume() called")
        Toast.makeText(this, "onResume() called", Toast.LENGTH_SHORT).show()
    }

    override fun onPause() {
        super.onPause()
        Log.d("MainActivity", "onPause() called")
        Toast.makeText(this, "onPause() called", Toast.LENGTH_SHORT).show()
    }

    override fun onStop() {
        super.onStop()
        Log.d("MainActivity", "onStop() called")
        Toast.makeText(this, "onStop() called", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MainActivity", "onDestroy() called")
        Toast.makeText(this, "onDestroy() called", Toast.LENGTH_SHORT).show()
    }
}