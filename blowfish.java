class blowfish
{
	void karray(String k[],String key)
	{
		int i=0,j=0,start=0,end=8;
		while(i<14)
		{
			k[i]=new String(key.substring(start,end));
			start=start+8;
			end=end+8;
			if(start>=key.length())
			{
				start=0;
				end=8;
			}
			i++;
		}
	}

	void pupdate(String p[],String k[])
	{
		int i=0,j=0;
		while(i<18)
		{
			p[i]=xor(p[i],k[j]);
			j++;
			if(j==14)
				j=0;
			i++;
		}
	}

	void supdate(String p[],String s[][])
	{
		int i=0,j=0,k=0;
		String cipher="0000000000000000";
		while(i<9)
		{
			cipher=encrypt(p,s,cipher);
			//System.out.print("\nCipher["+i+"]:"+cipher);
			p[j]=cipher.substring(0,8);
			p[j+1]=cipher.substring(8,16);
			j=j+2;
			i++;
		}
		i=0;
		j=0;
		while(i<512)
		{
			cipher=encrypt(p,s,cipher);
			//System.out.print("\nCipher["+i+"]:"+cipher);
			s[j][k]=cipher.substring(0,8);
			s[j][k+1]=cipher.substring(8,16);
			k=k+2;
			if(k>254)
			{
				j++;
				k=0;
			}
			i++;
		}
	}

	String encrypt(String p[],String s[][],String plain)
	{
		int i=0,j=0;
		String temp;
		String left=plain.substring(0,8);
		String right=plain.substring(8,16);
		//System.out.print("\nLeft:"+left);
		//System.out.print("\tRight:"+right);
		while(i<16)
		{
			//System.out.print("\nLeft:"+left+"\tP["+i+"]:"+p[i]);
			left=xor(p[i],left);
			/*while(left.length()<8)
				left=left+"0";//After xor(^) if last few bits are zeros they won't be in the string,so update them....
			if(left.equals("0"))
				left="00000000";//if xor value gives 0...*/
			//System.out.print("\nleft:"+left);
			temp=func(s,left);
			//System.out.print("\tTemp:"+temp);
			right=xor(temp,right);
			//System.out.print("\tRight:"+right);
			//while(right.length()<8)
			//	right=right+"0";
			temp=left;
			left=right;
			right=temp;
			i++;
		}
		temp=left;
		left=right;
		right=temp;
		left=xor(p[17],left);
		right=xor(p[16],right);
		/*while(left.length()<8)
			left=left+"0";
		while(right.length()<8)
			right=right+"0";*/
		//System.out.print("\nLeft:"+left+"\tRight:"+right);
		return (left+right).toUpperCase();//swapping after 17 and 18 xor..
	}

	int chartodec(char c)
	{
		int j=0;
		if(c>=97 && c<=102)
			c=(char)((int)c-32);
		if(c>=48 && c<=57)
			j=(int)c-48;
		else if(c>=65 && c<=70)
			j=(int)c-55;
		else
			return -1;
		return j;
	}

	String func(String s[][],String temp)
	{
		int s1,s2,s3,s4;
		//System.out.print("\nTemp in fun is:"+temp);
		String t1=temp.substring(0,2);
		String t2=temp.substring(2,4);
		String t3=temp.substring(4,6);
		String t4=temp.substring(6,8);
		//System.out.println("\n"+t1+t2+t3+t4);
		s1=16*(chartodec(t1.charAt(0)))+chartodec(t1.charAt(1));
		s2=16*(chartodec(t2.charAt(0)))+chartodec(t2.charAt(1));
		s3=16*(chartodec(t3.charAt(0)))+chartodec(t3.charAt(1));
		s4=16*(chartodec(t4.charAt(0)))+chartodec(t4.charAt(1));
		//System.out.println(s1+" "+s2+" "+s3+" "+s4);
		String sb1=s[0][s1];
		String sb2=s[1][s2];
		String sb3=s[2][s3];
		String sb4=s[3][s4];
		String res1=modadd(sb1,sb2);
		String res2=xor(res1,sb3);
		String res=modadd(res2,sb4);
		//System.out.print("\nsb4 is:"+sb4);
		//System.out.print("\nres1 is:"+res1+"\tres2 is:"+res2+"\tRes is :"+res);
		return res;
	}

	String xor(String p,String k)
	{
		conversion c=new conversion();
		int pr[]=c.hextobin(p);
		int kr[]=c.hextobin(k);
		int res[]=c.xor(pr,kr,pr.length);
		return c.bintohex(res);
	}

	String modadd(String s1,String s2)
	{
		long a=Long.parseLong(s1,16);
		long b=Long.parseLong(s2,16);
		String r=(Long.toHexString(a+b)).toUpperCase();
		if(r.length()<8)
			r="0"+r;//since if 1st bit is zero it isn't considered in addition so add them.. should have been in a while loop..
		if(r.length()>8)
			return r.substring(1,9);//to eliminate the carry bit...
		return r;
	}
}