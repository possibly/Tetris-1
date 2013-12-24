import javax.swing.*; 
import java.awt.event.*; 
import java.awt.*; 
import java.util.Arrays; 
import java.util.Random;
import java.lang.System;

/**
    This class will contain a two-dimensional array that will 
    keep in itself the figures, as well as all the logic. 
*/ 

public class GameMemory { 
    private int[][] grid = new int[24][12]; //Declaration of the JPanel
                                            //logical size.
                                            //
    private final int millisecondsPerMove = 500; //Defines the actual speed of the game. 
    Figure figure; 

    GameMemory() {

        figure = new Figure();
        figure.figureReinitialize();
        //Logic has it's own timer.
        Timer timer = new Timer(millisecondsPerMove, new TimerListener()); 
        timer.start(); 
    }


    /** 
        @retrun the grid with its values
    */ 
    public int[][] getGrid() { 
        return grid; 
    }

    /**
        This class defines everything the figure can do 
        as well as everything it can be. 
   */ 

    public class Figure { 
        private char figureType; 
    
        private int block1_X; 
        private int block1_Y; 
    
        private int block0_X = 6;   //This is the point where 
        private int block0_Y = 2;   //the figure's axis of rotation will appear. 
    
        private int block2_X; 
        private int block2_Y; 
    
        private int block3_X; 
        private int block3_Y; 
        
        /** 
            The constructor only initializes the type of figure. 
        */ 
        public Figure() { 
            setCoordinates(figureType = this.getFigure()); 
        }

        /**
            This method will set the coordinates of the 
            figure to certain values relatively to the 
            point of origin depending on the figure type 
            as well as it will set the color of the figure. 
        */

        public void setCoordinates(char figureType) {
            if (figureType == 'I') { 

                Action.penColor = Color.CYAN;

                block1_X = block0_X; 
                block1_Y = block0_Y + 1; 
                block2_X = block0_X; 
                block2_Y = block0_Y - 1; 
                block3_X = block0_X; 
                block3_Y = block0_Y - 2; 

            }
    
            else if(figureType == 'J') {
                
                Action.penColor = Color.WHITE;

                block1_X = block0_X; 
                block1_Y = block0_Y + 1; 
                block2_X = block0_X; 
                block2_Y = block0_Y - 1; 
                block3_X = block0_X - 1; 
                block3_Y = block0_Y - 1; 
            }
    
            else if(figureType == 'L') {

                Action.penColor = Color.ORANGE;

                block1_X = block0_X - 1; 
                block1_Y = block0_Y; 
                block2_X = block0_X + 1; 
                block2_Y = block0_Y; 
                block3_X = block0_X + 1; 
                block3_Y = block0_Y + 1; 

            }
    
            else if(figureType == 'O') {

                Action.penColor = Color.YELLOW;

                block1_X = block0_X; 
                block1_Y = block0_Y - 1; 
                block2_X = block0_X + 1; 
                block2_Y = block0_Y - 1; 
                block3_X = block0_X + 1; 
                block3_Y = block0_Y; 

            }
    
            else if(figureType == 'S') {

                Action.penColor = Color.GREEN;

                block1_X = block0_X - 1; 
                block1_Y = block0_Y; 
                block2_X = block0_X; 
                block2_Y = block0_Y + 1; 
                block3_X = block0_X + 1; 
                block3_Y = block0_Y + 1; 

            }
    
            else if(figureType == 'T') {

                Action.penColor = Color.MAGENTA;

                block1_X = block0_X + 1; 
                block1_Y = block0_Y; 
                block2_X = block0_X - 1; 
                block2_Y = block0_Y; 
                block3_X = block0_X; 
                block3_Y = block0_Y + 1; 

            }
    
            else if(figureType == 'Z') {

                Action.penColor = Color.RED;

                block1_X = block0_X + 1; 
                block1_Y = block0_Y; 
                block2_X = block0_X - 1; 
                block2_Y = block0_Y + 1; 
                block3_X = block0_X; 
                block3_Y = block0_Y + 1; 

            }
        }

        /** 
            This method randomly returns one of the 
            characters that represent type of the figure
            that is going to appear on the screen. 
            @return Character that represents the figure
        */

        private char getFigure() { 
            char[] figureType = {'I', 'J', 'L', 'O', 'S', 'T', 'Z'}; 
            return figureType[new Random().nextInt(7)];
        }
            
    
        //Theese are the accessors to X coordinates of the figure.
        public int getBlock0_X() { 
            return block0_X; 
        } 
    
    
        public int getBlock1_X() { 
            return block1_X; 
        } 
    
