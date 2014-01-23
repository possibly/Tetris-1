import java.awt.*; 
import javax.swing.*; 
import java.awt.event.*; 

/**
    This class will contain everything that 
    will be happening within the game window. 
*/ 

public class Action extends JPanel { 
    private final Color BACKGROUND_COLOR = Color.BLACK; //JPanel background 
    private GameMemory memory = new GameMemory();        
    private int[][] grid = memory.getGrid(); 
    private int milisecondsPerRepaint = 5;
    public static Color penColor = Color.GRAY;          //Variable for
                                                        //color

    public Action() { //Setting everything for JPanel
        setBackground(BACKGROUND_COLOR); 
        setMinimumSize(new Dimension(360, 720)); 
        setPreferredSize(new Dimension(360, 720)); 
        setMaximumSize(new Dimension(360, 720)); 
        setVisible(true); 
        new Timer(milisecondsPerRepaint, new TimerListener()).start(); 
        //Setting Everything for keylistener
        this.setFocusable(true); 
        this.requestFocus();
        this.addKeyListener(new MyKeyListener());  
    }

    @Override
    protected void paintComponent(Graphics pen) { 
        super.paintComponent(pen); 
        //This will go thrugh a 2D array and paint 
        //the figures that are not yet fallen into the color
        //that they are supposed to be. 
        for (int r = 0; r < grid.length; r++ ) 
            for (int c = 0; c < grid[c].length; c++){
                if (grid[r][c] == 1) 
                    pen.setColor(penColor); 
                else if (grid[r][c] == 2){ 
                    pen.setColor(penColor); 
                }
                else 
                    pen.setColor(Color.BLACK); 
                pen.fillRect(c * 30, r * 30, 
                            GameWindow.FRAME_SIZE_X/12, 
                            GameWindow.FRAME_SIZE_Y/24); 
                pen.setColor(Color.BLUE); 
                pen.drawRect(c * 30, r * 30, 
                            GameWindow.FRAME_SIZE_X/12, 
                            GameWindow.FRAME_SIZE_Y/24); 
                }    
    } 

    /**
        This class will repaint everything on JPanel once in a 
        specified period of time.
    */ 

    private class TimerListener implements ActionListener { 
        @Override
        public void actionPerformed(ActionEvent e) { 
            Action.this.repaint(); 
        } 
    } 

    /**
        This class will listen to the keys pressed and 
        react accordingly.
    */ 

    private class MyKeyListener implements KeyListener { 

        @Override
        public void keyPressed(KeyEvent e) { 
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) 
               Action.this.memory.figure.moveRight(); 
            if (e.getKeyCode() == KeyEvent.VK_LEFT) 
                Action.this.memory.figure.moveLeft(); 
            if (e.getKeyCode() == KeyEvent.VK_DOWN)
               Action.this.memory.figure.moveDown(); 
            if (e.getKeyCode() == KeyEvent.VK_UP)
               Action.this.memory.figure.rotateRight(); 

        }
        //Placeholders
        @Override 
        public void keyReleased(KeyEvent e) {}

        @Override 
        public void keyTyped(KeyEvent e) {} 
    }
} 
