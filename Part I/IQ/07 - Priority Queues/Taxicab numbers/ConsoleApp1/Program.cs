using System;
using System.Linq;
using System.Collections.Generic;

namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            int n = 1000000;

            foreach (var i in FindTaxicabNumbers(n))
                Console.WriteLine(i);

            Console.ReadKey();
        }

        public static IEnumerable<int> FindTaxicabNumbers(int n)
        {
            // m is cubic root of n
            int m = (int)Math.Floor(Math.Pow(n, (double)1 / 3));

            // O(m^2) time and O(m^2) space
            var list = new List<int>();
            for (int i = 1; i <= m; i++)
            {
                for (int j = i; j <= m; j++)
                {
                    list.Add((int)Math.Pow(i, 3) + (int)Math.Pow(j, 3));
                }
            }

            // we could sort the list by O(m^2*log(m^2)) time and find duplicates by O(m^2) time
            // but is is better use System.Linq and GroupBy that gives O(m^2) time in summary
            return list
                .GroupBy(x => x)
                .Where(x => x.Count() > 1)
                .Select(x => x.Key);
        }
    }
}