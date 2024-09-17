package ask.urfu.misc.patterns.fantasygame.util;

import ask.urfu.misc.patterns.fantasygame.DungeonMap;
import ask.urfu.misc.patterns.fantasygame.FantasyGame;
import ask.urfu.misc.patterns.fantasygame.models.DungeonLocation;
import ask.urfu.misc.patterns.fantasygame.models.character.Adventurer;
import ask.urfu.misc.patterns.fantasygame.models.character.Monster;
import ask.urfu.misc.patterns.fantasygame.models.gear.Item;

public interface GameTraverser {

  void traverse(FantasyGame game);

  void traverse(Adventurer adventurer);

  void traverse(Item item);

  void traverse(DungeonMap map);

  void traverse(DungeonLocation location);

  void traverse(Monster monster);

}
