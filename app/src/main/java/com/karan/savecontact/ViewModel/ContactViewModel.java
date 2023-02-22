package com.karan.savecontact.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.karan.savecontact.Database.DatabaseHelper;
import com.karan.savecontact.Database.TBLCategory;
import com.karan.savecontact.Database.TBLContact;

import java.util.List;

public class ContactViewModel extends AndroidViewModel {

    DatabaseHelper databaseHelper;

    public ContactViewModel(@NonNull Application application) {
        super(application);

        //Pass Context in DatabaseHelper
        databaseHelper = DatabaseHelper.databaseHelper(application.getApplicationContext());
    }

    public void addTBLCategory(TBLCategory tblCategory){
        databaseHelper.databaseInterface().addTBLCategory(tblCategory);
    }

    public List<TBLCategory> getTBLCategory(){
        return databaseHelper.databaseInterface().getTBLCategory();
    }

    public LiveData<List<TBLCategory>> getLiveTBLCategory(){
        return databaseHelper.databaseInterface().getLiveTBLCategory();
    }

    public List<TBLCategory> getTBLCategoryByCategory(String Category){
        return databaseHelper.databaseInterface().getTBLCategoryByCategory(Category);
    }

    public void updateTBLCategory(String Category, String NewCategory){
        databaseHelper.databaseInterface().updateTBLCategory(Category,NewCategory);
    }

    public void deleteTBLCategory(String Category){
        databaseHelper.databaseInterface().deleteTBLCategory(Category);
    }

    public void addTBLContact(TBLContact tblContact){
        databaseHelper.databaseInterface().addTBLContact(tblContact);
    }

    public List<TBLContact> getTBLContact(){
        return databaseHelper.databaseInterface().getTBLContact();
    }

    public List<TBLContact> getTBLContactCategory(String Category){
        return databaseHelper.databaseInterface().getTBLContactCategory(Category);
    }

    public LiveData<List<TBLContact>> getLiveTBLContact(){
        return databaseHelper.databaseInterface().getLiveTBLContact();
    }

    public void updateTBLContact(String FirstName, String LastName, String NewMobileNumber, String Email, String Category,String Image, String MobileNumber){
        databaseHelper.databaseInterface().updateTBLContact(FirstName,LastName,NewMobileNumber,Email,Category,Image,MobileNumber);
    }

    public List<TBLContact> getTBLContactByMobileNumber(String MobileNumber){
        return databaseHelper.databaseInterface().getTBLContactByMobileNumber(MobileNumber);
    }

    public void deleteTBLContact(String MobileNumber){
        databaseHelper.databaseInterface().deleteTBLContact(MobileNumber);
    }
}
