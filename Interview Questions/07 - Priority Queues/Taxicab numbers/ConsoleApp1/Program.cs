using System;
using System.Collections.Generic;

namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            int n = 42;

            Taxicab1(n);
            Taxicab2(n);
            Taxicab3(n);

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
                    list.Add(i * i * i + j * j * j);
                }
            }

            // O(n^2*log(n^2)) time
            list.Sort();

            // O(n^2) time
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
                    int x = i * i * i + j * j * j;
                    if (!set.Add(x))
                    {
                        Console.WriteLine(x);
                    }
                }
            }
        }

        public static void Taxicab3(int n)
        {
            // O(n) time and O(n) space
            var pq = new MinPQ<SumOfCubes>();
            for (int i = 1; i <= n; i++)
            {
                pq.Push(new SumOfCubes(i, i));
            }

            // O(n^2*logn) time
            var sentinel = new SumOfCubes(0, 0);
            while (pq.Count > 0)
            {
                var current = pq.Pop();

                if (current.Result == sentinel.Result)
                    Console.WriteLine($"{sentinel.A}^3+{sentinel.B}^3 = {current.A}^3+{current.B}^3 = {current.Result}");

                if (current.B <= n)
                    pq.Push(new SumOfCubes(current.A, current.B + 1));

                sentinel = current;
            }
        }
    }
}