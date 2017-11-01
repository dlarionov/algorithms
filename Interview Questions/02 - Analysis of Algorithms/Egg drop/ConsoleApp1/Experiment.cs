using System;

namespace ConsoleApp1
{
    public class Experiment
    {
        readonly int _height;
        readonly int _floor;

        /// <summary>
        /// Suppose that you have an n-story building (with floors 1 through n) and plenty of eggs.
        /// </summary>
        /// <param name="n"></param>
        /// <param name="t"></param>
        public Experiment(int n, int t)
        {
            if (n < 1 || t < 1 || t > n)
                throw new ArgumentException();

            _height = n;
            _floor = t;
        }

        /// <summary>
        /// An egg breaks if it is dropped from floor T or higher and does not break otherwise.
        /// </summary>
        /// <param name="floor"></param>
        /// <returns></returns>
        public bool Drop(int floor)
        {
            return floor >= _floor;
        }

        /// <summary>
        /// 1 egg, ≤T tosses.
        /// </summary>
        /// <param name="tosses"></param>
        /// <returns></returns>
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

        /// <summary>
        /// ∼1lgn eggs and ∼1lgn tosses.
        /// </summary>
        /// <param name="eggs"></param>
        /// <param name="tosses"></param>
        /// <returns></returns>
        public int Version1(int eggs, int tosses)
        {
            return BinarySearch(1, _height, ref eggs, ref tosses);
        }

        /// <summary>
        /// ∼lgT eggs and ∼2lgT tosses.
        /// </summary>
        /// <param name="eggs"></param>
        /// <param name="tosses"></param>
        /// <returns></returns>
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

        /// <summary>
        /// 2 eggs and ∼2√n tosses.
        /// </summary>
        /// <param name="tosses"></param>
        /// <returns></returns>
        public int Version3(int tosses)
        {
            int sqrt = (int)Math.Ceiling(Math.Sqrt(_height));
            int x = sqrt;
            while (!Drop(x))
            {
                if (--tosses < 1)
                    return -1;
                x = x + sqrt;
            }

            x = x - sqrt;
            while (!Drop(++x))
            {
                if (--tosses < 1)
                    return -1;
            }

            return x;
        }

        /// <summary>
        /// 2 eggs and ≤c√T tosses for some fixed constant c.
        /// </summary>
        /// <param name="tosses"></param>
        /// <returns></returns>
        public int Version4(int tosses)
        {
            int sqrt = (int)Math.Ceiling(Math.Sqrt(2 * _height));
            int x = sqrt;
            while (!Drop(x))
            {
                if (--tosses < 1)
                    return -1;
                x = x + --sqrt;
            }

            x = x - sqrt - 1;
            while (!Drop(++x))
            {
                if (--tosses < 1)
                    return -1;
            }

            return x;
        }
    }
}
