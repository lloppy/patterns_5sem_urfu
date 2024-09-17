package ask.urfu.misc.patterns.fantasygame.rules.magic;

import ask.urfu.misc.patterns.fantasygame.DungeonMap;
import ask.urfu.misc.patterns.fantasygame.models.DungeonLocation;
import ask.urfu.misc.patterns.fantasygame.models.character.Monster;
import ask.urfu.misc.patterns.fantasygame.util.MagicTraverser;
import java.util.function.Predicate;

public abstract class SpellEffectBase implements SpellEffect {

  private final Predicate<DungeonLocation> locationFilter;
  private final Predicate<Monster> monsterFilter;
  private int turnsLeft;


  protected SpellEffectBase(
      Predicate<DungeonLocation> locationFilter,
      Predicate<Monster> monsterFilter,
      int turnsLeft
  ) {
    this.locationFilter = locationFilter;
    this.monsterFilter = monsterFilter;
    this.turnsLeft = turnsLeft;
  }


  @Override
  public final void apply(DungeonMap map) {
    map.accept(new MagicTraverser(this));
  }

  @Override
  public boolean fits(DungeonLocation location) {
    return locationFilter.test(location);
  }

  @Override
  public boolean fits(Monster monster) {
    return monsterFilter.test(monster);
  }

}
