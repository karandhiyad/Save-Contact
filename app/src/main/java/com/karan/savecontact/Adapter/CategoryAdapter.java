package com.karan.savecontact.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.karan.savecontact.Database.DatabaseHelper;
import com.karan.savecontact.Database.TBLCategory;
import com.karan.savecontact.R;
import com.karan.savecontact.ViewModel.ContactViewModel;
import com.karan.savecontact.databinding.SampleCategoryBinding;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.viewHolder> {

    Context context;
    ArrayList<TBLCategory> tblCategories;

    public CategoryAdapter(Context context) {
        this.context = context;
    }

    public void SetCategories(ArrayList<TBLCategory> tblCategories){
        this.tblCategories = tblCategories;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_category, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.viewHolder holder, int position) {
        TBLCategory tblCategory = tblCategories.get(position);

        holder.binding.txCategoryName.setText(tblCategory.getCategory());
        
        holder.binding.igEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.ChangeCategoryDialog.show();

                holder.etAddCategory.setText(tblCategory.getCategory());

                holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (holder.etAddCategory.getText().toString().isEmpty()){
                            holder.etAddCategory.setError("Please Enter Category Name.");
                        }else if (!(holder.contactViewModel.getTBLCategoryByCategory(holder.etAddCategory.getText().toString().trim()).isEmpty())){
                            holder.etAddCategory.setError("This Category is already save.");
                        }else {
                            holder.contactViewModel.updateTBLCategory(tblCategory.getCategory(),holder.etAddCategory.getText().toString().trim());
                            holder.etAddCategory.setText("");
                            holder.ChangeCategoryDialog.dismiss();
                        }
                    }
                });

                holder.btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        holder.ChangeCategoryDialog.dismiss();
                    }
                });
            }
        });
        
        holder.binding.igDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    holder.contactViewModel.deleteTBLCategory(tblCategory.getCategory());
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return tblCategories.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        private SampleCategoryBinding binding;
        private ContactViewModel contactViewModel;
        private Dialog ChangeCategoryDialog;
        private Button btnUpdate, btnCancel;
        private EditText etAddCategory;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            binding = SampleCategoryBinding.bind(itemView);

            contactViewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(ContactViewModel.class);

            //Configure Switch Dialog Set
            ChangeCategoryDialog = new Dialog(context);
            ChangeCategoryDialog.setContentView(R.layout.custom_edit_dialog);
            ChangeCategoryDialog.getWindow().setBackgroundDrawable(context.getDrawable(R.drawable.corner_round));
            ChangeCategoryDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            btnUpdate = ChangeCategoryDialog.findViewById(R.id.btnSave);
            btnCancel = ChangeCategoryDialog.findViewById(R.id.btnCancel);
            etAddCategory = ChangeCategoryDialog.findViewById(R.id.etAddCategory);
        }
    }
}
