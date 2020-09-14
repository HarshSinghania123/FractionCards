package io.focusminds.fractioncards;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivityPlay extends AppCompatActivity {

    TextView numTxt1, numTxt2, numTxt3, numTxt4, scoreTxt, timerTxt, finishScoreTxt, finishCloseBtn, outCloseBtn, warningTxt, resultTxt;
    ImageView cardImg1, cardImg2, cardImg3, cardImg4, closeBtn;
    Button startBtn, ansBtn;
    List<String> numbersList = new ArrayList<>();
    EditText numeratorTxt, denomTxt;
    AnimatorSet set;
    Random rand;
    int score= 10;
    double btn1Value = 0.00, btn2Value = 0.00, btn3Value = 0.00, btn4Value = 0.00, usrResult = 0.00, calcResult =0.00;
    boolean btn1Clicked = false, btn2Clicked = false, btn3Clicked = false, btn4Clicked = false, jokerImpl = false;
    CountDownTimer timer;
    List<Integer> randnumsarr = new ArrayList<>(4);
    String val1= "", val2 ="";
    int i =0, randnum=0, numerator =0, denom =0;
    Runnable r, r2;
    Handler handler2, handler;
    int counter = 120, selectCount = 0;
    MediaPlayer btnClkSnd;

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
        closeBtn = findViewById(R.id.exit_icon);
        handler2 = new Handler();
        handler = new Handler();
        warningTxt = findViewById(R.id.warning_txt);
        resultTxt = findViewById(R.id.result_txt);
        btnClkSnd = MediaPlayer.create(this, R.raw.btn_clk_snd);
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


//        *****************************Close Button click******************************
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnClkSnd.start();
                popupDialogCreation("finish", scoreTxt.getText().toString());
            }
        });

