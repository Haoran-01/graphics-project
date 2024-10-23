package objects3D;
import static org.lwjgl.opengl.GL11.*;
import org.newdawn.slick.opengl.Texture;

public class Corsair {

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

    public Corsair(){

    }

    public void drawCorsair(float delta, Texture humanBodyTex, Texture humanHeadTex, Texture boatTex, Texture topTex,
                            Texture stringTex, Texture poleTex, Boolean startingWithUser){
        float theta = (float) (delta * 2 * Math.PI);
        float rotation = (float) Math.cos(theta * 2) * 45;

        Cube cube = new Cube();
        TexCube texCube = new TexCube();
        Cylinder cylinder = new Cylinder();
        TexCylinder texCylinder = new TexCylinder();
        Sphere sphere = new Sphere();
        TexSphere texSphere = new TexSphere();
        Human human = new Human();

        glPushMatrix();
        {
            glTranslatef(0,0,0);
            glRotatef(90,1,0,0);
            glColor3f(black[0], black[1], black[2]);
            cube.drawCube(18,18,0.5f);

            glPushMatrix();
            {
                glTranslatef(0,0,-15f);
                glRotatef(30, 1, -1, 0);
                texCylinder.drawTexCylinder(0.2f, 17.5f,10,poleTex);
            }
            glPopMatrix();

            glPushMatrix();
            {
                glTranslatef(0,0,-15f);
                glRotatef(30, 1, 1, 0);
                texCylinder.drawTexCylinder(0.2f, 17.5f,10,poleTex);
            }
            glPopMatrix();

            glPushMatrix();
            {
                glTranslatef(0,0,-15f);
                glRotatef(-30, 1, -1, 0);
                texCylinder.drawTexCylinder(0.2f, 17.5f,10,poleTex);
            }
            glPopMatrix();

            glPushMatrix();
            {
                glTranslatef(0,0,-15f);
                glRotatef(-30, 1, 1, 0);
                texCylinder.drawTexCylinder(0.2f, 17.5f,10,poleTex);

                glPushMatrix();
                {
                    // 顶端球
                    glTranslatef(0,0,0);
                    texSphere.DrawTexSphere(1f, 15, 15, topTex);
                    glPushMatrix();
                    {
                        // 旋转球
                        glTranslatef(0,0,0);
                        glRotatef(30,1,1,0);
                        if (delta == 0){
                            glRotatef(0, 0, 0, 0);
                        } else {
                            glRotatef(rotation, 1, 0, 0);
                        }
                        sphere.drawSphere(0.1f,4,4);
                        System.out.println(rotation);

                        glPushMatrix();
                        {
                            // 支撑柱子
                            glTranslatef(0,0,0);
                            glRotatef(38,1,0.49f,0);
                            texCylinder.drawTexCylinder(0.1f, 12.8f,10, stringTex);
                        }
                        glPopMatrix();

                        glPushMatrix();
                        {
                            // 支撑柱子
                            glTranslatef(0,0,0);
                            glRotatef(-38,1,0.49f,0);
                            texCylinder.drawTexCylinder(0.1f, 12.8f,10, stringTex);
                        }
                        glPopMatrix();

                        glPushMatrix();
                        {
                            // 支撑柱子
                            glTranslatef(0,0,0);
                            glRotatef(-38,1,-0.49f,0);
                            texCylinder.drawTexCylinder(0.1f, 12.8f,10, stringTex);
                        }
                        glPopMatrix();

                        glPushMatrix();
                        {
                            // 支撑柱子
                            glTranslatef(0,0,0);
                            glRotatef(-38,-1,0.49f,0);
                            texCylinder.drawTexCylinder(0.1f, 12.8f,10, stringTex);
                        }

                        glRotatef(38,-1,0.49f,0);
                        glPushMatrix();
                        {
                            // 船底
                            glTranslatef(0,0,13);
                            glColor3f(green[0],green[1],green[2]);
                            texCube.drawTexCube(6.8f,14,0.2f,boatTex);

                            // 座位1
                            glPushMatrix();
                            {
                                glTranslatef(0,6,-1);
                                glColor3f(blue[0],blue[1],blue[2]);
                                texCube.drawTexCube(6.8f,2,1,boatTex);

                                glPushMatrix();
                                {
                                    glTranslatef(0,-0.5f,-0.5f);
                                    glRotatef(-90,1,0,0);
                                    human.drawCorsairHuman(delta, humanHeadTex, humanBodyTex);
                                }
                                glPopMatrix();
                            }
                            glPopMatrix();

                            // 座位2
                            glPushMatrix();
                            {
                                glTranslatef(0,-6,-1);
                                glColor3f(blue[0],blue[1],blue[2]);
                                texCube.drawTexCube(6.8f,2,1,boatTex);

                                if (startingWithUser){
                                    glPushMatrix();
                                    {
                                        glTranslatef(0,0.5f,-0.5f);
                                        glRotatef(-90,1,0,0);
                                        glRotatef(180, 0,1,0);
                                        human.drawCorsairHuman(delta, humanHeadTex, humanBodyTex);
                                    }
                                    glPopMatrix();
                                }
                            }
                            glPopMatrix();

                            glPushMatrix();
                            {
                                // 左侧板
                                glTranslatef(0,7,-1.5f);
                                glRotatef(90,1,0,0);
                                texCube.drawTexCube(6.8f,3.2f,0.3f,boatTex);
                            }
                            glPopMatrix();

                            glPushMatrix();
                            {
                                // 右侧板
                                glTranslatef(0,-7,-1.5f);
                                glRotatef(90,1,0,0);
                                texCube.drawTexCube(6.8f,3.2f,0.3f,boatTex);
                            }
                            glPopMatrix();

                            glPushMatrix();
                            {
                                // 前挡板
                                glTranslatef(3.4f, 0,-0.5f);
                                glRotatef(90,0,1,0);
                                texCube.drawTexCube(1f,14,0.3f,boatTex);

                                glPushMatrix();
                                {
                                    glTranslatef(1f,4.5f,0);
                                    texCube.drawTexCube(1f,5f,0.3f,boatTex);
                                }
                                glPopMatrix();

                                glPushMatrix();
                                {
                                    glTranslatef(1f,-4.5f,0);
                                    texCube.drawTexCube(1f,5f,0.3f,boatTex);
                                }
                                glPopMatrix();
                            }
                            glPopMatrix();

                            glPushMatrix();
                            {
                                // 后挡板
                                glTranslatef(-3.4f, 0,-0.5f);
                                glRotatef(90,0,1,0);
                                texCube.drawTexCube(1f,14,0.3f,boatTex);

                                glPushMatrix();
                                {
                                    glTranslatef(1f,4.5f,0);
                                    texCube.drawTexCube(1f,5f,0.3f,boatTex);
                                }
                                glPopMatrix();

                                glPushMatrix();
                                {
                                    glTranslatef(1f,-4.5f,0);
                                    texCube.drawTexCube(1f,5f,0.3f,boatTex);
                                }
                                glPopMatrix();
                            }
                            glPopMatrix();
                        }
                        glPopMatrix();

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
