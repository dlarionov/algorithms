using System;

namespace ConsoleApp1
{
    public class Node<T> where T : IComparable<T>
    {
        public T Key { get; set; }
        public Node<T> Left { get; set; }
        public Node<T> Right { get; set; }
        public bool IsRed { get; set; }

        public override string ToString()
        {
            return $"{Key}";
        }
    }
}
