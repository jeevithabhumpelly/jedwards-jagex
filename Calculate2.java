import java.util.Stack;

public class Calculate2 {
 
    /*The calculate function takes a mathematical expression string as input and 
     * returns the result as a string. The function handles expressions with multiple 
     * operators and parentheses, where parentheses have higher precedence level than 
     * other operators. The input can contain the following:
        Operators: +, -, *, /, ^
        Parentheses: ( )
        Positive integers
    The function uses a stack-based algorithm to evaluate the expression. 
    It first removes all white spaces from the input string using the replaceAll method. 
    Then it initializes two stacks: one for numbers and one for operators. 
    The numbers stack will hold all the numbers in the expression, and the operators 
    stack will hold all the operators.
     */
    public static String calculate(String input) {
        
        if (!isValidInput(input)) {
            throw new IllegalArgumentException("Invalid input: " + input);
        }

        input = input.replaceAll("\\s+", "");
        Stack<Integer> numbers = new Stack<Integer>();
        Stack<Character> operators = new Stack<Character>();
    
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (Character.isDigit(c)) {
                int num = 0;
                while (i < input.length() && Character.isDigit(input.charAt(i))) {
                    num = num * 10 + (input.charAt(i) - '0');
                    i++;
                }
                i--;
                numbers.push(num);
            } else if (c == '(') {
                operators.push(c);
            } else if (c == ')') {
                while (operators.peek() != '(') {
                    int num2 = numbers.pop();
                    int num1 = numbers.pop();
                    char op = operators.pop();
                    int result = performOperation(num1, num2, op);
                    numbers.push(result);
                }
                operators.pop();
            } else if (isOperator(c)) {
                while (!operators.empty() && precedence(c) <= precedence(operators.peek())) {
                    int num2 = numbers.pop();
                    int num1 = numbers.pop();
                    char op = operators.pop();
                    int result = performOperation(num1, num2, op);
                    numbers.push(result);
                }
                operators.push(c);
            } else {
                return "Invalid input"; 
            }
        }
    
        while (!operators.empty()) {
            int num2 = numbers.pop();
            int num1 = numbers.pop();
            char op = operators.pop();
            int result = performOperation(num1, num2, op);
            numbers.push(result);
        }
    
        if (numbers.size() == 1) {
            // If there is only one number in the stack, return it as a string
            return String.valueOf(numbers.pop()); 
        } else {
            // If there is more than one number in the stack, the result is undefined
            return "Undefined result"; 
        }
    }
    
    private static boolean isOperator(char c) {
        return (c == '+' || c == '-' || c == '*' || c == '/' || c == '^');
    }
    
    private static int precedence(char c) {
        if (c == '+' || c == '-') {
            return 1;
        } else if (c == '*' || c == '/') {
            return 2;
        } else if (c == '^') {
            return 3;
        } else {
            return 0;
        }
    }
    
    private static int performOperation(int operand1, int operand2, char operator) {
        switch (operator) {
            case '+':
                return operand1 + operand2;
            case '-':
                return operand1 - operand2;
            case '*':
                return operand1 * operand2;
            case '/':
                if (operand2 == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                return operand1 / operand2;
            case '^':
                return (int) Math.pow(operand1, operand2);             
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }

    public static boolean isValidInput(String expression) {
        return expression.matches("^[0-9()+\\-*/^ \t]*$");
    }
    
}