class conversion
{
	int[] xor(int a[],int b[], int m)
	{
		int i,j;
		int c[]=new int[m];
		for(i=0;i<m;i++)
		{
			if(a[i]!=b[i])
				c[i]=1;
			else
				c[i]=0;
		}
		return c;
	}

	String chartobin(char c)
	{
		String bin[]={"0000","0001","0010","0011","0100","0101","0110","0111","1000","1001","1010","1011","1100","1101","1110","1111"};
		int j=0;
		if(c>=48 && c<=57)
			j=(int)c-48;
			//System.out.print("\nc is:"+c+"\tc-48 is:"+(c-48)+"\tj is:"+j);
		else if(c>=65 && c<=70)
			j=(int)c-55;
		else
			return null;
		return bin[j];
	}

	int[] hextobin(String s)
	{
		int i=0,j=0,k=0;
		String temp;
		int a[]=new int[4*s.length()];
		for(i=0;i<s.length();i++)
		{
			temp=chartobin(s.charAt(i));
			if(temp!=null)
			{
				//System.out.print("\nTemp is:"+temp+" ");
				for(j=0;j<4;j++)
				{
					a[k]=(int)temp.charAt(j)-48;
					//System.out.print(a[k]+" ");
					k++;
				}
				//System.out.print("\n");
			}
		}
		return a;
	}

	String hexvalue(String temp)
	{
		String bin[]={"0000","0001","0010","0011","0100","0101","0110","0111","1000","1001","1010","1011","1100","1101","1110","1111"};
		int i;
		String res="";
		char c;
		for(i=0;i<bin.length;i++)
		{
			if(bin[i].equals(temp))
			{
				if(i>=10)
				{
					c=(char)(55+i);
					res=res+c;
					//System.out.print("\nRes is:"+res);
					return res;
				}
				else
				{
					res=res+i;
					//System.out.print("\nRes is:"+res);
					return res;
				}
			}
		}
		return res;
	}

	String bintohex(int a[])
	{
		int i;
		String temp="",hexa="";
		for(i=0;i<a.length;i++)
		{
			temp=temp+a[i];
			//System.out.print("\nTemp is:"+temp);
			if(temp.length()%4==0)
			{
					hexa=hexa+hexvalue(temp);
					temp="";
			}
			//System.out.print("\nHexa is:"+hexa);
		}
		return hexa;
	}
}