package com.archives.museum;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONObject;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class LoginActivity extends AppCompatActivity {

    private static String APP_ID="102039461";
    private Tencent mTencent;
    String nickname;
    String figureurl_qq_2;
    String token;//token
    String expires_id;//有效期
    String uniqueCode;//唯一码
    CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        QMUIStatusBarHelper.translucent(this);
        QMUIStatusBarHelper.setStatusBarLightMode(this);
        setContentView(R.layout.activity_login);
    }

    IUiListener miUiListener=new IUiListener() {
        @Override
        public void onComplete(Object o) {
            uniqueCode=((JSONObject)o).optString("openid");
            token=((JSONObject)o).optString("access_token");
            expires_id=((JSONObject)o).optString("expires_in");
            mTencent.saveSession((JSONObject) o);

            QQToken qqToken =mTencent.getQQToken();
            mTencent.setOpenId(uniqueCode);
            mTencent.setAccessToken(token,expires_id);
            UserInfo info=new UserInfo(getApplicationContext(),qqToken);
            info.getUserInfo(new IUiListener() {
                @Override
                public void onComplete(Object o) {
                    String name=((JSONObject)o).optString("nickname");
                    String uimg=((JSONObject)o).optString("figureurl_qq_2");
                    udata("wxid_yvf2gt1lgl7522",name,uimg);
                    initv();
                }

                @Override
                public void onError(UiError uiError) {

                }

                @Override
                public void onCancel() {

                }

                @Override
                public void onWarning(int i) {

                }
            });
        }

        @Override
        public void onError(UiError uiError) {
            Toast.makeText(LoginActivity.this,"错误"+uiError,Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel() {
            Toast.makeText(LoginActivity.this,"登录取消",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onWarning(int i) {

        }
    };
    //QQ回调重写
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode== Constants.REQUEST_LOGIN) {
            Tencent.onActivityResultData(requestCode,resultCode,data,miUiListener);
        }
    }
    //QQ登录方法
    public void QQonLoginClick(View view){
        mTencent=Tencent.createInstance(APP_ID,this.getApplicationContext());
        checkBox=findViewById(R.id.checkBox);
        if (checkBox.isChecked()){
            mTencent.login(LoginActivity.this,"get_user_info",miUiListener);
        }else {
            Toast.makeText(this,"请先同意下方隐私政策",Toast.LENGTH_SHORT).show();
        }

    }
    public void protocol(View view){
        checkBox=findViewById(R.id.checkBox);
        if (checkBox.isChecked()!=true){
            checkBox.setChecked(true);
        }else {
            checkBox.setChecked(false);
        }

    }
    private void initv() {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    //储存udata到user
    public void udata(String uid,String uname,String uimg){
        SharedPreferences preferences = getSharedPreferences("user",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("uid", uid);
        editor.putString("unme", uname);
        editor.putString("uimg", uimg);
        editor.putBoolean("autho", true);
        editor.apply();
    }

}