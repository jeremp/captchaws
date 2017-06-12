package com.jeremp.captchaws.business;

import org.junit.Test;

/**
 *
 * @author jeremp
 */
public class ImageCaptchaBuilderTest {

    private final ImageCaptchaBuilder captchaBuilder = new ImageCaptchaBuilder();
    
    @Test
    public void buildImageTest(){
        captchaBuilder.withFontSize(55).withCurve().withCurveColor(255, 0, 102).generate("Jeremy1203");
    }
    
}
