using System;

namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            int[] arr = { -10, -7, -5, -4, -2, -1, 0, 1, 2, 3, 5, 6 };
            // Array.Sort(arr);

            for (int i = 0; i < arr.Length; i++)
            {
                int x = arr[i];
                int lo = i + 1;
                int hi = arr.Length - 1;

                while (lo < hi)
                {
                    int sum = x + arr[lo] + arr[hi];
                    if (sum > 0)
                        hi--;
                    else if (sum < 0)
                        lo++;
                    else
                    {
                        Console.WriteLine($"({arr[i]}, {arr[lo]}, {arr[hi]})");
                        lo++;
                        hi--;
                    }
                }
            }

            Console.ReadKey();
        }
    }
}