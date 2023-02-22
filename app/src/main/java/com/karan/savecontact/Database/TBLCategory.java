package com.karan.savecontact.Database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "TBLCategory")
public class TBLCategory {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "category")
    private String category;

    public TBLCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
