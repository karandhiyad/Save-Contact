package com.karan.savecontact;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.karan.savecontact.Fragment.AddCategoryFragment;
import com.karan.savecontact.Fragment.AddContactFragment;
import com.karan.savecontact.Fragment.ContactListFragment;
import com.karan.savecontact.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    public static ActivityMainBinding binding;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.MainToolbar);
        View headerView = binding.navView.getHeaderView(0);

        setSupportActionBar(toolbar);

        //Set Default Fragment
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.mainContainer, new ContactListFragment());
        fragmentTransaction.commit();

        //Navigation Drawer Set
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.nav_AddCategory) {
                    binding.appBarHome.txToolbarTitle.setText("Create and store category");
                    binding.appBarHome.igFilter.setVisibility(View.GONE);
                    binding.appBarHome.igSearch.setVisibility(View.GONE);

                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.mainContainer, new AddCategoryFragment());
                    fragmentTransaction.commit();

                } else if (id == R.id.nav_AddContact) {
                    binding.appBarHome.txToolbarTitle.setText("Add Contact");
                    binding.appBarHome.igFilter.setVisibility(View.GONE);
                    binding.appBarHome.igSearch.setVisibility(View.GONE);

                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.mainContainer, new AddContactFragment());
                    fragmentTransaction.commit();

                } else if (id == R.id.nav_ContactList) {
                    binding.appBarHome.txToolbarTitle.setText("Contact List");
                    binding.appBarHome.igFilter.setVisibility(View.VISIBLE);
                    binding.appBarHome.igSearch.setVisibility(View.VISIBLE);

                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.mainContainer, new ContactListFragment());
                    fragmentTransaction.commit();

                }
                drawerLayout.closeDrawer(GravityCompat.START);

                return true;
            }
        });
    }

    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
//            super.onBackPressed();
            finishAffinity();
        }
    }
}