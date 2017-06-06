using System;

namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            int[] arr = { -1, 1, 3, 5, 7, 9, 11, 10, 8, 6, 4, 2, 0, -2 };
            for (int i = -3; i < 12; i++)
            {
                Console.WriteLine($"{i} {FindLinear(arr, i)} {FindLogarithmic(arr, i)}");
            }
            Console.ReadKey();
        }

        private static int FindLinear(int[] arr, int x)
        {
            for (int i = 0; i < arr.Length; i++)
            {
                if (arr[i] == x)
                    return i;
            }
            return -1;
        }

        private static int FindLogarithmic(int[] arr, int x)
        {
            int lo = -1;
            int hi = arr.Length;

            while (lo > hi)
            {
                int m = lo + ((hi - lo) / 2);
            }
            
        }
    }
}