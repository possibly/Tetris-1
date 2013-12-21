import javax.swing.*; 
import java.awt.*; 

/**
    This class sets all the options for
    the JFrame
*/

public class GameWindow { 
    private final String GAME_NAME = "Tetris"; 
    public static final int FRAME_SIZE_X = 360; 
    public static final int FRAME_SIZE_Y = 720; 
     
    public GameWindow() { 

        JFrame gameWindow = new JFrame(GAME_NAME); 
        gameWindow.setSize(FRAME_SIZE_X, FRAME_SIZE_Y); 
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        gameWindow.setResizable(false); 
        gameWindow.setLocationRelativeTo(null);
        JPanel grid = new JPanel(new GridLayout(1, 1, 1, 1)); 
        Action action = new Action(); //Creates a JPanel
        grid.add(action);
//        grid.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        gameWindow.add(grid); 
        gameWindow.setVisible(true); 
    }
    
    public int getFrameWidth() {
        return FRAME_SIZE_X;
    } 

    public int getFrameHeight() {
        return FRAME_SIZE_Y;
    } 
    
} 
    
    

