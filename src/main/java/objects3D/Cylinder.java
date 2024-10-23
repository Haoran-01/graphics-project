package objects3D;

import org.lwjgl.opengl.GL11;

public class Cylinder {


	public Cylinder() {
	}

	// remember to use Math.PI isntead PI
	// Implement using notes and examine Tetrahedron to aid in the coding  look at lecture  7 , 7b and 8
	public void drawCylinder(float radius, float height, int nSegments ) {

		GL11.glBegin(GL11.GL_TRIANGLES);
		// use for loop to draw the cylinder
		for (float i = 0.0F; i < nSegments; i+= 1.0){

			// set the first angle and the next angle
			float angle = (float) (Math.PI * i * 2.0 / nSegments);
			float nextAngle = (float) (Math.PI * (i + 1.0) * 2.0 / nSegments);

			// get the start point
			float x1 = (float) (radius * Math.sin(angle)), y1 = (float) (radius * Math.cos(angle));
			float x2 = (float) (radius * Math.sin(nextAngle)), y2 = (float) (radius * Math.cos(nextAngle));


			// draw the top
			GL11.glNormal3f(0.0f, 0.0f, height);
			GL11.glVertex3f(0.0f, 0.0f, height);
			GL11.glNormal3f(x2, y2,0.0f);
			GL11.glVertex3f(x2, y2, height);
			GL11.glNormal3f(x1, y1,0.0f);
			GL11.glVertex3f(x1, y1, height);

			// draw the bottom
			GL11.glNormal3f(0.0f, 0.0f, 0.0f);
			GL11.glVertex3f(0.0f, 0.0f, 0.0f);
			GL11.glNormal3f(x1, y1,0.0f);
			GL11.glVertex3f(x1, y1, 0.0F);
			GL11.glNormal3f(x2, y2,0.0f);
			GL11.glVertex3f(x2, y2, 0.0F);

			// draw the top triangle
			GL11.glNormal3f(x1, y1,0.0f);
			GL11.glVertex3f(x1, y1, 0.0F);
			GL11.glNormal3f(x2, y2,0.0f);
			GL11.glVertex3f(x2, y2, height);
			GL11.glNormal3f(x1, y1,0.0f);
			GL11.glVertex3f(x1, y1, height);

			// draw the bottom triangle
			GL11.glNormal3f(x1, y1,0.0f);
			GL11.glVertex3f(x1, y1, 0.0F);
			GL11.glNormal3f(x2, y2,0.0f);
			GL11.glVertex3f(x2, y2, 0.0F);
			GL11.glNormal3f(x2, y2,0.0f);
			GL11.glVertex3f(x2, y2, height);
		}
		GL11.glEnd();
	}
}
