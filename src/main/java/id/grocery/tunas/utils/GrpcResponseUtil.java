package id.grocery.tunas.utils;

import id.grocery.tunas.exception.ApiRequestException;

public class GrpcResponseUtil {
    public static final String STATUS_FAILED = "FAILED";
    public static final String STATUS_SUCCESS = "SUCCESS";
    public static void throwIfFailed(String status, String message){
        if(STATUS_FAILED.equalsIgnoreCase(status)){
            throw new ApiRequestException(message);
        }
    }
}
