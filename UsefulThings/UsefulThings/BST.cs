namespace UsefulThings
{
    public class BST
    {
        public class Node
        {
            public int Key { get; set; }
            public string Value { get; set; }
            public Node Left { get; set; }
            public Node Right { get; set; }
            public int Size { get; set; }
        }

        public Node Root { get; private set; }

        public string Get(int key)
        {
            var x = Root;
            while (x != null)
            {
                if (key > x.Key) x = x.Right;
                else if (key < x.Key) x = x.Left;
                else return x.Value;
            }
            return null;
        }

        public void Add(int key, string value)
        {
            Root = Add(Root, key, value);
        }

        private Node Add(Node x, int key, string value)
        {
            if (x == null)
                return new Node { Key = key, Value = value };

            if (key > x.Key)
                x.Right = Add(x.Right, key, value);
            else if (key < x.Key)
                x.Left = Add(x.Left, key, value);
            else
                x.Value = value;

            x.Size = 1 + Size(x.Left) + Size(x.Right);

            return x;
        }

        public int Size()
        {
            return Size(Root);
        }

        private int Size(Node x)
        {
            if (x == null)
                return 0;
            return x.Size;
        }
    }
}
