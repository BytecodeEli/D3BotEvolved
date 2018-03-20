package net.d3add3d.d3be;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

	    try {
		    Scanner sc = new Scanner(Main.class.getClassLoader().getResourceAsStream("token.txt"));
		    if(sc.hasNext()) {
		    	String token = sc.next();
		    	//System.err.println("!!!!!!!!!!!!!!!!!!!!!!!!!!DANGEROUS: " + token); //PRINTS OUT THE TOKEN, MAKE SURE IT IS COMMENTED OUT UNLESS NEEDED (ADD TO.DO: when using)
			    D3BotEvolved bot = new D3BotEvolved(token);
		    } else {
			    System.err.println("TOKEN NOT FOUND!!");
			    System.exit(1);
		    }
	    } catch (NullPointerException e) {
		    System.err.println("D3BotEvolved TOKEN NPE");
		    e.printStackTrace();
	    }
    }
}
