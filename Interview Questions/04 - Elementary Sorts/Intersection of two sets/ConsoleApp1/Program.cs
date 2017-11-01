using System;
using System.Collections.Generic;

namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            // two arrays of distinct points
            Point[] a = new Point[] {
                    new Point(1, 1),
                    new Point(2, 2),
                    new Point(3, 3),
                    new Point(4, 4),
                    new Point(5, 5)
                };

            Point[] b = new Point[] {
                    new Point(5, 1),
                    new Point(4, 2),
                    new Point(3, 3),
                    new Point(2, 4),
                    new Point(1, 5),
                    new Point(2, 2),
                    new Point(0, 0),
                };

            // sort each for subquadratic time
            InsertionSort(a);
            InsertionSort(b);

            // find intersection for linear time
            Point[] c = Intersection(a, b);

            foreach(var p in c)
                Console.WriteLine(p);

            Console.ReadKey();
        }

        private static void InsertionSort(Point[] a)
        {
            for (int i = 0; i < a.Length; i++)
            {
                for (int j = i; j > 0; j--)
                {
                    if (a[j].CompareTo(a[j - 1]) < 0)
                        Swap(a, j, j - 1);
                    else
                        break;
                }
            }
        }

        private static void Swap(Point[] a, int i, int j)
        {
            Point swap = a[i];
            a[i] = a[j];
            a[j] = swap;
        }

        private static Point[] Intersection(Point[] a, Point[] b)
        {
            int i = 0;
            int j = 0;
            var intersection = new List<Point>();
            while (i < a.Length && j < b.Length)
            {
                int r = a[i].CompareTo(b[j]);
                if (r < 0)
                    i++;
                else if (r > 0)
                    j++;
                else
                {
                    intersection.Add(a[i]);
                    i++;
                    j++;
                }
            }

            return intersection.ToArray();
        }
    }
}