package ask.urfu.misc.patterns.fantasygame.service.generators;

import ask.urfu.misc.patterns.fantasygame.models.character.Monster;
import ask.urfu.misc.patterns.fantasygame.service.generators.MonsterGenerator.Provider;
import ask.urfu.misc.patterns.library.service.LoadFromFile;
import java.nio.file.Path;

public class FileMonsterProvider implements Provider {

  private final String filename;

  private final LoadFromFile loadFromFile;

  public FileMonsterProvider(String filename, LoadFromFile loadFromFile) {
    this.filename = filename;
    this.loadFromFile = loadFromFile;
  }

  public FileMonsterProvider(Path filename, LoadFromFile loadFromFile) {
    this.filename = filename.toString();
    this.loadFromFile = loadFromFile;
  }

  @Override
  public Monster getMonster() {
    return (Monster) loadFromFile.loadEnemy(filename);
  }

}
