package tetris.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import tetris.controller.Controller;

/**
 * Class that represents the game launched in itself.
 * @author t3hr1s
 *
 */
public class Game {

	private Board board;
	
	private Timer timer;
	
	private boolean timerState;
	
	private Controller controller;
	
	private int score;
	
	private int tetris;

	private Tetrimino nextTetrimmino;
	
	private boolean listenerAllowed;
	
	private Random random;
	
	private Tetrimino currentTetrimino;
	
	private Location initLocation;
	

	public Board getBoard() 
	{
		return board;
	}


	public void setBoard(Board board) 
	{
		this.board = board;
	}


	/**
	 * Method that "starts" the game by creating its new player and its game Board.
	 */
	public Game(Controller controller)
	{
		this.board = new Board();
		this.controller = controller;
		score = 0;
		tetris = 0;
		listenerAllowed = true;
		random = new Random();
		nextTetrimmino = Tetrimino.values()[random.nextInt(Tetrimino.values().length)];
		initLocation = new Location(0,5);
		
	}

	public void play(){
		controller.refreshDisplay();
		if(!createTetrimino(nextTetrimmino, initLocation)){
			listenerAllowed = false;
			stopTimer();
			controller.notifyWin();
			return;
		}
		currentTetrimino = nextTetrimmino;
		nextTetrimmino = Tetrimino.values()[random.nextInt(Tetrimino.values().length)];
		startTimer();
		listenerAllowed = true;
	}
	
	public void startTimer(){
		timer = new Timer();
		timer.schedule(createTimer(), 0, 500);
		timerState = true;
	}
	
	public void stopTimer(){
		timer.cancel();
		timer.purge();
		timerState = false;
	}


	/**
	 * Method that creates the timer for the current game.
	 * @return TimerTask
	 */
	private TimerTask createTimer()
	{
		TimerTask tim = new TimerTask() 
		{
			@Override
			public void run() {
				if(!moveTetrimino("bottom")){
					listenerAllowed = false;
					stopTimer();
					removeLine();
					play();				
				}else{
					controller.refreshDisplay();
				}
				
			}
		};

		return tim;
	}

	public boolean moveTetrimino(String dir)
	{
		if(this.timerState){
			List<Box> oldBoxesList = new ArrayList<Box>();
			oldBoxesList = board.getPlayedBoxes();
			List<Box> nextBoxes = new ArrayList<Box>();
			try{

				for(Box b : oldBoxesList){
					nextBoxes.add(board.getNextBox(dir, b.getBoxLocation()));

				}
				boolean bool = false;
				for(Box b : nextBoxes){
					if(b.getState() == States.PLACED){
						bool = true;
						if(!dir.equals("bottom"))
							return false;
					}


				}
				if(bool){
					for(Box b1 : oldBoxesList)
					{
						board.removeTetrimino(b1.getBoxLocation(), States.PLACED);

					}
					return false;
				}

				for(Box b : oldBoxesList)
				{
					board.removeTetrimino(b.getBoxLocation(), States.EMPTY);

				}

				for(Box b : nextBoxes){
					board.setTetrimino(currentTetrimino,b.getBoxLocation());
				}


			}
			catch(ArrayIndexOutOfBoundsException e)
			{
				if(dir.equals("bottom")){
					for(Box b : oldBoxesList){
						board.removeTetrimino(b.getBoxLocation(), States.PLACED);

					}
				}
				return false;
			}


			board.move(nextBoxes);

			if(dir.equals("bottom"))
				score++;
			return true;
		}
		return false;
	}

	public boolean rotateTetrimino(){
		
		if(this.timerState){
			if(currentTetrimino != Tetrimino.O){
				List<Box> list = board.getPlayedBoxes();
				ArrayList<Box> tempList = new ArrayList<Box>();

				Location base = list.get(0).getBoxLocation();
				Location location;
				int x,y;
				tempList.add(list.get(0));
				for(int i=1;i<4;i++){			
					try{
						location = list.get(i).getBoxLocation();
						x = location.getRow()-base.getRow();
						y = location.getColumn()-base.getColumn();
						tempList.add(board.getBox(base.getRow()+y,base.getColumn()-x));
						if(tempList.get(i).getState() == States.PLACED)
							return false;
					}catch(ArrayIndexOutOfBoundsException e){return false;};
				}

				for(int i=0;i<4;i++){
					list.get(i).removeTetrimino(States.EMPTY);
				}
				for(int i=0;i<4;i++){
					tempList.get(i).setTetrimino(currentTetrimino);
				}
				board.move(tempList);

			}
			return true;
		}
		return false;

	}
	
