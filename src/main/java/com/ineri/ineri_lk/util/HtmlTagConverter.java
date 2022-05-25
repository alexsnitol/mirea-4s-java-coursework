package com.ineri.ineri_lk.util;

import lombok.experimental.UtilityClass;

/**
 * @author Slotin Alexander (@alexsnitol)
 */

@UtilityClass
public class HtmlTagConverter {

    public String convertLineBreakToTagBr(String str) {
        return str.replaceAll("\n", "</br>").replaceAll("\r", "");
    }

}
