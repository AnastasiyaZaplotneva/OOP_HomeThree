package org.example.sem3;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractGame implements Game {

    Integer sizeWord;
    Integer attempts;
    String word;
    GameStatus gameStatus = GameStatus.INIT;
    List<String> moveHistory;

    public List<String> getMoveHistory() {
        return moveHistory;
    }

    @Override
    public void start(Integer sizeWord, Integer attempts) {
        this.sizeWord = sizeWord;
        this.attempts = attempts;
        word = generateWorld();//слово загаданное пк
        this.gameStatus = GameStatus.START;
        this.moveHistory = new ArrayList<>();

    }
    public void play(String val){
        moveHistory.add(val);
        if(attempts>1) {
            Answer answerGame = inputValue(val);
            if(answerGame.getBull()==sizeWord) {
                System.out.println("Вы угадали СЛОВО!");
                gameStatus = GameStatus.WIN;
            }
            else {
                System.out.println("коров - " + answerGame.getCow() + " , быков - " + answerGame.getBull());
                attempts--;
                System.out.println("Осталось " + attempts + " попыток");
            }
        }
        else {
            System.out.println("Попытки исчерпаны, вы не смогли угадать слово " + word + " \nВозможно, вам повезет в следующей игре");
            gameStatus = GameStatus.LOSE;
        }

    }
    @Override
    public Answer inputValue(String value) {
        int bulls = 0;
        int cows = 0;
        for (int i = 0; i < value.length(); i++) {
            if (value.charAt(i) == word.charAt(i)) {
                bulls++;
            }
            Character character = value.charAt(i);
            if (word.contains(character.toString())) {
                cows++;
            }
        }
        return new Answer(value, cows, bulls);
    }

    @Override
    public GameStatus getGameStatus() {

        return gameStatus;
    }

    private String generateWorld() {
        Random random = new Random();
        List<String> charList = generateCharList();
        String resWorld = "";
        for (int i = 0; i < sizeWord; i++) {
            int randomIndex = random.nextInt(charList.size());
            resWorld = resWorld.concat(charList.get(randomIndex));
            charList.remove(randomIndex);
        }
        return resWorld;
    }

    abstract List<String> generateCharList();
}
