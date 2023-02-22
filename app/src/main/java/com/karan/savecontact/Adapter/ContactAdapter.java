package com.karan.savecontact.Adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.karan.savecontact.Database.TBLCategory;
import com.karan.savecontact.Database.TBLContact;
import com.karan.savecontact.R;
import com.karan.savecontact.ViewModel.ContactViewModel;
import com.karan.savecontact.databinding.SampleCategoryBinding;
import com.karan.savecontact.databinding.SampleContactBinding;

import java.util.ArrayList;

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

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            binding = SampleContactBinding.bind(itemView);

            contactViewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(ContactViewModel.class);

        }
    }
}
