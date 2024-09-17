package ask.urfu.misc.patterns.fantasygame.service;

import ask.urfu.misc.patterns.fantasygame.models.DungeonLocation;
import ask.urfu.misc.patterns.fantasygame.models.PlayerCharacterClass;
import ask.urfu.misc.patterns.fantasygame.models.character.Adventurer;
import ask.urfu.misc.patterns.fantasygame.models.gear.GearSpecials;
import ask.urfu.misc.patterns.fantasygame.models.gear.GearType;
import ask.urfu.misc.patterns.fantasygame.models.gear.Item;
import ask.urfu.misc.patterns.fantasygame.util.LazyRandom;
import ask.urfu.misc.patterns.library.character.CharacterClass;
import ask.urfu.misc.patterns.library.character.Enemy;
import ask.urfu.misc.patterns.library.character.PlayerCharacter;
import ask.urfu.misc.patterns.library.game.EnemyGenerator;
import ask.urfu.misc.patterns.library.game.Location;
import ask.urfu.misc.patterns.library.game.Maps;
import ask.urfu.misc.patterns.library.gear.Gear;
import java.util.Arrays;

public class DungeonMaps implements Maps {

  private final LazyRandom random;

  private final EnemyGenerator[] enemyGenerators;
  private int index;

  public DungeonMaps(LazyRandom random, EnemyGenerator... enemyGenerators) {
    this.random = random;
    this.enemyGenerators = enemyGenerators;
  }

  @Override
  public PlayerCharacter newRandomPlayer(String name) {
    PlayerCharacterClass randomClass = random.nextEnum(PlayerCharacterClass::values);
    return newPlayer(name, randomClass, newPlayerGear(randomClass));
  }

  @Override
  public PlayerCharacter newPlayer(String name, CharacterClass chosenClass, Gear... gear) {
    PlayerCharacterClass playerClass = (PlayerCharacterClass) chosenClass;
    return new Adventurer(
        name,
        playerClass.initialModel(),
        playerClass,
        Arrays.stream(gear).map(Item.class::cast).toList()
    );
  }

  @Override
  public Gear[] newPlayerGear(CharacterClass chosenClass) {
    return switch ((PlayerCharacterClass) chosenClass) {
      case WARRIOR -> new Gear[]{
          Item.basic(GearType.SWORD),
          Item.basic(GearType.SHIELD)};
      case MAGE -> new Gear[]{
          Item.basic(GearType.STAFF),
          Item.basic(GearType.BOOTS)};
      case ROGUE -> new Gear[]{
          Item.basic(GearType.BOW),
          Item.basic(GearType.BOOTS)};
    };
  }

  @Override
  public Location newLocation() {
    DungeonLocation dungeonLocation = new DungeonLocation("Room " + index++);
    int enemies = random.nextInt(0, 3);
    // 0 - 3 random enemies
    if (enemies > 0) {
      dungeonLocation.attach(randomMonsters(enemies));
    }
    // 0 - 1 random item
    int items = random.nextInt(0, 1);
    for (int i = 0; i < items; i++) {
      dungeonLocation.attach(randomItem());
    }
    return dungeonLocation;
  }

  private Enemy[] randomMonsters(int n) {
    EnemyGenerator generator = random.nextOf(enemyGenerators);
    return generator == null
        ? new Enemy[0]
        : generator.createEnemies(n).toArray(new Enemy[0]);
  }

  private Gear randomItem() {
    GearType gearType = randomGearType();
    // 30% chance for special
    if (random.chance(30)) {
      GearSpecials special = randomGearSpecial();
      String name = special.getDescription() + " " + gearType.getName();
      return Item.special(
          name,
          gearType,
          special.getAdditionalEffect()
      );
    } else {
      return Item.basic(gearType);
    }
  }

  private GearType randomGearType() {
    return random.nextEnum(GearType::values);
  }

  private GearSpecials randomGearSpecial() {
    return random.nextEnum(GearSpecials::values);
  }

}
