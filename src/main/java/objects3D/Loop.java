package objects3D;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;

public class Loop {


    public Loop() {
    }

    // remember to use Math.PI isntead PI
    // Implement using notes and examine Tetrahedron to aid in the coding  look at lecture  7 , 7b and 8
    public void drawLoop(float radius, float height, int nSegments, Texture texture) {
        float texS = 0.0f, texT = 1.0f;
        texture.bind();
        Color.white.bind();
        glClearColor(0,0,0,0);
        glEnable(GL_TEXTURE_2D);

        GL11.glBegin(GL11.GL_TRIANGLES);
        // use for loop to draw the cylinder
        for (float i = 0.0F; i < nSegments; i+= 1.0){

            // set the first angle and the next angle
            float angle = (float) (Math.PI * i * 2.0 / nSegments);
            float nextAngle = (float) (Math.PI * (i + 1.0) * 2.0 / nSegments);

            // get the start point
            float x1 = (float) (radius * Math.sin(angle)), y1 = (float) (radius * Math.cos(angle));
            float x2 = (float) (radius * Math.sin(nextAngle)), y2 = (float) (radius * Math.cos(nextAngle));

            // draw the top triangle
            GL11.glNormal3f(x1, y1,0.0f);
            glTexCoord2f(texS + 0.0f, 0.0f);
            GL11.glVertex3f(x1, y1, 0.0F);

            GL11.glNormal3f(x2, y2,0.0f);
            glTexCoord2f(texS + 1.0f / nSegments, texT);
            GL11.glVertex3f(x2, y2, height);

            GL11.glNormal3f(x1, y1,0.0f);
            glTexCoord2f(texS + 0.0f, texT);
            GL11.glVertex3f(x1, y1, height);

            // draw the bottom triangle
            GL11.glNormal3f(x1, y1,0.0f);
            glTexCoord2f(texS + 0.0f, 0.0f);
            GL11.glVertex3f(x1, y1, 0.0F);

            GL11.glNormal3f(x2, y2,0.0f);
            glTexCoord2f(texS + 1.0f / nSegments, 0.0f);
            GL11.glVertex3f(x2, y2, 0.0F);

            GL11.glNormal3f(x2, y2,0.0f);
            glTexCoord2f(texS + 1.0f / nSegments, texT);
            GL11.glVertex3f(x2, y2, height);

            texS = texS + 1.0f / nSegments;
        }
        GL11.glEnd();
        glDisable(GL_TEXTURE_2D);
    }
}
