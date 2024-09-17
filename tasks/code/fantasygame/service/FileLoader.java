package ask.urfu.misc.patterns.fantasygame.service;

import ask.urfu.misc.patterns.fantasygame.models.FantasyCharacterModel;
import ask.urfu.misc.patterns.fantasygame.models.character.Monster;
import ask.urfu.misc.patterns.fantasygame.models.character.Peaceful;
import ask.urfu.misc.patterns.fantasygame.models.gear.GearType;
import ask.urfu.misc.patterns.fantasygame.models.gear.Item;
import ask.urfu.misc.patterns.fantasygame.util.FileScanner;
import ask.urfu.misc.patterns.library.character.Enemy;
import ask.urfu.misc.patterns.library.character.NonPlayerCharacter;
import ask.urfu.misc.patterns.library.character.PlayerCharacter;
import ask.urfu.misc.patterns.library.game.Map;
import ask.urfu.misc.patterns.library.service.LoadFromFile;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class FileLoader implements LoadFromFile {

  private final FileScanner scanner;

  public FileLoader(Path basePath) {
    this.scanner = new FileScanner(basePath);
  }

  @Override
  public PlayerCharacter loadPlayer(String filename) {
    return null; // TODO: 02.08.2024 implement
  }

  @Override
  public PlayerCharacter loadPlayer(Path filename) {
    return loadPlayer(filename.toString());
  }

  @Override
  public NonPlayerCharacter loadNpc(String filename) {
    var attributes = scanner.scanFile(filename);
    String name = attributes.get("NPC");
    String appearance = attributes.get("DESCRIPTION");
    List<String> text = attributes.entrySet().stream()
        .map(entry -> {
          try {
            Integer.parseInt(entry.getKey());
            return entry.getValue();
          } catch (Exception e) {
            return null;
          }
        })
        .filter(Objects::nonNull)
        .toList();
    return Peaceful.having()
        .name(name)
        .appearance(appearance)
        .questText(text.toArray(String[]::new))
        .done();
  }

  @Override
  public NonPlayerCharacter loadNpc(Path filename) {
    return loadNpc(filename.toString());
  }

  @Override
  public Enemy loadEnemy(String filename) {
    var attributes = scanner.scanFile(filename);
    if (attributes.containsKey("MONSTER")) {
      String name = attributes.get("MONSTER");
      int level = Integer.parseInt(attributes.get("LEVEL"));
      int strength = Integer.parseInt(attributes.get("STRENGTH"));
      int agility = Integer.parseInt(attributes.get("AGILITY"));
      int magic = Integer.parseInt(attributes.get("MAGIC"));
      var items = Arrays.stream(attributes.get("ITEMS").split(","))
          .map(String::trim)
          .map(GearType::valueOf)
          .map(Item::basic)
          .toList();
      return new Monster(name, new FantasyCharacterModel(level, strength, agility, magic), items);
    } else {
      return null;
    }
  }

  @Override
  public Enemy loadEnemy(Path filename) {
    return loadEnemy(filename.toString());
  }

  @Override
  public Map loadMap(String filename) {
    return null; // TODO: 02.08.2024 implement
  }

  @Override
  public Map loadMap(Path filename) {
    return loadMap(filename.toString());
  }

}
