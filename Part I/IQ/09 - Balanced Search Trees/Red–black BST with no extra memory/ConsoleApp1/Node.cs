using System;

namespace ConsoleApp1
{
    public class Node<T> where T : IComparable<T>
    {
        public T Key { get; set; }
        public Node<T> Left { get; set; }
        public Node<T> Right { get; set; }

        // Instead of using boolean property we could define
        // a red node as the one who has a child in the wrong place.
        // If we go this way all leaf nodes are guaranteed to to be black
        // and we should swap parent with his sibling when inserting a new node
        public bool IsRed { get; set; }
    }
}
