using System;

namespace UsefulThings
{
    public class Node<TKey, TValue> where TKey : IComparable<TKey>
    {
        public TKey Key { get; set; }
        public TValue Value { get; set; }
        public Node<TKey, TValue> Left { get; set; }
        public Node<TKey, TValue> Right { get; set; }
        public bool IsRed { get; set; }
    }

    /// <summary>
    /// Red-black binary search tree
    /// </summary>
    /// <typeparam name="TKey">Key</typeparam>
    /// <typeparam name="TValue">Value</typeparam>
    public class Tree<TKey, TValue> where TKey : IComparable<TKey>
    {
        public Node<TKey, TValue> Root { get; private set; }

        public TValue Get(TKey key)
        {
            var x = Root;
            while (x != null)
            {
                int cmp = x.Key.CompareTo(key);
                if (cmp < 0) x = x.Right;
                else if (cmp > 0) x = x.Left;
                else return x.Value;
            }
            return default(TValue);
        }

        public void Add(TKey key, TValue value) { Root = Add(Root, key, value); }

        private static Node<TKey, TValue> Add(Node<TKey, TValue> x, TKey key, TValue value)
        {
            if (x == null)
                return new Node<TKey, TValue> { Key = key, Value = value, IsRed = true };

            int cmp = x.Key.CompareTo(key);
            if (cmp < 0) x.Right = Add(x.Right, key, value);
            else if (cmp > 0) x.Left = Add(x.Left, key, value);
            else x.Value = value;

            x = Balance(x);

            return x;
        }

        private static bool IsRed(Node<TKey, TValue> x)
        {
            if (x == null)
                return false;
            return x.IsRed;
        }

        private static Node<TKey, TValue> Balance(Node<TKey, TValue> x)
        {
            if (IsRed(x.Right) && !IsRed(x.Left)) x = RotateLeft(x);
            if (IsRed(x.Left) && IsRed(x.Left.Left)) x = RotateRight(x);
            if (IsRed(x.Left) && IsRed(x.Right)) FlipColors(x);
            return x;
        }

        private static Node<TKey, TValue> RotateLeft(Node<TKey, TValue> h)
        {
            // assert isRed(h.right);
            var x = h.Right;
            h.Right = x.Left;
            x.Left = h;
            x.IsRed = h.IsRed;
            h.IsRed = true;
            return x;
        }

        private static Node<TKey, TValue> RotateRight(Node<TKey, TValue> h)
        {
            // assert isRed(h.left);
            var x = h.Left;
            h.Left = x.Right;
            x.Right = h;
            x.IsRed = h.IsRed;
            h.IsRed = true;
            return x;
        }

        private static void FlipColors(Node<TKey, TValue> h)
        {
            //assert !isRed(h);
            //assert isRed(h.left);
            //assert isRed(h.right);
            h.IsRed = true;
            h.Left.IsRed = false;
            h.Right.IsRed = false;
        }
    }
}
