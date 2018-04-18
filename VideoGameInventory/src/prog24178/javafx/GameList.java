/*
Name: Viktor Langeryt and Robert Verdi
File:  GameList.java
Other Files in this Project:
  VideoGameInventory.java
  Game.java
  GameList.java
  Platform.java
 */
package prog24178.javafx;

import java.util.ArrayList;

/**
 * This class represents a list of video games, as an ArrayList. It is a
 * collection of Game objects. It will allow for the sorting, display, editing,
 * adding, and removing records of video games.
 *
 * @see Game
 * @see Platform
 *
 * @author Viktor Langeryt
 * @author Robert Verdi
 */
public class GameList extends ArrayList<Game> {

	private ArrayList<Game> gameList = new ArrayList();

	/**
	 * Default GameList constructor
	 */
	public GameList() {
	}

	/**
	 * Constructor that accepts a variable list of Game objects.
	 *
	 * @param game game objects
	 */
    public GameList (Game...game){
        for(Game e: game){
            this.add(e);
        }
	}
	
	/**
	 * Constructor that accepts a value for the index of a Game object in 
	 * the list of Games.
	 * 
	 * @param index the index of a Game in the list of games.
	 */
	public GameList(int index) {		
	}
	
	/**
	 * Constructor that accepts both an index and a Game object value.
	 * 
	 * @param index an index in the list of Games
	 * @param g a Game object
	 */
	public GameList (int index, Game g) {
	}

	/**
	 * Retrieves a particular Game object from the ArrayList of Games.
	 *
	 * @param index the index in the ArrayList of the desired object.
	 * @return the Game object at the specified index.
	 * @throws IndexOutOfBoundsException if the given index is not in bounds of
	 * the current array list.
	 */
	@Override
	public Game get(int index) {
		if (index >= 0 && index < gameList.size()) {
			return gameList.get(index);
		} else {
			throw new IndexOutOfBoundsException("Error: Index must be greater "
					+ "than zero");
		}
	}

	/**
	 * Adds a game into the list. If it is being put into an index that is 
	 * occupied already, the other objects will be pushed down an index, and it 
	 * will be added in the created slot.
	 *
	 * @param index the index of the Game to be added
	 * @param g the Game object to be placed at the specified index.
	 * @throws IndexOutOfBoundsException if the index does not already exist in
	 * the array list.
	 */
	/* Replaced the "set" method, since this lets the "add" method in ArrayList
	* be overridden. 
	*/
	@Override
	public void add(int index, Game g) {
		if (index > 0 && index < gameList.size()) {
			gameList.add(index, g);
		} else {
			throw new IndexOutOfBoundsException("Error: The index does not "
					+ "exist in the array list yet");
		}
	}

//	/** Don't think we need this
//	 * Adds a Game object to the ArrayList.
//	 *
//	 * @param g the Game object to add to the list.
//	 */
//	public boolean add(Game g) {
//		// rough method
//		gameList.add(g);
//	}

	/**
	 * Removes from the list a Game object at a specified index.
	 *
	 * @param index index in the list of the Game object to be removed.
	 * @throws IndexOutOfBoundsException when the given index is not in bounds
	 * of the current the array list
	 * @return the removed Game object
	 */
	@Override
	public Game remove(int index) {
		Game removedGame = gameList.get(index);
		if (index >= 0 && index < gameList.size()) {
			gameList.remove(index);
			return removedGame;
		} else {
			throw new IndexOutOfBoundsException("Error: Index not found in "
					+ "bounds of the current array list.");
		}
	
	}

	/**
	 * Returns the index value of a specific Game object.
	 *
	 * @param g a Game object to be located.
	 * @return the index of the particular Game. Will return -1 if the Game is
	 * not in the list.
	 */
	public int indexOf(Game g) {
		return gameList.indexOf(g);
	}

	/**
	 * Checks to see if a Game object is in the gameList.
	 *
	 * @param g a Game object to search for
	 * @return true or false, depending on whether the Game is in the list.
	 */
	public boolean contains(Game g) {
		return gameList.contains(g);
	}

	/**
	 * Retrieves the size of the list of games.
	 *
	 * @return the size of the list of games.
	 */

	@Override
	public int size() {
		return gameList.size();
	}
	
	public GameList findGameByName(String searchTitle) {
		GameList searchResults = new GameList();
		
		return searchResults;
	}

	public GameList findGameByYear(int searchYear) {
		GameList searchResults = new GameList();
		
		return searchResults;
	}

}
