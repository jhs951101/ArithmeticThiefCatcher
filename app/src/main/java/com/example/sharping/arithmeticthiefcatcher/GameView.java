package com.example.sharping.arithmeticthiefcatcher;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import java.util.ArrayList;

/**
 * Created by SharPing on 2017-05-09.
 */

public class GameView extends View {

    public static int WIDTH;
    public static int HEIGHT;

    public static Flag gameFlagClass;

    public static short gameFlag;
    /*
    0: 게임 미시작
    1: 승부 미판정
    2: 승리
    3: 패배
    */

    /*
    0: player
    1: thief
    2: general
    */
    public static final int[][] NUMBER_IDS = {{R.drawable.playernumber0, R.drawable.playernumber1, R.drawable.playernumber2, R.drawable.playernumber3, R.drawable.playernumber4,
            R.drawable.playernumber5, R.drawable.playernumber6, R.drawable.playernumber7, R.drawable.playernumber8, R.drawable.playernumber9},
            {R.drawable.thiefnumber0, R.drawable.thiefnumber1, R.drawable.thiefnumber2, R.drawable.thiefnumber3, R.drawable.thiefnumber4,
            R.drawable.thiefnumber5, R.drawable.thiefnumber6, R.drawable.thiefnumber7, R.drawable.thiefnumber8, R.drawable.thiefnumber9},
            {R.drawable.char0, R.drawable.char1, R.drawable.char2, R.drawable.char3, R.drawable.char4,
            R.drawable.char5, R.drawable.char6, R.drawable.char7, R.drawable.char8, R.drawable.char9}};

    public static final int[] GAME_MODE_IDS = {R.drawable.stringmorethan, R.drawable.stringlessthan, R.drawable.stringplus, R.drawable.stringminus, R.drawable.stringproduct};

    public static final int ALPABET_a_IDS = R.drawable.chara;
    public static final int ALPABET_c_IDS = R.drawable.charc;
    public static final int ALPABET_e_IDS = R.drawable.chare;
    public static final int ALPABET_g_IDS = R.drawable.charg;
    public static final int ALPABET_i_IDS = R.drawable.chari;
    public static final int ALPABET_m_IDS = R.drawable.charm;
    public static final int ALPABET_o_IDS = R.drawable.charo;
    public static final int ALPABET_r_IDS = R.drawable.charr;
    public static final int ALPABET_s_IDS = R.drawable.chars;
    public static final int ALPABET_t_IDS = R.drawable.chart;
    public static final int CHAR_BLANK_IDS = R.drawable.charblank;
    public static final int CHAR_MINUS_IDS = R.drawable.charminus;
    public static final int CHAR_DANGDANG_IDS = R.drawable.chardangdang;
    public static final int CHAR_PERCENTAGE_IDS = R.drawable.charpercentage;

    public static Bitmap[][] IMG_NUMBERS = new Bitmap[3][10];  // [player or thief][number]
    public static Bitmap[] IMG_GAME_MODES = new Bitmap[5];

    public static Bitmap gameModeState;
    public static Bitmap IMG_ALPABET_a;
    public static Bitmap IMG_ALPABET_c;
    public static Bitmap IMG_ALPABET_e;
    public static Bitmap IMG_ALPABET_g;
    public static Bitmap IMG_ALPABET_i;
    public static Bitmap IMG_ALPABET_m;
    public static Bitmap IMG_ALPABET_o;
    public static Bitmap IMG_ALPABET_r;
    public static Bitmap IMG_ALPABET_s;
    public static Bitmap IMG_ALPABET_t;
    public static Bitmap IMG_CHAR_BLANK;
    public static Bitmap IMG_CHAR_MINUS;
    public static Bitmap IMG_CHAR_DANGDANG;
    public static Bitmap IMG_CHAR_PERCENTAGE;

    private MainActivity activity;
    private GameManager manager;
    private GamePlayTimer gamePlayTimer;

    private Player player;
    private ArrayList<Thief> thiefs;

    private ImageString imgStage;
    private ImageString imgRemainTime;
    private ImageString imgGameScore;
    
    private ImageString imgNumOfCorrect;
    private ImageString imgCurrentStamina;
    private ImageString imgBonusScore;
    private ImageString imgTotalScore;

    private BackgroundMusic introMusic;
    private BackgroundMusic gameOverMusic;
    private BackgroundMusic gameClearMusic;

    private SoundEffect btnReleasedEffect;
    private SoundEffect shootEffect;
    private SoundEffect releasedEffectState;

    private Bitmap popupBackColor;
    private Bitmap damagedBackColor;
    private DamageBackColorShow damagedBackColorShow;

    private Bitmap txtGameTitle;
    private Position txtGameTitlePosition;
    private Length txtGameTitleLength;

    private Bitmap btnStart;
    private Position btnStartPosition;
    private Length btnStartLength;

    private Bitmap btnExit;
    private Position btnExitPosition;
    private Length btnExitLength;

    private Bitmap[] btnGameModes = new Bitmap[5];
    private Position[] btnGameModePositions = new Position[5];
    private Length btnGameModeLength;
    private int[] btnGameModeIds = {R.drawable.btnmorethan, R.drawable.btnlessthan, R.drawable.btnplus, R.drawable.btnminus, R.drawable.btnproduct};

    private Bitmap[] btnStages = new Bitmap[5];
    private Position[] btnStagePositions = new Position[5];
    private Length btnStageLength;

    private int[] btnStageIds = {R.drawable.btnstage1, R.drawable.btnstage2, R.drawable.btnstage3, R.drawable.btnstage4, R.drawable.btnstage5};
    private int[] btnStageDisabledIds = {R.drawable.btnstage2disabled, R.drawable.btnstage2disabled, R.drawable.btnstage3disabled, R.drawable.btnstage4disabled, R.drawable.btnstage5disabled};
    private boolean[][] stageAvailable = new boolean[5][5];

    private Bitmap btnBack;
    private Position btnBackPosition;
    private Length btnBackLength;

    private Bitmap txtSelectGameMode;
    private Bitmap txtSelectStage;

    private Bitmap btnNextStage;
    private Position btnNextStagePosition;
    private Length btnNextStageLength;

    private Bitmap popupBtnConfirm;
    private Position popupBtnConfirmPosition;
    private Length popupBtnConfirmLength;

    private Bitmap popupBtnCancel;
    private Position popupBtnCancelPosition;
    private Length popupBtnCancelLength;

