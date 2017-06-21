using System;

namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            int[] n = { 1, 3, 5, 5, 7, 31 };
            int[] m = { 0, 0, 1, 1, 1, 2 };
            int k = FindKth(n, m, 4);
            Console.WriteLine(k);
            Console.ReadKey();
        }

        /// <summary>
        /// 
        /// </summary>
        /// <param name="n"></param>
        /// <param name="m"></param>
        /// <param name="k">starts from zero</param>
        /// <returns></returns>
        public static int FindKth(int[] n, int[] m, int k)
        {
            int lo = -1;
            int hi = n.Length - 1;
            int mid;

            while (hi - lo > 1)
            {
                mid = lo + (hi - lo) / 2;

                if (k - mid < 0)
                    lo = mid;
                else if (n[mid] >= m[k-mid])
                    hi = mid;
                else
                    lo = mid;
            }

            return n[hi];
        }
    }
}