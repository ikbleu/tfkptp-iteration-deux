
//Short demo of OpenGL
//Author:  Ryan Shackelford

//http://mefhisto.deviantart.com/art/Ecliz-Cursors-50266913
//http://images.spaceref.com/news/2004/041007-F-0000S-003-1.jpg

package src.TestYourJogl;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.IntBuffer;

import javax.imageio.ImageIO;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLException;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;
import javax.swing.event.MouseInputListener;


import com.sun.opengl.impl.mipmap.Image;
import com.sun.opengl.util.Animator;
import com.sun.opengl.util.BufferUtil;
import com.sun.opengl.util.ImageUtil;
import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureIO;

public class ViewPractice extends JFrame{

	private static final long serialVersionUID = 100;
	
	private double scale;
	private double scaleFactor;
	private double dX;
	private double dY;
	private double friction;
	boolean mousePressed;
	
	private Point pickPoint;
	private Point dragPoint;
	int selectedObj;
	
	private double offX;
	private double offY;

	MouseEvent prev;
	
	private int windowX;
	private int windowY;
	
	private static ViewPractice frame;
	Cursor fancyCursor;
	
	Texture moon_tex;
	Texture hud_tex;
	
	
	public static void main(String[] args) {
		frame = new ViewPractice();
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setVisible(true);

	}
	
	public ViewPractice(){
		
		scale = 1.0f;
		scaleFactor = 0.8;
		dX = 0;
		dY = 0;
		friction = 0.01;
		mousePressed = false;
		
		pickPoint = null;
		selectedObj = -1;
		
		offX = 0;
		offY = 0;
		
		windowX = 940;
		windowY = 600;

		setSize(windowX, windowY);
		setTitle("View Practice!");

		Toolkit toolkit = Toolkit.getDefaultToolkit();  
		java.awt.Image fcImage = toolkit.getImage("FancyPointer.png");
		Point hotSpot = new Point(0,0);  
		fancyCursor = toolkit.createCustomCursor(fcImage, hotSpot, "FancyCursor");  
		
		GraphicListener listener=new GraphicListener();
		GLCanvas canvas = new GLCanvas(new GLCapabilities());
	    canvas.addGLEventListener(listener);
	    canvas.addMouseWheelListener(listener);
	    canvas.addMouseListener(listener);
	    canvas.addMouseMotionListener(listener);
	    getContentPane().add(canvas);
	    setCursor(fancyCursor);
	    
	    Animator animator = new Animator(canvas);
	    animator.start();
	}
	
