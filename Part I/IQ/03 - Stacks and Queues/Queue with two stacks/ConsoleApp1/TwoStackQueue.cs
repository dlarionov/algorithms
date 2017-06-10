using System;
using System.Collections.Generic;

namespace ConsoleApp1
{
    public class TwoStackQueue<T>
    {
        private Stack<T> _left;
        private Stack<T> _right;

        public TwoStackQueue()
        {
            _left = new Stack<T>();
            _right = new Stack<T>();
        }

        public int size()
        {
            return _left.Count + _right.Count;
        }

        public void enqueue(T x)
        {
            _right.Push(x);
        }

        public T dequeue()
        {
            if (size() < 1)
                throw new IndexOutOfRangeException();

            if (_left.Count < 1)
                while (_right.Count > 0)
                    _left.Push(_right.Pop());

            return _left.Pop();
        }
    }
}
