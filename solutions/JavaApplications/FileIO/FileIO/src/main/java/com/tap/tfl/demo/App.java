package com.tap.tfl.demo;

import java.util.Scanner;

import com.tap.tfl.Entity.question;
import com.tap.tfl.Repository.questionBank;
import com.tap.tfl.ui.UIManager;

public class App {

    public static int ch;

    public static void main(String[] args) throws Exception {
        questionBank questionbank1 = new questionBank();
        UIManager uiManager = new UIManager();
        Scanner scanner = new Scanner(System.in);
       
        do {
            System.out.println();
            System.out.println("TFL Assessment Question Bank Menu");
            System.out.println("1. Add  Question");
            System.out.println("2. Update Question");
            System.out.println("3. Remove Question");
            System.out.println("4. Display All Questions");
            System.out.println("5. Display by Id ");
            System.out.println("6. Exit");
            System.out.println();
            System.out.println("Enter your choice: ");
            ch = scanner.nextInt();

            questionbank1.readFileAccess();  //read all questions from file
            

            switch (ch) {

                
                case 1: {
                    // get return que then call insert
                    question q = uiManager.setData();   // tocheck whether i can access the data file
                    //uiManager.getData(q);
                    questionbank1.insertQuestion(q);
                     questionbank1.writeFileAccess();
                    break;
                }
                case 2: {
                    System.out.println("Enter Question id: ");
                    // show sms to enter id for update question
                    questionbank1.updateQuestion(scanner.nextInt());
                     questionbank1.writeFileAccess();
                    break;
                }
                case 3: {
                    System.out.println("Enter the Question Id: ");
                    questionbank1.removeQuestion(scanner.nextInt());
                    System.out.println("Element Removed Successfully");
                     questionbank1.writeFileAccess();
                    break;
                }
                case 4: {
                    
                    questionbank1.display();
                    break;
                }
                case 5: {
                    System.out.println("Enter the Question Id: ");
                    questionbank1.displayById(scanner.nextInt());
                    break;
                }
                case 6: {
                    questionbank1.writeFileAccess();
                    System.out.println("Your Data is Successfully saved");
                    break;
                }
                default:
                    break;
            }
        } while (ch <= 5);
    }
}
