
import java.util.*;
import Exception.MillionaireException;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author raake
 */
public abstract class Options {

    protected String Question;
    protected String[] opt;
    protected int CorrectAnswer;
    protected String[] lifeline;
    protected int price;

    public String[] getOpt() {
        return opt;
    }

    public void setOpt(String[] opt) {
        this.opt = opt;
    }

    public Options() {
    }

    public String getQuestion() {
        return Question;
    }

    public Options(String Question, String[] opt, int CorrectAnswer, int price) {
        this.Question = Question;
        this.opt = opt;
        this.CorrectAnswer = CorrectAnswer;
        this.price = price;
    }

    public Options(String Question, String[] opt, int CorrectAnswer, String[] lifeline) {
        this.Question = Question;
        this.opt = opt;
        this.CorrectAnswer = CorrectAnswer;
        this.lifeline = lifeline;
    }

    public Options(String Question, String[] opt, int CorrectAnswer, String[] lifeline, int price) {
        this.Question = Question;
        this.opt = opt;
        this.CorrectAnswer = CorrectAnswer;
        this.lifeline = lifeline;
        this.price = price;
    }

    public Options(int price) {
        this.price = price;
    }

    public void setQuestion(String Question) {
        this.Question = Question;
    }

    public int getCorrectAnswer() {
        return CorrectAnswer;
    }

    public void setCorrectAnswer(int CorrectAnswer) {
        this.CorrectAnswer = CorrectAnswer;
    }

    public String[] getLifeline() {
        return lifeline;
    }

    public void setLifeline(String[] lifeline) {
        this.lifeline = lifeline;
    }

    public int getprice() {
        return price;
    }

    public void setUseropt(int price) {
        this.price = price;
    }

    public void display() {
        System.out.println(Question);
        System.out.println(opt[0] + "     " + opt[1]);
        System.out.println(opt[2] + "     " + opt[3]);
        System.out.println("PRICE: " + price);
        System.out.println("Please Enter your Answer by Entering the Option");
    }

   

    public int operations() throws MillionaireException {
        try{
        Random rand = new Random();
        Scanner read = new Scanner(System.in);
        int qloop = 1;
        int userinput = 100;
        int lifelineoption = 404;
        System.out.println("To Use Lifeline Enter 5");
        while (qloop != 0) {
            userinput = read.nextInt();
            System.out.println("Are You Sure About Your Answer. Enter 1 for Yes else Enter 2 to Enter Your Answer Again");
            qloop = read.nextInt();
            if (qloop == 1) {
                qloop = 0;
            }
        }

        if (userinput == 5 && "".equals(lifeline[0]) && "".equals(lifeline[1]) && "".equals(lifeline[2])) {
            throw new MillionaireException(" You Have used all the options");
        } else if (userinput == 5) {
            System.out.println("You Have Selected the Lifeline Option");
            System.out.println("Lifeline:");
            for (int i = 0; i < lifeline.length; i++) {
                System.out.println(lifeline[i]);
            }
            System.out.println("Please Enter the Lifeline");
            lifelineoption = read.nextInt();
            if (lifelineoption - 1 == 0 && (lifeline[0]) != "") {
                int phno;
                String[] friendoption = {"Your Friend is Sure About the Answer", "Your Friend is not Sure About the Answer and Time has ended", "Your Friend is Out of Answers"};
                System.out.println("Please Enter Your Friend's Phno");
                phno = read.nextInt();
                System.out.println("Calling Number: " + phno);
                int randd = rand.nextInt(friendoption.length);
                System.out.println(friendoption[randd]);
                if (randd == 0) {
                    System.out.println(opt[CorrectAnswer - 1]);
                    System.out.println("Enter Your Answer");
                    qloop = 1;
                    while (qloop != 0) {
                        userinput = read.nextInt();
                        System.out.println("Are Your Sure About Your Answer. Enter 1 for Yea and 2 for No");
                        int crtans = read.nextInt();
                        if (crtans == 1) {
                            qloop = 0;
                        }
                    }
                } else {
                    System.out.println("Enter Your Answer");
                    qloop = 1;
                    while (qloop != 0) {
                        userinput = read.nextInt();
                        System.out.println("Are Your Sure About Your Answer. Enter 1 for Yea and 2 for No");
                        int crtans = read.nextInt();
                        if (crtans == 1) {
                            qloop = 0;
                        }
                    }
                }

            } else if (lifelineoption - 1 == 1 && (lifeline[1]) != "") {
                int no = rand.nextInt(opt.length);

                System.out.println("These are the Options: ");
                System.out.println(opt[CorrectAnswer - 1]);
                while (no != CorrectAnswer) {
                    no = rand.nextInt(opt.length);
                }
                System.out.println(opt[no]);
                System.out.println("Enter Your Answer");
                qloop = 1;
                while (qloop != 0) {
                    userinput = read.nextInt();
                    System.out.println("Are Your Sure About Your Answer. Enter 1 for Yea and 2 for No");
                    int crtans = read.nextInt();
                    if (crtans == 1) {
                        qloop = 0;
                    }
                }

            } else if (lifelineoption - 1 == 2 && (lifeline[2]) != "") {
                for (int i = 0; i < opt.length; i++) {
                    System.out.println(opt[i] + " " + rand.nextInt(101) + "%");
                }
                System.out.println("Enter Your Answer");
                qloop = 1;
                while (qloop != 0) {
                    userinput = read.nextInt();
                    System.out.println("Are Your Sure About Your Answer. Enter 1 for Yea and 2 for No");
                    int crtans = read.nextInt();
                    if (crtans == 1) {
                        qloop = 0;
                    }
                }
            }
            else
            {
                throw new MillionaireException("You Have Used this Lifeline Option");
            }
        }
        if (userinput == CorrectAnswer) {
            System.out.println("Correct Answer Congratulations Moving to Next Question ");
            return lifelineoption;

        } else if (userinput != CorrectAnswer) {
            System.out.println("Wrong!! Game Over");
            return 99;
        }

        return 6;
    }
        catch(InputMismatchException e)
        {
            System.out.println("Please Enter only Integer as Input");
        }
        catch(MillionaireException e)
        {
            System.out.println("Message: "+e.getMessage()); 
        }
        return 101;
}
}
