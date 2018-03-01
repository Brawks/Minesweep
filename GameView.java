// Author: Samuel Saint-Fleur, Aden Li
// Student number: 300008314, 300022628
// Course: ITI 1121-C
// Assignment: 2
// Question: GameView
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * The class <b>GameView</b> provides the current view of the entire Game. It extends
 * <b>JFrame</b> and lays out a matrix of <b>DotButton</b> (the actual game) and 
 * two instances of JButton. The action listener for the buttons is the controller.
 *
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 * @author Samuel Saint-Fleur, Aden Li
 */

public class GameView extends JFrame {
    
    private DotButton[][] board;
    private GameModel gameModel;
    private javax.swing.JLabel nbreOfStepsLabel;

    /**
     * Constructor used for initializing the Frame
     * 
     * @param gameModel
     *            the model of the game (already initialized)
     * @param gameController
     *            the controller
     */

    public GameView(GameModel gameModel, GameController gameController) {

        super ("Minesweeper-ITI1121[C]");
        this.gameModel=gameModel;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton Qut = new JButton("Quit");
        JButton Rst = new JButton("Reset");
        JPanel mainScreen = new JPanel();
        JPanel stepScreen = new JPanel();

        board = new DotButton[gameModel.getWidth()][gameModel.getHeigth()];
        mainScreen.setLayout(new GridLayout(gameModel.getHeigth(),gameModel.getWidth()));
        Qut.addActionListener(gameController);
        Rst.addActionListener(gameController);

        //Invert due to board placing buttons vertically instead of horizontally
        for(int i = 0; i<gameModel.getHeigth();i++){
            for(int j =0; j<gameModel.getWidth();j++){
                board[j][i] = new DotButton(j,i,DotButton.COVERED);
                JButton srcBtn = (JButton) board[j][i];
                srcBtn.addActionListener(gameController);
                mainScreen.add(srcBtn);
            }
        }
        add(mainScreen,BorderLayout.CENTER);
        

        stepScreen.setLayout( new FlowLayout());
        nbreOfStepsLabel = new JLabel();
        nbreOfStepsLabel.setText("Number of Steps: "+gameModel.getNumberOfSteps());

        stepScreen.add(nbreOfStepsLabel);
        stepScreen.add(Qut);
        stepScreen.add(Rst);
        add(stepScreen, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        //System.out.println(gameModel.toString());
    }



    // ADD YOU CODE HERE

    /**
     * update the status of the board's DotButton instances based 
     * on the current game model, then redraws the view
     */

    public void update(){

    for (int w = 0; w<gameModel.getWidth(); w++){
        for(int h = 0; h<gameModel.getHeigth();h++){
            board[w][h].setIconNumber(getIcon(w,h));
        }
    }

    nbreOfStepsLabel.setText("Number of Steps: "+gameModel.getNumberOfSteps());

    }

    /**
     * returns the icon value that must be used for a given dot 
     * in the game
     * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the icon to use for the dot at location (i,j)
     */   
    private int getIcon(int i, int j){

        if(!(gameModel.isCovered(i,j))) {
            if (gameModel.isMined(i, j) == true){
                return (DotButton.MINED);
            }
            if (gameModel.getNeighbooringMines(i,j)==1){
                return (DotButton.ONE_NEIGHBOURS);
            }

            if (gameModel.getNeighbooringMines(i,j)==2){
                return (DotButton.TWO_NEIGHBOURS);
            }
            if (gameModel.getNeighbooringMines(i,j)==3){
                return (DotButton.THREE_NEIGHBOURS);
            }

            if (gameModel.getNeighbooringMines(i,j)==4){
                return (DotButton.FOUR_NEIGHBOURS);
            }
            if (gameModel.getNeighbooringMines(i,j)==5){
                return (DotButton.FIVE_NEIGHBOURS);
            }

            if (gameModel.getNeighbooringMines(i,j)==6){
                return (DotButton.SIX_NEIGHBOURS);
            }

            if (gameModel.getNeighbooringMines(i,j)==7){
                return (DotButton.SEVEN_NEIGHBOURS);
            }
            if (gameModel.getNeighbooringMines(i,j)==8){
                return (DotButton.EIGHT_NEIGHBOURS);
            }

            if (gameModel.getNeighbooringMines(i,j)==0){
                return (DotButton.ZERO_NEIGHBOURS);
            }
        }

        if ((gameModel.isMined(i,j)==true)&&(gameModel.hasBeenClicked(i,j)==true)){
            return (DotButton.CLICKED_MINE);
        }

        return (DotButton.COVERED);

    }


}
