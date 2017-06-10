using System.Collections.Generic;

namespace ConsoleApp1
{
    public class MaxStack
    {
        private Stack<int> _arr;
        private Stack<int> _max;

        public MaxStack()
        {
            _arr = new Stack<int>();
            _max = new Stack<int>();
        }

        public int Size()
        {
            return _arr.Count;
        }

        public int Max()
        {
            return _max.Peek();
        }

        public void Push(int x)
        {
            _arr.Push(x);
            if (_max.Count > 0)
            {
                int last = _max.Peek();
                _max.Push(x > last ? x : last);
            }
            else
                _max.Push(x);
        }

        public int Peek()
        {
            return _arr.Peek();
        }

        public int Pop()
        {
            _max.Pop();
            return _arr.Pop();
        }
    }
}
