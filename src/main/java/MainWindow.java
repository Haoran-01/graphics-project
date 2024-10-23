
import java.io.IOException;
import java.nio.FloatBuffer;
import java.util.Objects;

import net.java.games.input.*;
import objects3D.*;
import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.*;

import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import GraphicsObjects.Arcball;
import GraphicsObjects.Utils;

//Main windows class controls and creates the 3D virtual world , please do not change this class but edit the other classes to complete the assignment. 
// Main window is built upon the standard Helloworld LWJGL class which I have heavily modified to use as your standard openGL environment. 
// 

// Do not touch this class, I will be making a version of it for your 3rd Assignment 
public class MainWindow {

	private boolean MouseOnepressed = true;
	private boolean dragMode = false;
	private boolean BadAnimation = true;
	private boolean isWalk = false;
	private boolean closeLight = false;
	private boolean stopWhirligig ,changeOrthWhirligig = false;
	private boolean stopCorsair = false;
	private boolean goUp, playingCorsair, buyIceCream= false;
	private boolean stopSkyWheel, changeOrthSkyWheel = false;
	private boolean Earth = false;
	private String keyType = "";
	/** position of pointer */
	float x = 1600, y = 450, z = 0;
	float movingSpeed = 0.5f;

/*	float positionX = 1900, positionY = 450, positionZ = 1000;*/
	float angle;
	/** angle of rotation */
	float rotation = 0;
	/** time at last frame */
	long lastFrame;
	/** frames per second */
	int fps;
	/** last fps time */
	long lastFPS;

	long myDelta = 0; // to use for animation
	float Alpha = 0; // to use for animation
	long StartTime; // beginAnimiation

	// 视角拖动
	Arcball MyArcball = new Arcball();

	// 画图像网格
	boolean DRAWGRID = true;

	boolean waitForKeyrelease, waitForKeyrelease2, waitForKeyrelease3, waitForKeyrelease4, waitForKeyrelease5, waitForKeyrelease6, waitForKeyrelease7, waitForKeyrelease8= true;
	/** Mouse movement */
	int LastMouseX = -1;
	int LastMouseY = -1;

	float pullX = 0.0f; // arc ball X cord.
	float pullY = 0.0f; // arc ball Y cord.

	int OrthoNumber = 1200; // using this for screen size, making a window of 1200 x 800 so aspect ratio 3:2
							// // do not change this for assignment 3 but you can change everything for your
							// project

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

	// static GLfloat light_position[] = {0.0, 100.0, 100.0, 0.0};

	// support method to aid in converting a java float array into a Floatbuffer
	// which is faster for the opengl layer to process

	public void start() {

		StartTime = getTime();
		try {
			Display.setDisplayMode(new DisplayMode(1200, 800));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}

		initGL(); // init OpenGL
		getDelta(); // call once before loop to initialise lastFrame
		lastFPS = getTime(); // call before loop to initialise fps timer

		while (!Display.isCloseRequested()) {
			int delta = getDelta();
			update(delta);
			renderGL();
			Display.update();
			Display.sync(120); // cap fps to 120fps
		}

		Display.destroy();
	}

