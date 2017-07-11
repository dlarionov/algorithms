using System;

namespace ConsoleApp1
{
    public class SumOfCubes : IComparable<SumOfCubes>
    {
        public int A { get; private set; }
        public int B { get; private set; }
        public int Result { get; private set; }

        public SumOfCubes(int a, int b)
        {
            A = a;
            B = b;
            Result = a * a * a + b * b * b;
        }

        public int CompareTo(SumOfCubes other)
        {
            return Result.CompareTo(other.Result);
        }
    }
}
