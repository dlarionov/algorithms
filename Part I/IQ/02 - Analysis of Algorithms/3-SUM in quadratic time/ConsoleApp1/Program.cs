﻿using System;

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
                    if (hi == i)
                    {
                        hi--;
                        continue;
                    }

                    int sum = x + arr[lo] + arr[hi];
                    if (sum > 0)
                        hi--;
                    else if (sum < 0)
                        lo++;
                    else
                    {
                        if (i < lo)
                            Console.WriteLine($"({arr[i]}, {arr[lo]}, {arr[hi]})");
                        else if (i > hi)
                            Console.WriteLine($"({arr[lo]}, {arr[hi]}, {arr[i]})");
                        else
                            Console.WriteLine($"({arr[lo]}, {arr[i]}, {arr[hi]})");

                        hi--;
                    }
                }
            }

            Console.ReadKey();
        }
    }
}