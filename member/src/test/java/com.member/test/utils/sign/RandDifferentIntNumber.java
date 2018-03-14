package com.member.test.utils.sign;

import java.util.ArrayList;

public class RandDifferentIntNumber {

	public static int [] getDifferentIntNumber(int n ,int t){
	    int [] retValue = new int[t];
	    ArrayList al = new ArrayList();
	    for (int i = 0; i <t ; i++) {
	      int tempValue = (int)Math.round(Math.random()*n);
	      if(tempValue>1&&tempValue<=n&&!al.contains(""+tempValue)){
	        al.add(""+tempValue);
	      }else{
	        i--;
	      }
	    }
	    for (int i = 0; i < al.size(); i++) {
	      retValue[i]=Integer.parseInt(""+al.get(i));
	    }
	    return retValue;
	  }

	/**
	 * @param args
	 */
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		int a[]=getDifferentIntNumber(200,1);
//		System.out.println(a[0]);

//	}

}
