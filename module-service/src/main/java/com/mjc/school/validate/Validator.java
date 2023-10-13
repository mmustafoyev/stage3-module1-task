package com.mjc.school.validate;

import com.mjc.school.dto.NewsDTO;
import com.mjc.school.exception.InvalidNewsDataException;

public class Validator{
    public boolean validator(NewsDTO newsDTO) {
        try{

            // Validate title length
            if (newsDTO.getTitle().length() < 5 || newsDTO.getTitle().length() > 30) {
                throw new InvalidNewsDataException("Title length must be between 5 and 30 characters.");
            }

            // Validate content length
            else if (newsDTO.getContent().length() < 5 || newsDTO.getContent().length() > 255) {
                throw new InvalidNewsDataException("Content length must be between 5 and 255 characters.");
            }
        }catch (InvalidNewsDataException e){
            new RuntimeException();
            return false;
        }
        return true;
    }
}
