package ask.urfu.misc.patterns.fantasygame.models.perks;

import ask.urfu.misc.patterns.fantasygame.models.FantasyCharacterModel;

public class Strong extends PerkEffect {

  public Strong(FantasyCharacterModel base) {
    super("strong", base);
  }

  @Override
  public int getStrength() {
    return base.getStrength() + 1;
  }

}
