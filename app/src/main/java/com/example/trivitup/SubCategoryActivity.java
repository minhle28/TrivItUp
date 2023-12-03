package com.example.trivitup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.trivitup.databinding.ActivityMainBinding;
import com.example.trivitup.databinding.ActivitySubCategoryBinding;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.*;
import java.util.HashMap;
import java.util.Map;

public class SubCategoryActivity extends AppCompatActivity {
    // Define the subcategories
    ActivitySubCategoryBinding binding;
    FirebaseFirestore database;
    Map<String, String[]> subCategories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);

        binding = ActivitySubCategoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        database = FirebaseFirestore.getInstance();
        ArrayList<CategoryModel> categories = new ArrayList<>();

        CategoryAdapter adapter = new CategoryAdapter(this,categories);
        database.collection("categories")
                        .addSnapshotListener(new EventListener<QuerySnapshot>() {
                            @Override
                            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                                categories.clear();
                                for (DocumentSnapshot snapshot : value.getDocuments()){
                                    CategoryModel model = snapshot.toObject(CategoryModel.class);
                                    model.setCategoryId(snapshot.getId());
                                    categories.add(model);
                                }
                                adapter.notifyDataSetChanged();
                            }
                        });
        binding.categoryList.setLayoutManager(new GridLayoutManager(this,2));
        binding.categoryList.setAdapter(adapter);


    }





}