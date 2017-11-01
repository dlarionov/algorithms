namespace ConsoleApp1
{
    public class Node
    {
        public int Key { get; set; }
        public Node Left { get; set; }
        public Node Right { get; set; }

        public Node(int key, Node left = null, Node right = null)
        {
            Key = key;
            Left = left;
            Right = right;
        }
    }
}
