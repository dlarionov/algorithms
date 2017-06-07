using System;
using System.Collections.Generic;

namespace ConsoleApp1
{
    public class BitonicArray<T> where T : IComparable<T>
    {
        readonly T[] _bitonic;

        public BitonicArray(T[] unsortedArray)
        {
            int n = unsortedArray.Length;
            _bitonic = new T[unsortedArray.Length];
            Array.Copy(unsortedArray, _bitonic, n);
            int mid = new Random().Next(0, n);
            Array.Sort(_bitonic, 0, mid);
            Array.Sort(_bitonic, mid, n - mid, new ReverseComparer<T>());
        }

        public T[] ToArray()
        {
            return _bitonic;
        }

        public int FindLinear(T value)
        {
            for (int i = 0; i < _bitonic.Length; i++)
            {
                if (value.CompareTo(_bitonic[i]) == 0)
                    return i;
            }
            return -1;
        }

        /// <summary>
        /// 3*log(n)
        /// </summary>
        public int FindLogarithmicBad(T value)
        {
            int i = FindMax(_bitonic);
            int r = BinarySearch(_bitonic, 0, i, value);
            return r < 0
                ? BinarySearch(_bitonic, i, _bitonic.Length - i, value, new ReverseComparer<T>())
                : r;
        }

        // http://algs4.cs.princeton.edu/14analysis/BitonicMax.java.html
        private static int FindMax(T[] arr)
        {
            // l is the left index of the array minus one
            // we never call arr[-1] so there is nothing wrong
            int lo = -1;
            int hi = arr.Length - 1;
            int mid;
            while (hi - lo > 1) // lo < mid < hi
            {
                mid = lo + ((hi - lo) / 2);
                if (arr[mid].CompareTo(arr[mid + 1]) < 0)
                    lo = mid;
                else
                    hi = mid;
            }
            return hi;
        }

        // https://github.com/dotnet/corefx/blob/6dd451f51451a7d0ceea6104b51bd17005e9a0e6/src/System.Runtime.Extensions/src/System/Collections/ArrayList.cs
        private static int BinarySearch(T[] arr, int index, int count, T value, IComparer<T> comparer = null)
        {
            if (index < 0 || count < 0)
                throw new ArgumentOutOfRangeException();
            if (arr.Length < index  + count)
                throw new ArgumentException();

            if (comparer == null)
                comparer = Comparer<T>.Default;

            int lo = index;
            int hi = index + count - 1;
            int mid;
            while (lo <= hi)
            {
                mid = lo + ((hi - lo) / 2);
                int r = comparer.Compare(value, arr[mid]);
                if (r == 0)
                    return mid;
                if (r < 0)
                    hi = mid - 1;
                else
                    lo = mid + 1;
            }
            // return bitwise complement of the first element greater than value.
            // Since hi is less than lo now, ~lo is the correct item.
            return ~lo;
        }

        /// <summary>
        /// 2*log(n)
        /// </summary>
        public int FindLogarithmicGood(T value)
        {
            // TODO

            return -1;
        }
    }
}
