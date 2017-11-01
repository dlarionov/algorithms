using System;

namespace ConsoleApp1
{
    public class Point : IComparable<Point>
    {
        public int X { get; private set; }
        public int Y { get; private set; }

        public Point(int x, int y)
        {
            this.X = x;
            this.Y = y;
        }

        public int CompareTo(Point that)
        {
            int r;
            if (this.Y < that.Y) r = -1;
            else if (this.Y > that.Y) r = 1;
            else if (this.X < that.X) r = -1;
            else if (this.X > that.X) r = 1;
            else r = 0;
            return r;
        }

        public override string ToString()
        {
            return $"({X}, {Y})";
        }
    }
}
