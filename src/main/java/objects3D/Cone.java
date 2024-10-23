package objects3D;

import org.lwjgl.opengl.GL11;

public class Cone {

    public void drawCone(float radius, float height, int nSegments){

        GL11.glBegin(GL11.GL_TRIANGLES);

        for (float i = 0.0f; i < nSegments; i ++) { // a loop around circumference of a tube
            float angle = (float) (Math.PI * i * 2.0 / nSegments);
            float nextAngle = (float) (Math.PI * (i + 1.0) * 2.0 / nSegments);

            //compute sin & cosine
            float x1 = (float) (radius * Math.sin(angle)), y1 = (float) (radius * Math.cos(angle));
            float x2 = (float) (radius * Math.sin(nextAngle)), y2 = (float) (radius * Math.cos(nextAngle));

            GL11.glNormal3f(x1, y1, 0.0f);
            GL11.glVertex3f(x1, y1, 0.0f);

            GL11.glNormal3f(x1, y1, 0.0f);
            GL11.glVertex3f(x2, y2, 0.0f);

            GL11.glNormal3f(x1, y1, 0.0f);
            GL11.glVertex3f(0.0f, 0.0f, 0.0f);

            GL11.glNormal3f(x1, y1, 0.0f);
            GL11.glVertex3f(x1, y1, 0.0f);

            GL11.glNormal3f(x1, y1, 0.0f);
            GL11.glVertex3f(x2, y2, 0.0f);

            GL11.glNormal3f(0, 0, height);
            GL11.glVertex3f(0.0f, 0.0f, height);
        }
        GL11.glEnd();

    }
}
