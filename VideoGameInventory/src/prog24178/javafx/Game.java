package prog24178.javafx;

/**
 *
 * @author Robert Verdi
 */
public class Game {

	private String title = "TBA";
	private Platform system;
	private int releaseYear = 2010;
	private double price = 0.0;
	private int stockLevel = 0;
	private boolean used = false;

	public Game() {
		this.title = title;
		this.system = system;
		this.releaseYear = releaseYear;
		this.stockLevel = stockLevel;
		this.used = used;
	}

	public Game(String title, Platform system, int releaseYear, double price,
			int stockLevel, boolean used) {

		setTitle(title);
		setPlatform(system);
		setReleaseYear(releaseYear);
		setPrice(price);
		setStockLevel(stockLevel);
		setUsed(used);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		if (title.isEmpty()) {
			throw new IllegalArgumentException("Error: Title cannot be blank");
		} else {
			this.title = title;
		}
	}

	public Platform getPlatform() {
		return system;
	}

	public void setPlatform(Platform system) {
		this.system = system;
	}

	public int getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(int releaseYear) {
		if (releaseYear <= 2000) {
			throw new IllegalArgumentException("Error: Year must be greater "
					+ "than ....");
		} else {
			this.releaseYear = releaseYear;
		}
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		if (price <= 0) {
			throw new IllegalArgumentException("Error: The Price must be "
					+ "greater than 0.");
		} else {
			this.price = price;
		}
	}

	public int getStockLevel() {
		return stockLevel;
	}

	public void setStockLevel(int stockLevel) {
		if (stockLevel < 0) {
			throw new IllegalArgumentException("Error: Stock Level cannot be "
					+ "less than 0.");
		} else {
			this.stockLevel = stockLevel;
		}
	}

	public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}

//    private boolean validYear(int year) {
//        
//        boolean valid;
//    if(year <= 2000) {
//        throw new IllegalArgumentException("Error: Not a valid year");
//        valid = false;
//  } else {
//        valid = true;
//        return valid;
//    }
//}
	@Override
	public String toString() {

		return String.format("Title: %s\nPlatform: \nRelease Year: %d\nPrice: "
				+ "%f\nAmmount "
				+ "in Stock: %d\nUsed: %b", title, releaseYear, price,
				stockLevel, used);
	}
}
