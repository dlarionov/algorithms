using System;
using System.Collections.Generic;

namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            int n = 512;
            var rnd = new Random();
            int trueCnt = 0;
            int falseCnt = 0;
            int[] arr = new int[n];

            for (int j = 0; j < 1000; j++)
            {
                for (int i = 0; i < arr.Length; i++)
                {
                    arr[i] = rnd.Next(int.MinValue, int.MaxValue);
                }

                if (IsThere4Sum(arr))
                    trueCnt++;
                else
                    falseCnt++;
            }

            Console.WriteLine($"{trueCnt} / {falseCnt}");
            Console.ReadKey();
        }

        public static bool IsThere4Sum(int[] arr)
        {
            // O(n^2) time and O(n^2) space
            var set = new HashSet<int>();
            for (int i = 0; i < arr.Length; i++)
            {
                for (int j = i + 1; j < arr.Length; j++)
                {
                    if (!set.Add(arr[i] + arr[j]))
                        return true;
                }
            }
            return false;
        }
    }
}