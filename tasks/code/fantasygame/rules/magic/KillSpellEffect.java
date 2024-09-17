package ask.urfu.misc.patterns.fantasygame.rules.magic;

import ask.urfu.misc.patterns.fantasygame.models.DungeonLocation;
import ask.urfu.misc.patterns.fantasygame.models.character.Monster;
import java.util.function.Predicate;

public class KillSpellEffect extends SpellEffectBase {

  public KillSpellEffect(
      Predicate<DungeonLocation> locationFilter,
      Predicate<Monster> monsterFilter,
      int turnsLeft) {
    super(locationFilter, monsterFilter, turnsLeft);
  }

  @Override
  public void apply(DungeonLocation location, Monster monster) {
    location.removeEnemy(monster);
  }

}
