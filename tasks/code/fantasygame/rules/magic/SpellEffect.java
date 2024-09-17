package ask.urfu.misc.patterns.fantasygame.rules.magic;

import ask.urfu.misc.patterns.fantasygame.DungeonMap;
import ask.urfu.misc.patterns.fantasygame.models.DungeonLocation;
import ask.urfu.misc.patterns.fantasygame.models.character.Monster;

public interface SpellEffect {

  boolean fits(DungeonLocation location);

  boolean fits(Monster monster);

  void apply(DungeonMap map);

  void apply(DungeonLocation location, Monster monster);

}
