package fr.iutvalence.theris.battleship.controller;

import javax.swing.SwingUtilities;

import fr.iutvalence.theris.battleship.model.Board;
import fr.iutvalence.theris.battleship.model.Direction;
import fr.iutvalence.theris.battleship.model.Game;
import fr.iutvalence.theris.battleship.model.Location;
import fr.iutvalence.theris.battleship.model.Player;
import fr.iutvalence.theris.battleship.model.boats.AircraftCarrier;
import fr.iutvalence.theris.battleship.model.boats.Battleship;
import fr.iutvalence.theris.battleship.model.boats.Boat;
import fr.iutvalence.theris.battleship.model.boats.Cruiser;
import fr.iutvalence.theris.battleship.model.boats.Destroyer;
import fr.iutvalence.theris.battleship.model.boats.Submarine;
import fr.iutvalence.theris.battleship.view.Tuple;
import fr.iutvalence.theris.battleship.view.gui.BattleshipPanel;

public class Controller {

	private BattleshipPanel battleshipPanel;
	
	private Game game;
	
	public Controller() {
		battleshipPanel = new BattleshipPanel(this);
		SwingUtilities.invokeLater(this.battleshipPanel);
	}
	
	public Player getPlayer(int n_player){
		if (n_player==1)
			return game.getPlayer1();
		else return game.getPlayer2();
	}
	
	public void createNewGame(String player1, String player2){
		this.game = new Game(new Player(player1), new Player(player2));
	}
	
	public Board getPlayerBoard(int n_player){
		if (n_player==1)
			return game.getBoardPlayer1();
		else return game.getBoardPlayer2();
	}
	
	public boolean endGame(){
		return game.end();
	}
	
	public boolean addBoatToPlayer(int n_player, String direction, Tuple<Integer, Integer> location, String boat){
		Direction d = Direction.valueOf(direction.toUpperCase().trim());
		Location l = new Location(location.getFirstElement(), location.getSecondElement());
		Boat b = null;
		switch (boat.toLowerCase().trim()) {
		case "destroyer":
			b = new Destroyer();
			break;
		case "cruiser":
			b = new Cruiser();
			break;
		case "aircraft":
			b = new AircraftCarrier();
			break;
		case "submarine":
			b = new Submarine();
			break;
		case "battleship":
			b = new Battleship();
			break;
		}
		getPlayerBoard(n_player).createBoat(d, l, b);
		return false;
	}
}
