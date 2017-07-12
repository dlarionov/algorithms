using System;
using System.Collections.Generic;

namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            var root = new Node
            {
                Key = 15,
                Left = new Node
                {
                    Key = 10,
                    Left = new Node
                    {
                        Key = 7,
                        Right = new Node { Key = 8 }
                    },
                    Right = new Node { Key = 13 }
                },
                Right = new Node
                {
                    Key = 18,
                    Right = new Node
                    {
                        Key = 50,
                        Left = new Node
                        {
                            Key = 20,
                            Left = new Node { Key = 19 }
                        }
                    }
                }
            };

            Console.WriteLine(IsBST(root, int.MinValue, int.MaxValue));
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