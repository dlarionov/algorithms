using System;

namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            int[] a = new int[] { 5, 4, 3, 2, 1, 1 };
            int[] b = new int[] { 2, 1, 1, 5, 4, 3 };

            InsertionSort(a);
            InsertionSort(b);

            bool eq = a.Length == b.Length;
            if (eq)
            {
                for (int i = 0; i < a.Length; i++)
                {
                    if (a[i] != b[i])
                    {
                        eq = false;
                        break;
                    }
                }
            }

            Console.WriteLine(eq);
            Console.ReadKey();
        }

        private static void InsertionSort(int[] a)
        {
            for (int i = 0; i < a.Length; i++)
            {
                for (int j = i; j > 0; j--)
                {
                    if (a[j] < a[j - 1])
                        Swap(a, j, j - 1);
                    else
                        break;
                }
            }
        }

        private static void Swap(int[] a, int i, int j)
        {
            int swap = a[i];
            a[i] = a[j];
            a[j] = swap;
        }
    }
}