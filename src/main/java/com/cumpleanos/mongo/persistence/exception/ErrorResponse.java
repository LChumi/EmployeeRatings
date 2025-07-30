package com.cumpleanos.mongo.persistence.exception;

public record ErrorResponse(int status, String message) {
}