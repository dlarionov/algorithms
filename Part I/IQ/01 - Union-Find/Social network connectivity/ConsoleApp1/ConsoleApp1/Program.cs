using System;

namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            int n = 100;
            bool allConnected = false;
            Random rnd = new Random();
            var uf = new WeightedQuickUnion(n);
            for (int i = 0; i < 3 * n; i++)
            {
                int x = rnd.Next(0, n);
                int y = rnd.Next(0, n);

                if (!uf.Connected(x, y))
                {
                    uf.Union(x, y);

                    Console.WriteLine($"({x}, {y}) {uf.Count()}");

                    if (uf.Count() == 1)
                    {
                        allConnected = true;
                        break;
                    }
                }
            }

            Console.WriteLine(allConnected);
            Console.ReadLine();
        }
    }
}