package fr.iutvalence.theris.battleship.model;

import java.util.ArrayList;
import java.util.List;

import fr.iutvalence.theris.battleship.model.boats.Boat;

/**
 * @author Robin
 * A game.
 */
public class Game {

	/**
	 * Player1's board.
	 */
	private Board boardPlayer1;
	
	/**
	 * player2's board.
	 */
	private Board boardPlayer2;
	
	/**
	 * first player.
	 */
	private Player player1;
	
	/**
	 * second player.
	 */
	private Player player2;
	
	/**
	 * number of turns, allow to know who plays.
	 */
	private int turn;
	
	private List<Boat> player1Boats;
	private List<Boat> player2Boats;
	
	/**
	 * game constructor.
	 * @param player1 first player of the game
	 * @param player2 second player of the game
	 */
	public Game(Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
		
		boardPlayer1 = new Board();
		boardPlayer2 = new Board();
		
		player1Boats = new ArrayList<Boat>();
		player2Boats = new ArrayList<Boat>();
		
		turn = 0;
	}
	
	// TODO Fin de jeu
	public boolean end(){
		return true;
	}
	
	public Player getPlayer1() {
		return player1;
	}
	
	public Player getPlayer2() {
		return player2;
	}
	
	public Board getBoardPlayer1() {
		return boardPlayer1;
	}
	
	public Board getBoardPlayer2() {
		return boardPlayer2;
	}
	
	public List<Boat> getPlayer1Boats() {
		return player1Boats;
	}
	
	public List<Boat> getPlayer2Boats() {
		return player2Boats;
	}
 
}
