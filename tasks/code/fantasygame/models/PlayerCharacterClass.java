package ask.urfu.misc.patterns.fantasygame.models;

import ask.urfu.misc.patterns.library.character.CharacterClass;
import ask.urfu.misc.patterns.library.character.GameCharacter;

// Паттерн Singleton
public enum PlayerCharacterClass implements CharacterClass {

  WARRIOR("brave warrior", FantasyCharacterModel.initialWarrior()),

  MAGE("mighty wizard", FantasyCharacterModel.initialMage()),

  ROGUE("cunning rogue", FantasyCharacterModel.initialRogue());

  private final String description;
  private final FantasyCharacterModel initialModel;

  PlayerCharacterClass(String description, FantasyCharacterModel initialModel) {
    this.description = description;
    this.initialModel = initialModel;
  }

  @Override
  public String description() {
    return description;
  }

  @Override
  public FantasyCharacterModel initialModel() {
    return initialModel;
  }

  @Override
  public void levelUp(GameCharacter c) {
    // TODO: 06.09.2024 implement
  }
}
