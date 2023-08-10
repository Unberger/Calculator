import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введите математическое выражение:");
        Scanner scanner = new Scanner(System.in);
        String viraz = scanner.nextLine();
        String result = calc(viraz);
        System.out.println(result);
    }

    public static String calc (String viraz){
        String NOT_MATH_OPER = "т.к. строка не является математической операцией";
        String NOT_FORMAT_MATH = "т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)";
        String NOT_ROM_OTRIC = "т.к. в римской системе нет отрицательных чисел и нуля";
        String NOT_TWO_SS = "т.к. используются одновременно разные системы счисления";
        String NOT_SINGLE_DIGITS = "т.к. используются неверные числа";

        int operIndex = -1, operCount = 0;
        Roman roman = new Roman();
        String itog;
        String[] oper = {"+", "-", "*", "/"};
        String[] oper2 = {"\\+", "-", "\\*", "/"};
        char[] oper3 = {'+', '-', '*', '/'};


        for (int i = 0; i < oper.length; i++) {
            if (viraz.contains(oper[i])) {
                operIndex = i;
            }
        }
        try {

            if (operIndex == -1) {
                return NOT_MATH_OPER;
            }
            for (int i = 0; i < oper.length; i++) {
                operCount += viraz.length() - viraz.replace(String.valueOf(oper3[i]), "").length();
            }
            if (operCount > 1) {
                return NOT_FORMAT_MATH;
            }
            if (operCount == 0) {
                return NOT_MATH_OPER;
            }

            String[] num = viraz.split(oper2[operIndex]); //получили 2 числа из выражения

            if (roman.isRoman(num[0]) == roman.isRoman(num[1])) { //проверяем является ли операнды из одинаковой системы счисления
                int a, b, result;
                boolean isRoman = roman.isRoman(num[0]);

                if (isRoman) { //проверяем является ли операнды из римской системы счисления
                    a = roman.romToArab(num[0]);
                    b = roman.romToArab(num[1]);
                    if ((operIndex == 1) && (a <= b)) {
                        return NOT_ROM_OTRIC;
                    }

                } else { //иначе переводим операнды из выражения - строки в целочисленные
                    a = Integer.parseInt(num[0]);
                    b = Integer.parseInt(num[1]);
                }
                if ((0 >= a) | (a > 10) | (0 >= b) | (b > 10)) {
                    return NOT_SINGLE_DIGITS;
                }

                result = switch (oper[operIndex]) { //производим вычисления с помощью перебора вариантов
                    case "+" -> a + b;
                    case "-" -> a - b;
                    case "*" -> a * b;
                    default -> a / b;
                };

                if (isRoman) {
                    itog = roman.arabToRom(result);
                } else {
                    itog = String.valueOf(result);
                }

                return itog;

            } else {
                return NOT_TWO_SS;
            }

        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }
        return NOT_MATH_OPER;
    }
}
