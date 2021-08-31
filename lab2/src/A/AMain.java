package A;

import java.util.ArrayList;
import java.util.Stack;

/*5. Задать два стека, поменять информацию местами*/
public class AMain
{
    public static void main(String[] args)
    {
        Stack<Integer> st1 = new Stack<Integer>();
        Stack<Integer> st2 = new Stack<Integer>();
        st1.push(1);
        st1.push(2);
        st1.push(8);
        st1.push(7);

        st2.push(3);
        st2.push(8);


        System.out.println("First stack before: " + st1);
        System.out.println("Second stack before: " + st2);

        swapStacks(st1, st2);

        System.out.println("First stack after: " + st1);
        System.out.println("Second stack after: " + st2);
    }

    public static void swapStacks(Stack<Integer> st1, Stack<Integer> st2)
    {
        ArrayList<Integer> tmp = new ArrayList<>(st1);
        st1.clear();
        st1.addAll(st2);
        st2.clear();
        st2.addAll(tmp);
    }
}
