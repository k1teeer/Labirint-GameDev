import java.util.Scanner;
import java.util.Random;
Person person = new Person();

public class Main {
    public static void main(String[] args) {
        int step = 0;
        int personX, personY;
        int personLive = 5;
        int sizeBoard = 5;


        String person = "\uD83E\uDD8A";   //  🦊
        String monster = "\uD83D\uDC7E";  //  👾
        String goal = "\uD83E\uDD69";     //  🥩

        String leftBlock = " | ";
        String rightBlock = " |";
        String wall = " + —— + —— + —— + —— + —— + ";


        personX = 1;
        personY = sizeBoard;

        int castleY = 1;
        Random random = new Random();
        int castleX = 1 + random.nextInt(sizeBoard);


        String[][] board = new String[sizeBoard][sizeBoard];
        for (int y = 1; y <= sizeBoard; y++) {
            for (int x = 1; x <= sizeBoard; x++) {
                board[y - 1][x - 1] = "  ";
            }
        }


        int countMonster = sizeBoard * sizeBoard - sizeBoard - 1;
        for (int i = 0; i < countMonster; i++) {
            // random.nextInt(sizeBoard - 1) + 1 гарантирует, что монстры не попадут на y = 1 (первую строку)
            board[random.nextInt(sizeBoard - 1) + 1][random.nextInt(sizeBoard)] = monster;
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

                    board[personY - 1][personX - 1] = person;

                    outputBoard(board, personLive);

                    System.out.println("Введите, куда будет ходить персонаж (ход на 1 клетку по вертикали или горизонтали)");
                    System.out.println("Координаты персонажа - (x: " + personX + ", y: " + personY + ")");

                    int x = scanner.nextInt();
                    int y = scanner.nextInt();
                    System.out.println("Вы ввели координаты: " + x + ", " + y);


                    if (x < 1 || x > sizeBoard || y < 1 || y > sizeBoard) {
                        System.out.println("Выход за границы поля! Попробуйте снова.");
                        continue;
                    }


                    if (x != personX && y != personY) {
                        System.out.println("Некорректный ход! Ходить наискосок нельзя.");
                    } else if (Math.abs(x - personX) == 1 || Math.abs(y - personY) == 1) {


                        if (board[y - 1][x - 1].equals("  ")) {
                            board[personY - 1][personX - 1] = "  ";
                            personX = x;
                            personY = y;
                            step += 1;
                            System.out.println("Ход корректный; Новые координаты: " + personX + ", " + personY + "\nХод номер: " + step);

                        } else if (board[y - 1][x - 1].equals(goal)) {
                            System.out.println("Вы дошли до цели " + goal + "! Вы прошли игру!");
                            break;

                        } else if (board[y - 1][x - 1].equals(monster)) {
                            if (taskMonster(hardGame, personLive, monster, scanner, random)) {
                                board[personY - 1][personX - 1] = "  ";
                                personX = x;
                                personY = y;
                                step++;
                            } else {
                                personLive--;


                            }
                        }
                    } else {
                        System.out.println("Координаты не изменены; координаты: " + personX + ", " + personY);
                    }


                    if (personLive <= 0) {
                        break;
                    }
                }


                if (personLive <= 0) {
                    System.out.println("Закончились жизни. Итог: Игра окончена! 💀");
                }
                break;

            case "нет", "н", "no", "n", "нетконечно", "естественнонет", "ofcourseno", "false", "0":
                System.out.println("Ваш ответ:\t" + answeGameStart);
                System.out.println("Хорошо " + person + " Тогда в другой раз)))");
                break;

            default:
                System.out.println("Ты ввел некорректный ответ, введи ДА или НЕТ)");
        }
    }

    static boolean taskMonster(int hardGame, int personLive, String monster, Scanner scanner, Random random) {
        if (hardGame == 1) {
            int a = random.nextInt(1000);
            int b = random.nextInt(1000);
            int trueAnswer = a + b;
            System.out.println("На этой клетке сидит монстр " + monster + "! Решите задачу." + " твое кол во жизней " + personLive);
            System.out.println("Решай пример " + a + " + " + b + " = ? ");
            int answer = scanner.nextInt();
            if (answer == trueAnswer) {
                System.out.println("Хорош правильный овтет___ " + trueAnswer);
                return true;
            } else {
                personLive -= 1;
                System.out.println("Кпц ты в матане не шаришь, пример 8-го класса, правильный ответ___ " + trueAnswer + "за это получаешь damage -1 здоровье , теперь твое здоровье " + personLive + "но в кайф для тебя монстр тоже погиб в бою");
                return false;
            }

        } else if (hardGame == 2) {
            int a = random.nextInt(1000);
            int b = random.nextInt(1000);
            int trueAnswer = a - b;
            System.out.println("На этой клетке сидит монстр " + monster + "! Решите задачу." + " твое кол во жизней " + personLive);
            System.out.println("Решай пример " + a + " - " + b + " = ? ");
            int answer = scanner.nextInt();
            if (answer == trueAnswer) {
                System.out.println("Хорош правильный овтет___ " + trueAnswer);
                return true;
            } else {
                personLive -= 1;
                System.out.println("Кпц ты в матане не шаришь, пример 8-го класса, правильный ответ___ " + trueAnswer + "за это получаешь damage -1 здоровье , теперь твое здоровье " + personLive + "но в кайф для тебя монстр тоже погиб в бою");
                return false;
            }

        } else if (hardGame == 3) {
            int a = random.nextInt(100);
            int b = random.nextInt(10);
            int trueAnswer = a * b;
            System.out.println("На этой клетке сидит монстр " + monster + "! Решите задачу." + " твое кол во жизней " + personLive);
            System.out.println("Решай пример " + a + " * " + b + " = ? ");
            int answer = scanner.nextInt();
            if (answer == trueAnswer) {
                System.out.println("Хорош правильный овтет___ " + trueAnswer);
                return true;
            } else {
                personLive -= 1;
                System.out.println("Кпц ты в матане не шаришь, пример 8-го класса, правильный ответ___ " + trueAnswer + "за это получаешь damage -1 здоровье , теперь твое здоровье " + personLive + "но в кайф для тебя монстр тоже погиб в бою");
                return false;
            }
        } else if (hardGame == 4) {
            int a = random.nextInt(100);
            int b = random.nextInt(10);
            int trueAnswer = a / b;
            System.out.println("На этой клетке сидит монстр " + monster + "! Решите задачу." + " твое кол во жизней " + personLive);
            System.out.println("Решай пример " + a + " / " + b + " = ? ");
            int answer = scanner.nextInt();
            if (answer == trueAnswer) {
                System.out.println("Хорош правильный овтет___ " + trueAnswer);
                return true;
            } else {
                personLive -= 1;
                System.out.println("Кпц ты в матане не шаришь, пример 8-го класса, правильный ответ___- " + trueAnswer + "за это получаешь damage -1 здоровье , теперь твое здоровье " + personLive + "но в кайф для тебя монстр тоже погиб в бою");
                return false;
            }
        } else {
            int a = random.nextInt(10);
            int b = random.nextInt(10);
            int trueAnswer = a % b;
            System.out.println("На этой клетке сидит монстр " + monster + "! Решите задачу." + " твое кол во жизней " + personLive);
            System.out.println("Решай пример " + a + " % " + b + " = ? ");
            int answer = scanner.nextInt();
            if (answer == trueAnswer) {
                System.out.println("Хорош правильный овтет___ " + trueAnswer);
                return true;
            } else {
                personLive -= 1;
                System.out.println("Кпц ты в матане не шаришь, пример 8-го класса, правильный ответ___ " + trueAnswer + "за это получаешь damage -1 здоровье , теперь твое здоровье " + personLive + "но в кайф для тебя монстр тоже погиб в бою");
                return false;
            }
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

void main() {
}
