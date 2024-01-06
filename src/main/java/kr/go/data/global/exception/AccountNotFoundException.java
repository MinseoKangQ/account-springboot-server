package kr.go.data.global.exception;

public class AccountNotFoundException extends RuntimeException{
    private String id;

    public AccountNotFoundException(String id) {
        this.id = id;
    }
}
