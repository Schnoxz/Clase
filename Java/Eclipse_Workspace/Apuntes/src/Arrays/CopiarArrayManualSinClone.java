package Arrays;

public class CopiarArrayManualSinClone {
	
	int[] origen = {10, 20, 30};
	int[] destino = new int[origen.length];
	
	{
	
	for (int i = 0; i < origen.length; i++) {
		 destino[i] = origen[i];
	}
  }
}
