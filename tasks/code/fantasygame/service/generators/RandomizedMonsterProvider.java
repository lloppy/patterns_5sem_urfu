package ask.urfu.misc.patterns.fantasygame.service.generators;

import ask.urfu.misc.patterns.fantasygame.models.FantasyCharacterModel;
import ask.urfu.misc.patterns.fantasygame.models.character.Monster;
import ask.urfu.misc.patterns.fantasygame.service.generators.MonsterGenerator.Provider;
import java.util.Collections;
import java.util.random.RandomGenerator;

public class RandomizedMonsterProvider implements Provider {

  private final int level;
  private final RandomGenerator random;

  public RandomizedMonsterProvider(int level, RandomGenerator random) {
    this.level = level;
    this.random = random;
  }

  @Override
  public Monster getMonster() {
    FantasyCharacterModel model = new FantasyCharacterModel(
        level,
        random.nextInt(1, level + 1),
        random.nextInt(1, level + 1),
        random.nextInt(1, level + 1)
    );
    return new Monster("unknown monster", model, Collections.emptyList());
  }

}
