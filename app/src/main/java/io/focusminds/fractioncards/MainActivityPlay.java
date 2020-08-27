package io.focusminds.fractioncards;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivityPlay extends AppCompatActivity {

    TextView numTxt1, numTxt2, numTxt3, numTxt4, scoreTxt, timerTxt;
    ObjectAnimator animation, animation2;
//    ImageView reduceScoreImg, addScoreImg;
    ImageView cardImg1, cardImg2, cardImg3, cardImg4;
    int timerSec = 60;
    Button startBtn, ansBtn;
    List<String> numbersList = new ArrayList<>();
    EditText numeratorTxt, denomTxt;
    AnimatorSet set, set2, set3, set4;
    Animation fadeInAnim, fadeOutAnim;
    Random rand;
    static int score= 10;
    double btn1Value = 0.00, btn2Value = 0.00, btn3Value = 0.00, btn4Value = 0.00, usrResult = 0.00, calcResult =0.00;
    boolean btn1Clicked = false, btn2Clicked = false, btn3Clicked = false, btn4Clicked = false;
//    int randnumsarr[] = new int[4];
//    Fraction fraction = Fraction.getFraction("1/2");
    List<Integer> randnumsarr = new ArrayList<>(4);
    String val1= "", val2 ="";
    int i =0, randnum=0, numerator =0, denom =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_play);
        numTxt1 = findViewById(R.id.num_txt_1);
        numTxt2 = findViewById(R.id.num_txt_2);
        numTxt3 = findViewById(R.id.num_txt_3);
        numTxt4 = findViewById(R.id.num_txt_4);
        startBtn = findViewById(R.id.startBtn);
        cardImg1 = findViewById(R.id.imageView);
        cardImg2 = findViewById(R.id.imageView2);
        cardImg3 = findViewById(R.id.imageView3);
        cardImg4 = findViewById(R.id.imageView4);
        ansBtn = findViewById(R.id.ans_btn);
        numeratorTxt = findViewById(R.id.numerator_txt);
        denomTxt = findViewById(R.id.denom_txt);
        scoreTxt = findViewById(R.id.score_txt);
        timerTxt = findViewById(R.id.timer_txt);
//        reduceScoreImg = findViewById(R.id.reduce_score_star);
//        addScoreImg = findViewById(R.id.add_score_star);

        rand = new Random();

//          **********************************adding 55 elements************************
        {
            numbersList.add("1/1");
            numbersList.add("1/2");
            numbersList.add("2/2");
            numbersList.add("1/3");
            numbersList.add("2/3");
            numbersList.add("3/3");
            numbersList.add("1/4");
            numbersList.add("2/4");
            numbersList.add("3/4");
            numbersList.add("4/4");
            numbersList.add("1/5");
            numbersList.add("2/5");
            numbersList.add("3/5");
            numbersList.add("4/5");
            numbersList.add("5/5");
            numbersList.add("1/6");
            numbersList.add("2/6");
            numbersList.add("3/6");
            numbersList.add("4/6");
            numbersList.add("5/6");
            numbersList.add("6/6");
            numbersList.add("1/7");
            numbersList.add("2/7");
            numbersList.add("3/7");
            numbersList.add("4/7");
            numbersList.add("5/7");
            numbersList.add("6/7");
            numbersList.add("7/7");
            numbersList.add("1/8");
            numbersList.add("2/8");
            numbersList.add("3/8");
            numbersList.add("4/8");
            numbersList.add("5/8");
            numbersList.add("6/8");
            numbersList.add("7/8");
            numbersList.add("8/8");
            numbersList.add("1/9");
            numbersList.add("2/9");
            numbersList.add("3/9");
            numbersList.add("4/9");
            numbersList.add("5/9");
            numbersList.add("6/9");
            numbersList.add("7/9");
            numbersList.add("8/9");
            numbersList.add("9/9");
            numbersList.add("1/10");
            numbersList.add("2/10");
            numbersList.add("3/10");
            numbersList.add("4/10");
            numbersList.add("5/10");
            numbersList.add("6/10");
            numbersList.add("7/10");
            numbersList.add("8/10");
            numbersList.add("9/10");
            numbersList.add("10/10");
        }



