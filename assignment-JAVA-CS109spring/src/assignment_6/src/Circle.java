public class Circle extends Shape {
    private int radius;



    public Circle(Location location, char pattern, int radius) {
        super(location, pattern);
        this.radius = radius;
        grids =new char[radius*2][radius*2];
        fillGrids();
    }

    @Override
    public void fillGrids() {
        int length = radius * 2;
        for (int i = 0; i < radius; i++) {
            for (int j = 0; j < radius; j++) {
                Double maxLength = Math.sqrt(Math.pow(radius - i, 2) + Math.pow(radius - j, 2));
                Double minLength = Math.sqrt(Math.pow(radius - i - 1, 2) + Math.pow(radius - j - 1, 2));
                if ((radius > minLength && radius <= maxLength)||(radius > maxLength)) {
                    grids[i][j] = pattern;
                    grids[i][length - 1 - j] = pattern;
                    grids[length - 1 - i][j] = pattern;
                    grids[length - 1 - i][length - 1 - j] = pattern;
                } else {
                    grids[i][j] = ' ';
                    grids[i][length - 1 - j] = ' ';
                    grids[length - 1 - i][j] = ' ';
                    grids[length - 1 - i][length - 1 - j] = ' ';
                }
            }
        }
    }

    @Override
    public void enlarge() {
        radius += 1;
        grids =new char[radius*2][radius*2];
        fillGrids();
    }

    @Override
    public void shrink() {
        radius -= 1;
        grids =new char[radius*2][radius*2];
        fillGrids();
    }

    @Override
    public int area() {
        int count = 0;
        for (int i = 0; i < radius*2; i++) {
            for (int j = 0; j < radius*2; j++) {
                if (grids[i][j] == pattern ){
                    count+=1;
                }
            }
        }
        return count;
    }

    public String toString(){
        return String.format("Circle: %s area=%d pattern=%s",location.toString(), area(),pattern);
    }
}
