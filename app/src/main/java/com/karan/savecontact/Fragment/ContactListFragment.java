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
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),1);
        bindings.rvContactList.setAdapter(contactAdapter);
        bindings.rvContactList.setLayoutManager(gridLayoutManager);

        contactViewModel.getLiveTBLContact().observe(getViewLifecycleOwner(), new Observer<List<TBLContact>>() {
            @Override
            public void onChanged(List<TBLContact> tblContacts) {
                contactAdapter.SetContact((ArrayList<TBLContact>) tblContacts);
            }
        });


        return bindings.getRoot();
    }
}