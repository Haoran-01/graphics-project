package objects3D;

import org.lwjgl.opengl.GL11;

public class Sphere {


	public Sphere() {

	}
	// Implement using notes and examine Tetrahedron to aid in the coding  look at lecture  7 , 7b and 8 
	// 7b should be your primary source, we will cover more about circles in later lectures to understand why the code works 
	public void drawSphere(float radius,float nSlices,float nSegments) {
		// set the theta and phi
		float inctheta = (float) ((2.0f * Math.PI) / nSlices);
		float incphi = (float) (Math.PI / nSegments);

		// start draw the sphere
		GL11.glBegin(GL11.GL_QUADS);
		for (float theta = (float) -Math.PI; theta < Math.PI; theta+=inctheta){
			for (float phi = (float) -(Math.PI/2.0f); phi<(Math.PI/2.0f); phi+=incphi){

				float nextInctheta = theta + inctheta;
				float nextIncphi = phi + incphi;

				// calculate the point x, y, z value
				float x1 = (float) (radius * Math.cos(theta) * Math.cos(phi));
				float x2 = (float) (radius * Math.cos(nextInctheta) * Math.cos(phi));
				float x3 = (float) (radius * Math.cos(theta) * Math.cos(nextIncphi));
				float x4 = (float) (radius * Math.cos(nextInctheta) * Math.cos(nextIncphi));

				float y1 = (float) (radius * Math.cos(phi) * Math.sin(theta));
				float y2 = (float) (radius * Math.cos(phi) * Math.sin(nextInctheta));
				float y3 = (float) (radius * Math.cos(nextIncphi) * Math.sin(theta));
				float y4 = (float) (radius * Math.cos(nextIncphi) * Math.sin(nextInctheta));

				float z1 = (float) (radius * Math.sin(phi));
				float z2 = (float) (radius * Math.sin(phi));
				float z3 = (float) (radius * Math.sin(nextIncphi));
				float z4 = (float) (radius * Math.sin(nextIncphi));

				GL11.glNormal3f(x1,y1,z1);
				GL11.glVertex3f(x1,y1,z1);
				GL11.glNormal3f(x2,y2,z2);
				GL11.glVertex3f(x2,y2,z2);
				GL11.glNormal3f(x4,y4,z4);
				GL11.glVertex3f(x4,y4,z4);
				GL11.glNormal3f(x3,y3,z3);
				GL11.glVertex3f(x3,y3,z3);
			}
		}
		GL11.glEnd();
	}
}

 