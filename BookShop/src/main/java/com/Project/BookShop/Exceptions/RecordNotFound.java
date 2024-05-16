package com.Project.BookShop.Exceptions;

public class RecordNotFound extends RuntimeException{
        RecordNotFound(String message) {
            super(message);
        }
}
