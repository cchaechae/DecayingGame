import java.io.IOException;

import processing.core.PApplet;
import processing.core.PVector;


public class UrbanDecay extends PApplet{
	
	float radx;   // Radius
	float rady;
	float angle1; // angle
	float x;      // result
	float y;
	
	final static int NUM_WHITE_BUILDING = 14;
	final static int NUM_GREY_BUILDING = 13;
	final static int NUM_DARK_BUILDING = 6;
	
	final static float ZOOM = 0.5f;		
	
	float bottom = -PROJECTOR_RATIO/ZOOM;

	Building[] whiteBuilding = new Building[NUM_WHITE_BUILDING];
	Building[] greyBuilding = new Building[NUM_GREY_BUILDING];
	Building[] darkBuilding = new Building[NUM_DARK_BUILDING];
	
	KinectBodyDataProvider kinectReader;
	public static float PROJECTOR_RATIO = 1080f/1920.0f;
	
	public void createWindow(boolean useP2D, boolean isFullscreen, float windowsScale) {
		if (useP2D) {
			if(isFullscreen) {
				fullScreen(P2D);  			
			} else {
				size((int)(1920 * windowsScale), (int)(1080 * windowsScale), P2D);
			}
		} else {
			if(isFullscreen) {
				fullScreen();  			
			} else {
				size((int)(1920 * windowsScale), (int)(1080 * windowsScale));
			}
		}		
	}
	
	// use lower numbers to zoom out (show more of the world)
	// zoom of 1 means that the window is 2 meters wide and appox 1 meter tall.
	public void setScale(float zoom) {
		scale(zoom* width/2.0f, zoom * -width/2.0f);
		translate(1f/zoom, -PROJECTOR_RATIO/zoom );	
	}

	public void settings() {
		createWindow(false, true, .5f);
	}

	public void setup(){
		
		try {
			kinectReader = new KinectBodyDataProvider("test.kinect", 10);
		} catch (IOException e) {
			System.out.println("Unable to creat e kinect producer");
		}
		//kinectReader = new KinectBodyDataProvider(8008);
		kinectReader.start();
		
		// white buildings
		whiteBuilding[0] = new Building(this, -1.55f, bottom, 0.13f, 0.25f);
		whiteBuilding[1] = new Building(this, -1.3f, bottom, 0.21f, 0.52f);
		whiteBuilding[2] = new Building(this, -0.95f, bottom, 0.05f, 0.43f);
		whiteBuilding[3] = new Building(this, -0.9f, bottom, 0.3f, 0.48f);
		whiteBuilding[4] = new Building(this, -0.6f, bottom, 0.05f, 0.43f);
		whiteBuilding[5] = new Building(this, -0.4f, bottom, 0.24f, 0.84f);
		whiteBuilding[6] = new Building(this, -0.161f, bottom, 0.161f, 0.3f);
		whiteBuilding[7] = new Building(this,  0f, bottom, 0.08f, 0.97f);
		whiteBuilding[8] = new Building(this,  0.079f, bottom, 0.102f, 1.1f);
		whiteBuilding[9] = new Building(this,  0.18f, bottom, 0.08f, 0.97f);
		whiteBuilding[10] = new Building(this, 0.35f, bottom, 0.35f, 0.65f);
		whiteBuilding[11] = new Building(this, 0.8f, bottom, 0.21f, 0.45f);
		whiteBuilding[12] = new Building(this, 1.25f, bottom, 0.14f, 0.3f);
		whiteBuilding[13] = new Building(this, 1.43f, bottom, 0.13f, 0.22f);
		
		// grey buildings
		greyBuilding[0] = new Building(this, -1.8f, bottom, 0.32f, 0.5f);
		greyBuilding[1] = new Building(this, -1.2f, bottom, 0.25f, 0.85f);
		greyBuilding[2] = new Building(this, -0.7f, bottom, 0.25f, 0.63f);
		greyBuilding[3] = new Building(this, -0.3f, bottom, 0.03f, 1.15f);
		greyBuilding[4] = new Building(this, -0.271f, bottom, 0.08f, 1.25f);
		greyBuilding[5] = new Building(this, -0.192f, bottom, 0.024f, 1.5f);
		greyBuilding[6] = new Building(this, -0.17f, bottom, 0.081f, 1.25f);
		greyBuilding[7] = new Building(this, -0.09f, bottom, 0.03f, 1.15f);
		greyBuilding[8] = new Building(this, 0.1f, bottom, 0.35f, 1.2f);
		greyBuilding[9] = new Building(this, 0.65f, bottom, 0.3f, 0.9f);
		greyBuilding[10] = new Building(this, 1.3f, bottom, 0.3f, 0.52f);
		greyBuilding[11] = new Building(this, 1.5f, bottom, 0.2f, 0.34f);
		greyBuilding[12] = new Building(this, 1.8f, bottom, 0.15f, 0.25f);

		// dark grey buildings
		darkBuilding[0] = new Building(this, -1.5f, bottom, 0.22f, 0.82f);
		darkBuilding[1] = new Building(this, -1f, bottom, 0.22f, 1.2f);
		darkBuilding[2] = new Building(this, -0.5f, bottom, 0.25f, 1.3f);
		darkBuilding[3] = new Building(this, 0f, bottom, 0.4f, 1.4f);
		darkBuilding[4] = new Building(this,  0.6f, bottom, 0.18f, 1.1f);
		darkBuilding[5] = new Building(this,  1.1f, bottom, 0.53f, .8f);
	}
	
