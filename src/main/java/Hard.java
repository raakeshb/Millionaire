

import Exception.MillionaireException;
import java.util.InputMismatchException;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author raake
 */
public class Hard extends Options {

    public String question;
    public String[] options;
    public int Correct;
    public int amt;

    public Hard() {
    }

    public Hard(String Question, String[] opt, int CorrectAnswer, String[] lifeline, int price) {
        super(Question, opt, CorrectAnswer, lifeline, price);
    }

    public Hard(String question, String[] options, int Correct, int amt) {
        this.question = question;
        this.options = options;
        this.Correct = Correct;
        this.amt = amt;
    }

    public String getquestion() {
        return question;
    }

    public void setquestion(String question) {
        this.question = question;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public int getCorrect() {
        return Correct;
    }

    public void setCorrect(int Correct) {
        this.Correct = Correct;
    }

    public int getAmt() {
        return amt;
    }

    public void setAmt(int amt) {
        this.amt = amt;
    }

    public int round1() throws MillionaireException {
        Scanner read = new Scanner(System.in);
        int userinput = 404;
        int crtans = 0;
        int qloop = 1;
        System.out.println(question);
        System.out.println(options[0] + "     " + options[1]);
        System.out.println(options[2] + "     " + options[3]);
        System.out.println("PRICE: " + amt);
        try{while (qloop != 0) {
            System.out.println("Please Enter the Number to Select your Answer");
            userinput = read.nextInt();
            
            System.out.println("Are You Sure About Your Answer. Enter 1 for Yes else Enter 2 to Enter Your Answer Again");
            crtans = read.nextInt();
            if (crtans == 1) {
                qloop = 0;
            }
        }
        
        if (userinput == Correct) {
            System.out.println("Correct, Moving on to Next Question!!");
            return 1;
        } else if (userinput != Correct) {
            System.out.println("Wrong!! Game Over");
        }
        }
        catch(InputMismatchException e)
        {
            System.out.println("");
        }
        
        return 0;
    }

}
