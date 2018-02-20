package br.com.allefdeveloper.despesasmensais;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by allef on 20/02/2018.
 */

public class myApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
