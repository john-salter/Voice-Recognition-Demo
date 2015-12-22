package voiceRecognitionDemo;


public class nextPowerOf2 {
	public static int main(final int a)
    {
        int b = 1;
        while (b < a)
        {
            b = b << 1;
        }
        return b;
    }
}
