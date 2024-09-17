package ask.urfu.misc.patterns.fantasygame.rules.magic;

import ask.urfu.misc.patterns.fantasygame.models.DungeonLocation;
import ask.urfu.misc.patterns.fantasygame.models.character.Monster;
import ask.urfu.misc.patterns.library.game.Location;
import java.util.function.Predicate;

public class SpellEffectAccumulator {

  private Predicate<DungeonLocation> locationFilter = (x -> true);
  private Predicate<Monster> monsterFilter = (x -> true);
  private EffectSupplier effectSupplier;
  private int turnsLeft = 0;


  public SpellEffectAccumulator locations(Location location) {
    if (location != null) {
      locationFilter = locationFilter.and(l -> l == location);
    }
    return this;
  }

  public SpellEffectAccumulator randomMonster() {
    monsterFilter = monsterFilter.and(m -> true);
    return this;
  }

  public SpellEffectAccumulator onMonsterType(String name) {
    if (name != null) {
      monsterFilter = monsterFilter.and(m -> name.equalsIgnoreCase(m.getName()));
    }
    return this;
  }


  public SpellEffectAccumulator lasts(int turns) {
    turnsLeft = turns;
    return this;
  }

  public SpellEffectAccumulator instant() {
    turnsLeft = -1;
    return this;
  }

  public SpellEffectAccumulator lastsForever() {
    turnsLeft = -2;
    return this;
  }

  public SpellEffectAccumulator type(EffectSupplier effectSupplier) {
    this.effectSupplier = effectSupplier;
    return this;
  }

  public SpellEffect build() {
    return effectSupplier.apply(locationFilter, monsterFilter, turnsLeft);
  }

  public interface EffectSupplier {

    SpellEffect apply(
        Predicate<DungeonLocation> locationFilter,
        Predicate<Monster> monsterFilter,
        int turnsLeft);
  }

}
