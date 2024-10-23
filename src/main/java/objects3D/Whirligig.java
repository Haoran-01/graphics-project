package objects3D;

import org.newdawn.slick.opengl.Texture;

import static org.lwjgl.opengl.GL11.*;

public class Whirligig {

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

    public Whirligig(){

    }

    public void drawWhirligig(float delta, Texture loopTex, Texture humanBodyTex, Texture humanHeadTex, Texture horseTex,
                              Texture poleTex, boolean rotate){
        TexCylinder texCylinder = new TexCylinder();
        TexCube texCube = new TexCube();
        Cube cube = new Cube();
        Loop loop = new Loop();
        Cylinder cylinder = new Cylinder();
        Cone cone = new Cone();
        Human human = new Human();

        float theta = (float) (delta * 2 * Math.PI);
        float rotation = (float) Math.cos(theta) * 45;

        glPushMatrix();
        {
            // 底座1
            glTranslatef(0,0,0);
            glRotatef(90,1, 0, 0);
            glColor3f(pink[0], pink[2],pink[3]);
            cylinder.drawCylinder(10.0f, 0.5f, 32);

            glPushMatrix();
            {
                // 底座2
                glTranslatef(0,0,-0.5f);
                glColor3f(orange[0], orange[2],orange[3]);
                glRotatef(theta * 30, 0, 0, 1);
                cylinder.drawCylinder(9,0.5f,32);

                for (int i =0; i < 6; i++) {
                    glPushMatrix();
                    {
                        glRotatef(i * 60, 0, 0, 1);
                        glTranslatef(5.5f,0,-10f);
                        if (rotate){
                            glRotatef(delta * 100, 0, 0, 1);
                        }
                        texCylinder.drawTexCylinder(0.2f, 9, 10, poleTex);
                        glPushMatrix();
                        {
                            // 木马底部
                            if (i == 0 || i == 2 || i ==4){
                                glTranslatef(0,-0.6f,8.5f + rotation/30);
                            } else {
                                glTranslatef(0,-0.6f,8.5f - rotation/30);
                            }
                            texCube.drawTexCube(2.5f,4f,0.1f,horseTex);

                            glPushMatrix();
                            {
                                glTranslatef(0,-1.5f,-0.3f);
                                texCube.drawTexCube(2.5f,1,0.6f,horseTex);
                            }
                            glPopMatrix();

                            glPushMatrix();
                            {
                                glTranslatef(0,2,-0.5f);
                                glRotatef(90,1,0,0);
                                texCube.drawTexCube(2.5f,1,0.1f,horseTex);

                                if (i == 0 || i == 2 || i ==4){
                                    glPushMatrix();
                                    {
                                        glTranslatef(0,-0.3f,3f);
                                        glRotatef(180, 1, 0, 0);
                                        glRotatef(180,0,1,0);
                                        human.drawWhirligigHuman(delta,humanHeadTex,humanBodyTex);
                                    }
                                    glPopMatrix();
                                }
                            }
                            glPopMatrix();

                            glPushMatrix();
                            {
                                glTranslatef(0,-2,-0.5f);
                                glRotatef(90,1,0,0);
                                texCube.drawTexCube(2.5f,1,0.1f,horseTex);
                            }
                            glPopMatrix();

                            glPushMatrix();
                            {
                                glTranslatef(1.25f,0,-0.5f);
                                glRotatef(90,0,1,0);
                                texCube.drawTexCube(1,4,0.1f,horseTex);
                            }
                            glPopMatrix();

                            glPushMatrix();
                            {
                                glTranslatef(-1.25f,0,-0.5f);
                                glRotatef(90,0,1,0);
                                texCube.drawTexCube(1,4,0.1f,horseTex);
                            }
                            glPopMatrix();
                        }
                        glPopMatrix();
                    }
                    glPopMatrix();
                }
            }
            glPopMatrix();

            // 挡板
            glPushMatrix();
            {
                glTranslatef(0,0,-2.5f);
                glColor3f(blue[0],blue[1],blue[2]);
                loop.drawLoop(8f,2, 32, loopTex);
            }
            glPopMatrix();

            glPushMatrix();
            {
                // 中心杆子
                glTranslatef(0,0,-10f);
                texCylinder.drawTexCylinder(0.5f,10,10, poleTex);

                glPushMatrix();
                {
                    glTranslatef(0,0,0);
                    glRotatef(180, 1,0,0);
                    cone.drawCone(9,5,32);

                    glPushMatrix();
                    {
                        glTranslatef(0,0,0);
                        loop.drawLoop(9,0.5f,32, loopTex);
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
