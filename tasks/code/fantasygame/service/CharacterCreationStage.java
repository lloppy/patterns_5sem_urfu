package ask.urfu.misc.patterns.fantasygame.service;

import ask.urfu.misc.patterns.fantasygame.models.character.Adventurer;
import ask.urfu.misc.patterns.fantasygame.rules.charactercreation.CreateCharacter.Context;
import java.util.List;

interface CharacterCreationStage {

  void apply(Adventurer adventurer);

  CharacterCreationStage next();

  abstract class CharacterCreationStageBase implements CharacterCreationStage {

    protected final Context context;

    private final List<CharacterCreationStage> stages;

    protected CharacterCreationStageBase(Context context, List<CharacterCreationStage> stages) {
      this.context = context;
      this.stages = stages;
    }

    @Override
    public CharacterCreationStage next() {
      int index = stages.indexOf(this);
      return (index + 1) < stages.size()
          ? stages.get(index + 1)
          : null;
    }

  }

}
