using System;
using System.Collections.Generic;
using System.Text;

namespace ConsoleApp1
{
    public class MaxPQ
    {
        int[] _arr;
        int _size;

        public MaxPQ(int campacity)
        {
            _arr = new int[campacity];
        }

        private void Swap(int i, int j)
        {
            int x = _arr[i];
            _arr[i] = _arr[j];
            _arr[j] = x;
        }

        private void Up(int i)
        {
            while (i > 1 && _arr[i] > _arr[i / 2])
            {
                Swap(i, i / 2);
                i = i / 2;
            }
        }

        public void Insert(int x)
        {
            if (_size >= _arr.Length)
                throw new OverflowException();

            _arr[_size] = x;
            Up(_size);
            _size++;
        }

        private void Down(int i)
        {
            
        }
    }
}
