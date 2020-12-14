package com.aeradron.enigma.service.utility;

public class Constants {

    public static final String ENIGMA_URL = "/v1/api";
    public static final String PATH_USER = ENIGMA_URL + "/users";
    public static final String PATH_USER_GET = "/user/{userUuid}";
    public static final String PATH_USER_POST = "/user";
    public static final String PATH_USER_DELETE = "/user/{userUuid}";
    public static final String PATH_IMAGE = ENIGMA_URL + "/images";
    public static final String PATH_IMAGE_POST = "/image";
    public static final String PATH_IMAGE_GET = "/image/{imageId}";

    public static final String USER_TYPE_NU = "NU";
    public static final String USER_TYPE_IU = "IU";

    public static final int BCRYPT_STRENGTH = 10;
}
