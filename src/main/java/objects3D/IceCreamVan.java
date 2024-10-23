package objects3D;

import static org.lwjgl.opengl.GL11.*;
import GraphicsObjects.Utils;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

public class IceCreamVan {

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

    public IceCreamVan(){

    }

    public void drawIceCreamVan(Texture iceCreamVanPoleTexture, Texture iceCreamVanTexture){
        Sphere sphere = new Sphere();
        Cube cube = new Cube();
        TexCube texCube = new TexCube();
        Cone cone = new Cone();
        Cylinder cylinder = new Cylinder();
        IceCream iceCream = new IceCream();
        Human human = new Human();

        glPushMatrix();
        {
            // 车身
            glTranslatef(0.0f, 0.0f, 0.0f);
            glRotatef(90,1.0f, 0.0f,0.0f);
            texCube.drawTexCube(4.0f, 2.0f, 2.0f, iceCreamVanTexture);

            glPushMatrix();
            {
                // 轮子
                glTranslatef(2.0f, 1.1f, 1.0f);
                glColor3f(black[0] + 0.2f, black[1], black[2]);
                glRotatef(90, 1.0f, 0.0f, 0.0f);
                cylinder.drawCylinder(0.8f, 0.1f, 32);
            }
            glPopMatrix();

            glPushMatrix();
            {
                // 轮子
                glTranslatef(-2.0f, 1.1f, 1.0f);
                glRotatef(90, 1.0f, 0.0f, 0.0f);
                cylinder.drawCylinder(0.8f, 0.1f, 32);
            }
            glPopMatrix();

            glPushMatrix();
            {
                // 轮子
                glTranslatef(-2.0f, -1.1f, 1.0f);
                glRotatef(90, 1.0f, 0.0f, 0.0f);
                cylinder.drawCylinder(0.8f, 0.1f, 32);
            }
            glPopMatrix();

            glPushMatrix();
            {
                // 轮子
                glTranslatef(2.0f, -1.1f, 1.0f);
                glRotatef(90, 1.0f, 0.0f, 0.0f);
                cylinder.drawCylinder(0.8f, 0.1f, 32);
            }
            glPopMatrix();

            glPushMatrix();
            {
                // 桌板
                glTranslatef(0.0f, 0.0f, -1.0f);
                glColor3f(blue[0], blue[1] + 0.2f, blue[2] - 0.5f);
                cube.drawCube(5.0f, 2.4f, 0.1f);

                glPushMatrix();
                {
                    // 柱子
                    glTranslatef(2.0f, 1.1f, -1.0f);
                    texCube.drawTexCube(0.1f, 0.1f, 2.0f,iceCreamVanPoleTexture);
                }
                glPopMatrix();

                glPushMatrix();
                {
                    // 柱子
                    glTranslatef(-2.0f, 1.1f, -1.0f);
                    texCube.drawTexCube(0.1f, 0.1f, 2.0f,iceCreamVanPoleTexture);
                }
                glPopMatrix();

                glPushMatrix();
                {
                    // 柱子
                    glTranslatef(-2.0f, -1.1f, -1.0f);
                    texCube.drawTexCube(0.1f, 0.1f, 2.0f,iceCreamVanPoleTexture);
                }
                glPopMatrix();

                glPushMatrix();
                {
                    // 柱子
                    glTranslatef(2.0f, -1.1f, -1.0f);
                    texCube.drawTexCube(0.1f, 0.1f, 2.0f,iceCreamVanPoleTexture);
                }
                glPopMatrix();

                glPushMatrix();
                {
                    glTranslatef(0.0f, -1.1f, -0.35f);
                    texCube.drawTexCube(4.0f, 0.1f, 0.1f,iceCreamVanPoleTexture);
                }
                glPopMatrix();

                glPushMatrix();
                {
                    glTranslatef(2.0f, 0.0f, -0.35f);
                    glRotatef(90, 0.0f, 0.0f, 1.0f);
                    texCube.drawTexCube(2.2f, 0.1f, 0.1f,iceCreamVanPoleTexture);
                }
                glPopMatrix();

                glPushMatrix();
                {
                    glTranslatef(-2.0f, 0.0f, -0.35f);
                    glRotatef(90, 0.0f, 0.0f, 1.0f);
                    texCube.drawTexCube(2.2f, 0.1f, 0.1f,iceCreamVanPoleTexture);
                }
                glPopMatrix();

                for (int i = 1; i < 5; i++){
                    glPushMatrix();
                    {
                        glTranslatef(-2.0f + i * 0.8f, 0.0f, -0.7f);
                        iceCream.drawIceCream();
                    }
                    glPopMatrix();
                }

                glPushMatrix();
                {
                    // 冰激凌
                    glTranslatef(0.0f, 0.0f, -2.0f);
                    glColor3f(blue[0], blue[1] + 0.4f, blue[2] - 0.5f);
                    cube.drawCube(5.0f, 2.4f, 0.1f);

                    glPushMatrix();
                    {
                        glTranslatef(0.0f, 0.0f, -1.0f);
                        glRotatef(70, 0.0f, 1.5f, 0.0f);
                        glColor3f(yellow[0], yellow[1]-0.25f, yellow[2]);
                        cone.drawCone(0.7f, 2.5f, 32);

                        glPushMatrix();
                        {
                            glTranslatef(0.4f, 0.0f, -0.1f);
                            glColor3f(red[0], red[1], red[2]);
                            sphere.drawSphere(0.6f,32,32);
                        }
                        glPopMatrix();

                        glPushMatrix();
                        {
                            glTranslatef(-0.1f, -0.3f, -0.6f);
                            glColor3f(blue[0], blue[1], blue[2]);
                            sphere.drawSphere(0.55f,32,32);
                        }
                        glPopMatrix();
                        glPushMatrix();
                        {
                            glTranslatef(-0.4f, -0.4f, -0.1f);
                            glColor3f(yellow[0], yellow[1], yellow[2]);
                            sphere.drawSphere(0.6f,32,32);
                        }
                        glPopMatrix();
                    }
                    glPopMatrix();
                }
                glPopMatrix();
            }
            glPopMatrix();
        }
        glPopMatrix();
    }
}
