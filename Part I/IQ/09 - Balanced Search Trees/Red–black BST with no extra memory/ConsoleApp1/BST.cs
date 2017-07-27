using System;

namespace ConsoleApp1
{
    /// <summary>
    /// Red-black binary search tree
    /// </summary>
    public class BST<T> where T : IComparable<T>
    {
        public Node<T> Root { get; private set; }

        public Node<T> Get(T key)
        {
            var x = Root;
            while (x != null)
            {
                if (x.Compare(key) > 0) x = x.Right;
                else if (x.Compare(key) < 0) x = x.Left;
                else return x;
            }
            return null;
        }

        public void Put(T key)
        {
            Root = Put(Root, key);
        }

        private Node<T> Put(Node<T> h, T key)
        {
            if (h == null)
                return new Node<T> { Key = key }; // TODO it shoul be red!!!

            if (h.Compare(key) > 0)
                h.Right = Put(h.Right, key);
            else if (h.Compare(key) < 0)
                h.Left = Put(h.Left, key);
            else { }

            h = Balance(h);

            return h;
        }

        private Node<T> Balance(Node<T> h)
        {
            if (Node<T>.IsRed(h.Right) && !Node<T>.IsRed(h.Left))
                h = RotateLeft(h);
            if (Node<T>.IsRed(h.Left) && Node<T>.IsRed(h.Left.Left))
                h = RotateRight(h);
            if (Node<T>.IsRed(h.Left) && Node<T>.IsRed(h.Right))
                FlipColors(h);
            return h;
        }

        private Node<T> RotateLeft(Node<T> h)
        {
            // assert isRed(h.right);
            var x = h.Right;
            h.Right = x.Left;
            x.Left = h;

            if (!Node<T>.IsRed(h))
            {
                h.Swap();
                x.Swap();
            }

            return x;
        }

        private Node<T> RotateRight(Node<T> h)
        {
            // assert isRed(h.left);
            var x = h.Left;
            h.Left = x.Right;
            x.Right = h;

            if (!Node<T>.IsRed(h))
            {
                h.Swap();
                x.Swap();
            }

            return x;
        }

        private void FlipColors(Node<T> h)
        {
            //assert !isRed(h);
            //assert isRed(h.left);
            //assert isRed(h.right);
            h.Swap();
            h.Left.Swap();
            h.Right.Swap();
        }
    }
}
