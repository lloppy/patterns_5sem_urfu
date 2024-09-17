package ask.urfu.misc.patterns.fantasygame;

import ask.urfu.misc.patterns.fantasygame.models.DungeonLocation;
import ask.urfu.misc.patterns.fantasygame.models.FantasyCharacterModel;
import ask.urfu.misc.patterns.fantasygame.models.PlayerCharacterClass;
import ask.urfu.misc.patterns.fantasygame.models.character.Adventurer;
import ask.urfu.misc.patterns.fantasygame.models.gear.GearType;
import ask.urfu.misc.patterns.fantasygame.models.gear.Item;
import ask.urfu.misc.patterns.fantasygame.models.perks.Perk;
import ask.urfu.misc.patterns.fantasygame.service.DungeonMaps;
import ask.urfu.misc.patterns.fantasygame.service.FileLoader;
import ask.urfu.misc.patterns.fantasygame.service.generators.FileMonsterProvider;
import ask.urfu.misc.patterns.fantasygame.service.generators.MonsterGenerator;
import ask.urfu.misc.patterns.fantasygame.service.generators.RandomizedMonsterProvider;
import ask.urfu.misc.patterns.fantasygame.util.LazyRandom;
import ask.urfu.misc.patterns.library.game.EnemyGenerator;
import ask.urfu.misc.patterns.library.game.Location;
import ask.urfu.misc.patterns.library.game.Maps;
import ask.urfu.misc.patterns.library.service.LoadFromFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Random;
import java.util.random.RandomGenerator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GameConfig {

  private final Path basePath = Paths.get(System.getProperty("user.dir"));
  private final LazyRandom random;
  private final LoadFromFile loadFromFile;
  private final List<EnemyGenerator> generators = new ArrayList<>();
  private Maps maps;

  public GameConfig() {
    this.random = new LazyRandom(() ->
        new Random(LocalDateTime.now().get(ChronoField.MILLI_OF_DAY))
    );
    this.loadFromFile = new FileLoader(basePath);
  }

  GameConfig enemyFiles(String pattern) {
    try (var files = Files.list(basePath.resolve(pattern))) {
      files
          .filter(file -> file.toString().endsWith(".monster"))
          .forEach(file -> generators.add(
              new MonsterGenerator(new FileMonsterProvider(file, loadFromFile))
          ));
    } catch (IOException e) {
      throw new IllegalArgumentException(e);
    }
    return this;
  }

  public RandomGenerator getRandom() {
    return random;
  }

  public DungeonMap randomMap() {
    // generate locations
    List<Location> locations = Stream
        .generate(maps::newLocation)
        .limit(random.nextInt(5, 15))
        .collect(Collectors.toList());
    // generate connections
    boolean done = false;
    Location initial = locations.remove(0);
    Deque<Location> queue = new ArrayDeque<>();
    Location current = initial;
    while (!done) {
      int connections = random.nextInt(1, 4);
      List<Location> adjacent = locations.stream().limit(connections).toList();
      locations.removeAll(adjacent);
      Location finalCurrent = current;
      adjacent.forEach(location -> {
        location.attach(finalCurrent);
        finalCurrent.attach(location);
        queue.push(location);
      });
      done = locations.isEmpty();
      current = queue.removeFirst();
    }
    return new DungeonMap((DungeonLocation) initial);
  }

  public Adventurer randomAdventurer() {
    int random1to3 = random.nextInt(1, 4);
    var adventurer = switch (random1to3) {
      case 1 -> new Adventurer("Al the Handsome",
          FantasyCharacterModel.initialWarrior(), PlayerCharacterClass.WARRIOR,
          Item.basic(GearType.SWORD), Item.basic(GearType.SHIELD)
      );
      case 2 -> new Adventurer("Hyppolith the Useless",
          FantasyCharacterModel.initialMage(), PlayerCharacterClass.MAGE,
          Item.basic(GearType.STAFF)
      );
      case 3 -> new Adventurer("Lorg the Ears",
          FantasyCharacterModel.initialRogue(), PlayerCharacterClass.ROGUE,
          Item.basic(GearType.BOW), Item.basic(GearType.BOOTS)
      );
      default -> throw new IllegalStateException("Unexpected value: " + random1to3);
    };
    // 0-1 random perks applied
    Stream.generate(this::randomPerk)
        .limit(random.nextInt(2))
        .forEach(adventurer::applyPerks);
    return adventurer;
  }

  public Perk randomPerk() {
    return random.nextEnum(Perk::values);
  }

  public GameConfig randomMonsters(int level) {
    generators.add(
        new MonsterGenerator(new RandomizedMonsterProvider(level, random)));
    return this;
  }

  public GameConfig done() {
    this.maps = new DungeonMaps(random, generators.toArray(EnemyGenerator[]::new));
    return this;
  }


}
