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

        private bool Drop(int floor)
        {
            return floor >= _floor;
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
            int lo = 0;
            int hi = _height;
            int mid;
            while (hi - lo > 1)
            {
                mid = lo + (hi - lo) / 2;
                var broken = Drop(mid);
                if (--tosses < 1)
                    return -1;
                if (broken && --eggs < 1)
                    return -2;
                if (broken)
                    hi = mid;
                else
                    lo = mid;
            }

            return hi;
        }
    }
}
