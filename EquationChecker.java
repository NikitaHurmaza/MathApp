package rootFinder;

import java.util.Stack;

public class EquationChecker { // 1-3 условия, + дополнительное.

    public static boolean checkEquation(String equation) {
        for (int i = 0; i < equation.length(); i++) {
            char ch = equation.charAt(i);
            if (i > 0 && Operators(ch) && Operators(equation.charAt(i - 1))) {//проверка на два знака подряд
                return false;
            }
        }

        return true;
    }
    public static boolean Operators(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }

    public static boolean checkParentheses(String equation) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < equation.length(); i++) {
            char ch = equation.charAt(i);

            if (ch == '(') {
                stack.push(ch);
            } else if (ch == ')') {
                if (stack.isEmpty()) {
                    return false;
                }
                stack.pop();
            }
        }

        return stack.isEmpty();
    }
    public static int countNumbers(String equation) {
        int count = 0;
        boolean inNumber = false;
        for (int i = 0; i < equation.length(); i++) {
            char ch = equation.charAt(i);
            if (Character.isDigit(ch)) {
                if (!inNumber) {
                    count++;
                    inNumber = true;
                }
            } else {
                inNumber = false;
            }
        }
        return count;
    }
}

