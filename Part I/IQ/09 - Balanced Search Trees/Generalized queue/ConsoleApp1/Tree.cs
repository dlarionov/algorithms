using System;

namespace ConsoleApp1
{
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

        public void DeleteMin() { Root = DeleteMin(Root); }

        public void Delete(TKey key) { Root = Delete(Root, key); }

        private static Node<TKey, TValue> Delete(Node<TKey, TValue> x, TKey key)
        {
            if (x == null)
                return null;

            int cmp = x.Key.CompareTo(key);
            if (cmp < 0) x.Right = Delete(x.Right, key);
            else if (cmp > 0) x.Left = Delete(x.Left, key);
            else
            {
                if (x.Right == null) return x.Left;
                if (x.Left == null) return x.Right;
                var t = x;
                x = Min(t.Right);
                x.Right = DeleteMin(t.Right);
                x.Left = t.Left;
            }

            x = Balance(x);

            return x;
        }

        private static Node<TKey, TValue> Min(Node<TKey, TValue> x)
        {
            if (x.Left == null)
                return x;
            return Min(x.Left);
        }

        private static Node<TKey, TValue> DeleteMin(Node<TKey, TValue> x)
        {
            if (x.Left == null)
                return x.Right;
            x.Left = DeleteMin(x.Left);
            return x;
        }

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