        public int getBlock2_X() { 
            return block2_X; 
        } 
    
        public int getBlock3_X() { 
            return block3_X; 
        } 
    
    
        //Theese are accessors to Y coordinate of the figure. 
        public int getBlock0_Y() { 
            return block0_Y; 
        } 
    
        public int getBlock1_Y() { 
            return block1_Y; 
        } 
    
        public int getBlock2_Y() { 
            return block2_Y; 
        } 
    
        public int getBlock3_Y() { 
            return block3_Y; 
        } 

       
        /** 
            This method checks if any of the cells 
            are already taken. 
            @param v To check the available range of y coordinate. 
            @return Wether there is something or not. 
        */ 
        public boolean collisionCheck(int v) { 
            int value0 = GameMemory.this.grid[figure.getBlock0_Y() + v]
                                [figure.getBlock0_X()];  
            int value1 = GameMemory.this.grid[figure.getBlock1_Y() + v]
                                [figure.getBlock1_X()]; 
            int value2 = GameMemory.this.grid[figure.getBlock2_Y() + v]
                                [figure.getBlock2_X()]; 
            int value3 = GameMemory.this.grid[figure.getBlock3_Y() + v]
                                [figure.getBlock3_X()]; 
            if (value0 == 2 || value1 == 2 || 
                value2 == 2 || value3 == 2) {
                return true;
            }
            else 
                return false; 
        } 
    
        /**
            After the raw is filled destroyed this
            method will make the filled cells above the 
            raw to fall down. 
            @param row The index of raw which was destroyed.
        */

        public void gridReajustment(int row) { 
            for (int r = row; r > 3; r--) {
                for (int c=0; c < GameMemory.this.grid[r].length; c++){
                    if(GameMemory.this.grid[r - 1][c] == 0) 
                        GameMemory.this.grid[r][c] = 0; 
                    else { 
                        GameMemory.this.grid[r][c] =
                        GameMemory.this.grid[r - 1][c];
                        GameMemory.this.grid[r - 1][c] = 0;
                    }
                }
            }
        }

        /**
            This method check wether the raw is complietly filled
            with cells and if it is - all the cells are goind 
            to be destroyd. 
        */
        public void searchNDestroy() {
            int cellCount = 0;  
            for(int i = 0; i < GameMemory.this.grid.length; i++) {
                for(int j = 0; j < GameMemory.this.grid[i].length; j++) {
                    if(GameMemory.this.grid[i][j] == 2) {
                        cellCount++; 
                        if(cellCount == 12) {
                            gridReajustment(i);
                            new AePlayWave("../Assets/sounds/row_dissapear.wav").start();
                        }
                    }
                    
                }
                //Nulify the counter after counting one row.
                cellCount = 0; 
            }
        }
            
        /**
            This method will check if the can move down 
            withoud colliding with other figures and 
            goiing out of the frame; it will stop the 
            figure otherwise.
        */ 

        public void moveDown() { 
            boolean check = GameMemory.this.figure.collisionCheck(1);
            if (GameMemory.this.figure.getBlock1_Y() < 22 && 
                GameMemory.this.figure.getBlock3_Y() < 22 && 
                GameMemory.this.figure.getBlock2_Y() < 22 &&
                check == false) { // Check collision and bounds.

                setGridZero();

                block0_Y += 1;  
                block1_Y += 1;  
                block2_Y += 1;  
                block3_Y += 1;  

                figureReinitialize();
                new AePlayWave("../Assets/sounds/move1.wav").start();

            }

            else 
                //If we reached the end of field or toched 
                //the other figure the current one stops.
                GameMemory.this.figure.stopFigure(); 
  
        }

        /**
            This method will stop the movement of the 
            figure by putting the value of 2 into the cell. 
        */
        public void stopFigure() { 

            GameMemory.this.grid[figure.getBlock0_Y()]
                                [figure.getBlock0_X() ] = 2; 
            GameMemory.this.grid[figure.getBlock1_Y()]
                                [figure.getBlock1_X() ] = 2; 
            GameMemory.this.grid[figure.getBlock2_Y()]
                                [figure.getBlock2_X() ] = 2; 
            GameMemory.this.grid[figure.getBlock3_Y()]
                                [figure.getBlock3_X() ] = 2; 

            new AePlayWave("../Assets/sounds/stop.wav").start();
            //After the figure is stopped this will 
            //check if any rows are full and destroy if there are
            GameMemory.this.figure.searchNDestroy();
            //Create a new figure.
            GameMemory.this.figure = new GameMemory.Figure(); 
        }

