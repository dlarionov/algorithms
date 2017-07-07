using System;
using System.Collections.Generic;

namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            int n = 10000;
            // 1729
            for (int i = 1; i <= n; i++)
            {
                if (IsTaxicab(i))
                    Console.WriteLine(i);
            }
            
            Console.ReadKey();
        }

        public static bool IsTaxicab(int x)
        {
            int m = (int)Math.Floor(Math.Pow(x, (double)1 / 3));
            bool flag = false;
            double a, b;
            for (int i = 1; i < m; i++)
            {
                a = Math.Pow(i, 3);
                for (int j = 1; j < m; j++)
                {
                    b = Math.Pow(j, 3);
                    if (a + b == x)
                    {
                        if (flag)
                            return true;
                        else
                            flag = true;
                    }
                }
            }
            return false;
        }
    }
}