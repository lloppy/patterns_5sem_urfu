package ask.urfu.misc.patterns.fantasygame.models.character;

import ask.urfu.misc.patterns.fantasygame.models.gear.Item;
import ask.urfu.misc.patterns.fantasygame.util.GameTraverser;
import ask.urfu.misc.patterns.fantasygame.util.Traversable;
import ask.urfu.misc.patterns.library.character.CharacterModel;
import ask.urfu.misc.patterns.library.character.Enemy;
import ask.urfu.misc.patterns.library.gear.Gear;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Monster implements Enemy, Traversable {

  private final String name;
  private final CharacterModel attributes;
  private final List<Item> gear;

  // 😒 паблик
  public Monster(String name, CharacterModel attributes, List<Item> gear) {
    this.name = name;
    this.attributes = attributes;
    this.gear = new ArrayList<>(gear);
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public CharacterModel getModel() {
    return attributes;
  }

  @Override
  public Stream<Gear> gear() {
    return gear.stream().map(x -> x);
  }

  @Override
  public Enemy replicate() {
    return new Monster(this.name, this.attributes, this.gear);
  }

  @Override
  public void accept(GameTraverser traverser) {
    traverser.traverse(this);
  }
}
