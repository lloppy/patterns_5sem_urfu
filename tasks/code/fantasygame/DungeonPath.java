package ask.urfu.misc.patterns.fantasygame;

import ask.urfu.misc.patterns.library.game.Location;
import ask.urfu.misc.patterns.library.game.PlayerPath;
import java.util.LinkedList;
import java.util.List;

public class DungeonPath implements PlayerPath {

  private final LinkedList<Location> locations = new LinkedList<>();

  @Override
  public Location start() {
    return locations.peekFirst();
  }

  @Override
  public Location end() {
    return locations.peekLast();
  }

  @Override
  public void register(Location location) {
    if (location != null) {
      locations.addLast(location);
    }
  }

  @Override
  public PathScan scan() {
    return new SimplePathScan(this.locations);
  }

  private static class SimplePathScan implements PathScan {

    private final List<Location> locations;
    private final int total;
    private int i = -1;

    private SimplePathScan(List<Location> locations) {
      this.locations = locations;
      this.total = locations.size();
    }

    @Override
    public boolean hasNext() {
      return i + 1 < total;
    }

    @Override
    public boolean hasPrev() {
      return i > 0;
    }

    @Override
    public Location next() {
      return locations.get(i++);
    }

    @Override
    public Location prev() {
      return locations.get(--i);
    }
  }
}
