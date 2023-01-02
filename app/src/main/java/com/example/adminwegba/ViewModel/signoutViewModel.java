package com.example.adminwegba.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.adminwegba.Repositories.registrationRepo;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;

public class signoutViewModel extends AndroidViewModel {
    private registrationRepo signupRepo;
    private MutableLiveData<FirebaseUser> userlivedata;
    private MutableLiveData<Boolean> looggedliverdata;

    public signoutViewModel(@NonNull Application application) {
        super(application);
        signupRepo = new registrationRepo(application );
        looggedliverdata = signupRepo.getLooggedoutliverdata();
    }
    public void signout(){
        signupRepo.signOut();
    }


}
