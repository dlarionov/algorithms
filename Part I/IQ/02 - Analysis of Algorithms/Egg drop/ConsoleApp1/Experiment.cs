using System;

namespace ConsoleApp1
{
    public class Experiment
    {
        readonly int _height;
        readonly int _floor;

        public Experiment(int n, int t)
        {
            if (n < 1 || t < 1 || t > n)
                throw new ArgumentException();

            _height = n;
            _floor = t;
        }

        public bool Drop(int floor)
        {
            return floor >= _floor;
        }

        /// <summary>
        /// Search the floor from wich Drop() is true
        /// </summary>
        /// <param name="lo">bottom floor</param>
        /// <param name="hi">top floor</param>
        /// <param name="eggs"></param>
        /// <param name="tosses"></param>
        /// <returns>floor from wich Drop() is true</returns>
        public int BinarySearch(int lo, int hi, ref int eggs, ref int tosses)
        {
            if (lo < 1 || hi < 1 || lo > hi)
                throw new ArgumentOutOfRangeException();
            if (_height < hi - lo + 1)
                throw new ArgumentException();

            lo--; // (lo;hi] "lo minus one" trick
            int mid;
            while (hi - lo > 1)
            {
                mid = lo + (hi - lo) / 2;
                var fail = Drop(mid);
                if (--tosses < 1)
                    return -1;
                if (fail && --eggs < 1)
                    return -2;
                if (fail)
                    hi = mid;
                else
                    lo = mid;
            }

            return hi;
        }

        public int Version0(int tosses)
        {
            int i = 0;
            while (!Drop(++i))
            {
                if (--tosses < 1)
                    return -1;
            }
            return i;
        }

        public int Version1(int eggs, int tosses)
        {
            return BinarySearch(1, _height, ref eggs, ref tosses);
        }

        public int Version2(int eggs, int tosses)
        {
            int x = 1;
            while (x <= _height)
            {
                var fail = Drop(x);
                if (--tosses < 1)
                    return -1;
                if (fail)
                {
                    eggs--;
                    return BinarySearch(x > 1 ? x / 2 + 1 : 1, x, ref eggs, ref tosses);
                }

                x = x * 2;
            }

            return BinarySearch(x / 2 + 1, _height, ref eggs, ref tosses);
        }
    }
}
