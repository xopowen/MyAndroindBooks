package com.example.myapplication;

import static android.service.autofill.Validators.and;
import static android.view.KeyEvent.ACTION_DOWN;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class MainActivity extends Activity {
    ListView bookList;
    ListView townList;
    ArrayList<String> townArrayList;
    //ArrayAdapter<String> adapter;
    LinkedList<HashMap<String,Object>> library;
    SimpleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bottom = (Button)findViewById(R.id.bottomButton);


        bookList = findViewById(R.id.mainlist);
        //шак 1
        LinkedList<Book> myBooks = new LinkedList<>();
        myBooks.add(new Book("A.Aзимов","Основание",R.drawable.osnovanie));
        myBooks.add(new Book("A.Aзимов","Шинель",R.drawable.prestuplenie));
        myBooks.add(new Book("A.Aзимов","Зерцалия",R.drawable.shinel));
        myBooks.add(new Book("A.Aзимов","Основание",R.drawable.zertsalia));
        myBooks.add(new Book("A.Aзимов","Основание",0));
        myBooks.add(new Book("A.Aзимов","Основание",0));

        //шак 2
        library = new LinkedList<>();

        for(int i=0; i< myBooks.size(); i++){
            HashMap<String,Object> book = new HashMap<>();
            book.put("author", myBooks.get(i).author);
            book.put("name",myBooks.get(i).name);
            book.put("cover",myBooks.get(i).cover);
            library.add(book);
        }
        //шак 3 подготовка массивов from and to
        String[] from = {"author","name","cover"};
        int [] to     = {R.id.author,R.id.name,R.id.cover};

        //шак 4 создание адаптора
        adapter = new SimpleAdapter(
                this,library,R.layout.list_item,from,to);

        bookList.setAdapter(adapter);
        //создать список городов
        //townArrayList = new ArrayList<>();
        //townArrayList.add("Иркуткс");

        //создание адаптора
        //adapter = new ArrayAdapter<>(this,
                //R.layout.list_item,townArrayList);


       // townList.setAdapter(adapter);

       // townList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          //  @Override
          //  public void onItemClick(AdapterView<?> parent, View view, int i,long l){
            //    Toast.makeText(getApplicationContext(),"You are pressed on:"+i+"/ l=" + townArrayList.get(i),
            //            Toast.LENGTH_SHORT).show();
          //  }
   //     });

        //добавить поле ввотда и кнопку при нажатии которого добавиться в списко



    }
    public void onMyButtonClick(View view ){
        EditText strAuthon = findViewById(R.id.inputAuthon);
        EditText strName =findViewById(R.id.inputName);
        if( strAuthon.getText() != null && strName.getText() != null){
            Book addBook = new Book(strAuthon.getText().toString(),strName.getText().toString(),0);
            HashMap<String,Object> book = new HashMap<>();
            book.put("author", addBook.author);
            book.put("name",addBook.name);
            book.put("cover",addBook.cover);
            library.add(book);
            adapter.notifyDataSetChanged();
    }
    }

}
