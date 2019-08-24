package ct.emergency;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class AdminLogin extends AppCompatActivity {


    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
String unm1,pwd1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_login);
        // Set up the login form.
       }
    public void login(View v) throws Exception {

        final EditText userName = (EditText)findViewById(R.id.userName);
        final EditText userPwd= (EditText)findViewById(R.id.pwd);
        unm1=userName.getText().toString().trim();
        pwd1=userPwd.getText().toString().trim();

        if ("".equals(userName.getText().toString().trim()) || "".equals(userPwd.getText().toString().trim())) {
            Toast.makeText(getApplicationContext(), "All filds are mandatory", Toast.LENGTH_LONG).show();
        } else {


            if(unm1.equals("admin")&& pwd1.equals("admin")) {
                Intent i = new Intent(AdminLogin.this, AdminHome.class);
                startActivity(i);
                finish();
            }else{
                Toast.makeText(getApplicationContext(), "Incorrect Credentials", Toast.LENGTH_LONG).show();

            }

        }
    }




}

