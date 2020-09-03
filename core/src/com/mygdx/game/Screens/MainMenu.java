package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGame;

public class MainMenu implements Screen {

    MyGame game;
    //SpriteBatch batch;
    OrthographicCamera camera;

    Texture startButtonTexture;
    Texture exitButtonTexture;
    Texture backgroundTexture;

    Sprite startButtonSprite;
    Sprite exitButtonSprite;
    Sprite backgroundSprite;

    private static float BUTTON_RESIZE_FACTOR = 800f;
    private static float START_VERT_POSITION_FACTOR = 2.7f;
    private static float EXIT_VERT_POSITION_FACTOR = 4.2f;

    float height =576;
    float width = 720;

    Music backgroundMenuMusic;
    Sound buttonSound;

    public MainMenu(MyGame game){
        this.game = game;
        backgroundMenuMusic = Gdx.audio.newMusic(Gdx.files.internal("backgroungMenMusic.mp3"));
        backgroundMenuMusic.setLooping(true);
        backgroundMenuMusic.setVolume(0.3f);
        backgroundMenuMusic.play();

        buttonSound = Gdx.audio.newSound(Gdx.files.internal("SoundButton.mp3"));


        camera = new OrthographicCamera(width,height);
        camera.setToOrtho(false);
        //batch = new SpriteBatch();

        startButtonTexture = new Texture("buttonStart.png");
        exitButtonTexture = new Texture("buttonExit.png");
        backgroundTexture = new Texture("background720x576.jpg");

        startButtonSprite = new Sprite(startButtonTexture);
        exitButtonSprite = new Sprite(exitButtonTexture);
        backgroundSprite = new Sprite(backgroundTexture);

        startButtonSprite.setSize(startButtonSprite.getWidth() *(width/BUTTON_RESIZE_FACTOR), startButtonSprite.getHeight()*(width/BUTTON_RESIZE_FACTOR));
        exitButtonSprite.setSize(exitButtonSprite.getWidth() *(width/BUTTON_RESIZE_FACTOR), exitButtonSprite.getHeight()*(width/BUTTON_RESIZE_FACTOR));
        backgroundSprite.setSize(width,height);
        startButtonSprite.setPosition((width/2f -startButtonSprite.getWidth()/2) , width/START_VERT_POSITION_FACTOR);
        exitButtonSprite.setPosition((width/2f -exitButtonSprite.getWidth()/2) , width/EXIT_VERT_POSITION_FACTOR);
        backgroundSprite.setAlpha(0.6f);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update();
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.setProjectionMatrix(camera.combined);// устанавливаем в экземпляр spritebatch вид с камеры (области просмотра)

        //отрисовка игровых объектов
        game.batch.begin();
        backgroundSprite.draw(game.batch);
        startButtonSprite.draw(game.batch);
        exitButtonSprite.draw(game.batch);
        //PrintInfo();
        game.batch.end();
    }

    void update()
    {
        //топорный выход через наведение курсора и нажатие кнопки
        if(Gdx.input.isTouched()&& Gdx.input.getX()>exitButtonSprite.getBoundingRectangle().x && Gdx.input.getX()<exitButtonSprite.getBoundingRectangle().x+exitButtonSprite.getWidth()
                && Gdx.input.getY()>(height - exitButtonSprite.getBoundingRectangle().y - exitButtonSprite.getHeight())  && Gdx.input.getY()<height - exitButtonSprite.getBoundingRectangle().y)
        {
            buttonSound.play();
            Gdx.app.exit();
        }

        if(Gdx.input.isTouched()&& Gdx.input.getX()>startButtonSprite.getBoundingRectangle().x && Gdx.input.getX()<startButtonSprite.getBoundingRectangle().x+startButtonSprite.getWidth()
                && Gdx.input.getY()>(height - startButtonSprite.getBoundingRectangle().y - startButtonSprite.getHeight())  && Gdx.input.getY()<height - startButtonSprite.getBoundingRectangle().y)
        {
            buttonSound.play();
            dispose();
            game.setScreen(new GameScreen(game));

        }


    }

    void PrintInfo()
    {
        game.font.draw(game.batch,"getY " + Gdx.input.getY(), 50,50);
        game.font.draw(game.batch,"bgRegY " + exitButtonSprite.getBoundingRectangle().y, 50,70);
        game.font.draw(game.batch,"bgGetHeight " + exitButtonSprite.getHeight(), 50,90);


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
        startButtonTexture.dispose();
        exitButtonTexture.dispose();
        backgroundTexture.dispose();
       // batch.dispose();
        game.dispose();
        backgroundMenuMusic.dispose();
        buttonSound.dispose();
    }


/*
    SpriteBatch batch;
    Texture img;

    @Override
    public void create () {
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
    }

    @Override
    public void render () {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(img, 0, 0);
        batch.end();
    }

    @Override
    public void dispose () {
        batch.dispose();
        img.dispose();
    }
}
*/

}
