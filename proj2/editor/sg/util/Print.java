	package sg.util;

	public class Print {

	    public static void print(String[] args){

	        if (args.length < 1) {
	            System.out.println("No filename is provided!");
	            System.exit(1);
	        }
	        else if (args.length >= 2) {
	            String option = args[1];
	            if (option.equals("debug")) {
	                System.out.println("Debugging starts!");
	            }else{
	                System.out.println("The second argument can only be \"debug\"! ");
	                System.exit(1);
	            }
	        }
	    }
	}
