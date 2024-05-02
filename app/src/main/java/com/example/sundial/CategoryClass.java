package com.example.sundial;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class CategoryClass {
    private String name;

    public CategoryClass(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
