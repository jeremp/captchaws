package com.jeremp.captchaws.business;

import java.io.InputStream;

/**
 *
 * @author jeremp
 */
public interface CaptchaBuilder {

    /**
     *
     * @param phrase
     * @return
     */
    public InputStream generate(String phrase);
    
}
