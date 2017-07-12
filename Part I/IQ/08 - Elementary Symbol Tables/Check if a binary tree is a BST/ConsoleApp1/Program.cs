using System;

namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            var tree = new Node(15,
                left: new Node(10,
                    left: new Node(7, right: new Node(8)),
                    right: new Node(13)),
                right: new Node(18,
                    right: new Node(42,
                        left: new Node(20, left: new Node(19)))));

            Console.WriteLine(IsBST(tree, int.MinValue, int.MaxValue));
            Console.ReadKey();
        }

        public static bool IsBST(Node node, int min, int max)
        {
            if (node == null)
                return true;

            if (node.Key >= max || node.Key <= min)
                return false;

            return IsBST(node.Left, min, node.Key) && IsBST(node.Right, node.Key, max);
        }
    }
}