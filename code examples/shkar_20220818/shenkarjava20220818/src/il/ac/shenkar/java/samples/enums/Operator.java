package il.ac.shenkar.java.samples.enums;

enum Operator {

    MULTIPLY {
        double calculate(double num1, double num2) {
            return num1 * num2;
        }
    },
    DIVIDE {
        double calculate(double num1, double num2) throws OperatorException {
            if(num2==0) {
                throw new OperatorException("cannot divide by zero");
            }
            return num1 / num2;
        }
    },
    PLUS {
        double calculate(double num1, double num2) {
            return num1 + num2;
        }
    },
    MINUS {
        double calculate(double num1, double num2) {
            return num1 - num2;
        }
    };

    abstract double calculate(double num1, double num2) throws OperatorException;
}