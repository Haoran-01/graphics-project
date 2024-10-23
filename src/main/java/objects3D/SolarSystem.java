package objects3D;

import static org.lwjgl.opengl.GL11.*;
import GraphicsObjects.Utils;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

public class SolarSystem {

    static int year = 0,
            day = 0,
            adamYear=0,
            hesperYear=0,
            marsYear=0,
            jupiterYear=0,
            saturnYear=0,
            uranusYear=0,
            neptuneYear=0,
            sunYear=0;

    Sphere sphere = new Sphere();

    // basic colours
    static float black[] = { 0.0f, 0.0f, 0.0f, 1.0f };
    static float white[] = { 1.0f, 1.0f, 1.0f, 1.0f };

    static float grey[] = { 0.5f, 0.5f, 0.5f, 1.0f };
    static float spot[] = { 0.1f, 0.1f, 0.1f, 0.5f };

    // primary colours
    static float red[] = { 1.0f, 0.0f, 0.0f, 1.0f };
    static float green[] = { 0.0f, 1.0f, 0.0f, 1.0f };
    static float blue[] = { 0.0f, 0.0f, 1.0f, 1.0f };

    // secondary colours
    static float yellow[] = { 1.0f, 1.0f, 0.0f, 1.0f };
    static float magenta[] = { 1.0f, 0.0f, 1.0f, 1.0f };
    static float cyan[] = { 0.0f, 1.0f, 1.0f, 1.0f };

    // other colours
    static float orange[] = { 1.0f, 0.5f, 0.0f, 1.0f, 1.0f };
    static float brown[] = { 0.5f, 0.25f, 0.0f, 1.0f, 1.0f };
    static float dkgreen[] = { 0.0f, 0.5f, 0.0f, 1.0f, 1.0f };
    static float pink[] = { 1.0f, 0.6f, 0.6f, 1.0f, 1.0f };

    public SolarSystem(){

    }

    void drawEarth(){
        glPushMatrix();
        glTranslatef(1.8f, 0.0f,0.0f);
        glRotatef ( year, 0.0f, 0.0f, 1.0f);
        glRotatef ( day, 0.0f, 0.0f, 1.0f);
        sphere.drawSphere(0.16f, 32, 32);
        glPopMatrix();
    }

    void drawSun(){
        glPushMatrix();
        sphere.drawSphere(0.6f, 32, 32);
        glPopMatrix();
    }

    void drawMercury(){
        // 水星
        glPushMatrix();
        glTranslatef(0.8f, 0.0f,0.0f);
        glRotatef ( adamYear, 0.0f, 0.0f, 1.0f);
        glRotatef ( day, 0.0f, 0.0f, 1.0f);
        sphere.drawSphere(0.13f, 32, 32);
        glPopMatrix();
    }

    void drawHesperus(){
        // 金星
        glPushMatrix();
        glTranslatef(1.3f, 0.0f,0.0f);
        glRotatef ( hesperYear, 0.0f, 0.0f, 1.0f);
        glRotatef ( day, 0.0f, 0.0f, 1.0f);
        sphere.drawSphere(0.2f, 32, 32);
        glPopMatrix();
    }

    void drawMars(){
        // 火星
        glPushMatrix();
        glTranslatef(2.2f, 0.0f,0.0f);
        glRotatef ( marsYear, 0.0f, 0.0f, 1.0f);
        glRotatef ( day, 0.0f, 0.0f, 1.0f);
        sphere.drawSphere(0.14f, 32, 32);
        glPopMatrix();
    }

    void drawJupiter(){
        // 木星
        glPushMatrix();
        glTranslatef(2.7f, 0.0f,0.0f);
        glRotatef ( jupiterYear, 0.0f, 0.0f, 1.0f);
        glRotatef ( day, 0.0f, 0.0f, 1.0f);
        sphere.drawSphere(0.22f, 32, 32);
        glPopMatrix();
    }

    void drawSaturn(){
        // 土星
        glPushMatrix();
        glTranslatef(3.15f, 0.0f,0.0f);
        glRotatef ( saturnYear, 0.0f, 0.0f, 1.0f);
        glRotatef ( day, 0.0f, 0.0f, 1.0f);
        sphere.drawSphere(0.14f, 32, 32);
        glPopMatrix();
    }

    void drawUranus(){
        // 天王星
        glPushMatrix();
        glTranslatef(3.55f, 0.0f,0.0f);
        glRotatef ( uranusYear, 0.0f, 0.0f, 1.0f);
        glRotatef ( day, 0.0f, 0.0f, 1.0f);
        sphere.drawSphere(0.12f, 32, 32);
        glPopMatrix();
    }

    void drawNeptune(){
        // 海王星
        glPushMatrix();
        glTranslatef(3.8f, 0.0f,0.0f);
        glRotatef ( neptuneYear, 0.0f, 0.0f, 1.0f);
        glRotatef ( day, 0.0f, 0.0f, 1.0f);
        sphere.drawSphere(0.1f, 32, 32);
        glPopMatrix();
    }