	public void update(int delta) {
		// rotate quad
		// rotation += 0.01f * delta;

		int MouseX = Mouse.getX();
		int MouseY = Mouse.getY();
		int WheelPostion = Mouse.getDWheel();

		boolean MouseButonPressed = Mouse.isButtonDown(0);

		// 检测鼠标拖动事件
		if (MouseButonPressed && !MouseOnepressed) {
			MouseOnepressed = true;
			// System.out.println("Mouse drag mode");
			MyArcball.startBall(MouseX, MouseY, 1200, 800);
			dragMode = true;

			// 鼠标未点
		} else if (!MouseButonPressed) {
			// System.out.println("Mouse drag mode end ");
			MouseOnepressed = false;
			dragMode = false;
		}

		// 鼠标拖动模型
		if (dragMode) {
			MyArcball.updateBall(MouseX, MouseY, 1200, 800);
		}

		if (WheelPostion > 0) {
			OrthoNumber += 10;
		}

		// 鼠标滚轮
		if (WheelPostion < 0) {
			OrthoNumber -= 10;
			if (OrthoNumber < 610) {
				OrthoNumber = 610;
			}
		}

		/* rest key is R */
		if (Keyboard.isKeyDown(Keyboard.KEY_R))
			MyArcball.reset();

		/* bad animation can be turn on or off using A key) */

		if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
			z += movingSpeed * delta;
			isWalk = true;
			keyType = KeyType.KEY_DOWN_A;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			z -= movingSpeed * delta;
			isWalk = true;
			keyType = KeyType.KEY_DOWN_D;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
			x -= movingSpeed * delta;
			isWalk = true;
			keyType = KeyType.KEY_DOWN_W;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			x += movingSpeed * delta;
			isWalk = true;
			keyType = KeyType.KEY_DOWN_S;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_A) && Keyboard.isKeyDown(Keyboard.KEY_W)) {
			z -= movingSpeed/5 * delta;
			x += movingSpeed/5 * delta;
			isWalk = true;
			keyType = KeyType.KEY_DOWN_A_W;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_A) && Keyboard.isKeyDown(Keyboard.KEY_S)) {
			z -= movingSpeed/5 * delta;
			x -= movingSpeed/5 * delta;
			isWalk = true;
			keyType = KeyType.KEY_DOWN_A_S;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_D) && Keyboard.isKeyDown(Keyboard.KEY_W)) {
			z += movingSpeed/5 * delta;
			x += movingSpeed/5 * delta;
			isWalk = true;
			keyType = KeyType.KEY_DOWN_D_W;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_D) && Keyboard.isKeyDown(Keyboard.KEY_S)) {
			z += movingSpeed/5 * delta;
			x -= movingSpeed/5 * delta;
			isWalk = true;
			keyType = KeyType.KEY_DOWN_D_S;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)){
			movingSpeed = 1;
		}
		if (!Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) && !Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)){
			movingSpeed = 0.5f;
		}
		if (!Keyboard.isKeyDown(Keyboard.KEY_A) && !Keyboard.isKeyDown(Keyboard.KEY_W) && !Keyboard.isKeyDown(Keyboard.KEY_S) && !Keyboard.isKeyDown(Keyboard.KEY_D)){
			isWalk = false;
		}
		
		if (waitForKeyrelease) // check done to see if key is released
		{
			if (Keyboard.isKeyDown(Keyboard.KEY_O)) {
				closeLight = !closeLight;
				Keyboard.next();
				if (Keyboard.isKeyDown(Keyboard.KEY_O)) {
					waitForKeyrelease = true;
				} else {
					waitForKeyrelease = false;
				}
			}
		}

		if (waitForKeyrelease2) // check done to see if key is released
		{
			if (Keyboard.isKeyDown(Keyboard.KEY_Q)) {
				stopSkyWheel = !stopSkyWheel;
				Keyboard.next();
				if (Keyboard.isKeyDown(Keyboard.KEY_Q)) {
					waitForKeyrelease2 = true;
				} else {
					waitForKeyrelease2 = false;
				}
			}
		}

		if (waitForKeyrelease3) // check done to see if key is released
		{
			if (Keyboard.isKeyDown(Keyboard.KEY_E)) {
				changeOrthSkyWheel = !changeOrthSkyWheel;
				Keyboard.next();
				if (Keyboard.isKeyDown(Keyboard.KEY_E)) {
					waitForKeyrelease3 = true;
				} else {
					waitForKeyrelease3 = false;
				}
			}
		}

		if (waitForKeyrelease4) // check done to see if key is released
		{
			if (Keyboard.isKeyDown(Keyboard.KEY_R)) {
				stopWhirligig = !stopWhirligig;
				Keyboard.next();
				if (Keyboard.isKeyDown(Keyboard.KEY_R)) {
					waitForKeyrelease4 = true;
				} else {
					waitForKeyrelease4 = false;
				}
			}
		}

		if (waitForKeyrelease5) // check done to see if key is released
		{
			if (Keyboard.isKeyDown(Keyboard.KEY_T)) {
				changeOrthWhirligig = !changeOrthWhirligig;
				Keyboard.next();
				if (Keyboard.isKeyDown(Keyboard.KEY_T)) {
					waitForKeyrelease5 = true;
				} else {
					waitForKeyrelease5 = false;
				}
			}
		}

		if (waitForKeyrelease6) // check done to see if key is released
		{
			if (Keyboard.isKeyDown(Keyboard.KEY_F)) {
				stopCorsair = !stopCorsair;
				Keyboard.next();
				if (Keyboard.isKeyDown(Keyboard.KEY_F)){
					waitForKeyrelease6 = true;
				} else {
					waitForKeyrelease6 = false;
				}
			}
		}


		/* to check if key is released */
		if (Keyboard.isKeyDown(Keyboard.KEY_O) == false) {
			waitForKeyrelease = true;
		} else {
			waitForKeyrelease = false;
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_Q) == false) {
			waitForKeyrelease2 = true;
		} else {
			waitForKeyrelease2 = false;
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_E) == false) {
			waitForKeyrelease3 = true;
		} else {
			waitForKeyrelease3 = false;
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_R) == false) {
			waitForKeyrelease4 = true;
		} else {
			waitForKeyrelease4 = false;
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_T) == false) {
			waitForKeyrelease5 = true;
		} else {
			waitForKeyrelease5 = false;
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_F) == false) {
			waitForKeyrelease6 = true;
		} else {
			waitForKeyrelease6 = false;
		}



		// keep quad on the screen
		if (x < -4400)
			x = -4400;
		if (x > 2500)
			x = 2500;
		if (z > 4100){
			z = 4100;
		}
		if (z < -4100){
			z = -4100;
		}
		if (x< 600 && x>-1200 && z < 3900 && z > 2100){
			goUp = true;
			if (waitForKeyrelease7) // check done to see if key is released
			{
				if (Keyboard.isKeyDown(Keyboard.KEY_C)) {
					playingCorsair = !playingCorsair;
					Keyboard.next();
					if (Keyboard.isKeyDown(Keyboard.KEY_C)){
						waitForKeyrelease7 = true;
					} else {
						waitForKeyrelease7 = false;
					}
				}
			}

			if (Keyboard.isKeyDown(Keyboard.KEY_C) == false) {
				waitForKeyrelease7 = true;
			} else {
				waitForKeyrelease7 = false;
			}
		} else {
			goUp = false;
		}

		if (x>1600 && x<1900 && z<1350 && z>700) {
			if (Keyboard.isKeyDown(Keyboard.KEY_B)) {
				buyIceCream = true;
			}
		}

		updateFPS(); // update FPS Counter

		LastMouseX = MouseX;
		LastMouseY = MouseY;
	}

	/**
	 * Calculate how many milliseconds have passed since last frame.
	 * 
	 * @return milliseconds passed since last frame
	 */
	public int getDelta() {
		long time = getTime();
		int delta = (int) (time - lastFrame);
		lastFrame = time;

		return delta;
	}

	/**
	 * Get the accurate system time
	 * 
	 * @return The system time in milliseconds
	 */
	public long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

	/**
	 * Calculate the FPS and set it in the title bar
	 */
	public void updateFPS() {
		if (getTime() - lastFPS > 1000) {
			Display.setTitle("FPS: " + fps);
			fps = 0;
			lastFPS += 1000;
		}
		fps++;
	}

	public void initGL() {



		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		changeOrth();
		MyArcball.startBall(0, 0, 1200, 800);
		glMatrixMode(GL_MODELVIEW);


		// light position
		FloatBuffer lightPos1 = BufferUtils.createFloatBuffer(4);
		lightPos1.put(-400f).put(400f).put(1300f).put(1).flip();

		FloatBuffer lightPos2 = BufferUtils.createFloatBuffer(4);
		lightPos2.put(1000f).put(400f).put(2000f).put(1).flip();

		FloatBuffer lightPos3 = BufferUtils.createFloatBuffer(4);
		lightPos3.put(-400f).put(400f).put(-1300f).put(1).flip();

		FloatBuffer lightPos4 = BufferUtils.createFloatBuffer(4);
		lightPos4.put(1000f).put(400f).put(-2000f).put(1).flip();

		glLight(GL_LIGHT0, GL_POSITION, lightPos1);
		glEnable(GL_LIGHT0);
		glLight(GL_LIGHT0, GL_DIFFUSE, Utils.ConvertForGL(grey));

		glLight(GL_LIGHT1, GL_POSITION, lightPos2);
		glEnable(GL_LIGHT1);
		glLight(GL_LIGHT1, GL_DIFFUSE, Utils.ConvertForGL(grey));

		glLight(GL_LIGHT2, GL_POSITION, lightPos3);
		glEnable(GL_LIGHT2);
		glLight(GL_LIGHT2, GL_DIFFUSE, Utils.ConvertForGL(grey));

		glLight(GL_LIGHT3, GL_POSITION, lightPos4);
		glEnable(GL_LIGHT3);
		glLight(GL_LIGHT3, GL_DIFFUSE, Utils.ConvertForGL(grey));

		glEnable(GL_LIGHTING);

		glEnable(GL_DEPTH_TEST); // make sure depth buffer is switched
									// on
		glEnable(GL_NORMALIZE); // normalize normal vectors for safety
		glEnable(GL_COLOR_MATERIAL);

		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		try {
			init();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // load in texture

	}

	public void changeOrth() {

		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		/*glOrtho(1200 - OrthoNumber, OrthoNumber, (800 - (OrthoNumber * 0.66f)), (OrthoNumber * 0.66f), 100000, -100000);*/

		/*glFrustum(- OrthoNumber, OrthoNumber, OrthoNumber * 0.66f, OrthoNumber * 0.66f, 300, 500000);*/

		glFrustum(- OrthoNumber * 0.67f, OrthoNumber * 0.67f, -(OrthoNumber * 0.41f), (OrthoNumber * 0.41f), 500, 50000);
//		gluLookAt(2500, 1200, -100, 300, 1000, 0, 0, 1, 0);
//		gluLookAt(2500, 1200, 0, x, y, z, 0, 1, 0);
		gluLookAt(x+800, y+800, z, x, y+400, z, 0, 1, 0);
		glMatrixMode(GL_MODELVIEW);
		FloatBuffer CurrentMatrix = BufferUtils.createFloatBuffer(16);
		glGetFloat(GL_MODELVIEW_MATRIX, CurrentMatrix);

		// if(MouseOnepressed)
		// {

		MyArcball.getMatrix(CurrentMatrix);
		// }

		glLoadMatrix(CurrentMatrix);

	}

	/*
	 * You can edit this method to add in your own objects / remember to load in
	 * textures in the INIT method as they take time to load
	 * 
	 */
	public void renderGL() {
		changeOrth();

		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
		glColor3f(0.5f, 0.5f, 1.0f);

		myDelta = getTime() - StartTime;
		float delta = ((float) myDelta) / 10000;

		// code to aid in animation
		float theta = (float) (delta * 2 * Math.PI);
		float thetaDeg = delta * 360;
		float posn_x = (float) Math.cos(theta); // same as your circle code in your notes
		float posn_y = (float) Math.sin(theta);

		/*
		 * This code draws a grid to help you view the human models movement You may
		 * change this code to move the grid around and change its starting angle as you
		 * please
		 */
//		if (DRAWGRID) {
//			glPushMatrix();
//			Grid MyGrid = new Grid();
//			glTranslatef(600, 265, 0);
//			glScalef(200f, 200f, 200f);
//			MyGrid.DrawGrid();
//			glPopMatrix();
//		}

		if (closeLight){
			glDisable(GL_LIGHT0);
			glDisable(GL_LIGHT1);
			glDisable(GL_LIGHT2);
			glDisable(GL_LIGHT3);
		} else {
			glEnable(GL_LIGHT0);
			glEnable(GL_LIGHT1);
			glEnable(GL_LIGHT2);
			glEnable(GL_LIGHT3);
		}

		glPushMatrix();
		{
			Human human = new Human();
			glTranslatef(x,y,z);
			glRotatef(-90,0,1,0);
			glScalef(100,100,100);
			switch (keyType){
				case KeyType.KEY_DOWN_A:
					glRotatef(-90,0,1,0);
					BadAnimation = true;
					break;
				case KeyType.KEY_DOWN_D:
					glRotatef(90,0,1,0);
					break;
				case KeyType.KEY_DOWN_W:
					glRotatef(-180,0,1,0);
					break;
				case KeyType.KEY_DOWN_S:
					glRotatef(0,0,1,0);
					break;
				case KeyType.KEY_DOWN_A_S:
					glRotatef(-45,0,1,0);
					break;
				case KeyType.KEY_DOWN_A_W:
					glRotatef(-135,0,1,0);
					break;
				case KeyType.KEY_DOWN_D_S:
					glRotatef(45,0,1,0);
					break;
				case KeyType.KEY_DOWN_D_W:
					glRotatef(135,0,1,0);
					break;
			}
			if (goUp){
				glTranslatef(0,1,0);
			}
			if (movingSpeed == 0.5f && !playingCorsair){
				human.drawHuman(delta, isWalk, textureHead, textureBody, buyIceCream);
			} else if (movingSpeed == 1){
				human.drawHuman(3 * delta, isWalk, textureHead, textureBody, buyIceCream);
			}
			System.out.println("x: " + x + " y: " + y + " z: " + z);
		}
		glPopMatrix();

		glPushMatrix();
		{
			Chair chair = new Chair();
			glTranslatef(1600,450,-400);
			glRotatef(30, 0.0f,1.0f,0.0f);
			glScalef(100f,100f,100f);
			chair.drawChair(textureChairPole, textureChairPole);
		}
		glPopMatrix();

		glPushMatrix();
		{
			Chair chair = new Chair();
			glTranslatef(1200,450,-1200);
			glRotatef(30, 0.0f,1.0f,0.0f);
			glScalef(100f,100f,100f);
			chair.drawChair(textureChairPole, textureChairPole);
		}
		glPopMatrix();

		glPushMatrix();
		{
			DrawingBoard drawingBoard = new DrawingBoard();
			glTranslatef(1400, 400, -800);
			glRotatef(30, 0.0f, 1.0f, 0.0f);
			glScalef(100f, 100f, 100f);
			drawingBoard.drawDrawingBoard(textureDrawingBoardPole, textureDrawingBoard);
		}
		glPopMatrix();

		glPushMatrix();
		{
			Human drawHuman = new Human();
			glTranslatef(1600,450,-400);
			glRotatef(30,0.0f,1.0f,0.0f);
			glScalef(100f, 100f, 100f);
			drawHuman.drawDrawHuman(delta, BadAnimation, textureHead, textureBody, textureShoulder);
		}
		glPopMatrix();

		// ground
		glPushMatrix();
		TexCube texCube = new TexCube();
		glTranslatef(600, 265, 0);
		glRotatef(90, 1.0f, 0.0f,0.0f);
		glScalef(20f, 20f, 1f);
		texCube.drawTexCube(500f, 500f, 0.01f, textureGround);
		glPopMatrix();


		Texture[] groundTextures = new Texture[6];
		groundTextures[0] = textureBackground1;
		groundTextures[1] = textureBackgroundTop;
		groundTextures[2] = textureBackground4;
		groundTextures[3] = textureBackground2;
		groundTextures[4] = textureBackgroundBottom;
		groundTextures[5] = textureBackground3;

		//sky box
		glPushMatrix();
		glTranslatef(600, 5000, 0);
		TexCube2 texCube2Ground = new TexCube2();
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);
		Color.white.bind();
		textureGround.bind();
		glEnable(GL_TEXTURE_2D);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
		glRotatef(90, 1.0f, 0.0f,0.0f);
		glScalef(200f, 200f, 200f);
		texCube2Ground.drawTexCube2(50f, 50f, 50f, groundTextures);
		glDisable(GL_TEXTURE_2D);
		glPopMatrix();

		glPushMatrix();
		Human MyHuman = new Human();
		glTranslatef(1200, 450, 800);
		glRotatef(270f, 0.0f, 1.0f, 0.0f);
		glScalef(110f, 110f, 110f);

		MyHuman.drawIceCreamHuman(delta, BadAnimation, textureHead, textureBody); // give a delta for the Human object ot be animated

		glPopMatrix();


		// 摩天轮
		// earth model
		glPushMatrix();
		SkyWheel skyWheel = new SkyWheel();
		glTranslatef(-2500, 400, 0);
		glScalef(120f, 120f, 120f);
		if (stopSkyWheel){
			skyWheel.drawSkyWheel(0, textureSkyWheelTop, textureSkyWheel, textureSkyWheelPole, textureSkyWheelMainPole);
		} else if (changeOrthSkyWheel){
			skyWheel.drawSkyWheel(-delta, textureSkyWheelTop, textureSkyWheel, textureSkyWheelPole, textureSkyWheelMainPole);
		} else {
			skyWheel.drawSkyWheel(delta, textureSkyWheelTop, textureSkyWheel, textureSkyWheelPole, textureSkyWheelMainPole);
		}
		glPopMatrix();

		// 海盗船
		glPushMatrix();
		Corsair corsair = new Corsair();
		glTranslatef(-300, 400, 3000);
		glScalef(100,100,100);
		if (stopCorsair){
			corsair.drawCorsair(0,textureBody,textureHead, textureBoat, textureBoatTop, textureBoatString, textureBoatPole, playingCorsair);
		} else {
			corsair.drawCorsair(delta,textureBody,textureHead, textureBoat, textureBoatTop, textureBoatString, textureBoatPole, playingCorsair);
		}

		glPopMatrix();

		// 旋转木马
		glPushMatrix();
		Whirligig whirligig = new Whirligig();
		glTranslatef(-300, 400, -3000);
		glScalef(100,100,100);
		if (stopWhirligig){
			whirligig.drawWhirligig(0, textureWhirligigLoop, textureBody, textureHead, textureWhirligigHorse, textureWhirligigPole,false);
		} else if (changeOrthWhirligig){
			whirligig.drawWhirligig(delta, textureWhirligigLoop, textureBody, textureHead, textureWhirligigHorse, textureWhirligigPole,true);
		} else {
			whirligig.drawWhirligig(delta, textureWhirligigLoop, textureBody, textureHead, textureWhirligigHorse, textureWhirligigPole,false);
		}
		glPopMatrix();

		// earth model
		glPushMatrix();
		Earth earth = new Earth();
		glTranslatef(300, 400, 0);
		glScalef(150f, 150f, 150f);
		earth.drawEarth(delta ,texture, textureEarthFoundation, textureLoop);
		glPopMatrix();

		glPushMatrix();
		IceCreamVan iceCreamVan = new IceCreamVan();
		glTranslatef(1500, 450, 1000);
		glRotatef(270f, 0.0f, 1.0f,0.0f);
		glScalef(100f, 100f, 100f);
		iceCreamVan.drawIceCreamVan(textureIceCreamVanPole, textureIceCreamVan);
		glPopMatrix();

		// light texture
		Texture[] lightTextures = new Texture[6];
		for (int face = 0; face < 6; face++){
			lightTextures[face] = textureLight;
		}


		// light 1
		glPushMatrix();
		{
			LightPole lightPole = new LightPole();
			glTranslatef(-400, 400, 1300);
			glScalef(50, 50, 50);
			lightPole.DrawLightPole(lightTextures,textureLightPole, textureLightPoleFoundation);

		}
		glPopMatrix();
		// light 2
		glPushMatrix();
		{
			LightPole lightPole = new LightPole();
			glTranslatef(1000, 400, 2000);
			glScalef(50, 50, 50);
			lightPole.DrawLightPole(lightTextures,textureLightPole,textureLightPoleFoundation);

		}
		glPopMatrix();

		// light 3
		glPushMatrix();
		{
			LightPole lightPole = new LightPole();
			glTranslatef(-400, 400, -1300);
			glScalef(50, 50, 50);
			lightPole.DrawLightPole(lightTextures,textureLightPole,textureLightPoleFoundation);

		}
		glPopMatrix();

		// light 4
		glPushMatrix();
		{
			LightPole lightPole = new LightPole();
			glTranslatef(1000, 400, -2000);
			glScalef(50, 50, 50);
			lightPole.DrawLightPole(lightTextures, textureLightPole,textureLightPoleFoundation);
		}
		glPopMatrix();

		glPushMatrix();
		{
			Customer customer = new Customer();
			Human human = new Human();
			glTranslatef(1900,450, 1000);
			glRotatef(90f, 0.0f, 1.0f,0.0f);

			if (delta <= 0.2){
				glTranslatef(0, 0, 0 - delta * 1000);
				glScalef(100f, 100f, 100f);
				customer.drawCustomer(delta, BadAnimation, textureHead, textureBody);
			} else if (delta > 0.2 && delta < 0.3){
				glTranslatef(0, 0, -200);
				glScalef(100f, 100f, 100f);
				customer.drawCustomer2(delta, !BadAnimation, textureHead, textureBody);
			} else if (delta > 0.3 && delta < 0.45&& angle<=110){
				glTranslatef(0, 0, -200);
				glRotatef(-angle, 0.0f, 1.0f, 0.0f);
				glScalef(100f, 100f, 100f);
				customer.drawCustomer2(delta, !BadAnimation, textureHead, textureBody);
				angle += 0.5 * Math.PI;
			} else if (delta > 0.45 && delta < 0.95){
				glTranslatef((float) ((delta-0.45) * 1000), 0, (float) (-200 + (delta-0.45) * 500));
				glRotatef(-120,0.0f,1.0f,0.0f);
				glScalef(100f, 100f, 100f);
				customer.drawCustomer2(delta, BadAnimation, textureHead, textureBody);
			} else if ( delta > 0.95 && (angle - 110) < 30){
				glTranslatef(500, 0, 50);
				glRotatef(-120,0.0f,1.0f,0.0f);
				glRotatef(angle - 110, 0.0f, 1.0f, 0.0f);
				glScalef(100f, 100f, 100f);
				customer.drawCustomer2(delta, !BadAnimation, textureHead, textureBody);
				angle += 0.5 * Math.PI;
			} else if (delta > 0.99 && delta < 1.7) {
				glTranslatef(500, 0, 50);
				glTranslatef((float) ((delta-0.9873) * 1500), 0.0f, 50.0f);
				glRotatef(-90, 0.0f, 1.0f, 0.0f);
				glScalef(100f, 100f, 100f);
				customer.drawCustomer2(delta, BadAnimation, textureHead, textureBody);
			} else if (delta > 1.7 && (angle-140) < 30) {
				glTranslatef(1575.5f, 0.0f, 50.0f);
				glRotatef(-90, 0.0f, 1.0f, 0.0f);
				glRotatef(angle-140, 0.0f, 1.0f, 0.0f);
				glScalef(100f, 100f, 100f);
				customer.drawCustomer2(delta, BadAnimation, textureHead, textureBody);
				angle += 0.5 * Math.PI;
			} else if (delta > 1.74 && delta < 2.3){
				glTranslatef(1575.5f, 0.0f, 50.0f);
				glTranslatef((float) ((delta-1.74) * 1500), 0.0f, (float) -((delta-1.74) * 500));
				glRotatef(-60, 0.0f, 1.0f, 0.0f);
				glScalef(100f, 100f, 100f);
				customer.drawCustomer2(delta, BadAnimation, textureHead, textureBody);
			} else if (delta > 2.3 && angle - 170 < 90){
				glTranslatef(2415.5f, 0.0f, -230);
				glRotatef(-60, 0.0f, 1.0f, 0.0f);
				glRotatef((angle - 170), 0.0f, 1.0f, 0.0f);
				glScalef(100f, 100f, 100f);
				customer.drawCustomer2(delta, BadAnimation, textureHead, textureBody);
				angle += 0.5 * Math.PI;
			} else if (delta > 2.4 && delta < 3.1) {
				glTranslatef(2415.5f, 0.0f, -230);
				glTranslatef((float) -((delta-2.4) * 500), 0.0f, (float) -((delta-2.4) * 500));
				glRotatef(30, 0.0f, 1.0f, 0.0f);
				glScalef(100f, 100f, 100f);
				customer.drawCustomer2(delta, BadAnimation, textureHead, textureBody);
			} else if (delta > 3.1 && angle - 260 < 90) {
				glTranslatef(2065.5f, 0.0f, -580);
				glRotatef(30, 0.0f, 1.0f, 0.0f);
				glRotatef((angle - 260), 0.0f, 1.0f, 0.0f);
				glScalef(100f, 100f, 100f);
				customer.drawCustomer2(delta, !BadAnimation, textureHead, textureBody);
				angle += 0.5 * Math.PI;
			} else if (delta > 3.2) {
				glTranslatef(2200f, 0.0f, -700);
				glRotatef(120, 0.0f, 1.0f, 0.0f);
				glScalef(100f, 100f, 100f);
				human.drawSitDownHuman(delta, !BadAnimation, textureHead, textureBody);
			}
		}
		glPopMatrix();


		/**
		 * This code puts the earth code in which is larger than the human so it appears
		 * to change the scene
		 */
		if (Earth) {
			// Globe in the centre of the scene
			glPushMatrix();
			TexSphere MyGlobe = new TexSphere();
			glTranslatef(500, 500, 500);
			glScalef(140f, 140f, 140f);

			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);
			Color.white.bind();
			texture.bind();
			glEnable(GL_TEXTURE_2D);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
			glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
			MyGlobe.DrawTexSphere(8f, 100, 100, texture);
			glDisable(GL_TEXTURE_2D);
			glPopMatrix();
		}

	}

	public static void main(String[] argv) {
		MainWindow hello = new MainWindow();
		hello.start();
	}

	Texture texture, textureCube, textureHead, textureBody,
			textureGround, textureLight, textureLightPole,
			textureChairPole, textureDrawingBoard, textureDrawingBoardPole,
			textureLightPoleFoundation, textureEarthFoundation,
			textureIceCreamVanPole, textureIceCreamVan, textureBackgroundTop,
			textureBackgroundBottom, textureBackground1, textureBackground2,
			textureBackground3, textureBackground4, textureLoop, textureShoulder,
			textureBoat, textureBoatPole, textureBoatString, textureBoatTop,
			textureWhirligigHorse, textureWhirligigPole, textureWhirligigLoop,
	        textureSkyWheel, textureSkyWheelMainPole, textureSkyWheelTop, textureSkyWheelPole;

	/*
	 * Any additional textures for your assignment should be written in here. Make a
	 * new texture variable for each one so they can be loaded in at the beginning
	 */
	public void init() throws IOException {


		texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/earthspace.png"));
		textureCube = TextureLoader.getTexture("JPG", ResourceLoader.getResourceAsStream("res/cube.jpg"));
		textureHead = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/face.png"));
		textureBody = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/clothes.png"));
		textureShoulder = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/clothes.png"));
		textureLoop = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/loop.png"));
		textureEarthFoundation = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/earthFoundation.png"));
		textureGround = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/ground.png"));
		textureBackgroundTop = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/backgroundTop.png"));
		textureBackgroundBottom = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/backgroundBottom.png"));
		textureBackground1 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/background1.png"));
		textureBackground2 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/background2.png"));
		textureBackground3 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/background3.png"));
		textureBackground4 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/background4.png"));
		textureLight = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/light.png"));
		textureLightPole = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/pillar.png"));
		textureLightPoleFoundation = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/foundation.png"));
		textureChairPole = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/pillar.png"));
		textureDrawingBoard = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/valley.png"));
		textureDrawingBoardPole = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/drawingBoardPole.png"));
		textureIceCreamVanPole = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/iceCreamVanPole.png"));
		textureIceCreamVan = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/iceCreamVan.png"));
		textureBoat = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/boat.png"));
		textureBoatTop = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/topOfBoat.png"));
		textureBoatPole = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/poleOfBoat.png"));
		textureBoatString = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/stringOfBoat.png"));
		textureWhirligigHorse = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/whirligigHorse.png"));
		textureWhirligigLoop = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/whirligigLoop.png"));
		textureWhirligigPole = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/whirligigPole.png"));

		textureSkyWheel = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/skyWheel.png"));
		textureSkyWheelMainPole = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/skyWheelMainPole.png"));
		textureSkyWheelPole = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/skyWheelPole.png"));
		textureSkyWheelTop = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/skyWheelTop.png"));

		System.out.println("Texture loaded okay ");
	}
}
