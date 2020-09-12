import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Layout extends JFrame{
	private Container contents;
	
	private JButton[][] squares = new JButton[8][7];
	
	private Color color = Color.WHITE;
	
	private int row = 7;
	private int col = 3;
	
	private ImageIcon sun = new ImageIcon("1.png");
	
	public Layout(){
		super("Webale Chess");
		
		contents = getContentPane();
		contents.setLayout(new GridLayout(8, 7));
		
		ButtonHandler buttonHandler = new ButtonHandler();
		
		for(int i = 0 ; i < 8 ; i++)
		{
			for(int j = 0 ; j < 7 ; j++)
			{
				squares[i][j] = new JButton();
				if((i + j) % 2 != 0)
				{
					squares[i][j].setBackground(colorBlack);
				}
				contents.add(squares[i][j]);
				squares[i][j].addActionListener(buttonHandler);
			}
		}
		squares[row][col].setIcon(sun);
		
		setSize(800, 800);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private boolean isValidMove(int i, int j){
		int rowDelta = Math.abs(i - row);
		int colDelta = Math.abs(j - col);
		
		if((rowDelta == 0) && (colDelta == 1))
			return true;
		
		if((colDelta == 0) && (rowDelta == 1))
			return true;
		
		return false;
	}
	
	private void processClick(int i , int j){
		if(isValidMove(i, j) == false)
			return;
		squares[row][col].setIcon(null);
		squares[i][j].setIcon(sun);
		row = i;
		col = j;
	}
	
	private class ButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			Object source = e.getSource();
			
			for(int i = 0 ; i < 8 ; i++)
			{
				for(int j = 0 ; j < 7 ; j++)
				{
					if(source == squares[i][j])
					{
						processClick(i, j);
						return;
					}
				}
			}
		}
	}
	
	public static void main(String[] args){
		Layout gui = new Layout();
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}