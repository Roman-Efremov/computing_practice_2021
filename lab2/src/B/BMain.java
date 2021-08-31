package B;

/*5. На базе коллекций реализовать структуру хранения чисел с поддержкой
     следующих операций:
        • добавление/удаление числа;
        • поиск числа, наиболее близкого к заданному (т.е. модуль разницы минимален).
*/
public class BMain
{
    public static void main(String[] args)
    {
        NumberStorage nums = new NumberStorage();
        nums.add(2.0);
        nums.add(7.2);
        nums.add(2.8);
        nums.add(3.0);
        nums.add(3.5);
        nums.add(9.0);
        System.out.println(nums.toString());

        nums.delete(9.0);
        nums.delete(2.0);
        System.out.println(nums.toString());

        System.out.println("closest number for 3.2 is " + nums.findClosestNumber(3.2));
    }
}