	public void removeLine(){
		boolean b;
		int n = 0;
		for(int i=0;i<board.getRows();i++){
			b = true;
			for(int j=0;j<board.getColumns();j++){
				if(board.getBox(i, j).getState() == States.EMPTY){
					b = false;
					break;
				}
			}
			if(b){
				n++;
				for(int j=0;j<board.getColumns();j++){
					board.getBox(i, j).removeTetrimino(States.EMPTY);
				}

				for(int k=board.getColumns()-1;k>-1;k--){

					for(int j=i-1;j>-1;j--){
						try{
							board.getNextBox("bottom", new Location(j, k)).setTetrimino(board.getBox(j,k).getTetrimino(),board.getBox(j,k).getState());
						}catch(ArrayIndexOutOfBoundsException e){};
					}

				}
				
			}
			

		}
		if(n > 0){
			score += (((50*n*n*n) - (300*n*n) + (1150*n) - 600))/3;
			controller.tetrisSoundEffect();
		}
		if(n == 4)
			tetris++;
		controller.refreshDisplay();
	}
	
	/**
	 * Method allowing to set a tetrimino on the board by making the relatives Box around the first box having a tetrimino on it, depending on the tetrimino wanted.
	 * @param tetrimino
	 */
	private boolean createTetrimino(Tetrimino tetrimmino, Location location){
			
			List<Box> list = new ArrayList<Box>();
			
			Location[] tab = new Location[4];
			
			switch (tetrimmino){
			case I:
				tab[1]= location;
				tab[0]= new Location(location.getRow()+1,location.getColumn());
				tab[2]= new Location(location.getRow()+2,location.getColumn());
				tab[3]= new Location(location.getRow()+3,location.getColumn());
				break;
			case O:
				tab[0]= location;
				tab[1]= new Location(location.getRow()+1,location.getColumn());
				tab[2]= new Location(location.getRow()+1,location.getColumn()-1);
				tab[3]= new Location(location.getRow(),location.getColumn()-1);
				break;
			case L:
				tab[2]= location;
				tab[0]= new Location(location.getRow()+1,location.getColumn());
				tab[1]= new Location(location.getRow()+2,location.getColumn());
				tab[3]= new Location(location.getRow()+2,location.getColumn()+1);
				break;
			case Z:
				tab[0]= location;
				tab[3]= new Location(location.getRow(),location.getColumn()-1);
				tab[1]= new Location(location.getRow()+1,location.getColumn());
				tab[2]= new Location(location.getRow()+1,location.getColumn()+1);
				break;
			case S:
				tab[0]= location;
				tab[3]= new Location(location.getRow(),location.getColumn()+1);
				tab[1]= new Location(location.getRow()+1,location.getColumn());
				tab[2]= new Location(location.getRow()+1,location.getColumn()-1);
				break;
			case T:
				tab[0]= location;
				tab[1]= new Location(location.getRow()+1,location.getColumn());
				tab[2]= new Location(location.getRow(),location.getColumn()-1);
				tab[3]= new Location(location.getRow(),location.getColumn()+1);
				break;
			case J:
				tab[2]= location;
				tab[3]= new Location(location.getRow()+2,location.getColumn()-1);
				tab[0]= new Location(location.getRow()+1,location.getColumn());
				tab[1]= new Location(location.getRow()+2,location.getColumn());
				break;
			default :	break;
			}
			
			for(int i=0;i<4;i++){
				list.add(board.getBox(tab[i].getRow(),tab[i].getColumn()));
			}
			
			for(Box b : list){
				if(b.getState() != States.EMPTY)
					return false;
			}
			
			for(Box b : list){
				b.setTetrimino(tetrimmino);
			}
			
			board.setCurrentLocationPlayed(tab);
			
			return true;		
	}
	
	public int getScore() {
		return score;
	}
	
	public int getTetris() {
		return tetris;
	}
	
	public Tetrimino getNextType() {
		return nextTetrimmino;
	}
	
	public boolean isListenerAllowed(){
		return listenerAllowed;
	}
	
}