//        ***************************setting values to the cards on start click************
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btn1Clicked = false;
                btn2Clicked = false;
                btn3Clicked = false;
                btn4Clicked = false;
                numTxt1.setTextColor(Color.BLACK);
                numTxt2.setTextColor(Color.BLACK);
                numTxt3.setTextColor(Color.BLACK);
                numTxt4.setTextColor(Color.BLACK);
                startBtn.setVisibility(View.INVISIBLE);
                ansBtn.setVisibility(View.VISIBLE);

                startTimer();


//              ***************************Randomization of the list***********************
                Collections.shuffle(numbersList);



//               *************Creating a index list for randomization**************
                while( i < 4)
                {
                    randnum = rand.nextInt(55);
                    if(!(randnumsarr.contains(randnum)))
                    {
                        randnumsarr.add(randnum);
                        i++;
                    }
                }

//                **************************assigning fractions to textview******************************
                numTxt1.setText(numbersList .get(randnumsarr.get(0)));
                numTxt2.setText(numbersList .get(randnumsarr.get(1)));
                numTxt3.setText(numbersList .get(randnumsarr.get(2)));
                numTxt4.setText(numbersList .get(randnumsarr.get(3)));

//                Handler handler = new Handler();
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        numTxt1.setVisibility(View.VISIBLE);
//                        numTxt2.setVisibility(View.VISIBLE);
//                        numTxt3.setVisibility(View.VISIBLE);
//                        numTxt4.setVisibility(View.VISIBLE);
//                    }
//                },1000);

//                Handler handler2 = new Handler();
//                handler2.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        cardImg1.setImageResource(R.drawable.mirror_image);
//                    }
//                },500);


                rotations("clockwise", cardImg1);
                rotations("clockwise", cardImg2);
                rotations("clockwise", cardImg3);
                rotations("clockwise", cardImg4);

