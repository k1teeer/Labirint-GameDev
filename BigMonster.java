import java.util.Scanner;

public class BigMonster extends Monster {
    private String image = "\uD83D\uDC79"; // 👹

    BigMonster(int sizeBoard) {
        super(sizeBoard);
    }

    @Override
    public String getImage() {
        return image;
    }

    @Override
    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean taskMonster(int hardGame, int personLive) {
        if (hardGame == 1) {
            return super.taskMonster(1, personLive);
        }

        Scanner scanner = new Scanner(System.in);
        int a = random.nextInt(50 * hardGame) + 10;
        int b = random.nextInt(10 * hardGame) + 2;
        int c = random.nextInt(100 * hardGame) + 1;
        int trueAnswer = a * b - c;

        System.out.println("На этой клетке сидит Большой Монстр " + image + "! Решите сложную задачу. твое кол во жизней " + personLive);
        System.out.println("Решай пример " + a + " * " + b + " - " + c + " = ? ");
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