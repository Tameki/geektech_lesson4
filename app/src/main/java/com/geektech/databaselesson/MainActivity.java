package com.geektech.databaselesson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.geektech.databaselesson.sqlite.BeerDataSource;
import com.geektech.databaselesson.sqlite.BeerLocalDataSource;
import com.geektech.databaselesson.sqlite.model.Beer;
import com.geektech.databaselesson.data.SourceProvider;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BeerDataSource db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new BeerLocalDataSource(getApplicationContext());

        Beer nashe = new Beer("nashe", "KG");
        Beer arpa = new Beer("arpa", "KG");

        Beer[] newBeers = new Beer[]{nashe, arpa};

        db.addAll(newBeers);

        //Оба метода будут работать
//        db.addAll(nashe, arpa);

        Beer found = db.getById(1);
        log("Found = " + found.toString());
        found.setName("Kozel");
        db.update(found);

        List<Beer> beers = db.getAll();
        for (Beer beer : beers){
            log("List = " + beer.toString());
        }
    }

    private void log(String message){
        Log.d("ololo", message);
    }
}