//                set1 = (AnimatorSet) AnimatorInflater.loadAnimator(MainActivityPlay.this,R.animator.clockwise_flip);
//                set2 = (AnimatorSet) AnimatorInflater.loadAnimator(MainActivityPlay.this,R.animator.clockwise_flip);
//                set3 = (AnimatorSet) AnimatorInflater.loadAnimator(MainActivityPlay.this,R.animator.clockwise_flip);
//                set4 = (AnimatorSet) AnimatorInflater.loadAnimator(MainActivityPlay.this,R.animator.clockwise_flip);
//                set1.setTarget(cardImg1);
//                set1.start();
//                set2.setTarget(cardImg2);
//                set2.start();
//                set3.setTarget(cardImg3);
//                set3.start();
//                set4.setTarget(cardImg4);
//                set4.start();
            }
        });

        numTxt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btn1Clicked){
                    btn1Clicked = false;
                    btn1Value = 0.00;
                    numTxt1.setTextColor(Color.BLACK);
                }else {
                    String btnCaption = numTxt1.getText().toString();
                    btn1Value = parse(btnCaption);
                    btn1Clicked = true;
                    numTxt1.setTextColor(Color.RED);

                }
            }
        });

        numTxt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btn2Clicked){
                    btn2Clicked = false;
                    btn2Value = 0.00;
                    numTxt2.setTextColor(Color.BLACK);
                }else {
                    String btnCaption = numTxt2.getText().toString();
                    btn2Value = parse(btnCaption);
                    btn2Clicked = true;
                    numTxt2.setTextColor(Color.RED);
                }
            }
        });

        numTxt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btn3Clicked){
                    btn3Clicked = false;
                    btn3Value = 0.00;
                    numTxt3.setTextColor(Color.BLACK);
                }else {
                    String btnCaption = numTxt3.getText().toString();
                    btn3Value = parse(btnCaption);
                    btn3Clicked = true;
                    numTxt3.setTextColor(Color.RED);
                }
            }
        });

        numTxt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btn4Clicked){
                    btn4Clicked = false;
                    btn4Value = 0.00;
                    numTxt4.setTextColor(Color.BLACK);
                }else {
                    String btnCaption = numTxt4.getText().toString();
                    btn4Value = parse(btnCaption);
                    btn4Clicked = true;
                    numTxt4.setTextColor(Color.RED);
                }
            }
        });

        ansBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(!((numeratorTxt.getText().toString().equals("")) ||  (denomTxt.getText().toString().equals("")) || (numeratorTxt.getText().toString().equals(" ")) ||  (denomTxt.getText().toString().equals(" ")))){  // validation for empty edit text
                    numerator = Integer.parseInt(numeratorTxt.getText().toString());
                    denom = Integer.parseInt(denomTxt.getText().toString());
                    usrResult = (double) numerator/denom;
                    calcResult = btn1Value + btn2Value + btn3Value + btn4Value;
                    val1 = String.format("%.4f",usrResult);
                    val2 = String.format("%.4f",calcResult);
                    if(val1.equalsIgnoreCase(val2)){
                        Toast.makeText(getApplicationContext(), "Correct Answer", Toast.LENGTH_SHORT).show();
                        score += 1;
                        scoreTxt.setText(""+score);
//                        fadeOutAnim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_out);
//                        animation = ObjectAnimator.ofFloat(addScoreImg,"translationY", 80f);
//                        animation.setDuration(2000);
//                        animation.start();
//                        addScoreImg.startAnimation(fadeOutAnim);
//                        Handler handler2 = new Handler();
//                        handler2.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                addScoreImg.setVisibility(View.INVISIBLE);
//                            }
//                        },2000);

                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Wrong Answer C:"+ val2 + "  U:"+ val1, Toast.LENGTH_LONG).show();
                        score -=1;
                        scoreTxt.setText(""+score);
//                        fadeInAnim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
//                        animation2 = ObjectAnimator.ofFloat(reduceScoreImg,"translationY", 70f);
//                        animation2.setDuration(2000);
//                        animation2.start();
//                        reduceScoreImg.startAnimation(fadeInAnim);
//                        Handler handler2 = new Handler();
//                        handler2.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                reduceScoreImg.setVisibility(View.INVISIBLE);
//                            }
//                        },2000);
                    }

                    numeratorTxt.setText("");
                    denomTxt.setText("");
                    if(!(btn1Value == 0.00)){
                        numTxt1.setText("");
                        rotations("anti-clockwise", cardImg1);
//                        set1 = (AnimatorSet) AnimatorInflater.loadAnimator(MainActivityPlay.this,R.animator.anti_clockwise_flip);
//                        set1.setTarget(cardImg1);
//                        set1.start();
//                        Handler handler2 = new Handler();
//                        handler2.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                cardImg1.setImageResource(R.drawable.ic_launcher_background);
//                            }
//                        },500);

                        btn1Value = 0.00;
                    }
                    if(!(btn2Value == 0.00)){
                        numTxt2.setText("");
                        rotations("anti-clockwise", cardImg2);
//                        set2 = (AnimatorSet) AnimatorInflater.loadAnimator(MainActivityPlay.this,R.animator.anti_clockwise_flip);
//                        set2.setTarget(cardImg2);
//                        set2.start();
//                        Handler handler2 = new Handler();
//                        handler2.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                cardImg2.setImageResource(R.drawable.ic_launcher_background);
//                            }
//                        },500);
                        btn2Value = 0.00;
                    }
                    if(!(btn3Value == 0.00)){
                        numTxt3.setText("");
                        rotations("anti-clockwise", cardImg3);
//                        set3 = (AnimatorSet) AnimatorInflater.loadAnimator(MainActivityPlay.this,R.animator.anti_clockwise_flip);
//                        set3.setTarget(cardImg3);
//                        set3.start();
//                        Handler handler2 = new Handler();
//                        handler2.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                cardImg3.setImageResource(R.drawable.ic_launcher_background);
//                            }
//                        },500);
                        btn3Value = 0.00;
                    }
                    if(!(btn4Value == 0.00)){
                        numTxt4.setText("");
                        rotations("anti-clockwise", cardImg4);
//                        set4 = (AnimatorSet) AnimatorInflater.loadAnimator(MainActivityPlay.this,R.animator.anti_clockwise_flip);
//                        set4.setTarget(cardImg4);
//                        set4.start();
//                        Handler handler2 = new Handler();
//                        handler2.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                cardImg4.setImageResource(R.drawable.ic_launcher_background);
//                            }
//                        },500);
                        btn4Value = 0.00;
                    }

                    if(btn1Clicked && btn2Clicked && btn3Clicked && btn4Clicked)
                    {
                        startBtn.setVisibility(View.VISIBLE);
                        ansBtn.setVisibility(View.INVISIBLE);




                    }
                }
            }
        });

    }


