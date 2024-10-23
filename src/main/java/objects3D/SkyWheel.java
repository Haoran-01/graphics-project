package objects3D;
import static org.lwjgl.opengl.GL11.*;
import org.newdawn.slick.opengl.Texture;

public class SkyWheel {

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

    public SkyWheel(){

    }

    public void drawSkyWheel(float delta, Texture topTex, Texture skyWheelTex, Texture poleTex, Texture mainPoleTex){
        float theta = (float) (delta * 2 * Math.PI);
        float rotation = (float) Math.cos(theta * 2) * 45;
        Cube cube = new Cube();
        TexCube2 texCube2 = new TexCube2();
        TexCube texCube = new TexCube();
        Cylinder cylinder = new Cylinder();
        TexCylinder texCylinder = new TexCylinder();
        Sphere sphere = new Sphere();

        Texture[] textures = new Texture[6];
        textures[0] = topTex;
        textures[1] = skyWheelTex;
        textures[2] = topTex;
        textures[3] = topTex;
        textures[4] = topTex;
        textures[5] = skyWheelTex;

        glPushMatrix();
        {
            glTranslatef(0,0,0);
            glRotatef(90,1,0,0);
            texCube.drawTexCube(20,25,1f, poleTex);

            // 前侧柱子
            glPushMatrix();
            {
                glTranslatef(4,0,-17.3f);
                glRotatef(30,1,0,0);
                texCylinder.drawTexCylinder(1,20,15, mainPoleTex);
            }
            glPopMatrix();

            glPushMatrix();
            {
                glTranslatef(4,0,-17.3f);
                glRotatef(-30,1,0,0);
                texCylinder.drawTexCylinder(1,20,15, mainPoleTex);

                glPushMatrix();
                {
                    // 中间柱子
                    glTranslatef(1,0,0);
                    glRotatef(30,1,0,0);
                    glRotatef(-90,0,1,0);
                    cylinder.drawCylinder(1.5f,10,10);

                    glPushMatrix();
                    {
                        // 旋转轴
                        glTranslatef(0,0,5);
                        glRotatef(90,1,0,0);
                        glRotatef(theta*30,0,1,0);
                        sphere.drawSphere(0.1f,5,5);

                        for (int i = 0; i < 12; i++){
                            glPushMatrix();
                            {
                                glTranslatef(0,3,0);
                                glRotatef(i * 30, 0, 1, 0);
                                texCylinder.drawTexCylinder(0.5f,12,10, poleTex);
                            }
                            glPopMatrix();
                        }

                        for (int i = 0; i < 12; i++){
                            glPushMatrix();
                            {
                                // 旋转臂
                                glTranslatef(0,-3,0);
                                glRotatef(i * 30, 0, 1, 0);
                                texCylinder.drawTexCylinder(0.5f,12,10, poleTex);


                                glPushMatrix();
                                {
                                    // 车厢旋转柱
                                    glRotatef(-90,1,0,0);
                                    glTranslatef(12,0,-0.5f);
                                    cylinder.drawCylinder(0.3f,7,10);

                                    glPushMatrix();
                                    {
                                        // 车厢
                                        glTranslatef(2,0,3.5f);
                                        glRotatef(-i * 30, 0, 0, 1);
                                        glRotatef(-theta*30,0,0,1);
                                        texCube2.drawTexCube2(5,4.5f,4.5f,textures);
                                    }
                                    glPopMatrix();
                                }
                                glPopMatrix();
                            }
                            glPopMatrix();
                        }
                    }
                    glPopMatrix();
                }
                glPopMatrix();
            }
            glPopMatrix();

            // 后侧柱子
            glPushMatrix();
            {
                glTranslatef(-4,0,-17.3f);
                glRotatef(30,1,0,0);
                texCylinder.drawTexCylinder(1,20,15, mainPoleTex);
            }
            glPopMatrix();

            glPushMatrix();
            {
                glTranslatef(-4,0,-17.3f);
                glRotatef(-30,1,0,0);
                texCylinder.drawTexCylinder(1,20,15, mainPoleTex);
            }
            glPopMatrix();
        }
        glPopMatrix();
    }
}
