package objects3D;

import static org.lwjgl.opengl.GL11.*;
import GraphicsObjects.Utils;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

public class Earth {
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

    public Earth(){

    }

    public void drawEarth(float delta, Texture earthTex, Texture foundationTex, Texture loopTex){
        float theta = (float) (delta * 2 * Math.PI);
        float LimbRotation = (float) Math.cos(theta) * 45;

        Sphere sphere = new Sphere();
        TexCylinder texCylinder = new TexCylinder();
        Loop loop = new Loop();
        TexSphere texSphere = new TexSphere();

        glPushMatrix();
        {
            glTranslatef(0.0f, 0.0f, 0.0f);
            glRotatef(90.0f, 1.0f, 0.0f, 0.0f);

            /*glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);
            Color.white.bind();
            loopTex.bind();
            glEnable(GL_TEXTURE_2D);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);*/
            texCylinder.drawTexCylinder(6.0f, 1.5f, 32, foundationTex);
//            glEnable(GL_TEXTURE_2D);


            glPushMatrix();
            {
                glTranslatef(0.0f, 0.0f, -4f);

                glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);
                Color.white.bind();
                earthTex.bind();
                glEnable(GL_TEXTURE_2D);
                glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
                texSphere.DrawTexSphere(4f, 32, 32, earthTex);
                glDisable(GL_TEXTURE_2D);
                glRotatef(theta*20, 0.0f, 0.0f, 1.0f);

                glPushMatrix();
                {
                    glTranslatef(0.0f, 0.0f, 0.0f);/**/
                    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);
                    Color.white.bind();
                    foundationTex.bind();
                    glEnable(GL_TEXTURE_2D);
                    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
                    loop.drawLoop(4.2f, 0.5f, 32, loopTex);

                    glTranslatef(0.0f, 0.0f, 0.0f);
                    glRotatef(45.0f, 0.0f, 1.0f, 0.0f);
                    loop.drawLoop(4.4f, 0.5f, 32, loopTex);
                    glDisable(GL_TEXTURE_2D);
                }
                glPopMatrix();
            }
            glPopMatrix();
        }
        glPopMatrix();
    }
}
