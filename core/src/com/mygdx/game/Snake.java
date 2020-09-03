package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Snake {


    private boolean reterurnToMenu=false;

    public class Piece{
        Piece()
        {
            active = false;
        }
        public Vector2 position;
        public boolean active ;

        private void setPosition(float x, float y)
        {
            position.x = x;
            position.y = y;
        }

        public Vector2 getPosition() {
            return position;
        }
    }

    public class Health{
        Health()
        {
            active = true;
        }
        boolean active;
        Vector2 position;

        public void setPosition(float x, float y) {
            position.x = x; position.y = y;
        }

        public Vector2 getPosition() {
        return position;
        }
    }


    Piece body[]; //объявление массива кусочков тела
    Health health[];

    private final int LENGTH_SNAKE =200;
    private float X_COORDINATE_HEAD = 300.f;
    private float Y_COORDINATE_HEAD = 50.f;
    private float LENGTH_PIECE = 16.f;
    private final int START_LENGTH=15;
    private final int NUM_HP=3;
    private final int TIME_BETWEEN_HEAFRTH_LIGHT=10;
    private int speed = 9;
    Texture piece;
    private boolean directionLeft, directionRight, directionUp, directionDown;
    int Counter = 0;
    int EndSnake =START_LENGTH;
    private Rectangle head;
    Sound soundEatItself;
    private int Score=0;
    private  int CounterHearth=0;

    Texture textureHearth;
    Texture textureHead;


    public Snake()
    {
        soundEatItself = Gdx.audio.newSound(Gdx.files.internal("soundEatItself.mp3"));
        body = new Piece[LENGTH_SNAKE+1]; //создание массива
        health = new Health[NUM_HP];

        for(int i=0; i<LENGTH_SNAKE; i++) // инициализируем вектора
        {
            body[i] = new Piece();
            body[i].position = new Vector2(-30,-30);
        }

        for(int i=0;i<NUM_HP;i++)
        {
            health[i] = new Health();
            health[i].position = new Vector2();
        }

        health[0].setPosition(20,500);
        health[1].setPosition(84,500);
        health[2].setPosition(148,500);

        head = new Rectangle();

        body[0].setPosition(X_COORDINATE_HEAD,Y_COORDINATE_HEAD);
        body[0].active=true;
        for(int i=1; i<START_LENGTH;i++)
        {
            body[i].setPosition(body[i-1].getPosition().x - LENGTH_PIECE,body[i-1].getPosition().y);
            body[i].active = true;
        }

        directionLeft = directionUp = directionDown = false;
        directionRight = true;
        head = new Rectangle(body[0].getPosition().x-4,body[0].getPosition().y-4,24,24);
        textureHearth = new Texture("textureHearth.png");
        piece = new Texture("piece.png");
        textureHead = new Texture("textureHead.png");

    }

    public void render(SpriteBatch batch, BitmapFont font)
    {
        batch.draw(textureHead,body[0].position.x,body[0].position.y);
        for (int i=1; i<EndSnake; i++)
        {
            batch.draw(piece,body[i].position.x,body[i].position.y);
        }
        font.draw(batch,"Length: " + EndSnake, 600, 570);
        //font.draw(batch,"Snake.Speed = " + getSpeed(), 50, 380);
        //font.draw(batch,"EndPos.X = " + body[EndSnake-1].getPosition().x, 50, 400);
        //font.draw(batch,"EndPos.Y = " + body[EndSnake-1].getPosition().y, 50, 380);
        font.draw(batch, "Score: " + Score,600,550);

        for(int i=0; i<NUM_HP;i++) {
           if(health[i].active && CounterHearth<TIME_BETWEEN_HEAFRTH_LIGHT) {
               batch.draw(textureHearth, health[i].getPosition().x, health[i].getPosition().y, 64, 64, 0.0f, 1, 0.33f, 0); //большое сердце
           }

            if(health[i].active && CounterHearth>=TIME_BETWEEN_HEAFRTH_LIGHT && CounterHearth <2*TIME_BETWEEN_HEAFRTH_LIGHT) {
               batch.draw(textureHearth, health[i].getPosition().x, health[i].getPosition().y, 64, 64, 0.33f, 1, 0.64f, 0);// среднее сердце
            }

            if(health[i].active && CounterHearth>=2*TIME_BETWEEN_HEAFRTH_LIGHT&& CounterHearth<3*TIME_BETWEEN_HEAFRTH_LIGHT) {
                batch.draw(textureHearth, health[i].getPosition().x, health[i].getPosition().y, 64, 64, 0.64f, 1, 0.95f, 0); // маленькое сердце

            }

            if(health[i].active && CounterHearth>=3*TIME_BETWEEN_HEAFRTH_LIGHT && CounterHearth <4*TIME_BETWEEN_HEAFRTH_LIGHT) {
                batch.draw(textureHearth, health[i].getPosition().x, health[i].getPosition().y, 64, 64, 0.33f, 1, 0.64f, 0);// среднее сердце
            }


            if(CounterHearth>=4*TIME_BETWEEN_HEAFRTH_LIGHT) CounterHearth=0;
        }
        CounterHearth++;

    }

    private void EatSearchRight()
    {
        for(int i=1; i<EndSnake;i++)
        {
            if(body[0].getPosition().x+LENGTH_PIECE==body[i].getPosition().x && body[0].getPosition().y==body[i].getPosition().y) {
                SetLength(i);
                Score-=100;
            }

        }
    }

    private void EatSearchLeft()
    {
        for(int i=1; i<EndSnake;i++)
        {
            if(body[0].getPosition().x-LENGTH_PIECE==body[i].getPosition().x && body[0].getPosition().y==body[i].getPosition().y)
                SetLength(i);

        }
    }

    private void EatSearchUp()
    {
        for(int i=1; i<EndSnake;i++)
        {
            if(body[0].getPosition().x==body[i].getPosition().x && body[0].getPosition().y+LENGTH_PIECE==body[i].getPosition().y)
                SetLength(i);

        }
    }

    private void EatSearchDown()
    {
        for(int i=1; i<EndSnake;i++)
        {
            if(body[0].getPosition().x+LENGTH_PIECE==body[i].getPosition().x && body[0].getPosition().y-LENGTH_PIECE==body[i].getPosition().y)
                SetLength(i);

        }
    }

    private void SetLength(int i) {
        EndSnake = i;
        soundEatItself.play();
        if (health[2].active)
        {
            health[2].active=false;
        }
        else if(health[1].active)
        {
            health[1].active=false;
        }
        else if(health[0].active)
        {
            health[0].active=false;
            reterurnToMenu = true;

        }

    }

    private void SetNewPositionHead()
    {
        if(directionRight)
        {
            if(body[0].getPosition().x>=704)
            {
                EatSearchRight();
                body[0].setPosition(0,body[0].getPosition().y);
                head.x=body[0].getPosition().x-4;
                head.y = body[0].getPosition().y-4;
            }
            else {
                EatSearchRight();
                body[0].setPosition(body[0].getPosition().x + LENGTH_PIECE, body[0].getPosition().y);
                head.x = body[0].getPosition().x - 4;
                head.y = body[0].getPosition().y - 4;
            }
        }

        if(directionUp)
        {
            if(body[0].getPosition().y>=560)
            {
                EatSearchUp();
                body[0].setPosition(body[0].getPosition().x,0);
                head.x=body[0].getPosition().x-4;
                head.y = body[0].getPosition().y-4;

            }
            else {

                EatSearchUp();
                body[0].setPosition(body[0].getPosition().x, body[0].getPosition().y + LENGTH_PIECE);
                head.x = body[0].getPosition().x - 4;
                head.y = body[0].getPosition().y - 4;
            }

        }

        if(directionDown)
        {
            if(body[0].getPosition().y<=0)
            {
                EatSearchDown();
                body[0].setPosition(body[0].getPosition().x,562);
                head.x=body[0].getPosition().x-4;
                head.y = body[0].getPosition().y-4;

            }
            else {
                EatSearchDown();
                body[0].setPosition(body[0].getPosition().x, body[0].getPosition().y - LENGTH_PIECE);
                head.x = body[0].getPosition().x - 4;
                head.y = body[0].getPosition().y - 4;
            }
        }

        if(directionLeft)
        {
            if(body[0].getPosition().x<=0)
            {
                EatSearchLeft();
                body[0].setPosition(704,body[0].getPosition().y);
                head.x=body[0].getPosition().x-4;
                head.y = body[0].getPosition().y-4;
            }
            else {
                EatSearchLeft();
                body[0].setPosition(body[0].getPosition().x - LENGTH_PIECE, body[0].getPosition().y);
                head.x = body[0].getPosition().x - 4;
                head.y = body[0].getPosition().y - 4;
            }
        }
        // дописать в другие направления
    }

    public void AddLength() //увеличивает длинну змеи
    {
        //if(Counter>3) {
            if (EndSnake < LENGTH_SNAKE) {
                body[EndSnake].active = true;
               // body[EndSnake].
                EndSnake++;
                Score +=10;
            }
        //}

    }

    private boolean SearchLeft() // Ищет есть ли слева от головы часть змеи
    {
        for(int i=1; i<EndSnake; i++)
        {
            if (body[0].getPosition().x-LENGTH_PIECE==body[i].getPosition().x && body[0].getPosition().y==body[i].getPosition().y)
                return false;
        }

        return true;
    }

    private boolean SearchRight()// Ищет есть ли справа от головы часть змеи
    {
        for(int i=1; i<EndSnake; i++)
        {
            if (body[0].getPosition().x+LENGTH_PIECE==body[i].getPosition().x && body[0].getPosition().y==body[i].getPosition().y)
                return false;
        }

        return true;
    }

    private boolean SearchUp()// Ищет есть ли сверху от головы часть змеи
    {
        for(int i=1; i<EndSnake; i++)
        {
            if (body[0].getPosition().x==body[i].getPosition().x && body[0].getPosition().y+LENGTH_PIECE==body[i].getPosition().y)
                return false;
        }

        return true;
    }

    private boolean SearchDown()// Ищет есть ли снизу от головы часть змеи
    {
        for(int i=1; i<EndSnake; i++)
        {
            if (body[0].getPosition().x==body[i].getPosition().x && body[0].getPosition().y-LENGTH_PIECE==body[i].getPosition().y)
                return false;
        }

        return true;
    }

    public void update() //обновляет направление головы
    {

        if(Gdx.input.isKeyPressed(Input.Keys.UP) && !directionDown && SearchUp())
        {
            directionUp = true;
            directionRight = directionLeft = directionDown =false;

        }

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && !directionRight && SearchLeft())
        {
            directionLeft = true;
            directionRight = directionUp = directionDown =false;

        }

        if(Gdx.input.isKeyPressed(Input.Keys.DOWN) && !directionUp && SearchDown())
        {
            directionDown = true;
            directionRight = directionUp = directionLeft =false;

        }

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && !directionLeft && SearchRight())
        {
            directionRight = true;
            directionLeft = directionUp = directionDown =false;

        }


        if(Counter >getSpeed()) {

            Vector2 next = new Vector2(body[0].getPosition().x,body[0].getPosition().y);
            Vector2 tmp = new Vector2();
            SetNewPositionHead();
            //body[1].setPosition(next.x,next.y);

            for (int i = 1; i < LENGTH_SNAKE && body[i].active; i++) {
                tmp.x = body[i].getPosition().x; tmp.y = body[i].getPosition().y;

                body[i].setPosition(next.x,next.y);

                next.x = tmp.x; next.y = tmp.y;


            } //*/
            Counter = 0;
        }
        Counter++;
    }

    private int getSpeed() {

        if(EndSnake>=10 && EndSnake < 20)
             return 6;

        if(EndSnake>=20 && EndSnake < 30)
            return 5;

        if(EndSnake>=30 && EndSnake < 40)
            return 4;

        if(EndSnake>=40)
            return 3;


        return speed;
    }

    public Rectangle getHead()
    {
        return head;
    }

    public boolean getReterurnToMenu()
    {
        return reterurnToMenu;
    }


}