//        ***************************setting values to the cards on start click************
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btnClkSnd.start();

                btn1Clicked = false;
                btn2Clicked = false;
                btn3Clicked = false;
                btn4Clicked = false;

                selectCount = 0;
                counter = 120;
                resultTxt.setText("");

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

                rotations("clockwise", cardImg1);
                rotations("clockwise", cardImg2);
                rotations("clockwise", cardImg3);
                rotations("clockwise", cardImg4);

            }
        });

        numTxt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnClkSnd.start();
                warningTxt.setVisibility(View.INVISIBLE);
                if(btn1Clicked){
                    selectCount --;
                    btn1Clicked = false;
                    btn1Value = 0.00;
                    cardImg1.setBackgroundResource(R.drawable.heart);
                }else {
                    selectCount ++;
                    String btnCaption = numTxt1.getText().toString();
                    btn1Value = parse(btnCaption);
                    btn1Clicked = true;
                    cardImg1.setBackgroundResource(R.drawable.heart_selected);
                }
                resultTxt.setText("");
            }
        });

        numTxt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnClkSnd.start();
                warningTxt.setVisibility(View.INVISIBLE);
                if(btn2Clicked){
                    selectCount --;
                    btn2Clicked = false;
                    btn2Value = 0.00;
                    cardImg2.setBackgroundResource(R.drawable.clover);
                }else {
                    selectCount ++;
                    String btnCaption = numTxt2.getText().toString();
                    btn2Value = parse(btnCaption);
                    btn2Clicked = true;
                    cardImg2.setBackgroundResource(R.drawable.clover_selected);
                }
                resultTxt.setText("");
            }
        });

        numTxt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnClkSnd.start();
                warningTxt.setVisibility(View.INVISIBLE);
                if (btn3Clicked) {
                    selectCount--;
                    btn3Clicked = false;
                    btn3Value = 0.00;
                    cardImg3.setBackgroundResource(R.drawable.spade);
                } else {
                    selectCount++;
                    String btnCaption = numTxt3.getText().toString();
                    btn3Value = parse(btnCaption);
                    btn3Clicked = true;
                    cardImg3.setBackgroundResource(R.drawable.spade_selected);
                }
                resultTxt.setText("");
            }
        });

        numTxt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnClkSnd.start();
                warningTxt.setVisibility(View.INVISIBLE);
                if(btn4Clicked){
                    selectCount --;
                    btn4Clicked = false;
                    btn4Value = 0.00;
                    cardImg4.setBackgroundResource(R.drawable.diamond);
                }else {
                    selectCount ++;
                    String btnCaption = numTxt4.getText().toString();
                    btn4Value = parse(btnCaption);
                    btn4Clicked = true;
                    cardImg4.setBackgroundResource(R.drawable.diamond_selected);
                }
                resultTxt.setText("");
            }
        });

        ansBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnClkSnd.start();
                if(selectCount % 2 != 0 || selectCount == 0){
                    warningTxt.setVisibility(View.VISIBLE);
                }
                else {
                    if (!((numeratorTxt.getText().toString().equals("")) || (denomTxt.getText().toString().equals("")) ||
                            (numeratorTxt.getText().toString().equals(" ")) || (denomTxt.getText().toString().equals(" ")) || (selectCount % 2 != 0) || (selectCount == 0))) {  // validation for empty edit text
                        numerator = Integer.parseInt(numeratorTxt.getText().toString());
                        denom = Integer.parseInt(denomTxt.getText().toString());
                        usrResult = (double) numerator / denom;
                        calcResult = btn1Value + btn2Value + btn3Value + btn4Value;
                        val1 = String.format("%.4f", usrResult);
                        val2 = String.format("%.4f", calcResult);
                        if (val1.equalsIgnoreCase(val2)) {
                            score += 1;
                            scoreTxt.setText("" + score);
                            resultTxt.setText("Correct");


                            if (!(btn1Value == 0.00)) {
                                numTxt1.setText("");
                                rotations("anti-clockwise", cardImg1);
                                btn1Value = 0.00;
                                selectCount--;
                            }
                            if (!(btn2Value == 0.00)) {
                                numTxt2.setText("");
                                rotations("anti-clockwise", cardImg2);
                                btn2Value = 0.00;
                                selectCount--;
                            }
                            if (!(btn3Value == 0.00)) {
                                numTxt3.setText("");
                                rotations("anti-clockwise", cardImg3);
                                btn3Value = 0.00;
                                selectCount--;
                            }
                            if (!(btn4Value == 0.00)) {
                                numTxt4.setText("");
                                rotations("anti-clockwise", cardImg4);
                                btn4Value = 0.00;
                                selectCount--;
                            }

//                        ************************Game Completed for score 50**************************
                            if (score == 20) {
                                popupDialogCreation("finish", "20");
                            }

                        } else {
                            score -= 1;
                            scoreTxt.setText("" + score);
                            resultTxt.setText("Wrong");

                            if (!(btn1Value == 0.00)) {
                                cardImg1.setBackgroundResource(R.drawable.heart);
                                btn1Clicked = false;
                                btn1Value = 0.00;
                                selectCount--;

                            }
                            if (!(btn2Value == 0.00)) {
                                cardImg2.setBackgroundResource(R.drawable.clover);
                                btn2Clicked = false;
                                btn2Value = 0.00;
                                selectCount--;
                            }
                            if (!(btn3Value == 0.00)) {
                                cardImg3.setBackgroundResource(R.drawable.spade);
                                btn3Clicked = false;
                                btn3Value = 0.00;
                                selectCount--;
                            }
                            if (!(btn4Value == 0.00)) {
                                cardImg4.setBackgroundResource(R.drawable.diamond);
                                btn4Clicked = false;
                                btn4Value = 0.00;
                                selectCount--;
                            }

//                        **********************Game Over Score 0************************
                            if (score <= 0) {
                                popupDialogCreation("out", "0");
                            }
                        }

                        numeratorTxt.setText("");
                        denomTxt.setText("");

                        if (btn1Clicked && btn2Clicked && btn3Clicked && btn4Clicked) {
                            startBtn.setVisibility(View.VISIBLE);
                            ansBtn.setVisibility(View.INVISIBLE);
                            btn1Clicked = false;
                            btn2Clicked = false;
                            btn3Clicked = false;
                            btn4Clicked = false;

                            startTimer();
                        }
                        if ((score == (rand.nextInt(5)) && !(jokerImpl))) {
                            jokerAdditionalPoints("");
                            jokerImpl = true;
                        }
                    }
                }
            }
        });

    }


