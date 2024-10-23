package objects3D;

import GraphicsObjects.Utils;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.glPopMatrix;

public class Customer {

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

    public Customer(){

    }

    public void drawCustomer(float delta, boolean GoodAnimation, Texture head, Texture body) {
        float theta = (float) (delta * 2 * Math.PI);
        float LimbRotation;
        if (GoodAnimation) {
            LimbRotation = (float) Math.cos(theta) * 45;
        } else {
            LimbRotation = 0;
        }

        Sphere sphere = new Sphere();
        Cylinder cylinder = new Cylinder();


        glPushMatrix();

        {
            glTranslatef(0.0f, 0.5f, 0.0f);
            sphere.drawSphere(0.5f, 32, 32);
            glRotatef(LimbRotation, 0.0f, 1.0f, 0.0f);

            // chest
            glColor3f(green[0], green[1], green[2]);
            glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(green));
            glPushMatrix();
            {
                // Adding texture to the human chest
                TexSphere texture1 = new TexSphere();
                body.bind();
                glTranslatef(0.0f, 0.5f, 0.0f);


                glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);
                Color.white.bind();
                body.bind();

                glEnable(GL_TEXTURE_2D);
                glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
                texture1.DrawTexSphere(0.5f, 32, 32, body);

                glDisable(GL_TEXTURE_2D);

                // neck
                glColor3f(orange[0], orange[1], orange[2]);
                glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
                glPushMatrix();
                {
                    glTranslatef(0.0f, 0.0f, 0.0f);
                    glRotatef(-90.0f, 1.0f, 0.0f, 0.0f);
                    cylinder.drawCylinder(0.15f, 0.7f, 10);

                    // head
                    glColor3f(red[0], red[1], red[2]);
                    glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
                    glPushMatrix();
                    {
                        // Adding texture to the human head
                        TexSphere texture = new TexSphere();
                        head.bind();
                        glTranslatef(0.0f, 0.0f, 1.0f);
                        glRotatef(LimbRotation, 0.0f, 0.0f, 1.0f);

                        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);
                        Color.white.bind();
                        head.bind();

                        glEnable(GL_TEXTURE_2D);
                        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
                        texture.DrawTexSphere(0.5f, 32, 32, head);
                        glDisable(GL_TEXTURE_2D);

                        glPushMatrix();
                        {
                            glTranslatef(0.0f, 0.0f, 0.30f);
                            glRotatef(LimbRotation, 0.0f, 0.0f, 1.0f);
                            glColor3f(cyan[0], cyan[1], cyan[2]);
                            cylinder.drawCylinder(1f,0.1f, 10);
                            glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
                            glPushMatrix();
                            {
                                glTranslatef(0.0f, 0.0f, 0.0f);
                                glRotatef(LimbRotation, 0.0f, 0.0f, 1.0f);
                                glColor3f(cyan[0], cyan[1], cyan[2]);
                                cylinder.drawCylinder(0.45f,0.55f, 10);
                                glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
                                glPopMatrix();
                            }
                            glPopMatrix();
                        }
                        glPopMatrix();

                    }
                    glPopMatrix();

                    // left shoulder
                    glColor3f(blue[0], blue[1], blue[2]);
                    glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
                    glPushMatrix();
                    {
                        glTranslatef(0.5f, 0.4f, 0.0f);
                        sphere.drawSphere(0.25f, 10,10);

                        // left arm
                        glColor3f(orange[0], orange[1], orange[2]);
                        glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
                        glPushMatrix();
                        {
                            glTranslatef(0.0f, 0.0f, 0.0f);
                            glRotatef(90.0f, 1.0f, 0.0f, 0.0f);

                            glRotatef(LimbRotation, 1.0f, 0.0f, 0.0f);
                            // glRotatef(27.5f,0.0f,1.0f,0.0f);
                            cylinder.drawCylinder(0.15f, 0.7f, 10);

                            // left elbow
                            glColor3f(blue[0], blue[1], blue[2]);
                            glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
                            glPushMatrix();
                            {
                                glTranslatef(0.0f, 0.0f, 0.75f);
                                sphere.drawSphere(0.2f, 10,10);

                                // left forearm
                                glColor3f(orange[0], orange[1], orange[2]);
                                glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
                                glPushMatrix();
                                {
                                    glTranslatef(0.0f, 0.0f, 0.0f);
                                    glRotatef(45.0f, 1.0f, 0.0f, 0.0f);
                                    // Adjust the angle of rotation of the small arm as the arm swings to the rear
                                    if (LimbRotation < 0){
                                        glRotatef(LimbRotation, 1.0f, 0.0f, 0.0f);
                                    }
                                    // glRotatef(90.0f,0.0f,1.0f,0.0f);
                                    cylinder.drawCylinder(0.1f, 0.7f, 10);

                                    // left hand
                                    glColor3f(blue[0], blue[1], blue[2]);
                                    glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
                                    glPushMatrix();
                                    {
                                        glTranslatef(0.0f, 0.0f, 0.75f);
                                        sphere.drawSphere(0.2f, 10,10);

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
                    // to chest

                    // right shoulder

                    glColor3f(blue[0], blue[1], blue[2]);
                    glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
                    glPushMatrix();
                    {
                        glTranslatef(-0.5f, 0.4f, 0.0f);
                        sphere.drawSphere(0.25f, 10,10);

                        // right arm
                        glColor3f(orange[0], orange[1], orange[2]);
                        glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
                        glPushMatrix();
                        {
                            glTranslatef(0.0f, 0.0f, 0.0f);
                            glRotatef(90.0f, 1.0f, 0.0f, 0.0f);

                            glRotatef(LimbRotation, -1.0f, 0.0f, 0.0f);
                            // glRotatef(27.5f,0.0f,1.0f,0.0f);
                            cylinder.drawCylinder(0.15f, 0.7f, 10);

                            // right elbow
                            glColor3f(blue[0], blue[1], blue[2]);
                            glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
                            glPushMatrix();
                            {
                                glTranslatef(0.0f, 0.0f, 0.75f);
                                sphere.drawSphere(0.2f, 10,10);

                                // right forearm
                                glColor3f(orange[0], orange[1], orange[2]);
                                glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
                                glPushMatrix();
                                {
                                    glTranslatef(0.0f, 0.0f, 0.0f);
                                    glRotatef(45.0f, 1.0f, 0.0f, 0.0f);

                                    // Adjust the angle of rotation of the small arm as the arm swings to the rear
                                    if (-LimbRotation < 0){
                                        glRotatef(-LimbRotation, 1.0f, 0.0f, 0.0f);
                                    }
                                    // glRotatef(90.0f,0.0f,1.0f,0.0f);
                                    cylinder.drawCylinder(0.1f, 0.7f, 10);

                                    // right hand
                                    glColor3f(blue[0], blue[1], blue[2]);
                                    glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
                                    glPushMatrix();
                                    {
                                        glTranslatef(0.0f, 0.0f, 0.75f);
                                        sphere.drawSphere(0.2f, 10,10);


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

                    // chest

                }
                glPopMatrix();

                glPushMatrix();
                {
                    // shadow
                    glTranslatef(0.0f, -2.0f, 0.0f);
                    glColor3f(black[0], black[1], black[2]);
                    glRotatef(90, 1.0f, 0.0f, 0.0f);
                    cylinder.drawCylinder(1f, 0.1f, 10);
                }
                glPopMatrix();

                // pelvis

                // left hip
                glColor3f(blue[0], blue[1], blue[2]);
                glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
                glPushMatrix();
                {
                    glTranslatef(-0.3f, -0.4f, 0.0f);

                    sphere.drawSphere(0.25f, 10,10);

                    // left high leg
                    glColor3f(orange[0], orange[1], orange[2]);
                    glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
                    glPushMatrix();
                    {
                        glTranslatef(0.0f, 0.0f, 0.0f);
                        glRotatef(0.0f, 0.0f, 0.0f, 0.0f);

                        glRotatef((LimbRotation / 2) + 90, 1.0f, 0.0f, 0.0f);
                        // glRotatef(90.0f,1.0f,0.0f,0.0f);
                        cylinder.drawCylinder(0.2f, 0.7f, 10);

                        // left knee
                        glColor3f(blue[0], blue[1], blue[2]);
                        glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
                        glPushMatrix();
                        {
                            glTranslatef(0.0f, 0.0f, 0.75f);
                            glRotatef(0.0f, 0.0f, 0.0f, 0.0f);
                            sphere.drawSphere(0.25f, 10,10);

                            // left low leg
                            glColor3f(orange[0], orange[1], orange[2]);
                            glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
                            glPushMatrix();
                            {
                                // Adjust the angle of the lower leg rotation as the leg swings to the rear
                                glTranslatef(0.0f, 0.0f, 0.0f);
                                if (LimbRotation <= 0){
                                    glRotatef(LimbRotation,1.0f,0.0f,0.0f);
                                }
                                cylinder.drawCylinder(0.15f, 0.7f, 10);

                                // left foot
                                glColor3f(blue[0], blue[1], blue[2]);
                                glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
                                glPushMatrix();
                                {
                                    glTranslatef(0.0f, 0.0f, 0.75f);
                                    sphere.drawSphere(0.3f, 10,10);

                                    glPushMatrix();
                                    {
                                        glRotatef(90, 1.0f, 0.0f, 0.0f);
                                        cylinder.drawCylinder(0.25f, 0.6f, 10);
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

                // pelvis

                // right hip

                glColor3f(blue[0], blue[1], blue[2]);
                glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
                glPushMatrix();
                {
                    glTranslatef(0.3f, -0.4f, 0.0f);

                    sphere.drawSphere(0.25f, 10,10);

                    // right high leg
                    glColor3f(orange[0], orange[1], orange[2]);
                    glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
                    glPushMatrix();
                    {
                        glTranslatef(0.0f, 0.0f, 0.0f);
                        glRotatef(0.0f, 0.0f, 0.0f, 0.0f);

                        glRotatef((-LimbRotation / 2) + 90, 1.0f, 0.0f, 0.0f);
                        // glRotatef(90.0f,1.0f,0.0f,0.0f);
                        cylinder.drawCylinder(0.2f, 0.7f, 10);

                        // right knee
                        glColor3f(blue[0], blue[1], blue[2]);
                        glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
                        glPushMatrix();
                        {
                            glTranslatef(0.0f, 0.0f, 0.75f);
                            glRotatef(0.0f, 0.0f, 0.0f, 0.0f);
                            sphere.drawSphere(0.25f, 10,10);

                            // right low leg
                            glColor3f(orange[0], orange[1], orange[2]);
                            glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
                            glPushMatrix();
                            {
                                // Adjust the angle of the lower leg rotation as the leg swings to the rear
                                glTranslatef(0.0f, 0.0f, 0.0f);
                                if (-LimbRotation <= 0){
                                    glRotatef(-LimbRotation,1.0f,0.0f,0.0f);
                                }
                                cylinder.drawCylinder(0.15f, 0.7f, 10);

                                // right foot
                                glColor3f(blue[0], blue[1], blue[2]);
                                glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
                                glPushMatrix();
                                {
                                    glTranslatef(0.0f, 0.0f, 0.75f);
                                    sphere.drawSphere(0.3f, 10,10);
//
                                    glPushMatrix();
                                    {
                                        glRotatef(90, 1.0f, 0.0f, 0.0f);
                                        cylinder.drawCylinder(0.25f, 0.6f, 10);
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

    public void drawCustomer2(float delta, boolean GoodAnimation, Texture head, Texture body) {
        float theta = (float) (delta * 2 * Math.PI);
        float LimbRotation;
        if (GoodAnimation) {
            LimbRotation = (float) Math.cos(theta) * 45;
        } else {
            LimbRotation = 0;
        }

        Sphere sphere = new Sphere();
        Cylinder cylinder = new Cylinder();
        IceCream iceCream = new IceCream();


        glPushMatrix();

        {
            glTranslatef(0.0f, 0.5f, 0.0f);
            sphere.drawSphere(0.5f, 10,10);


            // chest
            glColor3f(green[0], green[1], green[2]);
            glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(green));
            glPushMatrix();
            {
                // Adding texture to the human chest
                TexSphere texture1 = new TexSphere();
                body.bind();
                glTranslatef(0.0f, 0.5f, 0.0f);


                glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);
                Color.white.bind();
                body.bind();

                glEnable(GL_TEXTURE_2D);
                glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
                texture1.DrawTexSphere(0.5f, 32, 32, body);

                glDisable(GL_TEXTURE_2D);

                // neck
                glColor3f(orange[0], orange[1], orange[2]);
                glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
                glPushMatrix();
                {
                    glTranslatef(0.0f, 0.0f, 0.0f);
                    glRotatef(-90.0f, 1.0f, 0.0f, 0.0f);
                    cylinder.drawCylinder(0.15f, 0.7f, 10);

                    // head
                    glColor3f(red[0], red[1], red[2]);
                    glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
                    glPushMatrix();
                    {
                        // Adding texture to the human head
                        TexSphere texture = new TexSphere();
                        head.bind();
                        glTranslatef(0.0f, 0.0f, 1.0f);
                        glRotatef(LimbRotation, 0.0f, 0.0f, 1.0f);

                        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);
                        Color.white.bind();
                        head.bind();

                        glEnable(GL_TEXTURE_2D);
                        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
                        texture.DrawTexSphere(0.5f, 32, 32, head);
                        glDisable(GL_TEXTURE_2D);

                        glPushMatrix();
                        {
                            glTranslatef(0.0f, 0.0f, 0.30f);
                            glRotatef(LimbRotation, 0.0f, 0.0f, 1.0f);
                            glColor3f(cyan[0], cyan[1], cyan[2]);
                            cylinder.drawCylinder(1f,0.1f, 10);
                            glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
                            glPushMatrix();
                            {
                                glTranslatef(0.0f, 0.0f, 0.0f);
                                glRotatef(LimbRotation, 0.0f, 0.0f, 1.0f);
                                glColor3f(cyan[0], cyan[1], cyan[2]);
                                cylinder.drawCylinder(0.45f,0.55f, 10);
                                glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
                                glPopMatrix();
                            }
                            glPopMatrix();
                        }
                        glPopMatrix();

                    }
                    glPopMatrix();

                    // left shoulder
                    glColor3f(blue[0], blue[1], blue[2]);
                    glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
                    glPushMatrix();
                    {
                        glTranslatef(0.5f, 0.4f, 0.0f);
                        sphere.drawSphere(0.25f, 10,10);

                        // left arm
                        glColor3f(orange[0], orange[1], orange[2]);
                        glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
                        glPushMatrix();
                        {
                            glTranslatef(0.0f, 0.0f, 0.0f);
                            glRotatef(90.0f, 1.0f, 0.0f, 0.0f);

                            glRotatef(LimbRotation, 1.0f, 0.0f, 0.0f);
                            // glRotatef(27.5f,0.0f,1.0f,0.0f);
                            cylinder.drawCylinder(0.15f, 0.7f, 10);

                            // left elbow
                            glColor3f(blue[0], blue[1], blue[2]);
                            glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
                            glPushMatrix();
                            {
                                glTranslatef(0.0f, 0.0f, 0.75f);
                                sphere.drawSphere(0.2f, 10,10);

                                // left forearm
                                glColor3f(orange[0], orange[1], orange[2]);
                                glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
                                glPushMatrix();
                                {
                                    glTranslatef(0.0f, 0.0f, 0.0f);
                                    glRotatef(45.0f, 1.0f, 0.0f, 0.0f);
                                    // Adjust the angle of rotation of the small arm as the arm swings to the rear
                                    if (LimbRotation < 0){
                                        glRotatef(LimbRotation, 1.0f, 0.0f, 0.0f);
                                    }
                                    // glRotatef(90.0f,0.0f,1.0f,0.0f);
                                    cylinder.drawCylinder(0.1f, 0.7f, 10);

                                    // left hand
                                    glColor3f(blue[0], blue[1], blue[2]);
                                    glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
                                    glPushMatrix();
                                    {
                                        glTranslatef(0.0f, 0.0f, 0.75f);
                                        sphere.drawSphere(0.2f, 10,10);

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
                    // to chest

                    // right shoulder

                    glColor3f(blue[0], blue[1], blue[2]);
                    glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
                    glPushMatrix();
                    {
                        glTranslatef(-0.5f, 0.4f, 0.0f);
                        sphere.drawSphere(0.25f, 10,10);

                        // right arm
                        glColor3f(orange[0], orange[1], orange[2]);
                        glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
                        glPushMatrix();
                        {
                            glTranslatef(0.0f, 0.0f, 0.0f);
                            glRotatef(90.0f, 1.0f, 0.0f, 0.0f);

                            glRotatef(LimbRotation, -1.0f, 0.0f, 0.0f);
                            // glRotatef(27.5f,0.0f,1.0f,0.0f);
                            cylinder.drawCylinder(0.15f, 0.7f, 10);

                            // right elbow
                            glColor3f(blue[0], blue[1], blue[2]);
                            glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
                            glPushMatrix();
                            {
                                glTranslatef(0.0f, 0.0f, 0.75f);
                                sphere.drawSphere(0.2f, 10,10);

                                // right forearm
                                glColor3f(orange[0], orange[1], orange[2]);
                                glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
                                glPushMatrix();
                                {
                                    glTranslatef(0.0f, 0.0f, 0.0f);
                                    glRotatef(45.0f, 1.0f, 0.0f, 0.0f);

                                    // Adjust the angle of rotation of the small arm as the arm swings to the rear
                                    if (-LimbRotation < 0){
                                        glRotatef(-LimbRotation, 1.0f, 0.0f, 0.0f);
                                    }
                                    // glRotatef(90.0f,0.0f,1.0f,0.0f);
                                    cylinder.drawCylinder(0.1f, 0.7f, 10);

                                    // right hand
                                    glColor3f(blue[0], blue[1], blue[2]);
                                    glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
                                    glPushMatrix();
                                    {
                                        glTranslatef(0.0f, 0.0f, 0.75f);
                                        sphere.drawSphere(0.2f, 10,10);

                                        glPushMatrix();
                                        {
                                            glTranslatef(0.0f, -0.5f, 0.0f);
                                            glRotatef(-90, 1.0f, 0.0f, 0.0f);
                                            iceCream.drawIceCream();
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

                    // chest

                }
                glPopMatrix();

                glPushMatrix();
                {
                    // shadow
                    glTranslatef(0.0f, -2.0f, 0.0f);
                    glColor3f(black[0], black[1], black[2]);
                    glRotatef(90, 1.0f, 0.0f, 0.0f);
                    cylinder.drawCylinder(1f, 0.1f, 10);
                }
                glPopMatrix();
                // pelvis

                // left hip
                glColor3f(blue[0], blue[1], blue[2]);
                glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
                glPushMatrix();
                {
                    glTranslatef(-0.3f, -0.4f, 0.0f);

                    sphere.drawSphere(0.25f, 10,10);

                    // left high leg
                    glColor3f(orange[0], orange[1], orange[2]);
                    glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
                    glPushMatrix();
                    {
                        glTranslatef(0.0f, 0.0f, 0.0f);
                        glRotatef(0.0f, 0.0f, 0.0f, 0.0f);

                        glRotatef((LimbRotation / 2) + 90, 1.0f, 0.0f, 0.0f);
                        // glRotatef(90.0f,1.0f,0.0f,0.0f);
                        cylinder.drawCylinder(0.2f, 0.7f, 10);

                        // left knee
                        glColor3f(blue[0], blue[1], blue[2]);
                        glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
                        glPushMatrix();
                        {
                            glTranslatef(0.0f, 0.0f, 0.75f);
                            glRotatef(0.0f, 0.0f, 0.0f, 0.0f);
                            sphere.drawSphere(0.25f, 10,10);

                            // left low leg
                            glColor3f(orange[0], orange[1], orange[2]);
                            glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
                            glPushMatrix();
                            {
                                // Adjust the angle of the lower leg rotation as the leg swings to the rear
                                glTranslatef(0.0f, 0.0f, 0.0f);
                                if (LimbRotation <= 0){
                                    glRotatef(LimbRotation,1.0f,0.0f,0.0f);
                                }
                                cylinder.drawCylinder(0.15f, 0.7f, 10);

                                // left foot
                                glColor3f(blue[0], blue[1], blue[2]);
                                glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
                                glPushMatrix();
                                {
                                    glTranslatef(0.0f, 0.0f, 0.75f);
                                    sphere.drawSphere(0.3f, 10,10);

                                    glPushMatrix();
                                    {
                                        glRotatef(90, 1.0f, 0.0f, 0.0f);
                                        cylinder.drawCylinder(0.25f, 0.6f, 10);
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

                // pelvis

                // right hip

                glColor3f(blue[0], blue[1], blue[2]);
                glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
                glPushMatrix();
                {
                    glTranslatef(0.3f, -0.4f, 0.0f);

                    sphere.drawSphere(0.25f, 10,10);

                    // right high leg
                    glColor3f(orange[0], orange[1], orange[2]);
                    glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
                    glPushMatrix();
                    {
                        glTranslatef(0.0f, 0.0f, 0.0f);
                        glRotatef(0.0f, 0.0f, 0.0f, 0.0f);

                        glRotatef((-LimbRotation / 2) + 90, 1.0f, 0.0f, 0.0f);
                        // glRotatef(90.0f,1.0f,0.0f,0.0f);
                        cylinder.drawCylinder(0.2f, 0.7f, 10);

                        // right knee
                        glColor3f(blue[0], blue[1], blue[2]);
                        glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
                        glPushMatrix();
                        {
                            glTranslatef(0.0f, 0.0f, 0.75f);
                            glRotatef(0.0f, 0.0f, 0.0f, 0.0f);
                            sphere.drawSphere(0.25f, 10,10);

                            // right low leg
                            glColor3f(orange[0], orange[1], orange[2]);
                            glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
                            glPushMatrix();
                            {
                                // Adjust the angle of the lower leg rotation as the leg swings to the rear
                                glTranslatef(0.0f, 0.0f, 0.0f);
                                if (-LimbRotation <= 0){
                                    glRotatef(-LimbRotation,1.0f,0.0f,0.0f);
                                }
                                cylinder.drawCylinder(0.15f, 0.7f, 10);

                                // right foot
                                glColor3f(blue[0], blue[1], blue[2]);
                                glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
                                glPushMatrix();
                                {
                                    glTranslatef(0.0f, 0.0f, 0.75f);
                                    sphere.drawSphere(0.3f, 10,10);
//
                                    glPushMatrix();
                                    {
                                        glRotatef(90, 1.0f, 0.0f, 0.0f);
                                        cylinder.drawCylinder(0.25f, 0.6f, 10);
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
}
