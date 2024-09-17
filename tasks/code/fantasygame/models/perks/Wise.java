package ask.urfu.misc.patterns.fantasygame.models.perks;

import ask.urfu.misc.patterns.fantasygame.models.FantasyCharacterModel;

public class Wise extends PerkEffect {

  protected Wise(FantasyCharacterModel base) {
    super("wise", base);
  }

  @Override
  public int getMagic() {
    return base.getMagic() + 1;
  }

}
