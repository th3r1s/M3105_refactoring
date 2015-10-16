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

	private Types nextType;
	
	private boolean listenerAllowed;
	
	private Random random;
	
	private Types currentType;
	

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
		nextType = Types.values()[random.nextInt(Types.values().length)];
		
	}

	public void play(){
		controller.refreshDisplay();
		if(!board.createTetrimino(new Tetriminos(nextType))){
			listenerAllowed = false;
			stopTimer();
			controller.notifyWin();
			return;
		}
		currentType = nextType;
		nextType = Types.values()[random.nextInt(Types.values().length)];
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
					board.setTetrimino(new Tetriminos(currentType),b.getBoxLocation());
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
			if(currentType != Types.O){
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
					tempList.get(i).setTetrimino(new Tetriminos(currentType));
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
	
	public int getScore() {
		return score;
	}
	
	public int getTetris() {
		return tetris;
	}
	
	public Types getNextType() {
		return nextType;
	}
	
	public boolean isListenerAllowed(){
		return listenerAllowed;
	}
	
}
