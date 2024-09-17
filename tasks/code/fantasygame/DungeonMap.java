package ask.urfu.misc.patterns.fantasygame;

import ask.urfu.misc.patterns.fantasygame.models.DungeonLocation;
import ask.urfu.misc.patterns.fantasygame.util.GameTraverser;
import ask.urfu.misc.patterns.fantasygame.util.Traversable;
import ask.urfu.misc.patterns.library.game.Location;
import ask.urfu.misc.patterns.library.game.Map;
import ask.urfu.misc.patterns.library.game.PlayerPath;
import java.util.Deque;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class DungeonMap implements Map, Traversable {

  private final PlayerPath path;
  private DungeonLocation currentLocation;


  public DungeonMap(DungeonLocation currentLocation) {
    this.currentLocation = currentLocation;
    this.path = new DungeonPath();
  }


  @Override
  public DungeonLocation currentLocation() {
    return currentLocation;
  }

  @Override
  public PlayerPath history() {
    return path;
  }

  @Override
  public void moveTo(Location newLocation) {
    if (currentLocation.enemies().findAny().isPresent()) {
      throw new IllegalStateException("Can't move. Enemies around");
    }
    path.register(currentLocation);
    currentLocation = (DungeonLocation) newLocation;
  }

  @Override
  public void accept(GameTraverser traverser) {
    traverser.traverse(this);
    Set<DungeonLocation> traversed = new HashSet<>();
    Deque<DungeonLocation> traverseCandidates = new LinkedList<>(List.of(currentLocation()));
    do {
      DungeonLocation currentTraversedLocation = traverseCandidates.removeFirst();
      if (!traversed.contains(currentTraversedLocation)) {
        currentTraversedLocation.accept(traverser);
        traversed.add(currentTraversedLocation);
        traverseCandidates.addAll(
            currentTraversedLocation.neighbours()
                .map(DungeonLocation.class::cast)
                .filter(l -> !traversed.contains(l))
                .toList());
      }
    } while (!traverseCandidates.isEmpty());
  }


  public void move(Direction direction) {
    var locationMap = mapDirections();
    if (locationMap.containsKey(direction)) {
      Location newLocation = locationMap.get(direction);
      moveTo(newLocation);
    } else {
      throw new IllegalArgumentException("Can't move. No locations in this direction");
    }
  }

  private java.util.Map<Direction, Location> mapDirections() {
    EnumMap<Direction, Location> map = new EnumMap<>(Direction.class);
    Location previousLocation = history().end();
    List<Location> otherLocations = currentLocation.neighbours()
        .filter(location -> location != previousLocation)
        .toList();
    switch (otherLocations.size()) {
      case 1 -> map.put(Direction.AHEAD, otherLocations.get(0));
      case 2 -> {
        map.put(Direction.LEFT, otherLocations.get(0));
        map.put(Direction.RIGHT, otherLocations.get(1));
      }
      case 3 -> {
        map.put(Direction.AHEAD, otherLocations.get(0));
        map.put(Direction.LEFT, otherLocations.get(1));
        map.put(Direction.RIGHT, otherLocations.get(2));
      }
      default -> { // no other options valid
      }
    }
    map.put(Direction.BACK, previousLocation);

    return map;
  }

}
