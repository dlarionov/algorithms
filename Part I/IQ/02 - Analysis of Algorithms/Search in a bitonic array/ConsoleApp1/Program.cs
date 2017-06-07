using System;

namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            int n = 42;
            int[] arr = new int[n];
            for (int i = 0; i < n; i++)
            {
                if (i % 2 == 0)
                    arr[i / 2] = i;
                else
                    arr[n - 1 - (i / 2)] = i;
            }
            foreach (var i in arr)
                Console.Write($"{i} ");
            Console.WriteLine();

            Console.WriteLine("x\t n\t 3logn\t 2logn2");
            for (int i = -1; i <= n; i++)
            {
                Console.WriteLine($"{i}\t{FindIndexLinear(arr, i)}\t{FindIndex3Logarithmic(arr, i)}\t{FindIndex2Logarithmic(arr, i)}");
            }

            Console.ReadKey();
        }

        private static int FindIndexLinear(int[] arr, int x)
        {
            for (int i = 0; i < arr.Length; i++)
            {
                if (arr[i] == x)
                    return i;
            }
            return -1;
        }

        private static int FindIndex3Logarithmic(int[] arr, int x)
        {
            int lo = -1;
            int hi = arr.Length;
            
            while (hi - lo > 1)
            {
                int m1 = lo + ((hi - lo) / 2); // 1/2
                int m2 = m1 + ((hi - m1) / 2); // 3/4

                if (arr[m1] < arr[m2])
                    lo = m1;
                else
                    hi = m2;
            }

            return hi < arr.Length ? hi : -1;
        }

        private static int FindIndex2Logarithmic(int[] arr, int x)
        {
            // TODO

            return -1;
        }
    }
}