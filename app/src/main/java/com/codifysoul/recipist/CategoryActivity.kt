package com.codifysoul.recipist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.codifysoul.recipist.databinding.ActivityCategoryBinding
import com.codifysoul.recipist.databinding.ActivityHomeBinding

class CategoryActivity :AppCompatActivity() {

    private lateinit var binding: ActivityCategoryBinding
    private lateinit var rvAdapter: CategoryAdapter
    private lateinit var dataList: ArrayList<Recipe>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater);
        setContentView(binding.root)
        title = intent.getStringExtra("TITLE")
        setupRecyclerView()

        binding.goBack.setOnClickListener {
            finish()
        }
    }



    private fun setupRecyclerView() {
        dataList = ArrayList()
        binding.rvCategory.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        var db = Room.databaseBuilder(this@CategoryActivity, AppDatabase::class.java, "db_name")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration().createFromAsset("recipe.db").build()
        var daoObject = db.getDao()
        var recipes = daoObject.getAll()
        for (i in recipes!!.indices){

            if(recipes[i]!!.category.contains(intent.getStringExtra("CATEGORY")!!)){
                dataList.add(recipes[i]!!)
            }
            rvAdapter = CategoryAdapter(dataList,this)
            binding.rvCategory.adapter = rvAdapter;
        }

    }
}