package com.example.studying.a100ballovapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.DialogFragment;
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
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.inputmethod.InputMethodManager;

import com.example.studying.a100ballovapplication.about_us.FragmentOne;
import com.example.studying.a100ballovapplication.about_us.ParentFragmentOne;
import com.example.studying.a100ballovapplication.contacts.ContactsFragment;
import com.example.studying.a100ballovapplication.enroll.EnrollFragment;
import com.example.studying.a100ballovapplication.login.LoginActivity;
import com.example.studying.a100ballovapplication.registration.RegistrationActivity;
import com.example.studying.a100ballovapplication.schedule.ChooseFragment;
import com.example.studying.a100ballovapplication.schedule.ScheduleFragment;

import static com.example.studying.a100ballovapplication.base.Defaults.KEY_FRAGMENT;


public class BasicNotLoggedActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

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
        Log.e("SSS", " KEY " + fragmentType);

        if (fragmentType.equals("О нас")){
            fragment = (ParentFragmentOne) ParentFragmentOne.newInstance(getSupportFragmentManager());
        } else if (fragmentType.equals("Контакты")){
            fragment = ContactsFragment.newInstance(getSupportFragmentManager());
        } else if(fragmentType.equals("Записаться")){
            fragment = EnrollFragment.newInstance(getSupportFragmentManager());
        } else if (fragmentType.equals("Расписание")){
            DialogFragment dlg1 = new ChooseFragment();
            dlg1.show(getSupportFragmentManager(), "");
            fragment = ContactsFragment.newInstance(getSupportFragmentManager());
        } else {
            fragment = ContactsFragment.newInstance(getSupportFragmentManager());
        }

        if(savedInstanceState == null && !fragmentType.equals("Расписание")) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container_basic, fragment).commit();
        }
        setTitle(fragmentType);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.basic_enter ){
            Intent intent = new Intent(BasicNotLoggedActivity.this, LoginActivity.class);
            intent.putExtra(KEY_FRAGMENT, item.getTitle());
            startActivity(intent);
        } else if (item.getItemId() == R.id.basic_reg){
            Intent intent = new Intent(BasicNotLoggedActivity.this, RegistrationActivity.class);
            intent.putExtra(KEY_FRAGMENT, item.getTitle());
            startActivity(intent);
        } else {

            Fragment fragment = null;
            Class fragmentClass;

            switch (item.getItemId()) {
                case R.id.basic_schedule:
                    DialogFragment dlg1 = new ChooseFragment();
                    dlg1.show(getSupportFragmentManager(), "");
                    fragmentClass = ScheduleFragment.class;
                    break;
                case R.id.basic_enroll:
                    fragmentClass = EnrollFragment.class;
                    break;
                case R.id.basic_contacts:
                    fragmentClass = ContactsFragment.class;
                    break;
                case R.id.basic_about:
                    fragmentClass = ParentFragmentOne.class;
                    break;
                default:
                    fragmentClass = ContactsFragment.class;
            }

            if (item.getItemId() != R.id.basic_schedule) {
                try {
                    fragment = (Fragment) fragmentClass.newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                // Вставить фрагмент, заменяя любой существующий
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container_basic, fragment).commit();

            }
        }
        item.setChecked(true);
        setTitle(item.getTitle());

        // Закрыть navigation drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_my);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_FRAGMENT, getTitle().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        setTitle(savedInstanceState.getString(KEY_FRAGMENT));
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

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() ==  MotionEvent.ACTION_DOWN) hideKeyboard();
        return super.dispatchTouchEvent(ev);
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
    }


}
