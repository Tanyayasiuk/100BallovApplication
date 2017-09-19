package com.example.studying.a100ballovapplication.registration;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.studying.a100ballovapplication.NavDrawActivity;
import com.example.studying.a100ballovapplication.R;
import com.example.studying.a100ballovapplication.base.BaseActivity;
import com.example.studying.a100ballovapplication.databinding.ActivityRegistrationBinding;

import static com.example.studying.a100ballovapplication.BasicNotLoggedActivity.KEY_FRAGMENT;

public class RegistrationActivity extends BaseActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        RegistrationViewModel viewModel = new RegistrationViewModel(this);
        this.viewModel = viewModel;

        ActivityRegistrationBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_registration);
        binding.setViewModel(viewModel);

        binding.toolbarReg.setNavigationIcon(R.drawable.ic_arrow_back);
        binding.toolbarReg.setTitle(getIntent().getStringExtra(KEY_FRAGMENT));

        binding.toolbarReg.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        super.onCreate(savedInstanceState);

        binding.registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistrationActivity.this, NavDrawActivity.class);
                startActivity(intent);
            }
        });

    }
}
