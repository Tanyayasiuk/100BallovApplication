package com.yasiuk.studying.a100ballovapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.MotionEvent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.yasiuk.studying.a100ballovapplication.books.BooksFragment;
import com.yasiuk.studying.a100ballovapplication.contacts.ContactsFragment;
import com.yasiuk.studying.a100ballovapplication.my_profile.MyProfileFragment;
import com.yasiuk.studying.a100ballovapplication.news.NewsFragment;
import com.yasiuk.studying.domain.entity.AuthState;
import com.yasiuk.studying.domain.interaction.AuthService;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;

import static com.yasiuk.studying.a100ballovapplication.base.Defaults.KEY_FRAGMENT;
import static com.yasiuk.studying.a100ballovapplication.base.Defaults.KEY_USER_EMAIL;
import static com.yasiuk.studying.a100ballovapplication.base.Defaults.KEY_USER_LOGIN;
import static com.yasiuk.studying.a100ballovapplication.base.Defaults.SHARED_PREFS_NAME;

public class NavDrawActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Inject
    public AuthService authService;
    private boolean exit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.appComponent.inject(this);

        setContentView(R.layout.activity_nav_draw);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Fragment fragment;
        int fragmentType = getIntent().getIntExtra(KEY_FRAGMENT, 0);

        if(fragmentType == R.string.news_item){
            fragment = NewsFragment.newInstance(getSupportFragmentManager());
        } else if(fragmentType == R.string.profile_item){
            fragment = MyProfileFragment.newInstance(getSupportFragmentManager());
        }else  {
            fragment = ContactsFragment.newInstance(getSupportFragmentManager());
        }

        if(savedInstanceState == null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container, fragment).commit();
        }
        setTitle(getResources().getString(fragmentType));

        View header = navigationView.getHeaderView(0);

        SharedPreferences preferences = getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE);

        TextView navTitle = (TextView) header.findViewById(R.id.nav_header_title);
        navTitle.setText(preferences.getString(KEY_USER_LOGIN, null));

        TextView navSubtitle = (TextView) header.findViewById(R.id.nav_header_subtitle);
        navSubtitle.setText(preferences.getString(KEY_USER_EMAIL, null));

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nav_draw, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_logout) {
            authService.observeState().subscribeWith(new DisposableObserver<AuthState>() {
                @Override
                public void onNext(@NonNull AuthState authState) {
                    //проверяем состояние авторизации
                    //если подписаны -
                    if(authState.isSigned()) {
                        Log.e("SSS", "isSigned");
                        //TODO Здесь надо намутить диалог с подтверждением и там удалять токен
                        //TODO Вернее сделать LogOut usecase
                        /*authService.removeAccessToken();
                        startActivity(new Intent(NavDrawActivity.this, MainActivity.class));*/
                    }
                }

                @Override
                public void onError(@NonNull Throwable e) {Log.e("SSS", e.getLocalizedMessage());}

                @Override
                public void onComplete() {}
            });
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        Fragment fragment = null;
        Class fragmentClass = ContactsFragment.class;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        if (id == R.id.nav_profile) {
            fragmentClass = MyProfileFragment.class;
        } else if (id == R.id.nav_books) {
            fragmentClass = BooksFragment.class;
        } else if (id == R.id.nav_news) {
            fragmentClass = NewsFragment.class;
        } else if (id == R.id.nav_contacts){
            fragmentClass = ContactsFragment.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.container, fragment, fragment.getClass().getName());
        fragmentTransaction.commit();

        setTitle(item.getTitle());

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

        if (exit) {
            this.finish();
        }else {
            //Toast.makeText(this, R.string.confirm_exit, Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 1000);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
