package fr.iutvalence.theris.battleship.model;

/**
 * @author Robin
 * Player.
 */
public class Player {

	/**
	 * player's nickname.
	 */
	private final String nickname;

	/**
	 * create a player according to his nickname.
	 * @param nickname of the player
	 */
	public Player(String nickname){
		this.nickname=nickname;
	}
	
	/**
	 * allow to get player's nickname.
	 * @return wanted nickname
	 */
	public String getNickname() {
		return nickname;
	}
}
