import java.util.Scanner;  

public class StringInput {
    public static void main(String[] args)  {  
        Scanner sc= new Scanner(System.in);  //System.in is a standard input stream  
        System.out.print("Enter a string 1: ");  
        System.out.print("Enter a string 2: "); 
        String str= sc.next();   //reads string before the space  
        System.out.print("You have entered: "+str);        
        sc.close();     
}  
}
