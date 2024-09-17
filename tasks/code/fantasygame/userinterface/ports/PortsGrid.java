package ask.urfu.misc.patterns.fantasygame.userinterface.ports;

public class PortsGrid {

  private ShowPort[][] grid = new ShowPort[1][1];

  public ShowPort get(int x, int y) {
    return grid[x][y];
  }

  public void addPort(int x, int y, ShowPort port) {
    ensureGridLargeEnough(x, y);
    grid[x][y] = port;
  }

  public int height() {
    return grid[0].length;
  }

  public int width() {
    return grid.length;
  }

  private void ensureGridLargeEnough(int x, int y) {
    int gridWidth = width();
    int gridHeight = height();

    int neededWidth = Math.max(x + 1, gridWidth);
    int neededHeight = Math.max(y + 1, gridWidth);

    if (gridWidth < neededWidth || gridHeight < neededHeight) {
      ShowPort[][] newGrid = new ShowPort[neededWidth][neededHeight];
      for (int i = 0; i < gridWidth; i++) {
        for (int j = 0; j < gridHeight; j++) {
          newGrid[i][j] = grid[i][j];
        }
      }
      grid = newGrid;
    }
  }

}