    private Bitmap popupExit;
    private Bitmap popupGameStart;
    private Bitmap popupGameOver;
    private Position popupExitPosition;

    private Bitmap heartIcon;
    private Position heartIconPosition;
    private Length heartIconLength;

    private Bitmap playerBoard;
    private Position playerBoardPosition;
    private Length playerBoardLength;

    private Bitmap txtStageClear;
    private Position txtStageClearPosition;

    private Bitmap txtGameScore;
    private Position txtGameScorePosition;
    private Length txtGameScoreLength;

    private Bitmap txtNumOfCorrect;
    private Position txtNumOfCorrectPosition;
    private Length txtNumOfCorrectLength;

    private Bitmap txtCurrentStamina;
    private Position txtCurrentStaminaPosition;
    private Length txtCurrentStaminaLength;

    private Bitmap txtBonusScore;
    private Position txtBonusScorePosition;
    private Length txtBonusScoreLength;

    private Bitmap txtTotalScore;
    private Position txtTotalScorePosition;
    private Length txtTotalScoreLength;

    private Bitmap hit;
    private Bitmap hitDamaged;
    private Length hitLength;

    private ArrayList<TouchPosition> touches;
    private DamageEffectTimer damageEffectTimer;

    private int MAX_HEALTH_LENGTH;
    private boolean setLength = false;

    private double MAX_HEALTH;
    private int currentScore;
    private int numOfCorrect;
    private long lastTime;

    private int selectedGameMode;
    /*
    1: 잡아라! 크다왕
    2: 잡아라! 작다왕
    3: 잡아라! 덧셈왕
    4: 잡아라! 뺄셈왕
    5: 잡아라! 곱셈왕
    */

    private int selectedStage;
    private boolean popupShowed;

    private int screenNumber = 1;
    /*
    1: 홈 화면
    2: 게임 모드 선택 화면
    3: 스테이지 선택 화면
    4: 게임 진행 화면
    */

    private boolean open1;
    private boolean open2;

    public GameView(MainActivity a, Context context) {
        super(context);
        this.activity = a;
        setView(context);
    }

    public GameView(MainActivity a, Context context, AttributeSet attrs) {
        super(context, attrs);
        this.activity = a;
        setView(context);
    }

