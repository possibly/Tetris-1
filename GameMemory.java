import javax.swing.*; 
import java.awt.event.*; 
import java.awt.*; 
import java.util.Arrays; 
import java.util.Random;
import java.lang.System;

/**
    This class will contain a two-dimensional array that will 
    keep in itself the figures, as well as checking if the raws are 
    full. 
*/ 

public class GameMemory { 
    private int[][] grid = new int[24][12]; //Declaration of the JPanel
                                           //logical size.
    Figure figure; 

    GameMemory() {

        figure = new Figure();
        figure.figureReinitialize();
        //Logic has it's own timer.
        Timer timer = new Timer(500, new TimerListener()); 
        timer.start(); 
    }


    public int[][] getGrid() { 
        return grid; 
    }

    public class Figure { 
        private char figureType; 
    
        private int block1_X; 
        private int block1_Y; 
    
        private int block0_X = 6; 
        private int block0_Y = 3; 
    
        private int block2_X; 
        private int block2_Y; 
    
        private int block3_X; 
        private int block3_Y; 
        
        public Figure() { 
            figureType = this.getFigure(); 
//            System.out.println(figureType); 
            setCoordinates(figureType); 
        }

        //Randomly choses the figure
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

       
        //This method checks if every cell of the figure
        //has nothing to collide with. v is the distance of collision
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
                System.out.println(value1);
                System.out.println(value0);
                System.out.println(value2);
                System.out.println(value3);
                System.out.println("Y-s");
                System.out.println(figure.getBlock1_Y());
                System.out.println(figure.getBlock0_Y());
                System.out.println(figure.getBlock2_Y());
                System.out.println(figure.getBlock3_Y());
                System.out.println("Pure var");
                System.out.println(block1_Y);
                System.out.println(block0_Y);
                System.out.println(block2_Y);
                System.out.println(block3_Y);

                System.out.println("X-s");
                System.out.println(figure.getBlock1_X());
                System.out.println(figure.getBlock0_X());
                System.out.println(figure.getBlock2_X());
                System.out.println(figure.getBlock3_X());
                System.out.println("Pure var");
                System.out.println(block1_X);
                System.out.println(block0_X);
                System.out.println(block2_X);
                System.out.println(block3_X);
                return true;
            }
            else 
                return false; 
        } 
    
        //This mehtod will move all the rows down after destruction
        //of one of them
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

        //This method will search and destroy the filled rows.
        public void searchNDestroy() {
            int cellCount = 0; 
            for(int i=0; i < GameMemory.this.grid.length; i++) {
                for(int j=0; j < GameMemory.this.grid[i].length; j++) {
                    if(GameMemory.this.grid[i][j] == 2) {
                        cellCount++; 
                        if(cellCount == 12) {
                            System.out.println(j);
                            System.out.println("Destroy!");
                            gridReajustment(i);
                        }
                    }
                    
                }
                cellCount = 0; 
            }
        }
            
        
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
            }

            else 
                //If we reached the end of field or toched 
                //the other figure the current one stops.
                GameMemory.this.figure.stopFigure(); 
        }

        public void stopFigure() { 

            GameMemory.this.grid[figure.getBlock0_Y()]
                                [figure.getBlock0_X() ] = 2; 
            GameMemory.this.grid[figure.getBlock1_Y()]
                                [figure.getBlock1_X() ] = 2; 
            GameMemory.this.grid[figure.getBlock2_Y()]
                                [figure.getBlock2_X() ] = 2; 
            GameMemory.this.grid[figure.getBlock3_Y()]
                                [figure.getBlock3_X() ] = 2; 

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

        //This will check if every single cell of the figure
        //is capable of rotation and rotate then if they are.
        public void rotateLeft() {

            block0_X = block0_X;
            block0_Y = block0_Y; 

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
        //This is a complicated formula for a general case of 
        //rotation.Returns the rotated cell. 
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
            System.out.println(block_X); 
            System.out.println(block_Y); 
            return new int[] {block_X, block_Y};
        }

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

            if(cellsCanRotate == 3 && figureType != 'O') {
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

        public int cellCanRotate(int X, int Y) {
            if(X > 11 || X < 0 || Y < 0 || Y > 23 || 
               GameMemory.this.grid[Y][X] == 2)
                return 0; 
            else
                return 1;
        }
            
            
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
            System.out.println(block_X); 
            System.out.println(block_Y); 
            return new int[] {block_X, block_Y};
        }

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

        public void setGridZero() { 
            for(int i = 0; i < GameMemory.this.grid.length; i++) {
                for(int j = 0; j < GameMemory.this.grid[i].length; j++) {
                    if(GameMemory.this.grid[i][j] == 1) 
                        GameMemory.this.grid[i][j] = 0; 
                }
            }
        }

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
                
                Action.penColor = Color.BLUE;

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
    }

    private class TimerListener implements ActionListener { 
        @Override 
        public void actionPerformed(ActionEvent e) { 
            boolean heck = GameMemory.this.figure.collisionCheck(0);
            if (heck == false) { 
                System.out.println(heck);
                figure.moveDown(); 
                System.out.println(Arrays.deepToString(grid)); // To debug.
            }
            if(heck == true) { 
                System.out.println("You lost!");
                System.exit(0);
            }

        } 
    }



}
