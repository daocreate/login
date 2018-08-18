package com.ultimatefinal.leprince.login.helper;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by Le prince on 13/08/2018.
 */
public class InputValidation {

    private Context contexte;

    public InputValidation(Context contexte) {
        this.contexte = contexte;
    }

     public boolean isInputEditTextFilled(TextInputEditText textInputEditText, TextInputLayout textInputLayout, String message)
     {
         String value= textInputEditText.getText().toString();
         if (value.isEmpty())
         {
             textInputEditText.setError(message);
             hideKeybordForm(textInputEditText);
             return false;
         }
         else
         {
             textInputLayout.setErrorEnabled(false);
         }
         return true;
     }

    public boolean isInputEditTextEmail(TextInputEditText textInputEditText,TextInputLayout textInputLayout, String message)
    {
        String value= textInputEditText.getText().toString().trim();
        if (value.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(value).matches())
        {
            textInputEditText.setError(message);
            hideKeybordForm(textInputEditText);
            return false;
        }
        else
        {
            textInputLayout.setErrorEnabled(false);
        }
        return true;
    }

    public boolean isInputEditTextMatches(TextInputEditText textInputEditText,TextInputEditText textInputEditText1, TextInputLayout textInputLayout, String message)
    {
        String value= textInputEditText.getText().toString().trim();
        String value1= textInputEditText1.getText().toString().trim();
        if (!value.contentEquals(value1))
        {
            textInputLayout.setError(message);
            hideKeybordForm(textInputEditText1);
            return false;
        }
        else
        {
            textInputLayout.setErrorEnabled(false);
        }
        return true;

    }

    private  void hideKeybordForm(View view)
    {
        InputMethodManager inn=(InputMethodManager) contexte.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inn.hideSoftInputFromWindow(view.getWindowToken(), WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }
}
