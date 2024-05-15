import java.util.ArrayList;
import java.util.List;

public class OverLapShapeCanvas implements ShapeCanvas{
    private List<Shape> shapes;
    private char[][] canvas;
    public OverLapShapeCanvas(int rows, int cols){
        shapes = new ArrayList<>();
        canvas = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                canvas[i][j]= ' ';
            }
        }
    }
    @Override
    public boolean addShape(int x, int y, char pattern, int... params) {
        boolean flag = false;
        if (params.length==1){
            int radius = params[0];
            Location location = new Location(x, y);
            Circle circle = new Circle(location, pattern, radius);
            char[][] grids = circle.getGrids();
            for (int i = 0; i < grids.length; i++) {
                for (int j = 0; j < grids[i].length; j++) {
                    if (grids[i][j]!=' '&& (x+i < canvas.length && y +j<canvas[i].length)){
                        flag = true;
                        canvas[x+i][y+j]=grids[i][j];
                    }
                }
            }
            if(flag){
                shapes.add(circle);
            }
        } else if (params.length == 3) {
            int width = params[0];
            int height = params[1];
            int rightDirection = params[2];
            Direction direction;
            if (rightDirection == 0) {
                direction = Direction.LEFT_UP;
            } else if (rightDirection == 1) {
                direction = Direction.LEFT_DOWN;
            } else if (rightDirection == 2) {
                direction = Direction.RIGHT_UP;
            } else {
                direction = Direction.RIGHT_DOWN;
            }
            Location location = new Location(x, y);
            RightTriangle rightTriangle = new RightTriangle(location, pattern, width, height, direction);
            char[][] grids = rightTriangle.getGrids();
            for (int i = 0; i < grids.length; i++) {
                for (int j = 0; j < grids[i].length; j++) {
                    if (grids[i][j]!=' '&& (x+i < canvas.length && y +j<canvas[i].length)){
                        flag = true;
                        canvas[x+i][y+j]=grids[i][j];
                    }
                }
            }
            if(flag){
                shapes.add(rightTriangle);
            }
        }
        return flag;
    }

    @Override
    public int getSpaceGridCount() {
        int count = 0;
        for (int i = 0; i < canvas.length; i++) {
            for (int j = 0; j < canvas[i].length; j++) {
                if (canvas[i][j] == ' ') {
                    count += 1;
                }
            }
        }
        return count;
    }

    @Override
    public int getShapeCount() {
        int count = shapes.size();
        return count;
    }

    @Override
    public List<Shape> getShapesByArea() {
        List<Shape> shapes1 = new ArrayList<>();
        for (int i = 0; i < shapes.size(); i++) {
            shapes1.add(shapes.get(i));
        }
        for (int i = 0; i < shapes1.size(); i++) {
            for (int j = shapes1.size()-1; j > i; j--) {
                if ((shapes1.get(j).area() < shapes1.get(j-1).area()) || (shapes1.get(j).area() == shapes1.get(j-1).area()&& shapes1.get(j).getPattern() <shapes1.get(j-1).getPattern()) ){
                    Shape temp = shapes1.get(j);
                    shapes1.set(j, shapes1.get(j-1));
                    shapes1.set(j-1, temp);
                }
            }
        }
        return shapes1;
    }

    @Override
    public List<Shape> getShapesByLocation() {
        List<Shape> shapes1 = new ArrayList<>();
        for (int i = 0; i < shapes.size(); i++) {
            shapes1.add(shapes.get(i));
        }
        for (int i = 0; i < shapes1.size(); i++) {
            for (int j = shapes1.size()-1; j > i; j--) {
                if (shapes1.get(j).getLocation().getX()< shapes1.get(j-1).getLocation().getX()|| (shapes1.get(j).getLocation().getX()== shapes1.get(j-1).getLocation().getX()&&shapes1.get(j).getLocation().getY()< shapes1.get(j-1).getLocation().getY())|| (shapes1.get(j).getLocation().getY() == shapes1.get(j-1).getLocation().getY()&&shapes1.get(j).getLocation().getX()==shapes1.get(j-1).getLocation().getX()&&shapes1.get(j).getPattern() <shapes1.get(j-1).getPattern())){
                    Shape temp = shapes1.get(j);
                    shapes1.set(j, shapes1.get(j-1));
                    shapes1.set(j-1, temp);
                }
            }
        }
        return shapes1;
    }

    @Override
    public char[][] getCanvas() {
        return canvas;
    }
}
