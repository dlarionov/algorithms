using System;
using System.Collections.Generic;
using System.Text;

namespace ConsoleApp1
{
    public class ThreeColor
    {
        private int[] _arr;
        private int _swap;
        private int _color;

        public ThreeColor(int[] arr)
        {
            int n = arr.Length;
            _arr = new int[n];
            for (int i = 0; i < n; i++)
            {
                _arr[i] = arr[i];
            }

            int lo = 0; // lo is first one index
            int hi = 0; // hi is first two index
            
            for (int i = 0; i < n; i++)
            {
                switch(Color(_arr[i]))
                {
                    case 0:
                        lo++;
                        break;
                    case 1:
                        // Swap(mid, lo);
                        break;
                    case 2:
                        // Swap(mid, hi);
                        hi++;
                        break;
                    default:
                        throw new NotImplementedException();
                }
            }
        }

        public bool Test()
        {
            for (int i = 1; i < _arr.Length; i++)
            {
                if (_arr[i] < _arr[i - 1])
                    return false;
            }

            return _arr.Length >= _swap && _arr.Length >= _color;
        }

        private void Swap(int i, int j)
        {
            _swap++;
            int swap = _arr[i];
            _arr[i] = _arr[j];
            _arr[j] = swap;
        }

        private int Color(int i)
        {
            _color++;
            return i;
        }
    }
}
