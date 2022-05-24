package com.ineri.ineri_lk.util;

import lombok.experimental.UtilityClass;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class NewLineConverter {

    public List<String> convert(String str) {
        return Arrays.stream(str.split("\n")).collect(Collectors.toList());
    }

}
