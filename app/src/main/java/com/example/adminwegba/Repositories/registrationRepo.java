package com.example.adminwegba.Repositories;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;


public class registrationRepo {
    private Application app;
    private FirebaseAuth firebaseauth;
    private MutableLiveData<Boolean> looggedoutliverdata;
    private MutableLiveData<FirebaseUser> userlivedata;




    public registrationRepo(Application app ) {
        this.app = app;
        firebaseauth = FirebaseAuth.getInstance();
        looggedoutliverdata = new MutableLiveData<>();
        userlivedata = new MutableLiveData<>();


    }

    public void login(String email , String password){
        firebaseauth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            userlivedata.postValue(firebaseauth.getCurrentUser());
                        }else {
                            Toast.makeText(app, "wrong email or password", Toast.LENGTH_SHORT).show();
                            Log.d("login","wrong email or password"+task.getException().getMessage().toString());
                        }
                    }
                });
    }
    public MutableLiveData<Boolean> getLooggedoutliverdata() {
        return looggedoutliverdata;
    }


    public void signOut(){
        firebaseauth.signOut();
        looggedoutliverdata.postValue(true);
    }

    public MutableLiveData<FirebaseUser> getUserlivedata() {
        return userlivedata;
    }



}
