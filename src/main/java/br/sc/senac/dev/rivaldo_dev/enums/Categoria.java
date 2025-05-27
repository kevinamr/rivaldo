package br.sc.senac.dev.rivaldo_dev.enums;

public enum Categoria {
	APLICATIVO,
	PERIFERICO,
	HARDWARE,
	INTERNET;

    public static Categoria fromInt(int code) {
        return Categoria.values()[code];
    }

}
