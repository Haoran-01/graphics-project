package objects3D;

import static org.lwjgl.opengl.GL11.*;
import GraphicsObjects.Utils;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

public class DrawingBoard {
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

    public DrawingBoard(){

    }

    public void drawDrawingBoard(Texture pole, Texture draw){
        TexCube texCube = new TexCube();

        glPushMatrix();
        {
            glTranslatef(0.0f,0.0f,0.0f);
            glRotatef(90, 1.0f, 0.0f,0.0f);
            texCube.drawTexCube(3.0f, 3.0f, 0.5f, pole);

            glPushMatrix();
            {
                glTranslatef(1.5f, 1f, -1f);
                glRotatef(90.0f, 0.0f,0.0f,1.0f);
                glRotatef(-70, 0.0f, 1.0f,0.0f);
                texCube.drawTexCube(6f, 0.3f, 0.3f, pole);

                glPushMatrix();
                {
                    glTranslatef(-1.50f, 1.5f, 0.0f);
                    glRotatef(-90, 0.0f, 0.0f, 1.0f);
                    texCube.drawTexCube(3.0f, 4.0f, 0.5f, draw);
                }
                glPopMatrix();
            }
            glPopMatrix();

            glPushMatrix();
            {
                glTranslatef(-1.5f, 1f, -1f);
                glRotatef(90.0f, 0.0f,0.0f,1.0f);
                glRotatef(-70, 0.0f, 1.0f,0.0f);
                texCube.drawTexCube(6f, 0.3f, 0.3f, pole);
            }
            glPopMatrix();

            glPushMatrix();
            {
                glTranslatef(-1.5f, -1f, -1f);
                glRotatef(90.0f, 0.0f,0.0f,1.0f);
                glRotatef(70, 0.0f, 1.0f,0.0f);
                texCube.drawTexCube(6f, 0.3f, 0.3f, pole);
            }
            glPopMatrix();

            glPushMatrix();
            {
                glTranslatef(1.5f, -1f, -1f);
                glRotatef(90.0f, 0.0f,0.0f,1.0f);
                glRotatef(70, 0.0f, 1.0f,0.0f);
                texCube.drawTexCube(6f, 0.3f, 0.3f, pole);
            }
            glPopMatrix();
        }
        glPopMatrix();
    }
}
