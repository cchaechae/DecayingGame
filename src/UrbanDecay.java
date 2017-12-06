import java.io.IOException;

import processing.core.PApplet;
import processing.core.PShape;
import processing.core.PVector;


public class UrbanDecay extends PApplet{

	//Particle[] field;
	
	float radx;   // Radius
	float rady;
	float angle1; // angle
	float x;      // result
	float y;
	float minus = 0.05f;
	final static int NUM_PARTICLE = 3000;
	float bottom;
	PShape city;
	Umbrella umbrella;
	
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
		translate(1f/zoom , -PROJECTOR_RATIO/zoom );		
		bottom = -PROJECTOR_RATIO/zoom;
	}

	public void settings() {
		createWindow(false, false, .5f);
	}

	public void setup(){
		
		/*
		 * use this code to run your PApplet from data recorded by UPDRecorder 
		 */
		/*
		try {
			kinectReader = new KinectBodyDataProvider("test.kinect", 10);
		} catch (IOException e) {
			System.out.println("Unable to creat e kinect producer");
		}
		 */
		
		try {
			kinectReader = new KinectBodyDataProvider("exitTest.kinect", 10);
		} catch (IOException e) {
			System.out.println("Unable to creat e kinect producer");
		}
		//kinectReader = new KinectBodyDataProvider(8008);
		kinectReader.start();
		
		
//		field = new Particle[NUM_PARTICLE];
//		for (int i=0; i < NUM_PARTICLE; i++) {
//			radx=random(0.1f);
//			rady=random(0.1f);
//			angle1= random(359);
//			
//			x=(radx*cos(radians(angle1))); //+width/2;
//			y=(radx*sin(radians(angle1))); //+height/2;
//			
//			field[i] = new Particle(this,x,y,color(random(0,255),random(0,255),random(0,255)));
//		 }
	}
	
	public void draw(){
		setScale(.5f);
		
		noStroke();
		background(0);
		
		fill(150, 150, 150);
		drawBuilding(-1.8f, 0.6f, 0.2f);
		drawBuilding(-1.4f, 0.73f, 0.35f);
		drawBuilding(-0.9f, 1.1f, 0.15f);
		drawBuilding(-.5f, 1.6f, 0.25f);
		drawBuilding(.2f, 0.73f, 0.45f);
		drawBuilding(.8f, 0.86f, 0.15f);
		drawBuilding(1.4f, 0.73f, 0.2f);
		
		fill(255, 255, 255);
		//ground
		rect(-2f, bottom, 4f, 0.1f);
		//white buildings
		drawBuilding(-1.9f, 0.4f, 0.2f);
		drawBuilding(-1.6f, 0.5f, 0.18f);
		drawBuilding(-1.36f, 0.35f, 0.4f);
		drawBuilding(-0.8f, 0.7f, 0.24f);
		drawBuilding(-0.76f, 0.83f, 0.16f);
		drawBuilding(-0.52f, 0.5f, 0.2f);
		drawBuilding(-0.69f, 1f, 0.02f);
		drawBuilding(-0.2f, 0.67f, 0.13f);
		drawBuilding(0, 1.1f, 0.35f);
		drawBuilding(0.5f, 0.3f, 0.8f);
		drawBuilding(0.9f, 0.6f, 0.45f);
		drawBuilding(1.5f, 0.25f, 0.2f);

		System.out.println();
		// leave trails instead of clearing background \ 
		//noStroke();
		//fill(0,0,0, 10);
		//rect(-1,-1, 2, 2); //draw transparent rect of the window

//		KinectBodyData bodyData = kinectReader.getMostRecentData();
		KinectBodyData bodyData = kinectReader.getData();
		Body person = bodyData.getPerson(0);
		if(person != null){
			PVector head = person.getJoint(Body.HEAD);
			PVector spine = person.getJoint(Body.SPINE_SHOULDER);
			PVector spineBase = person.getJoint(Body.SPINE_BASE);
			PVector shoulderLeft = person.getJoint(Body.SHOULDER_LEFT);
			PVector shoulderRight = person.getJoint(Body.SHOULDER_RIGHT);
			PVector footLeft = person.getJoint(Body.FOOT_LEFT);
			PVector footRight = person.getJoint(Body.FOOT_RIGHT);
			PVector handLeft = person.getJoint(Body.HAND_LEFT);
			PVector handRight = person.getJoint(Body.HAND_RIGHT);

			fill(255,255,255);
			noStroke();
			
			umbrella = new Umbrella (this, 0.5f, head);
			if (head != null){
				umbrella.drawUmbrella();
			}
			
//			drawIfValid(head);
//			drawIfValid(spine);
//			drawIfValid(spineBase);
//			drawIfValid(shoulderLeft);
//			drawIfValid(shoulderRight);
//			drawIfValid(footLeft);
//			drawIfValid(footRight);
//			drawIfValid(handLeft);
//			drawIfValid(handRight);

//			if(head != null){
//				for (int i=0; i < NUM_PARTICLE; i++) {
//					if(!field[i].clicked){
//						field[i].update(field[i].location.x+head.x, field[i].location.y+head.y);
//						field[i].draw();
//						field[i].update(field[i].location.x-head.x, field[i].location.y-head.y);
//						System.out.println(field[i].location.x);
//					}
//					else{
//						field[i].falling();
//					}
//										
//					System.out.println(field[i].location.x);
//				}
//			}
//			
//			if(mousePressed){
//				for(int i=0; i < field.length; i++){
//					if(field[i].location.y < head.y+0.05f){
//						System.out.println(field[i].location.y);
//						System.out.println(head.y+0.05f);
//						
//						field[i].mouseClicked();
//					}
//				}
//				//minus = minus - 0.001f;
//			}

			
//			if( 
//					(footRight != null) &&
//					(footLeft != null) &&
//					(handLeft != null) &&
//					(handRight != null) 
//					) {
//				stroke(255,0,0, 100);
//				noFill();
//				strokeWeight(.05f); // because of scale weight needs to be much thinner
//				curve(
//						footLeft.x, footLeft.y, 
//						handLeft.x, handLeft.y, 
//						handRight.x, handRight.y,
//						footRight.x, footRight.y
//						);
//			}
		}
	}

	
	public void drawBuilding(float x, float height, float width){
		beginShape();
		vertex(x, bottom);
		vertex(x, bottom + height);
		vertex(x + width, bottom + height);
		vertex(x + width, bottom);
		endShape(CLOSE);
	}
	
//	public void initCity(){
//		fill(255, 255, 255);
//
//		beginShape();
//		vertex(0,0);
//		vertex(0,1f);
//		vertex(1f,1f);
//		vertex(1f,0);
//		endShape(CLOSE);
//			
//	}
	
	
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
