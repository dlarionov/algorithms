using System;

namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            int n = 100;
            var rnd = new Random();
            var bst = new BST<int>();

            for (int i = 0; i < n; i++)
            {
                bst.Put(i);
            }

            var node = bst.Get(42);
        }
    }
}