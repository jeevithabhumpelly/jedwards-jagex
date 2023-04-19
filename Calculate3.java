import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Stack;

public class Calculate3 {
    /*
     * The calculate function first tokenizes the input expression by splitting it into operators, parentheses, 
     * and numbers (integer or decimal). The tokens are stored in an array of strings.
        The function then uses two stacks: one for values and one for operators. 
        It iterates over the tokens and performs the appropriate operation depending on the token type:
     */
    public static String calculate(String expression) {

        String[] tokens = expression.replaceAll("\\s+", "")
        .split("(?=[-+*/^()])|(?<=[^-+*/^()][-+*/^()])|(?<=\\/)");

        Stack<Double> values = new Stack<>();
        Stack<String> operators = new Stack<>();

        for (String token : tokens) {
            if (token.matches("^[+-]?\\d+(\\.\\d+)?$")) {
                values.push(Double.parseDouble(token));
            } else if (token.equals("(")) {
                operators.push(token);
            } else if (token.equals(")")) {
                while (!operators.peek().equals("(")) {
                    values.push(applyOperator(operators.pop(), values.pop(), values.pop()));
                }
                operators.pop();
            } else if (token.matches("[-+*/^]")) {
                while (!operators.isEmpty() && hasPrecedence(token, operators.peek())) {
                    values.push(applyOperator(operators.pop(), values.pop(), values.pop()));
                }
                operators.push(token);
            }
        }
        while (!operators.isEmpty()) {
            values.push(applyOperator(operators.pop(), values.pop(), values.pop()));
        }
        if (values.size() != 1) {
            return "Invalid input or undefined result";
        }
        //return String.format("%.1f", values.pop());
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("#.###", symbols);
        df.setRoundingMode(RoundingMode.HALF_UP);
        return df.format(values.pop());
        
    }

    private static double applyOperator(String operator, double b, double a) {
        switch (operator) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                if (b == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                return a / b;
            case "^":
                return Math.pow(a, b);
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }

    private static boolean hasPrecedence(String op1, String op2) {
        if (op2.equals("(") || op2.equals(")")) {
            return false;
        }
        if ((op1.equals("^") && op2.matches("[-+*/^]")) || 
            (op1.matches("[*/]") && op2.matches("[+-]"))) {
            return false;
        }
        return true;
    }
}
