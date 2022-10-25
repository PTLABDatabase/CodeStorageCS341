
public class Timer {
	static  long startTimeNanoSecond;
	static long startTimeMilliSecond = System.currentTimeMillis();
	static  long endTimeNanoSecond = System.nanoTime();
	static long endTimeMilliSecond = System.currentTimeMillis();

	public String start(){
		startTimeNanoSecond = System.nanoTime();
		startTimeMilliSecond = System.currentTimeMillis();
		return "Started" ;
	}
	
	public String end(){
		endTimeNanoSecond = System.nanoTime();
        endTimeMilliSecond = System.currentTimeMillis();
		return "Stopped" ;
	}
	
	public String time() {
		return "Time Taken "
				+ (endTimeMilliSecond - startTimeMilliSecond) + " ms";
	}
	
	
	public static void main(String[] args) {
		long startTimeNanoSecond = System.nanoTime();
		long startTimeMilliSecond = System.currentTimeMillis();

		for (int i = 0; i < 6; i++) {
			System.out.println("hello");
		}

		long endTimeNanoSecond = System.nanoTime();
		long endTimeMilliSecond = System.currentTimeMillis();

		System.out.println("Time Taken in " + (endTimeNanoSecond - startTimeNanoSecond) + " ns");
		System.out.println("Time Taken in " + (endTimeMilliSecond - startTimeMilliSecond) + " ms");

	}
	}