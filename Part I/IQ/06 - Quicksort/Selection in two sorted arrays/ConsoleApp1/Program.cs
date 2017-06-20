using System;

namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            int[] n = { 1, 3, 5, 5, 7, 31 };
            int[] m = { 0, 0, 1, 1, 1, 2, 4, 7, 7, 42 };
            int k = FindKth(n, m, 6);
            Console.WriteLine(k);
            Console.ReadKey();
        }

        public static int FindKth(int[]n, int[]m, int k)
        {
            bool isKth(int i) => n[i] >= m[k - i];

            int lo = 0;
            int hi = n.Length - 1;
            int mid = lo + (hi - lo) / 2;
            while (hi - lo > 1) // todo
            {
                if (isKth(mid))
                    lo++;
                else
                    hi++;
            }

            return -1;
        }


    }
}