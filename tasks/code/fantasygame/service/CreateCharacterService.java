package ask.urfu.misc.patterns.fantasygame.service;

import ask.urfu.misc.patterns.fantasygame.models.FantasyCharacterModel;
import ask.urfu.misc.patterns.fantasygame.models.PlayerCharacterClass;
import ask.urfu.misc.patterns.fantasygame.models.character.Adventurer;
import ask.urfu.misc.patterns.fantasygame.models.gear.Item;
import ask.urfu.misc.patterns.fantasygame.models.perks.Perk;
import ask.urfu.misc.patterns.fantasygame.rules.charactercreation.CreateCharacter;
import ask.urfu.misc.patterns.fantasygame.rules.charactercreation.CreateCharacter.Context;
import ask.urfu.misc.patterns.fantasygame.rules.charactercreation.Race;
import java.util.ArrayList;
import java.util.List;

public class CreateCharacterService {

  public Adventurer create(CreateCharacter.Context context) {
    Adventurer adventurer = new Adventurer(
        "Adventurer",
        FantasyCharacterModel.peaceful(),
        PlayerCharacterClass.WARRIOR);
    CharacterCreationStage currentStage = planCreationStages(context);
    while (currentStage != null) {
      currentStage.apply(adventurer);
      currentStage = currentStage.next();
    }
    return adventurer;
  }

  private CharacterCreationStage planCreationStages(Context context) {
    List<CharacterCreationStage> stages = new ArrayList<>();
    stages.add(new PutClass(context, stages));
    stages.add(new ApplyRace(context, stages));
    stages.add(new ApplyPerk(context, stages));
    stages.add(new PutInventory(context, stages));
    return stages.get(0);
  }

  private static class PutClass extends CharacterCreationStage.CharacterCreationStageBase {


    protected PutClass(Context context, List<CharacterCreationStage> stages) {
      super(context, stages);
    }

    @Override
    public void apply(Adventurer adventurer) {
      PlayerCharacterClass characterClass = context.getCharacterClass();
      if (characterClass != null) {
        adventurer.setClass(characterClass);
        switch (characterClass) {
          case WARRIOR -> adventurer.setModel(FantasyCharacterModel.initialWarrior());
          case MAGE -> adventurer.setModel(FantasyCharacterModel.initialMage());
          case ROGUE -> adventurer.setModel(FantasyCharacterModel.initialRogue());
          default -> { //do nothing
          }
        }
      }
    }
  }


  private static class ApplyRace extends CharacterCreationStage.CharacterCreationStageBase {


    protected ApplyRace(Context context, List<CharacterCreationStage> stages) {
      super(context, stages);
    }

    @Override
    public void apply(Adventurer adventurer) {
      Race race = context.getRace();
      switch (race) {
        case ELF -> adventurer.applyPerks(Perk.AGILE, Perk.WISE);
        case DWARF -> adventurer.applyPerks(Perk.STRONG);
        default -> {  // do nothing
        }
      }
    }
  }

  private static class ApplyPerk extends CharacterCreationStage.CharacterCreationStageBase {


    protected ApplyPerk(Context context, List<CharacterCreationStage> stages) {
      super(context, stages);
    }

    @Override
    public void apply(Adventurer adventurer) {
      Perk perk = context.getPerk();
      if (perk != null) {
        adventurer.applyPerks();
      }
    }
  }

  private static class PutInventory extends CharacterCreationStage.CharacterCreationStageBase {


    protected PutInventory(Context context, List<CharacterCreationStage> stages) {
      super(context, stages);
    }

    @Override
    public void apply(Adventurer adventurer) {
      Item classItem = context.getClassItem();
      Item selectableItem = context.getSelectableItem();
      adventurer.addItems(classItem, selectableItem);
    }
  }

}


