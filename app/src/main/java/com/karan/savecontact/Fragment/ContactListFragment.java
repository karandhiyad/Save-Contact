package com.karan.savecontact.Fragment;

import static com.karan.savecontact.MainActivity.binding;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.karan.savecontact.Adapter.CategoryAdapter;
import com.karan.savecontact.Adapter.ContactAdapter;
import com.karan.savecontact.Database.TBLCategory;
import com.karan.savecontact.Database.TBLContact;
import com.karan.savecontact.R;
import com.karan.savecontact.ViewModel.ContactViewModel;
import com.karan.savecontact.databinding.FragmentContactListBinding;

import java.util.ArrayList;
import java.util.List;


public class ContactListFragment extends Fragment {

    public ContactListFragment() {
        // Required empty public constructor
    }

    private FragmentContactListBinding bindings;
    private ContactViewModel contactViewModel;
    private ArrayList<String> CategoryList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        bindings = FragmentContactListBinding.inflate(getLayoutInflater());

        binding.appBarHome.txToolbarTitle.setText("Contact List");
        binding.appBarHome.igFilter.setVisibility(View.VISIBLE);
        binding.appBarHome.igSearch.setVisibility(View.VISIBLE);

        contactViewModel = new ViewModelProvider(this).get(ContactViewModel.class);

        ContactAdapter contactAdapter = new ContactAdapter(getContext());
        contactAdapter.SetContact((ArrayList<TBLContact>) contactViewModel.getTBLContact());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
        bindings.rvContactList.setAdapter(contactAdapter);
        bindings.rvContactList.setLayoutManager(gridLayoutManager);

        contactViewModel.getLiveTBLContact().observe(getViewLifecycleOwner(), new Observer<List<TBLContact>>() {
            @Override
            public void onChanged(List<TBLContact> tblContacts) {
                contactAdapter.SetContact((ArrayList<TBLContact>) tblContacts);
            }
        });

        ArrayList<TBLCategory> tblCategories = (ArrayList<TBLCategory>) contactViewModel.getTBLCategory();

        for (TBLCategory tblCategory : tblCategories) {
            CategoryList.add(tblCategory.getCategory());
        }

        //Spinner Adapter
        ArrayAdapter<String> wifiAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, CategoryList);

        // Drop down layout style - list view with radio button
        wifiAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        binding.appBarHome.spCategory.setAdapter(wifiAdapter);

        binding.appBarHome.igFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.appBarHome.spCategoryCard.getVisibility() == View.GONE) {
                    binding.appBarHome.spCategoryCard.setVisibility(View.VISIBLE);
                    binding.appBarHome.txToolbarTitle.setVisibility(View.GONE);

                    contactAdapter.SetContact((ArrayList<TBLContact>) contactViewModel.getTBLContactCategory(binding.appBarHome.spCategory.getSelectedItem().toString()));

                } else {
                    binding.appBarHome.spCategoryCard.setVisibility(View.GONE);
                    binding.appBarHome.txToolbarTitle.setVisibility(View.VISIBLE);

                    contactAdapter.SetContact((ArrayList<TBLContact>) contactViewModel.getTBLContact());
                }
            }
        });


        binding.appBarHome.spCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                contactAdapter.SetContact((ArrayList<TBLContact>) contactViewModel.getTBLContactCategory(binding.appBarHome.spCategory.getSelectedItem().toString()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        return bindings.getRoot();
    }
}