package main;

public class Demo { 
    public static void main(String[] args) { 
        System.out.println(recoder.convenience.Format.toString("%N",
	    new recoder.CrossReferenceServiceConfiguration().getNameInfo()
	    	.getClassType("java.lang")
	    	.getAllSupertypes())); 
    } 
}
