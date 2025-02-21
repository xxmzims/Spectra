package ru.ugrinovich.Spectra.exceptions.is_already_exist;

public class SerialNumberIsAlreadyExistException extends RuntimeException {
    public SerialNumberIsAlreadyExistException(String serialNumber) {
        super("This " + serialNumber + "is already exist");
    }
}
