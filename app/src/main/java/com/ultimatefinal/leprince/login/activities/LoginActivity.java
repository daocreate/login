package com.ultimatefinal.leprince.login.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.ultimatefinal.leprince.login.R;
import com.ultimatefinal.leprince.login.helper.InputValidation;
import com.ultimatefinal.leprince.login.sql.DatabaseHelper;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private  NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;
    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;
    private AppCompatButton appCompatButtonLogin;
    private AppCompatTextView textViewLinkRegister;
    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        initViews();
        initiListners();
        initObjects();
    }
    private void initViews()
    {
       nestedScrollView= (NestedScrollView) findViewById(R.id.nestedScrollView);

       textInputEditTextEmail= (TextInputEditText) findViewById(R.id.TextInputEditTextEmail);
        textInputLayoutPassword =(TextInputLayout) findViewById(R.id.TextInputLayoutPaasword);

        textInputEditTextEmail=(TextInputEditText) findViewById(R.id.TextInputEditTextEmail);
        textInputEditTextPassword= (TextInputEditText) findViewById(R.id.TextInputEditTextPassword);

        appCompatButtonLogin= (AppCompatButton) findViewById(R.id.AppCompatButtonLogin);

        textViewLinkRegister=(AppCompatTextView) findViewById(R.id.textviewlinkregister);
    }

    private void initiListners()
    {
        appCompatButtonLogin.setOnClickListener(this);
        textViewLinkRegister.setOnClickListener(this);
    }
    private void initObjects()
    {
        databaseHelper = new DatabaseHelper(this);
        inputValidation = new InputValidation(this);
    }

    @Override
    public void onClick(View v) {
    switch (v.getId())
    {
        case  R.id.AppCompatButtonLogin:
            VeridyFromSQLite();
            break;
        case R.id.textviewlinkregister:
            Intent intentRegister = new Intent(getApplication(), RegisterActivity.class);
            startActivity(intentRegister);
            break;
    }
    }
    private void VeridyFromSQLite()
    {
        if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.err_message_email)))
        {
            return ;
        }

        if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.err_message_email)))
        {
            return;
        }
        if (databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim()
                , textInputEditTextPassword.getText().toString().trim() ))
        {
            Intent accountsIntent = new Intent(this,UsersActivity.class);
            accountsIntent.putExtra("EMAIL", textInputEditTextEmail.getText().toString().trim());
            startActivity(accountsIntent);

        }
        else
        {
            Snackbar.make(nestedScrollView, getString(R.string.err_valid_email_password),Snackbar.LENGTH_LONG).show();

        }

    }
    private void empytyInputEditText()
    {
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
    }

}
