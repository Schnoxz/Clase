package ClasesConstructores;

public class ReferenciaYCopiaObjeto {
	// Cuenta c2 = c1; No copia al objeto ambas apuntan al mismo objeto en memoria, si modificas c2 también se modifica c1 porque son la misma referencia. Para copiar un objeto necesitas crear un nuevo objeto con los mismos valores (clonación o constructor de copia).
	
	Cuenta c1 = new Cuenta(100);
	Cuenta c2 = c1; // c2 y c1 apuntan al MISMO objeto
	c2.setSaldo(999);
	System.out.println(c1.getSaldo()); // 999 (mismo objeto!)
	// Para copiar un array:
	int[] a = {1, 2, 3};
	int[] b = a; // ALIAS (misma referencia)
	int[] c = a.clone(); // COPIA real (objetos independientes)
	b[0] = 50; // modifica a también
	c[1] = 100; // NO modifica a
	System.out.println(a[0]); // 50 (b sí lo modifica)
	System.out.println(a[1]); // 2 (c no lo modifica)
 }
}
