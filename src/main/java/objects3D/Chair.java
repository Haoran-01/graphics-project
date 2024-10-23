package objects3D;

import static org.lwjgl.opengl.GL11.*;
import GraphicsObjects.Utils;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

public class Chair {
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

    public Chair() {

    }

    public void drawChair(Texture pole, Texture chair){
        TexCube texCube = new TexCube();
        TexCylinder texCylinder = new TexCylinder();

        glPushMatrix();
        {
            glTranslatef(0.0f, 0.0f, 0.0f);
            glColor3f(brown[0], brown[1], brown[2]);
            glRotatef(90, 1.0f,0.0f,0.0f);
            texCube.drawTexCube(2f,1f,0.3f, chair);

            glPushMatrix();
            {
                glTranslatef(0.0f,0.0f,1.0f);
                glColor3f(brown[0], brown[1], brown[2]);
                glRotatef(90, 0.0f, 1.0f, 0.0f);
                texCube.drawTexCube(2f,0.3f,0.3f, chair);

                glPushMatrix();
                {
                    glTranslatef(-1.0f, 0.0f, 0.0f);
                    glColor3f(brown[0], brown[1], brown[2]);
                    glRotatef(90, 0.0f,1.0f,0.0f);
                    texCylinder.drawTexCylinder(0.5f,0.2f,32, pole);
                }
                glPopMatrix();
            }
            glPopMatrix();
        }
        glPopMatrix();
    }
}
