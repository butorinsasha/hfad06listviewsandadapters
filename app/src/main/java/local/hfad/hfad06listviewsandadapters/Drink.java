package local.hfad.hfad06listviewsandadapters;

public class Drink {
    private final String name;
    private final String description;
    private final int imageResourceId;

    public static final Drink[] drinksArray = {
            new Drink("Latte", "A couple of espresso with streamed milk", R.drawable.latte),
            new Drink("Cuppuccino", "Espresso, hot milk, and a steamed milk foam", R.drawable.cappuccino),
            new Drink("Filter", "Highest quality beans roasted and brewed fresh", R.drawable.filter),
            new Drink("Americano", "No milk foam, just coffee and milk if you wish", R.drawable.americano)

    };

    public Drink(String name, String description, int imageResourceId) {
        this.name = name;
        this.description = description;
        this.imageResourceId = imageResourceId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public String toString() {
        return this.name;
    }
}
