package com.karan.savecontact.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.karan.savecontact.Database.TBLCategory;
import com.karan.savecontact.Database.TBLContact;
import com.karan.savecontact.R;
import com.karan.savecontact.ViewModel.ContactViewModel;
import com.karan.savecontact.databinding.FragmentAddContactBinding;

import java.util.ArrayList;


public class AddContactFragment extends Fragment {

    public AddContactFragment() {
        // Required empty public constructor
    }

    private FragmentAddContactBinding binding;
    Uri uri;
    private ArrayList<String> CategoryList = new ArrayList<>();
    private ContactViewModel contactViewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAddContactBinding.inflate(getLayoutInflater());

        contactViewModel = new ViewModelProvider(this).get(ContactViewModel.class);

        ArrayList<TBLCategory> tblCategories = (ArrayList<TBLCategory>) contactViewModel.getTBLCategory();

        for (TBLCategory tblCategory : tblCategories){
            CategoryList.add(tblCategory.getCategory());
        }

        //Spinner Adapter
        ArrayAdapter<String> wifiAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, CategoryList);

        // Drop down layout style - list view with radio button
        wifiAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        binding.spCategory.setAdapter(wifiAdapter);

        binding.igProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ImagePicker();
                }catch (Exception e){
                    e.printStackTrace();
                    Log.e("ImagePicker",e.toString());
                }
            }
        });

        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.etFirstName.getText().toString().isEmpty()){
                    binding.etFirstName.setError("Please Enter First Name.");
                }else if (binding.etLastName.getText().toString().isEmpty()){
                    binding.etLastName.setError("Please Enter Last Name.");
                }else if (binding.etMobileNumber.getText().toString().isEmpty()){
                    binding.etMobileNumber.setError("Please Enter Mobile Number.");
                }else if (binding.etEmail.getText().toString().isEmpty()){
                    binding.etEmail.setError("Please Enter Email.");
                }else if (binding.etMobileNumber.getText().toString().length() != 10){
                    binding.etMobileNumber.setError("Please Enter Valid Mobile Number.");
                }else if (!(contactViewModel.getTBLContactByMobileNumber(binding.etMobileNumber.getText().toString().trim()).isEmpty())){
                    binding.etMobileNumber.setError("This Mobile Number is already save.");
                }else if (uri == null){
                    Toast.makeText(getContext(), "Please Select Image.", Toast.LENGTH_SHORT).show();
                }else {
                    contactViewModel.addTBLContact(new TBLContact(binding.etFirstName.getText().toString().trim(),
                            binding.etLastName.getText().toString().trim(),binding.etMobileNumber.getText().toString().trim(),
                            binding.etEmail.getText().toString().trim(), binding.spCategory.getSelectedItem().toString(),uri.toString()));

                    FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.mainContainer,new ContactListFragment());
                    fragmentTransaction.addToBackStack(null).commit();
                }
            }
        });

        return binding.getRoot();
    }

    private void ImagePicker(){
        ImagePicker.with(this)
                .galleryOnly()
                .cropSquare()	//Crop square image, its same as crop(1f, 1f)
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK){
            uri = data.getData();
            binding.igProfileImage.setImageURI(uri);

        }else if (resultCode == ImagePicker.RESULT_ERROR){
            Toast.makeText(getActivity(), "Profile Upload Failed, Please try again.", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getActivity(), "Profile Upload Failed, Please try again.", Toast.LENGTH_SHORT).show();

        }
    }
}