        public void moveRight() { 
            if (figure.canMoveRight() == true) {
                setGridZero();

                block0_X += 1;  
                block1_X += 1;  
                block2_X += 1;  
                block3_X += 1;  

                figureReinitialize();
            }
        }
        
        /**
            This method will check if the figure 
            won't exceed the array boundaries and 
            if there is not other figure on the right 
            @return The ability to move right.
        */

        public boolean canMoveRight() {
           if (figure.getBlock1_X() < 11 && 
               figure.getBlock0_X() < 11 &&
               figure.getBlock2_X() < 11 &&
               figure.getBlock3_X() < 11 &&
               GameMemory.this.grid[figure.getBlock0_Y()]
                                   [figure.getBlock0_X() + 1] != 2 &&
               GameMemory.this.grid[figure.getBlock1_Y()]
                                   [figure.getBlock1_X() + 1] != 2 &&
               GameMemory.this.grid[figure.getBlock2_Y()]
                                   [figure.getBlock2_X() + 1] != 2 &&
               GameMemory.this.grid[figure.getBlock3_Y()]
                                   [figure.getBlock3_X() + 1] != 2)
                return true; 
            else 
                return false;
        }

        public void moveLeft() { 
           if (figure.canMoveLeft() == true) {
                setGridZero();

                block0_X -= 1;  
                block1_X -= 1;  
                block2_X -= 1;  
                block3_X -= 1;  

                figureReinitialize();
            }
        }

        /**
            This method will check if the figure 
            won't exceed the array boundaries and 
            if there is not other figure on the left
            @return The ability to move left.
        */

        public boolean canMoveLeft() {
           if (figure.getBlock1_X() > 0 && 
               figure.getBlock0_X() > 0 &&
               figure.getBlock2_X() > 0 &&
               figure.getBlock3_X() > 0 &&
               GameMemory.this.grid[figure.getBlock0_Y()]
                                   [figure.getBlock0_X() - 1] != 2 &&
               GameMemory.this.grid[figure.getBlock1_Y()]
                                   [figure.getBlock1_X() - 1] != 2 &&
               GameMemory.this.grid[figure.getBlock2_Y()]
                                   [figure.getBlock2_X() - 1] != 2 &&
               GameMemory.this.grid[figure.getBlock3_Y()]
                                   [figure.getBlock3_X() - 1] != 2)
                return true; 
            else 
                return false;
        }

        /**       
            This will check if every single cell of the figure
            is capable of rotation and rotate them if they are.
        */
        public void rotateLeft() {

            int cellsCanRotate = 0; //for the figure to rotate around
                                    //its axis it needs to 
                                    //all 3 other cells to be rotatable
                                    //this variable will hold the 
                                    //number of cells that are able 
                                    //to rotate

            int[] rotatedCell1 = rotateCellLeft(block1_X, block1_Y); 
            cellsCanRotate += cellCanRotate(rotatedCell1[0],
                                            rotatedCell1[1]);
                                     
            int[] rotatedCell2 = rotateCellLeft(block2_X, block2_Y); 
            cellsCanRotate += cellCanRotate(rotatedCell2[0],
                                            rotatedCell2[1]);

            int[] rotatedCell3 = rotateCellLeft(block3_X, block3_Y); 
            cellsCanRotate += cellCanRotate(rotatedCell3[0],
                                            rotatedCell3[1]);

            if(cellsCanRotate == 3) {
                setGridZero();

                block1_X = rotatedCell1[0]; 
                block1_Y = rotatedCell1[1];  
                block2_X = rotatedCell2[0]; 
                block2_Y = rotatedCell2[1];  
                block3_X = rotatedCell3[0]; 
                block3_Y = rotatedCell3[1];  

                figureReinitialize();
            }
        }
        /**
            This method takes the current coordinates 
            of the cell and after rotation returns an array
            containing new coordinates. 
            @param block_X coordinate of the cell
            @param block_Y coordinate of the cell
            @return The array containing new x and y coordinates of the cell
        */

        private int[] rotateCellLeft(int block_X, int block_Y) { 
            block_X -= block0_X; 
            block_Y -= block0_Y; 
            block_Y *= -1; 
            
            int rblock_X = block_X; 
            int rblock_Y = block_Y;  
            rblock_X = (int)Math.round(block_X * Math.cos(Math.PI/2) - 
                            block_Y * Math.sin(Math.PI/2));
            rblock_Y = (int)Math.round(block_X * Math.sin(Math.PI/2) + 
                            block_Y * Math.cos(Math.PI/2));
            rblock_Y *= -1; 

            block_X = rblock_X + block0_X; 
            block_Y = rblock_Y + block0_Y; 
            return new int[] {block_X, block_Y};
        }

