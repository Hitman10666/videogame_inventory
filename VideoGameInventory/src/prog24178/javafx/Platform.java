/*
Name: Viktor Langeryt and Robert Verdi
File:  Platform.java
Other Files in this Project:
  VideoGameInventory.java
  Game.java
  GameList.java
 */

package prog24178.javafx;

/**
 * This enum represents the possible platforms a video game can be published on.
 *
 * @author Viktor Langeryt
 * @author Robert Verdi
 */
public enum Platform {

    XBOXONE(1, "Xbox One"),
    XBOX360(2, "Xbox 360"),
    PS3(3, "Playstation 3"),
    PS4(4, "Playstation 4"),
    PC(5, "PC"),
    SWITCH(6, "Nintendo Switch"),
    WII(7, "Nintendo Wii"),
    WIIU(8, "Nintendo Wii-U"),
    DS(9, "Nintendo 3DS");

    private int index;
    private String name;

    private Platform(int index, String name) {
        this.index = index;
        this.name = name;
    }

    /**
     * Retrieves the name of the platform, as a string.
     * 
     * @return the name of the platform as a string.
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the index of a particular platform
     * 
     * @return the index of a particular platform
     */
    public int getIndex() {
        return index;
    }
}
