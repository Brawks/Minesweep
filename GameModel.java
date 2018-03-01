// Author: Samuel Saint-Fleur, Aden Li
// Course: ITI 1121, University of Ottawa
// Assignment: 2
// Question: GameModel
import java.util.Random;



/**
 * The class <b>GameModel</b> holds the model, the state of the systems. 
 * It stores the following information:
 * - the state of all the ``dots'' on the board (mined or not, clicked
 * or not, number of neighbooring mines...)
 * - the size of the board
 * - the number of steps since the last reset
 *
 * The model provides all of this informations to the other classes trough 
 *  appropriate Getters. 
 * The controller can also update the model through Setters.
 * Finally, the model is also in charge of initializing the game
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 * @author Samuel Saint-Fleur, Aden Li
 */
public class GameModel {

    private int width;
    private int heigth;
    private int numberofMines;
    private DotInfo[][] model;
    private int numberofSteps;
    private java.util.Random generator = new Random();

     // ADD YOUR INSTANCE VARIABLES HERE

    /**
     * Constructor to initialize the model to a given size of board.
     * 
     * @param width
     *            the width of the board
     * 
     * @param heigth
     *            the heigth of the board
     * 
     * @param numberOfMines
     *            the number of mines to hide in the board
     */
    public GameModel(int width, int heigth, int numberOfMines) {
        this.width=width;
        this.heigth=heigth;
        this.numberofMines=numberOfMines;
        numberofSteps=0;
        model = new DotInfo[width][heigth];

        for(int x = 0; x<model.length;x++){
            for(int y = 0;y<model[x].length;y++){
                model[x][y] = new DotInfo(x,y);
            }
        }
        // ADD YOU CODE HERE

        int mine = numberofMines;
        while(mine>0){
            int col = 0;
            int rw = 0;
            col = generator.nextInt(this.width);
            rw = generator.nextInt(this.heigth);

            if (!(model[col][rw].isMined())){
                model[col][rw].setMined();
                mine--;
            }
        }

        for(int w = 0; w<model.length; w++){
            for(int h = 0; h<model[w].length;h++){
                int curMine = 0;

                curMine += (this.isMined(w+1,h)) ? 1 : 0;
                curMine += (this.isMined(w+1,h+1)) ? 1 : 0;
                curMine += (this.isMined(w+1,h-1)) ? 1 : 0;
                curMine += (this.isMined(w,h+1)) ? 1 : 0;
                curMine += (this.isMined(w,h-1)) ? 1 : 0;
                curMine += (this.isMined(w-1,h)) ? 1 : 0;
                curMine += (this.isMined(w-1,h+1)) ? 1 : 0;
                curMine += (this.isMined(w-1,h-1)) ? 1 : 0;   
                model[w][h].setNeighbooringMines(curMine);
            }
        }
    }

 
    /**
     * Resets the model to (re)start a game. The previous game (if there is one)
     * is cleared up . 
     */
    public void reset(){

        numberofSteps=0;
        model = new DotInfo[width][heigth];
        for(int x = 0; x<model.length;x++){
            for(int y = 0;y<model[x].length;y++){
                model[x][y] = new DotInfo(x,y);
            }
        }
        // ADD YOU CODE HERE

        int mine = numberofMines;
        while(mine>0){
            int col = 0;
            int rw = 0;
            col = generator.nextInt(this.width);
            rw = generator.nextInt(this.heigth);

            if (!(model[col][rw].isMined())){
                model[col][rw].setMined();
                mine--;
            }
        }

        for(int w = 0; w<model.length; w++){
            for(int h = 0; h<model[w].length;h++){
                int curMine = 0;

                curMine += (this.isMined(w+1,h)) ? 1 : 0;
                curMine += (this.isMined(w+1,h+1)) ? 1 : 0;
                curMine += (this.isMined(w+1,h-1)) ? 1 : 0;
                curMine += (this.isMined(w,h+1)) ? 1 : 0;
                curMine += (this.isMined(w,h-1)) ? 1 : 0;
                curMine += (this.isMined(w-1,h)) ? 1 : 0;
                curMine += (this.isMined(w-1,h+1)) ? 1 : 0;
                curMine += (this.isMined(w-1,h-1)) ? 1 : 0;   
                model[w][h].setNeighbooringMines(curMine);
            }
            
        }
    }


    /**
     * Getter method for the heigth of the game
     * 
     * @return the value of the attribute heigthOfGame
     */   
    public int getHeigth(){
        return heigth;
    // ADD YOU CODE HERE
    }
    /**
     * Getter method for the width of the game
     * 
     * @return the value of the attribute widthOfGame
     */
    public int getWidth(){
        return width;

    // ADD YOU CODE HERE

    }



