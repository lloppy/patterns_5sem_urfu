package ask.urfu.misc.patterns.fantasygame.models.gear;

import ask.urfu.misc.patterns.fantasygame.models.FantasyCharacterModel;
import ask.urfu.misc.patterns.library.character.CharacterModel;
import ask.urfu.misc.patterns.library.gear.GearEffect;

// Переписанный класс Singleton
// Паттерн Singleton
public class GearEffects {
  private static final GearEffects INSTANCE = new GearEffects();

  private GearEffects() {
  }

  public static GearEffects getInstance() {
    return INSTANCE;
  }

  public static GearEffect sharp() {
    return model -> ((FantasyCharacterModel) model).increaseStrength(1);
  }

  public static GearEffect vorpal() {
    return model -> ((FantasyCharacterModel) model).increaseStrength(3);
  }

  public static GearEffect magical() {
    return model -> ((FantasyCharacterModel) model).increaseMagic(1);
  }

  public static GearEffect powerful() {
    return model -> ((FantasyCharacterModel) model).increaseMagic(3);
  }

  public static GearEffect sturdy() {
    return model -> ((FantasyCharacterModel) model).increaseProtection(1);
  }

  public static GearEffect perfect() {
    return model -> ((FantasyCharacterModel) model).increaseProtection(3);
  }

  public static GearEffect compose(GearEffect main, GearEffect additional) {
    if (main == null) {
      return additional;
    } else if (additional == null) {
      return main;
    } else {
      return model -> {
        CharacterModel step1 = main.applyTo(model);
        CharacterModel step2 = additional.applyTo(step1);
        return step2;
      };
    }
  }
}
