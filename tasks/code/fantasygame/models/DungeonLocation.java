package ask.urfu.misc.patterns.fantasygame.models;

import ask.urfu.misc.patterns.fantasygame.models.character.Monster;
import ask.urfu.misc.patterns.fantasygame.util.GameTraverser;
import ask.urfu.misc.patterns.fantasygame.util.Traversable;
import ask.urfu.misc.patterns.library.character.Enemy;
import ask.urfu.misc.patterns.library.game.Location;
import ask.urfu.misc.patterns.library.gear.Gear;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class DungeonLocation implements Location, Traversable {

  private final String name;
  private final List<Location> neighbours = new ArrayList<>();
  private final List<Enemy> enemies = new ArrayList<>();
  private final List<Gear> items = new ArrayList<>();

  public DungeonLocation(String name) {
    this.name = name;
  }


  @Override
  public String name() {
    return name;
  }

  @Override
  public Stream<Location> neighbours() {
    return neighbours.stream();
  }

  @Override
  public Stream<Enemy> enemies() {
    return enemies.stream();
  }

  @Override
  public Stream<Gear> items() {
    return items.stream();
  }

  @Override
  public void attach(Location... neighbours) {
    this.neighbours.addAll(Arrays.stream(neighbours).toList());
  }

  @Override
  public void attach(Enemy... enemies) {
    this.enemies.addAll(Arrays.stream(enemies).toList());
  }

  @Override
  public void attach(Gear... gear) {
    this.items.addAll(Arrays.stream(gear).toList());
  }

  @Override
  public void removeEnemy(Monster monster) {
    enemies.remove(monster);
  }

  @Override
  public void accept(GameTraverser traverser) {
    traverser.traverse(this);
    enemies().map(Monster.class::cast).forEach(m -> m.accept(traverser));
  }

}
