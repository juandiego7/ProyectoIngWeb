//Clase para manejar errores
package co.edu.udea.iw.exception;


import org.apache.log4j.Logger;
/**
 * Manejo de nuestras propias excepciones y mandarlas a una capa superior
 * @author Raul Antonio Martinez Silgado - rantonio.martinez@udea.edu.co
 * @author Juan Diego Goez Durango - diego.goez@udea.edu.co
 * @version 1.0
 */

public class MyException extends Exception {
	Logger log = Logger.getLogger(MyException.class);
	
	public MyException() {
		super();
	}

	public MyException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public MyException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		log.error(arg0,arg1);
		//log.log(Level.ERROR, arg1);
	}

	public MyException(String arg0) {
		super(arg0);
		log.error(arg0);
	}

	public MyException(Throwable arg0) {
		super(arg0);
		log.error(arg0);
	}

}
