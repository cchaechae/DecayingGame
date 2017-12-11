import java.util.Random;

import processing.core.PApplet;
import processing.core.PVector;

public class RainDemo extends PApplet{

	Rain[] rain;
	Rain[] rainOne;
	Rain[] rainTwo;
	Rain[] rainThree;
	public static float PROJECTOR_RATIO = 1080f/1920.0f;
	
	int windowWidth;
	
	
	public void createWindow(boolean useP2D, boolean isFullscreen, float windowsScale) {
		windowWidth = (int)(1920 * windowsScale);
		if (useP2D) {
			if(isFullscreen) {
				fullScreen(P2D);
				
			} else {
				size(windowWidth, (int)(1080 * windowsScale), P2D);
			}
		} else {
			if(isFullscreen) {
			
				fullScreen();  			
			} else {
				
				size(windowWidth, (int)(1080 * windowsScale));
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
		//size(600,800);
		createWindow(false, true, .5f);
	}
		
	public void setup(){
		background(0);
		
		
		rain = new Rain[25];
		rainOne = new Rain[100];
		rainTwo = new Rain[10];
		rainThree = new Rain[200];
		
		
		
		int secWidth = windowWidth/4;
		System.out.println(windowWidth);
		
		for (int i = 0; i < 25; i++) {

			rain[i] = new Rain(this,  random(-2f, -1f),random(0, 1.5f), random(0.2f, 0.4f));

		}

		for (int i = 0; i < 100; i++) {

			rainOne[i] = new Rain(this, random(-1f, 0f),  random(0, 1.5f),random(0.2f, 0.4f));

		}

		for (int i = 0; i < 10; i++) {

			rainTwo[i] = new Rain(this,  random(0f, 1f),  random(0, 1.5f),  random(0.2f, 0.4f));

		}

		for (int i = 0; i < 200; i++) {

			rainThree[i] = new Rain(this,  random(1f, 2f), random(0, 1.5f),
					random(0.2f, 0.4f));
		}

	}
	public static int getRandom(int[] array) {
	    int rnd = new Random().nextInt(array.length);
	    return array[rnd];
	}

			
	public void draw(){
		
		setScale(0.5f);
		
		background(0);
		
		
		fill(204, 102, 0);
		
		arc(0.7f, 0.5f, 0.5f, 0.5f,0,HALF_PI+HALF_PI);

		
//		for (int i = 0; i < 25 ; i++) {
//			rain[i].isUmbrella(true);
//			rain[i].setUmbrellaDimensions(0.7f,0.5f,0.5f);
//			rain[i].draw();
//			
//		}
//		for (int i = 0; i < 100 ; i++) {
//			rainOne[i].isUmbrella(true);
//			rainOne[i].setUmbrellaDimensions(0.7f,0.5f,0.5f);
//			rainOne[i].draw();
//			
//		}
//		for (int i = 0; i < 10 ; i++) {
//			rainTwo[i].isUmbrella(true);
//			rainTwo[i].setUmbrellaDimensions(0.7f,0.5f,0.5f);
//			rainTwo[i].draw();
//			
//		}
//		for (int i = 0; i < 200 ; i++) {
//			rainThree[i].isUmbrella(true);
//			rainThree[i].setUmbrellaDimensions(0.7f,0.5f,0.5f);
//			rainThree[i].draw();
//		}
//		
		
	}
		
	public static void main(String[] args) {
		PApplet.main(RainDemo.class.getName());
	}
}
