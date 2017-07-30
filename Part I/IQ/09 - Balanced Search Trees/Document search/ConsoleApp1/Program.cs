using System;
using System.Collections.Generic;

namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            string[] document = { "AA", "A", "B", "C", "D", "A", "A", "B", "B", "C", "C", "D", "A", "C", "D" };
            string[] pattern = { "A", "C", "D" };

            List<List<int>> positions = GetPositions(document, pattern);

            int i = 0;
            bool asc = true; // direction of the scan (forward or backward)
            int x = positions[0][0]; // position of the first query word
            int y = 0; // variable for storing position of the last query word
            int len = 0; // variable for storing difference between minimum y and maximum x

            while (true)
            {
                if (asc)
                {
                    var arr = positions[++i];
                    var j = FindIndexOfTheFirstElementGreatherThanX(arr, x);
                    if (j < 0)
                        break;
                    x = arr[j];

                    if (i == pattern.Length - 1)
                    {
                        asc = false;
                        y = x; // save last position
                    }
                }
                else
                {
                    var arr = positions[--i];
                    var j = FindIndexOfTheLastElementLesserThanX(arr, x);
                    x = arr[j];

                    if (i == 0)
                    {
                        asc = true;
                        if (len == 0 || len > y - x)
                            len = y - x;

                        // take next element and scan forward again
                        if (++j == arr.Count)
                            break;
                        x = arr[j];
                    }
                }
            }

            Console.WriteLine(len);
            Console.ReadKey();
        }

        private static List<List<int>> GetPositions(string[] document, string[] pattern)
        {
            var positions = new List<List<int>>();
            for (int i = 0; i < pattern.Length; i++)
            {
                var query = pattern[i];
                var list = new List<int>();
                for (int j = 0; j < document.Length; j++)
                {
                    var word = document[j];
                    if (query == word)
                        list.Add(j);
                }
                positions.Add(list);
            }
            return positions;
        }

        private static int FindIndexOfTheLastElementLesserThanX(IList<int> list, int x)
        {
            int l = -1;
            int r = list.Count;
            while ((r - l) > 1)
            {
                int m = l + ((r - l) / 2);
                if (list[m] > x)
                {
                    r = m;
                }
                else
                {
                    l = m;
                }
            }

            return l > -1 ? l : -1;
        }

        private static int FindIndexOfTheFirstElementGreatherThanX(IList<int> list, int x)
        {
            int l = -1;
            int r = list.Count;
            while ((r - l) > 1)
            {
                int m = l + ((r - l) / 2);
                if (list[m] < x)
                {
                    l = m;
                }
                else
                {
                    r = m;
                }
            }

            return r < list.Count ? r : -1;
        }
    }
}