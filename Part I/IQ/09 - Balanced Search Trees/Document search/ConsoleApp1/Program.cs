using System;
using System.Collections.Generic;

namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            string[] document = { "A", "B", "C", "D", "A", "A", "B", "B", "C", "C", "D", "D" };
            string[] pattern = { "B", "D" };

            // TODO don't store positions for duplicate queries
            var positions = new List<List<int>>();
            for (int i = 0; i < pattern.Length; i++)
            {
                var query = pattern[i];
                var list = new List<int>();
                for (int j = 0; i < document.Length; j++)
                {
                    var word = document[j];
                    if (query == word)
                        list.Add(j);
                }
                positions.Add(list);
            }

            // TODO scan positions
            int index = 0;
            int[] result = new int[pattern.Length];
            //while(index < pattern.Length)
            //{
            //    arr[index] = positions[index]
            //}

            Console.ReadLine();
        }
    }
}