    public void setStageButtonsState(){
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                if(i >= 2){
                    stageAvailable[i][j] = false;
                }
                else{
                    if(j >= 1)
                        stageAvailable[i][j] = false;
                    else
                        stageAvailable[i][j] = true;
                }
            }
        }
    }

    public void setGameModeButtons(){
        for(int i=0; i<5; i++)
            btnGameModes[i] = BitmapFactory.decodeResource(getResources(), btnGameModeIds[i]);
    }

    public void setStageButtons(int x){
        for(int i=0; i<5; i++){
            if(stageAvailable[x][i] == true)
                btnStages[i] = BitmapFactory.decodeResource(getResources(), btnStageIds[i]);
            else
                btnStages[i] = BitmapFactory.decodeResource(getResources(), btnStageDisabledIds[i]);
        }
    }

    public void setImageString(){
        for(int i=0; i<3; i++){
            for(int j=0; j<10; j++){
                IMG_NUMBERS[i][j] = BitmapFactory.decodeResource(getResources(), NUMBER_IDS[i][j]);
            }
        }
        
        for(int i=0; i<5; i++) {
            IMG_GAME_MODES[i] = BitmapFactory.decodeResource(getResources(), GAME_MODE_IDS[i]);
        }

        IMG_ALPABET_a = BitmapFactory.decodeResource(getResources(), ALPABET_a_IDS);
        IMG_ALPABET_c = BitmapFactory.decodeResource(getResources(), ALPABET_c_IDS);
        IMG_ALPABET_e = BitmapFactory.decodeResource(getResources(), ALPABET_e_IDS);
        IMG_ALPABET_g = BitmapFactory.decodeResource(getResources(), ALPABET_g_IDS);
        IMG_ALPABET_i = BitmapFactory.decodeResource(getResources(), ALPABET_i_IDS);
        IMG_ALPABET_m = BitmapFactory.decodeResource(getResources(), ALPABET_m_IDS);
        IMG_ALPABET_o = BitmapFactory.decodeResource(getResources(), ALPABET_o_IDS);
        IMG_ALPABET_r = BitmapFactory.decodeResource(getResources(), ALPABET_r_IDS);
        IMG_ALPABET_s = BitmapFactory.decodeResource(getResources(), ALPABET_s_IDS);
        IMG_ALPABET_t = BitmapFactory.decodeResource(getResources(), ALPABET_t_IDS);
        IMG_CHAR_BLANK = BitmapFactory.decodeResource(getResources(), CHAR_BLANK_IDS);
        IMG_CHAR_MINUS = BitmapFactory.decodeResource(getResources(), CHAR_MINUS_IDS);
        IMG_CHAR_DANGDANG = BitmapFactory.decodeResource(getResources(), CHAR_DANGDANG_IDS);
        IMG_CHAR_PERCENTAGE = BitmapFactory.decodeResource(getResources(), CHAR_PERCENTAGE_IDS);
    }

    public void setBitmaps1(Context c){
        txtGameTitle = BitmapFactory.decodeResource(c.getResources(), R.drawable.txtlogo);
        btnStart = BitmapFactory.decodeResource(c.getResources(), R.drawable.btnstart);
        btnExit = BitmapFactory.decodeResource(c.getResources(), R.drawable.btnexit);

        btnGameModes[0] = BitmapFactory.decodeResource(c.getResources(), btnGameModeIds[0]);
        btnStages[0] = BitmapFactory.decodeResource(c.getResources(), btnStageIds[0]);
        btnBack = BitmapFactory.decodeResource(c.getResources(), R.drawable.btnback);

        txtGameScore = BitmapFactory.decodeResource(c.getResources(), R.drawable.txtgamescore);
        txtNumOfCorrect = BitmapFactory.decodeResource(c.getResources(), R.drawable.txtnumofcorrect);
        txtCurrentStamina = BitmapFactory.decodeResource(c.getResources(), R.drawable.txtcurrnetstamina);
        txtBonusScore = BitmapFactory.decodeResource(c.getResources(), R.drawable.txtbonusscore);
        txtTotalScore = BitmapFactory.decodeResource(c.getResources(), R.drawable.txttotalscore);
        hit = BitmapFactory.decodeResource(c.getResources(), R.drawable.smallhit);
    }

    public void setBitmaps2(Context c){
        btnNextStage = BitmapFactory.decodeResource(c.getResources(), R.drawable.btnnextstage);
        heartIcon = BitmapFactory.decodeResource(c.getResources(), R.drawable.hearticon);
        playerBoard = BitmapFactory.decodeResource(c.getResources(), R.drawable.playerboard);

        popupBtnConfirm = BitmapFactory.decodeResource(c.getResources(), R.drawable.popupbtnconfirm);
        popupBtnCancel = BitmapFactory.decodeResource(c.getResources(), R.drawable.popupbtncancel);

        popupExit = BitmapFactory.decodeResource(c.getResources(), R.drawable.popupexit);
        popupGameStart = BitmapFactory.decodeResource(c.getResources(), R.drawable.popupgamestart);
        popupGameOver = BitmapFactory.decodeResource(c.getResources(), R.drawable.popupgameover);
    }

    public void setLengthes1(){
        txtGameTitleLength = new Length(txtGameTitle.getWidth(), txtGameTitle.getHeight());
        btnStartLength = new Length(btnStart.getWidth(), btnStart.getHeight());
        btnExitLength = new Length(btnStart.getWidth(), btnStart.getHeight());

        btnGameModeLength = new Length(btnGameModes[0].getWidth(), btnGameModes[0].getHeight());
        btnStageLength = new Length(btnStages[0].getWidth(), btnStages[0].getHeight());
        btnBackLength = new Length(btnBack.getWidth(), btnBack.getHeight());

        txtGameScoreLength = new Length(txtGameScore.getWidth(), txtGameScore.getHeight());
        txtNumOfCorrectLength = new Length(txtNumOfCorrect.getWidth(), txtNumOfCorrect.getHeight());
        txtCurrentStaminaLength = new Length(txtCurrentStamina.getWidth(), txtCurrentStamina.getHeight());
        txtBonusScoreLength = new Length(txtBonusScore.getWidth(), txtBonusScore.getHeight());
        txtTotalScoreLength = new Length(txtTotalScore.getWidth(), txtTotalScore.getHeight());
        hitLength = new Length(hit.getWidth(), hit.getHeight());
    }

    public void setLengthes2(){
        btnNextStageLength = new Length(btnNextStage.getWidth(), btnNextStage.getHeight());
        heartIconLength = new Length(heartIcon.getWidth(), heartIcon.getHeight());
        playerBoardLength = new Length(playerBoard.getWidth(), playerBoard.getHeight());

        popupBtnConfirmLength = new Length(popupBtnConfirm.getWidth(), popupBtnConfirm.getHeight());
        popupBtnCancelLength = new Length(popupBtnCancel.getWidth(), popupBtnCancel.getHeight());
    }

    public void removeGameModeButtons(){
        for(int i=0; i<5; i++) {
            btnGameModes[i].recycle();
            btnGameModes[i] = null;
        }
    }

    public void removeStageButtons(){
        for(int i=0; i<5; i++){
            btnStages[i].recycle();
            btnStages[i] = null;
        }
    }

    public void removeImageString(){
        for(int i=0; i<3; i++){
            for(int j=0; j<10; j++){
                IMG_NUMBERS[i][j].recycle();
                IMG_NUMBERS[i][j] = null;
            }
        }

        for(int i=0; i<5; i++) {
            IMG_GAME_MODES[i].recycle();
            IMG_GAME_MODES[i] = null;
        }

        IMG_ALPABET_a.recycle();
        IMG_ALPABET_c.recycle();
        IMG_ALPABET_e.recycle();
        IMG_ALPABET_g.recycle();
        IMG_ALPABET_i.recycle();
        IMG_ALPABET_m.recycle();
        IMG_ALPABET_o.recycle();
        IMG_ALPABET_r.recycle();
        IMG_ALPABET_s.recycle();
        IMG_ALPABET_t.recycle();
        IMG_CHAR_BLANK.recycle();
        IMG_CHAR_MINUS.recycle();
        IMG_CHAR_DANGDANG.recycle();
        IMG_CHAR_PERCENTAGE.recycle();

        IMG_ALPABET_a = null;
        IMG_ALPABET_c = null;
        IMG_ALPABET_e = null;
        IMG_ALPABET_g = null;
        IMG_ALPABET_i = null;
        IMG_ALPABET_m = null;
        IMG_ALPABET_o = null;
        IMG_ALPABET_r = null;
        IMG_ALPABET_s = null;
        IMG_ALPABET_t = null;
        IMG_CHAR_BLANK = null;
        IMG_CHAR_MINUS = null;
        IMG_CHAR_DANGDANG = null;
        IMG_CHAR_PERCENTAGE = null;
    }

    public void removeBitmaps1(){
        btnGameModes[0].recycle();
        btnStages[0].recycle();
        btnBack.recycle();

        txtGameScore.recycle();
        txtNumOfCorrect.recycle();
        txtCurrentStamina.recycle();
        txtBonusScore.recycle();
        txtTotalScore.recycle();
        hit.recycle();

        btnGameModes[0] = null;
        btnStages[0] = null;
        btnBack = null;

        txtStageClear = null;
        txtGameScore = null;
        txtNumOfCorrect = null;
        txtCurrentStamina = null;
        txtBonusScore = null;
        txtTotalScore = null;
        hit = null;
    }

    public void removeBitmaps2(){
        btnNextStage.recycle();
        heartIcon.recycle();
        playerBoard.recycle();

        btnNextStage = null;
        heartIcon = null;
        playerBoard = null;
    }

    public void setView(Context c){
        this.setBackgroundResource(R.drawable.introbackground);

        setBitmaps1(c);
        setLengthes1();
        removeBitmaps1();

        setBitmaps2(c);
        setLengthes2();
        removeBitmaps2();

        setStageButtonsState();

        thiefs = new ArrayList<>();
        touches = new ArrayList<>();

        imgStage = new ImageString(new Flag(true), 2);
        imgRemainTime = new ImageString(new Flag(true), 2);
        imgGameScore = new ImageString(new Flag(true), 2);

        imgNumOfCorrect = new ImageString(new Flag(true), 2);
        imgCurrentStamina = new ImageString(new Flag(true), 2);
        imgBonusScore = new ImageString(new Flag(true), 2);
        imgTotalScore = new ImageString(new Flag(true), 2);

        introMusic = new IntroMusic(this, c);
        gameOverMusic = new GameOverMusic(c);
        gameClearMusic = new GameClearMusic(c);

        btnReleasedEffect = new BtnReleasedEffect(this, c);
        shootEffect = new GunEffect(this, c);

        releasedEffectState = btnReleasedEffect;
        damagedBackColorShow = new DamageBackColorShow();

        gameFlagClass = new Flag(false);
        player = new Player();

        MAX_HEALTH = 100.0;
        popupShowed = false;
        gameFlag = 0;

        introMusic.playSound();
    }

    public int getCenterPosition(int pos, int length){
        return pos - (length/2);
    }

    public void drawButtons(Canvas canvas){
        if(screenNumber == 1) {
            canvas.drawBitmap(btnStart, btnStartPosition.getX(), btnStartPosition.getY(), null);
            canvas.drawBitmap(btnExit, btnExitPosition.getX(), btnExitPosition.getY(), null);
        }
        else if(screenNumber == 2) {
            for(int i=0; i<5; i++)
                canvas.drawBitmap(btnGameModes[i], btnGameModePositions[i].getX(), btnGameModePositions[i].getY(), null);
        }
        else if(screenNumber == 3) {
            for(int i=0; i<5; i++)
                canvas.drawBitmap(btnStages[i], btnStagePositions[i].getX(), btnStagePositions[i].getY(), null);
        }
        else if(screenNumber == 4 && gameFlag == 2){
            canvas.drawBitmap(btnBack, btnBackPosition.getX(), btnBackPosition.getY(), null);
            canvas.drawBitmap(btnNextStage, btnNextStagePosition.getX(), btnNextStagePosition.getY(), null);
        }
    }

    public void drawDamagedBackColor(Canvas canvas){
        if(damagedBackColorShow.show)
            canvas.drawBitmap(damagedBackColor, 0, 0, null);
    }

    public void drawTouchCircles(Canvas canvas){
        for(int i=0; i<touches.size(); i++){
            TouchPosition p = touches.get(i);

            if(p.isDamaged())
                canvas.drawBitmap(hitDamaged, getCenterPosition(p.getX(), hitLength.getWidth()), getCenterPosition(p.getY(), hitLength.getHeight()), null);
            else
                canvas.drawBitmap(hit, getCenterPosition(p.getX(), hitLength.getWidth()), getCenterPosition(p.getY(), hitLength.getHeight()), null);
        }
    }

    public void drawHealthBar(Canvas canvas){
        Paint healthFrameStyle = new Paint();
        healthFrameStyle.setColor(Color.WHITE);
        healthFrameStyle.setStyle(Paint.Style.STROKE);
        healthFrameStyle.setStrokeWidth(10);

        Paint healthStyle = new Paint();
        healthStyle.setColor(Color.RED);
        healthStyle.setStyle(Paint.Style.FILL);

        Rect myHealthBar = new Rect(50 + heartIconLength.getWidth() + 20, HEIGHT-100, (int) convertToMyHealth(player.getHealth()), HEIGHT-50);
        Rect myHealthFrame = new Rect(50 + heartIconLength.getWidth() + 20, HEIGHT-100, MAX_HEALTH_LENGTH, HEIGHT-50);

        canvas.drawBitmap(heartIcon, heartIconPosition.getX(), heartIconPosition.getY(), null);
        canvas.drawRect(myHealthBar, healthStyle);
        canvas.drawRect(myHealthFrame, healthFrameStyle);  // 자신의 체력바
    }

    public void showPopupButtons(Canvas canvas, char type){
        if(type == 'a'){
            popupBtnConfirmPosition.setX( getCenterPosition(WIDTH/2, popupBtnConfirmLength.getWidth()) );
            canvas.drawBitmap(popupBtnConfirm, popupBtnConfirmPosition.getX(), popupBtnConfirmPosition.getY(), null);
        }
        else if(type == 'c'){
            popupBtnConfirmPosition.setX( getCenterPosition(WIDTH/2 - 150, popupBtnConfirmLength.getWidth()) );
            canvas.drawBitmap(popupBtnConfirm, popupBtnConfirmPosition.getX(), popupBtnConfirmPosition.getY(), null);
            canvas.drawBitmap(popupBtnCancel, popupBtnCancelPosition.getX(), popupBtnCancelPosition.getY(), null);
        }
    }

    public void turnOnDamageEffectTimer(){
        player.minusHeaith(20);
        damageEffectTimer = new DamageEffectTimer(damagedBackColorShow);
        damageEffectTimer.setLimitTime(100);
        damageEffectTimer.start();
    }

    public void confirmGameOver(){
        if (player.getHealth() <= 0) {
            gameFlag = 3;
            gameFlagClass.flag = false;
        }
    }

    @Override
    public void onDraw(Canvas canvas){

        if(!setLength){
            setWIdthHeight();
        }
        else{
            if(screenNumber == 1){
                canvas.drawBitmap(txtGameTitle, txtGameTitlePosition.getX(), txtGameTitlePosition.getY(), null);
                drawButtons(canvas);

                if(popupShowed){
                    canvas.drawBitmap(popupBackColor, 0, 0, null);
                    canvas.drawBitmap(popupExit, popupExitPosition.getX(), popupExitPosition.getY(), null);
                    showPopupButtons(canvas, 'c');
                }
            }
            else if(screenNumber == 2){
                canvas.drawBitmap(txtSelectGameMode, 0, 10, null);
                canvas.drawBitmap(btnBack, btnBackPosition.getX(), btnBackPosition.getY(), null);
                drawButtons(canvas);
            }
            else if(screenNumber == 3){
                canvas.drawBitmap(txtSelectStage, 0, 10, null);
                canvas.drawBitmap(btnBack, btnBackPosition.getX(), btnBackPosition.getY(), null);
                drawButtons(canvas);

                if(popupShowed){
                    canvas.drawBitmap(popupBackColor, 0, 0, null);
                    canvas.drawBitmap(popupGameStart, popupExitPosition.getX(), popupExitPosition.getY(), null);
                    //canvas.drawText(strGameModes[selectedGameMode-1] + " Stage " + selectedStage, WIDTH / 2 - 300, popupExitPosition.getY() + 120, textStyle);
                    
                    int x = getCenterPosition(WIDTH/2, gameModeState.getWidth()) - gameModeState.getWidth()/4;

                    canvas.drawBitmap(gameModeState, x, popupExitPosition.getY() + 50, null);
                    imgStage.drawImageString(canvas, x + gameModeState.getWidth() + 50, popupExitPosition.getY() + 50);
                    showPopupButtons(canvas, 'c');
                }
            }
            else if(screenNumber == 4){
                if(gameFlag == 1) {  // 승부 미판정
                    for(int i=0; i<thiefs.size(); i++) {
                        Thief thief = thiefs.get(i);
                        thief.draw(canvas);

                        if(thief.isTimeout()) {
                            boolean correct = thief.minusStamina(player.getPlayerValue());
                            correct = !correct;

                            if (correct) {
                                currentScore += 2000;
                                numOfCorrect++;
                                imgGameScore.setImageString("score: " + currentScore);
                            } else {
                                currentScore -= 1000;
                                imgGameScore.setImageString("score: " + currentScore);
                                turnOnDamageEffectTimer();
                                confirmGameOver();
                                thief.playLaughEffect();
                            }

                            thief.setTimeoutDisable();
                        }
                    }

                    canvas.drawBitmap(heartIcon, heartIconPosition.getX(), heartIconPosition.getY(), null);
                    canvas.drawBitmap(playerBoard, playerBoardPosition.getX(), playerBoardPosition.getY(), null);
                    player.playerImageString.drawImageString(canvas, getCenterPosition(playerBoardPosition.getX() + playerBoardLength.getWidth()/2, player.playerImageString.getWidth()),
                            getCenterPosition(playerBoardPosition.getY() + playerBoardLength.getHeight()/2, player.playerImageString.getHeight()));

                    drawHealthBar(canvas);
                    drawTouchCircles(canvas);
                    drawDamagedBackColor(canvas);

                    //canvas.drawText("Time: " + convertToHMS(gamePlayTimer.getRemainTime()), 20, 100, textStyle);
                    //canvas.drawText("Score: " + currentScore, 20, 200, textStyle);
                    convertToHMS(gamePlayTimer.getRemainTime());

                    imgRemainTime.drawImageString(canvas, 50, 50);
                    imgGameScore.drawImageString(canvas, 50, 50 + imgRemainTime.getHeight() + 50);
                }
                else if(gameFlag == 2){  // 승리
                    if(open2)
                        open2Operation();

                    canvas.drawBitmap(popupBackColor, 0, 0, null);

                    canvas.drawBitmap(txtStageClear, txtStageClearPosition.getX(), txtStageClearPosition.getY(), null);
                    canvas.drawBitmap(txtGameScore, txtGameScorePosition.getX(), txtGameScorePosition.getY(), null);
                    canvas.drawBitmap(txtNumOfCorrect, txtNumOfCorrectPosition.getX(), txtNumOfCorrectPosition.getY(), null);
                    canvas.drawBitmap(txtCurrentStamina, txtCurrentStaminaPosition.getX(), txtCurrentStaminaPosition.getY(), null);
                    canvas.drawBitmap(txtBonusScore, txtBonusScorePosition.getX(), txtBonusScorePosition.getY(), null);
                    canvas.drawBitmap(txtTotalScore, txtTotalScorePosition.getX(), txtTotalScorePosition.getY(), null);

                    //canvas.drawText(String.valueOf(currentScore), txtNumOfCorrectLength.getWidth() + 10, txtNumOfCorrectPosition.getY() + 100, textStyle);  // 지정한 스타일을 적용해 텍스트를 그림
                    //canvas.drawText(String.valueOf(player.getHealth()) + "%", txtCurrentStaminaLength.getWidth() + 10, txtCurrentStaminaPosition.getY() + 100, textStyle);
                    //canvas.drawText(totalScore, txtTotalScore.getWidth() + 10, txtTotalScorePosition.getY() + 100, textStyle);
                    imgGameScore.drawImageString(canvas, txtGameScorePosition.getX() + txtGameScoreLength.getWidth() + 50, txtGameScorePosition.getY());
                    imgNumOfCorrect.drawImageString(canvas, txtNumOfCorrectPosition.getX() + txtNumOfCorrectLength.getWidth() + 50, txtNumOfCorrectPosition.getY());
                    imgCurrentStamina.drawImageString(canvas, txtCurrentStaminaPosition.getX() + txtCurrentStaminaLength.getWidth() + 50, txtCurrentStaminaPosition.getY());
                    imgBonusScore.drawImageString(canvas, txtBonusScorePosition.getX() + txtBonusScoreLength.getWidth() + 50, txtBonusScorePosition.getY());
                    imgTotalScore.drawImageString(canvas, txtTotalScorePosition.getX() + txtTotalScoreLength.getWidth() + 50, txtTotalScorePosition.getY());

                    drawButtons(canvas);
                }
                else if(gameFlag == 3){  // 패배
                    if(open1)
                        open1Operation();

                    drawDamagedBackColor(canvas);

                    if(popupShowed) {
                        canvas.drawBitmap(popupBackColor, 0, 0, null);
                        canvas.drawBitmap(popupGameOver, popupExitPosition.getX(), popupExitPosition.getY(), null);
                        showPopupButtons(canvas, 'c');
                    }
                }
            }
        }

        invalidate();
    }

    public void open1Operation(){
        gameOverMusic.playSound();
        releasedEffectState.gameEnd();
        popupShowed = true;
        open1 = false;
    }

    public void open2Operation(){
        gameClearMusic.playSound();
        releasedEffectState.gameEnd();

        int bonusScore = calculateScore();
        imgGameScore.setImageString(String.valueOf(currentScore));
        imgNumOfCorrect.setImageString(String.valueOf(numOfCorrect));
        imgCurrentStamina.setImageString(player.getHealth() + "%");
        imgBonusScore.setImageString(String.valueOf(bonusScore));

        int sum = currentScore + bonusScore;
        imgTotalScore.setImageString(String.valueOf(sum));

        open2 = false;
    }

    public void gameStart(){
        releasedEffectState.gameStart();
        gameFlag = 1;
        gameFlagClass.flag = true;
        damagedBackColorShow.show = false;
        currentScore = 0;
        numOfCorrect = 0;

        imgRemainTime.setImageString("00:00:60");
        imgGameScore.setImageString("score: 0");

        player.resetHealth();
        player.initializePlayerValue();
        player.playerImageString.setImageString(String.valueOf(player.getPlayerValue()));

        thiefs.clear();
        touches.clear();

        manager = new GameManager(getResources(), getContext(), player, thiefs, selectedGameMode, selectedStage);
        manager.start();

        gamePlayTimer = new GamePlayTimer();
        gamePlayTimer.setLimitTime(60000);
        gamePlayTimer.start();
        lastTime = gamePlayTimer.getRemainTime() / 1000;

        open1 = true;
        open2 = true;
    }

    public void attackCharacter(int X, int Y){
        boolean damaged = false;

        for(int i=0; i<thiefs.size(); i++) {
            Thief thief = thiefs.get(i);

            if(X >= thief.getX() && X <= thief.getX() + thief.getWidth()
                    && Y >= thief.getY() && Y <= thief.getY() + thief.getHeight()) {
                boolean correct = thief.minusStamina(player.getPlayerValue());
                damaged = true;

                if(thief.isAvailable()) {
                    if(correct){
                        currentScore += 2000;
                        numOfCorrect++;
                        imgGameScore.setImageString("score: " + currentScore);
                        thief.turnOnDeadEffectTImer();
                    }
                    else{
                        currentScore -= 1000;
                        imgGameScore.setImageString("score: " + currentScore);
                        thief.flagClass.flag = false;
                        turnOnDamageEffectTimer();
                        confirmGameOver();
                        thief.playLaughEffect();
                    }
                }
            }
        }

        touches.add(new TouchPosition(X, Y, damaged));
    }

    public void removeTouchEffect(){
        if(touches.size() != 0)
            touches.remove(0);
    }

    public void selectCancelButton(){
        btnReleasedEffect.playSound();
        popupShowed = false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        final int action = event.getAction();

        int X = (int) event.getRawX();
        int Y = (int) event.getRawY() - 100;

        int pointer_count = event.getPointerCount();

        switch (action & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                if(screenNumber == 4 && gameFlag == 1) {
                    X = (int) event.getX(0);
                    Y = (int) event.getY(0);

                    attackCharacter(X, Y);
                    releasedEffectState.playSound();
                }
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                if(screenNumber == 4 && gameFlag == 1) {
                    X = (int) event.getX(pointer_count-1);
                    Y = (int) event.getY(pointer_count-1);

                    attackCharacter(X, Y);
                    releasedEffectState.playSound();
                }
                break;
            case MotionEvent.ACTION_UP:
                if(screenNumber == 1) {
                    if(popupShowed) {
                        if (X >= popupBtnConfirmPosition.getX() && X <= popupBtnConfirmPosition.getX() + popupBtnConfirmLength.getWidth()
                                && Y >= popupBtnConfirmPosition.getY() && Y <= popupBtnConfirmPosition.getY() + popupBtnConfirmLength.getHeight()) {  // 확인 버튼을 눌렀으면
                            releasedEffectState.playSound();
                            System.exit(0);

                        } else if (X >= popupBtnCancelPosition.getX() && X <= popupBtnCancelPosition.getX() + popupBtnCancelLength.getWidth()
                                && Y >= popupBtnCancelPosition.getY() && Y <= popupBtnCancelPosition.getY() + popupBtnCancelLength.getHeight()) {  // 취소 버튼을 눌렀으면
                            selectCancelButton();
                        }
                    }
                    else {
                        if (X >= btnExitPosition.getX() && X <= btnExitPosition.getX() + btnExitLength.getWidth()
                                && Y >= btnExitPosition.getY() && Y <= btnExitPosition.getY() + btnExitLength.getHeight()) {  // Exit 버튼이 눌렸으면
                            releasedEffectState.playSound();
                            backPressed();
                        } else if (X >= btnStartPosition.getX() && X <= btnStartPosition.getX() + btnStartLength.getWidth()
                                && Y >= btnStartPosition.getY() && Y <= btnStartPosition.getY() + btnStartLength.getHeight()) {  // Start 버튼이 눌렸으면
                            releasedEffectState.playSound();

                            btnStart.recycle();
                            btnExit.recycle();

                            btnStart = null;
                            btnExit = null;

                            setGameModeButtons();
                            btnBack = BitmapFactory.decodeResource(getResources(), R.drawable.btnback);
                            txtSelectGameMode = BitmapFactory.decodeResource(getResources(), R.drawable.txtselectgamemode);

                            screenNumber = 2;
                        }
                    }
                }
                else if(screenNumber == 2) {
                    if(X >= btnBackPosition.getX() && X <= btnBackPosition.getX() + btnBackLength.getWidth()
                            && Y >= btnBackPosition.getY() && Y <= btnBackPosition.getY() + btnBackLength.getHeight()) {  // back 버튼이 눌렸으면
                        releasedEffectState.playSound();
                        backPressed();
                    }
                    else{
                        checkBtnGameModesWithBinarySearch(Y);
                    }
                }
                else if(screenNumber == 3) {
                    if(popupShowed) {
                        if (X >= popupBtnConfirmPosition.getX() && X <= popupBtnConfirmPosition.getX() + popupBtnConfirmLength.getWidth()
                                && Y >= popupBtnConfirmPosition.getY() && Y <= popupBtnConfirmPosition.getY() + popupBtnConfirmLength.getHeight()) {  // 확인 버튼을 눌렀으면
                            setBackgroundResource(R.drawable.gamebackground);
                            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

                            removeStageButtons();

                            txtSelectStage.recycle();
                            txtSelectStage = null;

                            hit = BitmapFactory.decodeResource(getResources(), R.drawable.smallhit);
                            hitDamaged = BitmapFactory.decodeResource(getResources(), R.drawable.smallhitdamaged);
                            heartIcon = BitmapFactory.decodeResource(getResources(), R.drawable.hearticon);
                            playerBoard = BitmapFactory.decodeResource(getResources(), R.drawable.playerboard);
                            btnNextStage = BitmapFactory.decodeResource(getResources(), R.drawable.btnnextstage);

                            txtStageClear = BitmapFactory.decodeResource(getResources(), R.drawable.txtstageclear);
                            txtGameScore = BitmapFactory.decodeResource(getResources(), R.drawable.txtgamescore);
                            txtNumOfCorrect = BitmapFactory.decodeResource(getResources(), R.drawable.txtnumofcorrect);
                            txtCurrentStamina = BitmapFactory.decodeResource(getResources(), R.drawable.txtcurrnetstamina);
                            txtBonusScore = BitmapFactory.decodeResource(getResources(), R.drawable.txtbonusscore);
                            txtTotalScore = BitmapFactory.decodeResource(getResources(), R.drawable.txttotalscore);

                            gameStart();

                            popupShowed = false;
                            screenNumber = 4;
                        }
                        else if (X >= popupBtnCancelPosition.getX() && X <= popupBtnCancelPosition.getX() + popupBtnCancelLength.getWidth()
                                && Y >= popupBtnCancelPosition.getY() && Y <= popupBtnCancelPosition.getY() + popupBtnCancelLength.getHeight()) {  // 취소 버튼을 눌렀으면
                            selectCancelButton();
                        }
                    }
                    else {
                        if(X >= btnBackPosition.getX() && X <= btnBackPosition.getX() + btnBackLength.getWidth()
                                && Y >= btnBackPosition.getY() && Y <= btnBackPosition.getY() + btnBackLength.getHeight()) {  // back 버튼이 눌렸으면
                            releasedEffectState.playSound();
                            backPressed();
                        }
                        else {
                            checkBtnStagesWithBinarySearch(Y);
                        }
                    }
                }
                else if(screenNumber == 4){
                    if(popupShowed) {
                        if (X >= popupBtnConfirmPosition.getX() && X <= popupBtnConfirmPosition.getX() + popupBtnConfirmLength.getWidth()
                                && Y >= popupBtnConfirmPosition.getY() && Y <= popupBtnConfirmPosition.getY() + popupBtnConfirmLength.getHeight()) {  // 확인 버튼을 눌렀으면
                            gameStart();
                            popupShowed = false;
                        }
                        else if (X >= popupBtnCancelPosition.getX() && X <= popupBtnCancelPosition.getX() + popupBtnCancelLength.getWidth()
                                && Y >= popupBtnCancelPosition.getY() && Y <= popupBtnCancelPosition.getY() + popupBtnCancelLength.getHeight()) {  // 취소 버튼을 눌렀으면
                            releasedEffectState.playSound();

                            popupShowed = false;
                            backPressed();
                        }
                    }
                    else if(gameFlag == 2) {
                        if (X >= btnBackPosition.getX() && X <= btnBackPosition.getX() + btnBackLength.getWidth()
                                && Y >= btnBackPosition.getY() && Y <= btnBackPosition.getY() + btnBackLength.getHeight()) {  // back 버튼이 눌렸으면
                            releasedEffectState.playSound();
                            backPressed();
                        } else if (X >= btnNextStagePosition.getX() && X <= btnNextStagePosition.getX() + btnNextStageLength.getWidth()
                                && Y >= btnNextStagePosition.getY() && Y <= btnNextStagePosition.getY() + btnNextStageLength.getHeight()) {  // next stage 버튼이 눌렸으면
                            releasedEffectState.playSound();
                            //popupShowed = true;
                        }
                    }
                    else if(gameFlag == 1) {
                        removeTouchEffect();
                    }
                }
                break;
            case MotionEvent.ACTION_POINTER_UP:
                if(screenNumber == 4 && gameFlag == 1){
                    removeTouchEffect();
                }
                break;
            case MotionEvent.ACTION_OUTSIDE:
                break;
        }

        return true;
    }

    public void checkBtnGameModesWithBinarySearch(int Y){
        int pressedGameMode = -1;

        int start = 0;
        int end = 4;

        while(start <= end){
            int mid = (start+end)/2;

            if (Y >= btnGameModePositions[mid].getY() && Y <= btnGameModePositions[mid].getY() + btnGameModeLength.getHeight()) {
                pressedGameMode = mid;
                break;
            }
            else if(Y < btnGameModePositions[mid].getY()){
                end = mid - 1;
            }
            else{
                start = mid + 1;
            }
        }

        if (pressedGameMode != -1) {
            releasedEffectState.playSound();
            selectedGameMode = pressedGameMode + 1;

            removeGameModeButtons();

            txtSelectGameMode.recycle();
            txtSelectGameMode = null;

            setImageString();

            txtSelectStage = BitmapFactory.decodeResource(getResources(), R.drawable.txtselectstage);
            setStageButtons(selectedGameMode-1);

            screenNumber = 3;
        }
    }

    public void checkBtnStagesWithBinarySearch(int Y){
        int pressedStage = -1;

        int start = 0;
        int end = 4;

        while(start <= end){
            int mid = (start+end)/2;

            if (Y >= btnStagePositions[mid].getY() && Y <= btnStagePositions[mid].getY() + btnStageLength.getHeight()) {
                pressedStage = mid;
                break;
            }
            else if(Y < btnStagePositions[mid].getY()){
                end = mid - 1;
            }
            else{
                start = mid + 1;
            }
        }

        if (pressedStage != -1) {
            if(stageAvailable[selectedGameMode-1][pressedStage]) {
                releasedEffectState.playSound();
                selectedStage = pressedStage + 1;
                gameModeState = IMG_GAME_MODES[selectedGameMode-1];
                imgStage.setImageString("stage " + selectedStage);
                popupShowed = true;
            }
        }
    }

    public int calculateScore(){  // 1000 ~ 10000
        return player.getHealth() * 100 - (manager.getAllNumofThief() - numOfCorrect);
    }

    public void backPressed(){
        if(!popupShowed) {
            if (screenNumber == 1) {
                popupShowed = true;
            }
            else if(screenNumber == 2){
                removeGameModeButtons();

                txtSelectGameMode.recycle();
                txtSelectGameMode = null;

                txtGameTitle = BitmapFactory.decodeResource(getResources(), R.drawable.txtlogo);
                btnStart = BitmapFactory.decodeResource(getResources(), R.drawable.btnstart);
                btnExit = BitmapFactory.decodeResource(getResources(), R.drawable.btnexit);

                screenNumber = 1;
            }
            else if(screenNumber == 3){
                removeStageButtons();
                removeImageString();

                txtSelectStage.recycle();
                txtSelectStage = null;

                txtSelectGameMode = BitmapFactory.decodeResource(getResources(), R.drawable.txtselectgamemode);
                setGameModeButtons();

                screenNumber = 2;
            }
            else if(screenNumber == 4){
                releasedEffectState.gameEnd();
                setBackgroundResource(R.drawable.introbackground);
                introMusic.playSound();
                activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

                gameFlag = 0;
                gameFlagClass.flag = false;

                hit.recycle();
                hitDamaged.recycle();
                heartIcon.recycle();
                playerBoard.recycle();
                btnNextStage.recycle();
                txtStageClear.recycle();
                txtGameScore.recycle();
                txtNumOfCorrect.recycle();
                txtCurrentStamina.recycle();
                txtBonusScore.recycle();
                txtTotalScore.recycle();

                hit = null;
                hitDamaged = null;
                heartIcon = null;
                playerBoard = null;
                btnNextStage = null;
                txtStageClear = null;
                txtGameScore = null;
                txtNumOfCorrect = null;
                txtCurrentStamina = null;
                txtBonusScore = null;
                txtTotalScore = null;

                setStageButtons(selectedGameMode-1);
                txtSelectStage = BitmapFactory.decodeResource(getResources(), R.drawable.txtselectstage);

                screenNumber = 3;
            }
        }
    }

    public double convertToMyHealth(int n){
        return n / MAX_HEALTH * MAX_HEALTH_LENGTH;
    }

    public void convertToHMS(long t){  // 1초 - 1000ms
        long sec = t / 1000;

        if(sec != lastTime) {

            int hour = (int) (sec / 3600);
            sec = (sec % 3600);

            int minute = (int) (sec / 60);
            sec = (sec % 60);

            int second = (int) (sec);

            String h = String.valueOf(hour);
            String m = String.valueOf(minute);
            String s = String.valueOf(second);

            if (hour <= 9)
                h = "0" + h;
            if (minute <= 9)
                m = "0" + m;
            if (second <= 9)
                s = "0" + s;

            imgRemainTime.setImageString(h + ":" + m + ":" + s);
            lastTime = sec;
        }
    }

    public void setWIdthHeight(){
        WIDTH = getWidth();
        HEIGHT = getHeight();

        int yPos = getCenterPosition(HEIGHT/2, btnStartLength.getHeight()) + (btnStartLength.getHeight()) + 150;
        int gap = 10;

        txtGameTitlePosition = new Position( getCenterPosition(WIDTH/2, txtGameTitleLength.getWidth()), getCenterPosition(HEIGHT/2, txtGameTitleLength.getHeight()) + 40 );
        btnStartPosition = new Position(getCenterPosition(WIDTH/2, btnStartLength.getWidth()), yPos);
        btnExitPosition = new Position(getCenterPosition(WIDTH/2, btnExitLength.getWidth()), yPos + btnStartLength.getHeight() + gap);

        txtStageClearPosition = new Position(50, getCenterPosition(HEIGHT/2, txtNumOfCorrectLength.getHeight()) - 150 - 150 - 150 - 300);
        txtGameScorePosition = new Position(50, getCenterPosition(HEIGHT/2, txtNumOfCorrectLength.getHeight()) - 150 - 150 - 150);
        txtNumOfCorrectPosition = new Position(50, getCenterPosition(HEIGHT/2, txtNumOfCorrectLength.getHeight()) - 150 - 150);
        txtCurrentStaminaPosition = new Position(50, getCenterPosition(HEIGHT/2, txtNumOfCorrectLength.getHeight()) - 150);
        txtBonusScorePosition = new Position(50, getCenterPosition(HEIGHT/2, txtNumOfCorrectLength.getHeight()));
        txtTotalScorePosition = new Position(50, getCenterPosition(HEIGHT/2, txtNumOfCorrectLength.getHeight()) + 300);

        btnBackPosition = new Position(10, HEIGHT - btnBackLength.getHeight() - 10);
        btnNextStagePosition = new Position(WIDTH - btnNextStageLength.getWidth() - 10, HEIGHT - btnNextStageLength.getHeight() - 10);
        heartIconPosition = new Position(50, HEIGHT-100);
        playerBoardPosition = new Position(getCenterPosition(WIDTH/2, playerBoardLength.getWidth()), HEIGHT - playerBoardLength.getHeight() - 90);

        popupExitPosition = new Position( getCenterPosition(WIDTH/2, popupExit.getWidth()), getCenterPosition(HEIGHT/2, popupExit.getHeight()) );
        popupBtnConfirmPosition = new Position(0, popupExitPosition.getY() + popupExit.getHeight() - popupBtnConfirmLength.getHeight() - 50);
        popupBtnConfirmPosition.setY(popupExitPosition.getY() + popupExit.getHeight() - popupBtnConfirmLength.getHeight() - 50);
        popupBtnCancelPosition = new Position(getCenterPosition(WIDTH/2 + 150, popupBtnCancelLength.getWidth()), popupExitPosition.getY() + popupExit.getHeight() - popupBtnCancelLength.getHeight() - 50);

        Bitmap originalPopupBackColor = BitmapFactory.decodeResource(getResources(), R.drawable.popupbackcolor);
        Bitmap originalDamagedBackColor = BitmapFactory.decodeResource(getResources(), R.drawable.damagebackcolor);

        popupBackColor = Bitmap.createScaledBitmap(originalPopupBackColor, WIDTH, HEIGHT, true);
        damagedBackColor = Bitmap.createScaledBitmap(originalDamagedBackColor, WIDTH, HEIGHT, true);

        originalPopupBackColor.recycle();
        originalDamagedBackColor.recycle();

        originalPopupBackColor = null;
        originalDamagedBackColor = null;

        int x = getCenterPosition(WIDTH/2, btnGameModeLength.getWidth());
        int y = getCenterPosition(HEIGHT/4, btnGameModeLength.getHeight());

        for(int j=0; j<5; j++){
            btnGameModePositions[j] = new Position(x, y);
            y += (btnGameModeLength.getHeight() + 10);
        }

        x = getCenterPosition(WIDTH/2, btnStageLength.getWidth());
        y = getCenterPosition(HEIGHT/4, btnStageLength.getHeight());

        for(int j=0; j<5; j++){
            btnStagePositions[j] = new Position(x, y);
            y += (btnStageLength.getHeight() + 10);
        }

        MAX_HEALTH_LENGTH = WIDTH - 50 - 20;
        setLength = true;
    }

    public void stopBgMusic(){
        introMusic.stopSound();
    }

    public SoundEffect getBtnReleasedEffect(){
        return this.btnReleasedEffect;
    }

    public SoundEffect getShootEffect(){
        return this.shootEffect;
    }

    public void setReleasedEffectState(SoundEffect se){
        this.releasedEffectState = se;
    }
}
