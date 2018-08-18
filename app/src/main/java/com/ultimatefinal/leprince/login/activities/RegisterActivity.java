package com.ultimatefinal.leprince.login.activities;

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
import com.ultimatefinal.leprince.login.model.User;
import com.ultimatefinal.leprince.login.sql.DatabaseHelper;

/**
 * Created by Le prince on 13/08/2018.
 */
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{


    private final AppCompatActivity activity =RegisterActivity.this;

    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutName;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;
    private TextInputLayout textInputLayoutConfrimPassword;

    private TextInputEditText TextInputEditTextName;
    private TextInputEditText TextInputEditTextEmail;
    private TextInputEditText TextInputEditTextPassword;
    private TextInputEditText TextInputEditTextConfrimPassword;

    private AppCompatButton appCompatButtonRegister;
    private AppCompatTextView appCompatTextViewLoginLink;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;
    private User user;

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.AppCompatButtonRegister:
                postDataToSQLite();
                break;
            case R.id.AppCompatButtonLogin:
                finish();
        }
    }

    private  void postDataToSQLite()
    {
        if (!inputValidation.isInputEditTextFilled(TextInputEditTextName, textInputLayoutName, getString(R.string.err_message_nameor)))
        {
            return ;
        }
        if (!inputValidation.isInputEditTextFilled(TextInputEditTextEmail, textInputLayoutEmail, getString(R.string.err_message_email)))
        {
            return;
        }
        if (!inputValidation.isInputEditTextEmail(TextInputEditTextEmail, textInputLayoutEmail, getString(R.string.err_message_email)))
        {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(TextInputEditTextPassword, textInputLayoutPassword, getString(R.string.err_message_password)))
        {
            return;
        }
        if (!inputValidation.isInputEditTextMatches(TextInputEditTextPassword, TextInputEditTextConfrimPassword,textInputLayoutConfrimPassword,
                getString(R.string.err_password_match)))
        {
            return;
        }
        if (databaseHelper.checkUser(TextInputEditTextName.getText().toString().trim()
                , TextInputEditTextPassword.getText().toString().trim() ))
        {
            user.setName(TextInputEditTextName.getText().toString().trim());
            user.setEmail(TextInputEditTextEmail.getText().toString().trim());
            user.setPassword(TextInputEditTextPassword.getText().toString().trim());

            databaseHelper.addUser(user);
        }
        else
        {
            Snackbar.make(nestedScrollView, getString(R.string.success_messgae),Snackbar.LENGTH_LONG).show();
            emptyInputEditText();
        }

    }
    private void  emptyInputEditText()
    {
        TextInputEditTextName.setText(null);
        TextInputEditTextEmail.setText(null);
        TextInputEditTextPassword.setText(null);
        TextInputEditTextConfrimPassword.setText(null);
    }
    @Override
    protected void onCreate(Bundle saveInstanceState) // j'ai modifié ici
    {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();
        initViews();
        initiListners();
        initObjects();
    }



    private void initViews()
    {
        nestedScrollView= (NestedScrollView) findViewById(R.id.nestedScrollView);

        textInputLayoutName= (TextInputLayout) findViewById(R.id.TextInputLayoutName);
        textInputLayoutEmail= (TextInputLayout) findViewById(R.id.TextInputLayoutEmail);
        textInputLayoutPassword =(TextInputLayout) findViewById(R.id.TextInputLayoutPaasword);
        textInputLayoutConfrimPassword =(TextInputLayout) findViewById(R.id.TextInputLayoutConfirmPasword);

        TextInputEditTextName=(TextInputEditText) findViewById(R.id.TextInputEditTextName);
        TextInputEditTextEmail=(TextInputEditText) findViewById(R.id.TextInputEditTextEmail);
        TextInputEditTextPassword= (TextInputEditText) findViewById(R.id.TextInputEditTextPassword);
        TextInputEditTextConfrimPassword= (TextInputEditText) findViewById(R.id.TextInputEditTextConfirmPassword);
        appCompatButtonRegister= (AppCompatButton) findViewById(R.id.AppCompatButtonRegister);

       appCompatTextViewLoginLink=(AppCompatTextView) findViewById(R.id.AppCompatButtonLogin);
    }

    private void initiListners()
    {
        appCompatButtonRegister.setOnClickListener(this);
        appCompatTextViewLoginLink.setOnClickListener(this);
    }
    private void initObjects()
    {
        databaseHelper = new DatabaseHelper(activity);
        inputValidation = new InputValidation(activity);
        user =new User();
    }
}
