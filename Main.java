import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int step = 0;
        int sizeBoard = 5;

        Person person = new Person(1, sizeBoard);
        String goal = "\uD83E\uDD69"; // 🥩

        int castleY = 1;
        Random random = new Random();
        int castleX = 1 + random.nextInt(sizeBoard);

        String[][] board = new String[sizeBoard][sizeBoard];
        for (int y = 1; y <= sizeBoard; y++) {
            for (int x = 1; x <= sizeBoard; x++) {
                board[y - 1][x - 1] = "  ";
            }
        }

        int countMonster = sizeBoard * sizeBoard - sizeBoard - 5;
        Monster[] arrMonster = new Monster[countMonster + 1];
        int count = 0;
        Monster test;

        while (count <= countMonster) {
            if (random.nextBoolean()) {
                test = new Monster(sizeBoard);
            } else {
                test = new BigMonster(sizeBoard);
            }
            if (board[test.getY()][test.getX()].equals("  ")) {
                board[test.getY()][test.getX()] = test.getImage();
                arrMonster[count] = test;
                count++;
            }
        }

        board[castleY - 1][castleX - 1] = goal;

        System.out.println("Привет! Ты готов начать играть в игру? ");
        Scanner scanner = new Scanner(System.in);
        String answeGameStart = scanner.nextLine().toLowerCase().trim();

        switch (answeGameStart) {
            case "да", "д", "yes", "y", "конечно", "естественно", "ofcourse", "true", "1":
                System.out.println("Ваш ответ:\t" + answeGameStart);
                System.out.println("Выбери уровень сложности (от 1 до 5):");
                int hardGame = scanner.nextInt();

                while (true) {
                    board[person.getY() - 1][person.getX() - 1] = person.getImage();

                    outputBoard(board, person.getLive());

                    System.out.println("Введите, куда будет ходить персонаж (ход на 1 клетку по вертикали или горизонтали)");
                    System.out.println("Координаты персонажа - (x: " + person.getX() + ", y: " + person.getY() + ")");

                    int x = scanner.nextInt();
                    int y = scanner.nextInt();
                    System.out.println("Вы ввели координаты: " + x + ", " + y);

                    if (x < 1 || x > sizeBoard || y < 1 || y > sizeBoard) {
                        System.out.println("Выход за границы поля! Попробуйте снова.");
                        continue;
                    }

                    if (x != person.getX() && y != person.getY()) {
                        System.out.println("Некорректный ход! Ходить наискосок нельзя.");
                    } else if (person.isMoveCorrect(x, y)) {
                        String nextCell = board[y - 1][x - 1];

                        if (nextCell.equals("  ")) {
                            board[person.getY() - 1][person.getX() - 1] = "  ";
                            person.move(x, y);
                            step += 1;
                            System.out.println("Ход корректный; Новые координаты: " + person.getX() + ", " + person.getY() + "\nХод номер: " + step);

                        } else if (nextCell.equals(goal)) {
                            System.out.println("Вы дошли до цели " + goal + "! Вы прошли игру!");
                            break;

                        } else {
                            for (Monster m : arrMonster) {
                                if (m != null && m.conflictPerson(x, y)) {
                                    if (m.taskMonster(hardGame, person.getLive())) {
                                        board[person.getY() - 1][person.getX() - 1] = "  ";
                                        person.move(x, y);
                                        step++;
                                    } else {
                                        person.downLive();
                                        // Монстр тоже погиб в бою — очищаем его клетку
                                        board[y - 1][x - 1] = "  ";
                                    }
                                    break;
                                }
                            }
                        }
                    } else {
                        System.out.println("Координаты не изменены; координаты: " + person.getX() + ", " + person.getY());
                    }

                    if (person.getLive() <= 0) {
                        break;
                    }
                }

                if (person.getLive() <= 0) {
                    System.out.println("Закончились жизни. Итог: Игра окончена! 💀");
                }
                break;

            case "нет", "н", "no", "n", "нетконечно", "естественнонет", "ofcourseno", "false", "0":
                System.out.println("Ваш ответ:\t" + answeGameStart);
                System.out.println("Хорошо " + person.getImage() + " Тогда в другой раз)))");
                break;

            default:
                System.out.println("Ты ввел некорректный ответ, введи ДА или НЕТ)");
        }
    }

    static void outputBoard(String[][] board, int live) {
        String leftBlock = " | ";
        String rightBlock = " |";
        String wall = " + —— + —— + —— + —— + —— + ";

        for (String[] row : board) {
            System.out.println(wall);
            for (String cell : row) {
                System.out.print(leftBlock);
                System.out.print(cell);
            }
            System.out.println(rightBlock);
        }
        System.out.println(wall);
        System.out.println("Количество твоих жизней: " + live + "\n");
    }
}