using System;
using System.Collections.Generic;

namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            int n = 10000;

            Taxicab1(n);
            Taxicab2(n);

            Console.ReadKey();
        }

        public static void Taxicab1(int n)
        {
            // m is cubic root of n
            int m = (int)Math.Floor(Math.Pow(n, (double)1 / 3));

            // O(m^2) time and O(m^2) space
            var list = new List<int>();
            for (int i = 1; i <= m; i++)
            {
                for (int j = i; j <= m; j++)
                {
                    int x = (int)Math.Pow(i, 3) + (int)Math.Pow(j, 3);
                    list.Add(x);
                }
            }

            // O(m^2*log(m^2)) time
            list.Sort();

            // O(m^2)
            int prev = -1;
            foreach (var next in list)
            {
                if (prev == next)
                {
                    Console.WriteLine($"{prev}");
                }
                prev = next;
            }
        }

        public static void Taxicab2(int n)
        {
            // m is cubic root of n
            int m = (int)Math.Floor(Math.Pow(n, 1d / 3));

            // O(m^2) time and O(m^2) space
            var set = new HashSet<int>();
            for (int i = 1; i <= m; i++)
            {
                for (int j = i; j <= m; j++)
                {
                    int x = (int)Math.Pow(i, 3) + (int)Math.Pow(j, 3);
                    if (!set.Add(x))
                    {
                        Console.WriteLine($"{x}");
                    }
                }
            }
        }
    }
}