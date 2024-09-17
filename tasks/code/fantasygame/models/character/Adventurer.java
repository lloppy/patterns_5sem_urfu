package ask.urfu.misc.patterns.fantasygame.models.character;

import ask.urfu.misc.patterns.fantasygame.models.FantasyCharacterModel;
import ask.urfu.misc.patterns.fantasygame.models.PlayerCharacterClass;
import ask.urfu.misc.patterns.fantasygame.models.gear.Item;
import ask.urfu.misc.patterns.fantasygame.models.perks.Perk;
import ask.urfu.misc.patterns.fantasygame.util.GameTraverser;
import ask.urfu.misc.patterns.fantasygame.util.Traversable;
import ask.urfu.misc.patterns.library.character.CharacterClass;
import ask.urfu.misc.patterns.library.character.PlayerCharacter;
import ask.urfu.misc.patterns.library.gear.Gear;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

public class Adventurer implements PlayerCharacter, Traversable {
  // 😒 нет одного инстанса

  private final List<Perk> perks = new ArrayList<>();
  private final List<Item> gear = new ArrayList<>();
  private String name;
  private FantasyCharacterModel attributes;
  private PlayerCharacterClass chosenClass;

  // 😒 паблик
  public Adventurer(
      String name,
      FantasyCharacterModel attributes,
      PlayerCharacterClass chosenClass,
      Collection<Item> gear) {
    this.name = name;
    this.attributes = attributes;
    this.chosenClass = chosenClass;
    this.gear.addAll(gear);
  }

  // 😒 паблик
  public Adventurer(
      String name,
      FantasyCharacterModel attributes,
      PlayerCharacterClass chosenClass,
      Item... gear) {
    this(name, attributes, chosenClass, new ArrayList<>(Arrays.asList(gear)));
  }


  @Override
  public String getName() {
    return name;
  }

  @Override
  public FantasyCharacterModel getModel() {
    return attributes;
  }

  public void setModel(FantasyCharacterModel model) {
    this.attributes = model;
  }

  @Override
  public Stream<Gear> gear() {
    return gear.stream().map(Gear.class::cast);
  }

  @Override
  public CharacterClass playerClass() {
    return chosenClass;
  }

  @Override
  public void accept(GameTraverser traverser) {
    traverser.traverse(this);
    gear.forEach(traverser::traverse);
    traverser.traverse(this);
  }

  public Stream<Perk> perks() {
    return perks.stream();
  }

  public void applyPerks(Perk... newPerks) {
    for (Perk perk : newPerks) {
      perks.add(perk);
      attributes = perk.applyTo(attributes);
    }
  }

  public void changeName(String newName) {
    if (newName != null && !newName.isBlank()) {
      this.name = newName;
    }
  }

  public void setClass(PlayerCharacterClass characterClass) {
    this.chosenClass = characterClass;
  }

  public void addItems(Item... items) {
    this.gear.addAll(Arrays.asList(items));
  }

}
