package ask.urfu.misc.patterns.fantasygame.rules.charactercreation;

public interface Collaborator {

  CreateCharacter manager();

  void setManager(CreateCharacter manager);

  void cancelAction(String reason);

  default void signalUpdate() {
    manager().wasUpdated(this);
  }

}
