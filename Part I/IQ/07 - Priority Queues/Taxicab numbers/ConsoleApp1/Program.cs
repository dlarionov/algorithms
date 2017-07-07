using System;
using System.Linq;
using System.Collections.Generic;

namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            int n = 100000;

            foreach (var i in TaxicabNumbers1(n))
                Console.WriteLine(i);

            Console.ReadKey();
        }

        public static IEnumerable<int> TaxicabNumbers1(int n)
        {
            var list = new List<int>();

            int max = (int)Math.Floor(Math.Pow(n, (double)1 / 3));
            for (int i = 1; i <= max; i++)
            {
                for (int j = i + 1; j <= max; j++)
                {
                    list.Add((int)Math.Pow(i, 3) + (int)Math.Pow(j, 3));
                }
            }

            return list
                .GroupBy(x => x)
                .Where(x => x.Count() > 1)
                .Select(x => x.Key);
        }
    }
}