	public void draw(){
		
		setScale(ZOOM);
		
		background(0);
		noStroke();
		
		fill(255, 255, 255);
		//ground
		rect(-2f, bottom, 4f, 0.1f);
		
		fill(50, 50, 50);
		
		for(int i = 0; i < NUM_DARK_BUILDING; i++){
			noStroke();
			darkBuilding[i].draw();
			if(mousePressed)
			{
				darkBuilding[i].initParticle();
				darkBuilding[i].decay();
				darkBuilding[i].mouseClicked();
			}
			darkBuilding[i].drawParticle();
		}
		
		fill(120, 120, 120);	
		for(int i = 0; i < NUM_GREY_BUILDING; i++){
			noStroke();
			greyBuilding[i].draw();
			if(mousePressed)
			{
				greyBuilding[i].initParticle();
				greyBuilding[i].decay();
				greyBuilding[i].mouseClicked();
			}
			greyBuilding[i].drawParticle();
		}
				
		fill(255, 255, 255);	
		//white buildings
		for(int i = 0; i < NUM_WHITE_BUILDING; i++){
			noStroke();
			whiteBuilding[i].draw();
			if(mousePressed)
			{
				whiteBuilding[i].initParticle();
				whiteBuilding[i].decay();
				whiteBuilding[i].mouseClicked();
			}
			whiteBuilding[i].drawParticle();
		}			


		//		KinectBodyData bodyData = kinectReader.getMostRecentData();
		KinectBodyData bodyData = kinectReader.getData();
		Body person = bodyData.getPerson(0);
		if(person != null){
//			PVector head = person.getJoint(Body.HEAD);
//			PVector spine = person.getJoint(Body.SPINE_SHOULDER);
//			PVector spineBase = person.getJoint(Body.SPINE_BASE);
//			PVector shoulderLeft = person.getJoint(Body.SHOULDER_LEFT);
//			PVector shoulderRight = person.getJoint(Body.SHOULDER_RIGHT);
//			PVector footLeft = person.getJoint(Body.FOOT_LEFT);
//			PVector footRight = person.getJoint(Body.FOOT_RIGHT);
//			PVector handLeft = person.getJoint(Body.HAND_LEFT);
//			PVector handRight = person.getJoint(Body.HAND_RIGHT);

//			fill(255,255,255);
//			noStroke();
//			drawIfValid(head);
		}
	}	
	
	/**
	 * Draws an ellipse in the x,y position of the vector (it ignores z).
	 * Will do nothing is vec is null.  This is handy because get joint 
	 * will return null if the joint isn't tracked. 
	 * @param vec
	 */
	public void drawIfValid(PVector vec) {
		if(vec != null) {
			ellipse(vec.x, vec.y, .1f,.1f);
		}
	}

	public static void main(String[] args) {
		PApplet.main(UrbanDecay.class.getName());
	}

}
