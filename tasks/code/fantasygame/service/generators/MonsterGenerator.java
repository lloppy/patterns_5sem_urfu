package ask.urfu.misc.patterns.fantasygame.service.generators;

import ask.urfu.misc.patterns.fantasygame.models.character.Monster;
import ask.urfu.misc.patterns.library.character.Enemy;
import ask.urfu.misc.patterns.library.game.EnemyGenerator;

public class MonsterGenerator extends EnemyGenerator {

  private final Monster exemplary;

  public MonsterGenerator(Provider provider) {
    this.exemplary = provider.getMonster();
  }

  @Override
  protected Enemy newEnemy() {
    return exemplary.replicate();
  }

  public interface Provider {

    Monster getMonster();

  }

}
