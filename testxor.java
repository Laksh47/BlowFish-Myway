import java.io.*;
class testxor
{
	public static void main(String z[])throws IOException
	{
		blowfish b=new blowfish();
		System.out.println("\n"+b.xor("9B002AF5","79532303"));
	}
}