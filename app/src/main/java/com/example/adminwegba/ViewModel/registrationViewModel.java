package com.example.adminwegba.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.adminwegba.Repositories.registrationRepo;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;

public class registrationViewModel extends AndroidViewModel  {
    private registrationRepo signupRepo;
    private MutableLiveData<DatabaseError> errorMutable = new MutableLiveData<>();
    private MutableLiveData<FirebaseUser> userlivedata;




    public registrationViewModel(@NonNull Application application) {
        super(application);
        signupRepo = new registrationRepo(application);
        userlivedata= signupRepo.getUserlivedata();
    }
    public MutableLiveData<FirebaseUser> getUserlivedata() {
        return userlivedata;
    }

    public void login(String email ,String password ){
        signupRepo.login(email,password);
    }

}