    /**
     * returns true if the dot at location (i,j) is mined, false otherwise
    * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the status of the dot at location (i,j)
     */   
    public boolean isMined(int i, int j){
        if (i<=-1 || i >= width || j <=-1 || j>= heigth) 
        {
            return false;
        }else {return model[i][j].isMined();}
    }

    /**
     * returns true if the dot  at location (i,j) has 
     * been clicked, false otherwise
     * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the status of the dot at location (i,j)
     */   
    public boolean hasBeenClicked(int i, int j){
        if (i<=-1 || i >= width || j <=-1 || j>= heigth) 
            {return true;} else {return model[i][j].hasBeenClicked();}
    // ADD YOU CODE HERE

    }

  /**
     * returns true if the dot  at location (i,j) has zero mined 
     * neighboor, false otherwise
     * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the status of the dot at location (i,j)
     */   
    public boolean isBlank(int i, int j){
        if (i<=-1 || i >= width || j <=-1 || j>= heigth)
            {return false;}
        else {return model[i][j].getNeighbooringMines() == 0;}

    // ADD YOU CODE HERE

    }
    /**
     * returns true if the dot is covered, false otherwise
    * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the status of the dot at location (i,j)
     */   
    public boolean isCovered(int i, int j){

        if (i<=-1 || i >= width || j <=-1 || j>= heigth)
        {return false;} else {return model[i][j].isCovered();}   
    // ADD YOU CODE HERE

    }

    /**
     * returns the number of neighbooring mines os the dot  
     * at location (i,j)
     *
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the number of neighbooring mines at location (i,j)
     */   
    public int getNeighbooringMines(int i, int j){

        if (i<=-1 || i >= width || j <=-1 || j>= heigth)
            {return 1;} else {return model[i][j].getNeighbooringMines();}
    // ADD YOU CODE HERE

    }


    /**
     * Sets the status of the dot at location (i,j) toed
     * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     */   
    public void uncover(int i, int j){
        if (!(i<=-1 || i >= width || j <=-1 || j>= heigth)) 
        {
        model[i][j].uncover();
        }
    // ADD YOU CODE HERE

    }

    /**
     * Sets the status of the dot at location (i,j) to clicked
     * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     */   
    public void click(int i, int j){
        if (!(i<=-1 || i >= width || j <=-1 || j>= heigth)) 
        {
        model[i][j].click();
        }
    // ADD YOU CODE HERE

    }
     /**
     * all remaining covered dot
     */   
    public void uncoverAll(){
        
    // ADD YOU CODE HERE
        for(int i=0;i<width;i++)
        {
            for(int z=0; z<heigth;z++)
            {
                model[i][z].uncover();    
            }
        }
            
    }

 

    /**
     * Getter method for the current number of steps
     * 
     * @return the current number of steps
     */   
    public int getNumberOfSteps(){


        return numberofSteps;
        
    // ADD YOU CODE HERE

    }

  

    /**
     * Getter method for the model's dotInfo reference
     * at location (i,j)
     *
      * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     *
     * @return model[i][j]
     */   
    public DotInfo get(int i, int j) {


        return model[i][j];
    // ADD YOU CODE HERE

    }


   /**
     * The metod <b>step</b> updates the number of steps. It must be called 
     * once the model has been updated after the payer selected a new square.
     */
     public void step(){
         numberofSteps=numberofSteps+1;

    }
 
   /**
     * The metod <b>isFinished</b> returns true iff the game is finished, that
     * is, all the nonmined dots areed.
     *
     * @return true if the game is finished, false otherwise
     */
    public boolean isFinished(){
        for(int i=0;i<width;i++){
            for(int z=0; z<heigth;z++){
                if(!model[i][z].isMined()) {
                    if (model[i][z].isCovered()) {
                        return false;
                    }
                }
                }
            }
            return true;
        }

        
    // ADD YOU CODE HERE


   /**
     * Builds a String representation of the model
     *
     * @return String representation of the model
     */

    public String toString(){
        String stR = "";
        for(int i=0;i<heigth;i++)
        {
            for(int z=0; z<width;z++)
            {
                if (model[z][i].isMined()){
                    stR+="M  ";
                } else if (isBlank(z,i)){
                    stR+="B  ";
                } else {
                    stR+="N"+getNeighbooringMines(z,i)+" ";
                }  
            }
            stR+="\n";
        }
        return stR;
    }
}
