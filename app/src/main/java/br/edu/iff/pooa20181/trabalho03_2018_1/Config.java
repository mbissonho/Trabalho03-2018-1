package br.edu.iff.pooa20181.trabalho03_2018_1;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class Config extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("0320181.realm")
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
    }

}

