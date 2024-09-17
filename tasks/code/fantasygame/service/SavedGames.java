package ask.urfu.misc.patterns.fantasygame.service;

import ask.urfu.misc.patterns.fantasygame.FantasyGame;
import ask.urfu.misc.patterns.fantasygame.FantasyGame.GameSave;
import java.util.HashMap;
import java.util.Map;

public class SavedGames {

  private static final SavedGames SERVICE = new SavedGames();
  private static int count = 0;
  private final Map<Integer, GameSave> saved = new HashMap<>();

  public static SavedGames service() {
    return SERVICE;
  }

  public int saveGame() {
    GameSave save = FantasyGame.theGame().createSave();
    int saveId = count;
    count++;
    saved.put(saveId, save);
    return saveId;
  }

  public void loadGame(int saveId) {
    GameSave gameSave = saved.get(saveId);
    if (gameSave != null) {
      FantasyGame.theGame().loadSave(gameSave);
    }
  }

}
