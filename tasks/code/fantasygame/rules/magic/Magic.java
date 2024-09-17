package ask.urfu.misc.patterns.fantasygame.rules.magic;

import ask.urfu.misc.patterns.fantasygame.models.character.Adventurer;
import ask.urfu.misc.patterns.fantasygame.util.LazyRandom;

public class Magic {

  private final LazyRandom random;
  SpellParser parser = new SpellParser();

  public Magic(LazyRandom random) {
    this.random = random;
  }

  public Spell getSpell(String incantation) {
    return parser.parseSpell(incantation);
  }

  public SpellEffect castSpell(Adventurer caster, Spell spell) {
    int spellPower = spell.power();
    int casterPower = caster.getModel().getMagic();
    // one can cast higher power spells with chances
    boolean spellSuccess = spellPower <= casterPower
        || random.chance((spellPower - casterPower) * 10);
    return spellSuccess
        ? spell.effect(new SpellEffectAccumulator()).build()
        : null;
  }

}
