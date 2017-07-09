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
            // O(n^2) time and O(n^2) space
            var list = new List<int>();
            for (int i = 1; i <= n; i++)
            {
                for (int j = i; j <= n; j++)
                {
                    int x = (int)Math.Pow(i, 3) + (int)Math.Pow(j, 3);
                    list.Add(x);
                }
            }

            // O(n^2*log(n^2)) time
            list.Sort();

            // O(n^2)
            int prev = -1;
            foreach (var next in list)
            {
                if (prev == next)
                {
                    Console.WriteLine(prev);
                }
                prev = next;
            }
        }

        public static void Taxicab2(int n)
        {
            // O(n^2) time and O(n^2) space
            var set = new HashSet<int>();
            for (int i = 1; i <= n; i++)
            {
                for (int j = i; j <= n; j++)
                {
                    int x = (int)Math.Pow(i, 3) + (int)Math.Pow(j, 3);
                    if (!set.Add(x))
                    {
                        Console.WriteLine(x);
                    }
                }
            }
        }

        public static void Taxicab3(int n)
        {
            // O(n^2*log(2*n)) time and O(n) space
            var pq = new MinPQ<int>();
            for (int i = 1; i <= n; i++)
            {
                int x = (int)Math.Pow(i, 3);
                pq.Push(2 * x);
            }

            int prev = -1;
            while (pq.Count > 0)
            {
                int next = pq.Pop();
                if (next == prev)
                    Console.WriteLine(next);
                else
                {

                }

                // TODO
            }
        }
    }
}