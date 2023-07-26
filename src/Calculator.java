import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        int num1, num2, operIndex = -1, operCount = 0;
        Roman roman = new Roman();
        String[] oper = {"+", "-", "*", "/"};
        String[] oper2 = {"\\+", "-", "\\*", "/"};
        char[] oper3 = {'+', '-', '*', '/'};
        System.out.println("Введите математическое выражение:");
        Scanner scanner = new Scanner(System.in);
        String viraz = scanner.nextLine();

        for (int i = 0; i < oper.length; i++) {
            if (viraz.contains(oper[i])) {
                operIndex = i;
            }
        }
        if (operIndex == -1) {
            System.out.println("throws Exception //т.к. строка не является математической операцией");
            System.exit(1);
        }

        for (int i = 0; i < oper.length; i++) {
            //String[] virazMas = new String[]{viraz};
            operCount += viraz.length() - viraz.replace(String.valueOf(oper3[i]), "").length();
        }
        if (operCount > 1) {
            System.out.println("throws Exception //т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
            System.exit(1);
        }
        if (operCount == 0) {
            System.out.println("throws Exception //т.к. строка не является математической операцией");
            System.exit(1);
        }



        String[] num = viraz.split(oper2[operIndex]); //получили 2 числа из выражения

        if (roman.isRoman(num[0]) == roman.isRoman(num[1])) { //проверяем является ли операнды из одинаковой системы счисления
            int a, b, result;
            boolean isRoman = roman.isRoman(num[0]);

            if (isRoman){ //проверяем является ли операнды из римской системы счисления
                a = roman.romToArab(num[0]);
                b = roman.romToArab(num[1]);
                if ((operIndex == 1) && (a <= b)) {
                    System.out.println("throws Exception //т.к. в римской системе нет отрицательных чисел и нуля");
                    System.exit(1);
                }

            } else { //иначе переводим операнды из выражения - строки в целочисленные 
                a = Integer.parseInt(num[0]);
                b = Integer.parseInt(num[1]);
            }
            
            
            switch (oper[operIndex]) { //производим вычисления с помощью перебора вариантов
                case "+":
                    result = a + b;
                    break;
                case "-":
                    result = a - b;
                    break;
                case "*":
                    result = a * b;
                    break;
                default:
                    result = a / b;
            }
            if (isRoman) {
                System.out.println(roman.arabToRom(result));
            } else {
                System.out.println(result);
            }
        } else {
            System.out.println("throws Exception //т.к. используются одновременно разные системы счисления");
            System.exit(1);
        }
    }
}