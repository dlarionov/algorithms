using System;
using System.Collections.Generic;
using System.Text;

namespace ConsoleApp1
{
    public class BST
    {
        public Node Root { get; private set; }

        public BST()
        {
        }

        public bool HasKey(int key)
        {
            var x = Root;
            while(x != null)
            {
                if (key > x.Key) x = x.Right;
                else if (key < x.Key) x = x.Left;
                else return true;
            }
            return false;
        }

        public void Add(int key)
        {
            Root = Add(Root, key);
        }

        private Node Add(Node x, int key)
        {
            if (x == null)
                return new Node(key);
            if (key > x.Key)
                x.Right = Add(x.Right, key);
            else if (key < x.Key)
                x.Left = Add(x.Left, key);
            return x;
        }
    }
}
