package com.geektech.databaselesson;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.geektech.databaselesson.data.beer.BeerSourceContract;
import com.geektech.databaselesson.data.beer.model.BeerEntity;
import com.geektech.databaselesson.sqlite.BeerDataSource;
import com.geektech.databaselesson.sqlite.BeerLocalDataSource;
import com.geektech.databaselesson.sqlite.model.Beer;
import com.geektech.databaselesson.data.SourceProvider;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BeerDataSource sqlDb;
    private BeerSourceContract roomDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sqlDb = new BeerLocalDataSource(getApplicationContext());
        roomDb = SourceProvider.getBeerSource(getApplicationContext());

        checkSQLite();

        checkRoom();
    }

    private void checkRoom(){
        log("Start Room check");
        BeerEntity nashe = new BeerEntity("nashe", "KG");
        BeerEntity arpa = new BeerEntity("arpa", "KG");

        BeerEntity[] newBeers = new BeerEntity[]{nashe, arpa};

        roomDb.save(newBeers);

        //Так как запись в Базу идет асинхронно ждем пока выполнится
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                fetchRoomData();
            }
        }, 300);
    }

    private void fetchRoomData(){
        //При возвращении результата из callback, мы уже будем не на основом потоке
        //Если вы как либо попытаетесь поменять любую View или что либо из основного потока
        //вылетел ошибка
        roomDb.get( 1, new BeerSourceContract.BeerCallback() {
                    @Override
                    public void onSuccess(final BeerEntity result) {
                        log("Found = " + result.toString());
                        result.setName("Kozel");
                        roomDb.save(result);

                        //Данный метод вызовет ошибку, т.к. мы на другом потоке
                        //setTitle("Test set");

                        //Таким образом можно прыгнуть на основной поток
                        /*runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                setTitle(result.getName());
                            }
                        });*/
                    }
                }
        );

        roomDb.getAll( new BeerSourceContract.BeerListCallback() {
            @Override
            public void onSuccess(List<BeerEntity> result) {
                log("List start");
                for (BeerEntity beer : result){
                    log(beer.toString());
                }
                log("end");
                log("Room check finish");
            }
        });
    }

    private void checkSQLite(){
        log("Start SQLite check");
        Beer nashe = new Beer("nashe", "KG");
        Beer arpa = new Beer("arpa", "KG");

        Beer[] newBeers = new Beer[]{nashe, arpa};

        sqlDb.addAll(newBeers);

        //Оба метода будут работать
//        db.addAll(nashe, arpa);

        Beer found = sqlDb.getById(1);
        log("Found = " + found.toString());
        found.setName("Kozel");
        sqlDb.update(found);

        List<Beer> savedBeers = sqlDb.getAll();
        log("List start");
        for (Beer beer : savedBeers){
            log(beer.toString());
        }
        log("end");
        log("SQLite check finish");
    }

    private void log(String message){
        Log.d("ololo", message);
    }
}
