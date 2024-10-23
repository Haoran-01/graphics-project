package objects3D;

import static org.lwjgl.opengl.GL11.*;

import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import GraphicsObjects.Point4f;
import GraphicsObjects.Vector4f;

public class TexCube2 {

    public TexCube2() {

    }

    public void drawTexCube2(float length, float width, float height, Texture[] textures) {
        // Set out all the points and faces of the square

		/*Point4f vertices[] = { new Point4f(1.0f, 1.0f, 1.0f, 0.0f), new Point4f(-1.0f, 1.0f, 1.0f, 0.0f),
				new Point4f(-1.0f, -1.0f, 1.0f, 0.0f), new Point4f(1.0f, -1.0f, 1.0f, 0.0f),
				new Point4f(1.0f, 1.0f, -1.0f, 0.0f), new Point4f(1.0f, -1.0f, -1.0f, 0.0f),
				new Point4f(-1.0f, 1.0f, -1.0f, 0.0f), new Point4f(-1.0f, -1.0f, -1.0f, 0.0f) };*/

        Point4f vertices[] = { new Point4f(-length/2, -width/2, -height/2, 0.0f), new Point4f(-length/2, -width/2, height/2, 0.0f),
                new Point4f(-length/2, width/2, -height/2, 0.0f), new Point4f(-length/2, width/2, height/2, 0.0f),
                new Point4f(length/2, -width/2, -height/2, 0.0f), new Point4f(length/2, -width/2, height/2, 0.0f),
                new Point4f(length/2, width/2, -height/2, 0.0f), new Point4f(length/2, width/2, height/2, 0.0f) };


        int faces[][] = { { 0, 4, 5, 1 }, { 0, 2, 6, 4 }, { 0, 1, 3, 2 }, { 4, 6, 7, 5 }, { 1, 5, 7, 3 },
                { 2, 3, 7, 6 } };

        // begin draw the cube
        glDisable(GL_LIGHTING);
        for (int face = 0; face < 6; face++){
            textures[face].bind();
            Color.white.bind();
            glClearColor(0,0,0,0);
            glEnable(GL_TEXTURE_2D);
            glBegin(GL_QUADS);

            // calculate the normal vector
            Vector4f v = vertices[faces[face][1]].MinusPoint(vertices[faces[face][0]]);
            Vector4f w = vertices[faces[face][3]].MinusPoint(vertices[faces[face][0]]);
            Vector4f normal = v.cross(w).Normal();
            glNormal3f(normal.x, normal.y, normal.z);

            // Set the mapping of the cube to the material image
            glTexCoord2f(0.0f, 0.0f);
            glVertex3f(vertices[faces[face][0]].x, vertices[faces[face][0]].y, vertices[faces[face][0]].z);

            glTexCoord2f(0.0f, 0.85f);
            glVertex3f(vertices[faces[face][1]].x, vertices[faces[face][1]].y, vertices[faces[face][1]].z);

            glTexCoord2f(0.85f, 0.85f);
            glVertex3f(vertices[faces[face][2]].x, vertices[faces[face][2]].y, vertices[faces[face][2]].z);

            glTexCoord2f(0.85f, 0.0f);
            glVertex3f(vertices[faces[face][3]].x, vertices[faces[face][3]].y, vertices[faces[face][3]].z);
            glEnd();
            glDisable(GL_TEXTURE_2D);
        }
        glEnable(GL_LIGHTING);

    }

}

/*
 *
 *
 * }
 *
 */
