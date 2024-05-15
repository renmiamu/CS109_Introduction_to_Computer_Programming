public abstract class Shape {
    protected char[][] grids;
    protected char pattern;
    protected Location location;
    public Shape(Location location, char pattern){
        this.location = location;
        this.pattern = pattern;
    }

    public char getPattern() {
        return pattern;
    }

    public char[][] getGrids() {
        return grids;
    }
    public abstract void fillGrids();

    public Location getLocation() {
        return location;
    }

    public abstract void enlarge();
    public abstract void shrink();
    public abstract int area();
}
