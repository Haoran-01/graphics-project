package objects3D;

import static org.lwjgl.opengl.GL11.*;
import GraphicsObjects.Utils;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

public class IceCream {
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

    public IceCream(){

    }

    public void drawIceCream(){
        Cone cone = new Cone();
        TexCylinder texCylinder = new TexCylinder();
        TexSphere texSphere = new TexSphere();
        Sphere sphere = new Sphere();

        glPushMatrix();
        {
            glTranslatef(0.0f, 0.0f, 0.0f);
            glColor3f(yellow[0], yellow[1]-0.25f, yellow[2]);
            cone.drawCone(0.3f,0.7f,32);

            glPushMatrix();
            {
                glTranslatef(0.0f, 0.0f, 0.0f);
                glColor3f(white[0], white[1]-0.25f, white[2]);
                sphere.drawSphere(0.25f, 32, 32);
            }
            glPopMatrix();
        }
        glPopMatrix();
    }

}
