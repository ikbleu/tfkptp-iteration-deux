

package src.view;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLException;
import javax.swing.JFrame;
import com.sun.opengl.util.Animator;


/**
 *
 * @author rdshack
 */
 class ScreenManager extends JFrame{
	 
	 	enum OptionalDisplay{
	 		NONE,
	 		KEYBINDING,
	 		OVERVIEW,
	 		TECHNOLOGY,
	 		COMMANDQUEUE;
	 	}
	 
	 	private static final long serialVersionUID = 100;
	 	
	 	private ViewPort viewPort;
	 	private HUD hud;
	 	private KeyBindingOverview keyBindingOverview;
	 	private Overview overview;
	 	private TechnologyTree technologyTree;
	 	private CommandQueueOverview commandQueueOverview;
	 	
	 	private OptionalDisplay optionalDisplay;
		
		private double scale;
		private double panX;
		private double panY;
		
		private GLCanvas canvas;
		
		private static final double scaleFactor = 0.8;
		private static final int windowWidth = 1280;
		private static final int windowHeight = 800;

		private static final int hudWidth = 1280;
		private static final int hudHeight = 250;
		
		private static final int overviewWidth = 300;
		private static final int overviewHeight = 420;
		
		
		private Animator animator;

		public ScreenManager(int mapWidth, int mapHeight){
			
			scale = 1.0;
			panX = 0;
			panY = 0;
			
			viewPort = new ViewPort(mapWidth, mapHeight);
			hud = new HUD(hudWidth, hudHeight);
			
			keyBindingOverview = new KeyBindingOverview();
			overview = new Overview(overviewWidth, overviewHeight);
			technologyTree = new TechnologyTree();
			commandQueueOverview = new CommandQueueOverview();
			
			optionalDisplay = OptionalDisplay.NONE;

			setSize(windowWidth, windowHeight);
			setTitle("Robot Unicorns...ATTACK!");
			setVisible(true);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setResizable(false);
			
			
			GraphicListener listener = new GraphicListener();
			GLCanvas canvas = new GLCanvas(new GLCapabilities());
		    canvas.addGLEventListener(listener);

		    getContentPane().add(canvas);
		    
		    animator = new Animator(canvas);
		    
		    this.validate();
		}
		
		public void start(){
			animator.start();
		}
		
		public void updateOverview(String title, String subTitle, String[] list) {
			overview.setTitle(title);
			overview.setSubTitle(subTitle);
			overview.setList(list);
		}

		
		public class GraphicListener implements GLEventListener {
			

			public void display(GLAutoDrawable drawable) {
			
				GL gl = drawable.getGL();
				gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);

				renderTexturedHex(gl, 0, 0, 1.0);
				

			}
			
			public void displayChanged(GLAutoDrawable drawable, boolean arg1, boolean arg2) {
			
			}
			
			public void reshape(GLAutoDrawable drawable, int x, int y, int w, int h) {	
			
				GL gl = drawable.getGL();
				gl.glViewport(0, 0, w, h);
				gl.glMatrixMode(GL.GL_PROJECTION);
				gl.glLoadIdentity();
				gl.glOrtho(-5.0, 5.0, -3.0, 3.0, -3.0, 3.0);	//specifies rendering box
				gl.glMatrixMode(GL.GL_MODELVIEW);
				gl.glLoadIdentity();
			}

			public void init(GLAutoDrawable drawable) {
			
				try {
					
					GL gl = drawable.getGL();									//get GL pipeline
					gl.glEnable(GL.GL_TEXTURE_2D);								//enable 2D textures
					gl.glEnable(GL.GL_BLEND);
					gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);
					
					gl.glMatrixMode(GL.GL_PROJECTION);
					gl.glLoadIdentity();
					gl.glOrtho(-5.0, 5.0, -3.0, 3.0, -3.0, 3.0);	//specifies rendering box
					gl.glMatrixMode(GL.GL_MODELVIEW);
					gl.glLoadIdentity();
					
				} 
				
				catch (GLException e) {
					e.printStackTrace();
				} 
			}
			
			private void renderTexturedHex(GL gl, double x, double y, double sideLength) {
				
				double r = sideLength*Math.cos(0.523);  //0.523 radians = 30 degrees
				double h = sideLength*Math.sin(0.523);

				gl.glBegin(GL.GL_POLYGON);
				
					gl.glTexCoord2d(0.0, 0.3);
					gl.glVertex2d(x,y);

					gl.glTexCoord2d(0.0, 0.7);
					gl.glVertex2d(x+h, y+r);
					
					gl.glTexCoord2d(0.5, 1.0);
					gl.glVertex2d(x+h+sideLength, y+r);
			
					gl.glTexCoord2d(1.0, 0.7);
					gl.glVertex2d(x+2*h+sideLength, y);
			
					gl.glTexCoord2d(1.0, 0.3);
					gl.glVertex2d(x+h+sideLength, y-r);
			
					gl.glTexCoord2d(0.5, 0.0);
					gl.glVertex2d(x+h, y-r);

				gl.glEnd();
			}
		}

}
