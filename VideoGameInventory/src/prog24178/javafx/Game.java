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
import javafx.beans.property.*;

/**
 * This class represents a video game which has a number of attributes 
 * associated with it.
 * 
 * @author Robert Verdi
 * @author Viktor Langeryt
 */
public class Game 
//		implements Serializable 
{

	private final StringProperty title;
    private final StringProperty publisher;
    private final StringProperty system;
    private final IntegerProperty stockLevel;
    private final IntegerProperty releaseYear;
	private final DoubleProperty price;
	private final BooleanProperty used;

	
	// Fields for records
//	private String title = "TBA";
//	private String publisher = "Unknown";
//	private String[] systemArr = {"Xbox One","Xbox 360", "Playstation 3", 
//		"Playstation 4", "PC", "Nintendo Switch", "Nintendo Wii", 
//		"Nintendo Wii-U", "Nintendo 3DS"};
//	private String system;
//	private int releaseYear = 2010;
//	private double price = 0.0;
//	private int stockLevel = 0;
//	private boolean used = false;
	
	// Field size constants
	public static final int SIZE_TITLE = 30; // each char is 2 bytes
	public static final int SIZE_PUB = 30;
	public static final int SIZE_PLATFORM = 17;
	public static final int SIZE_RECORD = ((2 * (SIZE_TITLE + SIZE_PUB + 
			SIZE_PLATFORM)) + 8 + 4 + 4);

	public Game() {
		// invoking all argument constructor
		this("TBD", "Unknown", "Xbox 360", 2010, 1.00, 0, true);
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
	public Game(String title, String publisher, String system, int releaseYear, double price,
			int stockLevel, boolean used) {
		
		this.title = new SimpleStringProperty(prepStringField(title, SIZE_TITLE));
		this.publisher = new SimpleStringProperty(prepStringField
		(publisher, SIZE_PUB));
		this.system = new SimpleStringProperty(prepStringField
		(system, SIZE_PLATFORM));
		this.releaseYear = new SimpleIntegerProperty(releaseYear);
		this.price = new SimpleDoubleProperty(price);
		this.stockLevel = new SimpleIntegerProperty(stockLevel);
		this.used = new SimpleBooleanProperty(used);

//		setTitle(prepStringField(title, SIZE_TITLE));
//		setPublisher(prepStringField(publisher, SIZE_PUB));
//		setPlatform(prepStringField(system, SIZE_PLATFORM));
//		setReleaseYear(releaseYear);
//		setPrice(price);
//		setStockLevel(stockLevel);
//		setUsed(used);
	}


	/**
	 * Retrieves the title of the game.
	 * 
	 * @return the title of the game, as a string
	 */
	public String getTitle() {
		return title.get();
	}

	/**
	 * Retrieves the title StringProperty
	 * @return title StringProperty
	 */
	public StringProperty titleProperty() {
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
			this.title.set(title);
		}
	}
  
  /**
	 * Retrieves the publisher of the game.
	 * 
	 * @return the publisher of the game, as a string
	 */
	public String getPublisher() {
		return publisher.get();
	}

	/**
	 * Retrieves the publisher StringProperty
	 *
	 * @return StringProperty for publisher
	 */
	public StringProperty publisherProperty() {
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
			this.publisher.set(publisher);
		}
	}

	/**
	 * Retrieves the system for the system the game is on
	 * 
	 * @return Platform value for the game
	 */
	public String getPlatform() {
		return system.get();
	}

	/**
	 * Retrieves the StringProperty for the system/console/platform/whatever
	 * @return StringProperty for console/gaming system
	 */
	    public StringProperty systemProperty() {
        return system;
    }
	/**
	 * Sets the console/system value for the game
	 * 
	 * @param system string for the system of the game
	 */
	public void setPlatform(String system) {
		this.system.set(system);
	}

	/**
	 * Retrieves the release year for the game
	 * 
	 * @return the release year, as a 4-digit integer
	 */
	public int getReleaseYear() {
		return releaseYear.get();
	}

	/**
	 * IntegerProperty for release year of game
	 * @return IntegerProperty for release year of game
	 */
	    public IntegerProperty yearProperty() {
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
			this.releaseYear.set(releaseYear);
		}
	}

	/**
	 * Retrieves the current price of the video game
	 * 
	 * @return the price of the video game, in 0.00 format
	 */
	public double getPrice() {
		return price.get();
	}

	/**
	 * DoubleProperty of price
	 * 
	 * @return the DoubleProperty of the price for the game
	 */
	    public DoubleProperty priceProperty() {
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
			this.price.set(price);
		}
	}

	/**
	 * Retrieves the current stock level of a video game
	 * 
	 * @return the current stock level of a video game
	 */
	public int getStockLevel() {
		return stockLevel.get();
	}

	/**
	 * The IntegerProperty for the game stock level
	 * 
	 * @return IntegerProperty for stock level 
	 */
	    public IntegerProperty stockLevelProperty() {
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
			this.stockLevel.set(stockLevel);
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
		return used.get();
	}

	/**
	 * The BooleanProperty value for the used boolean variable
	 * 
	 * @return BooleanProperty value for used 
	 */
	    public BooleanProperty usedProperty() {
        return used;
    }
	/**
	 * Sets whether a game is used or not
	 * 
	 * @param used true means game is used, false means it's new
	 */
	public void setUsed(boolean used) {
		this.used.set(used);
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
//	private void readObject(ObjectInputStream aInputStream) throws ClassNotFoundException, IOException {
//		title = aInputStream.readUTF();
//		publisher = aInputStream.readUTF();
////		system = aInputStream.readUTF(system.getName()); // still don't know how to deal with this enum shit
//		releaseYear = aInputStream.readInt();
//		stockLevel = aInputStream.readInt();
//		price = aInputStream.readDouble();
//		used = aInputStream.readBoolean();
//	}
//
//	/**
//	 * Allows the writing of a Game object for filing purposes
//	 * 
//	 * @param aOutputStream the output stream
//	 * @throws IOException 
//	 */
//	private void writeObject(ObjectOutputStream aOutputStream) throws IOException {
//		aOutputStream.writeUTF(title);
//		aOutputStream.writeUTF(publisher);
//		aOutputStream.writeUTF(system.getName()); // again with the enum
//		aOutputStream.writeInt(releaseYear);
//		aOutputStream.writeDouble(price);
//		aOutputStream.writeInt(stockLevel);
//		aOutputStream.writeBoolean(used);
//	}

	/**
	 * Returns a string representation of the video game
	 * 
	 * @return string representation of video game
	 */
	@Override
	public String toString() {
		return String.format("Title: %s\nPublisher: %s\nPlatform: %s\nRelease Year: %d\nPrice: "
				+ "%f\nAmmount in Stock: %d\nUsed: %b", title, publisher, 
				system, releaseYear, price, stockLevel, used);
	}
}
