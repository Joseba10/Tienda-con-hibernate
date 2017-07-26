package com.ipartek.catalogo.DAL;

public class ProductoYaExisteDALException extends ProductoDALException {

	private static final long serialVersionUID = 1L;

	public ProductoYaExisteDALException() {
		super();
	}

	public ProductoYaExisteDALException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ProductoYaExisteDALException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProductoYaExisteDALException(String message) {
		super(message);
	}

	public ProductoYaExisteDALException(Throwable cause) {
		super(cause);
	}

}
