package org.example.sem3;

import java.util.List;

public interface Game {
    void start(Integer sizeWord, Integer attempts);
    void play(String val);

    Answer inputValue(String value);

    GameStatus getGameStatus();
    List<String> getMoveHistory();
}
