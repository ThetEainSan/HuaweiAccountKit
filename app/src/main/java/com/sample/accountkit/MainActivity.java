package com.sample.accountkit;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.huawei.hmf.tasks.Task;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.support.account.AccountAuthManager;
import com.huawei.hms.support.account.request.AccountAuthParams;
import com.huawei.hms.support.account.request.AccountAuthParamsHelper;
import com.huawei.hms.support.account.result.AuthAccount;
import com.huawei.hms.support.account.service.AccountAuthService;
import com.huawei.hms.support.hwid.ui.HuaweiIdAuthButton;

public class MainActivity extends AppCompatActivity {
    HuaweiIdAuthButton btnAuthCode;
    HuaweiIdAuthButton btnIdToken;
    Button btnSilent;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnAuthCode=findViewById(R.id.btnAuthCode);
        btnIdToken=findViewById(R.id.btnIdToken);
        btnSilent=findViewById(R.id.btnSilent);


        btnAuthCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccountAuthParams authParams = new AccountAuthParamsHelper(AccountAuthParams.DEFAULT_AUTH_REQUEST_PARAM).setAuthorizationCode().createParams();
                AccountAuthService service = AccountAuthManager.getService(MainActivity.this, authParams);
                startActivityForResult(service.getSignInIntent(), 8888);

            }
        });


        btnIdToken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccountAuthParams authParams2 = new AccountAuthParamsHelper(AccountAuthParams.DEFAULT_AUTH_REQUEST_PARAM).setAuthorizationCode().createParams();
                AccountAuthService service2 = AccountAuthManager.getService(MainActivity.this, authParams2);
                startActivityForResult(service2.getSignInIntent(), 2222);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 8888) {
            Task<AuthAccount> authAccountTask = AccountAuthManager.parseAuthResultFromIntent(data);
            if (authAccountTask.isSuccessful()) {
                // The sign-in is successful, and the user's ID information and authorization code are obtained.
                AuthAccount authAccount = authAccountTask.getResult();
                Log.i(MainActivity.class.getSimpleName(), "serverAuthCode:" + authAccount.getAuthorizationCode());
            } else {
                // The sign-in failed.
                Log.e(MainActivity.class.getSimpleName(), "sign in failed:" + ((ApiException) authAccountTask.getException()).getStatusCode());
            }
        }


        else if (requestCode == 2222){
            Task<AuthAccount> authAccountTask = AccountAuthManager.parseAuthResultFromIntent(data);
            if (authAccountTask.isSuccessful()) {
                // The sign-in is successful, and the user's ID information and authorization code are obtained.
                AuthAccount authAccount = authAccountTask.getResult();
                Log.i(MainActivity.class.getSimpleName(), "idToken:" + authAccount.getIdToken());
                Log.i(MainActivity.class.getSimpleName(), "accountFlag:" + authAccount.getAccountFlag() );
            } else {
                // The sign-in failed.
                Log.e(MainActivity.class.getSimpleName(), "sign in failed:" + ((ApiException) authAccountTask.getException()).getStatusCode());
            }


        }
    }
}