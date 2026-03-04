package Arrays;

public class RecorridoFor {
	
	int[] nums = {3, 7, 1, 9, 4};
	
	for (int i = 0; i < nums.length; i++) {
		System.out.println("Posición " + i + ": " + nums[i]);
	 
	}
	
	// Suma y media
	double suma = 0;
	for (int i = 0; i < nums.length; i++) {
	 suma += nums[i];
	 
	}
	
	double media = suma / nums.length;
	System.out.println("Media: " + media);

	}
}
