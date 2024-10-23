package objects3D;
import static org.lwjgl.opengl.GL11.*;
import GraphicsObjects.Utils;
import org.lwjgl.BufferUtils;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import java.nio.FloatBuffer;

public class LightPole {
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

    public LightPole(){

    }

    public void DrawLightPole(Texture[] lightTextures, Texture poleTexture, Texture foundation){
        TexCylinder texCylinder = new TexCylinder();
        TexSphere texSphere = new TexSphere();
        Sphere sphere = new Sphere();
        TexCube texCube = new TexCube();
        TexCube2 texCube2 = new TexCube2();

        glPushMatrix();
        {
            glTranslatef(0.0f, 0.0f, 0.0f);
            glRotatef(90, 1.0f, 0.0f, 0.0f);
            texCube.drawTexCube(2.0f, 2.0f, 3.0f, foundation);

            glPushMatrix();
            {
                glPushMatrix();
                {
                    glTranslatef(0.0f, 0.0f, 1.5f);
                    texCube.drawTexCube(2.3f, 2.3f, 0.5f, foundation);
                }
                glPopMatrix();


                glTranslatef(0.0f, 0.0f, -1.5f);
                texCube.drawTexCube(1.8f, 1.8f, 0.2f, foundation);

                glPushMatrix();
                {
                    glTranslatef(0.0f, 0.0f, -0.1f);
                    texCube.drawTexCube(1.5f, 1.5f, 0.2f, foundation);

                    glPushMatrix();
                    {
                        glColor3f(grey[0], grey[1], grey[2]);
                        glTranslatef(0.0f, 0.0f, -0.2f);
                        texCylinder.drawTexCylinder(0.65f, 0.2f, 10, poleTexture);

                        glPushMatrix();
                        {
                            // 灯柱
                            glColor3f(grey[1], grey[2], grey[3]);
                            glTranslatef(0.0f, 0.0f, -20f);
                            texCylinder.drawTexCylinder(0.5f, 20f, 10, poleTexture);

                            glPushMatrix();
                            {
                                glTranslatef(0.0f, 0.0f, 0.0f);
                                texCube.drawTexCube(1.5f, 1.5f, 0.2f ,foundation);

                                glPushMatrix();
                                {
                                    // 灯罩
                                    glTranslatef(0.0f, 0.0f, -1f);
                                    texCube2.drawTexCube2(1.3f, 1.3f, 2.0f, lightTextures);

                                    glPushMatrix();
                                    {
                                        glTranslatef(0.0f, 0.0f, -1.0f);
                                        texCube.drawTexCube(1.8f, 1.8f, 0.2f, foundation);
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
                glPopMatrix();
            }
            glPopMatrix();


        }
        glPopMatrix();
    }
}

