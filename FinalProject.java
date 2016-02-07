import java.util.Scanner;  
import java.util.concurrent.TimeUnit;
import java.awt.event.*;
import java.awt.*; 
import java.awt.Color; 
import java.awt.Graphics;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.*;


public class FinalProject {

	public static void main(String[] args) {
		Connect4JFrame frame = new Connect4JFrame();
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

class Connect4JFrame extends JFrame implements ActionListener {


	int[][]			theArray;
	boolean			end=false;
	private Button		btn1, btn2, btn3, btn4, btn5, btn6, btn7;
	private Label		lblSpacer;
	public static final int BLANK = 0;
	public static final int BLUE = 1;
	public static final int YELLOW = 2;

	public static final int NUM_ROWS = 7;	// 6 rows
	public static final int NUM_COLS = 7;	// 7 columns

	public static final String SPACE = "        "; 
	public Scanner input = new Scanner(System.in);
	public String player1, player2;

	public int activeColour = BLUE;
	Color winnercolor;
	public String message;
	
	public static int r=0,c=0,r1=0,c1=0,r2=0,c2=0,r3=0,c3=0;
	
	public Connect4JFrame() {	

		setTitle("Connect4");
		
		Panel panel = new Panel();
		btn1 = new Button("1");
		btn1.addActionListener(this);
		panel.add(btn1);
		lblSpacer = new Label(SPACE);
		panel.add(lblSpacer);

		btn2 = new Button("2");
		btn2.addActionListener(this);
		panel.add(btn2);
		lblSpacer = new Label(SPACE);
		panel.add(lblSpacer);

		btn3 = new Button("3");
		btn3.addActionListener(this);
		panel.add(btn3);
		lblSpacer = new Label(SPACE);
		panel.add(lblSpacer);

		btn4 = new Button("4");
		btn4.addActionListener(this);
		panel.add(btn4);
		lblSpacer = new Label(SPACE);
		panel.add(lblSpacer);

		btn5 = new Button("5");
		btn5.addActionListener(this);
		panel.add(btn5);
		lblSpacer = new Label(SPACE);
		panel.add(lblSpacer);

		btn6 = new Button("6");
		btn6.addActionListener(this);
		panel.add(btn6);
		lblSpacer = new Label(SPACE);
		panel.add(lblSpacer);

		btn7 = new Button("7");
		btn7.addActionListener(this);
		panel.add(btn7);

		add(panel, BorderLayout.SOUTH);
		
		
		getNames();

		initialize();
		// Set to a reasonable size.
		setSize(800, 1000);
	
		
		
		
	} // Connect4
	
	//get players' names
	public void getNames(){
		player1 = JOptionPane.showInputDialog("Player 1 Name:");
		player2 = JOptionPane.showInputDialog("Player 2 Name:");
	}
	public void initialize() {
		theArray=new int[NUM_ROWS][NUM_COLS];
		for (int row=0; row<NUM_ROWS; row++)
			for (int col=0; col<NUM_COLS; col++)
				theArray[row][col]=BLANK;

	} // initialize
	

	public void paint(Graphics g) {
		Panel panel1 = new Panel();
		
		panel1.add(new JLabel("Blue:       " +player1+"    "));
		panel1.add(new JLabel("Yellow:     "+player2));
		
		add(panel1, BorderLayout.NORTH);
		
		
		g.setColor(Color.BLACK);
		g.fillRect(100, 80, 80+80*NUM_COLS, 50+80*NUM_ROWS);
		for (int row=0; row<NUM_ROWS; row++)
			for (int col=0; col<NUM_COLS; col++) {
				if(!end){
					
					if (theArray[row][col]==BLANK) g.setColor(Color.WHITE);
					if (theArray[row][col]==BLUE) g.setColor(Color.BLUE);
					if (theArray[row][col]==YELLOW) g.setColor(Color.YELLOW);
					
				}
				else{//after winning

					
					if(row==r&&col==c){
						if(theArray[row][col]==BLUE||theArray[row][col]==YELLOW){
							theArray[row][col]=3;
				            g.setColor(Color.GREEN);
						}
						else{
							theArray[row][col]=BLUE;
				            g.setColor(winnercolor);
						}
					}
					
					else if(row==r1&&col==c1){
						if(theArray[row][col]==BLUE||theArray[row][col]==YELLOW){
								theArray[row][col]=3;
					            g.setColor(Color.GREEN);
							}
						else{
							theArray[row][col]=BLUE;
				            g.setColor(winnercolor);
						}
					}
					
					else if(row==r2&&col==c2){
						if(theArray[row][col]==BLUE||theArray[row][col]==YELLOW){
							theArray[row][col]=3;
				            g.setColor(Color.GREEN);
						}
						else{
							theArray[row][col]=BLUE;
				            g.setColor(winnercolor);
						}
					}
					
					else if(row==r3&&col==c3){
						if(theArray[row][col]==BLUE||theArray[row][col]==YELLOW){
							theArray[row][col]=3;
				            g.setColor(Color.GREEN);
						}
						else{
							theArray[row][col]=BLUE;
				            g.setColor(winnercolor);
						}
					}
					else{
						if (theArray[row][col]==BLANK) g.setColor(Color.WHITE);
						if (theArray[row][col]==BLUE) g.setColor(Color.BLUE);
						if (theArray[row][col]==YELLOW) g.setColor(Color.YELLOW);
					}
				}
				g.fillOval(130+80*col, 100+80*row, 80, 80);
			}
		if(!end) check4(g);
	} // paint

	public void putDisk(int n) {

		if (end) return;
		int row;
		n--;
		for (row=0; row<NUM_ROWS; row++)
			if (theArray[row][n]>0) break;
		if (row>0) {
			theArray[--row][n]=activeColour;
			if (activeColour==BLUE)
				activeColour=YELLOW;
			
			else
				activeColour=BLUE;	
			repaint();
		}
	}

	public void displayWinner(Graphics g, int n) {
		g.setColor(Color.RED);
		g.setFont(new Font("Courier", Font.BOLD, 200));
		if (n==BLUE)
			g.drawString(player1+ "wins!", 100, 400);
		else
			g.drawString(player2+ " wins!", 100, 400);
		
		playagain();
		
	}
	public void playagain(){
		
	}
	
	public void flashdisk(Graphics g){
		
		ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(new Runnable()
        {
            public void run()
            {
                repaint();
            }
        }, 0, 1, TimeUnit.SECONDS);
    }

	public void winnercolor(Graphics g){
		if(theArray[r][c]==BLUE)
			winnercolor=Color.BLUE;//blue wins
		else
			winnercolor=Color.YELLOW;//yellow wins
	}
	public void check4(Graphics g) {
	// see if there are 4 disks in a row: horizontal, vertical or diagonal
		// horizontal rows
		for (int row=0; row<NUM_ROWS; row++) {
			for (int col=0; col<NUM_COLS-3; col++) {
				int curr = theArray[row][col];
				if (curr>0
				 && curr == theArray[row][col+1]
				 && curr == theArray[row][col+2]
				 && curr == theArray[row][col+3]) {
					end=true;
					r=row;r1=row;r2=row;r3=row;c=col;c1=col+1;c2=col+2;c3=col+3;
					winnercolor(g);
					displayWinner(g, theArray[row][col]);
					flashdisk(g);
					
					
					
				}
			}
		}
		// vertical columns
		for (int col=0; col<NUM_COLS; col++) {
			for (int row=0; row<NUM_ROWS-3; row++) {
				int curr = theArray[row][col];
				if (curr>0
				 && curr == theArray[row+1][col]
				 && curr == theArray[row+2][col]
				 && curr == theArray[row+3][col]){
					end=true;
					r=row;r1=row+1;r2=row+2;r3=row+3;c=col;c1=col;c2=col;c3=col;
					winnercolor(g);
					displayWinner(g, theArray[row][col]);
					flashdisk(g);
					
					
				}
			}
		}
		// diagonal lower left to upper right
		for (int row=0; row<NUM_ROWS-3; row++) {
			for (int col=0; col<NUM_COLS-3; col++) {
				int curr = theArray[row][col];
				if (curr>0
				 && curr == theArray[row+1][col+1]
				 && curr == theArray[row+2][col+2]
				 && curr == theArray[row+3][col+3]){
					end=true;
					r=row;r1=row+1;r2=row+2;r3=row+3;c=col;c1=col+1;c2=col+2;c3=col+3;
					winnercolor(g);	
					displayWinner(g, theArray[row][col]);
					flashdisk(g);
				
					
				}
			}
		}
		// diagonal upper left to lower right
		for (int row=NUM_ROWS-1; row>=3; row--) {
			for (int col=0; col<NUM_COLS-3; col++) {
				int curr = theArray[row][col];
				if (curr>0
				 && curr == theArray[row-1][col+1]
				 && curr == theArray[row-2][col+2]
				 && curr == theArray[row-3][col+3]){
					end=true;
					r=row;r1=row-1;r2=row-2;r3=row-3;c=col;c1=col+1;c2=col+2;c3=col+3;
					winnercolor(g);
					displayWinner(g, theArray[row][col]);
					flashdisk(g);
					
					
				}
			}
		}
	} // end check4

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn1)
			putDisk(1);
		else if (e.getSource() == btn2)
			putDisk(2);
		else if (e.getSource() == btn3)
			putDisk(3);
		else if (e.getSource() == btn4)
			putDisk(4);
		else if (e.getSource() == btn5)
			putDisk(5);
		else if (e.getSource() == btn6)
			putDisk(6);
		else if (e.getSource() == btn7)
			putDisk(7);

	} // end ActionPerformed


} // class
