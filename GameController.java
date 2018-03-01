// Author: Samuel Saint-Fleur, Aden Li
// Course: ITI 1121, University of Ottawa
// Assignment: 2
// Question: GameController
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.*;


/**
 * The class <b>GameController</b> is the controller of the game. It is a listener
 * of the view, and has a method <b>play</b> which computes the next
 * step of the game, and  updates model and view.
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 * @author Samuel Saint-Fleur, Aden Li
 */


public class GameController implements ActionListener {

    // ADD YOUR INSTANCE VARIABLES HERE
    private GameModel gameModel;
    private GameView gameView;

    /**
     * Constructor used for initializing the controller. It creates the game's view 
     * and the game's model instances
     * 
     * @param width
     *            the width of the board on which the game will be played
     * @param height
     *            the height of the board on which the game will be played
     * @param numberOfMines
     *            the number of mines hidden in the board
     */
    public GameController(int width, int height, int numberOfMines) {

    // ADD YOU CODE HERE
        gameModel = new GameModel(width,height,numberOfMines);
        gameView = new GameView(gameModel,this);
    }


    /**
     * Callback used when the user clicks a button (reset or quit)
     *
     * @param e
     *            the ActionEvent
     */

    public void actionPerformed(ActionEvent e) {
        
    // ADD YOU CODE HERE
        if(e.getActionCommand().equals("Quit"))
        {
            System.exit(0);
        }else if(e.getActionCommand().equals("Reset"))
        {
            reset();
        } else //if (e.getActionCommand().equals("DotB"))
        {
            int col, rw = 0;
            col = ((DotButton) e.getSource()).getColumn();
            rw = ((DotButton) e.getSource()).getRow();
            this.play(col,rw);
            gameView.update();
        }
    }

    /**
     * resets the game
     */
    private void reset(){

    // ADD YOU CODE HERE
        gameModel.reset();
        gameView.update();
    }

