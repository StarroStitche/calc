import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String expression = scanner.nextLine();
            System.out.println(calc(expression));
        }
    }
    public static String calc(String input) {
        String[] splitInput = input.split(" ", 3);
        int firstNum;
        int secondNum;
        int result;
        boolean isFirstRoman = true;
        boolean isSecondRoman = true;

        try {
            firstNum = Integer.parseInt(splitInput[0]);
            isFirstRoman = false;
        } catch (NumberFormatException e) {
            firstNum = Roman.valueOf(splitInput[0]).ordinal() + 1;
        }
        try {
            secondNum = Integer.parseInt(splitInput[2]);
            isSecondRoman = false;
        } catch (NumberFormatException e) {
            secondNum = Roman.valueOf(splitInput[2]).ordinal() + 1;
        }
        switch (splitInput[1]) {
            case "+" -> result = firstNum + secondNum;
            case "-" -> result = firstNum - secondNum;
            case "*" -> result = firstNum * secondNum;
            case "/" -> result = firstNum / secondNum;
            default -> throw new RuntimeException("Доступные операции только: + - * /");
        }
        if (!isFirstRoman && !isSecondRoman &&
                firstNum > 0 && firstNum <= 10  &&
                secondNum > 0 && secondNum <= 10){
            return Integer.toString(result);
        } else if (isFirstRoman && isSecondRoman){
            return arabToRoman(result);
        } else {
            throw new RuntimeException("Числа разных форматов не допустимы");
        }
    }

    static String arabToRoman(int num) {
        if (num <=0) {
            throw new RuntimeException("Невозможно записать отрицательный результат или 0 Арабскими цифрами");
        }
        if (num == 100) {
            return "C";
        }
        if (num == 90) {
            return "XC";
        }
        StringBuilder ret = new StringBuilder();
        if(num / 50 == 1) {
            ret.append("L");
            num -= 50;
        }
        if (num / 40 == 1) {
            ret = new StringBuilder(Roman.X.name() + "L");
            num -= 40;
        }
        while (num / 10 > 0) {
                ret.append(Roman.X.name());
                num -= 10;
        }
        if (num / 9 == 1) {
            ret.append(Roman.IX.name());
            num -= 9;
        }
        if (num / 5 == 1) {
            ret.append(Roman.V.name());
            num -= 5;
        }
        if (num / 4 == 1) {
            ret.append(Roman.IV.name());
            num -= 4;
        }
        while (num > 0) {
            ret.append(Roman.I.name());
            num -= 1;
        }
        return ret.toString();
    }
}