package br.com.efrozza.apiexemplo.services.exceptions;

public class DataIntegretyVaiolationException extends RuntimeException {

    public DataIntegretyVaiolationException(String message) {
        super(message);
    }
}
