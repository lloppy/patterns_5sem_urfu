package ask.urfu.misc.patterns.fantasygame.models.perks;

import ask.urfu.misc.patterns.fantasygame.models.FantasyCharacterModel;

public class Promising extends PerkEffect {

  protected Promising(FantasyCharacterModel base) {
    super("promising", base);
  }

  @Override
  public int getLevel() {
    return base.getLevel() + 1;
  }

}
