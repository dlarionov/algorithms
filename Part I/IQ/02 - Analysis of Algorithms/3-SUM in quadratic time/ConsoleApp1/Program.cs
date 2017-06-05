using System;

namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            int[] arr = { -7, -5, -4, -3, -3, 0, 1, 2, 3, 4, 5, 6 };
            // Array.Sort(arr);

            for (int i = 0; i < arr.Length; i++)
            {
                int x = arr[i];
                int lo = 0;
                int hi = arr.Length - 1;

                while (lo < hi)
                {
                    int y = arr[lo];
                    int z = arr[hi];
                    int sum = y + z;

                    if (hi == i || sum > x)
                        hi--;
                    else if (lo == i || sum < x)
                        lo++;
                    else
                    {
                        Console.WriteLine($"({arr[lo]},{arr[i]},{arr[hi]})");
                        break;
                    }
                }
            }

            Console.ReadLine();
        }
    }
}