	public class GraphicListener implements GLEventListener, 
											MouseWheelListener,
											MouseInputListener{
		
		public void display(GLAutoDrawable drawable) {
			
			GL gl = drawable.getGL();
			gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
			
			if(pickPoint!=null) {
				pick(gl);
				pickPoint = null;
			}
			
			render(gl, GL.GL_RENDER);
		}
		

		private void render(GL gl, int mode) {

			gl.glPushMatrix();
			
				gl.glScaled(scale, scale, 1.0f);
				gl.glTranslated(offX,offY,0.0);
				updateOffset();
				renderMap(gl, 20, 20);
				
			gl.glPopMatrix();
			
			renderHud(gl);
		}
		
		private void renderHud(GL gl) {
			
			gl.glLoadName(9000);  
			
			gl.glPushMatrix();
			gl.glTranslated(0,0,-1);
			
			hud_tex.bind();
			
			if(selectedObj == 9000)
				gl.glColor3d(1.0,0,0);
			
			gl.glBegin(GL.GL_POLYGON);
			
				gl.glTexCoord2d(0.0, 0.0);
				gl.glVertex2d(-5.1,-3.0);
				
				gl.glTexCoord2d(0.0, 1.0);
				gl.glVertex2d(-5.1,-1.2);
				
				gl.glTexCoord2d(0.25, 1.0);
				gl.glVertex2d(-2.5,-1.2);
				
				gl.glTexCoord2d(0.25, 0.0);
				gl.glVertex2d(-2.5,-3.0);
				
			gl.glEnd();
			
			gl.glBegin(GL.GL_POLYGON);
			
				gl.glTexCoord2d(0.25, 0.6);
				gl.glVertex2d(-2.5,-1.9);
			
				gl.glTexCoord2d(0.78, 0.6);
				gl.glVertex2d(2.5,-1.9);
	
				gl.glTexCoord2d(0.78, 0.0);
				gl.glVertex2d(2.5,-3.0);
			
				gl.glTexCoord2d(0.25, 0.0);
				gl.glVertex2d(-2.5,-3.0);
			
			gl.glEnd();
			
			gl.glBegin(GL.GL_POLYGON);
			
				gl.glTexCoord2d(0.78, 0.8);
				gl.glVertex2d(2.5,-1.2);
		
				gl.glTexCoord2d(0.98, 0.8);
				gl.glVertex2d(5.0,-1.2);

				gl.glTexCoord2d(0.98, 0.0);
				gl.glVertex2d(5.0,-3.0);
		
				gl.glTexCoord2d(0.78, 0.0);
				gl.glVertex2d(2.5,-3.0);
		
			gl.glEnd();
			
			gl.glColor3d(1,1,1);
			gl.glPopMatrix();
			
			
		}


		private void updateOffset() {
			
			offX += dX;
			offY -= dY;		//y-axis inverted in java
			
			if(!mousePressed){
				dX = dX*(1-friction);
				dY = dY*(1-friction);
			}
			else {
				dX = 0;
				dY = 0;	
			}
			
		}


		private void renderMap(GL gl, int height, int length) {
			int index = 1;				//used for opengl picking
			
			for(int i=0; i<height/2; i++) {
				for(int j=0; j<length; j++) {
					gl.glLoadName(index);  
					if(selectedObj==index)
						gl.glColor3d(1.0,0,0);
					renderTexturedHex(gl, -9+ 1.85*j, i*3.3, 1);
					index++;
					gl.glColor3d(1.0,1.0,1.0);
					gl.glLoadName(index);
					if(selectedObj==index)
						gl.glColor3d(1.0,0,0);
					renderTexturedHex(gl, -8.08+ 1.85*j, 1.65+i*3.3, 1);
					index++;
					gl.glColor3d(1.0,1.0,1.0);
				}
			}
		}
		
		private void renderTexturedHex(GL gl, double x, double y, double sideLength) {
			
			double r = sideLength*Math.cos(0.523);  //0.523 radians = 30 degrees
			double h = sideLength*Math.sin(0.523);
			
			moon_tex.bind();
			
			gl.glBegin(GL.GL_POLYGON);
			
				gl.glTexCoord2d(0.0, 0.3);
				gl.glVertex2d(x,y);

				gl.glTexCoord2d(0.0, 0.7);
				gl.glVertex2d(x, y+sideLength);
				
				gl.glTexCoord2d(0.5, 1.0);
				gl.glVertex2d(x+r, y+sideLength+h);
		
				gl.glTexCoord2d(1.0, 0.7);
				gl.glVertex2d(x+2*r, y+sideLength);
		
				gl.glTexCoord2d(1.0, 0.3);
				gl.glVertex2d(x+2*r, y);
		
				gl.glTexCoord2d(0.5, 0.0);
				gl.glVertex2d(x+r, y-h);

			gl.glEnd();
		}
		
		private void pick(GL gl){
			
			GLU glu = new GLU();
			
			int buff[] = new int[1024];
			
			IntBuffer selectBuffer = BufferUtil.newIntBuffer(1024);
			int hits;
			int[] viewport = new int[4];
			float[] projection = new float[16];

			gl.glGetIntegerv(GL.GL_VIEWPORT, viewport, 0);
			gl.glGetFloatv(GL.GL_PROJECTION_MATRIX, projection, 0);

			gl.glSelectBuffer(1024, selectBuffer);
			gl.glRenderMode(GL.GL_SELECT);

			gl.glInitNames();
			gl.glPushName(-1);

			gl.glMatrixMode(GL.GL_PROJECTION);
			gl.glPushMatrix();
			
			    gl.glLoadIdentity();
			   
			    glu.gluPickMatrix((double) pickPoint.x,
			        (double)windowY - pickPoint.y - 40, 
			        1.0, 1.0, viewport, 0);
			    
			    gl.glMultMatrixf(projection, 0);
			    gl.glMatrixMode(GL.GL_MODELVIEW);
			    render(gl, GL.GL_SELECT);
			    gl.glMatrixMode(GL.GL_PROJECTION);
			    
			gl.glPopMatrix();


			hits = gl.glRenderMode(GL.GL_RENDER);	
		    selectBuffer.get(buff);
		    selectedObj = getClosestHit(hits,buff);
			gl.glMatrixMode(GL.GL_MODELVIEW);

		}



		private int getClosestHit(int hits, int[] buff) {
			
			int closestName = buff[3];
			int closestZ = buff[1];
			
			for(int i=1; i < hits; i++) {
				if(buff[4*i+1] < closestZ) {
					closestName = buff[4*i+3];
					closestZ = buff[4*i+1];
				}
			}
			return closestName;
		}



		public void displayChanged(GLAutoDrawable drawable, boolean arg1, boolean arg2) {
			
		}

		public void reshape(GLAutoDrawable drawable, int x, int y, int w, int h) {
			   windowX = frame.getSize().width;
			   windowY = frame.getSize().height;	
			   
			    GL gl = drawable.getGL();
			    gl.glViewport(0, 0, w, h);
			    gl.glMatrixMode(GL.GL_PROJECTION);
			    gl.glLoadIdentity();
			    gl.glOrtho(-5.0, 5.0, -3.0, 3.0, -3.0, 3.0);
			    gl.glMatrixMode(GL.GL_MODELVIEW);
			    gl.glLoadIdentity();
		}

		public void mouseWheelMoved(MouseWheelEvent e) {
			
			int dir = e.getWheelRotation();
			if(dir < 0)
				scale = Math.max(scaleFactor*scale, 0.1);	
			else
				scale = Math.min(1/scaleFactor*scale, 1.5);	
			
		}


		public void mouseClicked(MouseEvent e) {
			
			//picking
			pickPoint = e.getPoint();
			
		}

		public void mouseEntered(MouseEvent e) {	
		}


		public void mouseExited(MouseEvent e) {	
		}


		public void mousePressed(MouseEvent e) {
			//dragging
			mousePressed = true;
			prev = e;
			dX = 0;
			dY = 0;
		}

		public void mouseReleased(MouseEvent e) {
			prev = null;
			mousePressed = false;
			dX *= 0.15;
			dY *= 0.15;
		}


		public void mouseDragged(MouseEvent e) {
			
			dragPoint = e.getPoint();
			mousePressed = true;
			if(prev != null) {
				
				int newX = e.getX();
				int newY = e.getY();
				dX = (newX - prev.getX())/scale/windowX*10.26;	
				dY = (newY - prev.getY())/scale/windowY*6.39;	
			}
			prev = e;

		}


		public void mouseMoved(MouseEvent e) {	
		}
		
		public void init(GLAutoDrawable drawable) {

			try {
				
				GL gl = drawable.getGL();									//get GL pipeline
				gl.glEnable(GL.GL_TEXTURE_2D);								//enable 2D textures
				gl.glEnable(GL.GL_BLEND);
				gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);
				BufferedImage moon = ImageIO.read(new File("moon.jpg"));	//read in image to use as texture
				BufferedImage hud = ImageIO.read(new File("hud.png"));
				ImageUtil.flipImageVertically(moon); 
				ImageUtil.flipImageVertically(hud); 
				hud_tex = TextureIO.newTexture(hud, true);
				moon_tex = TextureIO.newTexture(moon,true);					
				

			} catch (GLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
