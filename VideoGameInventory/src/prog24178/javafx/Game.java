/*
Name: Viktor Langeryt and Robert Verdi
File:  Platform.java
Other Files in this Project:
  VideoGameInventory.java
  Platform.java
  GameList.java
 */

package prog24178.javafx;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * This class represents a video game which has a number of attributes 
 * associated with it.
 * 
 * @author Robert Verdi
 * @author Viktor Langeryt
 */
public class Game implements Serializable {

	// Fields for records
	private String title = "TBA";
	private String publisher = "Unknown";
	private Platform system;
	private int releaseYear = 2010;
	private double price = 0.0;
	private int stockLevel = 0;
	private boolean used = false;
	
	// Field size constants
	public static final int SIZE_TITLE = 30; // each char is 2 bytes
	public static final int SIZE_RECORD = ((2 * SIZE_TITLE) + 8 + 4 + 4);
	/* Doubles are 8 bytes, ints are 4 bytes; 1 double and 2 ints for this 
	record. How the hell do you know how big an enum is?
	*/

	public Game() {
		// invoking all argument constructor
		this("TBD", "Unknown", Platform.XBOX360, 2010, 1.00, 0, true);
	}

	/**
	 * Constructor that accepts various aspects of a video game.
	 * 
	 * @param title The title of the game, as a string, max 30 characters
	 * @param publisher The publisher of the game as a string, max 30 characters
	 * @param system the platform which the game is on
	 * @param releaseYear the year of release for the game
	 * @param price current price of the game
	 * @param stockLevel quantity in stock
	 * @param used yes if the game is used, no if it's new
	 */
	public Game(String title, String publisher, Platform system, int releaseYear, double price,
			int stockLevel, boolean used) {

		setTitle(prepStringField(title, SIZE_TITLE));
		setPublisher(publisher);
		setPlatform(system);
		setReleaseYear(releaseYear);
		setPrice(price);
		setStockLevel(stockLevel);
		setUsed(used);
	}

	/**
	 * Retrieves the title of the game.
	 * 
	 * @return the title of the game, as a string
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title of the game. Will be run through the string prepper method
	 * in order to truncate it to 30 characters.
	 * 
	 * @param title the title of the game, as a string
	 */
	public void setTitle(String title) {
		if (title.isEmpty()) {
			throw new IllegalArgumentException("Error: Title cannot be blank");
		} else {
			this.title = title;
		}
	}
  
  /**
	 * Retrieves the publisher of the game.
	 * 
	 * @return the publisher of the game, as a string
	 */
	public String getPublisher() {
		return publisher;
	}

	/**
	 * Sets the publisher of the game. Will be run through the string prepper method
	 * in order to truncate it to 30 characters.
	 * 
	 * @param publisher the publisher of the game, as a string
	 */
	public void setPublisher(String publisher) {
		if (publisher.isEmpty()) {
			throw new IllegalArgumentException("Error: Publisher cannot be blank");
		} else {
			this.publisher = publisher;
		}
	}

	/**
	 * Retrieves the Platform enum for the system the game is on
	 * 
	 * @return Platform value for the game
	 */
	public Platform getPlatform() {
		return system;
	}

	/**
	 * Sets the Platform value for the game
	 * 
	 * @param system enum for the Platform of the game
	 */
	public void setPlatform(Platform system) {
		this.system = system;
	}

	/**
	 * Retrieves the release year for the game
	 * 
	 * @return the release year, as a 4-digit integer
	 */
	public int getReleaseYear() {
		return releaseYear;
	}

	/**
	 * Sets the release year of the game
	 * 
	 * @param releaseYear 
	 * @throws IllegalArgumentException if the year is invalid (less than 2000
	 * or in the future).
	 */
	public void setReleaseYear(int releaseYear) {
		if (releaseYear <= 2000 || releaseYear > 2018) {
			throw new IllegalArgumentException("Error: Year must be greater "
					+ "than ....");
		} else {
			this.releaseYear = releaseYear;
		}
	}

	/**
	 * Retrieves the current price of the video game
	 * 
	 * @return the price of the video game, in 0.00 format
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Sets the price of the video game
	 * 
	 * @param price the price of the video game, in 0.00 format
	 * @throws IllegalArgumentException if the price is 0.00 or less
	 */
	public void setPrice(double price) {
		if (price <= 0) {
			throw new IllegalArgumentException("Error: The Price must be "
					+ "greater than 0.");
		} else {
			this.price = price;
		}
	}

	/**
	 * Retrieves the current stock level of a video game
	 * 
	 * @return the current stock level of a video game
	 */
	public int getStockLevel() {
		return stockLevel;
	}

	/**
	 * Sets the stock level of a video game. 
	 * 
	 * @param stockLevel integer for the stock level of a video game
	 * @throws IllegalArgumentException of the stock value is less than zero.
	 */
	public void setStockLevel(int stockLevel) {
		if (stockLevel < 0) {
			throw new IllegalArgumentException("Error: Stock Level cannot be "
					+ "less than 0.");
		} else {
			this.stockLevel = stockLevel;
		}
	}

	/**
	 * Retrieves the boolean value for whether a game is used or new (true means
	 * used, false means new)
	 * 
	 * @return boolean value of whether game is used or new (true = used, false 
	 * = new)
	 */
	public boolean isUsed() {
		return used;
	}

	/**
	 * Sets whether a game is used or not
	 * 
	 * @param used true means game is used, false means it's new
	 */
	public void setUsed(boolean used) {
		this.used = used;
	}

	/**
	 * Prepares the string record values so they are a consistent length for
	 * entering into the file
	 * 
	 * @param value the string that is to be prepared
	 * @param size the length that the string must be
	 * @return a new string value for the title meeting size specifications for 
	 * file writing
	 */
	public String prepStringField(String value, int size) {
		if (value.length() > size) {
			return value.substring(0, size);
		}
		while (value.length() != size) {
			value += " ";
		}
		return value;
	}

	/**
	 * Allows the reading of a Game object for filing purposes
	 * 
	 * @param aInputStream an input stream
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 */
	private void readObject(ObjectInputStream aInputStream) throws ClassNotFoundException, IOException {
		title = aInputStream.readUTF();
		publisher = aInputStream.readUTF();
//		system = aInputStream.readUTF(getClass(system)); still don't know how to deal with this enum shit
		releaseYear = aInputStream.readInt();
		stockLevel = aInputStream.readInt();
		price = aInputStream.readDouble();
		used = aInputStream.readBoolean();
	}

	/**
	 * Allows the writing of a Game object for filing purposes
	 * 
	 * @param aOutputStream the output stream
	 * @throws IOException 
	 */
	private void writeObject(ObjectOutputStream aOutputStream) throws IOException {
		aOutputStream.writeUTF(title);
		aOutputStream.writeUTF(publisher);
//      aOutputStream.writeUTF(system); again with the enum shit
		aOutputStream.writeInt(releaseYear);
		aOutputStream.writeDouble(price);
		aOutputStream.writeInt(stockLevel);
		aOutputStream.writeBoolean(used);
	}

	/**
	 * Returns a string representation of the video game
	 * 
	 * @return string representation of video game
	 */
	@Override
	public String toString() {
		return String.format("Title: %s\nPublisher: %s\nPlatform: \nRelease Year: %d\nPrice: "
				+ "%f\nAmmount in Stock: %d\nUsed: %b", title, publisher, releaseYear, price,
				stockLevel, used);
	}
}
