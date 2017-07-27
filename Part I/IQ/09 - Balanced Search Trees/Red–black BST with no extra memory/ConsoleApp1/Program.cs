using System;

namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            var bst = new BST<int>();

            for (int i = 0; i < 100; i++)
            {
                bst.Put(i);
            }

            Console.WriteLine(bst.Get(42));
            Console.ReadLine();
        }
    }
}