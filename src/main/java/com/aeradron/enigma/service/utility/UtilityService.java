package com.aeradron.enigma.service.utility;

import org.mindrot.jbcrypt.BCrypt;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class UtilityService {

    public static boolean checkStringNotNull(String in) {
        boolean output = false;
        if (in != null && !in.isEmpty() && in.trim().length() != 0) {
            output = true;
        }
        return output;
    }

    public static boolean checkListNotNullOrEmpty(List<?> in) {
        boolean output = false;
        if (in != null && !in.isEmpty()) {
            output = true;
        }
        return output;
    }


    public static Date getISTDate() throws Exception{
        SimpleDateFormat dateTime = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        dateTime.setTimeZone(TimeZone.getTimeZone("IST"));
        String dateIST = dateTime.format(new Date());
        return new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").parse(dateIST);
    }

    public static String passwordEncoder(String plainPassword){
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt(Constants.BCRYPT_STRENGTH));
    }

    public static boolean passwordValidator(String plainPassword, String encodedPassword) {
        return BCrypt.checkpw(plainPassword, encodedPassword);
    }

    public static byte[] compressBytes(byte[] data) throws IOException{
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        outputStream.close();
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
        return outputStream.toByteArray();
    }

    public static byte[] decompressBytes(byte[] data) throws Exception {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!inflater.finished()) {
            int count = inflater.inflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        outputStream.close();

        return outputStream.toByteArray();
    }
}
