package ru.ugrinovich.Spectra.exceptions.is_already_exist;

public class EmailAdressIsAlreadyExistException extends RuntimeException {
    public EmailAdressIsAlreadyExistException(String email) {
        super("This email " + email + " is already exist");
    }
}
