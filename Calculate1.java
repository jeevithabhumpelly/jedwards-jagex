
public class Calculate1 {
    
    /*
     * The calculate function takes a mathematical expression string as input, 
     * splits the string into operands and operator, and then calculates the result
     * based on the operator.It handles division by zero by returning "Undefined", 
     * and handles invalid input by returning "Invalid input".
     */
    public static String calculate(String input) {
        
        if (!isValidInput(input)) {
            throw new IllegalArgumentException("Invalid input: " + input);
        }

        //Split the input string into operands and operator
        String[] tokens = input.split("\\s+");
        int operand1 = Integer.parseInt(tokens[0]);
        char operator = tokens[1].charAt(0);
        int operand2 = Integer.parseInt(tokens[2]);

        //Calculate the result based on the operator
        int result = 0;
        switch (operator) {
            case '+':
                result = operand1 + operand2;
                break;
            case '-':
                result = operand1 - operand2;
                break;
            case '*':
                result = operand1 * operand2;
                break;
            case '/':
                if (operand2 == 0) {
                    return "Undefined";
                }
                result = operand1 / operand2;
                break;
            case '^':
                result = (int) Math.pow(operand1, operand2);
                break;
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
        return Integer.toString(result);
    }
     
    public static boolean isValidInput(String expression) {
        return expression.matches("^\\s*\\d+\\s*[-+\\/*^]\\s*\\d+\\s*$");
    }
}
