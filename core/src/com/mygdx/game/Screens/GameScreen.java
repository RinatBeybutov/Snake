package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.FoodEat;
import com.mygdx.game.MyGame;
import com.mygdx.game.Snake;


public class GameScreen implements Screen {

    MyGame game;
    Texture backgroundGameTexture;
    Snake snake;
    FoodEat foodEat;
    Music backgroundGameMusic;
    Music backgroundGameHappyFeet;
    Music backgroundGameCooks;
    Music backgroundGameSocoBachi;
    Sound soundEat;

    //delete!
    private static final int FRAME_COLS = 6, FRAME_ROWS =5;
    Animation<TextureRegion> walkAnimation;
    Texture walkSheet; // batch уже есть!
    float stateTime;
    // delete!

    private void SoundManager()
    {
       if (!backgroundGameMusic.isPlaying() && !backgroundGameCooks.isPlaying() && !backgroundGameHappyFeet.isPlaying()
            && !backgroundGameSocoBachi.isPlaying())
        {
            switch (1 + (int) (Math.random()*4)) {
                case 1:
                    backgroundGameSocoBachi.play();
                    break;

                case 2:

                    backgroundGameHappyFeet.play();
                    break;

                case 3:

                   backgroundGameCooks.play();
                    break;

                case 4:

                    backgroundGameMusic.play();
                    break;


            }
        }
    }

    public GameScreen(MyGame game)
    {
        this.game =game;
        backgroundGameTexture = new Texture("backgroundGame.jpg");
        snake = new Snake();
        foodEat = new FoodEat("foodTexture.png",100,100);
        backgroundGameMusic = Gdx.audio.newMusic(Gdx.files.internal("backgroundGameMusic.mp3"));
        backgroundGameHappyFeet = Gdx.audio.newMusic(Gdx.files.internal("backgroundGameHappyFeet.mp3"));
        backgroundGameCooks = Gdx.audio.newMusic(Gdx.files.internal("backgroundGameCooks.mp3"));
        backgroundGameSocoBachi = Gdx.audio.newMusic(Gdx.files.internal("backgroundGameSokoBachi.mp3"));

        SoundManager(); // фоновая музыка
        soundEat = Gdx.audio.newSound(Gdx.files.internal("soundEat.mp3"));

        //delete!!
        walkSheet = new Texture("spriteAnimation.png");
        TextureRegion[][] tmp = TextureRegion.split(walkSheet,
                walkSheet.getWidth()/FRAME_COLS,
                walkSheet.getHeight()/FRAME_ROWS);

        TextureRegion[] walkFrames = new TextureRegion[FRAME_ROWS*FRAME_COLS];
        int index = 0;
        for(int i=0; i< FRAME_ROWS; i++)
        {
            for(int j = 0; j<FRAME_COLS; j++)
            {
                walkFrames[index++] = tmp[i][j];
            }
        }

        walkAnimation = new Animation<TextureRegion>(0.025f, walkFrames);

        stateTime = 0f;
        //delete!!
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stateTime += Gdx.graphics.getDeltaTime();
        TextureRegion currentFrame = walkAnimation.getKeyFrame(stateTime,true);
        game.batch.begin();
        game.batch.draw(backgroundGameTexture,0,0);
        game.batch.draw(currentFrame,575,430);
        foodEat.render(game.batch, game.font);
        snake.render(game.batch,game.font);
        game.batch.end();
        SoundManager();
        update();



    }

    public void update()
    {
        if(snake.getReterurnToMenu())
        {

            backgroundGameMusic.stop();
            backgroundGameCooks.stop();
            backgroundGameHappyFeet.stop();
            backgroundGameSocoBachi.stop();
            dispose();
            game.setScreen(new MainMenu(game));

        }
        snake.update();
        if (snake.getHead().contains(foodEat.getPosition()))
        {
            soundEat.play();
            snake.AddLength();
            foodEat.destroy();
            foodEat.recreate();


        }

    }
    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        backgroundGameTexture.dispose();
        backgroundGameMusic.dispose();
        soundEat.dispose();
        backgroundGameCooks.dispose();
        backgroundGameHappyFeet.dispose();
        backgroundGameSocoBachi.dispose();

    }
}
