package com.karan.savecontact.Adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.karan.savecontact.Database.TBLCategory;
import com.karan.savecontact.Database.TBLContact;
import com.karan.savecontact.Fragment.ContactListFragment;
import com.karan.savecontact.R;
import com.karan.savecontact.ViewModel.ContactViewModel;
import com.karan.savecontact.databinding.SampleCategoryBinding;
import com.karan.savecontact.databinding.SampleContactBinding;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.viewHolder> {
    Context context;
    ArrayList<TBLContact> tblContacts;

    public ContactAdapter(Context context) {
        this.context = context;
    }

    public void SetContact(ArrayList<TBLContact> tblContacts){
        this.tblContacts = tblContacts;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ContactAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_contact, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactAdapter.viewHolder holder, int position) {
        TBLContact tblContact = tblContacts.get(position);

        holder.binding.txName.setText(tblContact.getFirstname()+" "+tblContact.getLastname());
        holder.binding.txNumber.setText(tblContact.getMobilenumber());
        holder.binding.igProfileImage.setImageURI(Uri.parse(tblContact.getImage()));

        holder.binding.igDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.contactViewModel.deleteTBLContact(tblContact.getMobilenumber());
            }
        });

        holder.binding.igEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.ChangeCategoryDialog.show();

                holder.etFirstName.setText(tblContact.getFirstname());
                holder.etLastName.setText(tblContact.getLastname());
                holder.etMobileNumber.setText(tblContact.getMobilenumber());
                holder.etEmail.setText(tblContact.getEmail());
                holder.igProfileImage.setImageURI(Uri.parse(tblContact.getImage()));


                for (int i = 0; i<holder.CategoryList.size();i++){
                    if (holder.CategoryList.get(i).equals(tblContact.getCategory())){
                        holder.spCategory.setSelection(i);
                        break;
                    }
                }

                holder.btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        holder.ChangeCategoryDialog.dismiss();
                    }
                });

                holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (holder.etFirstName.getText().toString().isEmpty()){
                            holder.etFirstName.setError("Please Enter First Name.");
                        }else if (holder.etLastName.getText().toString().isEmpty()){
                            holder.etLastName.setError("Please Enter Last Name.");
                        }else if (holder.etMobileNumber.getText().toString().isEmpty()){
                            holder.etMobileNumber.setError("Please Enter Mobile Number.");
                        }else if (holder.etEmail.getText().toString().isEmpty()){
                            holder.etEmail.setError("Please Enter Email.");
                        }else if (holder.etMobileNumber.getText().toString().length() != 10){
                            holder.etMobileNumber.setError("Please Enter Valid Mobile Number.");
                        }else {
                            holder.contactViewModel.updateTBLContact(holder.etFirstName.getText().toString().trim(),
                                    holder.etLastName.getText().toString().trim(),holder.etMobileNumber.getText().toString().trim(),
                                    holder.etEmail.getText().toString().trim(), holder.spCategory.getSelectedItem().toString(),tblContact.getImage(),tblContact.getMobilenumber());

                            holder.ChangeCategoryDialog.dismiss();
                        }
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return tblContacts.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        private SampleContactBinding binding;
        private ContactViewModel contactViewModel;
        private Dialog ChangeCategoryDialog;
        private Button btnUpdate,btnCancel;
        private EditText etFirstName,etLastName,etMobileNumber,etEmail;
        private CircleImageView igProfileImage;
        private Spinner spCategory;
        private ArrayList<String> CategoryList = new ArrayList<>();

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            binding = SampleContactBinding.bind(itemView);

            contactViewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(ContactViewModel.class);

            //Configure Switch Dialog Set
            ChangeCategoryDialog = new Dialog(context);
            ChangeCategoryDialog.setContentView(R.layout.custom_contact_dialog);
            ChangeCategoryDialog.getWindow().setBackgroundDrawable(context.getDrawable(R.drawable.corner_round));
            ChangeCategoryDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            btnUpdate = ChangeCategoryDialog.findViewById(R.id.btnSave);
            btnCancel = ChangeCategoryDialog.findViewById(R.id.btnCancel);
            etFirstName = ChangeCategoryDialog.findViewById(R.id.etFirstName);
            etLastName = ChangeCategoryDialog.findViewById(R.id.etLastName);
            etEmail = ChangeCategoryDialog.findViewById(R.id.etEmail);
            etMobileNumber = ChangeCategoryDialog.findViewById(R.id.etMobileNumber);
            spCategory = ChangeCategoryDialog.findViewById(R.id.spCategory);
            igProfileImage = ChangeCategoryDialog.findViewById(R.id.igProfileImage);

            ArrayList<TBLCategory> tblCategories = (ArrayList<TBLCategory>) contactViewModel.getTBLCategory();

            for (TBLCategory tblCategory : tblCategories){
                CategoryList.add(tblCategory.getCategory());
            }

            //Spinner Adapter
            ArrayAdapter<String> wifiAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, CategoryList);

            // Drop down layout style - list view with radio button
            wifiAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            // attaching data adapter to spinner
            spCategory.setAdapter(wifiAdapter);
        }
    }

}
