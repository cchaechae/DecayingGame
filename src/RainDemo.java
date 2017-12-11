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
		
		//int rand = getRandom(arr);
		for (int i = 0; i < 25; i++) {
			
		
			rain[i] = new Rain(this, (int)random(0 ,240), (int)random(0, this.height), (int)random(2, 4));
		
		}
		
		for (int i = 0; i < 100; i++) {
			
			rainOne[i] = new Rain(this, (int)random( 240, 480), (int)random(0, this.height), (int)random(2, 4));
		}
	
		for (int i = 0; i < 10; i++) {
			
			rainTwo[i] = new Rain(this, (int)random(480, 720), (int)random(0, this.height), (int)random(2, 4));
		}
		
		for (int i = 0; i < 200; i++) {
			
			rainThree[i] = new Rain(this, (int)random(720, this.width), (int)random(0, this.height), (int)random(2, 4));
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
		
		arc(0f, 0f, 0.5f, 0.5f, PI,2*PI);
		
	
		
	
		
		
		System.out.println(PI);
		
		for (int i = 0; i < 25 ; i++) {
			
			rain[i].draw();
			rain[i].isUmbrella(true);
			rain[i].setUmbrellaDimensions(300,400,250);
		}
		for (int i = 0; i < 100 ; i++) {
			rainOne[i].draw();
			rainOne[i].isUmbrella(true);
			rainOne[i].setUmbrellaDimensions(300,400,250);
		}
		for (int i = 0; i < 10 ; i++) {
			rainTwo[i].draw();
			rainTwo[i].isUmbrella(true);
			rainTwo[i].setUmbrellaDimensions(300,400,250);
		}
		for (int i = 0; i < 200 ; i++) {
			rainThree[i].draw();
			rainThree[i].isUmbrella(true);
			rainThree[i].setUmbrellaDimensions(300,400,250);
		}
		
		
	}
		
	public static void main(String[] args) {
		PApplet.main(RainDemo.class.getName());
	}
}
