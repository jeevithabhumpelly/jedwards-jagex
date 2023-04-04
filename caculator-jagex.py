# Define operator priority
priority = {"+": 1, "-": 1, "*": 2, "/": 2, "^": 3}
# Define a function to calculate an arithmetic expression
def calculate_expression(expression):
    print(expression, '= ', end='')
    
    # Remove all spaces from the expression
    expression = expression.replace(" ", "")
    
    # Initialize an empty stack and an empty list for storing the result
    stack = []
    result = []
    
    # Loop through each character in the expression
    i = 0
    while i < len(expression):
        char = expression[i]
        # If the character is a digit or a decimal point or a negative sign at the beginning of the expression or after an operator or a left parenthesis
        if char.isdigit() or char == "." or (char == "-" and (i == 0 or expression[i-1] in "+-*/^(")):
            # Find the end of the number and append it to the result list
            j = i + 1
            while j < len(expression) and (expression[j].isdigit() or expression[j] == "."):
                j += 1
            result.append(expression[i:j])
            i = j
        # If the character is an operator
        elif char in "+-*/^":
            # Pop all operators with higher or equal priority from the stack and append them to the result list
            while stack and stack[-1] in "+-*/^" and priority[stack[-1]] >= priority[char]:
                result.append(stack.pop())
            # Push the current operator onto the stack
            stack.append(char)
            i += 1
        # If the character is a left parenthesis
        elif char == "(":
            # Push it onto the stack
            stack.append(char)
            i += 1
        # If the character is a right parenthesis
        elif char == ")":
            # Pop all operators from the stack and append them to the result list until a left parenthesis is found
            while stack and stack[-1] != "(":
                result.append(stack.pop())
            # If there's no left parenthesis, raise an error
            if not stack:
                raise ValueError("Mismatched parentheses")
            # Pop the left parenthesis from the stack
            stack.pop()
            i += 1
        # If the character is not a digit, a decimal point, a negative sign, an operator, a left parenthesis, or a right parenthesis, raise an error
        else:
            raise ValueError("Invalid character: {}".format(char))
    
    # Pop all remaining operators from the stack and append them to the result list
    while stack:
        if stack[-1] == "(":
            raise ValueError("Mismatched parentheses")
        result.append(stack.pop())
    
    # Evaluate the postfix expression
    for char in result:
        if char.isdigit() or char.replace(".", "").isdigit() or (char.startswith('-') and char[1:].replace(".", "").isdigit()):
            # If the character is a number, push it onto the stack
            stack.append(float(char))
        elif char in "+-*/^":
            # If the character is an operator, pop two numbers from the stack, apply the operator, and push the result onto the stack
            if len(stack) < 2:
                raise ValueError("Invalid expression")
            b = stack.pop()
            a = stack.pop()
            if char == "+":
                stack.append(a + b)
            elif char == "-":
                stack.append(a - b)
            elif char == "*":
                stack.append(a * b)
            elif char == "/":
                stack.append(a / b)
            elif char == "^":
                stack.append(a ** b)
        else:
            # If the character is not a number or an operator, raise an error
            raise ValueError("Invalid character: {}".format(char))
   
    # Print the original expression and the result
    print(stack.pop())
    
# Examples of using the calculate_expression function
calculate_expression("2 + 5")
calculate_expression("8 - 3")
calculate_expression("5 * 4")
calculate_expression("8 / 2")
calculate_expression("4 ^ 2")
calculate_expression("1 + 2 * 3")
calculate_expression("(1 + 2) * 3")
calculate_expression("6 + 3 - 2 + 12")
calculate_expression("2 * 15 + 23")
calculate_expression("10 - 3 ^ 2")
calculate_expression("3.5 * 3")
calculate_expression("-53 + -24")
calculate_expression("10 / 3")
calculate_expression("(-20 * 1.8) / 2")
calculate_expression("-12.315 - 42")
