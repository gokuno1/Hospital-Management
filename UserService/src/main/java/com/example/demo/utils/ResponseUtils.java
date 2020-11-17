package com.example.demo.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtils {
	
		public static <T> ResponseEntity<T> getOKResponse(T obj) {
			return returnResposne(obj, HttpStatus.OK);
		}

		public static <T> ResponseEntity<T> getCreatedResponse(T obj) {
			return returnResposne(obj, HttpStatus.CREATED);
		}

		public static <T> ResponseEntity<T> getBadRequestResponse(T obj) {
			return returnResposne(obj, HttpStatus.BAD_REQUEST);
		}

		public static <T> ResponseEntity<T> getUnAuthorizedResponse(T obj) {
			return returnResposne(obj, HttpStatus.UNAUTHORIZED);
		}

		public static <T> ResponseEntity<T> getNotFoundResponse(T obj) {
			return returnResposne(obj, HttpStatus.NOT_FOUND);
		}

		public static <T> ResponseEntity<T> getInternalServerErrorResponse(T obj) {
			return returnResposne(obj, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		public static <T> ResponseEntity<T> getNoContentResponse(T obj) {
			return returnResposne(obj, HttpStatus.NO_CONTENT);
		}

		public static <T> ResponseEntity<T> getAcceptedResponse(T obj) {
			return returnResposne(obj, HttpStatus.ACCEPTED);
		}

		private static <T> ResponseEntity<T> returnResposne(T obj, HttpStatus status) {
			return new ResponseEntity<T>(obj, status);
		}

	
}