        /**       
            This will check if every single cell of the figure
            is capable of rotation and rotate them if they are.
        */

        public void rotateRight() { 
            int cellsCanRotate = 0;

            int[] rotatedCell1 = rotateCellRight(block1_X, block1_Y); 
            cellsCanRotate += cellCanRotate(rotatedCell1[0],
                                            rotatedCell1[1]);
                                     
            int[] rotatedCell2 = rotateCellRight(block2_X, block2_Y); 
            cellsCanRotate += cellCanRotate(rotatedCell2[0],
                                            rotatedCell2[1]);

            int[] rotatedCell3 = rotateCellRight(block3_X, block3_Y); 
            cellsCanRotate += cellCanRotate(rotatedCell3[0],
                                            rotatedCell3[1]);

            if(cellsCanRotate == 3 && this.figureType != 'O') {
                setGridZero();

                block1_X = rotatedCell1[0]; 
                block1_Y = rotatedCell1[1];  
                block2_X = rotatedCell2[0]; 
                block2_Y = rotatedCell2[1];  
                block3_X = rotatedCell3[0]; 
                block3_Y = rotatedCell3[1];  

                figureReinitialize();
            }
        }

        /**
            This method checks if a particular cell 
            will not exceed the boundaries of the 
            grid and won't bump into other cells during rotation. 
            @return The integer to count that cell will be able to rotate.
        */

        public int cellCanRotate(int X, int Y) {
            if(X > 11 || X < 0 || Y < 0 || Y > 23 || 
               GameMemory.this.grid[Y][X] == 2)
                return 0; 
            else
                return 1;
        }
            
            
        /**
            This method takes the current coordinates 
            of the cell and after rotation returns an array 
            containing new coordinates. 
            @param block_X coordinate of the cell
            @param block_Y coordinate of the cell
            @return The array containing new x and y coordinates of the cell
        */

        private int[] rotateCellRight(int block_X, int block_Y) { 
            block_X -= block0_X; 
            block_Y -= block0_Y; 
            block_Y *= -1; 
            
            int rblock_X = block_X; 
            int rblock_Y = block_Y;  
            rblock_X = (int)Math.round(block_X * Math.cos(-Math.PI/2) - 
                            block_Y * Math.sin(-Math.PI/2));
            rblock_Y = (int)Math.round(block_X * Math.sin(-Math.PI/2) + 
                            block_Y * Math.cos(-Math.PI/2));
            rblock_Y *= -1; 

            block_X = rblock_X + block0_X; 
            block_Y = rblock_Y + block0_Y; 
            return new int[] {block_X, block_Y};
        }

        /**
            This method sets the cells of the grid with 
            the current coordinates to 1 meaning that 
            the figure is not stopped yet. 
        */

        public void figureReinitialize() {

            GameMemory.this.grid[figure.getBlock0_Y()]
                                [figure.getBlock0_X() ] = 1; 
            GameMemory.this.grid[figure.getBlock1_Y()]
                                [figure.getBlock1_X() ] = 1; 
            GameMemory.this.grid[figure.getBlock2_Y()]
                                [figure.getBlock2_X() ] = 1; 
            GameMemory.this.grid[figure.getBlock3_Y()]
                                [figure.getBlock3_X() ] = 1; 
        }

        /**
            This method sets the cells of the grid with 
            the current coordinates to 0 if it is currently 1
            meaning that the figure will dissapear. 
        */
        public void setGridZero() { 
            for(int i = 0; i < GameMemory.this.grid.length; i++) {
                for(int j = 0; j < GameMemory.this.grid[i].length; j++) {
                    if(GameMemory.this.grid[i][j] == 1) 
                        GameMemory.this.grid[i][j] = 0; 
                }
            }
        }
    }

    /**
        This class will check if the figure is collided 
        during initialization which will mean that the 
        gird is overflowed and the game if over if this is 
        not the cause the figure will move down. 
    */

    private class TimerListener implements ActionListener { 
        @Override 
        public void actionPerformed(ActionEvent e) { 
            boolean check = GameMemory.this.figure.collisionCheck(0);
            if (check == false) { 
                figure.moveDown(); 
            }
            if(check == true) { 
                new AePlayWave("../Assets/sounds/game_over.wav").start();
                //This is just a timer to delay exit from the game 
                //in order to allow the audio file to play complietly. 
                new java.util.Timer().schedule( 
                    new java.util.TimerTask() {
                            @Override
                            public void run() {
                                System.out.println("You lost!");
                                System.exit(0);
                            }
                        }, 615);
            }
        } 
    }



}
