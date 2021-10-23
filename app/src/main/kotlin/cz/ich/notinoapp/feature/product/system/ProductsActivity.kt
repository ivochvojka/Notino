package cz.ich.notinoapp.feature.product.system

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cz.ich.notinoapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.layout_toolbar))
    }
}