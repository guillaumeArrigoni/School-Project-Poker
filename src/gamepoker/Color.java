package gamepoker;

public class Color {
    private String name;

    public static Color Tr = new Color("Tr") ;
    public static Color Pi = new Color("Pi");
    public static Color Co = new Color("Co");
    public static Color Ca = new Color("Ca");

    public Color(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object object) {
        // If the object is compared with itself then return true
        if (object == this) {
            return true;
        }
        // Check if object is an instance of Value or not
        if (!(object instanceof Color)) {
            return false;
        }
        // Typecast object to Value so that we can compare both data
        Color color2 = (Color) object;

        // Compare the data members and return accordingly
        return color2.name.equals(this.name);
    }

    @Override
    public String toString() {
        return this.name;
    }


}