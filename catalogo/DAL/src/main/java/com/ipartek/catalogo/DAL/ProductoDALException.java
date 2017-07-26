package com.ipartek.catalogo.DAL;

public class ProductoDALException extends RuntimeException {

	private static final long serialVersionUID = -7536930890973019777L;

	public ProductoDALException() {
		super();
	}

	public ProductoDALException(String message) {
		super(message);
	}

	public ProductoDALException(Throwable cause) {
		super(cause);
	}

	public ProductoDALException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProductoDALException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

}