    /**
     * <b>play</b> is the method called when the user clicks on a square.
     * If that square is not already clicked, then it applies the logic
     * of the game to uncover that square, and possibly end the game if
     * that square was mined, or possibly uncover some other squares. 
     * It then checks if the game
     * is finished, and if so, congratulates the player, showing the number of
     * moves, and gives to options: start a new game, or exit
     * @param width
     *            the selected column
     * @param heigth
     *            the selected line
     */
    private void play(int width, int heigth){

    // ADD YOU CODE HERE
        if(!gameModel.hasBeenClicked(width,heigth))
        {
            gameModel.uncover(width,heigth);
            gameModel.click(width,heigth);
            gameModel.step();

            if(gameModel.isMined(width,heigth))
            {
                gameModel.uncoverAll();
                gameView.update();

                int decision = 0;
                decision = JOptionPane.showOptionDialog(null,"Would you like to play again?","BOOM! BIG SHAQ! YOU CLICKED A MINE!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

                switch(decision){
                    case JOptionPane.YES_OPTION: reset(); break;
                    case JOptionPane.NO_OPTION: System.exit(0); break;
                }

            }else if (gameModel.isFinished()) {
                gameModel.uncoverAll();
                gameView.update();

                int decision = 0;
                decision = JOptionPane.showOptionDialog(null,"Would you like to play again?","You beat the game in "+gameModel.getNumberOfSteps()+" steps!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                switch(decision){
                    case JOptionPane.YES_OPTION: reset(); break;
                    case JOptionPane.NO_OPTION: System.exit(0); break;
                }
            }
             else if(gameModel.isBlank(width,heigth))
            {
                    clearZone(gameModel.get(width,heigth));
            }
             
        }
        gameView.update();

    }

   /**
     * <b>clearZone</b> is the method that computes which new dots should be ``uncovered'' 
     * when a new square with no mine in its neighborood has been selected
     * @param initialDot
     *      the DotInfo object corresponding to the selected DotButton that
     * had zero neighbouring mines
     */
    private void clearZone(DotInfo initialDot) {

    // ADD YOU CODE HERE
        //Up to 8 neighbours
        int size = gameModel.getWidth() * gameModel.getHeigth();
        GenericArrayStack<DotInfo> stack = new GenericArrayStack<DotInfo>(size);

        stack.push(initialDot);
        while(!stack.isEmpty()) {
            DotInfo ndot = (DotInfo) stack.pop();
            int curx = ndot.getX();
            int cury = ndot.getY();
            if (ndot.getNeighbooringMines()==0) {
            if (gameModel.isCovered(curx+1,cury)==true&&!gameModel.isMined(curx+1,cury)){
                gameModel.uncover(curx+1,cury);
                gameModel.click(curx+1,cury);
                if(gameModel.isBlank(curx+1,cury)==true){
                    //System.out.println(gameModel.isBlank(curx+1,cury));
                    //System.out.println("Point : ("+(curx+1)+","+(cury)+")");
                    stack.push(gameModel.get(curx+1,cury));
                }
            }
            if (gameModel.isCovered(curx+1,cury+1)==true&&!gameModel.isMined(curx+1,cury+1)){
                gameModel.uncover(curx+1,cury+1);
                gameModel.click(curx+1,cury+1);
                if(gameModel.isBlank(curx+1,cury+1)==true){
                    //System.out.println(gameModel.isBlank(curx+1,cury+1));
                    //System.out.println("Point : ("+(curx+1)+","+(cury+1)+")");
                    stack.push(gameModel.get(curx+1,cury+1));
                }
            }
            if (gameModel.isCovered(curx+1,cury-1)==true&&!gameModel.isMined(curx+1,cury-1)){
                gameModel.uncover(curx+1,cury-1);
                gameModel.click(curx+1,cury-1);
                if(gameModel.isBlank(curx+1,cury-1)==true){
                    //System.out.println(gameModel.isBlank(curx+1,cury-1));
                    //System.out.println("Point : ("+(curx+1)+","+(cury-1)+")");
                    stack.push(gameModel.get(curx+1,cury-1));
                }
            }
            if (gameModel.isCovered(curx,cury+1)==true&&!gameModel.isMined(curx,cury+1)){
                gameModel.uncover(curx,cury+1);
                gameModel.click(curx,cury+1);
                if(gameModel.isBlank(curx,cury+1)==true){
                    //System.out.println(gameModel.isBlank(curx,cury+1));
                    //System.out.println("Point : ("+(curx)+","+(cury+1)+")");
                    stack.push(gameModel.get(curx,cury+1));
                }
            }
            if (gameModel.isCovered(curx,cury-1)==true&&!gameModel.isMined(curx,cury-1)){
                gameModel.uncover(curx,cury-1);
                gameModel.click(curx,cury-1);
                if(gameModel.isBlank(curx,cury-1)==true){
                    //System.out.println(gameModel.isBlank(curx,cury-1));
                    //System.out.println("Point : ("+(curx)+","+(cury-1)+")");
                    stack.push(gameModel.get(curx,cury-1));
                }
            }
            if (gameModel.isCovered(curx-1,cury)==true&&!gameModel.isMined(curx-1,cury)){
                gameModel.uncover(curx-1,cury);
                gameModel.click(curx-1,cury);
                if(gameModel.isBlank(curx-1,cury)==true){
                    //System.out.println(gameModel.isBlank(curx-1,cury));
                    //System.out.println("Point : ("+(curx-1)+","+(cury)+")");
                    stack.push(gameModel.get(curx-1,cury));
                }
            }
            if (gameModel.isCovered(curx-1,cury+1)==true&&!gameModel.isMined(curx-1,cury+1)){
                gameModel.uncover(curx-1,cury+1);
                gameModel.click(curx-1,cury+1);
                if(gameModel.isBlank(curx-1,cury+1)==true){
                    //System.out.println(gameModel.isBlank(curx-1,cury+1));
                    //System.out.println("Point : ("+(curx-1)+","+(cury+1)+")");
                    stack.push(gameModel.get(curx-1,cury+1));
                }
            }
            if (gameModel.isCovered(curx-1,cury-1)==true&&!gameModel.isMined(curx-1,cury-1)){
                gameModel.uncover(curx-1,cury-1);
                gameModel.click(curx-1,cury-1);
                if(gameModel.isBlank(curx-1,cury-1)==true){
                    //System.out.println(gameModel.isBlank(curx-1,cury-1));
                    //System.out.println("Point : ("+(curx-1)+","+(cury-1)+")");
                    stack.push(gameModel.get(curx-1,cury-1));
                }
            }
            }
        }
    }

}