//    *********************Timer for game completion******************
    public void startTimer() {

        if (timer != null) {
            timer.cancel();
            timer = null;
            timerTxt.setText("00");
            counter = 120;
            return;
        }
        timer = new CountDownTimer(120000, 1000) {

            public void onTick(long millisUntilFinished) {
                timerTxt.setText("" +(--counter ));
            }

            public void onFinish() {
                timerTxt.setText("00");
                counter = 120;
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
        timer = null;
        resultTxt.setText("");
        warningTxt.setVisibility(View.INVISIBLE);

        rotations("anti-clockwise", cardImg1);
        rotations("anti-clockwise", cardImg2);
        rotations("anti-clockwise", cardImg3);
        rotations("anti-clockwise", cardImg4);
        btn1Value = 0.00;
        btn2Value = 0.00;
        btn3Value = 0.00;
        btn4Value = 0.00;
        btn1Clicked = false;
        btn2Clicked = false;
        btn3Clicked = false;
        btn4Clicked = false;

    }

//    *******************rotations done for cards*************************
    public void rotations(String direction, final ImageView imageView){

        if(direction.equals("clockwise")){
            set = (AnimatorSet) AnimatorInflater.loadAnimator(MainActivityPlay.this,R.animator.clockwise_flip);


            r= new Runnable() {
                @Override
                public void run() {
                    if(imageView == cardImg1)
                    imageView.setBackgroundResource(R.drawable.heart);
                    else if(imageView == cardImg2)
                    imageView.setBackgroundResource(R.drawable.clover);
                    else if(imageView == cardImg3)
                    imageView.setBackgroundResource(R.drawable.spade);
                    else if(imageView == cardImg4)
                    imageView.setBackgroundResource(R.drawable.diamond);

                }
            };
            handler2.postDelayed(r,500);

//            **************assigning visiblity to text while rotation/**************
//            Handler handler = new Handler();
//            handler.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    numTxt1.setVisibility(View.VISIBLE);
//                    numTxt2.setVisibility(View.VISIBLE);
//                    numTxt3.setVisibility(View.VISIBLE);
//                    numTxt4.setVisibility(View.VISIBLE);
//                }
//            },1000);

            r2= new Runnable() {
                @Override
                public void run() {
                    numTxt1.setVisibility(View.VISIBLE);
                    numTxt2.setVisibility(View.VISIBLE);
                    numTxt3.setVisibility(View.VISIBLE);
                    numTxt4.setVisibility(View.VISIBLE);
                }
            };
            handler.postDelayed(r2,1000);

        }
        else if(direction.equals("anti-clockwise")){
            set = (AnimatorSet) AnimatorInflater.loadAnimator(MainActivityPlay.this,R.animator.anti_clockwise_flip);
            Handler handler2 = new Handler();
            handler2.postDelayed(new Runnable() {
                @Override
                public void run() {
                    imageView.setBackgroundResource(R.drawable.front_image);
                }
            },500);

        }
        set.setTarget(imageView);
        set.start();

    }


//    ************Creating Win/Out popup**********************
    public void popupDialogCreation(String state, String score){
        final Dialog popupDialog = new Dialog(MainActivityPlay.this);
        popupDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        popupDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                finish();
                btnClkSnd.stop();
            }
        });
        if(state.equals("finish")){
            popupDialog.setContentView(R.layout.activity_finish_popup);
            finishScoreTxt = popupDialog.findViewById(R.id.finish_score_txt);
            finishCloseBtn = popupDialog.findViewById(R.id.finish_close_btn);
            finishCloseBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    popupDialog.dismiss();
                }
            });
            finishScoreTxt.setText((score));
        }
        else if(state.equals("out")){
            popupDialog.setContentView(R.layout.activity_out_popup);
            outCloseBtn = popupDialog.findViewById(R.id.out_close_btn);
            outCloseBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    popupDialog.dismiss();
                }
            });
        }
        popupDialog.show();
    }


//  ************************implementation of jokers********************
    public void jokerAdditionalPoints(String range){
        score++;
        scoreTxt.setText(""+score);
        final Dialog jokerPopup = new Dialog(MainActivityPlay.this);
        jokerPopup.requestWindowFeature(Window.FEATURE_NO_TITLE);
        jokerPopup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        jokerPopup.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {

            }
        });
        jokerPopup.setContentView(R.layout.activity_joker_view);
        jokerPopup.show();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                jokerPopup.dismiss();
            }
        }, 3000);
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
    @Override
    protected void onStop(){
        handler2= null;
        handler = null;
        super.onStop();
    }
    @Override
    protected  void onPause() {
        super.onPause();
    }
}