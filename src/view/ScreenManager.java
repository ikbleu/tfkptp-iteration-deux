

package src.view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLException;
import javax.swing.JFrame;
import com.sun.opengl.util.Animator;
import com.sun.opengl.util.ImageUtil;

import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureIO;

import src.model.interfaces.Displayable;
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
        private CommandSelection commandSelection;
        private ResourceInfo resourceInfo;

        private GraphicsTableSingleton graphicsTable = GraphicsTableSingleton.getInstance();
	 	
	 	private OptionalDisplay optionalDisplay;

        private double screenRatio = 1.6;
		
		private double scale;
		private double panX;
		private double panY;

        private double hexWidth = .200;
        private double hexHeight = .173205;
		
		private GLCanvas canvas;
		
		private static final double scaleFactor = 0.8;
		private static final int windowWidth = 1280;
		private static final int windowHeight = 800;

		private static final int hudWidth = 1280;
		private static final int hudHeight = 250;
		
		private static final int overviewWidth = 300;
		private static final int overviewHeight = 420;


                //Textures
                Texture hud_tex;
		Texture commandSelection_tex;
                Texture resourceInfo_tex;
                Texture ViewPortTest_tex;
                Texture[][] ViewPortTex;
		
		private Animator animator;

		ScreenManager(int mapWidth, int mapHeight){
			
			scale = 1.0;
			panX = 0;
			panY = 0;
			
			viewPort = new ViewPort(mapWidth, mapHeight);
                        ViewPortTex = new Texture[mapHeight][mapWidth];
			hud = new HUD(hudWidth, hudHeight);
			
			keyBindingOverview = new KeyBindingOverview();
			overview = new Overview(overviewWidth, overviewHeight);
			technologyTree = new TechnologyTree();
			commandQueueOverview = new CommandQueueOverview();

                        commandSelection = new CommandSelection(null);
                        resourceInfo = new ResourceInfo();

                        
			
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
		
		void start(){
			animator.start();
		}
		
		void updateOverview(String title, String subTitle, String[] list) {
			overview.setTitle(title);
			overview.setSubTitle(subTitle);
			overview.setList(list);
		}

                void setStatusOverview(Displayable[] d){
                    hud.setStatusOverview(d);
                    hud.refreshImage();
                    try{
                        hud_tex = TextureIO.newTexture(hud.image(),true);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                void setCommandSelection(Displayable[] d){
                    commandSelection = new CommandSelection(d);
                    try {
    			commandSelection_tex = TextureIO.newTexture(commandSelection.image(),true);
                    }
                    catch (GLException e) {
    			e.printStackTrace();
                    }
                }

                void updateViewPort(){
                    viewPort.refreshImage();
                    //for testing
                    for(int i = 0; i < 15; ++i){
                        for(int j =0; j < 15; ++j){
                            try{
                                ViewPortTex[i][j] = TextureIO.newTexture(graphicsTable.getGraphic("Grassland"),true);
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }

                void updateResourceInfo(){
                    resourceInfo.refreshImage();
                    try {
                        resourceInfo_tex = TextureIO.newTexture(resourceInfo.image(),true);
                    }
                    catch (GLException e) {
                        e.printStackTrace();
                    }
                }

		
		class GraphicListener implements GLEventListener {
			

			public void display(GLAutoDrawable drawable) {
			
				GL gl = drawable.getGL();
				gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);

                                System.out.println("Hello");
				//render different components
				gl.glPushMatrix();
			
                                    gl.glScaled(scale, scale, 1.0f);
                                    //gl.glTranslated(offX,offY,0.0);
                                    //updateOffset();
                                    renderMap(gl, 15, 15);
				
                                gl.glPopMatrix();

                                renderHUD(gl);
                                renderCommandSelection(gl);
				renderResourceInfo(gl);

			}
            
            private void renderHUD(GL gl) {
            	 
            
                hud_tex.bind();
 
	    		gl.glPushMatrix();

	    			gl.glBegin(GL.GL_POLYGON);
	    			
	    				gl.glTexCoord2d(0,1);
	    				gl.glVertex2d(0,1);
	    				
	    				gl.glTexCoord2d(0,0);
	    				gl.glVertex2d(0,0.7);
	    				
	    				gl.glTexCoord2d(1, 0);
	    				gl.glVertex2d(1, 0.7);
	    				
	    				gl.glTexCoord2d(1, 1);
	    				gl.glVertex2d(1, 1);
	    				
	    				
	    			gl.glEnd();
	    			
                gl.glPopMatrix();

            }
            private void renderCommandSelection(GL gl) {


                double csWidth = .15626;
                double csBoxHeight = .08125;
                double boxes = commandSelection.boxes();
                commandSelection_tex.bind();

                gl.glPushMatrix();

                gl.glBegin(GL.GL_POLYGON);

                gl.glTexCoord2d(0.0, 0.0);
	    		gl.glVertex2d(1.0-csWidth,0.0);

                gl.glTexCoord2d(0.0, 1.0);
	    		gl.glVertex2d(1.0-csWidth, csBoxHeight*boxes);

                gl.glTexCoord2d(1.0, 1.0);
	    		gl.glVertex2d(1.0, csBoxHeight*boxes);

                gl.glTexCoord2d(1.0, 0.0);
	    		gl.glVertex2d(1.0, 0.0);


                gl.glEnd();

                gl.glPopMatrix();

            }

            private void renderResourceInfo(GL gl) {

                 

                 double riWidth = .2578125;
                 double riHeight = .1125;

                 resourceInfo_tex.bind();

                 gl.glPushMatrix();

                 gl.glBegin(GL.GL_POLYGON);

                 	gl.glTexCoord2d(0.0, 0.0);
                 	gl.glVertex2d(0.0,0.0);

                 	gl.glTexCoord2d(0.0, 1.0);
                 	gl.glVertex2d(0.0, riHeight);

                 	gl.glTexCoord2d(1.0, 1.0);
                 	gl.glVertex2d(riWidth, riHeight);

                 	gl.glTexCoord2d(1.0, 0.0);
                 	gl.glVertex2d(riWidth, 0.0);


                 gl.glEnd();

                 gl.glPopMatrix();

            }
			
			public void displayChanged(GLAutoDrawable drawable, boolean arg1, boolean arg2) {
			
			}
			
			public void reshape(GLAutoDrawable drawable, int x, int y, int w, int h) {	
			
				GL gl = drawable.getGL();
				gl.glViewport(0, 0, w, h);
				gl.glMatrixMode(GL.GL_PROJECTION);
				gl.glLoadIdentity();
				gl.glOrtho(0.0, 1.0, 1.0, 0.0, -1.0, 1.0);	//specifies rendering box
				gl.glMatrixMode(GL.GL_MODELVIEW);
				gl.glLoadIdentity();
			}

			public void init(GLAutoDrawable drawable) {
			
				try {
					
					GL gl = drawable.getGL();									//get GL pipeline
					gl.glEnable(GL.GL_TEXTURE_2D);								//enable 2D textures
					gl.glEnable(GL.GL_BLEND);
					gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);
                                        updateViewPort();
                                         setStatusOverview(null);
                                         updateResourceInfo();
                                         setCommandSelection(null);
					
				} 
				
				catch (GLException e) {
					e.printStackTrace();
				} 
			}
			
			private void renderHex(GL gl, double x, double y, double sideLength) {
				
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
                        
            private void renderMap(GL gl, int height, int width){
                double beginX =  .5 - (((double)width)/2.0 * .2);
                double beginY =  .5 - (((double)height)/2.0 * .173205 * (screenRatio));
                            
                for(int i = 0; i < height; ++i){
                     for(int j =0; j < width; ++j){
                            renderViewPortHex(gl, beginX+i*.150, beginY + (i+(j*2))*.0866025 *(screenRatio), i, j);
                     }
                 }
            }

            private void renderViewPortHex(GL gl, double x, double y, int i, int j) {

            	

                        ViewPortTex[i][j].bind();

				gl.glBegin(GL.GL_POLYGON);

					gl.glTexCoord2d(0.0, 0.5);
					gl.glVertex2d(x-.1,y);

					gl.glTexCoord2d(0.25, 0);
					gl.glVertex2d(x-.05,y-.0866025*(screenRatio));

					gl.glTexCoord2d(0.75, 0);
					gl.glVertex2d(x+.05, y-.0866025*(screenRatio));

					gl.glTexCoord2d(1.0, .5);
					gl.glVertex2d(x+.1, y);

					gl.glTexCoord2d(.75, 1.0);
					gl.glVertex2d(x+.05, y+.0866025*(screenRatio));

					gl.glTexCoord2d(.25, 1.0);
					gl.glVertex2d(x-.05, y+.0866025*(screenRatio));

				gl.glEnd();
			}
		}

}
