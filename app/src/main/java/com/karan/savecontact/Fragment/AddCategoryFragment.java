package com.karan.savecontact.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import com.karan.savecontact.Adapter.CategoryAdapter;
import com.karan.savecontact.Database.DatabaseHelper;
import com.karan.savecontact.Database.TBLCategory;
import com.karan.savecontact.R;
import com.karan.savecontact.ViewModel.ContactViewModel;
import com.karan.savecontact.databinding.FragmentAddCategoryBinding;

import java.util.ArrayList;
import java.util.List;

public class AddCategoryFragment extends Fragment {

    public AddCategoryFragment() {
        // Required empty public constructor
    }

    private FragmentAddCategoryBinding binding;
    private ContactViewModel contactViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAddCategoryBinding.inflate(getLayoutInflater());

        contactViewModel = new ViewModelProvider(this).get(ContactViewModel.class);

        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.etAddCategory.getText().toString().isEmpty()){
                    binding.etAddCategory.setError("Please Enter Category Name.");
                }else if (!(contactViewModel.getTBLCategoryByCategory(binding.etAddCategory.getText().toString().trim()).isEmpty())){
                    binding.etAddCategory.setError("This Category is already save.");
                }else {
                    contactViewModel.addTBLCategory(new TBLCategory(binding.etAddCategory.getText().toString().trim()));
                    binding.etAddCategory.setText("");
                }
            }
        });

        CategoryAdapter categoryAdapter = new CategoryAdapter(getContext());
        categoryAdapter.SetCategories((ArrayList<TBLCategory>) contactViewModel.getTBLCategory());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),1);
        binding.rvCategoryList.setAdapter(categoryAdapter);
        binding.rvCategoryList.setLayoutManager(gridLayoutManager);

        contactViewModel.getLiveTBLCategory().observe(getViewLifecycleOwner(), new Observer<List<TBLCategory>>() {
            @Override
            public void onChanged(List<TBLCategory> tblCategories) {
                categoryAdapter.SetCategories((ArrayList<TBLCategory>) tblCategories);
            }
        });



        return binding.getRoot();
    }
}