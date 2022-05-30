package com.ineri.ineri_lk.util;

import lombok.experimental.UtilityClass;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Slotin Alexander (@alexsnitol)
 */

@UtilityClass
public class NewLineConverter {

    public List<String> convert(String str) {
        if (str == null) {
            return null;
        }
        return Arrays.stream(str.split("\n")).collect(Collectors.toList());
    }

}
