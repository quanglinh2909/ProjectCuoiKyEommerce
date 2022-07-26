package com.example.projectcuoikyeommerce.activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectcuoikyeommerce.R;
import com.example.projectcuoikyeommerce.activity.admin.DashBoardActivity;
import com.example.projectcuoikyeommerce.data_local.DataLocalManager;
import com.example.projectcuoikyeommerce.model.User;
import com.example.projectcuoikyeommerce.presenter.LoginPresenter;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginActivity extends AppCompatActivity {
    private TextView textCreateAccount;
    private EditText username,password;
    private Button btnLogin;
    private LoginPresenter loginPresenter;
    private SignInButton sign_in_button;
    private static final String TAG = "GoogleActivity";
    private static final int RC_SIGN_IN = 9001;

    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]
    private GoogleSignInClient mGoogleSignInClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        User user = DataLocalManager.getInstance().getUser();
        if(user != null){
          if(user.getRole() != null &&  user.getRole() == 0){
              Intent intent = new Intent(LoginActivity.this,MainActivity.class);
              startActivity(intent);
              finishAffinity();
          }
            if( user.getRole() != null && user.getRole() == 1){
                Intent intent = new Intent(LoginActivity.this, DashBoardActivity.class);
                startActivity(intent);
                finishAffinity();
            }

        }
        initUI();
        handleAction();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        // [END config_signin]

        // [START initialize_auth]
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();


    }


    private void initUI() {
        textCreateAccount = findViewById(R.id.textCreateAccount);
        btnLogin = findViewById(R.id.login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        sign_in_button = findViewById(R.id.sign_in_button);
        loginPresenter = new LoginPresenter(this);
    }

    private void handleAction() {
        textCreateAccount.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this,SignUpActivity.class);
            startActivity(intent);
        });

        btnLogin.setOnClickListener(v -> {
         loginPresenter.login(username.getText().toString(),password.getText().toString());
        });

        sign_in_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
    }
    // [START onactivityresult]
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
            }
        }
    }
    // [END onactivityresult]

    // [START auth_with_google]
    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            updateUI(null);
                        }
                    }
                });
    }
    // [END auth_with_google]

    // [START signin]
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    // [END signin]

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void updateUI(FirebaseUser user) {

        if (user != null){
            User user1=  loginPresenter.createUser(user.getDisplayName(),user.getUid(),user.getEmail());
            if(user1 != null){
                DataLocalManager.getInstance().setUser(user1);
                if(user1.getRole() != null && user1.getRole() == 0){
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    finishAffinity();

                }
                if(user1.getRole() != null && user1.getRole() == 1){
                    Intent intent = new Intent(getApplicationContext(), DashBoardActivity.class);
                    startActivity(intent);
                    finishAffinity();
                }
            }else{
                Toast.makeText(this, "Lỗi !!!!", Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(this, "Lỗi !!!!", Toast.LENGTH_SHORT).show();
        }

    }
}