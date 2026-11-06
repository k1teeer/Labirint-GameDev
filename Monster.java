import java.util.Random;
import java.util.Scanner;

public class Monster {
    private String image = "\uD83D\uDC7E"; // 👾
    private final int x, y;
    Random random = new Random();

    Monster(int sizeBoard) {
        // Монстры не попадают на первую строку (y = 0 в массиве)
        this.y = random.nextInt(sizeBoard - 1) + 1;
        this.x = random.nextInt(sizeBoard);
    }

    public String getImage() {
        return image;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public boolean conflictPerson(int perX, int perY) {
        return perY - 1 == this.y && perX - 1 == this.x;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean taskMonster(int hardGame, int personLive) {
        Scanner scanner = new Scanner(System.in);
        int a, b, trueAnswer;
        String op;

        if (hardGame == 1) {
            a = random.nextInt(1000);
            b = random.nextInt(1000);
            trueAnswer = a + b;
            op = " + ";
        } else if (hardGame == 2) {
            a = random.nextInt(1000);
            b = random.nextInt(1000);
            trueAnswer = a - b;
            op = " - ";
        } else if (hardGame == 3) {
            a = random.nextInt(100);
            b = random.nextInt(10);
            trueAnswer = a * b;
            op = " * ";
        } else if (hardGame == 4) {
            a = random.nextInt(100);
            b = random.nextInt(9) + 1;
            trueAnswer = a / b;
            op = " / ";
        } else {
            a = random.nextInt(10);
            b = random.nextInt(9) + 1;
            trueAnswer = a % b;
            op = " % ";
        }

        System.out.println("На этой клетке сидит монстр " + image + "! Решите задачу. твое кол во жизней " + personLive);
        System.out.println("Решай пример " + a + op + b + " = ? ");
        int answer = scanner.nextInt();

        if (answer == trueAnswer) {
            System.out.println("Хорош правильный овтет___ " + trueAnswer);
            return true;
        } else {
            System.out.println("Кпц ты в матане не шаришь, пример 8-го класса, правильный ответ___ " + trueAnswer + " за это получаешь damage -1 здоровье , теперь твое здоровье " + (personLive - 1) + " но в кайф для тебя монстр тоже погиб в бою");
            return false;
        }
    }
}