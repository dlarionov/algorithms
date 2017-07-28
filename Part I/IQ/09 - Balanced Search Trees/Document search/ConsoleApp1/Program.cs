using System;
using System.Linq;
using System.Collections.Generic;

namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            string[] document = { "A", "B", "C", "D", "A", "A", "B", "B", "C", "C", "D", "D" };
            string[] pattern = { "B", "D" };
            var positions = GetPositions(document, pattern);

            List<int> lengths = new List<int>();
            int x = positions[0][0];
            int y = x;
            int i = 1;
            bool asc = true;
            while (true)
            {
                if (asc)
                {
                    var arr = positions[i++];
                    var j = FindIndexOfTheFirstElementGreatherThanX(arr, x);
                    if (j < 0)
                        break;
                    x = arr[j];

                    if (i >= pattern.Length)
                    {
                        asc = false;
                        i--;
                        y = x; // save last position
                    }
                }
                else
                {
                    var arr = positions[--i];
                    var j = FindIndexOfTheFirstElementLesserThanX(arr, x);
                    x = arr[j];

                    if (i <= 0)
                    {
                        asc = true;
                        i++;
                        lengths.Add(y - x);
                    }
                }
            }

            if (lengths.Count > 0)
                Console.WriteLine(lengths.Min());
            else
                Console.WriteLine("path not found");

            Console.ReadLine();
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

        // TODO it should be tested
        private static int FindIndexOfTheFirstElementLesserThanX(List<int> list, int x)
        {
            int l = -1;
            int r = list.Count;
            while ((r - l) > 1)
            {
                int m = l + ((r - l) / 2);
                if (list[m] > x)
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

        // TODO it should be tested
        private static int FindIndexOfTheFirstElementGreatherThanX(List<int> list, int x)
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