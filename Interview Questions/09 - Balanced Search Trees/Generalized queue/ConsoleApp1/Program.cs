using System;
using System.Collections.Generic;

namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            var data = new GeneralizedQueue<char>();
            for (char c = 'A'; c <= 'Z'; c++)
            {
                data.AddLast(c);
            }

            data.RemoveFirst();
            data.RemoveFirst();

            int x = 7;
            Console.WriteLine(data.Get(x));
            data.Remove(x);
            Console.WriteLine(data.Get(x));
            data.Remove(x);
            Console.WriteLine(data.Get(x));

            Console.ReadKey();
        }
    }
}