//    *********************Timer for game completion******************
    public void startTimer(){

        new CountDownTimer(120000, 1000) {

            public void onTick(long millisUntilFinished) {
                timerTxt.setText(""+(millisUntilFinished/1000));
            }

            public void onFinish() {
                timerTxt.setText("00");
                timesUp();
            }
        }.start();
    }


//  ***************When timer completes*****************************
    public void timesUp (){
        numeratorTxt.setText("");
        denomTxt.setText("");
        startBtn.setVisibility(View.VISIBLE);
        ansBtn.setVisibility(View.INVISIBLE);
        numTxt1.setText("");
        numTxt2.setText("");
        numTxt3.setText("");
        numTxt4.setText("");

        rotations("anti-clockwise", cardImg1);
        rotations("anti-clockwise", cardImg2);
        rotations("anti-clockwise", cardImg3);
        rotations("anti-clockwise", cardImg4);
        btn1Value = 0.00;
        btn2Value = 0.00;
        btn3Value = 0.00;
        btn4Value = 0.00;



    }

//    *******************rotations done for cards*************************
    public void rotations(String direction, final ImageView imageView){

        if(direction.equals("clockwise")){
            set = (AnimatorSet) AnimatorInflater.loadAnimator(MainActivityPlay.this,R.animator.clockwise_flip);

//            ********************Image assigning while rotation
            Handler handler2 = new Handler();
            handler2.postDelayed(new Runnable() {
                @Override
                public void run() {
                    imageView.setImageResource(R.drawable.mirror_image);
                }
            },500);

//            **************assigning visiblity to text while rotation/**************
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    numTxt1.setVisibility(View.VISIBLE);
                    numTxt2.setVisibility(View.VISIBLE);
                    numTxt3.setVisibility(View.VISIBLE);
                    numTxt4.setVisibility(View.VISIBLE);
                }
            },1000);

        }
        else if(direction.equals("anti-clockwise")){
            set = (AnimatorSet) AnimatorInflater.loadAnimator(MainActivityPlay.this,R.animator.anti_clockwise_flip);
            Handler handler2 = new Handler();
            handler2.postDelayed(new Runnable() {
                @Override
                public void run() {
                    imageView.setImageResource(R.drawable.ic_launcher_background);
                }
            },500);

        }
        set.setTarget(imageView);
        set.start();

    }





//  *********************** String to double conversion**************
    double parse(String ratio) {
        if (ratio.contains("/")) {
            String[] rat = ratio.split("/");
            return Double.parseDouble(rat[0]) / Double.parseDouble(rat[1]);
        } else {
            return Double.parseDouble(ratio);
        }
    }
}