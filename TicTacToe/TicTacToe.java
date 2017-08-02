import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class TicTacToe extends JFrame {
   JFrame frame = new JFrame("Tic-Tac-Toe");
   JButton[] buttons = new JButton[9];
   JButton resetButton = new JButton("Reset");
   boolean xturn = true;
   boolean gamewon = false;
   int gamecounter = 0;
   String buttonLabel;
   public TicTacToe() {
     super();
     frame.setSize(350, 450);
     frame.setDefaultCloseOperation(EXIT_ON_CLOSE);       
     frame.setVisible(true);
     frame.setResizable(false);
   }
   public void initialize() {
      JPanel mainPanel = new JPanel(new BorderLayout());
      JPanel menuPanel = new JPanel(new BorderLayout());
      JPanel gamePanel = new JPanel(new GridLayout(3,3));
      
      // set sizes
      mainPanel.setPreferredSize(new Dimension(325,425));
      menuPanel.setPreferredSize(new Dimension(300,50)); 
      gamePanel.setPreferredSize(new Dimension(300,300));
      
      // add mainPanel to frame
      frame.add(mainPanel);
      
      // add menuPanel and gamePanel to mainPanel
      mainPanel.add(menuPanel, BorderLayout.NORTH);
      mainPanel.add(gamePanel, BorderLayout.SOUTH);
      
      // Add reset button and add ActionListener
      menuPanel.add(resetButton);
      resetButton.addActionListener(new resetButtonActionListener());
      
      // Add 3 x 3 grid of buttons to gamePanel and add ActionListener to each      
      for(int i = 0; i < 9; i++) {
         buttons[i] = new JButton();
         buttonLabel = String.valueOf(i+1);
         buttons[i].setText(buttonLabel);
         buttons[i].setVisible(true);
         gamePanel.add(buttons[i]);
         buttons[i].addActionListener(new buttonActionListener());      
      }           
   }
   private class buttonActionListener implements ActionListener {
      public void actionPerformed(ActionEvent a) {
         String mark;
         if(xturn) {
               mark = "X";
               xturn = false; // Next time thru it will be O's turn
         } else {
             mark = "O";
             xturn = true;  // Next time thru it will be X's turn 
         }
         // Loop thru 9 buttons to see which was clicked.
         // When button found, set mark, disable button, increment gamecounter, check for win
         for (int j = 0; j < 9; j++) {
            if(a.getSource() == buttons[j]) {
                buttons[j].setText(mark);
                buttons[j].setEnabled(false);
                gamecounter++;
                checkWin(); 
            }
         } 
      }
   }
   private class resetButtonActionListener implements ActionListener {
       public void actionPerformed(ActionEvent a) {
          // If reset is pressed relabel and renable all buttons
          if(a.getSource() == resetButton) {
             for (int k = 0; k < 9; k++) {
                 buttonLabel = String.valueOf(k+1);
                 buttons[k].setText(buttonLabel);
                 buttons[k].setEnabled(true);                                        
             }
             // Always start with "X", gamecoutner to zero
             xturn = true;     
             gamecounter = 0;        
          }         
       }
   }
   private void checkWin() {
      //Horizontal Wins
      if(buttons[0].getText()==buttons[1].getText() && buttons[1].getText()==buttons[2].getText()) {
         gameover(buttons[0].getText());
      }
      else if(buttons[3].getText()==buttons[4].getText() && buttons[4].getText()==buttons[5].getText()) {
         gameover(buttons[3].getText());
      }
      else if(buttons[6].getText()==buttons[7].getText() && buttons[7].getText()==buttons[8].getText()) {
         gameover(buttons[6].getText());
      }
      
      // Vertical Wins
      else if(buttons[0].getText()==buttons[3].getText() && buttons[3].getText()==buttons[6].getText()) {
         gameover(buttons[0].getText());
      }
      else if(buttons[1].getText()==buttons[4].getText() && buttons[4].getText()==buttons[7].getText()) {
         gameover(buttons[1].getText());
      }
       else if(buttons[2].getText()==buttons[5].getText() && buttons[5].getText()==buttons[8].getText()) {
         gameover(buttons[2].getText());
      }
      
      // Diagnol Wins
      else if(buttons[0].getText()==buttons[4].getText() && buttons[4].getText()==buttons[8].getText()) {
         gameover(buttons[0].getText());
      }
      else if(buttons[2].getText()==buttons[4].getText() && buttons[4].getText()==buttons[6].getText()) {
         gameover(buttons[2].getText());
      }
      
      // Nobody Wins
      else if(gamecounter == 9) {
         gameover("Nobody");
      }         
   }
   public void gameover(String buttonText) {
      // Console message of winner
      System.out.println(buttonText + " wins");
      
      // Message acknowledgement of winner
      JOptionPane.showMessageDialog(this, buttonText + " wins", "GAME  OVER MAN" ,JOptionPane.PLAIN_MESSAGE);
      
      // Programmatically click resetButton
      resetButton.doClick();
   }
   public static void main(String[] args) {
      TicTacToe game = new TicTacToe();         
      game.initialize();
   }
}