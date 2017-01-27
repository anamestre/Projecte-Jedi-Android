package com.mestre.ana.sessio3;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * Created by Ana on 26/01/2017.
 */

public class Evalua {

    private String expr;

    private Stack<Token> operator;
    private Stack<Token> value;
    private boolean error;

    Evalua(String expr){
        this.expr = expr;
    }
/*

    private void processOperator(Token t) {
        Token A = null, B = null;
        if (value.isEmpty()) {
            System.out.println("Expression error.");
            error = true;
            return;
        } else {
            B = value.peek();
            value.pop();
        }
        if (value.isEmpty()) {
            System.out.println("Expression error.");
            error = true;
            return;
        } else {
            A = value.peek();
            value.pop();
        }
        Token R = t.operate(A.getValue(), B.getValue());
        value.push(R);
    }

    public Double processInput(String input) {
        // The tokens that make up the input
        String[] parts = input.split(" ");
        Token[] tokens = new Token[parts.length];
        for (int n = 0; n < parts.length; n++) {
            tokens[n] = new Token(parts[n]);
        }

        // Main loop - process all input tokens
        for (int n = 0; n < tokens.length; n++) {
            Token nextToken = tokens[n];
            if (nextToken.getType() == Token.NUMBER) {
                value.push(nextToken);
            } else if (nextToken.getType() == Token.OPERATOR) {
                if (operator.isEmpty() || nextToken.getPrecedence() > operator.peek().getPrecedence()) {
                    operator.push(nextToken);
                } else {
                    while (!operator.isEmpty() && nextToken.getPrecedence() <= operator.peek().getPrecedence()) {
                        Token toProcess = operator.peek();
                        operator.pop();
                        processOperator(toProcess);
                    }
                    operator.push(nextToken);
                }
            }
            else if (nextToken.getType() == Token.LPAREN) operator.push(nextToken);
            else if (nextToken.getType() == Token.RPAREN) {
                while (!operator.isEmpty() && operator.peek().getType() == Token.OPERATOR) {
                    Token toProcess = operator.peek();
                    operator.pop();
                    processOperator(toProcess);
                }
                if (!operator.isEmpty() && operator.peek().getType() == Token.LPAREN) {
                    operator.pop();
                }
                else {
                    System.out.println("Error: unbalanced parenthesis.");
                    error = true;
                }
            }

        }
        // Empty out the operator stack at the end of the input
        while (!operator.isEmpty() && operator.peek().getType() == Token.OPERATOR) {
            Token toProcess = operator.peek();
            operator.pop();
            processOperator(toProcess);
        }
        // Print the result if no error has been seen.
        if(!error) {
            Token result = value.peek();
            value.pop();
            /*if (!operator.isEmpty() || !value.isEmpty()) {
                System.out.println("Expression error.");
            } else {
                return result.getValue();
            }
        }
    } */


}
