using System;

namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            var uf = new WeightedQuickUnion(10);
            uf.Union(0, 4);
            uf.Union(2, 5);
            uf.Union(2, 6);
            uf.Union(4, 2);
            uf.Union(1, 7);
            uf.Union(7, 3);
            uf.Union(3, 8);
            uf.Union(9, 3);

            for (int i = 0; i < 10; i++)
            {
                Console.WriteLine($"max for {i} is {uf.FindMax(i)}");
            }

            Console.ReadLine();
        }
    }
}