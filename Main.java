
import java.util.*;
import java.util.Scanner;
// main entrance of bus reservation system
// it is used to access admin pannel and user pannel
// MenuService class is used to access customer menu and admin menu

public class Main
{
    public static void main(String args[])
    {
        //Ask user to select 2 options , 1.Admin or 2.customer
        // transport name Menaka Bus Service 

        MenuService menus = new MenuService();

        Scanner in = new Scanner(System.in);

        System.out.println("***********************************");
        System.out.println("Developed by : SUNDARAMOORTHI M");
        System.out.println("BCA SEMESTER 3 ROLL NO : 24UCA143");
        System.out.println("**************************************");

        System.out.println("***********************************");
        System.out.println("-----------------------------------");
        System.out.println("Menaka Bus Services Private Limited");

        System.out.println("***********************************");
        System.out.println("-----------------------------------");

        int passkey = 1234; // store pass key
        int pass; // store user pass
        int choice ; // user choice

        

        int n;
        do
        {

         System.out.println("Who are You ?");


        System.out.println("1.Admin");
        System.out.println("2.Customer");

        System.out.println("Enter service you want : ");
        choice = in.nextInt();

        switch(choice)
        {
            case 1 :
               System.out.println("Enter your passkey Admin :");
               pass = in.nextInt();

               boolean correct = true;
               do
               {
               if(pass == passkey)
               {
               // System.out.println("project is under developement , in future admin services will we diplayed Thankyou !");
               menus.ShowAdminMenu();
                break;
               }

               else
               {
                System.out.println("Please  try again");
               }

            } while(!correct);
               break;

            case 2 :
               menus.ShowCustomerMenu();
               break;

            default :
              System.out.println("Invalid choice");
              break;
        }

        System.out.println("press 1 to go to main or 0 to exit");
        n = in.nextInt();
        
        }while(n!=0);

        in.close();
    }

}

