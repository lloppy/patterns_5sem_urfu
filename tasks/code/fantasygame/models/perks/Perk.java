package ask.urfu.misc.patterns.fantasygame.models.perks;

import ask.urfu.misc.patterns.fantasygame.models.FantasyCharacterModel;
import java.util.function.UnaryOperator;

// Паттерн Singleton
public enum Perk {

  STRONG(Strong::new),
  AGILE(Agile::new),
  WISE(Wise::new),
  PROMISING(Promising::new);

  private final UnaryOperator<FantasyCharacterModel> perkEffect;

  Perk(UnaryOperator<FantasyCharacterModel> perkEffect) {
    this.perkEffect = perkEffect;
  }

  public FantasyCharacterModel applyTo(FantasyCharacterModel model) {
    return this.perkEffect.apply(model);
  }

}
