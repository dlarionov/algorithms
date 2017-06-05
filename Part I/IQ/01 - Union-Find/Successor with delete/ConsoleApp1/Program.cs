using System;

namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            int n = 100;
            QuickUnion qu = new QuickUnion(n);
            Random rnd = new Random();
            for (int i = 0; i < 3*n; i++)
            {
                qu.Remove(rnd.Next(0, n));
            }

            for (int i = 0; i < n; i++)
            {
                Console.WriteLine($"successor for {i} is {qu.Successor(i)}");
            }

            Console.ReadKey();
        }
    }
}