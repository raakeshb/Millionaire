
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author raake
 */
import Exception.MillionaireException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Millionaire {

    public static String[][] qs(int i) {
        String[][] qb = new String[25][1];
        String filename = null;
        int arr = 0;
        if (i == 1) {
            filename = "qq.txt";
        } else if (i == 2) {
            filename = "hard.txt";
        }
        try {
            File file = new File(filename);
            Scanner filescan = new Scanner(file);
            while (filescan.hasNextLine()) {
                String data = filescan.nextLine();
                qb[arr][0] = data;
                arr++;
            }
            filescan.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred, While reading your file");
        }

        return qb;
    }

    public static void main(String[] args) throws MillionaireException {
        Scanner read = new Scanner(System.in);
        Random rand = new Random();
        int optionchoice = 100;
        int walkaway;
        String Name;
        String[] qitems = null;
        String[][] questions = null;
        ArrayList<Integer> omitit = new ArrayList<Integer>();
        int[] omiteasy = {0, 1, 2, 3, 4, 5, 6, 7, 8};
        int[] omithard = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
        int input = 0;
        System.out.println("Welcome to Who Wants to be a Millionaire\n");

        while (input != 3) {
            System.out.println("       Menu");
            System.out.println("    1.Start the Game");
            System.out.println("    2.Rules");
            System.out.println("    3.Exit");
            input = read.nextInt();
            if (input == 2) {
                System.out.println("        Rules");
                System.out.println("User gets 2 Opions : Easy and Hard");
                System.out.println("Each Options has 3 Rounds with series of 9 questions for Easy and 15 questions for Hard");
                System.out.println("Lifeline is accessible for easy from starting and for Hard will be available at round 2");
                System.out.println("You Can withdraw at the Second Half of the Round");
                System.out.println("If User Answers Wrong Game Over!!");
                while (input != 4) {
                    System.out.println("Enter 4 to Go back to Main Menu");
                    input = read.nextInt();
                }
            } else if (input == 1) {

                System.out.println("Game Starting >>>>");
                System.out.println("Please Enter Your Name");
                Name = read.nextLine();
                Name = read.nextLine();
                System.out.println("Select the Difficulty 1 For Easy and 2 For Hard");
                try {
                    optionchoice = read.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("You Have Provided Input other than Integer");

                }
                if (optionchoice == 1) {
                    for (int i = 0; i < omiteasy.length; i++) {
                        omitit.add(omiteasy[i]);
                    }
                    questions = qs(optionchoice);
                    String[] lifeli = {"1.Phone-A-Friend", "2.50/50", "3.Audience Poll"};
                    int[] PriceAmount = {100, 500, 1000, 8000, 16000, 32000, 125000, 500000, 1000000};
                    int Amt = 0;
                    int iterator = 0;
                    int randd;
                    int round = 1;
                    String[] qoptions;
                    for (int i = 0; i < 9; i++) {
                        if ((i + 1) % 3 == 0) {
                            System.out.println("End of Round: " + round);
                            round++;
                        }

                        randd = rand.nextInt(omiteasy.length);
                        while (!omitit.isEmpty()) {
                            randd = rand.nextInt(omiteasy.length);
                            if (omitit.size() == 1) {
                                randd = omitit.get(0);
                                break;
                            } else if (omitit.contains(randd)) {
                                omitit.remove(omitit.indexOf(randd));
                                break;
                            }

                        }

                        qitems = questions[randd][0].split(",");
                        qoptions = qitems[1].split(":");

                        Options qb;

                        qb = new Easy(qitems[0], qoptions, Integer.parseInt(qitems[2]), lifeli, PriceAmount[iterator]);
                        qb.display();
                        try {
                            int m = qb.operations();
                            if (m <= 3) {
                                lifeli[m - 1] = "";
                                System.out.println("You Won $" + PriceAmount[iterator]);
                                iterator++;
                                if ((i + 1) == 3 || (i + 1) == 6) {
                                    System.out.println("Do You Want to Withdraw. Enter 1 for Yes and 2 for No");
                                    walkaway = read.nextInt();
                                    if (walkaway == 1) {
                                        System.out.println("Your Final Amount $" + PriceAmount[iterator]);
                                        input = 3;
                                        break;
                                    }
                                }
                            } else if (m == 101) {
                                continue;
                            } else if (m == 99) {
                                if (i == 0) {
                                    System.out.println("You Won 0$");
                                    input = 3;
                                    break;
                                } else {
                                    System.out.println("Your Final Amount $" + PriceAmount[iterator - 1]);

                                    input = 3;
                                    break;
                                }
                            } else {

                                System.out.println("You Won $" + PriceAmount[iterator]);
                                iterator++;
                                if ((i + 1) == 3 || (i + 1) == 6) {
                                    System.out.println("Do You Want to Withdraw. Enter 1 for Yes and 2 for No");
                                    walkaway = read.nextInt();
                                    if (walkaway == 1) {
                                        System.out.println("Your Final Amount $" + PriceAmount[iterator]);

                                        input = 3;
                                        break;
                                    }
                                }

                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Please Use Integers for providing Input");
                        }
                    }

                } else if (optionchoice == 2) {
                    for (int i = 0; i < omithard.length; i++) {
                        omitit.add(omithard[i]);
                    }
                    questions = qs(optionchoice);
                    String[] lifeli = {"1.Phone-A-Friend", "2.50/50", "3.Audience Poll"};
                    int[] PriceAmount = {100, 200, 300, 400,500, 1000, 2000, 4000, 8000, 32000, 64000, 125000, 250000, 500000, 1000000};
                    int randd;
                    int q;
                    int iterator = 0;
                    int Amt = 0;
                    int round = 1;
                    String[] qoptions;
                    for (int i = 0; i < 5; i++) {
                        if ((i + 1) % 5 == 0) {
                            System.out.println("End of Round: " + round);
                            round++;
                        }
                        randd = rand.nextInt(omithard.length);
                        while (omitit.size() != 0) {
                            randd = rand.nextInt(omithard.length);
                            if (omitit.size() == 1) {
                                randd = omitit.get(0);
                                break;
                            } else if (omitit.contains(randd)) {
                                omitit.remove(omitit.indexOf(randd));
                                break;

                            }

                        }

                        qitems = questions[randd][0].split(",");
                        qoptions = qitems[1].split(":");
                        Hard qb;
                        qb = new Hard(qitems[0], qoptions, Integer.parseInt(qitems[2]), PriceAmount[iterator]);
                        q = qb.round1();
                        
                         try {
                             if(q ==1){
                        System.out.println("You Won: " + PriceAmount[iterator]);
                             }
                             else if (q ==1 && iterator ==0)
                             {
                                 System.out.println("You Won : $0");
                             }
                       
                             else if (q == 0) {
                                input = 3;
                                System.out.println("Final Amount: " + PriceAmount[iterator - 1]);
                                break;
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Please Use Integers for providing Input");
                        }
                         iterator++;
                    }
                    if (input == 3) {
                        break;
                    } else {
                        for (int i = 5; i < 15; i++) {

                            randd = rand.nextInt(omithard.length);
                        while (!omitit.isEmpty()) {
                            randd = rand.nextInt(omithard.length);
                            if (omitit.size() == 1) {
                                randd = omitit.get(0);
                                break;
                            } else if (omitit.contains(randd)) {
                                omitit.remove(omitit.indexOf(randd));
                                break;
                            }

                        }

                            qitems = questions[randd][0].split(",");
                            qoptions = qitems[1].split(":");

                            Options qb;

                            qb = new Hard(qitems[0], qoptions, Integer.parseInt(qitems[2]), lifeli, PriceAmount[iterator]);
                            qb.display();
                            try {
                                int m = qb.operations();
                                if (m <= 3) {
                                    lifeli[m - 1] = "";

                                    System.out.println("You Won $" + PriceAmount[iterator]);
                                    iterator++;
                                    if ((i + 1) == 3 || (i + 1) == 6) {
                                        System.out.println("Do You Want to Withdraw. Enter 1 for Yes and 2 for No");
                                        walkaway = read.nextInt();
                                        if (walkaway == 1) {
                                            System.out.println("Your Final Amount $" + PriceAmount[iterator]);
                                            input = 3;
                                            break;
                                        }
                                    }
                                } else if (m == 99) {
                                    if (i == 0) {
                                        System.out.println("You Won 0$");
                                        input = 3;
                                        break;
                                    } else {
                                        System.out.println("Your Final Amount $" + PriceAmount[iterator - 1]);
                                        input = 3;
                                        break;
                                    }
                                } else if (m == 101) {
                                    continue;
                                } else {

                                    System.out.println("You Won $" + PriceAmount[iterator]);
                                    iterator++;
                                    if ((i + 1) == 3 || (i + 1) == 6) {
                                        System.out.println("Do You Want to Withdraw. Enter 1 for Yes and 2 for No");
                                        walkaway = read.nextInt();
                                        if (walkaway == 1) {
                                            System.out.println("Your Final Amount $" + PriceAmount[iterator]);
                                            input = 3;
                                            break;
                                        }
                                    }

                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Please Use Integers for providing Input");
                            }
                        }
                    }

                }
            }

        }

    }
}
