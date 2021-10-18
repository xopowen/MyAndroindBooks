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
import android.widget.Toast;

import androidx.annotation.NonNull;

public class MainActivity extends Activity {
    public int[][] arrCircle;
    int x;
    int y;
    int red;
    int green;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new DrawView(this));
        arrCircle = new int[4]
                [4];
        for (int i = 0; i < arrCircle.length; i++) {
            for (int j = 0; j < arrCircle[i].length; j++) {
                arrCircle[i][j] = (int) Math.round((Math.random() ) );
            }
        }
    }

    class DrawView extends View {

        Paint p;
        Rect rect;

        public DrawView(Context context) {
            super(context);
            p = new Paint();
            rect = new Rect();
        }
        //нарисовали на экране круги на равном расстояние
        @Override
        protected void onDraw(Canvas canvas) {
            // заливка канвы цветом
            canvas.drawARGB(80, 102, 204, 255);

            // настройка кисти
            // красный цвет
            p.setColor(Color.RED);
            // толщина линии = 10
            p.setStrokeWidth(10);
            red = 0;
            green = 0;
            for(int i = 50; i < 100*arrCircle.length+50; i += 100){
                for(int j = 50; j < 100 * arrCircle[0].length + 50; j += 100) {
                    if(arrCircle[i / 100][j / 100] != 1) {
                        arrCircle[i / 100][j / 100] = 0;
                        p.setColor(Color.RED);
                        red++;
                    }else{
                        p.setColor(Color.GREEN);
                        green++;
                    }
                    // рисуем круг с центром в (100,200), радиус = 50
                    canvas.drawCircle(i, j, 50, p);
                }
            }
            if ((green==0)||red==0) {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "ура вы выйграли", Toast.LENGTH_SHORT);
                toast.show();
            for (int i = 0; i < arrCircle.length; i++) {
                    for (int j = 0; j < arrCircle[i].length; j++) {
                        arrCircle[i][j] = (int) Math.round((Math.random() ) );
                    }
            }
            }

            invalidate();


        }

    }

    @Override
    public boolean onTouchEvent (MotionEvent event){
        //что делать, если пользователь только коснулся экрана
        if (event.getAction() == ACTION_DOWN){
            //получаем координаты точки косания
            x = (int) event.getX();
            y = (int) event.getY();
            if ((x < 100*arrCircle.length)&&(y<100*arrCircle[0].length)){
               // if((x % 100 < 50/2)||(y % 100 < 50/2)){
                    for(int i = 0; i < arrCircle.length;i+=1){
                        for(int j = 0; j < arrCircle[i].length;j++) {
                            if(arrCircle[i][j] != 1) {
                                if((x)/100 == i){
                                    arrCircle[i][j] = 1;
                                }
                                if((y)/100 == j){
                                    arrCircle[i][j] = 1;
                                }
                            }else {
                                if((x)/100 ==i ){
                                    arrCircle[i][j] = 0;
                                }
                                if((y)/100==j){
                                    arrCircle[i][j] = 0;
                                }
                            }
                          }
                        }

                }
        }

        return true;
    }

}
/*                if((x % 100 < 50/2)||(y % 100 < 50/2)){
                    if(arrCircle[(int)x / 100][(int)y / 100] != 1){
                        arrCircle[(int)x / 100][(int)y / 100] = 1;
                    }else{
                        arrCircle[(int)x / 100][(int)y / 100] = 0;
                    }
                }
            }*/