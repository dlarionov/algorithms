using System;

namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            int n = 100;
            var rnd = new Random();
            var bst = new BST();

            for (int i = 0; i < n; i++)
            {
                bst.Add(rnd.Next(n));
            }

            Traverse(bst.Root);

            Console.ReadKey();
        }

        /// <summary>
        /// Joseph M. Morris [Inf. Proc. Letters 9 (1979), 197-200]
        /// https://stackoverflow.com/a/791378/1392696
        /// </summary>
        /// <param name="root"></param>
        public static void Traverse(Node root)
        {
            Node parent = root;
            Node right = null;
            Node curr;

            while (parent != null)
            {
                curr = parent.Left;
                if (curr != null)
                {
                    // search for thread
                    while (curr != right && curr.Right != null)
                        curr = curr.Right;

                    if (curr != right)
                    {
                        // insert thread
                        curr.Right = parent;
                        Console.WriteLine(parent);
                        parent = parent.Left;
                        continue;
                    }
                    else
                        // remove thread, left subtree of P already traversed
                        // this restores the node to original state
                        curr.Right = null;
                }
                else
                    Console.WriteLine(parent);

                right = parent;
                parent = parent.Right;
            }
        }

        public static bool IsBST(Node node, int min = int.MinValue, int max = int.MaxValue)
        {
            if (node == null)
                return true;

            if (node.Key >= max || node.Key <= min)
                return false;

            return IsBST(node.Left, min, node.Key) && IsBST(node.Right, node.Key, max);
        }
    }
}