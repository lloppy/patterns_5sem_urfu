package ask.urfu.misc.patterns.fantasygame.models.perks;

import ask.urfu.misc.patterns.fantasygame.models.FantasyCharacterModel;

public class Agile extends PerkEffect {

  protected Agile(FantasyCharacterModel base) {
    super("cunning", base);
  }

  @Override
  public int getAgility() {
    return base.getAgility() + 1;
  }

}
