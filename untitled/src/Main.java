//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.*;

public class Main {
    public static void main(String[] args) {

        int a;
        System.out.println("enter number of ellment of array :  ");
        Scanner number =new Scanner (System.in);
        a= number.nextInt();

        int [] arrry =new int [a];

        for(int i=0;i<a;i++){
            Scanner num =new Scanner (System.in);
            arrry[i]= num.nextInt();
        }
        System.out.println("target : ");
        Scanner tar =new Scanner (System.in);
        a= tar.nextInt();

        for (int i=0;i<a;i++)
        {
            for (int j=i;j<a;j++)
            {
                if (arrry[i]+arrry[j]==a)
                {
                    System.out.format("%d and %d in index %d and %d are two value that sum is your target \n",arrry[i],arrry[j],i+1,j+1);
                }
            }
        }

    }
}