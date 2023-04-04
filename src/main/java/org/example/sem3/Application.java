package org.example.sem3;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        UserMenu.printMenu();
        Scanner scanner = new Scanner(System.in);
        System.out.println("выберите игру: ");
        int num = scanner.nextInt();
        Game game = null;
        switch (num) {
            case 1:
                game = new NumberGame();
                break;
            case 2:
                game = new EnGame();
                break;
            case 3:
                game = new RuGame();
                break;
            default:
                System.out.println("данная игра еще не добавлена!");
        }
        System.out.println("Введите количество букв в слове: ");
        int letters = scanner.nextInt();
        System.out.println("Укажите максимальное количество попыток: ");
        int maxTry = scanner.nextInt();
        scanner.nextLine();
        game.start(letters,maxTry);

        while (game.getGameStatus().equals(GameStatus.START)){

            System.out.println("Ваш ход: ");
            String answer = scanner.nextLine();
            game.play(answer);

        }
        System.out.println("Вы хотите увидеть историю ваших ходов? Введите Y если да, и N, если нет");
        String choice = scanner.nextLine();
        if(choice.equalsIgnoreCase("Y")){
            System.out.println(game.getMoveHistory());
        }
    }

}

