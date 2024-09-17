package ask.urfu.misc.patterns.fantasygame.models.perks;

import ask.urfu.misc.patterns.fantasygame.models.FantasyCharacterModel;

public abstract class PerkEffect extends FantasyCharacterModel {

  private final String name;
  protected FantasyCharacterModel base;


  protected PerkEffect(String name, FantasyCharacterModel base) {
    this.name = name;
    this.base = base;
  }


  public String getName() {
    return name;
  }

  @Override
  public int getLevel() {
    return base.getLevel();
  }

  @Override
  public int getStrength() {
    return base.getStrength();
  }

  @Override
  public int getAgility() {
    return base.getAgility();
  }

  @Override
  public int getMagic() {
    return base.getMagic();
  }

}
