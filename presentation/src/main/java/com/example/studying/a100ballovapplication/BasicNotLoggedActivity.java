package com.example.studying.a100ballovapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.example.studying.a100ballovapplication.about_us.FragmentOne;
import com.example.studying.a100ballovapplication.about_us.ParentFragmentOne;
import com.example.studying.a100ballovapplication.contacts.ContactsFragment;
import com.example.studying.a100ballovapplication.enroll.EnrollFragment;


public class BasicNotLoggedActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final String KEY_FRAGMENT = "KEY_FRAGMENT";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basic_nav_draw);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_my);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_my);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_my);
        navigationView.setNavigationItemSelectedListener(this);

        Fragment fragment;
        String fragmentType = getIntent().getStringExtra(KEY_FRAGMENT);

        if (fragmentType.equals("О нас")){
            fragment = (ParentFragmentOne) ParentFragmentOne.newInstance(getSupportFragmentManager());
        } else if (fragmentType.equals("Контакты")){
            fragment = ContactsFragment.newInstance(getSupportFragmentManager());
        } else if(fragmentType.equals("Записаться")){
            fragment = EnrollFragment.newInstance(getSupportFragmentManager());
        } else {
            fragment = ContactsFragment.newInstance(getSupportFragmentManager());
        }

        if(savedInstanceState == null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container_basic, fragment, fragment.getClass().getName()).commit();

            setTitle(fragmentType);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        //TODO Для кнопок ВХОД и РЕГИСТРАЦИЯ сделать отдельную логику (не фрагменты)

        Fragment fragment = null;
        Class fragmentClass;

        switch(item.getItemId()) {
            case R.id.basic_schedule:
                fragmentClass = ContactsFragment.class;
                break;
            case R.id.basic_enroll:
                fragmentClass = EnrollFragment.class;
                break;
            case R.id.basic_contacts:
                fragmentClass = ContactsFragment.class;
                break;
            case R.id.basic_about:
                //TODO Разобраться с косяком загрузки в этом месте
                fragmentClass = ParentFragmentOne.class;
                break;
            default:
                fragmentClass = ContactsFragment.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Вставить фрагмент, заменяя любой существующий
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container_basic, fragment).commit();

        // Выделение существующего элемента с помощью NavigationView
        item.setChecked(true);

        //Установить заголовок тулбара
        setTitle(item.getTitle());

        // Закрыть navigation drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_my);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_my);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}