    void move()
    {
        //adamYear=0,hesperYear=0,marsYear=0,jupiterYear=0,saturnYear=0,uranusYear=0,neptuneYear=0;
        adamYear=(adamYear+12)%360;
        hesperYear=(hesperYear+20)%360;
        year=(year+8)%360;
        marsYear=(marsYear+6)%360;
        jupiterYear=(jupiterYear+5)%360;
        saturnYear=(saturnYear+4)%360;
        uranusYear=(uranusYear+3)%360;
        neptuneYear=(neptuneYear+1)%360;
        day = (day + 30) % 360;
    }

    public void drawSolarSystem(float delta, boolean GoodAnimation){
        float theta = (float) (delta * 2 * Math.PI);
        float posn_x = (float) Math.cos(theta);
        float posn_y = (float) Math.sin(theta);
        float planetRotation, planetRotation2;
        if (GoodAnimation) {
            planetRotation = (float) Math.cos(theta) * 45;
            planetRotation2 = (float) Math.cos(theta) * 10 ;
            System.out.println(planetRotation + ":" + planetRotation2);
        } else {
            planetRotation = 0;
            planetRotation2 = 0;
        }

        Sphere sphere = new Sphere();

        glPushMatrix();
        {
            glTranslatef(0.0f, 0.0f, 0.0f);
            sphere.drawSphere(0.01f, 5, 5);

            glPushMatrix();
            {
                // 水星
                glTranslatef(1.5f, 0.0f, 0.0f);
                glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
                glRotatef(planetRotation, 0.0f, 1.0f, 0.0f);
                sphere.drawSphere(0.1f, 32, 32);
            }
            glPopMatrix();

            glPushMatrix();
            {
                // 金星
                glTranslatef(2.5f, 0.0f, 0.0f);
                glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
                glTranslatef(posn_x, 0.0f, posn_y);
                glRotatef(planetRotation2 * 1.4f, 0.0f, 1.0f, 0.0f);
                sphere.drawSphere(0.15f, 32, 32);

            }
            glPopMatrix();
        }
        glPopMatrix();


        /*glPushMatrix();
        {
            glTranslatef(0.0f, 0.0f, 0.0f);
            sphere.drawSphere(0.01f, 5, 5);

            glPushMatrix();
            {
                // 金星
                glTranslatef(2.5f, 0.0f, 0.0f);
                sphere.drawSphere(0.15f, 32, 32);
                glRotatef(planetRotation2 * 1.4f, 0.0f, 1.0f, 0.0f);
            }
            glPopMatrix();

            glPushMatrix();
            {
                glTranslatef(0.0f, 1.0f, 0.0f);
                sphere.drawSphere(1.0f, 32, 32);

                // 水星
                glTranslatef(1.5f, 0.0f, 0.0f);
                glRotatef(planetRotation , 0.0f, 1.0f, 0.0f);
                sphere.drawSphere(0.1f, 32, 32);
                glTranslatef(posn_x, 1.0f, posn_y);


                // 金星
                glTranslatef(2.5f, 0.0f, 0.0f);
                glRotatef(planetRotation2, 0.0f, 1.0f, 0.0f);
                sphere.drawSphere(0.15f, 32, 32);
                glTranslatef(posn_x*2, 1.0f, posn_y*2);


                // 地球
                glTranslatef(3.5f, 0.0f, 0.0f);
                sphere.drawSphere(0.3f, 32, 32);
                glRotatef(planetRotation * 30, 0.0f, 1.0f, 0.0f);

                // 火星
                glTranslatef(4.5f, 0.0f, 0.0f);
                sphere.drawSphere(0.2f, 32, 32);
                glRotatef(planetRotation * 24, 0.0f, 1.0f, 0.0f);

                // 木星
                glTranslatef(5.5f, 0.0f, 0.0f);
                sphere.drawSphere(0.7f, 32, 32);
                glRotatef(-planetRotation * 13, 0.0f, 1.0f, 0.0f);

                // 土星
                glTranslatef(7.3f, 0.0f, 0.0f);
                sphere.drawSphere(0.6f, 32, 32);
                glRotatef(planetRotation * 10, 0.0f, 1.0f, 0.0f);

                // 天王星
                glTranslatef(9f, 0.0f, 0.0f);
                sphere.drawSphere(0.3f, 32, 32);
                glRotatef(planetRotation * 7, 0.0f, 1.0f, 0.0f);

                // 海王星
                glTranslatef(10f, 0.0f, 0.0f);
                sphere.drawSphere(0.3f, 32, 32);
                glRotatef(planetRotation * 5, 0.0f, 1.0f, 0.0f);

            }
            glPopMatrix();
        }
        glPopMatrix();*/
    }

}
