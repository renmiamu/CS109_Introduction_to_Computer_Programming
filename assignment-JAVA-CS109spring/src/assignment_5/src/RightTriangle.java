public class RightTriangle extends Shape {
    private int width;
    private int height;
    private final Direction d;

    public RightTriangle(Location location, char pattern, int width, int height, Direction d) {
        super(location, pattern);
        this.width = width;
        this.height = height;
        this.d = d;
        grids = new char[height][width];
        fillGrids();
    }

    @Override
    public void fillGrids() {
        Double tanInit = (double) (height) / width;
        switch (d) {
            case RIGHT_UP:
                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < width; j++) {
                        if (j != 0) {
                            Double tanMax = (double) ((i + 1)) / j;
                            Double tanMin = (double) (i) / (j + 1);
                            if ((tanInit > tanMin && tanInit <= tanMax) || tanInit > tanMax) {
                                grids[i][j] = pattern;
                            } else {
                                grids[i][j] = ' ';
                            }
                        } else {
                            Double tanExist = (double) (i) / (j + 1);
                            if (tanInit > tanExist) {
                                grids[i][j] = pattern;
                            } else {
                                grids[i][j] = ' ';
                            }
                        }
                    }
                }
                break;
            case LEFT_UP:
                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < width; j++) {
                        if ((width -j-1) != 0) {
                            Double tanMax = (double) (i+1) / (width - j -1);
                            Double tanMin = (double) (i) / (width - j);
                            if ((tanInit > tanMin && tanInit <= tanMax) || tanInit > tanMax) {
                                grids[i][j] = pattern;
                            } else {
                                grids[i][j] = ' ';
                            }
                        } else {
                            Double tanExist = (double) (i) / (width - j);
                            if (tanInit > tanExist) {
                                grids[i][j] = pattern;
                            } else {
                                grids[i][j] = ' ';
                            }
                        }
                    }
                }
                break;
            case RIGHT_DOWN:
                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < width; j++) {
                        if (j != 0) {
                            Double tanMax = (double) (height - i) / (j);
                            Double tanMin = (double) (height - i - 1) / (j + 1);
                            if ((tanInit > tanMin && tanInit <= tanMax) || tanInit > tanMax) {
                                grids[i][j] = pattern;
                            } else {
                                grids[i][j] = ' ';
                            }
                        } else {
                            Double tanExist = (double) (height - i - 1) / (j + 1);
                            if (tanInit > tanExist) {
                                grids[i][j] = pattern;
                            } else {
                                grids[i][j] = ' ';
                            }
                        }
                    }
                }
                break;
            case LEFT_DOWN:
                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < width; j++) {
                        if ((width - j - 1) != 0) {
                            Double tanMax = (double) (height - i) / (width - j - 1);
                            Double tanMin = (double) (height - i - 1) / (width - j);
                            if ((tanInit > tanMin && tanInit <= tanMax) || tanInit > tanMax) {
                                grids[i][j] = pattern;
                            } else {
                                grids[i][j] = ' ';
                            }
                        } else {
                            Double tanExist = (double) (height - i - 1) / (width - j);
                            if (tanInit > tanExist) {
                                grids[i][j] = pattern;
                            } else {
                                grids[i][j] = ' ';
                            }
                        }
                    }
                }
                break;
        }

    }

    @Override
    public void enlarge() {
        height += 1;
        width += 1;
        grids = new char[height][width];
        fillGrids();
    }

    @Override
    public void shrink() {
        height -= 1;
        width -= 1;
        grids = new char[height][width];
        fillGrids();
    }

    @Override
    public int area() {
        int count = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (grids[i][j] == pattern) {
                    count += 1;
                }
            }
        }
        return count;
    }

    public String toString() {
        return String.format("RightTriangle: %s area=%d pattern=%s", location.toString(), area(), pattern);
    }
}
