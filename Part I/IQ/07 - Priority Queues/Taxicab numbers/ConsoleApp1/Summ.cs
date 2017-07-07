using System;

namespace ConsoleApp1
{
    public class Summ : IComparable<Summ>
    {
        public double A { get; set; }
        public double B { get; set; }
        public double Result { get { return A + B; } }

        public int CompareTo(Summ that)
        {
            return Result.CompareTo(that.Result);
        }
    }
}
