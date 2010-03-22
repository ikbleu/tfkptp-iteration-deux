

package src.view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLException;
import javax.swing.JFrame;
import com.sun.opengl.util.Animator;
import com.sun.opengl.util.FPSAnimator;
import com.sun.opengl.util.ImageUtil;

import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureIO;

import src.control.interfaces.DisplayableBinding;
import src.control.interfaces.DisplayableKeyMap;
import src.model.interfaces.Displayable;
import src.util.SimpleMovingAverageTimer;
import src.model.interfaces.SakuraMap;

//
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.event.MouseInputListener;

import java.awt.Point;
import java.util.LinkedList;
//


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
                private int vpWidth;
                private int vpHeight;
	 	private HUD hud;
	 	private KeyBindingOverview keyBindingOverview;
	 	private Overview overview;
	 	private KBOverview kboverview;
	 	private TechnologyTree technologyTree;
	 	private CommandQueueOverview commandQueueOverview;
                private CommandSelection commandSelection;
                private ResourceInfo resourceInfo;
                private SakuraMap sakura;

                private GraphicsTableSingleton graphicsTable = GraphicsTableSingleton.getInstance();
	 	private LinkedList<Point> selectedTiles = new LinkedList<Point>();

	 	private OptionalDisplay optionalDisplay;
	 	private SimpleMovingAverageTimer timer;

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


                //
                private double dX=0;
                private double dY=0;
                private double friction;
                boolean mousePressed;

                private Point pickPoint= null;
                private Point dragPoint;

                private double offX=0;
                private double offY=0;
                MouseEvent prev;
                //

                //Textures
                Texture background_tex;
                Texture hud_tex;
		Texture commandSelection_tex;
                Texture resourceInfo_tex;
                Texture ViewPortTest_tex;
                Texture[][] ViewPortTex;
                Texture overview_tex;
                Texture kboverview_tex;
		
		private FPSAnimator animator;

		ScreenManager(int mapWidth, int mapHeight, SakuraMap sakura){
			
			timer = new SimpleMovingAverageTimer();
			timer.start();
			
			scale = 1.0;
			panX = 0;
			panY = 0;
			
			friction = 1.0;
                        this.sakura = sakura;

            vpWidth = sakura.mapWidth();
            vpHeight = sakura.mapHeight();
			
			viewPort = new ViewPort(mapWidth, mapHeight, sakura);
                        ViewPortTex = new Texture[mapHeight][mapWidth];
			hud = new HUD(hudWidth, hudHeight);
			
			keyBindingOverview = new KeyBindingOverview();
			overview = new Overview(overviewWidth, overviewHeight);
			kboverview = new KBOverview(overviewWidth, overviewHeight);
			technologyTree = new TechnologyTree();
			commandQueueOverview = new CommandQueueOverview();

                        commandSelection = new CommandSelection(null);
                        resourceInfo = new ResourceInfo();

                        resourceInfo.setResources(sakura.getPlayerResources());

                        
			
			optionalDisplay = OptionalDisplay.NONE;

			setSize(windowWidth, windowHeight);
			setTitle("Robot Unicorns...ATTACK!");
			setVisible(true);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setResizable(false);
			
			
			GraphicListener listener = new GraphicListener();
			GLCanvas canvas = new GLCanvas(new GLCapabilities());
                        canvas.addGLEventListener(listener);
                        canvas.addKeyListener(listener);

                        //
                        canvas.addMouseWheelListener(listener);
                        canvas.addMouseListener(listener);
                        canvas.addMouseMotionListener(listener);
                        //

                        getContentPane().add(canvas);
		    
                        animator = new FPSAnimator(canvas, 60);

                        
		    
		    this.validate();
		}
		
		void start(){
			animator.start();
		}

                void toggleViewPort(){
                    viewPort.toggleViewPort();
                }

                void setSelectedTiles(LinkedList<Point> tiles){
                    selectedTiles = tiles;
                }
		
		void updateOverview(String code, Displayable[] direct, Displayable[] dList, Displayable selected) {
			
			overview.setOverview(code, direct, dList, selected);
        	optionalDisplay = OptionalDisplay.OVERVIEW;
            overview.refreshImage();
            System.out.println("UPDATING THAT over");
            
            try {
                overview_tex = TextureIO.newTexture(overview.image(),true);
            }
            catch (GLException e) {
                e.printStackTrace();
            }


		}
		
		void updateKBOverview(DisplayableKeyMap dkm) {
			kboverview.setKBOverview(dkm);
            kboverview.refreshImage();
            System.out.println("UPDATING THAT KB");
            
            try {
                kboverview_tex = TextureIO.newTexture(kboverview.image(),true);
            }
            catch (GLException e) {
                e.printStackTrace();
            }
		}
		

                void updateStatusOverview(Displayable[] d){
                    hud.setStatusOverview(d);
                    hud.refreshImage();
                    try{
                        hud_tex = TextureIO.newTexture(hud.image(),true);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                void updateCommandSelection(Displayable[] d){
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
                    for(int i = 0; i < vpHeight; ++i){
                        for(int j =0; j < vpWidth; ++j){
                            try{
                                if(viewPort.get(i, j)!=null)
                                    ViewPortTex[i][j] = TextureIO.newTexture(viewPort.get(i, j),true);
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }

                void updateResourceInfo(){
                    resourceInfo.setResources(sakura.getPlayerResources());
                    resourceInfo.refreshImage();
                    try {
                        resourceInfo_tex = TextureIO.newTexture(resourceInfo.image(),true);
                    }
                    catch (GLException e) {
                        e.printStackTrace();
                    }
                }
                
                void initOverview(){
                    try {
                        overview_tex = TextureIO.newTexture(overview.image(),true);
                        kboverview_tex = TextureIO.newTexture(kboverview.image(),true);
                        
                    }
                    catch (GLException e) {
                        e.printStackTrace();
                    }
                }

		
		class GraphicListener implements GLEventListener, MouseWheelListener,
											MouseInputListener, KeyListener {
			


			public void display(GLAutoDrawable drawable) {
				
				timer.mark();
			
				GL gl = drawable.getGL();
				gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);

                //System.out.println(timer.marksPerSecond());
				//render different components
                                renderBG(gl);
                renderMap(gl, vpWidth, vpHeight);
                renderHUD(gl);
                renderCommandSelection(gl);
				renderResourceInfo(gl);
				

				switch(optionalDisplay) {
					case OVERVIEW: renderOverview(gl); break;
					case KEYBINDING: renderKBOverview(gl); break;
				}

			}



			private void renderBG(GL gl) {


                background_tex.bind();

	    		gl.glPushMatrix();

	    			gl.glBegin(GL.GL_POLYGON);

	    				gl.glTexCoord2d(0,1);
	    				gl.glVertex2d(0,1);

	    				gl.glTexCoord2d(0,0);
	    				gl.glVertex2d(0,0);

	    				gl.glTexCoord2d(1, 0);
	    				gl.glVertex2d(1, 0);

	    				gl.glTexCoord2d(1, 1);
	    				gl.glVertex2d(1, 1);


	    			gl.glEnd();

                gl.glPopMatrix();

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
            
            private void renderOverview(GL gl) {
            	
                double oWidth = .35;
                double oHeight = .60;

                overview_tex.bind();

                gl.glPushMatrix();

                gl.glBegin(GL.GL_POLYGON);

                	gl.glTexCoord2d(0.0, 0.0);
                	gl.glVertex2d(0.4,0.1);

                	gl.glTexCoord2d(1.0, 0.0);
                	gl.glVertex2d(0.4+oWidth, 0.1);

                	gl.glTexCoord2d(1.0, 1.0);
                	gl.glVertex2d(0.4+oWidth, 0.1+oHeight);

                	gl.glTexCoord2d(0.0, 1.0);
                	gl.glVertex2d(0.4, 0.1+oHeight);


                gl.glEnd();

                gl.glPopMatrix();
            	
            }
            
            private void renderKBOverview(GL gl) {
                double oWidth = .35;
                double oHeight = .60;

                kboverview_tex.bind();


                gl.glPushMatrix();

                gl.glBegin(GL.GL_POLYGON);

                	gl.glTexCoord2d(0.0, 0.0);
                	gl.glVertex2d(0.4,0.1);

                	gl.glTexCoord2d(1.0, 0.0);
                	gl.glVertex2d(0.4+oWidth, 0.1);

                	gl.glTexCoord2d(1.0, 1.0);
                	gl.glVertex2d(0.4+oWidth, 0.1+oHeight);

                	gl.glTexCoord2d(0.0, 1.0);
                	gl.glVertex2d(0.4, 0.1+oHeight);


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
                                         updateStatusOverview(null);
                                         updateResourceInfo();
                                         initOverview();
                                         updateCommandSelection(null);
                                         background_tex = TextureIO.newTexture(graphicsTable.getGraphic("space"),true);
                                         
					
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
                         
                
                gl.glPushMatrix();
	                gl.glScaled(scale, scale, 1.0f);
	                //gl.glTranslated(,.5,0.0);
			gl.glTranslated(offX,offY,0.0);
	                updateOffset();
	                
	                for(int i = 0; i < height; ++i){
	                     for(int j =0; j < width; ++j){
                                    if(viewPort.get(i, j)!=null){
                                        if(viewPort.getScheme(i, j) == 1){
                                            gl.glColor3d(.3,.3,.3);
                                        }
                                        if(viewPort.getScheme(i, j) == 2){
                                            gl.glColor3d(0,0,0);
                                        }
                                        renderViewPortHex(gl, beginX+i*.150, beginY + (i+(j*2))*.0866025 *(screenRatio), i, j);
                                    }
                                    gl.glColor3d(1,1,1);
	                     }
	                 }
	            gl.glPopMatrix();
            }
            //added
            private void updateOffset() {

			offX += dX;
			offY += dY;		//y-axis inverted in java

			if(!mousePressed){
				dX = dX*(1-friction);
				dY = dY*(1-friction);
			}
			else {
				dX = 0;
				dY = 0;
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

                        public void mouseClicked(MouseEvent e) {


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
				dX = (newX - prev.getX())/scale/370;
				dY = (newY - prev.getY())/scale/220;
			}
			prev = e;

		}


		public void mouseMoved(MouseEvent e) {
		}
                public void mouseWheelMoved(MouseWheelEvent e) {

			int dir = e.getWheelRotation();
			if(dir > 0)
				scale = Math.max(scaleFactor*scale, 0.3);
			else
				scale = Math.min(1/scaleFactor*scale, 1.5);

		}




				public void keyPressed(KeyEvent e) {
					// TODO Auto-generated method stub
					
				}




				public void keyReleased(KeyEvent e) {
					System.out.println("I hear a key");
					if(e.getKeyCode() == KeyEvent.VK_K) {
						System.out.println("i hear k");
						if (optionalDisplay == OptionalDisplay.KEYBINDING)
							optionalDisplay = OptionalDisplay.NONE;
						else
							optionalDisplay = OptionalDisplay.KEYBINDING;
							
					}
					
					
				}




				public void keyTyped(KeyEvent e) {
					

					
				}
		}

                


}
