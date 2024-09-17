package ask.urfu.misc.patterns.fantasygame.util;

import ask.urfu.misc.patterns.fantasygame.DungeonMap;
import ask.urfu.misc.patterns.fantasygame.FantasyGame;
import ask.urfu.misc.patterns.fantasygame.models.DungeonLocation;
import ask.urfu.misc.patterns.fantasygame.models.character.Adventurer;
import ask.urfu.misc.patterns.fantasygame.models.character.Monster;
import ask.urfu.misc.patterns.fantasygame.models.gear.Item;
import ask.urfu.misc.patterns.fantasygame.rules.magic.SpellEffect;

public class MagicTraverser implements GameTraverser {

  private final SpellEffect effect;
  DungeonLocation currentLocation;
  boolean locationSelected;

  public MagicTraverser(SpellEffect spellEffect) {
    effect = spellEffect;
  }

  @Override
  public void traverse(FantasyGame game) {
    // do nothing
  }

  @Override
  public void traverse(Adventurer adventurer) {
    // do nothing
  }

  @Override
  public void traverse(Item item) {
    // do nothing
  }

  @Override
  public void traverse(DungeonMap map) {
    // do nothing
  }

  @Override
  public void traverse(DungeonLocation location) {
    // do nothing
    if (location != currentLocation) {
      currentLocation = location;
      locationSelected = effect.fits(location);
    }
  }

  @Override
  public void traverse(Monster monster) {
    if (locationSelected && effect.fits(monster)) {
      apply(effect, currentLocation, monster);
    }
  }

  private void apply(SpellEffect effect, DungeonLocation currentLocation, Monster monster) {
    effect.apply(currentLocation, monster);
  }

}
